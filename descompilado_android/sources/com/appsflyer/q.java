package com.appsflyer;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.appsflyer.share.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class q extends OneLinkHttpTask {

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static List<String> f204 = Arrays.asList("onelink.me", "onelnk.com", "app.aflink.com");

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private String f205;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private e f206;

    interface e {
        /* JADX INFO: renamed from: ˋ */
        void mo85(String str);

        /* JADX INFO: renamed from: ॱ */
        void mo86(Map<String, String> map);
    }

    q(Uri uri, AppsFlyerLib appsFlyerLib) {
        super(appsFlyerLib);
        if (TextUtils.isEmpty(uri.getHost()) || TextUtils.isEmpty(uri.getPath())) {
            return;
        }
        boolean z = false;
        Iterator<String> it = f204.iterator();
        while (it.hasNext()) {
            if (uri.getHost().contains(it.next())) {
                z = true;
            }
        }
        String[] strArrSplit = uri.getPath().split(Constants.URL_PATH_DELIMITER);
        if (z && strArrSplit.length == 3) {
            this.f97 = strArrSplit[1];
            this.f205 = strArrSplit[2];
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final void m175(@NonNull e eVar) {
        this.f206 = eVar;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final boolean m176() {
        return (TextUtils.isEmpty(this.f97) || TextUtils.isEmpty(this.f205)) ? false : true;
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˏ */
    final void mo93(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        httpsURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˏ */
    final String mo92() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl("https://%sonelink.%s/shortlink-sdk/v1"));
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(this.f97);
        sb.append("?id=");
        sb.append(this.f205);
        return sb.toString();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˊ */
    final void mo90(String str) {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.optString(next));
            }
            this.f206.mo86(map);
        } catch (JSONException e2) {
            this.f206.mo85("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e2);
        }
    }

    @Override // com.appsflyer.OneLinkHttpTask
    /* JADX INFO: renamed from: ˋ */
    final void mo91() {
        this.f206.mo85("Can't get one link data");
    }
}
