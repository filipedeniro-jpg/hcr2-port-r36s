package com.ironsource.mediationsdk.utils;

import android.content.Context;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class DailyCappingManager {
    private static final int RAND_MINUTES = 10;
    private String mAdUnitName;
    private Context mContext;
    private DailyCappingListener mListener;
    private Timer mTimer = null;
    private Map<String, Integer> mSmashIdToMaxShowsPerDay = new HashMap();
    private Map<String, Integer> mSmashIdToCounter = new HashMap();
    private Map<String, String> mSmashIdToCounterDate = new HashMap();
    private IronSourceLoggerManager mLogger = IronSourceLoggerManager.getLogger();

    public DailyCappingManager(String str, DailyCappingListener dailyCappingListener) {
        this.mAdUnitName = str;
        this.mListener = dailyCappingListener;
        scheduleTimer();
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void addSmash(AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                if (abstractSmash.getMaxAdsPerDay() != 99) {
                    this.mSmashIdToMaxShowsPerDay.put(getUniqueId(abstractSmash), Integer.valueOf(abstractSmash.getMaxAdsPerDay()));
                }
            } catch (Exception e) {
                this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "addSmash", e);
            }
        }
    }

    public void increaseShowCounter(AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                String uniqueId = getUniqueId(abstractSmash);
                if (!this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                } else {
                    saveCounter(uniqueId, getTodayShowCount(uniqueId) + 1);
                }
            } catch (Exception e) {
                this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "increaseShowCounter", e);
            }
        }
    }

    public boolean shouldSendCapReleasedEvent(AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                try {
                    String uniqueId = getUniqueId(abstractSmash);
                    if (!this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                        return false;
                    }
                    if (getTodayDate().equalsIgnoreCase(getCounterDate(uniqueId))) {
                        return false;
                    }
                    return this.mSmashIdToMaxShowsPerDay.get(uniqueId).intValue() <= getCounter(uniqueId);
                } catch (Exception e) {
                    this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "shouldSendCapReleasedEvent", e);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isCapped(AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                try {
                    String uniqueId = getUniqueId(abstractSmash);
                    if (this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                        return this.mSmashIdToMaxShowsPerDay.get(uniqueId).intValue() <= getTodayShowCount(uniqueId);
                    }
                    return false;
                } catch (Exception e) {
                    this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "isCapped", e);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimerTick() {
        synchronized (this) {
            try {
                Iterator<String> it = this.mSmashIdToMaxShowsPerDay.keySet().iterator();
                while (it.hasNext()) {
                    zeroCounter(it.next());
                }
                this.mListener.onDailyCapReleased();
                scheduleTimer();
            } catch (Exception e) {
                this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "onTimerTick", e);
            }
        }
    }

    private void scheduleTimer() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.utils.DailyCappingManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DailyCappingManager.this.onTimerTick();
            }
        }, getUtcMidnight());
    }

    private Date getUtcMidnight() {
        Random random = new Random();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"), Locale.US);
        gregorianCalendar.set(11, 0);
        gregorianCalendar.set(12, random.nextInt(10));
        gregorianCalendar.set(13, random.nextInt(60));
        gregorianCalendar.set(14, random.nextInt(1000));
        gregorianCalendar.add(5, 1);
        return gregorianCalendar.getTime();
    }

    private int getTodayShowCount(String str) {
        if (!getTodayDate().equalsIgnoreCase(getCounterDate(str))) {
            zeroCounter(str);
        }
        return getCounter(str);
    }

    private String getCounterDate(String str) {
        if (this.mSmashIdToCounterDate.containsKey(str)) {
            return this.mSmashIdToCounterDate.get(str);
        }
        String stringFromSharedPrefs = IronSourceUtils.getStringFromSharedPrefs(this.mContext, getDayKeyName(str), getTodayDate());
        this.mSmashIdToCounterDate.put(str, stringFromSharedPrefs);
        return stringFromSharedPrefs;
    }

    private int getCounter(String str) {
        if (this.mSmashIdToCounter.containsKey(str)) {
            return this.mSmashIdToCounter.get(str).intValue();
        }
        int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(this.mContext, getCounterKeyName(str), 0);
        this.mSmashIdToCounter.put(str, Integer.valueOf(intFromSharedPrefs));
        return intFromSharedPrefs;
    }

    private void saveCounter(String str, int i) {
        this.mSmashIdToCounter.put(str, Integer.valueOf(i));
        this.mSmashIdToCounterDate.put(str, getTodayDate());
        IronSourceUtils.saveIntToSharedPrefs(this.mContext, getCounterKeyName(str), i);
        IronSourceUtils.saveStringToSharedPrefs(this.mContext, getDayKeyName(str), getTodayDate());
    }

    private void zeroCounter(String str) {
        this.mSmashIdToCounter.put(str, 0);
        this.mSmashIdToCounterDate.put(str, getTodayDate());
        IronSourceUtils.saveIntToSharedPrefs(this.mContext, getCounterKeyName(str), 0);
        IronSourceUtils.saveStringToSharedPrefs(this.mContext, getDayKeyName(str), getTodayDate());
    }

    private String getUniqueId(AbstractSmash abstractSmash) {
        return this.mAdUnitName + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + abstractSmash.getSubProviderId() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + abstractSmash.getName();
    }

    private String getCounterKeyName(String str) {
        return str + "_counter";
    }

    private String getDayKeyName(String str) {
        return str + "_day";
    }

    private String getTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }
}
