package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvg<T> implements zzdvi<T> {
    private zzdvv<T> zzhwz;

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final T get() {
        zzdvv<T> zzdvvVar = this.zzhwz;
        if (zzdvvVar != null) {
            return zzdvvVar.get();
        }
        throw new IllegalStateException();
    }

    public static <T> void zzaw(zzdvv<T> zzdvvVar, zzdvv<T> zzdvvVar2) {
        zzdvo.checkNotNull(zzdvvVar2);
        zzdvg zzdvgVar = (zzdvg) zzdvvVar;
        if (zzdvgVar.zzhwz != null) {
            throw new IllegalStateException();
        }
        zzdvgVar.zzhwz = zzdvvVar2;
    }
}
