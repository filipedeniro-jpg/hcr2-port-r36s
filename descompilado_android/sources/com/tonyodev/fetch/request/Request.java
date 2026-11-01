package com.tonyodev.fetch.request;

import android.net.Uri;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.appsflyer.share.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class Request {
    private final String filePath;
    private final Map<String, String> headers;
    private int priority;
    private final String url;

    public Request(@NonNull String str) {
        this(str, generateFileName(str));
    }

    public Request(@NonNull String str, @NonNull String str2) {
        this(str, generateDirectoryName(), str2);
    }

    public Request(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.headers = new ArrayMap();
        this.priority = 600;
        if (str == null || str.isEmpty()) {
            throw new NullPointerException("Url cannot be null or empty");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new NullPointerException("directory path cannot be null or empty");
        }
        if (str3 == null || str3.isEmpty()) {
            throw new NullPointerException("File Name cannot be null or empty");
        }
        String scheme = Uri.parse(str).getScheme();
        if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
            throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + str);
        }
        this.url = str;
        this.filePath = cleanFilePath(generateFilePath(str2, str3));
    }

    @NonNull
    public Request addHeader(@NonNull String str, @Nullable String str2) {
        return addHeader(new Header(str, str2));
    }

    @NonNull
    public Request addHeader(@NonNull Header header) {
        if (header == null) {
            throw new NullPointerException("Header cannot be null");
        }
        this.headers.put(header.getHeader(), header.getValue());
        return this;
    }

    @NonNull
    public Request setPriority(int i) {
        this.priority = 600;
        if (i == 601) {
            this.priority = 601;
        }
        return this;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    @NonNull
    public String getFilePath() {
        return this.filePath;
    }

    @NonNull
    public List<Header> getHeaders() {
        ArrayList arrayList = new ArrayList(this.headers.size());
        for (String str : this.headers.keySet()) {
            arrayList.add(new Header(str, this.headers.get(str)));
        }
        return arrayList;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Header> it = getHeaders().iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(",");
        }
        if (this.headers.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "{url:" + this.url + " ,filePath:" + this.filePath + ",headers:{" + sb.toString() + "},priority:" + this.priority + "}";
    }

    private static String generateFileName(String str) {
        if (str == null) {
            throw new NullPointerException("Url cannot be null");
        }
        return new Date().getTime() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Uri.parse(str).getLastPathSegment();
    }

    private static String generateDirectoryName() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    }

    private static String generateFilePath(String str, String str2) {
        if (Uri.parse(str2).getPathSegments().size() != 1) {
            return str2;
        }
        return str + Constants.URL_PATH_DELIMITER + str2;
    }

    private static String cleanFilePath(String str) {
        return str.replace("//", Constants.URL_PATH_DELIMITER);
    }
}
