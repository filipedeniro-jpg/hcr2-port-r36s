package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdtu implements Cloneable {
    private static final zzdtx zzhql = new zzdtx();
    private int mSize;
    private boolean zzhqm;
    private int[] zzhqn;
    private zzdtx[] zzhqo;

    zzdtu() {
        this(10);
    }

    private zzdtu(int i) {
        this.zzhqm = false;
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
        }
        int i5 = i2 / 4;
        this.zzhqn = new int[i5];
        this.zzhqo = new zzdtx[i5];
        this.mSize = 0;
    }

    final int size() {
        return this.mSize;
    }

    final zzdtx zzhg(int i) {
        return this.zzhqo[i];
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0043 A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdtu)) {
            return false;
        }
        zzdtu zzdtuVar = (zzdtu) obj;
        int i = this.mSize;
        if (i != zzdtuVar.mSize) {
            return false;
        }
        int[] iArr = this.zzhqn;
        int[] iArr2 = zzdtuVar.zzhqn;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            }
            if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            }
            i2++;
        }
        if (z) {
            zzdtx[] zzdtxVarArr = this.zzhqo;
            zzdtx[] zzdtxVarArr2 = zzdtuVar.zzhqo;
            int i3 = this.mSize;
            for (int i4 = 0; i4 < i3; i4++) {
                if (!zzdtxVarArr[i4].equals(zzdtxVarArr2[i4])) {
                    z2 = false;
                    if (z2) {
                        return true;
                    }
                }
            }
            z2 = true;
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 17;
        for (int i = 0; i < this.mSize; i++) {
            iHashCode = (((iHashCode * 31) + this.zzhqn[i]) * 31) + this.zzhqo[i].hashCode();
        }
        return iHashCode;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzdtu zzdtuVar = new zzdtu(i);
        System.arraycopy(this.zzhqn, 0, zzdtuVar.zzhqn, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            zzdtx[] zzdtxVarArr = this.zzhqo;
            if (zzdtxVarArr[i2] != null) {
                zzdtuVar.zzhqo[i2] = (zzdtx) zzdtxVarArr[i2].clone();
            }
        }
        zzdtuVar.mSize = i;
        return zzdtuVar;
    }
}
