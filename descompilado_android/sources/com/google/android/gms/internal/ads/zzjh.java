package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjh implements zzji {
    private final byte[] zzana = new byte[8];
    private final Stack<zzjj> zzanb = new Stack<>();
    private final zzjr zzanc = new zzjr();
    private zzjl zzand;
    private int zzane;
    private int zzanf;
    private long zzang;

    zzjh() {
    }

    @Override // com.google.android.gms.internal.ads.zzji
    public final void zza(zzjl zzjlVar) {
        this.zzand = zzjlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzji
    public final void reset() {
        this.zzane = 0;
        this.zzanb.clear();
        this.zzanc.reset();
    }

    @Override // com.google.android.gms.internal.ads.zzji
    public final boolean zzb(zzix zzixVar) throws InterruptedException, IOException {
        String str;
        double dLongBitsToDouble;
        int iZzam;
        int iZza;
        zznt.checkState(this.zzand != null);
        while (true) {
            if (!this.zzanb.isEmpty() && zzixVar.getPosition() >= this.zzanb.peek().zzanh) {
                this.zzand.zzai(this.zzanb.pop().zzanf);
                return true;
            }
            if (this.zzane == 0) {
                long jZza = this.zzanc.zza(zzixVar, true, false, 4);
                if (jZza == -2) {
                    zzixVar.zzfz();
                    while (true) {
                        zzixVar.zza(this.zzana, 0, 4);
                        iZzam = zzjr.zzam(this.zzana[0]);
                        if (iZzam != -1 && iZzam <= 4) {
                            iZza = (int) zzjr.zza(this.zzana, iZzam, false);
                            if (this.zzand.zzah(iZza)) {
                                break;
                            }
                        }
                        zzixVar.zzab(1);
                    }
                    zzixVar.zzab(iZzam);
                    jZza = iZza;
                }
                if (jZza == -1) {
                    return false;
                }
                this.zzanf = (int) jZza;
                this.zzane = 1;
            }
            if (this.zzane == 1) {
                this.zzang = this.zzanc.zza(zzixVar, false, true, 8);
                this.zzane = 2;
            }
            int iZzag = this.zzand.zzag(this.zzanf);
            if (iZzag != 0) {
                if (iZzag == 1) {
                    long position = zzixVar.getPosition();
                    this.zzanb.add(new zzjj(this.zzanf, this.zzang + position));
                    this.zzand.zzd(this.zzanf, position, this.zzang);
                    this.zzane = 0;
                    return true;
                }
                if (iZzag == 2) {
                    long j = this.zzang;
                    if (j > 8) {
                        StringBuilder sb = new StringBuilder(42);
                        sb.append("Invalid integer size: ");
                        sb.append(j);
                        throw new zzgt(sb.toString());
                    }
                    this.zzand.zzc(this.zzanf, zza(zzixVar, (int) j));
                    this.zzane = 0;
                    return true;
                }
                if (iZzag == 3) {
                    long j2 = this.zzang;
                    if (j2 > 2147483647L) {
                        StringBuilder sb2 = new StringBuilder(41);
                        sb2.append("String element size: ");
                        sb2.append(j2);
                        throw new zzgt(sb2.toString());
                    }
                    zzjl zzjlVar = this.zzand;
                    int i = this.zzanf;
                    int i2 = (int) j2;
                    if (i2 == 0) {
                        str = "";
                    } else {
                        byte[] bArr = new byte[i2];
                        zzixVar.readFully(bArr, 0, i2);
                        str = new String(bArr);
                    }
                    zzjlVar.zza(i, str);
                    this.zzane = 0;
                    return true;
                }
                if (iZzag == 4) {
                    this.zzand.zza(this.zzanf, (int) this.zzang, zzixVar);
                    this.zzane = 0;
                    return true;
                }
                if (iZzag == 5) {
                    long j3 = this.zzang;
                    if (j3 != 4 && j3 != 8) {
                        StringBuilder sb3 = new StringBuilder(40);
                        sb3.append("Invalid float size: ");
                        sb3.append(j3);
                        throw new zzgt(sb3.toString());
                    }
                    zzjl zzjlVar2 = this.zzand;
                    int i3 = this.zzanf;
                    int i4 = (int) this.zzang;
                    long jZza2 = zza(zzixVar, i4);
                    if (i4 == 4) {
                        dLongBitsToDouble = Float.intBitsToFloat((int) jZza2);
                    } else {
                        dLongBitsToDouble = Double.longBitsToDouble(jZza2);
                    }
                    zzjlVar2.zza(i3, dLongBitsToDouble);
                    this.zzane = 0;
                    return true;
                }
                StringBuilder sb4 = new StringBuilder(32);
                sb4.append("Invalid element type ");
                sb4.append(iZzag);
                throw new zzgt(sb4.toString());
            }
            zzixVar.zzab((int) this.zzang);
            this.zzane = 0;
        }
    }

    private final long zza(zzix zzixVar, int i) throws InterruptedException, IOException {
        zzixVar.readFully(this.zzana, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.zzana[i2] & 255));
        }
        return j;
    }
}
