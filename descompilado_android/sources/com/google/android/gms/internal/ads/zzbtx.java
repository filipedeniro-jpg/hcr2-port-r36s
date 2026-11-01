package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtx {
    private Bundle extras;
    private zzaak zzcvp;
    private List<?> zzeif;
    private double zzeik;
    private float zzeiv;

    @Nullable
    private IObjectWrapper zzfeg;
    private int zzfku;
    private zzwk zzfkv;
    private View zzfkw;
    private zzxd zzfkx;
    private zzbbc zzfky;

    @Nullable
    private zzbbc zzfkz;
    private View zzfla;
    private IObjectWrapper zzflb;
    private zzaas zzflc;
    private zzaas zzfld;
    private String zzfle;

    @Nullable
    private String zzlr;
    private SimpleArrayMap<String, zzaae> zzflf = new SimpleArrayMap<>();
    private SimpleArrayMap<String, String> zzflg = new SimpleArrayMap<>();
    private List<zzxd> zzcwe = Collections.emptyList();

    public final synchronized void zzdh(int i) {
        this.zzfku = i;
    }

    public final synchronized void zzb(zzwk zzwkVar) {
        this.zzfkv = zzwkVar;
    }

    public final synchronized void zza(zzaak zzaakVar) {
        this.zzcvp = zzaakVar;
    }

    public final synchronized void setImages(List<zzaae> list) {
        this.zzeif = list;
    }

    public final synchronized void zzf(List<zzxd> list) {
        this.zzcwe = list;
    }

    public final synchronized void zza(@Nullable zzxd zzxdVar) {
        this.zzfkx = zzxdVar;
    }

    public final synchronized void zzaa(View view) {
        this.zzfla = view;
    }

    public final synchronized void setStarRating(double d) {
        this.zzeik = d;
    }

    public final synchronized void zza(zzaas zzaasVar) {
        this.zzflc = zzaasVar;
    }

    public final synchronized void zzb(zzaas zzaasVar) {
        this.zzfld = zzaasVar;
    }

    public final synchronized void zzfr(String str) {
        this.zzfle = str;
    }

    public final synchronized void zzi(zzbbc zzbbcVar) {
        this.zzfky = zzbbcVar;
    }

    public final synchronized void zzj(zzbbc zzbbcVar) {
        this.zzfkz = zzbbcVar;
    }

    public final synchronized void zzas(IObjectWrapper iObjectWrapper) {
        this.zzfeg = iObjectWrapper;
    }

    public final synchronized void zzo(String str, String str2) {
        try {
            if (str2 == null) {
                this.zzflg.remove(str);
            } else {
                this.zzflg.put(str, str2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void zza(String str, zzaae zzaaeVar) {
        try {
            if (zzaaeVar == null) {
                this.zzflf.remove(str);
            } else {
                this.zzflf.put(str, zzaaeVar);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private final synchronized void setMediaContentAspectRatio(float f) {
        this.zzeiv = f;
    }

    public final synchronized void zzfs(@Nullable String str) {
        this.zzlr = str;
    }

    private final synchronized String zzft(String str) {
        return this.zzflg.get(str);
    }

    public final synchronized int zzahl() {
        return this.zzfku;
    }

    public final synchronized zzwk getVideoController() {
        return this.zzfkv;
    }

    public final synchronized zzaak zzqk() {
        return this.zzcvp;
    }

    public final synchronized View zzahm() {
        return this.zzfkw;
    }

    public final synchronized String getHeadline() {
        return zzft("headline");
    }

    public final synchronized List<?> getImages() {
        return this.zzeif;
    }

    @Nullable
    public final zzaas zzahn() {
        List<?> list = this.zzeif;
        if (list != null && list.size() != 0) {
            Object obj = this.zzeif.get(0);
            if (obj instanceof IBinder) {
                return zzaar.zzl((IBinder) obj);
            }
        }
        return null;
    }

    public final synchronized List<zzxd> getMuteThisAdReasons() {
        return this.zzcwe;
    }

    @Nullable
    public final synchronized zzxd zzaho() {
        return this.zzfkx;
    }

    public final synchronized String getBody() {
        return zzft(TtmlNode.TAG_BODY);
    }

    public final synchronized Bundle getExtras() {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        return this.extras;
    }

    public final synchronized String getCallToAction() {
        return zzft("call_to_action");
    }

    public final synchronized View zzahp() {
        return this.zzfla;
    }

    public final synchronized IObjectWrapper zzql() {
        return this.zzflb;
    }

    public final synchronized String getStore() {
        return zzft("store");
    }

    public final synchronized String getPrice() {
        return zzft("price");
    }

    public final synchronized double getStarRating() {
        return this.zzeik;
    }

    public final synchronized zzaas zzqj() {
        return this.zzflc;
    }

    public final synchronized String getAdvertiser() {
        return zzft("advertiser");
    }

    public final synchronized zzaas zzqm() {
        return this.zzfld;
    }

    public final synchronized String getCustomTemplateId() {
        return this.zzfle;
    }

    public final synchronized zzbbc zzahq() {
        return this.zzfky;
    }

    @Nullable
    public final synchronized zzbbc zzahr() {
        return this.zzfkz;
    }

    @Nullable
    public final synchronized IObjectWrapper zzahs() {
        return this.zzfeg;
    }

    public final synchronized SimpleArrayMap<String, zzaae> zzaht() {
        return this.zzflf;
    }

    public final synchronized float getMediaContentAspectRatio() {
        return this.zzeiv;
    }

    @Nullable
    public final synchronized String zzahu() {
        return this.zzlr;
    }

    public final synchronized SimpleArrayMap<String, String> zzahv() {
        return this.zzflg;
    }

    public final synchronized void destroy() {
        if (this.zzfky != null) {
            this.zzfky.destroy();
            this.zzfky = null;
        }
        if (this.zzfkz != null) {
            this.zzfkz.destroy();
            this.zzfkz = null;
        }
        this.zzfeg = null;
        this.zzflf.clear();
        this.zzflg.clear();
        this.zzfkv = null;
        this.zzcvp = null;
        this.zzfkw = null;
        this.zzeif = null;
        this.extras = null;
        this.zzfla = null;
        this.zzflb = null;
        this.zzflc = null;
        this.zzfld = null;
        this.zzfle = null;
    }

    public static zzbtx zza(zzajr zzajrVar) {
        try {
            zzwk videoController = zzajrVar.getVideoController();
            zzaak zzaakVarZzqk = zzajrVar.zzqk();
            View view = (View) zzat(zzajrVar.zzru());
            String headline = zzajrVar.getHeadline();
            List<?> images = zzajrVar.getImages();
            String body = zzajrVar.getBody();
            Bundle extras = zzajrVar.getExtras();
            String callToAction = zzajrVar.getCallToAction();
            View view2 = (View) zzat(zzajrVar.zzrv());
            IObjectWrapper iObjectWrapperZzql = zzajrVar.zzql();
            String advertiser = zzajrVar.getAdvertiser();
            zzaas zzaasVarZzqm = zzajrVar.zzqm();
            zzbtx zzbtxVar = new zzbtx();
            zzbtxVar.zzfku = 1;
            zzbtxVar.zzfkv = videoController;
            zzbtxVar.zzcvp = zzaakVarZzqk;
            zzbtxVar.zzfkw = view;
            zzbtxVar.zzo("headline", headline);
            zzbtxVar.zzeif = images;
            zzbtxVar.zzo(TtmlNode.TAG_BODY, body);
            zzbtxVar.extras = extras;
            zzbtxVar.zzo("call_to_action", callToAction);
            zzbtxVar.zzfla = view2;
            zzbtxVar.zzflb = iObjectWrapperZzql;
            zzbtxVar.zzo("advertiser", advertiser);
            zzbtxVar.zzfld = zzaasVarZzqm;
            return zzbtxVar;
        } catch (RemoteException e) {
            zzatm.zzd("Failed to get native ad from content ad mapper", e);
            return null;
        }
    }

    public static zzbtx zza(zzajq zzajqVar) {
        try {
            zzwk videoController = zzajqVar.getVideoController();
            zzaak zzaakVarZzqk = zzajqVar.zzqk();
            View view = (View) zzat(zzajqVar.zzru());
            String headline = zzajqVar.getHeadline();
            List<?> images = zzajqVar.getImages();
            String body = zzajqVar.getBody();
            Bundle extras = zzajqVar.getExtras();
            String callToAction = zzajqVar.getCallToAction();
            View view2 = (View) zzat(zzajqVar.zzrv());
            IObjectWrapper iObjectWrapperZzql = zzajqVar.zzql();
            String store = zzajqVar.getStore();
            String price = zzajqVar.getPrice();
            double starRating = zzajqVar.getStarRating();
            zzaas zzaasVarZzqj = zzajqVar.zzqj();
            zzbtx zzbtxVar = new zzbtx();
            zzbtxVar.zzfku = 2;
            zzbtxVar.zzfkv = videoController;
            zzbtxVar.zzcvp = zzaakVarZzqk;
            zzbtxVar.zzfkw = view;
            zzbtxVar.zzo("headline", headline);
            zzbtxVar.zzeif = images;
            zzbtxVar.zzo(TtmlNode.TAG_BODY, body);
            zzbtxVar.extras = extras;
            zzbtxVar.zzo("call_to_action", callToAction);
            zzbtxVar.zzfla = view2;
            zzbtxVar.zzflb = iObjectWrapperZzql;
            zzbtxVar.zzo("store", store);
            zzbtxVar.zzo("price", price);
            zzbtxVar.zzeik = starRating;
            zzbtxVar.zzflc = zzaasVarZzqj;
            return zzbtxVar;
        } catch (RemoteException e) {
            zzatm.zzd("Failed to get native ad from app install ad mapper", e);
            return null;
        }
    }

    public static zzbtx zzb(zzajw zzajwVar) {
        try {
            return zza(zzajwVar.getVideoController(), zzajwVar.zzqk(), (View) zzat(zzajwVar.zzru()), zzajwVar.getHeadline(), zzajwVar.getImages(), zzajwVar.getBody(), zzajwVar.getExtras(), zzajwVar.getCallToAction(), (View) zzat(zzajwVar.zzrv()), zzajwVar.zzql(), zzajwVar.getStore(), zzajwVar.getPrice(), zzajwVar.getStarRating(), zzajwVar.zzqj(), zzajwVar.getAdvertiser(), zzajwVar.getMediaContentAspectRatio());
        } catch (RemoteException e) {
            zzatm.zzd("Failed to get native ad assets from unified ad mapper", e);
            return null;
        }
    }

    public static zzbtx zzb(zzajq zzajqVar) {
        try {
            return zza(zzajqVar.getVideoController(), zzajqVar.zzqk(), (View) zzat(zzajqVar.zzru()), zzajqVar.getHeadline(), zzajqVar.getImages(), zzajqVar.getBody(), zzajqVar.getExtras(), zzajqVar.getCallToAction(), (View) zzat(zzajqVar.zzrv()), zzajqVar.zzql(), zzajqVar.getStore(), zzajqVar.getPrice(), zzajqVar.getStarRating(), zzajqVar.zzqj(), null, 0.0f);
        } catch (RemoteException e) {
            zzatm.zzd("Failed to get native ad assets from app install ad mapper", e);
            return null;
        }
    }

    public static zzbtx zzb(zzajr zzajrVar) {
        try {
            return zza(zzajrVar.getVideoController(), zzajrVar.zzqk(), (View) zzat(zzajrVar.zzru()), zzajrVar.getHeadline(), zzajrVar.getImages(), zzajrVar.getBody(), zzajrVar.getExtras(), zzajrVar.getCallToAction(), (View) zzat(zzajrVar.zzrv()), zzajrVar.zzql(), null, null, -1.0d, zzajrVar.zzqm(), zzajrVar.getAdvertiser(), 0.0f);
        } catch (RemoteException e) {
            zzatm.zzd("Failed to get native ad assets from content ad mapper", e);
            return null;
        }
    }

    private static zzbtx zza(zzwk zzwkVar, zzaak zzaakVar, View view, String str, List list, String str2, Bundle bundle, String str3, View view2, IObjectWrapper iObjectWrapper, String str4, String str5, double d, zzaas zzaasVar, String str6, float f) {
        zzbtx zzbtxVar = new zzbtx();
        zzbtxVar.zzfku = 6;
        zzbtxVar.zzfkv = zzwkVar;
        zzbtxVar.zzcvp = zzaakVar;
        zzbtxVar.zzfkw = view;
        zzbtxVar.zzo("headline", str);
        zzbtxVar.zzeif = list;
        zzbtxVar.zzo(TtmlNode.TAG_BODY, str2);
        zzbtxVar.extras = bundle;
        zzbtxVar.zzo("call_to_action", str3);
        zzbtxVar.zzfla = view2;
        zzbtxVar.zzflb = iObjectWrapper;
        zzbtxVar.zzo("store", str4);
        zzbtxVar.zzo("price", str5);
        zzbtxVar.zzeik = d;
        zzbtxVar.zzflc = zzaasVar;
        zzbtxVar.zzo("advertiser", str6);
        zzbtxVar.setMediaContentAspectRatio(f);
        return zzbtxVar;
    }

    private static <T> T zzat(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return null;
        }
        return (T) ObjectWrapper.unwrap(iObjectWrapper);
    }
}
