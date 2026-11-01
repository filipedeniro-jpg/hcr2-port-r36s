package com.my.target;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.sdk.precache.DownloadManager;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAppwallAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ey extends d<com.my.target.core.models.sections.e> {
    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        boolean z;
        JSONArray jSONArrayOptJSONArray;
        com.my.target.core.models.sections.e eVar = (com.my.target.core.models.sections.e) alVar;
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA != null) {
            JSONArray jSONArrayNames = jSONObjectA.names();
            fb.l();
            com.my.target.core.models.sections.e eVar2 = eVar;
            int i = 0;
            while (true) {
                if (i >= jSONArrayNames.length()) {
                    z = false;
                    break;
                }
                String strOptString = jSONArrayNames.optString(i);
                if (b.a.g.equals(strOptString) || b.a.k.equals(strOptString) || b.a.l.equals(strOptString) || b.a.j.equals(strOptString)) {
                    JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject(strOptString);
                    if (jSONObjectOptJSONObject == null || (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners")) == null || jSONArrayOptJSONArray.length() <= 0) {
                        eVar2 = null;
                    } else {
                        com.my.target.core.models.sections.e eVarA = com.my.target.core.models.sections.e.a(strOptString);
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(DownloadManager.SETTINGS);
                        if (jSONObjectOptJSONObject2 != null) {
                            eVarA.setTitle(jSONObjectOptJSONObject2.optString("title", eVarA.getTitle()));
                            eVarA.b(jSONObjectOptJSONObject2.optString("icon_hd", eVarA.g()));
                            eVarA.c(jSONObjectOptJSONObject2.optString("bubble_icon_hd", eVarA.h()));
                            eVarA.d(jSONObjectOptJSONObject2.optString("label_icon_hd", eVarA.i()));
                            eVarA.e(jSONObjectOptJSONObject2.optString("goto_app_icon_hd", eVarA.j()));
                            eVarA.f(jSONObjectOptJSONObject2.optString("item_highlight_icon", eVarA.k()));
                            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("icon_status");
                            if (jSONArrayOptJSONArray2 != null) {
                                int length = jSONArrayOptJSONArray2.length();
                                for (int i2 = 0; i2 < length; i2++) {
                                    JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                    if (jSONObjectOptJSONObject3 != null) {
                                        eVarA.f().add(new Pair<>(jSONObjectOptJSONObject3.optString("value"), jSONObjectOptJSONObject3.optString("icon_hd")));
                                    }
                                }
                            }
                        }
                        fa faVarA = fa.a(eVarA, afVar, bVar, context);
                        for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                            JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray.optJSONObject(i3);
                            if (jSONObjectOptJSONObject4 != null) {
                                com.my.target.core.models.banners.j jVarNewBanner = com.my.target.core.models.banners.j.newBanner();
                                faVarA.a(jSONObjectOptJSONObject4, jVarNewBanner);
                                String bundleId = jVarNewBanner.getBundleId();
                                if (!TextUtils.isEmpty(bundleId)) {
                                    PackageManager packageManager = context.getPackageManager();
                                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(bundleId);
                                    jVarNewBanner.setAppInstalled((launchIntentForPackage == null || packageManager.queryIntentActivities(launchIntentForPackage, 65536).isEmpty()) ? false : true);
                                }
                                eVarA.a(jVarNewBanner);
                            }
                        }
                        eVar2 = eVarA;
                    }
                    if (eVar2 != null && !eVar2.R().isEmpty()) {
                        z = true;
                        break;
                    }
                }
                i++;
            }
            if (z) {
                eVar2.d(afVar.D());
                eVar2.setRawData(jSONObjectA);
                return eVar2;
            }
        }
        return null;
    }

    @NonNull
    public static d<com.my.target.core.models.sections.e> newParser() {
        return new ey();
    }

    private ey() {
    }
}
