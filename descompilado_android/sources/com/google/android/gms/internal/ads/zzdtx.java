package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdtx implements Cloneable {
    private Object value;
    private zzdtv<?, ?> zzhqr;
    private List<Object> zzhqs = new ArrayList();

    zzdtx() {
    }

    final int zznv() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.zzhqs.iterator();
        if (!it.hasNext()) {
            return 0;
        }
        it.next();
        throw new NoSuchMethodError();
    }

    final void zza(zzdtq zzdtqVar) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.zzhqs.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    public final boolean equals(Object obj) {
        List<Object> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdtx)) {
            return false;
        }
        zzdtx zzdtxVar = (zzdtx) obj;
        if (this.value != null && zzdtxVar.value != null) {
            zzdtv<?, ?> zzdtvVar = this.zzhqr;
            if (zzdtvVar != zzdtxVar.zzhqr) {
                return false;
            }
            if (!zzdtvVar.zzhqp.isArray()) {
                return this.value.equals(zzdtxVar.value);
            }
            Object obj2 = this.value;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) zzdtxVar.value);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) zzdtxVar.value);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) zzdtxVar.value);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) zzdtxVar.value);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) zzdtxVar.value);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) zzdtxVar.value);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) zzdtxVar.value);
        }
        List<Object> list2 = this.zzhqs;
        if (list2 != null && (list = zzdtxVar.zzhqs) != null) {
            return list2.equals(list);
        }
        try {
            return Arrays.equals(toByteArray(), zzdtxVar.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zznv()];
        zza(zzdtq.zzae(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzbce, reason: merged with bridge method [inline-methods] */
    public final zzdtx clone() {
        zzdtx zzdtxVar = new zzdtx();
        try {
            zzdtxVar.zzhqr = this.zzhqr;
            if (this.zzhqs == null) {
                zzdtxVar.zzhqs = null;
            } else {
                zzdtxVar.zzhqs.addAll(this.zzhqs);
            }
            if (this.value != null) {
                if (this.value instanceof zzdtz) {
                    zzdtxVar.value = (zzdtz) ((zzdtz) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzdtxVar.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzdtxVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzdtxVar.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzdtxVar.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzdtxVar.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzdtxVar.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzdtxVar.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzdtz[]) {
                        zzdtz[] zzdtzVarArr = (zzdtz[]) this.value;
                        zzdtz[] zzdtzVarArr2 = new zzdtz[zzdtzVarArr.length];
                        zzdtxVar.value = zzdtzVarArr2;
                        while (i < zzdtzVarArr.length) {
                            zzdtzVarArr2[i] = (zzdtz) zzdtzVarArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzdtxVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
