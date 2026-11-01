package net.hockeyapp.android.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class SendFeedbackTask extends ConnectionTask<Void, Void, HashMap<String, String>> {
    private List<Uri> attachmentUris;
    private Context context;
    private String email;
    private Handler handler;
    private boolean isFetchMessages;
    private String name;
    private ProgressDialog progressDialog;
    private String subject;
    private String text;
    private String token;
    private HttpURLConnection urlConnection;
    private String urlString;
    private boolean showProgressDialog = true;
    private int lastMessageId = -1;

    public SendFeedbackTask(Context context, String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.context = context;
        this.urlString = str;
        this.name = str2;
        this.email = str3;
        this.subject = str4;
        this.text = str5;
        this.attachmentUris = list;
        this.token = str6;
        this.handler = handler;
        this.isFetchMessages = z;
        if (context != null) {
            Constants.loadFromContext(context);
        }
    }

    public void setShowProgressDialog(boolean z) {
        this.showProgressDialog = z;
    }

    public void setLastMessageId(int i) {
        this.lastMessageId = i;
    }

    public void attach(Context context) {
        this.context = context;
    }

    public void detach() {
        this.context = null;
        this.progressDialog = null;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        String str = this.isFetchMessages ? "Retrieving discussions..." : "Sending feedback..";
        ProgressDialog progressDialog = this.progressDialog;
        if ((progressDialog == null || !progressDialog.isShowing()) && this.showProgressDialog) {
            this.progressDialog = ProgressDialog.show(this.context, "", str, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public HashMap<String, String> doInBackground(Void... voidArr) {
        Context context;
        if (this.isFetchMessages && this.token != null) {
            return doGet();
        }
        if (this.isFetchMessages) {
            return null;
        }
        if (this.attachmentUris.isEmpty()) {
            return doPostPut();
        }
        HashMap<String, String> mapDoPostPutWithAttachments = doPostPutWithAttachments();
        String str = mapDoPostPutWithAttachments.get("status");
        if (str != null && str.startsWith("2") && (context = this.context) != null) {
            File file = new File(context.getCacheDir(), "HockeyApp");
            if (file.exists()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        }
        return mapDoPostPutWithAttachments;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(HashMap<String, String> map) {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.handler != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (map != null) {
                bundle.putString("request_type", map.get("type"));
                bundle.putString("feedback_response", map.get(ServerResponseWrapper.RESPONSE_FIELD));
                bundle.putString("feedback_status", map.get("status"));
            } else {
                bundle.putString("request_type", "unknown");
            }
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00b6 A[DONT_GENERATE, PHI: r1
  0x00b6: PHI (r1v4 java.net.HttpURLConnection) = (r1v3 java.net.HttpURLConnection), (r1v5 java.net.HttpURLConnection) binds: [B:17:0x00b4, B:11:0x00ab] A[DONT_GENERATE, DONT_INLINE]] */
    private HashMap<String, String> doPostPut() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "send");
        HttpURLConnection httpURLConnectionBuild = null;
        try {
            HashMap map2 = new HashMap();
            map2.put("name", this.name);
            map2.put("email", this.email);
            map2.put("subject", this.subject);
            map2.put(MimeTypes.BASE_TYPE_TEXT, this.text);
            map2.put("bundle_identifier", Constants.APP_PACKAGE);
            map2.put("bundle_short_version", Constants.APP_VERSION_NAME);
            map2.put("bundle_version", Constants.APP_VERSION);
            map2.put("os_version", Constants.ANDROID_VERSION);
            map2.put("oem", Constants.PHONE_MANUFACTURER);
            map2.put("model", Constants.PHONE_MODEL);
            if (this.token != null) {
                this.urlString += this.token + com.appsflyer.share.Constants.URL_PATH_DELIMITER;
            }
            httpURLConnectionBuild = new HttpURLConnectionBuilder(this.urlString).setRequestMethod(this.token != null ? HttpRequest.METHOD_PUT : HttpRequest.METHOD_POST).writeFormFields(map2).build();
            httpURLConnectionBuild.connect();
            map.put("status", String.valueOf(httpURLConnectionBuild.getResponseCode()));
            map.put(ServerResponseWrapper.RESPONSE_FIELD, getStringFromConnection(httpURLConnectionBuild));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnectionBuild != null) {
                httpURLConnectionBuild.disconnect();
            }
        }
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00ba A[DONT_GENERATE, PHI: r1
  0x00ba: PHI (r1v4 java.net.HttpURLConnection) = (r1v2 java.net.HttpURLConnection), (r1v5 java.net.HttpURLConnection) binds: [B:17:0x00b8, B:11:0x00af] A[DONT_GENERATE, DONT_INLINE]] */
    private HashMap<String, String> doPostPutWithAttachments() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "send");
        HttpURLConnection httpURLConnectionBuild = null;
        try {
            HashMap map2 = new HashMap();
            map2.put("name", this.name);
            map2.put("email", this.email);
            map2.put("subject", this.subject);
            map2.put(MimeTypes.BASE_TYPE_TEXT, this.text);
            map2.put("bundle_identifier", Constants.APP_PACKAGE);
            map2.put("bundle_short_version", Constants.APP_VERSION_NAME);
            map2.put("bundle_version", Constants.APP_VERSION);
            map2.put("os_version", Constants.ANDROID_VERSION);
            map2.put("oem", Constants.PHONE_MANUFACTURER);
            map2.put("model", Constants.PHONE_MODEL);
            if (this.token != null) {
                this.urlString += this.token + com.appsflyer.share.Constants.URL_PATH_DELIMITER;
            }
            httpURLConnectionBuild = new HttpURLConnectionBuilder(this.urlString).setRequestMethod(this.token != null ? HttpRequest.METHOD_PUT : HttpRequest.METHOD_POST).writeMultipartData(map2, this.context, this.attachmentUris).build();
            httpURLConnectionBuild.connect();
            map.put("status", String.valueOf(httpURLConnectionBuild.getResponseCode()));
            map.put(ServerResponseWrapper.RESPONSE_FIELD, getStringFromConnection(httpURLConnectionBuild));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnectionBuild != null) {
                httpURLConnectionBuild.disconnect();
            }
        }
        return map;
    }

    private HashMap<String, String> doGet() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.urlString + Util.encodeParam(this.token));
        if (this.lastMessageId != -1) {
            sb.append("?last_message_id=" + this.lastMessageId);
        }
        HashMap<String, String> map = new HashMap<>();
        HttpURLConnection httpURLConnectionBuild = null;
        try {
            httpURLConnectionBuild = new HttpURLConnectionBuilder(sb.toString()).build();
            map.put("type", "fetch");
            httpURLConnectionBuild.connect();
            map.put("status", String.valueOf(httpURLConnectionBuild.getResponseCode()));
            map.put(ServerResponseWrapper.RESPONSE_FIELD, getStringFromConnection(httpURLConnectionBuild));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnectionBuild != null) {
                httpURLConnectionBuild.disconnect();
            }
        }
        return map;
    }
}
