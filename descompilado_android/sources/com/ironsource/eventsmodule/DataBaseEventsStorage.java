package com.ironsource.eventsmodule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.provider.BaseColumns;
import android.util.Log;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DataBaseEventsStorage extends SQLiteOpenHelper implements IEventsStorageHelper {
    private static final String COMMA_SEP = ",";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_TEXT = " TEXT";
    private static DataBaseEventsStorage mInstance;
    private final int DB_OPEN_BACKOFF_TIME;
    private final int DB_RETRY_NUM;
    private final String SQL_CREATE_ENTRIES;
    private final String SQL_DELETE_TABLE;

    public DataBaseEventsStorage(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.DB_RETRY_NUM = 4;
        this.DB_OPEN_BACKOFF_TIME = 400;
        this.SQL_DELETE_TABLE = "DROP TABLE IF EXISTS events";
        this.SQL_CREATE_ENTRIES = "CREATE TABLE events (_id INTEGER PRIMARY KEY,eventid INTEGER,timestamp INTEGER,type TEXT,data TEXT )";
    }

    public static synchronized DataBaseEventsStorage getInstance(Context context, String str, int i) {
        if (mInstance == null) {
            mInstance = new DataBaseEventsStorage(context, str, i);
        }
        return mInstance;
    }

    @Override // com.ironsource.eventsmodule.IEventsStorageHelper
    public synchronized void saveEvents(List<EventData> list, String str) {
        SQLiteDatabase dataBaseWithRetries;
        if (list != null) {
            if (!list.isEmpty()) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    try {
                        dataBaseWithRetries = getDataBaseWithRetries(true);
                        try {
                            Iterator<EventData> it = list.iterator();
                            while (it.hasNext()) {
                                ContentValues contentValuesForEvent = getContentValuesForEvent(it.next(), str);
                                if (dataBaseWithRetries != null && contentValuesForEvent != null) {
                                    dataBaseWithRetries.insert(EventEntry.TABLE_NAME, null, contentValuesForEvent);
                                }
                            }
                            if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                                dataBaseWithRetries.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                                dataBaseWithRetries.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dataBaseWithRetries = sQLiteDatabase;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.database.Cursor, android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v4 */
    @Override // com.ironsource.eventsmodule.IEventsStorageHelper
    public synchronized ArrayList<EventData> loadEvents(String str) {
        ArrayList<EventData> arrayList;
        SQLiteDatabase dataBaseWithRetries;
        arrayList = new ArrayList<>();
        ?? Query = 0;
        Query = 0;
        try {
            try {
                dataBaseWithRetries = getDataBaseWithRetries(false);
                try {
                    Query = dataBaseWithRetries.query(EventEntry.TABLE_NAME, null, "type = ?", new String[]{str}, null, null, "timestamp ASC");
                    if (Query.getCount() > 0) {
                        Query.moveToFirst();
                        while (!Query.isAfterLast()) {
                            arrayList.add(new EventData(Query.getInt(Query.getColumnIndex(EventEntry.COLUMN_NAME_EVENT_ID)), Query.getLong(Query.getColumnIndex("timestamp")), new JSONObject(Query.getString(Query.getColumnIndex("data")))));
                            Query.moveToNext();
                        }
                        Query.close();
                    }
                    if (Query != 0 && !Query.isClosed()) {
                        Query.close();
                    }
                    if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                        dataBaseWithRetries.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Log.e(IronSourceConstants.IRONSOURCE_CONFIG_NAME, "Exception while loading events: ", th);
                    if (Query != 0 && !Query.isClosed()) {
                        Query.close();
                    }
                    if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                    }
                    return arrayList;
                }
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0 && !Query.isClosed()) {
                    Query.close();
                }
                if (0 != 0 && Query.isOpen()) {
                    Query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                Query.close();
            }
            if (0 != 0) {
                Query.close();
            }
            throw th;
        }
        return arrayList;
    }

    @Override // com.ironsource.eventsmodule.IEventsStorageHelper
    public synchronized void clearEvents(String str) {
        SQLiteDatabase dataBaseWithRetries = null;
        String[] strArr = {str};
        try {
            try {
                dataBaseWithRetries = getDataBaseWithRetries(true);
                dataBaseWithRetries.delete(EventEntry.TABLE_NAME, "type = ?", strArr);
                if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                    dataBaseWithRetries.close();
                }
            } catch (Throwable th) {
                Log.e(IronSourceConstants.IRONSOURCE_CONFIG_NAME, "Exception while clearing events: ", th);
                if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                }
            }
        } catch (Throwable th2) {
            if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                dataBaseWithRetries.close();
            }
            throw th2;
        }
    }

    private ContentValues getContentValuesForEvent(EventData eventData, String str) {
        if (eventData == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues(4);
        contentValues.put(EventEntry.COLUMN_NAME_EVENT_ID, Integer.valueOf(eventData.getEventId()));
        contentValues.put("timestamp", Long.valueOf(eventData.getTimeStamp()));
        contentValues.put("type", str);
        contentValues.put("data", eventData.getAdditionalData());
        return contentValues;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY,eventid INTEGER,timestamp INTEGER,type TEXT,data TEXT )");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        onCreate(sQLiteDatabase);
    }

    private synchronized SQLiteDatabase getDataBaseWithRetries(boolean z) throws Throwable {
        int i = 0;
        while (true) {
            try {
                if (z) {
                    return getWritableDatabase();
                }
                return getReadableDatabase();
            } catch (Throwable th) {
                i++;
                if (i >= 4) {
                    throw th;
                }
                SystemClock.sleep(i * 400);
            }
        }
    }

    static abstract class EventEntry implements BaseColumns {
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_EVENT_ID = "eventid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final int NUMBER_OF_COLUMNS = 4;
        public static final String TABLE_NAME = "events";

        EventEntry() {
        }
    }
}
