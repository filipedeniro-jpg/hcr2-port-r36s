package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
class zzgh extends zzgi {
    protected final byte[] zzpc;

    zzgh(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzpc = bArr;
    }

    protected int zzey() {
        return 0;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    public byte zzv(int i) {
        return this.zzpc[i];
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    byte zzw(int i) {
        return this.zzpc[i];
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    public int size() {
        return this.zzpc.length;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    public final zzfx zzb(int i, int i2) {
        int iZzb = zzb(0, i2, size());
        if (iZzb == 0) {
            return zzfx.zzov;
        }
        return new zzge(this.zzpc, zzey(), iZzb);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    final void zza(zzfy zzfyVar) throws IOException {
        zzfyVar.zzb(this.zzpc, zzey(), size());
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    protected final String zzc(Charset charset) {
        return new String(this.zzpc, zzey(), size(), charset);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    public final boolean zzew() {
        int iZzey = zzey();
        return zzkg.zze(this.zzpc, iZzey, size() + iZzey);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx) || size() != ((zzfx) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof zzgh) {
            zzgh zzghVar = (zzgh) obj;
            int iZzex = zzex();
            int iZzex2 = zzghVar.zzex();
            if (iZzex == 0 || iZzex2 == 0 || iZzex == iZzex2) {
                return zza(zzghVar, 0, size());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzgi
    final boolean zza(zzfx zzfxVar, int i, int i2) {
        if (i2 > zzfxVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzfxVar.size()) {
            int size2 = zzfxVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzfxVar instanceof zzgh) {
            zzgh zzghVar = (zzgh) zzfxVar;
            byte[] bArr = this.zzpc;
            byte[] bArr2 = zzghVar.zzpc;
            int iZzey = zzey() + i2;
            int iZzey2 = zzey();
            int iZzey3 = zzghVar.zzey();
            while (iZzey2 < iZzey) {
                if (bArr[iZzey2] != bArr2[iZzey3]) {
                    return false;
                }
                iZzey2++;
                iZzey3++;
            }
            return true;
        }
        return zzfxVar.zzb(0, i2).equals(zzb(0, i2));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfx
    protected final int zza(int i, int i2, int i3) {
        return zzhm.zza(i, this.zzpc, zzey(), i3);
    }
}
