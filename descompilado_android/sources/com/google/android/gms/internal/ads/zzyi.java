package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzyi<T> {
    private final String zzce;
    private final int zzcfj;
    private final T zzcfk;

    private zzyi(int i, String str, T t) {
        this.zzcfj = i;
        this.zzce = str;
        this.zzcfk = t;
        zzuo.zzoi().zza(this);
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zza(Bundle bundle);

    protected abstract T zza(JSONObject jSONObject);

    public abstract void zza(SharedPreferences.Editor editor, T t);

    public final String getKey() {
        return this.zzce;
    }

    public final T zzpm() {
        return this.zzcfk;
    }

    public static zzyi<Boolean> zza(int i, String str, Boolean bool) {
        return new zzyh(i, str, bool);
    }

    public static zzyi<Integer> zza(int i, String str, int i2) {
        return new zzyk(i, str, Integer.valueOf(i2));
    }

    public static zzyi<Long> zza(int i, String str, long j) {
        return new zzyj(i, str, Long.valueOf(j));
    }

    public static zzyi<Float> zza(int i, String str, float f) {
        return new zzym(i, str, Float.valueOf(f));
    }

    public static zzyi<String> zza(int i, String str, String str2) {
        return new zzyl(i, str, str2);
    }

    public static zzyi<String> zzb(int i, String str) {
        zzyi<String> zzyiVarZza = zza(i, str, (String) null);
        zzuo.zzoi().zzb(zzyiVarZza);
        return zzyiVarZza;
    }

    public static zzyi<String> zzc(int i, String str) {
        zzyi<String> zzyiVarZza = zza(i, str, (String) null);
        zzuo.zzoi().zzc(zzyiVarZza);
        return zzyiVarZza;
    }

    public final int getSource() {
        return this.zzcfj;
    }

    /* synthetic */ zzyi(int i, String str, Object obj, zzyh zzyhVar) {
        this(i, str, obj);
    }
}
