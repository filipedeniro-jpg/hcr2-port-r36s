package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.device.ads.WebRequest;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.games.GamesStatusCodes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.ai;
import com.my.target.bj;
import com.my.target.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwa {
    private final Executor executor;
    private final zzawv zzblh;
    private final zzaai zzddy;
    private final zzrz zzeeb;
    private final zzdf zzefm;
    private final ScheduledExecutorService zzfez;
    private final zzbvr zzfod;
    private final com.google.android.gms.ads.internal.zzb zzfoe;
    private final zzbwo zzfof;
    private final Context zzlk;

    public zzbwa(Context context, zzbvr zzbvrVar, zzdf zzdfVar, zzawv zzawvVar, com.google.android.gms.ads.internal.zzb zzbVar, zzrz zzrzVar, Executor executor, zzcvk zzcvkVar, zzbwo zzbwoVar, ScheduledExecutorService scheduledExecutorService) {
        this.zzlk = context;
        this.zzfod = zzbvrVar;
        this.zzefm = zzdfVar;
        this.zzblh = zzawvVar;
        this.zzfoe = zzbVar;
        this.zzeeb = zzrzVar;
        this.executor = executor;
        this.zzddy = zzcvkVar.zzddy;
        this.zzfof = zzbwoVar;
        this.zzfez = scheduledExecutorService;
    }

    public static List<zzxd> zzi(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(bj.gv);
        if (jSONObjectOptJSONObject == null) {
            return Collections.emptyList();
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("reasons");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            zzxd zzxdVarZzk = zzk(jSONArrayOptJSONArray.optJSONObject(i));
            if (zzxdVarZzk != null) {
                arrayList.add(zzxdVarZzk);
            }
        }
        return arrayList;
    }

    @Nullable
    public static zzxd zzj(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(bj.gv);
        if (jSONObjectOptJSONObject2 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("default_reason")) == null) {
            return null;
        }
        return zzk(jSONObjectOptJSONObject);
    }

    @Nullable
    private static zzxd zzk(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString(IronSourceConstants.EVENTS_ERROR_REASON);
        String strOptString2 = jSONObject.optString("ping_url");
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
            return null;
        }
        return new zzxd(strOptString, strOptString2);
    }

    public final zzdcp<zzaae> zzc(JSONObject jSONObject, String str) {
        return zza(jSONObject.optJSONObject(str), this.zzddy.zzcvn);
    }

    public final zzdcp<List<zzaae>> zzd(JSONObject jSONObject, String str) {
        return zza(jSONObject.optJSONArray(str), this.zzddy.zzcvn, this.zzddy.zzbjv);
    }

    private final zzdcp<List<zzaae>> zza(@Nullable JSONArray jSONArray, boolean z, boolean z2) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return zzdcf.zzah(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = z2 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            arrayList.add(zza(jSONArray.optJSONObject(i), z));
        }
        return zzdcf.zzb(zzdcf.zzg(arrayList), zzbvz.zzdoi, this.executor);
    }

    private final zzdcp<zzaae> zza(@Nullable JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return zzdcf.zzah(null);
        }
        final String strOptString = jSONObject.optString("url");
        if (TextUtils.isEmpty(strOptString)) {
            return zzdcf.zzah(null);
        }
        final double dOptDouble = jSONObject.optDouble("scale", 1.0d);
        boolean zOptBoolean = jSONObject.optBoolean("is_transparent", true);
        final int iOptInt = jSONObject.optInt("width", -1);
        final int iOptInt2 = jSONObject.optInt("height", -1);
        if (z) {
            return zzdcf.zzah(new zzaae(null, Uri.parse(strOptString), dOptDouble, iOptInt, iOptInt2));
        }
        return zza(jSONObject.optBoolean("require"), (zzdcp<Object>) zzdcf.zzb(this.zzfod.zza(strOptString, dOptDouble, zOptBoolean), new zzczs(strOptString, dOptDouble, iOptInt, iOptInt2) { // from class: com.google.android.gms.internal.ads.zzbwc
            private final String zzcys;
            private final int zzdsz;
            private final int zzdta;
            private final double zzfoh;

            {
                this.zzcys = strOptString;
                this.zzfoh = dOptDouble;
                this.zzdsz = iOptInt;
                this.zzdta = iOptInt2;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                String str = this.zzcys;
                return new zzaae(new BitmapDrawable(Resources.getSystem(), (Bitmap) obj), Uri.parse(str), this.zzfoh, this.zzdsz, this.zzdta);
            }
        }, this.executor), (Object) null);
    }

    public final zzdcp<zzzz> zze(JSONObject jSONObject, String str) {
        final JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        if (jSONObjectOptJSONObject == null) {
            return zzdcf.zzah(null);
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("images");
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
        if (jSONArrayOptJSONArray == null && jSONObjectOptJSONObject2 != null) {
            jSONArrayOptJSONArray = new JSONArray();
            jSONArrayOptJSONArray.put(jSONObjectOptJSONObject2);
        }
        return zza(jSONObjectOptJSONObject.optBoolean("require"), (zzdcp<Object>) zzdcf.zzb(zza(jSONArrayOptJSONArray, false, true), new zzczs(this, jSONObjectOptJSONObject) { // from class: com.google.android.gms.internal.ads.zzbwb
            private final JSONObject zzfbh;
            private final zzbwa zzfog;

            {
                this.zzfog = this;
                this.zzfbh = jSONObjectOptJSONObject;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                return this.zzfog.zza(this.zzfbh, (List) obj);
            }
        }, this.executor), (Object) null);
    }

    private static Integer zzf(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt(i.H), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final zzdcp<zzbbc> zzl(JSONObject jSONObject) {
        JSONObject jSONObjectZza = zzavm.zza(jSONObject, "html_containers", "instream");
        if (jSONObjectZza == null) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("video");
            if (jSONObjectOptJSONObject == null) {
                return zzdcf.zzah(null);
            }
            if (TextUtils.isEmpty(jSONObjectOptJSONObject.optString("vast_xml"))) {
                zzatm.zzeu("Required field 'vast_xml' is missing");
                return zzdcf.zzah(null);
            }
            return zza((zzdcp<Object>) zzdcf.zza(this.zzfof.zzm(jSONObjectOptJSONObject), ((Integer) zzuo.zzoj().zzd(zzyt.zzcnw)).intValue(), TimeUnit.SECONDS, this.zzfez), (Object) null);
        }
        return zza(jSONObjectZza.optBoolean("require"), this.zzfof.zzp(jSONObjectZza.optString("base_url"), jSONObjectZza.optString(ai.a.da)), (Object) null);
    }

    private static <T> zzdcp<T> zza(zzdcp<T> zzdcpVar, T t) {
        final Object obj = null;
        return zzdcf.zzb(zzdcpVar, Exception.class, new zzdbq(obj) { // from class: com.google.android.gms.internal.ads.zzbwd
            private final Object zzfoi;

            {
                this.zzfoi = obj;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj2) {
                Object obj3 = this.zzfoi;
                zzatm.zza("Error during loading assets.", (Exception) obj2);
                return zzdcf.zzah(obj3);
            }
        }, zzawx.zzdwb);
    }

    private static <T> zzdcp<T> zza(boolean z, final zzdcp<T> zzdcpVar, T t) {
        if (z) {
            return zzdcf.zzb(zzdcpVar, new zzdbq(zzdcpVar) { // from class: com.google.android.gms.internal.ads.zzbwg
                private final zzdcp zzfoj;

                {
                    this.zzfoj = zzdcpVar;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    return obj != null ? this.zzfoj : zzdcf.zzi(new zzcin("Retrieve required value in native ad response failed.", 0));
                }
            }, zzawx.zzdwb);
        }
        return zza(zzdcpVar, (Object) null);
    }

    final /* synthetic */ zzdcp zzb(String str, Object obj) throws Exception {
        com.google.android.gms.ads.internal.zzp.zzjz();
        zzbbc zzbbcVarZza = zzbbl.zza(this.zzlk, zzbct.zzaan(), "native-omid", false, false, this.zzefm, this.zzblh, null, null, this.zzfoe, this.zzeeb, null, false);
        final zzaxc zzaxcVarZzl = zzaxc.zzl(zzbbcVarZza);
        zzbbcVarZza.zzyy().zza(new zzbcp(zzaxcVarZzl) { // from class: com.google.android.gms.internal.ads.zzbwf
            private final zzaxc zzeew;

            {
                this.zzeew = zzaxcVarZzl;
            }

            @Override // com.google.android.gms.internal.ads.zzbcp
            public final void zzab(boolean z) {
                this.zzeew.zzwl();
            }
        });
        zzbbcVarZza.loadData(str, WebRequest.CONTENT_TYPE_HTML, "UTF-8");
        return zzaxcVarZzl;
    }

    final /* synthetic */ zzzz zza(JSONObject jSONObject, List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        String strOptString = jSONObject.optString(MimeTypes.BASE_TYPE_TEXT);
        Integer numZzf = zzf(jSONObject, "bg_color");
        Integer numZzf2 = zzf(jSONObject, "text_color");
        int iOptInt = jSONObject.optInt("text_size", -1);
        boolean zOptBoolean = jSONObject.optBoolean("allow_pub_rendering");
        int iOptInt2 = jSONObject.optInt("animation_ms", 1000);
        return new zzzz(strOptString, list, numZzf, numZzf2, iOptInt > 0 ? Integer.valueOf(iOptInt) : null, jSONObject.optInt("presentation_ms", GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND) + iOptInt2, this.zzddy.zzbjw, zOptBoolean);
    }
}
