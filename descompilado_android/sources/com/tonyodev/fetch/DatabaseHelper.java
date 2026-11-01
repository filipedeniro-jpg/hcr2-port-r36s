package com.tonyodev.fetch;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.tonyodev.fetch.exception.EnqueueException;

/* JADX INFO: loaded from: classes2.dex */
final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String COLUMN_DOWNLOADED_BYTES = "_written_bytes";
    private static final String COLUMN_ERROR = "_error";
    private static final String COLUMN_FILEPATH = "_file_path";
    private static final String COLUMN_FILE_SIZE = "_file_size";
    private static final String COLUMN_HEADERS = "_headers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRIORITY = "_priority";
    private static final String COLUMN_STATUS = "_status";
    private static final String COLUMN_URL = "_url";
    private static final String DB_NAME = "com_tonyodev_fetch.db";
    static final int EMPTY_COLUMN_VALUE = -1;
    static final int INDEX_COLUMN_DOWNLOADED_BYTES = 5;
    static final int INDEX_COLUMN_ERROR = 7;
    static final int INDEX_COLUMN_FILEPATH = 2;
    static final int INDEX_COLUMN_FILE_SIZE = 6;
    static final int INDEX_COLUMN_HEADERS = 4;
    static final int INDEX_COLUMN_ID = 0;
    static final int INDEX_COLUMN_PRIORITY = 8;
    static final int INDEX_COLUMN_STATUS = 3;
    static final int INDEX_COLUMN_URL = 1;
    private static final String TABLE_NAME = "requests";
    private static final int VERSION = 2;
    private static DatabaseHelper databaseHelper;
    private final SQLiteDatabase db;
    private boolean loggingEnabled;

    String getInsertStatementClose() {
        return ";";
    }

    String getInsertStatementOpen() {
        return "INSERT INTO requests ( _id, _url, _file_path, _status, _headers, _written_bytes, _file_size, _error, _priority ) VALUES ";
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        this.loggingEnabled = true;
        this.db = getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE requests ( _id INTEGER PRIMARY KEY NOT NULL, _url TEXT NOT NULL, _file_path TEXT NOT NULL, _status INTEGER NOT NULL, _headers TEXT NOT NULL, _written_bytes INTEGER NOT NULL, _file_size INTEGER NOT NULL, _error INTEGER NOT NULL, _priority INTEGER NOT NULL, unique( _file_path ) )");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != 1) {
            return;
        }
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX table_unique ON requests ( _file_path)");
    }

    static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseHelper != null) {
            return databaseHelper;
        }
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
        return databaseHelper;
    }

    synchronized boolean insert(long j, String str, String str2, int i, String str3, long j2, long j3, int i2, int i3) {
        return insert(getInsertStatementOpen() + getRowInsertStatement(j, str, str2, i, str3, j2, j3, i2, i3) + getInsertStatementClose());
    }

    String getRowInsertStatement(long j, String str, String str2, int i, String str3, long j2, long j3, int i2, int i3) {
        return "( " + j + ", " + DatabaseUtils.sqlEscapeString(str) + ", " + DatabaseUtils.sqlEscapeString(str2) + ", " + i + ", " + DatabaseUtils.sqlEscapeString(str3) + ", " + j2 + ", " + j3 + ", " + i3 + ", " + i2 + " )";
    }

    synchronized boolean insert(String str) {
        if (str == null) {
            return false;
        }
        try {
            try {
                try {
                    this.db.beginTransaction();
                    this.db.execSQL(str);
                    this.db.setTransactionSuccessful();
                    try {
                        this.db.endTransaction();
                        return true;
                    } catch (SQLiteException e) {
                        if (this.loggingEnabled) {
                            e.printStackTrace();
                        }
                        throw new EnqueueException(e.getMessage(), ErrorUtils.getCode(e.getMessage()));
                    }
                } catch (Exception e2) {
                    if (this.loggingEnabled) {
                        e2.printStackTrace();
                    }
                    throw new EnqueueException(e2.getMessage(), ErrorUtils.getCode(e2.getMessage()));
                }
            } catch (Throwable th) {
                try {
                    this.db.endTransaction();
                    throw th;
                } catch (SQLiteException e3) {
                    if (this.loggingEnabled) {
                        e3.printStackTrace();
                    }
                    throw new EnqueueException(e3.getMessage(), ErrorUtils.getCode(e3.getMessage()));
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    synchronized boolean pause(long j) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = 902 WHERE _id = " + j + " AND " + COLUMN_STATUS + " != " + FetchConst.STATUS_DONE + " AND " + COLUMN_STATUS + " != " + FetchConst.STATUS_ERROR);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                this.db.endTransaction();
                cursorRawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _id = " + j + " AND " + COLUMN_STATUS + " = " + FetchConst.STATUS_PAUSED, null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (SQLiteException e2) {
                if (this.loggingEnabled) {
                    e2.printStackTrace();
                }
                if (cursorRawQuery != null) {
                }
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
        return z;
    }

    synchronized boolean resume(long j) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = 900 WHERE _id = " + j + " AND " + COLUMN_STATUS + " != " + FetchConst.STATUS_DONE + " AND " + COLUMN_STATUS + " != " + FetchConst.STATUS_ERROR);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                this.db.endTransaction();
                cursorRawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _id = " + j + " AND " + COLUMN_STATUS + " = " + FetchConst.STATUS_QUEUED, null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (SQLiteException e2) {
                if (this.loggingEnabled) {
                    e2.printStackTrace();
                }
                if (cursorRawQuery != null) {
                }
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
        return z;
    }

    synchronized boolean updateStatus(long j, int i, int i2) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = " + i + ", " + COLUMN_ERROR + " = " + i2 + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    synchronized boolean updateFileBytes(long j, long j2, long j3) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _file_size = " + j3 + ", " + COLUMN_DOWNLOADED_BYTES + " = " + j2 + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    synchronized boolean delete(long j) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("DELETE FROM requests WHERE _id = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    synchronized boolean deleteAll() {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("DELETE FROM requests");
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    synchronized boolean setPriority(long j, int i) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _priority = " + i + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
            z = true;
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    synchronized boolean retry(long j) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = 900, _error = -1 WHERE _id = " + j + " AND " + COLUMN_STATUS + " = " + FetchConst.STATUS_ERROR);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                this.db.endTransaction();
                cursorRawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _id = " + j + " AND " + COLUMN_STATUS + " = " + FetchConst.STATUS_QUEUED, null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Throwable th) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
            if (cursorRawQuery != null) {
            }
        }
        return z;
    }

    synchronized boolean updateUrl(long j, String str) {
        boolean z;
        z = false;
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _url = " + DatabaseUtils.sqlEscapeString(str) + " WHERE " + COLUMN_ID + " = " + j);
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                this.db.endTransaction();
                cursorRawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _id = " + j + " AND " + COLUMN_URL + " = " + DatabaseUtils.sqlEscapeString(str), null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (SQLiteException e2) {
                if (this.loggingEnabled) {
                    e2.printStackTrace();
                }
                if (cursorRawQuery != null) {
                }
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
        return z;
    }

    synchronized Cursor get(long j) {
        try {
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _id = " + j, null);
    }

    synchronized Cursor get() {
        try {
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests", null);
    }

    synchronized Cursor get(long[] jArr) {
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            sb.append('(');
            if (jArr.length > 0) {
                for (long j : jArr) {
                    sb.append(j);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(')');
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _id IN " + sb.toString(), null);
    }

    synchronized Cursor getByStatus(int i) {
        try {
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _status = " + i, null);
    }

    synchronized Cursor getByUrlAndFilePath(String str, String str2) {
        try {
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            return null;
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _url = " + DatabaseUtils.sqlEscapeString(str) + " AND " + COLUMN_FILEPATH + " = " + DatabaseUtils.sqlEscapeString(str2) + " LIMIT 1", null);
    }

    synchronized Cursor getNextPendingRequest() {
        Cursor cursorRawQuery = this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 AND _priority = 601 LIMIT 1", null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
            return cursorRawQuery;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 LIMIT 1", null);
    }

    synchronized boolean hasPendingRequests() {
        boolean z;
        Cursor cursorRawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _status = 900 LIMIT 1", null);
        z = false;
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
            z = true;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return z;
    }

    synchronized void verifyOK() {
        try {
            this.db.beginTransaction();
            this.db.execSQL("UPDATE requests SET _status = 900 WHERE _status = 901");
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            this.db.endTransaction();
        } catch (SQLiteException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
    }

    synchronized void clean() {
        Cursor cursorRawQuery = this.db.rawQuery("SELECT _id, _file_path FROM requests WHERE _status = 903", null);
        if (cursorRawQuery == null) {
            return;
        }
        if (cursorRawQuery.getCount() < 1) {
            cursorRawQuery.close();
            return;
        }
        try {
            this.db.beginTransaction();
            cursorRawQuery.moveToFirst();
            while (!cursorRawQuery.isAfterLast()) {
                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(COLUMN_FILEPATH));
                if (string != null && !Utils.fileExist(string)) {
                    long j = cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(COLUMN_ID));
                    this.db.execSQL("UPDATE requests SET _status = 904, _error = -111 WHERE _id = " + j);
                }
                cursorRawQuery.moveToNext();
            }
            this.db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            try {
                this.db.endTransaction();
            } catch (SQLiteException e2) {
                if (this.loggingEnabled) {
                    e2.printStackTrace();
                }
            }
            cursorRawQuery.close();
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    synchronized void setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
    }
}
