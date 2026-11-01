package com.ironsource.mediationsdk.events;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.eventsmodule.EventsSender;
import com.ironsource.eventsmodule.IEventsManager;
import com.ironsource.eventsmodule.IEventsSenderResultListener;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.IronSourceSegment;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseEventsManager implements IEventsManager {
    private static final String KEY_SESSION_DEPTH = "sessionDepth";
    int mAdUnitType;
    Set<Integer> mConnectivitySensitiveEventsSet;
    private Context mContext;
    private DataBaseEventsStorage mDbStorage;
    private EventThread mEventThread;
    String mEventType;
    private AbstractEventsFormatter mFormatter;
    String mFormatterType;
    private boolean mHasServerResponse;
    private ArrayList<EventData> mLocalEvents;
    private IronSourceLoggerManager mLoggerManager;
    private int[] mOptOutEvents;
    private IronSourceSegment mSegment;
    private ServerSegmetData mServerSegmentData;
    private String mSessionId;
    private int mTotalEvents;
    final int DEFAULT_BACKUP_THRESHOLD = 1;
    final int DEFAULT_MAX_NUMBER_OF_EVENTS = 100;
    final int DEFAULT_MAX_EVENTS_PER_BATCH = 5000;
    final int NO_CONNECTIVITY_EVENT_ID_ADDITION = 90000;
    final int DATABASE_VERSION = 5;
    final String DATABASE_NAME = "supersonic_sdk.db";
    final String KEY_PROVIDER = "provider";
    final String KEY_PLACEMENT = "placement";
    private final String MEDIATION_ABT = "abt";
    private boolean mHadTopPriorityEvent = false;
    private boolean mIsEventsEnabled = true;
    private int mMaxNumberOfEvents = 100;
    private int mMaxEventsPerBatch = 5000;
    private int mBackupThreshold = 1;
    private Map<String, String> mBatchParams = new HashMap();
    private Map<String, String> mGenericEventParams = new HashMap();
    private String mAbt = "";

    protected abstract String getCurrentPlacement(int i);

    protected abstract int getSessionDepth(EventData eventData);

    protected abstract boolean increaseSessionDepthIfNeeded(EventData eventData);

    protected void initConnectivitySensitiveEventsSet() {
    }

    protected abstract boolean isTopPriorityEvent(EventData eventData);

    protected abstract void setCurrentPlacement(EventData eventData);

    protected abstract boolean shouldExtractCurrentPlacement(EventData eventData);

    protected abstract boolean shouldIncludeCurrentPlacement(EventData eventData);

    static /* synthetic */ int access$908(BaseEventsManager baseEventsManager) {
        int i = baseEventsManager.mTotalEvents;
        baseEventsManager.mTotalEvents = i + 1;
        return i;
    }

    void initState() {
        this.mLocalEvents = new ArrayList<>();
        this.mTotalEvents = 0;
        this.mFormatter = EventsFormatterFactory.getFormatter(this.mFormatterType, this.mAdUnitType);
        this.mEventThread = new EventThread(this.mEventType + "EventThread");
        this.mEventThread.start();
        this.mEventThread.prepareHandler();
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mSessionId = IronSourceObject.getInstance().getSessionId();
        this.mConnectivitySensitiveEventsSet = new HashSet();
        initConnectivitySensitiveEventsSet();
    }

    public synchronized void start(Context context, IronSourceSegment ironSourceSegment) {
        this.mFormatterType = IronSourceUtils.getDefaultEventsFormatterType(context, this.mEventType, this.mFormatterType);
        verifyCurrentFormatter(this.mFormatterType);
        this.mFormatter.setEventsServerUrl(IronSourceUtils.getDefaultEventsURL(context, this.mEventType, null));
        this.mDbStorage = DataBaseEventsStorage.getInstance(context, "supersonic_sdk.db", 5);
        backupEventsToDb();
        this.mOptOutEvents = IronSourceUtils.getDefaultOptOutEvents(context, this.mEventType);
        this.mSegment = ironSourceSegment;
        this.mContext = context;
    }

    public synchronized void setServerSegmentData(ServerSegmetData serverSegmetData) {
        this.mServerSegmentData = serverSegmetData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isNoConnectivitydEvent(String str, EventData eventData) {
        return str.equalsIgnoreCase("none") && this.mConnectivitySensitiveEventsSet.contains(Integer.valueOf(eventData.getEventId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int convertEventtoNotConnected(EventData eventData) {
        return eventData.getEventId() + 90000;
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public synchronized void log(final EventData eventData) {
        this.mEventThread.postTask(new Runnable() { // from class: com.ironsource.mediationsdk.events.BaseEventsManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (eventData == null || !BaseEventsManager.this.mIsEventsEnabled) {
                    return;
                }
                eventData.addToAdditionalData("eventSessionId", BaseEventsManager.this.mSessionId);
                String connectionType = IronSourceUtils.getConnectionType(BaseEventsManager.this.mContext);
                if (eventData.getEventId() != 40 && eventData.getEventId() != 41) {
                    eventData.addToAdditionalData(Constants.RequestParameters.CONNECTION_TYPE, connectionType);
                }
                if (BaseEventsManager.this.isNoConnectivitydEvent(connectionType, eventData)) {
                    EventData eventData2 = eventData;
                    eventData2.setEventId(BaseEventsManager.this.convertEventtoNotConnected(eventData2));
                }
                if (!BaseEventsManager.this.getGenericEventParams().isEmpty()) {
                    for (Map.Entry<String, String> entry : BaseEventsManager.this.getGenericEventParams().entrySet()) {
                        if (!eventData.getAdditionalDataJSON().has(entry.getKey()) && entry.getKey() != "eventId" && entry.getKey() != "timestamp") {
                            eventData.addToAdditionalData(entry.getKey(), entry.getValue());
                        }
                    }
                }
                try {
                    BaseEventsManager.this.mLoggerManager.log(IronSourceLogger.IronSourceTag.EVENT, ("{\"eventId\":" + eventData.getEventId() + ",\"timestamp\":" + eventData.getTimeStamp() + "," + eventData.getAdditionalData().substring(1)).replace(",", "\n"), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (BaseEventsManager.this.shouldEventBeLogged(eventData)) {
                    if (BaseEventsManager.this.shouldAddSessionDepth(eventData)) {
                        int sessionDepth = BaseEventsManager.this.getSessionDepth(eventData);
                        if (BaseEventsManager.this.increaseSessionDepthIfNeeded(eventData)) {
                            sessionDepth = BaseEventsManager.this.getSessionDepth(eventData);
                        }
                        eventData.addToAdditionalData("sessionDepth", Integer.valueOf(sessionDepth));
                    }
                    if (BaseEventsManager.this.shouldExtractCurrentPlacement(eventData)) {
                        BaseEventsManager.this.setCurrentPlacement(eventData);
                    } else if (!TextUtils.isEmpty(BaseEventsManager.this.getCurrentPlacement(eventData.getEventId())) && BaseEventsManager.this.shouldIncludeCurrentPlacement(eventData)) {
                        EventData eventData3 = eventData;
                        eventData3.addToAdditionalData("placement", BaseEventsManager.this.getCurrentPlacement(eventData3.getEventId()));
                    }
                    BaseEventsManager.this.mLocalEvents.add(eventData);
                    BaseEventsManager.access$908(BaseEventsManager.this);
                }
                boolean zIsTopPriorityEvent = BaseEventsManager.this.isTopPriorityEvent(eventData);
                if (!BaseEventsManager.this.mHadTopPriorityEvent && zIsTopPriorityEvent) {
                    BaseEventsManager.this.mHadTopPriorityEvent = true;
                }
                if (BaseEventsManager.this.mDbStorage != null) {
                    if (BaseEventsManager.this.shouldSendEvents()) {
                        BaseEventsManager.this.sendEvents();
                        return;
                    }
                    BaseEventsManager baseEventsManager = BaseEventsManager.this;
                    if (baseEventsManager.shouldBackupEventsToDb(baseEventsManager.mLocalEvents) || zIsTopPriorityEvent) {
                        BaseEventsManager.this.backupEventsToDb();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void sendEvents() {
        this.mHadTopPriorityEvent = false;
        ArrayList<EventData> arrayListInitCombinedEventList = initCombinedEventList(this.mLocalEvents, this.mDbStorage.loadEvents(this.mEventType), this.mMaxEventsPerBatch);
        this.mLocalEvents.clear();
        this.mDbStorage.clearEvents(this.mEventType);
        this.mTotalEvents = 0;
        if (arrayListInitCombinedEventList.size() > 0) {
            JSONObject json = GeneralProperties.getProperties().toJSON();
            try {
                updateSegmentsData(json);
                String abt = getAbt();
                if (!TextUtils.isEmpty(abt)) {
                    json.put("abt", abt);
                }
                Map<String, String> batchParams = getBatchParams();
                if (!batchParams.isEmpty()) {
                    for (Map.Entry<String, String> entry : batchParams.entrySet()) {
                        if (!json.has(entry.getKey())) {
                            json.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new EventsSender(new IEventsSenderResultListener() { // from class: com.ironsource.mediationsdk.events.BaseEventsManager.2
                @Override // com.ironsource.eventsmodule.IEventsSenderResultListener
                public synchronized void onEventsSenderResult(final ArrayList<EventData> arrayList, final boolean z) {
                    BaseEventsManager.this.mEventThread.postTask(new Runnable() { // from class: com.ironsource.mediationsdk.events.BaseEventsManager.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                ArrayList<EventData> arrayListLoadEvents = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType);
                                BaseEventsManager.this.mTotalEvents = arrayListLoadEvents.size() + BaseEventsManager.this.mLocalEvents.size();
                            } else if (arrayList != null) {
                                BaseEventsManager.this.mDbStorage.saveEvents(arrayList, BaseEventsManager.this.mEventType);
                                ArrayList<EventData> arrayListLoadEvents2 = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType);
                                BaseEventsManager.this.mTotalEvents = arrayListLoadEvents2.size() + BaseEventsManager.this.mLocalEvents.size();
                            }
                        }
                    });
                }
            }).execute(this.mFormatter.format(arrayListInitCombinedEventList, json), this.mFormatter.getEventsServerUrl(), arrayListInitCombinedEventList);
        }
    }

    private ArrayList<EventData> initCombinedEventList(ArrayList<EventData> arrayList, ArrayList<EventData> arrayList2, int i) {
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList);
        arrayList3.addAll(arrayList2);
        Collections.sort(arrayList3, new Comparator<EventData>() { // from class: com.ironsource.mediationsdk.events.BaseEventsManager.3
            @Override // java.util.Comparator
            public int compare(EventData eventData, EventData eventData2) {
                return eventData.getTimeStamp() >= eventData2.getTimeStamp() ? 1 : -1;
            }
        });
        if (arrayList3.size() <= i) {
            return new ArrayList<>(arrayList3);
        }
        ArrayList<EventData> arrayList4 = new ArrayList<>(arrayList3.subList(0, i));
        this.mDbStorage.saveEvents(arrayList3.subList(i, arrayList3.size()), this.mEventType);
        return arrayList4;
    }

    private void verifyCurrentFormatter(String str) {
        AbstractEventsFormatter abstractEventsFormatter = this.mFormatter;
        if (abstractEventsFormatter == null || !abstractEventsFormatter.getFormatterType().equals(str)) {
            this.mFormatter = EventsFormatterFactory.getFormatter(str, this.mAdUnitType);
        }
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setBackupThreshold(int i) {
        if (i > 0) {
            this.mBackupThreshold = i;
        }
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setMaxNumberOfEvents(int i) {
        if (i > 0) {
            this.mMaxNumberOfEvents = i;
        }
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setMaxEventsPerBatch(int i) {
        if (i > 0) {
            this.mMaxEventsPerBatch = i;
        }
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setOptOutEvents(int[] iArr, Context context) {
        this.mOptOutEvents = iArr;
        IronSourceUtils.saveDefaultOptOutEvents(context, this.mEventType, iArr);
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setEventsUrl(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AbstractEventsFormatter abstractEventsFormatter = this.mFormatter;
        if (abstractEventsFormatter != null) {
            abstractEventsFormatter.setEventsServerUrl(str);
        }
        IronSourceUtils.saveDefaultEventsURL(context, this.mEventType, str);
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setFormatterType(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mFormatterType = str;
        IronSourceUtils.saveDefaultEventsFormatterType(context, this.mEventType, str);
        verifyCurrentFormatter(str);
    }

    @Override // com.ironsource.eventsmodule.IEventsManager
    public void setIsEventsEnabled(boolean z) {
        this.mIsEventsEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void backupEventsToDb() {
        this.mDbStorage.saveEvents(this.mLocalEvents, this.mEventType);
        this.mLocalEvents.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldSendEvents() {
        return (this.mTotalEvents >= this.mMaxNumberOfEvents || this.mHadTopPriorityEvent) && this.mHasServerResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBackupEventsToDb(ArrayList<EventData> arrayList) {
        return arrayList != null && arrayList.size() >= this.mBackupThreshold;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldEventBeLogged(EventData eventData) {
        int[] iArr;
        if (eventData != null && (iArr = this.mOptOutEvents) != null && iArr.length > 0) {
            int eventId = eventData.getEventId();
            int i = 0;
            while (true) {
                int[] iArr2 = this.mOptOutEvents;
                if (i >= iArr2.length) {
                    break;
                }
                if (eventId == iArr2[i]) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public void setHasServerResponse(boolean z) {
        this.mHasServerResponse = z;
    }

    String getProviderNameForEvent(EventData eventData) {
        try {
            return new JSONObject(eventData.getAdditionalData()).optString("provider", "");
        } catch (JSONException unused) {
            return "";
        }
    }

    public void triggerEventsSend() {
        sendEvents();
    }

    public void sendEventToUrl(EventData eventData, String str) {
        try {
            ArrayList<EventData> arrayList = new ArrayList<>();
            arrayList.add(eventData);
            new EventsSender().execute(this.mFormatter.format(arrayList, GeneralProperties.getProperties().toJSON()), str, null);
        } catch (Exception unused) {
        }
    }

    private void updateSegmentsData(JSONObject jSONObject) {
        try {
            if (this.mSegment != null) {
                if (this.mSegment.getAge() > 0) {
                    jSONObject.put(IronSourceSegment.AGE, this.mSegment.getAge());
                }
                if (!TextUtils.isEmpty(this.mSegment.getGender())) {
                    jSONObject.put(IronSourceSegment.GENDER, this.mSegment.getGender());
                }
                if (this.mSegment.getLevel() > 0) {
                    jSONObject.put(IronSourceSegment.LEVEL, this.mSegment.getLevel());
                }
                if (this.mSegment.getIsPaying() != null) {
                    jSONObject.put(IronSourceSegment.PAYING, this.mSegment.getIsPaying().get());
                }
                if (this.mSegment.getIapt() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    jSONObject.put(IronSourceSegment.IAPT, this.mSegment.getIapt());
                }
                if (this.mSegment.getUcd() > 0) {
                    jSONObject.put(IronSourceSegment.USER_CREATION_DATE, this.mSegment.getUcd());
                }
            }
            if (this.mServerSegmentData != null) {
                String segmentId = this.mServerSegmentData.getSegmentId();
                if (!TextUtils.isEmpty(segmentId)) {
                    jSONObject.put("segmentId", segmentId);
                }
                JSONObject customSegments = this.mServerSegmentData.getCustomSegments();
                Iterator<String> itKeys = customSegments.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject.put(next, customSegments.get(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setABT(String str) {
        this.mAbt = str;
    }

    public String getAbt() {
        return this.mAbt;
    }

    public void setBatchParams(Map<String, String> map) {
        this.mBatchParams.putAll(map);
    }

    public Map<String, String> getBatchParams() {
        return this.mBatchParams;
    }

    public void setEventGenericParams(Map<String, String> map) {
        this.mGenericEventParams.putAll(map);
    }

    public Map<String, String> getGenericEventParams() {
        return this.mGenericEventParams;
    }

    private class EventThread extends HandlerThread {
        private Handler mHandler;

        EventThread(String str) {
            super(str);
        }

        void postTask(Runnable runnable) {
            this.mHandler.post(runnable);
        }

        void prepareHandler() {
            this.mHandler = new Handler(getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldAddSessionDepth(EventData eventData) {
        return (eventData.getEventId() == 14 || eventData.getEventId() == 514 || eventData.getEventId() == 140 || eventData.getEventId() == 40 || eventData.getEventId() == 41) ? false : true;
    }
}
