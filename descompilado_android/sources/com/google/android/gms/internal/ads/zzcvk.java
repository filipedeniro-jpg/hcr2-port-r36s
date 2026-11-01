package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvk {
    public final zztw zzbli;
    public final zzaai zzddy;

    @Nullable
    public final zzafj zzdjz;
    public final int zzgcq;

    @Nullable
    public final zzvs zzgjq;

    @Nullable
    public final zzyc zzgjr;
    public final zztp zzgjs;
    public final String zzgjt;
    public final ArrayList<String> zzgju;
    public final ArrayList<String> zzgjv;
    public final zztx zzgjw;
    public final PublisherAdViewOptions zzgjx;
    public final zzvm zzgjy;
    public final Set<String> zzgjz;

    private zzcvk(zzcvm zzcvmVar) {
        this.zzbli = zzcvmVar.zzbli;
        this.zzgjt = zzcvmVar.zzgjt;
        this.zzgjq = zzcvmVar.zzgjq;
        this.zzgjs = new zztp(zzcvmVar.zzgjs.versionCode, zzcvmVar.zzgjs.zzcbn, zzcvmVar.zzgjs.extras, zzcvmVar.zzgjs.zzcbo, zzcvmVar.zzgjs.zzcbp, zzcvmVar.zzgjs.zzcbq, zzcvmVar.zzgjs.zzabj, zzcvmVar.zzgjs.zzbke || zzcvmVar.zzbke, zzcvmVar.zzgjs.zzcbr, zzcvmVar.zzgjs.zzcbs, zzcvmVar.zzgjs.zzng, zzcvmVar.zzgjs.zzcbt, zzcvmVar.zzgjs.zzcbu, zzcvmVar.zzgjs.zzcbv, zzcvmVar.zzgjs.zzcbw, zzcvmVar.zzgjs.zzcbx, zzcvmVar.zzgjs.zzcby, zzcvmVar.zzgjs.zzcbz, zzcvmVar.zzgjs.zzcca, zzcvmVar.zzgjs.zzabk, zzcvmVar.zzgjs.zzabl);
        this.zzgjr = zzcvmVar.zzgjr != null ? zzcvmVar.zzgjr : zzcvmVar.zzddy != null ? zzcvmVar.zzddy.zzcvo : null;
        this.zzgju = zzcvmVar.zzgju;
        this.zzgjv = zzcvmVar.zzgjv;
        this.zzddy = zzcvmVar.zzgju != null ? zzcvmVar.zzddy == null ? new zzaai(new NativeAdOptions.Builder().build()) : zzcvmVar.zzddy : null;
        this.zzgjw = zzcvmVar.zzgjw;
        this.zzgcq = zzcvmVar.zzgcq;
        this.zzgjx = zzcvmVar.zzgjx;
        this.zzgjy = zzcvmVar.zzgjy;
        this.zzdjz = zzcvmVar.zzdjz;
        this.zzgjz = zzcvmVar.zzgjz;
    }

    public final zzack zzamv() {
        PublisherAdViewOptions publisherAdViewOptions = this.zzgjx;
        if (publisherAdViewOptions == null) {
            return null;
        }
        return publisherAdViewOptions.zzje();
    }
}
