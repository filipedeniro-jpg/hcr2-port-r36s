package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdd extends zzax {

    @zzcc
    private String experimentId;

    @zzcc
    private String experimentStartTime;

    @zzcc
    @zzbe
    private Long timeToLiveMillis;

    @zzcc
    private String triggerEvent;

    @zzcc
    @zzbe
    private Long triggerTimeoutMillis;

    @zzcc
    private String variantId;

    public final zzdd zzan(String str) {
        this.experimentId = str;
        return this;
    }

    public final zzdd zzao(String str) {
        this.experimentStartTime = str;
        return this;
    }

    public final zzdd zza(Long l) {
        this.timeToLiveMillis = l;
        return this;
    }

    public final zzdd zzap(String str) {
        this.triggerEvent = str;
        return this;
    }

    public final zzdd zzb(Long l) {
        this.triggerTimeoutMillis = l;
        return this;
    }

    public final zzdd zzaq(String str) {
        this.variantId = str;
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax
    /* JADX INFO: renamed from: zza */
    public final /* synthetic */ zzax zzb(String str, Object obj) {
        return (zzdd) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax
    /* JADX INFO: renamed from: zza */
    public final /* synthetic */ zzax clone() {
        return (zzdd) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzb */
    public final /* synthetic */ zzby clone() {
        return (zzdd) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby
    public final /* synthetic */ zzby zzb(String str, Object obj) {
        return (zzdd) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzdd) super.clone();
    }
}
