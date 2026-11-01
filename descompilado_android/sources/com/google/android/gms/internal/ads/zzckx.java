package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckx {
    private final Executor executor;
    private final Map<String, zzcle> zzgat = new ConcurrentHashMap();
    private final Map<String, Map<String, List<zzcle>>> zzgau = new ConcurrentHashMap();
    private JSONObject zzgav;

    public zzckx(Executor executor) {
        this.executor = executor;
    }

    public final void zzaku() {
        com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzc(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcla
            private final zzckx zzgaw;

            {
                this.zzgaw = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzgaw.zzakx();
            }
        });
        this.executor.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzckz
            private final zzckx zzgaw;

            {
                this.zzgaw = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzgaw.zzakw();
            }
        });
    }

    public final Map<String, List<Bundle>> zzs(String str, String str2) {
        JSONObject jSONObject;
        JSONArray jSONArrayOptJSONArray;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return Collections.emptyMap();
        }
        Map<String, List<zzcle>> map = this.zzgau.get(str);
        if (map == null) {
            return Collections.emptyMap();
        }
        List<zzcle> list = map.get(str2);
        if (list == null) {
            String strOptString = "";
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcme)).booleanValue() && (jSONObject = this.zzgav) != null && (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject != null) {
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("including");
                        JSONArray jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("excluding");
                        if (zza(jSONArrayOptJSONArray2, str2) && !zza(jSONArrayOptJSONArray3, str2)) {
                            strOptString = jSONObjectOptJSONObject.optString("effective_ad_unit_id", "");
                            break;
                        }
                    }
                }
            }
            list = map.get(strOptString);
        }
        if (list == null) {
            return Collections.emptyMap();
        }
        HashMap map2 = new HashMap();
        for (zzcle zzcleVar : list) {
            String str3 = zzcleVar.zzfev;
            if (!map2.containsKey(str3)) {
                map2.put(str3, new ArrayList());
            }
            ((List) map2.get(str3)).add(zzcleVar.zzehx);
        }
        return map2;
    }

    public final void zzgd(String str) {
        if (TextUtils.isEmpty(str) || this.zzgat.containsKey(str)) {
            return;
        }
        this.zzgat.put(str, new zzcle(str, "", new Bundle()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzakv, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final synchronized void zzaky() {
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectZztz = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuy().zztz();
        if (jSONObjectZztz != null) {
            try {
                JSONArray jSONArrayOptJSONArray2 = jSONObjectZztz.optJSONArray("ad_unit_id_settings");
                this.zzgav = jSONObjectZztz.optJSONObject("ad_unit_patterns");
                if (jSONArrayOptJSONArray2 != null) {
                    for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                        JSONObject jSONObject = jSONArrayOptJSONArray2.getJSONObject(i);
                        String strOptString = jSONObject.optString("ad_unit_id", "");
                        String strOptString2 = jSONObject.optString("format", "");
                        ArrayList arrayList = new ArrayList();
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("mediation_config");
                        if (jSONObjectOptJSONObject != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("ad_networks")) != null) {
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                                ArrayList arrayList2 = new ArrayList();
                                if (jSONObject2 != null) {
                                    JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("data");
                                    Bundle bundle = new Bundle();
                                    if (jSONObjectOptJSONObject2 != null) {
                                        Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
                                        while (itKeys.hasNext()) {
                                            String next = itKeys.next();
                                            bundle.putString(next, jSONObjectOptJSONObject2.optString(next, ""));
                                        }
                                    }
                                    JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray("rtb_adapters");
                                    if (jSONArrayOptJSONArray3 != null) {
                                        ArrayList arrayList3 = new ArrayList();
                                        for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                                            String strOptString3 = jSONArrayOptJSONArray3.optString(i3, "");
                                            if (!TextUtils.isEmpty(strOptString3)) {
                                                arrayList3.add(strOptString3);
                                            }
                                        }
                                        ArrayList arrayList4 = arrayList3;
                                        int size = arrayList4.size();
                                        int i4 = 0;
                                        while (i4 < size) {
                                            Object obj = arrayList4.get(i4);
                                            i4++;
                                            String str = (String) obj;
                                            zzgd(str);
                                            if (this.zzgat.get(str) != null) {
                                                arrayList2.add(new zzcle(str, strOptString2, bundle));
                                            }
                                        }
                                    }
                                }
                                arrayList.addAll(arrayList2);
                            }
                        }
                        if (!TextUtils.isEmpty(strOptString2) && !TextUtils.isEmpty(strOptString)) {
                            Map<String, List<zzcle>> concurrentHashMap = this.zzgau.get(strOptString2);
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap<>();
                            }
                            this.zzgau.put(strOptString2, concurrentHashMap);
                            List<zzcle> arrayList5 = concurrentHashMap.get(strOptString);
                            if (arrayList5 == null) {
                                arrayList5 = new ArrayList<>();
                            }
                            arrayList5.addAll(arrayList);
                            concurrentHashMap.put(strOptString, arrayList5);
                        }
                    }
                }
            } catch (JSONException e) {
                zzatm.zza("Malformed config loading JSON.", e);
            }
        }
    }

    private static boolean zza(JSONArray jSONArray, String str) {
        if (jSONArray != null && str != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    if (Pattern.compile(jSONArray.optString(i)).matcher(str).lookingAt()) {
                        return true;
                    }
                } catch (PatternSyntaxException e) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "RtbAdapterMap.hasAtleastOneRegexMatch");
                }
            }
        }
        return false;
    }

    final /* synthetic */ void zzakx() {
        this.executor.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzclc
            private final zzckx zzgaw;

            {
                this.zzgaw = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzgaw.zzaky();
            }
        });
    }
}
