package net.hockeyapp.android.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class HttpURLConnectionBuilder {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_TIMEOUT = 120000;
    private SimpleMultipartEntity mMultipartEntity;
    private String mRequestBody;
    private String mRequestMethod;
    private final String mUrlString;
    private int mTimeout = DEFAULT_TIMEOUT;
    private final Map<String, String> mHeaders = new HashMap();

    public HttpURLConnectionBuilder(String str) {
        this.mUrlString = str;
    }

    public HttpURLConnectionBuilder setRequestMethod(String str) {
        this.mRequestMethod = str;
        return this;
    }

    public HttpURLConnectionBuilder setRequestBody(String str) {
        this.mRequestBody = str;
        return this;
    }

    public HttpURLConnectionBuilder writeFormFields(Map<String, String> map) {
        try {
            String formString = getFormString(map, "UTF-8");
            setHeader(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_FORM);
            setRequestBody(formString);
            return this;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpURLConnectionBuilder writeMultipartData(Map<String, String> map, Context context, List<Uri> list) {
        try {
            this.mMultipartEntity = new SimpleMultipartEntity();
            this.mMultipartEntity.writeFirstBoundaryIfNeeds();
            for (String str : map.keySet()) {
                this.mMultipartEntity.addPart(str, map.get(str));
            }
            for (int i = 0; i < list.size(); i++) {
                Uri uri = list.get(i);
                boolean z = true;
                if (i != list.size() - 1) {
                    z = false;
                }
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                String lastPathSegment = uri.getLastPathSegment();
                this.mMultipartEntity.addPart(MessengerShareContentUtility.ATTACHMENT + i, lastPathSegment, inputStreamOpenInputStream, z);
            }
            this.mMultipartEntity.writeLastBoundaryIfNeeds();
            setHeader(HttpRequest.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=" + this.mMultipartEntity.getBoundary());
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpURLConnectionBuilder setTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Timeout has to be positive.");
        }
        this.mTimeout = i;
        return this;
    }

    public HttpURLConnectionBuilder setHeader(String str, String str2) {
        this.mHeaders.put(str, str2);
        return this;
    }

    public HttpURLConnectionBuilder setBasicAuthorization(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        sb.append(Base64.encodeToString((str + ":" + str2).getBytes(), 2));
        setHeader(HttpRequest.HEADER_AUTHORIZATION, sb.toString());
        return this;
    }

    public HttpURLConnection build() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.mUrlString).openConnection();
            httpURLConnection.setConnectTimeout(this.mTimeout);
            httpURLConnection.setReadTimeout(this.mTimeout);
            if (Build.VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("Connection", "close");
            }
            if (!TextUtils.isEmpty(this.mRequestMethod)) {
                httpURLConnection.setRequestMethod(this.mRequestMethod);
                if (!TextUtils.isEmpty(this.mRequestBody) || this.mRequestMethod.equalsIgnoreCase(HttpRequest.METHOD_POST) || this.mRequestMethod.equalsIgnoreCase(HttpRequest.METHOD_PUT)) {
                    httpURLConnection.setDoOutput(true);
                }
            }
            for (String str : this.mHeaders.keySet()) {
                httpURLConnection.setRequestProperty(str, this.mHeaders.get(str));
            }
            if (!TextUtils.isEmpty(this.mRequestBody)) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                bufferedWriter.write(this.mRequestBody);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if (this.mMultipartEntity != null) {
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_LENGTH, String.valueOf(this.mMultipartEntity.getContentLength()));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(this.mMultipartEntity.getOutputStream().toByteArray());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormString(Map<String, String> map, String str) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            String str3 = map.get(str2);
            arrayList.add(URLEncoder.encode(str2, str) + Constants.RequestParameters.EQUAL + URLEncoder.encode(str3, str));
        }
        return TextUtils.join(Constants.RequestParameters.AMPERSAND, arrayList);
    }
}
