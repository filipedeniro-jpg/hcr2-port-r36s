package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class k extends AsyncTask<String, Void, String> {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private WeakReference<Context> f171;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    Map<String, String> f175;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private boolean f176;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    String f178;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private URL f179;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private HttpURLConnection f180;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private String f177 = "";

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private boolean f174 = false;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private boolean f173 = true;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private boolean f172 = true;

    @Override // android.os.AsyncTask
    protected final void onCancelled() {
    }

    k(Context context, boolean z) {
        this.f176 = false;
        this.f171 = new WeakReference<>(context);
        this.f176 = z;
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        if (this.f178 == null) {
            this.f178 = new JSONObject(this.f175).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters and merged with bridge method [inline-methods] */
    public final String doInBackground(String... strArr) {
        if (this.f176) {
            return null;
        }
        try {
            this.f179 = new URL(strArr[0]);
            if (this.f173) {
                s.m187().m201(this.f179.toString(), this.f178);
                int length = this.f178.getBytes("UTF-8").length;
                StringBuilder sb = new StringBuilder("call = ");
                sb.append(this.f179);
                sb.append("; size = ");
                sb.append(length);
                sb.append(" byte");
                sb.append(length > 1 ? "s" : "");
                sb.append("; body = ");
                sb.append(this.f178);
                f.AnonymousClass4.m127(sb.toString());
            }
            this.f180 = (HttpURLConnection) this.f179.openConnection();
            this.f180.setReadTimeout(com.google.ads.mediation.unity.BuildConfig.VERSION_CODE);
            this.f180.setConnectTimeout(com.google.ads.mediation.unity.BuildConfig.VERSION_CODE);
            this.f180.setRequestMethod(HttpRequest.METHOD_POST);
            this.f180.setDoInput(true);
            this.f180.setDoOutput(true);
            this.f180.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
            OutputStream outputStream = this.f180.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(this.f178);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            this.f180.connect();
            int responseCode = this.f180.getResponseCode();
            if (this.f172) {
                AppsFlyerLib.getInstance();
                this.f177 = AppsFlyerLib.m44(this.f180);
            }
            if (this.f173) {
                s.m187().m200(this.f179.toString(), responseCode, this.f177);
            }
            if (responseCode == 200) {
                AFLogger.afInfoLog("Status 200 ok");
                Context context = this.f171.get();
                if (this.f179.toString().startsWith(ServerConfigHandler.getUrl(AppsFlyerLib.f22)) && context != null) {
                    SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
                    editorEdit.putBoolean("sentRegisterRequestToAF", true);
                    editorEdit.apply();
                    AFLogger.afDebugLog("Successfully registered for Uninstall Tracking");
                }
            } else {
                this.f174 = true;
            }
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder("Error while calling ");
            sb2.append(this.f179.toString());
            AFLogger.afErrorLog(sb2.toString(), th);
            this.f174 = true;
        }
        return this.f177;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters and merged with bridge method [inline-methods] */
    public final void onPostExecute(String str) {
        if (this.f174) {
            AFLogger.afInfoLog("Connection error: ".concat(String.valueOf(str)));
        } else {
            AFLogger.afInfoLog("Connection call succeeded: ".concat(String.valueOf(str)));
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m155() {
        this.f173 = false;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final HttpURLConnection m154() {
        return this.f180;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m153() {
        this.f172 = false;
    }
}
