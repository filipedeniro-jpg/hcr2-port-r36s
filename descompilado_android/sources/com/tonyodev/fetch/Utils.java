package com.tonyodev.fetch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.tonyodev.fetch.callback.FetchTask;
import com.tonyodev.fetch.exception.InvalidStatusException;
import com.tonyodev.fetch.exception.NotUsableException;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class Utils {
    private Utils() {
    }

    static void throwIfFetchTaskNull(FetchTask fetchTask) {
        if (fetchTask == null) {
            throw new NullPointerException("FetchTask cannot be null");
        }
    }

    static void throwIfInvalidStatus(int i) {
        if (i != -900) {
            switch (i) {
                case FetchConst.STATUS_QUEUED /* 900 */:
                case FetchConst.STATUS_DOWNLOADING /* 901 */:
                case FetchConst.STATUS_PAUSED /* 902 */:
                case FetchConst.STATUS_DONE /* 903 */:
                case FetchConst.STATUS_ERROR /* 904 */:
                case FetchConst.STATUS_REMOVED /* 905 */:
                    return;
                default:
                    throw new InvalidStatusException(i + " is not a valid status ", -114);
            }
        }
    }

    static boolean hasIntervalElapsed(long j, long j2, long j3) {
        return TimeUnit.NANOSECONDS.toMillis(j2 - j) >= j3;
    }

    static int getProgress(long j, long j2) {
        if (j2 < 1 || j < 1) {
            return 0;
        }
        if (j >= j2) {
            return 100;
        }
        double d = j;
        double d2 = j2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (int) ((d / d2) * 100.0d);
    }

    static String headerListToString(List<Header> list, boolean z) {
        if (list == null) {
            return "{}";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (Header header : list) {
                jSONObject.put(header.getHeader(), header.getValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            if (!z) {
                return "{}";
            }
            e.printStackTrace();
            return "{}";
        }
    }

    static List<Header> headerStringToList(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                arrayList.add(new Header(next, jSONObject.getString(next)));
            }
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    static boolean isOnWiFi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static boolean createFileIfNotExist(String str) throws IOException, NullPointerException {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.createNewFile();
    }

    static boolean createDirIfNotExist(String str) throws NullPointerException {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    static boolean deleteFile(String str) {
        return new File(str).delete();
    }

    static long getFileSize(String str) {
        return new File(str).length();
    }

    static boolean fileExist(String str) {
        return new File(str).exists();
    }

    static File getFile(String str) {
        return new File(str);
    }

    static void createFileOrThrow(String str) throws IOException, NullPointerException {
        File file = getFile(str);
        boolean zCreateDirIfNotExist = createDirIfNotExist(file.getParentFile().getAbsolutePath());
        boolean zCreateFileIfNotExist = createFileIfNotExist(file.getAbsolutePath());
        if (zCreateDirIfNotExist && zCreateFileIfNotExist) {
            return;
        }
        throw new IOException("File could not be created for the filePath:" + str);
    }

    static void throwIfNotUsable(Fetch fetch) {
        if (fetch == null) {
            throw new NullPointerException("Fetch cannot be null");
        }
        if (fetch.isReleased()) {
            throw new NotUsableException("Fetch instance: " + fetch.toString() + " cannot be reused after calling its release() method.Call Fetch.getInstance() for a new instance of Fetch.", -115);
        }
    }

    static RequestInfo cursorToRequestInfo(Cursor cursor, boolean z, boolean z2) {
        RequestInfo requestInfoCreateRequestInfo = null;
        try {
            if (cursor != null) {
                try {
                    if (!cursor.isClosed() && cursor.getCount() >= 1) {
                        cursor.moveToFirst();
                        requestInfoCreateRequestInfo = createRequestInfo(cursor, z2);
                        if (cursor != null && z) {
                            cursor.close();
                        }
                        return requestInfoCreateRequestInfo;
                    }
                } catch (Exception e) {
                    if (z2) {
                        e.printStackTrace();
                    }
                    if (cursor != null && z) {
                    }
                }
            }
            if (cursor != null && z) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (cursor != null && z) {
                cursor.close();
            }
            throw th;
        }
    }

    static List<RequestInfo> cursorToRequestInfoList(Cursor cursor, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            try {
                try {
                    if (!cursor.isClosed() && cursor.getCount() >= 1) {
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            arrayList.add(createRequestInfo(cursor, z2));
                            cursor.moveToNext();
                        }
                        return arrayList;
                    }
                } catch (Exception e) {
                    if (z2) {
                        e.printStackTrace();
                    }
                }
            } finally {
                if (cursor != null && z) {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    static RequestInfo createRequestInfo(Cursor cursor, boolean z) {
        if (cursor == null || cursor.isClosed() || cursor.getCount() < 1) {
            return null;
        }
        long j = cursor.getLong(0);
        int i = cursor.getInt(3);
        String string = cursor.getString(1);
        String string2 = cursor.getString(2);
        int i2 = cursor.getInt(7);
        long j2 = cursor.getLong(6);
        int i3 = cursor.getInt(8);
        long j3 = cursor.getLong(5);
        return new RequestInfo(j, i, string, string2, getProgress(j3, j2), j3, j2, i2, headerStringToList(cursor.getString(4), z), i3);
    }

    static ArrayList<Bundle> cursorToQueryResultList(Cursor cursor, boolean z, boolean z2) {
        Cursor cursor2 = cursor;
        ArrayList<Bundle> arrayList = new ArrayList<>();
        if (cursor2 != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        long j = cursor2.getLong(0);
                        int i = cursor2.getInt(3);
                        String string = cursor2.getString(1);
                        String string2 = cursor2.getString(2);
                        int i2 = cursor2.getInt(7);
                        long j2 = cursor2.getLong(6);
                        int i3 = cursor2.getInt(8);
                        long j3 = cursor2.getLong(5);
                        ArrayList<Bundle> arrayListHeadersToBundleList = headersToBundleList(cursor2.getString(4), z2);
                        int progress = getProgress(j3, j2);
                        Bundle bundle = new Bundle();
                        bundle.putLong(FetchService.EXTRA_ID, j);
                        bundle.putInt(FetchService.EXTRA_STATUS, i);
                        bundle.putString(FetchService.EXTRA_URL, string);
                        bundle.putString(FetchService.EXTRA_FILE_PATH, string2);
                        bundle.putInt(FetchService.EXTRA_ERROR, i2);
                        bundle.putLong(FetchService.EXTRA_DOWNLOADED_BYTES, j3);
                        bundle.putLong(FetchService.EXTRA_FILE_SIZE, j2);
                        bundle.putInt(FetchService.EXTRA_PROGRESS, progress);
                        bundle.putInt(FetchService.EXTRA_PRIORITY, i3);
                        bundle.putParcelableArrayList(FetchService.EXTRA_HEADERS, arrayListHeadersToBundleList);
                        arrayList.add(bundle);
                        cursor.moveToNext();
                        cursor2 = cursor;
                    }
                    return arrayList;
                }
            } catch (Exception e) {
                if (z2) {
                    e.printStackTrace();
                }
            } finally {
                if (cursor != null && z) {
                    cursor.close();
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    static long generateRequestId() {
        return System.nanoTime();
    }

    static void sendEventUpdate(LocalBroadcastManager localBroadcastManager, long j, int i, int i2, long j2, long j3, int i3) {
        if (localBroadcastManager == null) {
            return;
        }
        Intent intent = new Intent(FetchService.EVENT_ACTION_UPDATE);
        intent.putExtra(FetchService.EXTRA_ID, j);
        intent.putExtra(FetchService.EXTRA_STATUS, i);
        intent.putExtra(FetchService.EXTRA_PROGRESS, i2);
        intent.putExtra(FetchService.EXTRA_DOWNLOADED_BYTES, j2);
        intent.putExtra(FetchService.EXTRA_FILE_SIZE, j3);
        intent.putExtra(FetchService.EXTRA_ERROR, i3);
        localBroadcastManager.sendBroadcast(intent);
    }

    static ArrayList<Bundle> headersToBundleList(String str, boolean z) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        if (str == null) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Bundle bundle = new Bundle();
                bundle.putString(FetchService.EXTRA_HEADER_NAME, next);
                bundle.putString(FetchService.EXTRA_HEADER_VALUE, jSONObject.getString(next));
                arrayList.add(bundle);
            }
        } catch (JSONException e) {
            if (z) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    static String bundleListToHeaderString(List<Bundle> list, boolean z) {
        if (list == null) {
            return "{}";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Bundle bundle : list) {
                String string = bundle.getString(FetchService.EXTRA_HEADER_NAME);
                String string2 = bundle.getString(FetchService.EXTRA_HEADER_VALUE);
                if (string2 == null) {
                    string2 = "";
                }
                if (string != null) {
                    jSONObject.put(string, string2);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            if (!z) {
                return "{}";
            }
            e.printStackTrace();
            return "{}";
        }
    }

    static boolean containsRequest(Cursor cursor, boolean z) {
        if (cursor == null || cursor.getCount() <= 0) {
            return false;
        }
        if (!z) {
            return true;
        }
        cursor.close();
        return true;
    }

    static void throwIfInvalidUrl(String str) {
        String scheme = Uri.parse(str).getScheme();
        if (scheme == null || !(scheme.equals("http") || scheme.equals("https"))) {
            throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + str);
        }
    }
}
