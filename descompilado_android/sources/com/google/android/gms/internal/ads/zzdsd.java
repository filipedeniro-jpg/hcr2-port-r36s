package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
interface zzdsd {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException;

    <T> void zza(List<T> list, zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException;

    <K, V> void zza(Map<K, V> map, zzdre<K, V> zzdreVar, zzdpq zzdpqVar) throws IOException;

    long zzaxq() throws IOException;

    long zzaxr() throws IOException;

    int zzaxs() throws IOException;

    long zzaxt() throws IOException;

    int zzaxu() throws IOException;

    boolean zzaxv() throws IOException;

    String zzaxw() throws IOException;

    zzdot zzaxx() throws IOException;

    int zzaxy() throws IOException;

    int zzaxz() throws IOException;

    int zzaya() throws IOException;

    long zzayb() throws IOException;

    int zzayc() throws IOException;

    long zzayd() throws IOException;

    int zzayn() throws IOException;

    boolean zzayo() throws IOException;

    @Deprecated
    <T> T zzb(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException;

    void zzk(List<Double> list) throws IOException;

    void zzl(List<Float> list) throws IOException;

    void zzm(List<Long> list) throws IOException;

    void zzn(List<Long> list) throws IOException;

    void zzo(List<Integer> list) throws IOException;

    void zzp(List<Long> list) throws IOException;

    void zzq(List<Integer> list) throws IOException;

    void zzr(List<Boolean> list) throws IOException;

    void zzs(List<String> list) throws IOException;

    void zzt(List<zzdot> list) throws IOException;

    void zzu(List<Integer> list) throws IOException;

    void zzv(List<Integer> list) throws IOException;

    void zzw(List<Integer> list) throws IOException;

    void zzx(List<Long> list) throws IOException;

    void zzy(List<Integer> list) throws IOException;

    void zzz(List<Long> list) throws IOException;
}
