package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvm {
    private boolean zzbke;
    private zztw zzbli;
    private zzaai zzddy;
    private zzafj zzdjz;
    private zzvs zzgjq;
    private zzyc zzgjr;
    private zztp zzgjs;
    private String zzgjt;
    private ArrayList<String> zzgju;
    private ArrayList<String> zzgjv;
    private zztx zzgjw;
    private PublisherAdViewOptions zzgjx;

    @Nullable
    private zzvm zzgjy;
    private int zzgcq = 1;
    public final Set<String> zzgjz = new HashSet();

    public final zzcvm zzg(zztp zztpVar) {
        this.zzgjs = zztpVar;
        return this;
    }

    public final zztp zzamx() {
        return this.zzgjs;
    }

    public final zzcvm zzd(zztw zztwVar) {
        this.zzbli = zztwVar;
        return this;
    }

    public final zztw zzjo() {
        return this.zzbli;
    }

    public final zzcvm zzc(zzvs zzvsVar) {
        this.zzgjq = zzvsVar;
        return this;
    }

    public final zzcvm zzgf(String str) {
        this.zzgjt = str;
        return this;
    }

    public final String zzamy() {
        return this.zzgjt;
    }

    public final zzcvm zzc(zzyc zzycVar) {
        this.zzgjr = zzycVar;
        return this;
    }

    public final zzcvm zzbe(boolean z) {
        this.zzbke = z;
        return this;
    }

    public final zzcvm zzdj(int i) {
        this.zzgcq = i;
        return this;
    }

    public final zzcvm zzb(ArrayList<String> arrayList) {
        this.zzgju = arrayList;
        return this;
    }

    public final zzcvm zzc(ArrayList<String> arrayList) {
        this.zzgjv = arrayList;
        return this;
    }

    public final zzcvm zzb(zzaai zzaaiVar) {
        this.zzddy = zzaaiVar;
        return this;
    }

    public final zzcvm zzb(zztx zztxVar) {
        this.zzgjw = zztxVar;
        return this;
    }

    public final zzcvm zzb(zzafj zzafjVar) {
        this.zzdjz = zzafjVar;
        this.zzgjr = new zzyc(false, true, false);
        return this;
    }

    public final zzcvm zzb(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzgjx = publisherAdViewOptions;
        if (publisherAdViewOptions != null) {
            this.zzbke = publisherAdViewOptions.getManualImpressionsEnabled();
            this.zzgjy = publisherAdViewOptions.zzjd();
        }
        return this;
    }

    public final zzcvk zzamz() {
        Preconditions.checkNotNull(this.zzgjt, "ad unit must not be null");
        Preconditions.checkNotNull(this.zzbli, "ad size must not be null");
        Preconditions.checkNotNull(this.zzgjs, "ad request must not be null");
        return new zzcvk(this);
    }
}
