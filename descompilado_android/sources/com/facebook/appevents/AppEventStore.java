package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/* JADX INFO: loaded from: classes.dex */
class AppEventStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static final String TAG = "com.facebook.appevents.AppEventStore";

    AppEventStore() {
    }

    public static synchronized void persistEvents(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        AppEventUtility.assertIsNotMainThread();
        PersistedEvents andClearStore = readAndClearStore();
        if (andClearStore.containsKey(accessTokenAppIdPair)) {
            andClearStore.get(accessTokenAppIdPair).addAll(sessionEventsState.getEventsToPersist());
        } else {
            andClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
        }
        saveEventsToDisk(andClearStore);
    }

    public static synchronized void persistEvents(AppEventCollection appEventCollection) {
        AppEventUtility.assertIsNotMainThread();
        PersistedEvents andClearStore = readAndClearStore();
        for (AccessTokenAppIdPair accessTokenAppIdPair : appEventCollection.keySet()) {
            andClearStore.addEvents(accessTokenAppIdPair, appEventCollection.get(accessTokenAppIdPair).getEventsToPersist());
        }
        saveEventsToDisk(andClearStore);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008a A[Catch: all -> 0x0091, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0021, B:9:0x0024, B:42:0x008a, B:12:0x002f, B:21:0x0045, B:22:0x0048, B:25:0x0053, B:26:0x0057, B:29:0x005e, B:30:0x0061, B:34:0x0073, B:33:0x006c, B:36:0x0075, B:37:0x0078, B:40:0x0083), top: B:51:0x0003, inners: #1, #4, #9, #10 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    public static synchronized PersistedEvents readAndClearStore() {
        MovedClassObjectInputStream movedClassObjectInputStream;
        String str;
        String str2;
        PersistedEvents persistedEvents;
        Object obj;
        Object obj2;
        Object obj3;
        AppEventUtility.assertIsNotMainThread();
        ?? applicationContext = FacebookSdk.getApplicationContext();
        ?? r2 = 0;
        persistedEvents = null;
        persistedEvents = null;
        persistedEvents = null;
        try {
            try {
                movedClassObjectInputStream = new MovedClassObjectInputStream(new BufferedInputStream(applicationContext.openFileInput(PERSISTED_EVENTS_FILENAME)));
                try {
                    PersistedEvents persistedEvents2 = (PersistedEvents) movedClassObjectInputStream.readObject();
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        File fileStreamPath = applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME);
                        fileStreamPath.delete();
                        obj3 = fileStreamPath;
                        obj2 = movedClassObjectInputStream;
                    } catch (Exception e) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e);
                        obj3 = e;
                        obj2 = "Got unexpected exception when removing events file: ";
                    }
                    persistedEvents = persistedEvents2;
                    applicationContext = obj3;
                    obj = obj2;
                } catch (FileNotFoundException unused) {
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e2) {
                        e = e2;
                        str = TAG;
                        str2 = "Got unexpected exception when removing events file: ";
                        Log.w(str, str2, e);
                    }
                    if (persistedEvents == null) {
                        persistedEvents = new PersistedEvents();
                    }
                    return persistedEvents;
                } catch (Exception e3) {
                    e = e3;
                    Log.w(TAG, "Got unexpected exception while reading events: ", e);
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        File fileStreamPath2 = applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME);
                        fileStreamPath2.delete();
                        applicationContext = fileStreamPath2;
                        obj = movedClassObjectInputStream;
                    } catch (Exception e4) {
                        e = e4;
                        str = TAG;
                        str2 = "Got unexpected exception when removing events file: ";
                        Log.w(str, str2, e);
                    }
                }
            } catch (Throwable th) {
                th = th;
                r2 = obj;
                Utility.closeQuietly(r2);
                try {
                    applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                } catch (Exception e5) {
                    Log.w(TAG, "Got unexpected exception when removing events file: ", e5);
                }
                throw th;
            }
        } catch (FileNotFoundException unused2) {
            movedClassObjectInputStream = null;
        } catch (Exception e6) {
            e = e6;
            movedClassObjectInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            Utility.closeQuietly(r2);
            applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
            throw th;
        }
        if (persistedEvents == null) {
            persistedEvents = new PersistedEvents();
        }
        return persistedEvents;
    }

    private static void saveEventsToDisk(PersistedEvents persistedEvents) throws Throwable {
        Context applicationContext = FacebookSdk.getApplicationContext();
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
                try {
                    objectOutputStream2.writeObject(persistedEvents);
                    Utility.closeQuietly(objectOutputStream2);
                } catch (Exception e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    Log.w(TAG, "Got unexpected exception while persisting events: ", e);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception unused) {
                    }
                    Utility.closeQuietly(objectOutputStream);
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    Utility.closeQuietly(objectOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";

        public MovedClassObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected ObjectStreamClass readClassDescriptor() throws ClassNotFoundException, IOException {
            ObjectStreamClass classDescriptor = super.readClassDescriptor();
            if (classDescriptor.getName().equals(ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                return ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            }
            return classDescriptor.getName().equals(APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME) ? ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class) : classDescriptor;
        }
    }
}
