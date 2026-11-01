package com.fingersoft.ads_sdk.advertising.providers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RelativeLayout;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.json.ApplicationInfo;
import com.fingersoft.ads_sdk.advertising.json.IResultContainer;
import com.fingersoft.ads_sdk.advertising.statistics.AdStatisticRecorder;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import com.google.android.gms.ads.AdSize;
import com.tonyodev.fetch.FetchService;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AdProvider {
    public static final int BANNER_PROVIDER_ADMOB = 0;
    public static final int BANNER_PROVIDER_AMAZON = 1;
    public static final int BANNER_PROVIDER_FACEBOOK = 2;
    public static final int BANNER_PROVIDER_FINGERSOFT = 3;
    public static final int BANNER_PROVIDER_INMOBI = 4;
    public static final int BANNER_PROVIDER_INNERACTIVE = 11;
    public static final int BANNER_PROVIDER_MADVERTISE = 5;
    public static final int BANNER_PROVIDER_MILLENNIAL = 6;
    public static final int BANNER_PROVIDER_MOBFOX = 7;
    public static final int BANNER_PROVIDER_MOPUB = 8;
    public static final int BANNER_PROVIDER_NOT_SET = -1;
    public static final int BANNER_PROVIDER_SAMSUNG = 9;
    public static final int BANNER_PROVIDER_SMAATO = 10;
    private static final int DEFAULT_BANNER_HEIGHT = 50;
    private static final int DEFAULT_BANNER_WIDTH = 320;
    private static final int[][] bannerSizeMap = {new int[]{BannerSizes.BANNER_120X20.getValue(), 120, 20}, new int[]{BannerSizes.BANNER_120X600.getValue(), 120, 600}, new int[]{BannerSizes.BANNER_168X28.getValue(), 168, 28}, new int[]{BannerSizes.BANNER_216X36.getValue(), 216, 36}, new int[]{BannerSizes.BANNER_300X50.getValue(), 300, 50}, new int[]{BannerSizes.BANNER_320X50.getValue(), 320, 50}, new int[]{BannerSizes.BANNER_300X250.getValue(), 300, 250}, new int[]{BannerSizes.BANNER_468X60.getValue(), 468, 60}, new int[]{BannerSizes.BANNER_480X60.getValue(), FetchService.QUERY_SINGLE, 60}, new int[]{BannerSizes.BANNER_728X90.getValue(), 728, 90}};
    protected AdProvider instance;
    private AdManager mAdManager = null;
    protected boolean isHidden = false;
    private long mLastTimeUsed = 0;
    protected boolean mTimerActive = false;
    private int mTimesShown = 0;
    protected int mShowLimit = 9999;
    private boolean adVisible = false;
    private boolean testMode = false;
    protected RelativeLayout.LayoutParams adParams = null;
    private boolean mForceStayOnCurrentProvider = false;
    private int mProviderID = -1;
    private boolean mImpressionRecorded = false;
    protected boolean portraitOnly = false;
    protected boolean createDone = false;

    protected abstract void closeInternal();

    protected abstract void createInternal(boolean z);

    public abstract String getName();

    protected abstract void hideInternal();

    protected abstract void pauseInternal();

    protected abstract void refreshInternal();

    protected abstract void resumeInternal();

    public abstract void setName(String str);

    protected abstract void showInternal();

    protected enum AdRequestErrors {
        ERROR_CODE_INTERNAL_ERROR(0),
        ERROR_CODE_INVALID_REQUEST(1),
        ERROR_CODE_NETWORK_ERROR(2),
        ERROR_CODE_NO_FILL(3);

        private final int value;

        AdRequestErrors(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static AdRequestErrors fromInteger(int i) {
            return values()[i];
        }
    }

    public boolean isAdVisible() {
        return this.adVisible;
    }

    public void setProviderID(int i) {
        this.mProviderID = i;
    }

    public int getProviderID() {
        return this.mProviderID;
    }

    public void updateName(int i) {
        if (i > 0) {
            setName(getName() + "" + (i + 1));
        }
    }

    private void setShowLimit(int i) {
        AdUtils.log("Show limit change DISABLED (" + i + " for " + getName());
    }

    protected class BackgroundTask extends AsyncTask<Void, Void, Void> {
        public String name;
        public boolean testMode;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            return null;
        }

        protected BackgroundTask() {
        }
    }

    public boolean canRefresh() {
        return this.mTimesShown < this.mShowLimit || this.mForceStayOnCurrentProvider;
    }

    public void incrementTimesShown() {
        this.mTimesShown++;
        AdUtils.log("Times shown: " + this.mTimesShown + " / " + this.mShowLimit);
    }

    public void _debugForceStayOnCurrentProvider(boolean z) {
        this.mForceStayOnCurrentProvider = z;
    }

    protected enum BannerSizes {
        BANNER_120X20(0),
        BANNER_120X600(1),
        BANNER_168X28(2),
        BANNER_216X36(3),
        BANNER_300X50(4),
        BANNER_320X50(5),
        BANNER_300X250(6),
        BANNER_468X60(7),
        BANNER_480X60(8),
        BANNER_728X90(9);

        private final int value;

        BannerSizes(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static BannerSizes fromInteger(int i) {
            return values()[i];
        }

        public static AdSize toAdSize(BannerSizes bannerSizes) {
            int i = AnonymousClass8.$SwitchMap$com$fingersoft$ads_sdk$advertising$providers$AdProvider$BannerSizes[bannerSizes.ordinal()];
            if (i == 1) {
                return AdSize.MEDIUM_RECTANGLE;
            }
            if (i == 2) {
                return AdSize.FULL_BANNER;
            }
            if (i == 3) {
                return AdSize.LEADERBOARD;
            }
            return AdSize.BANNER;
        }
    }

    /* JADX INFO: renamed from: com.fingersoft.ads_sdk.advertising.providers.AdProvider$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$AdProvider$BannerSizes = new int[BannerSizes.values().length];

        static {
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$AdProvider$BannerSizes[BannerSizes.BANNER_300X250.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$AdProvider$BannerSizes[BannerSizes.BANNER_468X60.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$AdProvider$BannerSizes[BannerSizes.BANNER_728X90.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected static int[] getBannerSizeValues(BannerSizes bannerSizes) {
        int value = bannerSizes.getValue();
        int[][] iArr = bannerSizeMap;
        int i = iArr[value][1];
        int i2 = iArr[value][2];
        AdUtils.log("Getting sizevalues " + bannerSizes + " val: " + bannerSizes.getValue());
        int[] iArr2 = {i, i2};
        AdUtils.log(i + "X" + i2);
        return iArr2;
    }

    @SuppressLint({"NewApi"})
    protected static BannerSizes getOptimalSlotSize(Activity activity, List<BannerSizes> list) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        double d = displayMetrics.density;
        double d2 = displayMetrics.widthPixels;
        double d3 = displayMetrics.heightPixels;
        Double.isNaN(d3);
        double d4 = d3 / 5.625d;
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics2);
            d = displayMetrics2.density;
            d2 = displayMetrics2.widthPixels;
            double d5 = displayMetrics2.heightPixels;
            Double.isNaN(d5);
            d4 = d5 / 5.625d;
            AdUtils.log("Used getRealMetrics to find optimal size");
        }
        if (d2 < d4) {
            AdUtils.log("width > height, assuming invalid portrait state, switching.");
            double d6 = d2;
            d2 = d4;
            d4 = d6;
        }
        AdUtils.log("Density: " + d + " " + d2 + "X" + d4);
        for (BannerSizes bannerSizes : list) {
            int value = bannerSizes.getValue();
            int[][] iArr = bannerSizeMap;
            int i = iArr[value][1];
            int i2 = iArr[value][2];
            AdUtils.log("Testing " + bannerSizes);
            double d7 = (double) i;
            Double.isNaN(d7);
            if (d7 * d <= d2) {
                double d8 = i2;
                Double.isNaN(d8);
                if (d8 * d <= d4) {
                    AdUtils.log("Optimal slot size found: " + bannerSizes);
                    return bannerSizes;
                }
            }
        }
        AdUtils.log("Default banner returned " + BannerSizes.BANNER_320X50);
        return BannerSizes.BANNER_320X50;
    }

    public AdProvider() {
        this.instance = null;
        this.instance = this;
    }

    public void setAdManager(AdManager adManager) {
        this.mAdManager = adManager;
    }

    protected int getXAlignment() {
        return this.mAdManager.getXAlignment();
    }

    protected int getYAlignment() {
        return this.mAdManager.getYAlignment();
    }

    protected AdManager getAdManager() {
        return this.mAdManager;
    }

    protected void onAdViewFailed() {
        this.mAdManager.onAdViewFailed(this.instance);
    }

    protected Activity getActivity() {
        return this.mAdManager.getActivity();
    }

    protected void trackPageView(String str) {
        this.mAdManager.trackPageView(str);
    }

    public boolean IsCreateDone() {
        return this.createDone;
    }

    protected RelativeLayout getAdTarget() {
        return this.mAdManager.getAdTarget();
    }

    public void create(boolean z) {
        ApplicationInfo applicationInfo;
        this.testMode = z;
        IResultContainer container = this.mAdManager.getContainer();
        if (container != null && (applicationInfo = container.getApplicationInfo()) != null) {
            int providerShowLimit = applicationInfo.getProviderShowLimit(getName());
            if (providerShowLimit > 0) {
                setShowLimit(providerShowLimit);
            } else {
                AdUtils.log("Show limit read failedf");
            }
        }
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r3) {
                if (AdProvider.this.adParams == null) {
                    AdProvider.this.adParams = new RelativeLayout.LayoutParams(-2, -2);
                    AdProvider.this.adParams.addRule(14);
                    AdProvider.this.adParams.addRule(12);
                    AdUtils.log("adParams initialized");
                }
                AdProvider.this.mTimesShown = 0;
                AdProvider.this.createInternal(this.testMode);
                AdUtils.log(this.name + " - After Ad Load");
            }
        };
        backgroundTask.testMode = z;
        backgroundTask.name = getName();
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    public final void pause() {
        AdUtils.log("Called AdPause on " + getName());
        this.adVisible = false;
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdProvider.this.pauseInternal();
            }
        };
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    @SuppressLint({"NewApi"})
    public final boolean resume() {
        AdUtils.log("Called AdResume on " + getName());
        if (!this.mImpressionRecorded) {
            this.mImpressionRecorded = true;
            Log.e("", "recording impression, wasnt recorded earlier");
            recordImpression();
        }
        if (this.mTimesShown > this.mShowLimit) {
            AdUtils.log("Can't resume, showlimit reached");
            this.mAdManager.onAdViewFailed(this.instance);
            return false;
        }
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdProvider.this.resumeInternal();
            }
        };
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
        return true;
    }

    public final void refresh() {
        ApplicationInfo applicationInfo;
        AdUtils.log("Refresh " + getName());
        IResultContainer container = this.mAdManager.getContainer();
        if (container != null && (applicationInfo = container.getApplicationInfo()) != null) {
            int providerShowLimit = applicationInfo.getProviderShowLimit(getName());
            if (providerShowLimit > 0) {
                setShowLimit(providerShowLimit);
            } else {
                AdUtils.log("Show limit read failed");
            }
        }
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdProvider.this.refreshInternal();
            }
        };
        backgroundTask.testMode = this.testMode;
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    public final void close() {
        this.adVisible = false;
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdProvider.this.closeInternal();
            }
        };
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    public void markUsage() {
        this.mLastTimeUsed = System.currentTimeMillis();
    }

    public long getTimeSinceLastUsed() {
        return System.currentTimeMillis() - this.mLastTimeUsed;
    }

    public void resetUsage() {
        this.mLastTimeUsed = 0L;
    }

    public final void show() {
        this.adVisible = true;
        Log.e("", "at show");
        if (!this.mImpressionRecorded) {
            Log.e("", "impression not recorded earlier, record now");
            recordImpression();
        }
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdProvider.this.showInternal();
            }
        };
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    public final void hide() {
        this.adVisible = false;
        BackgroundTask backgroundTask = new BackgroundTask() { // from class: com.fingersoft.ads_sdk.advertising.providers.AdProvider.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                AdUtils.log("HIDE INTERNAL");
                AdProvider.this.hideInternal();
            }
        };
        if (Build.VERSION.SDK_INT >= 11) {
            backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            backgroundTask.execute(new Void[0]);
        }
    }

    public void recordClick() {
        AdStatisticRecorder.instance().recordClick(getName());
    }

    public void recordImpression() {
        AdStatisticRecorder.instance().recordImpression(getName());
        this.mImpressionRecorded = true;
    }

    protected void onAdViewSuccess() {
        if (AdManager.getAdManagerState() != AdManager.AdManagerState.SHOW_ADS) {
            AdUtils.log("Ad loaded while not in show ads state. Cancelling show");
            pause();
            this.mImpressionRecorded = false;
            Log.e("", "did not record impression, recording in show/resume");
            return;
        }
        AdUtils.log("Another show internal? show ad");
        recordImpression();
        this.mAdManager.trackPageView("adreceived/" + this.instance.getName());
        this.mAdManager.onAdViewSuccess(this.instance);
    }
}
