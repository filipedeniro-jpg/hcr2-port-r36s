package com.appsflyer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.appsflyer.share.Constants;
import com.appsflyer.share.LinkGenerator;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CreateOneLinkHttpTask extends OneLinkHttpTask {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private String f90;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private Map<String, String> f91;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private ResponseListener f92;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private String f93;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private boolean f94;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private Context f95;

    public interface ResponseListener {
        @WorkerThread
        void onResponse(String str);

        @WorkerThread
        void onResponseError(String str);
    }

    public CreateOneLinkHttpTask(@NonNull String str, @NonNull Map<String, String> map, AppsFlyerLib appsFlyerLib, @NonNull Context context, boolean z) {
        super(appsFlyerLib);
        this.f93 = "";
        this.f94 = false;
        this.f94 = z;
        this.f95 = context;
        if (this.f95 != null) {
            this.f93 = context.getPackageName();
        } else {
            AFLogger.afWarnLog("CreateOneLinkHttpTask: context can't be null");
        }
        this.f97 = str;
        this.f90 = "-1";
        this.f91 = map;
    }

    public void setListener(@NonNull ResponseListener responseListener) {
        this.f92 = responseListener;
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void mo93(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        if (this.f94) {
            return;
        }
        httpsURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setUseCaches(false);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject(this.f91);
        jSONObject.put("ttl", this.f90);
        jSONObject.put("data", jSONObject2);
        httpsURLConnection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
        dataOutputStream.writeBytes(jSONObject.toString());
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final String mo92() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl("https://%sonelink.%s/shortlink-sdk/v1"));
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(this.f97);
        return sb.toString();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void mo90(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                this.f92.onResponse(jSONObject.optString(itKeys.next()));
            }
        } catch (JSONException e) {
            this.f92.onResponseError("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e);
        }
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void mo91() {
        LinkGenerator linkGeneratorAddParameters = new LinkGenerator(Constants.USER_INVITE_LINK_TYPE).setBaseURL(this.f97, AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_DOMAIN), this.f93).addParameter(Constants.URL_SITE_ID, this.f93).addParameters(this.f91);
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string != null) {
            linkGeneratorAddParameters.setReferrerCustomerId(string);
        }
        this.f92.onResponse(linkGeneratorAddParameters.generateLink());
    }
}
