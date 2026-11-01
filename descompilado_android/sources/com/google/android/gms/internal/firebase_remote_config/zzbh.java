package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
final class zzbh extends zzaz {
    private final zzfo zzdu;
    private final zzbf zzdv;

    zzbh(zzbf zzbfVar, zzfo zzfoVar) {
        this.zzdv = zzbfVar;
        this.zzdu = zzfoVar;
        zzfoVar.setLenient(true);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void flush() throws IOException {
        this.zzdu.flush();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void writeBoolean(boolean z) throws IOException {
        this.zzdu.zzc(z);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzat() throws IOException {
        this.zzdu.zzeg();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzav() throws IOException {
        this.zzdu.zzei();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzad(String str) throws IOException {
        this.zzdu.zzbf(str);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzaw() throws IOException {
        this.zzdu.zzel();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zze(int i) throws IOException {
        this.zzdu.zzd(i);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zza(long j) throws IOException {
        this.zzdu.zzd(j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zza(BigInteger bigInteger) throws IOException {
        this.zzdu.zza(bigInteger);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zza(double d) throws IOException {
        this.zzdu.zzb(d);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zza(float f) throws IOException {
        this.zzdu.zzb(f);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zza(BigDecimal bigDecimal) throws IOException {
        this.zzdu.zza(bigDecimal);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzas() throws IOException {
        this.zzdu.zzef();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzau() throws IOException {
        this.zzdu.zzeh();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void writeString(String str) throws IOException {
        this.zzdu.zzbg(str);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaz
    public final void zzax() throws IOException {
        this.zzdu.setIndent("  ");
    }
}
