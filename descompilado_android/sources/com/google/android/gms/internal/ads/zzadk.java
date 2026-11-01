package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.appsflyer.share.Constants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadk {
    public static final zzadx<zzbbc> zzcwt = zzadj.zzcws;
    public static final zzadx<zzbbc> zzcwu = zzadm.zzcws;
    public static final zzadx<zzbbc> zzcwv = zzadl.zzcws;
    public static final zzadx<zzbbc> zzcww = new zzadq();
    public static final zzadx<zzbbc> zzcwx = new zzadp();
    public static final zzadx<zzbbc> zzcwy = zzado.zzcws;
    public static final zzadx<Object> zzcwz = new zzads();
    public static final zzadx<zzbbc> zzcxa = new zzadr();
    public static final zzadx<zzbbc> zzcxb = zzadn.zzcws;
    public static final zzadx<zzbbc> zzcxc = new zzadu();
    public static final zzadx<zzbbc> zzcxd = new zzadt();
    public static final zzadx<zzayt> zzcxe = new zzbaa();
    public static final zzadx<zzayt> zzcxf = new zzbad();
    public static final zzadx<zzbbc> zzcxg = new zzadh();
    public static final zzaed zzcxh = new zzaed();
    public static final zzadx<zzbbc> zzcxi = new zzadw();
    public static final zzadx<zzbbc> zzcxj = new zzadv();
    public static final zzadx<zzbbc> zzcxk = new zzady();

    static final /* synthetic */ void zza(zzbcl zzbclVar, Map map) {
        String str = (String) map.get("tx");
        String str2 = (String) map.get("ty");
        String str3 = (String) map.get("td");
        try {
            int i = Integer.parseInt(str);
            int i2 = Integer.parseInt(str2);
            int i3 = Integer.parseInt(str3);
            zzdf zzdfVarZzzb = zzbclVar.zzzb();
            if (zzdfVarZzzb != null) {
                zzdfVarZzzb.zzcc().zza(i, i2, i3);
            }
        } catch (NumberFormatException unused) {
            zzatm.zzeu("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void zza(zzbcd zzbcdVar, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzatm.zzeu("URL missing from httpTrack GMSG.");
        } else {
            new zzavv(zzbcdVar.getContext(), ((zzbck) zzbcdVar).zzxp().zzbnh, str).zzup();
        }
    }

    static final /* synthetic */ void zza(zzaft zzaftVar, Map map) throws zzdi {
        String str = (String) map.get("u");
        if (str == null) {
            zzatm.zzeu("URL missing from click GMSG.");
            return;
        }
        Uri uriZza = Uri.parse(str);
        try {
            zzdf zzdfVarZzzb = ((zzbcl) zzaftVar).zzzb();
            if (zzdfVarZzzb != null && zzdfVarZzzb.zzc(uriZza)) {
                uriZza = zzdfVarZzzb.zza(uriZza, ((zzbcd) zzaftVar).getContext(), ((zzbcn) zzaftVar).getView(), ((zzbcd) zzaftVar).zzxl());
            }
        } catch (zzdi unused) {
            String strValueOf = String.valueOf(str);
            zzatm.zzeu(strValueOf.length() != 0 ? "Unable to append parameter to URL: ".concat(strValueOf) : new String("Unable to append parameter to URL: "));
        }
        zzbcd zzbcdVar = (zzbcd) zzaftVar;
        new zzavv(zzbcdVar.getContext(), ((zzbck) zzaftVar).zzxp().zzbnh, zzask.zzc(uriZza, zzbcdVar.getContext())).zzup();
    }

    static final /* synthetic */ void zzb(zzbcd zzbcdVar, Map map) {
        PackageManager packageManager = zzbcdVar.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String strOptString = jSONObject2.optString("id");
                        String strOptString2 = jSONObject2.optString("u");
                        String strOptString3 = jSONObject2.optString("i");
                        String strOptString4 = jSONObject2.optString("m");
                        String strOptString5 = jSONObject2.optString(TtmlNode.TAG_P);
                        String strOptString6 = jSONObject2.optString(Constants.URL_CAMPAIGN);
                        jSONObject2.optString("f");
                        jSONObject2.optString("e");
                        String strOptString7 = jSONObject2.optString(VungleWebViewActivity.INTENT_URL);
                        Intent uri = null;
                        if (!TextUtils.isEmpty(strOptString7)) {
                            try {
                                uri = Intent.parseUri(strOptString7, 0);
                            } catch (URISyntaxException e) {
                                String strValueOf = String.valueOf(strOptString7);
                                zzatm.zzc(strValueOf.length() != 0 ? "Error parsing the url: ".concat(strValueOf) : new String("Error parsing the url: "), e);
                            }
                        }
                        if (uri == null) {
                            uri = new Intent();
                            if (!TextUtils.isEmpty(strOptString2)) {
                                uri.setData(Uri.parse(strOptString2));
                            }
                            if (!TextUtils.isEmpty(strOptString3)) {
                                uri.setAction(strOptString3);
                            }
                            if (!TextUtils.isEmpty(strOptString4)) {
                                uri.setType(strOptString4);
                            }
                            if (!TextUtils.isEmpty(strOptString5)) {
                                uri.setPackage(strOptString5);
                            }
                            if (!TextUtils.isEmpty(strOptString6)) {
                                String[] strArrSplit = strOptString6.split(Constants.URL_PATH_DELIMITER, 2);
                                if (strArrSplit.length == 2) {
                                    uri.setComponent(new ComponentName(strArrSplit[0], strArrSplit[1]));
                                }
                            }
                        }
                        try {
                            jSONObject.put(strOptString, packageManager.resolveActivity(uri, 65536) != null);
                        } catch (JSONException e2) {
                            zzatm.zzc("Error constructing openable urls response.", e2);
                        }
                    } catch (JSONException e3) {
                        zzatm.zzc("Error parsing the intent data.", e3);
                    }
                }
                ((zzaft) zzbcdVar).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((zzaft) zzbcdVar).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((zzaft) zzbcdVar).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzbcd zzbcdVar, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            zzatm.zzeu("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] strArrSplit = str.split(",");
        HashMap map2 = new HashMap();
        PackageManager packageManager = zzbcdVar.getContext().getPackageManager();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(strArrSplit2.length > 1 ? strArrSplit2[1].trim() : "android.intent.action.VIEW", Uri.parse(strArrSplit2[0].trim())), 65536) == null) {
                z = false;
            }
            map2.put(str2, Boolean.valueOf(z));
        }
        ((zzaft) zzbcdVar).zza("openableURLs", map2);
    }
}
