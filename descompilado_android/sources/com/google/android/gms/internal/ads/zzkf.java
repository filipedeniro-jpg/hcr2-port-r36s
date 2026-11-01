package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkf implements zziu, zzjd {
    private static final zziz zzani = new zzke();
    private static final int zzawn = zzoh.zzbi("qt  ");
    private long zzagm;
    private int zzape;
    private int zzapf;
    private zziw zzapi;
    private int zzawq;
    private int zzawr;
    private long zzaws;
    private int zzawt;
    private zzoa zzawu;
    private zzkh[] zzawv;
    private boolean zzaww;
    private final zzoa zzawo = new zzoa(16);
    private final Stack<zzjt> zzawp = new Stack<>();
    private final zzoa zzanp = new zzoa(zznz.zzbfx);
    private final zzoa zzanq = new zzoa(4);

    @Override // com.google.android.gms.internal.ads.zziu
    public final void release() {
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final boolean zzfy() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final boolean zza(zzix zzixVar) throws InterruptedException, IOException {
        return zzkg.zzd(zzixVar);
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final void zza(zziw zziwVar) {
        this.zzapi = zziwVar;
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final void zzc(long j, long j2) {
        this.zzawp.clear();
        this.zzawt = 0;
        this.zzapf = 0;
        this.zzape = 0;
        if (j == 0) {
            zzgk();
            return;
        }
        zzkh[] zzkhVarArr = this.zzawv;
        if (zzkhVarArr != null) {
            for (zzkh zzkhVar : zzkhVarArr) {
                zzkl zzklVar = zzkhVar.zzawz;
                int iZzdw = zzklVar.zzdw(j2);
                if (iZzdw == -1) {
                    iZzdw = zzklVar.zzdx(j2);
                }
                zzkhVar.zzavd = iZzdw;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:102:0x0202  */
    /* JADX WARN: Code duplicated, block: B:104:0x0222  */
    /* JADX WARN: Code duplicated, block: B:105:0x0226  */
    /* JADX WARN: Code duplicated, block: B:107:0x022c  */
    /* JADX WARN: Code duplicated, block: B:141:0x0271  */
    /* JADX WARN: Code duplicated, block: B:143:0x0274  */
    /* JADX WARN: Code duplicated, block: B:145:0x0278  */
    /* JADX WARN: Code duplicated, block: B:146:0x027a  */
    /* JADX WARN: Code duplicated, block: B:149:0x0287  */
    /* JADX WARN: Code duplicated, block: B:150:0x0289  */
    /* JADX WARN: Code duplicated, block: B:152:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:157:0x019a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x0006 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0196  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ce  */
    @Override // com.google.android.gms.internal.ads.zziu
    public final int zza(zzix zzixVar, zzja zzjaVar) throws InterruptedException, IOException {
        zzkh[] zzkhVarArr;
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        boolean z4;
        int i2;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        long position;
        while (true) {
            int i3 = this.zzawq;
            if (i3 == 0) {
                if (this.zzawt == 0) {
                    if (zzixVar.zza(this.zzawo.data, 0, 8, true)) {
                        this.zzawt = 8;
                        this.zzawo.zzbd(0);
                        this.zzaws = this.zzawo.zzig();
                        this.zzawr = this.zzawo.readInt();
                        if (this.zzaws == 1) {
                            zzixVar.readFully(this.zzawo.data, 8, 8);
                            this.zzawt += 8;
                            this.zzaws = this.zzawo.zzik();
                        }
                        i = this.zzawr;
                        if (i != zzjq.zzars) {
                            z4 = true;
                        } else {
                            z4 = true;
                        }
                        if (z4) {
                            position = (zzixVar.getPosition() + this.zzaws) - ((long) this.zzawt);
                            this.zzawp.add(new zzjt(this.zzawr, position));
                            if (this.zzaws == this.zzawt) {
                                zzdv(position);
                            } else {
                                zzgk();
                            }
                            z6 = true;
                        } else {
                            i2 = this.zzawr;
                            if (i2 != zzjq.zzasi) {
                                z5 = true;
                            } else {
                                z5 = true;
                            }
                            if (z5) {
                                if (this.zzawt == 8) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                zznt.checkState(z7);
                                if (this.zzaws <= 2147483647L) {
                                    z8 = true;
                                } else {
                                    z8 = false;
                                }
                                zznt.checkState(z8);
                                this.zzawu = new zzoa((int) this.zzaws);
                                System.arraycopy(this.zzawo.data, 0, this.zzawu.data, 0, 8);
                                z6 = true;
                                this.zzawq = 1;
                            } else {
                                z6 = true;
                                this.zzawu = null;
                                this.zzawq = 1;
                            }
                        }
                    } else {
                        z6 = false;
                    }
                } else {
                    if (this.zzaws == 1) {
                        zzixVar.readFully(this.zzawo.data, 8, 8);
                        this.zzawt += 8;
                        this.zzaws = this.zzawo.zzik();
                    }
                    i = this.zzawr;
                    if (i != zzjq.zzars || i == zzjq.zzaru || i == zzjq.zzarv || i == zzjq.zzarw || i == zzjq.zzarx || i == zzjq.zzasg) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        position = (zzixVar.getPosition() + this.zzaws) - ((long) this.zzawt);
                        this.zzawp.add(new zzjt(this.zzawr, position));
                        if (this.zzaws == this.zzawt) {
                            zzdv(position);
                        } else {
                            zzgk();
                        }
                        z6 = true;
                    } else {
                        i2 = this.zzawr;
                        if (i2 != zzjq.zzasi || i2 == zzjq.zzart || i2 == zzjq.zzasj || i2 == zzjq.zzask || i2 == zzjq.zzatd || i2 == zzjq.zzate || i2 == zzjq.zzatf || i2 == zzjq.zzash || i2 == zzjq.zzatg || i2 == zzjq.zzath || i2 == zzjq.zzati || i2 == zzjq.zzatj || i2 == zzjq.zzatk || i2 == zzjq.zzasf || i2 == zzjq.zzaqr || i2 == zzjq.zzatr) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            if (this.zzawt == 8) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            zznt.checkState(z7);
                            if (this.zzaws <= 2147483647L) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            zznt.checkState(z8);
                            this.zzawu = new zzoa((int) this.zzaws);
                            System.arraycopy(this.zzawo.data, 0, this.zzawu.data, 0, 8);
                            z6 = true;
                            this.zzawq = 1;
                        } else {
                            z6 = true;
                            this.zzawu = null;
                            this.zzawq = 1;
                        }
                    }
                }
                if (!z6) {
                    return -1;
                }
            } else {
                if (i3 != 1) {
                    if (i3 != 2) {
                        throw new IllegalStateException();
                    }
                    long j = Long.MAX_VALUE;
                    int i4 = 0;
                    int i5 = -1;
                    while (true) {
                        zzkhVarArr = this.zzawv;
                        if (i4 >= zzkhVarArr.length) {
                            break;
                        }
                        zzkh zzkhVar = zzkhVarArr[i4];
                        int i6 = zzkhVar.zzavd;
                        if (i6 != zzkhVar.zzawz.zzavb) {
                            long j2 = zzkhVar.zzawz.zzamm[i6];
                            if (j2 < j) {
                                i5 = i4;
                                j = j2;
                            }
                        }
                        i4++;
                    }
                    if (i5 == -1) {
                        return -1;
                    }
                    zzkh zzkhVar2 = zzkhVarArr[i5];
                    zzjf zzjfVar = zzkhVar2.zzaxa;
                    int i7 = zzkhVar2.zzavd;
                    long j3 = zzkhVar2.zzawz.zzamm[i7];
                    int i8 = zzkhVar2.zzawz.zzaml[i7];
                    if (zzkhVar2.zzawy.zzaxf == 1) {
                        j3 += 8;
                        i8 -= 8;
                    }
                    long position2 = (j3 - zzixVar.getPosition()) + ((long) this.zzapf);
                    if (position2 < 0 || position2 >= PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                        zzjaVar.zzams = j3;
                        return 1;
                    }
                    zzixVar.zzab((int) position2);
                    if (zzkhVar2.zzawy.zzaqq == 0) {
                        while (true) {
                            int i9 = this.zzapf;
                            if (i9 >= i8) {
                                break;
                            }
                            int iZza = zzjfVar.zza(zzixVar, i8 - i9, false);
                            this.zzapf += iZza;
                            this.zzape -= iZza;
                        }
                    } else {
                        byte[] bArr = this.zzanq.data;
                        bArr[0] = 0;
                        bArr[1] = 0;
                        bArr[2] = 0;
                        int i10 = zzkhVar2.zzawy.zzaqq;
                        int i11 = 4 - zzkhVar2.zzawy.zzaqq;
                        while (this.zzapf < i8) {
                            int i12 = this.zzape;
                            if (i12 == 0) {
                                zzixVar.readFully(this.zzanq.data, i11, i10);
                                this.zzanq.zzbd(0);
                                this.zzape = this.zzanq.zzij();
                                this.zzanp.zzbd(0);
                                zzjfVar.zza(this.zzanp, 4);
                                this.zzapf += 4;
                                i8 += i11;
                            } else {
                                int iZza2 = zzjfVar.zza(zzixVar, i12, false);
                                this.zzapf += iZza2;
                                this.zzape -= iZza2;
                            }
                        }
                    }
                    zzjfVar.zza(zzkhVar2.zzawz.zzaxm[i7], zzkhVar2.zzawz.zzawm[i7], i8, 0, null);
                    zzkhVar2.zzavd++;
                    this.zzapf = 0;
                    this.zzape = 0;
                    return 0;
                }
                long j4 = this.zzaws - ((long) this.zzawt);
                long position3 = zzixVar.getPosition() + j4;
                zzoa zzoaVar = this.zzawu;
                if (zzoaVar != null) {
                    zzixVar.readFully(zzoaVar.data, this.zzawt, (int) j4);
                    if (this.zzawr == zzjq.zzaqr) {
                        zzoa zzoaVar2 = this.zzawu;
                        zzoaVar2.zzbd(8);
                        if (zzoaVar2.readInt() != zzawn) {
                            zzoaVar2.zzbe(4);
                            while (true) {
                                if (zzoaVar2.zzie() <= 0) {
                                    z3 = false;
                                    break;
                                }
                                if (zzoaVar2.readInt() == zzawn) {
                                    z3 = true;
                                    break;
                                }
                            }
                        } else {
                            z3 = true;
                            break;
                        }
                        this.zzaww = z3;
                    } else if (!this.zzawp.isEmpty()) {
                        this.zzawp.peek().zzauj.add(new zzjs(this.zzawr, this.zzawu));
                    }
                } else {
                    if (j4 < PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                        zzixVar.zzab((int) j4);
                    } else {
                        zzjaVar.zzams = zzixVar.getPosition() + j4;
                        z = true;
                    }
                    zzdv(position3);
                    if (z || this.zzawq == 2) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        return 1;
                    }
                }
                z = false;
                zzdv(position3);
                if (z) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                if (z2) {
                    return 1;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long getDurationUs() {
        return this.zzagm;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long zzdt(long j) {
        long j2 = Long.MAX_VALUE;
        for (zzkh zzkhVar : this.zzawv) {
            zzkl zzklVar = zzkhVar.zzawz;
            int iZzdw = zzklVar.zzdw(j);
            if (iZzdw == -1) {
                iZzdw = zzklVar.zzdx(j);
            }
            long j3 = zzklVar.zzamm[iZzdw];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private final void zzgk() {
        this.zzawq = 0;
        this.zzawt = 0;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00e4  */
    private final void zzdv(long j) throws zzgt {
        zzkv zzkvVar;
        zziy zziyVar;
        zzkj zzkjVarZza;
        while (!this.zzawp.isEmpty() && this.zzawp.peek().zzaui == j) {
            zzjt zzjtVarPop = this.zzawp.pop();
            if (zzjtVarPop.type == zzjq.zzars) {
                ArrayList arrayList = new ArrayList();
                zzkv zzkvVarZza = null;
                zziy zziyVar2 = new zziy();
                zzjs zzjsVarZzan = zzjtVarPop.zzan(zzjq.zzatr);
                if (zzjsVarZzan != null && (zzkvVarZza = zzjv.zza(zzjsVarZzan, this.zzaww)) != null) {
                    zziyVar2.zzb(zzkvVarZza);
                }
                long j2 = Long.MAX_VALUE;
                long jMax = -9223372036854775807L;
                int i = 0;
                while (i < zzjtVarPop.zzauk.size()) {
                    zzjt zzjtVar = zzjtVarPop.zzauk.get(i);
                    if (zzjtVar.type != zzjq.zzaru || (zzkjVarZza = zzjv.zza(zzjtVar, zzjtVarPop.zzan(zzjq.zzart), C.TIME_UNSET, (zzil) null, this.zzaww)) == null) {
                        zzkvVar = zzkvVarZza;
                        zziyVar = zziyVar2;
                    } else {
                        zzkl zzklVarZza = zzjv.zza(zzkjVarZza, zzjtVar.zzao(zzjq.zzarv).zzao(zzjq.zzarw).zzao(zzjq.zzarx), zziyVar2);
                        if (zzklVarZza.zzavb != 0) {
                            zzkh zzkhVar = new zzkh(zzkjVarZza, zzklVarZza, this.zzapi.zzb(i, zzkjVarZza.type));
                            zzgq zzgqVarZzo = zzkjVarZza.zzafw.zzo(zzklVarZza.zzawk + 30);
                            if (zzkjVarZza.type == 1) {
                                if (zziyVar2.zzgb()) {
                                    zzgqVarZzo = zzgqVarZzo.zza(zziyVar2.zzafo, zziyVar2.zzafp);
                                }
                                if (zzkvVarZza != null) {
                                    zzgqVarZzo = zzgqVarZzo.zza(zzkvVarZza);
                                }
                            }
                            zzkhVar.zzaxa.zze(zzgqVarZzo);
                            zzkvVar = zzkvVarZza;
                            zziyVar = zziyVar2;
                            jMax = Math.max(jMax, zzkjVarZza.zzagm);
                            arrayList.add(zzkhVar);
                            long j3 = zzklVarZza.zzamm[0];
                            if (j3 < j2) {
                                j2 = j3;
                            }
                        } else {
                            zzkvVar = zzkvVarZza;
                            zziyVar = zziyVar2;
                        }
                    }
                    i++;
                    zziyVar2 = zziyVar;
                    zzkvVarZza = zzkvVar;
                }
                this.zzagm = jMax;
                this.zzawv = (zzkh[]) arrayList.toArray(new zzkh[arrayList.size()]);
                this.zzapi.zzga();
                this.zzapi.zza(this);
                this.zzawp.clear();
                this.zzawq = 2;
            } else if (!this.zzawp.isEmpty()) {
                this.zzawp.peek().zzauk.add(zzjtVarPop);
            }
        }
        if (this.zzawq != 2) {
            zzgk();
        }
    }
}
