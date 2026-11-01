package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjp {
    public int height;
    public int number;
    public int type;
    public int width;
    public zzil zzafe;
    public int zzafi;
    public byte[] zzafj;
    public int zzafl;
    public int zzafm;
    private String zzafs;
    public String zzapl;
    public int zzapm;
    public boolean zzapn;
    public byte[] zzapo;
    public zzje zzapp;
    public byte[] zzapq;
    public int zzapr;
    public int zzaps;
    public int zzapt;
    public boolean zzapu;
    public int zzapv;
    public int zzapw;
    public int zzapx;
    public int zzapy;
    public int zzapz;
    public float zzaqa;
    public float zzaqb;
    public float zzaqc;
    public float zzaqd;
    public float zzaqe;
    public float zzaqf;
    public float zzaqg;
    public float zzaqh;
    public float zzaqi;
    public float zzaqj;
    public int zzaqk;
    public long zzaql;
    public long zzaqm;
    public boolean zzaqn;
    public boolean zzaqo;
    public zzjf zzaqp;
    public int zzaqq;

    private zzjp() {
        this.width = -1;
        this.height = -1;
        this.zzapr = -1;
        this.zzaps = -1;
        this.zzapt = 0;
        this.zzafj = null;
        this.zzafi = -1;
        this.zzapu = false;
        this.zzapv = -1;
        this.zzapw = -1;
        this.zzapx = -1;
        this.zzapy = 1000;
        this.zzapz = 200;
        this.zzaqa = -1.0f;
        this.zzaqb = -1.0f;
        this.zzaqc = -1.0f;
        this.zzaqd = -1.0f;
        this.zzaqe = -1.0f;
        this.zzaqf = -1.0f;
        this.zzaqg = -1.0f;
        this.zzaqh = -1.0f;
        this.zzaqi = -1.0f;
        this.zzaqj = -1.0f;
        this.zzafl = 1;
        this.zzaqk = -1;
        this.zzafm = 8000;
        this.zzaql = 0L;
        this.zzaqm = 0L;
        this.zzaqo = true;
        this.zzafs = "eng";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:102:0x01c8 A[PHI: r1
  0x01c8: PHI (r1v93 int) = (r1v89 int), (r1v96 int) binds: [B:106:0x01e3, B:99:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:148:0x0310  */
    /* JADX WARN: Code duplicated, block: B:149:0x0312  */
    /* JADX WARN: Code duplicated, block: B:152:0x031a  */
    /* JADX WARN: Code duplicated, block: B:153:0x033d  */
    /* JADX WARN: Code duplicated, block: B:155:0x0343  */
    /* JADX WARN: Code duplicated, block: B:157:0x0347  */
    /* JADX WARN: Code duplicated, block: B:159:0x034b  */
    /* JADX WARN: Code duplicated, block: B:162:0x0353  */
    /* JADX WARN: Code duplicated, block: B:166:0x035d  */
    /* JADX WARN: Code duplicated, block: B:169:0x036f  */
    /* JADX WARN: Code duplicated, block: B:172:0x0375  */
    /* JADX WARN: Code duplicated, block: B:174:0x037b  */
    /* JADX WARN: Code duplicated, block: B:195:0x043c  */
    /* JADX WARN: Code duplicated, block: B:197:0x0466  */
    /* JADX WARN: Code duplicated, block: B:199:0x046e  */
    /* JADX WARN: Code duplicated, block: B:200:0x0485  */
    /* JADX WARN: Code duplicated, block: B:202:0x048d  */
    /* JADX WARN: Code duplicated, block: B:89:0x0153  */
    public final void zza(zziw zziwVar, int i) throws zzgt {
        byte b;
        String str;
        List<byte[]> listSingletonList;
        String str2;
        List<byte[]> list;
        String str3;
        String str4;
        int i2;
        String str5;
        int iZzbj;
        int i3;
        int i4;
        int i5;
        zzgq zzgqVarZza;
        int i6;
        float f;
        zzoi zzoiVar;
        int i7;
        int i8;
        int i9;
        int i10 = 3;
        switch (this.zzapl) {
            case "V_MPEG4/ISO/AP":
                b = 5;
                break;
            case "V_MPEG4/ISO/SP":
                b = 3;
                break;
            case "A_MS/ACM":
                b = 22;
                break;
            case "A_TRUEHD":
                b = 17;
                break;
            case "A_VORBIS":
                b = 10;
                break;
            case "A_MPEG/L2":
                b = 13;
                break;
            case "A_MPEG/L3":
                b = 14;
                break;
            case "V_MS/VFW/FOURCC":
                b = 8;
                break;
            case "S_DVBSUB":
                b = 27;
                break;
            case "V_MPEG4/ISO/ASP":
                b = 4;
                break;
            case "V_MPEG4/ISO/AVC":
                b = 6;
                break;
            case "S_VOBSUB":
                b = 25;
                break;
            case "A_DTS/LOSSLESS":
                b = 20;
                break;
            case "A_AAC":
                b = 12;
                break;
            case "A_AC3":
                b = 15;
                break;
            case "A_DTS":
                b = 18;
                break;
            case "V_VP8":
                b = 0;
                break;
            case "V_VP9":
                b = 1;
                break;
            case "S_HDMV/PGS":
                b = 26;
                break;
            case "V_THEORA":
                b = 9;
                break;
            case "A_DTS/EXPRESS":
                b = 19;
                break;
            case "A_PCM/INT/LIT":
                b = 23;
                break;
            case "V_MPEGH/ISO/HEVC":
                b = 7;
                break;
            case "S_TEXT/UTF8":
                b = 24;
                break;
            case "V_MPEG2":
                b = 2;
                break;
            case "A_EAC3":
                b = 16;
                break;
            case "A_FLAC":
                b = 21;
                break;
            case "A_OPUS":
                b = 11;
                break;
            default:
                b = -1;
                break;
        }
        byte[] bArr = null;
        switch (b) {
            case 0:
                str = MimeTypes.VIDEO_VP8;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i11 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i11 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else if (zznw.zzbd(str4)) {
                    if (this.zzapt == 0) {
                        i8 = this.zzapr;
                        if (i8 == -1) {
                            i8 = this.width;
                        }
                        this.zzapr = i8;
                        i9 = this.zzaps;
                        if (i9 == -1) {
                            i9 = this.height;
                        }
                        this.zzaps = i9;
                    }
                    i6 = this.zzapr;
                    if (i6 != -1 || (i7 = this.zzaps) == -1) {
                        f = -1.0f;
                    } else {
                        f = (this.height * i6) / (this.width * i7);
                    }
                    if (this.zzapu) {
                        if (this.zzaqa != -1.0f && this.zzaqb != -1.0f && this.zzaqc != -1.0f && this.zzaqd != -1.0f && this.zzaqe != -1.0f && this.zzaqf != -1.0f && this.zzaqg != -1.0f && this.zzaqh != -1.0f && this.zzaqi != -1.0f && this.zzaqj != -1.0f) {
                            bArr = new byte[25];
                            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                            byteBufferWrap.put((byte) 0);
                            byteBufferWrap.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                            byteBufferWrap.putShort((short) (this.zzaqi + 0.5f));
                            byteBufferWrap.putShort((short) (this.zzaqj + 0.5f));
                            byteBufferWrap.putShort((short) this.zzapy);
                            byteBufferWrap.putShort((short) this.zzapz);
                        }
                        zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                    } else {
                        zzoiVar = null;
                    }
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                    i10 = 2;
                } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                } else if (!MimeTypes.APPLICATION_VOBSUB.equals(str4) || MimeTypes.APPLICATION_PGS.equals(str4) || MimeTypes.APPLICATION_DVBSUBS.equals(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                } else {
                    throw new zzgt("Unexpected MIME type.");
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 1:
                str = MimeTypes.VIDEO_VP9;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i12 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i12 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArr);
                                byteBufferWrap2.put((byte) 0);
                                byteBufferWrap2.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap2.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap2.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap2.putShort((short) this.zzapy);
                                byteBufferWrap2.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 2:
                str = MimeTypes.VIDEO_MPEG2;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i13 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i13 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap3 = ByteBuffer.wrap(bArr);
                                byteBufferWrap3.put((byte) 0);
                                byteBufferWrap3.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap3.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap3.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap3.putShort((short) this.zzapy);
                                byteBufferWrap3.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 3:
            case 4:
            case 5:
                byte[] bArr2 = this.zzapq;
                listSingletonList = bArr2 == null ? null : Collections.singletonList(bArr2);
                str2 = MimeTypes.VIDEO_MP4V;
                str4 = str2;
                i2 = -1;
                i3 = -1;
                int i14 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i14 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap4 = ByteBuffer.wrap(bArr);
                                byteBufferWrap4.put((byte) 0);
                                byteBufferWrap4.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap4.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap4.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap4.putShort((short) this.zzapy);
                                byteBufferWrap4.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 6:
                zzoj zzojVarZzf = zzoj.zzf(new zzoa(this.zzapq));
                list = zzojVarZzf.zzafd;
                this.zzaqq = zzojVarZzf.zzaqq;
                str3 = MimeTypes.VIDEO_H264;
                str4 = str3;
                listSingletonList = list;
                i2 = -1;
                i3 = -1;
                int i15 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i15 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap5 = ByteBuffer.wrap(bArr);
                                byteBufferWrap5.put((byte) 0);
                                byteBufferWrap5.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap5.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap5.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap5.putShort((short) this.zzapy);
                                byteBufferWrap5.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 7:
                zzop zzopVarZzh = zzop.zzh(new zzoa(this.zzapq));
                list = zzopVarZzh.zzafd;
                this.zzaqq = zzopVarZzh.zzaqq;
                str3 = MimeTypes.VIDEO_H265;
                str4 = str3;
                listSingletonList = list;
                i2 = -1;
                i3 = -1;
                int i16 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i16 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap6 = ByteBuffer.wrap(bArr);
                                byteBufferWrap6.put((byte) 0);
                                byteBufferWrap6.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap6.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap6.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap6.putShort((short) this.zzapy);
                                byteBufferWrap6.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 8:
                listSingletonList = zza(new zzoa(this.zzapq));
                if (listSingletonList != null) {
                    str2 = MimeTypes.VIDEO_VC1;
                    str4 = str2;
                } else {
                    Log.w("MatroskaExtractor", "Unsupported FourCC. Setting mimeType to video/x-unknown");
                    str4 = MimeTypes.VIDEO_UNKNOWN;
                }
                i2 = -1;
                i3 = -1;
                int i17 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i17 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap7 = ByteBuffer.wrap(bArr);
                                byteBufferWrap7.put((byte) 0);
                                byteBufferWrap7.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap7.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap7.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap7.putShort((short) this.zzapy);
                                byteBufferWrap7.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 9:
                str4 = MimeTypes.VIDEO_UNKNOWN;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i18 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i18 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap8 = ByteBuffer.wrap(bArr);
                                byteBufferWrap8.put((byte) 0);
                                byteBufferWrap8.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap8.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap8.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap8.putShort((short) this.zzapy);
                                byteBufferWrap8.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 10:
                listSingletonList = zzd(this.zzapq);
                str4 = MimeTypes.AUDIO_VORBIS;
                i2 = 8192;
                i3 = -1;
                int i19 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i19 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap9 = ByteBuffer.wrap(bArr);
                                byteBufferWrap9.put((byte) 0);
                                byteBufferWrap9.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap9.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap9.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap9.putShort((short) this.zzapy);
                                byteBufferWrap9.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 11:
                ArrayList arrayList = new ArrayList(3);
                arrayList.add(this.zzapq);
                arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzaql).array());
                arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzaqm).array());
                listSingletonList = arrayList;
                str4 = MimeTypes.AUDIO_OPUS;
                i2 = 5760;
                i3 = -1;
                int i110 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i110 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap10 = ByteBuffer.wrap(bArr);
                                byteBufferWrap10.put((byte) 0);
                                byteBufferWrap10.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap10.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap10.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap10.putShort((short) this.zzapy);
                                byteBufferWrap10.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 12:
                listSingletonList = Collections.singletonList(this.zzapq);
                str2 = MimeTypes.AUDIO_AAC;
                str4 = str2;
                i2 = -1;
                i3 = -1;
                int i111 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i111 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap11 = ByteBuffer.wrap(bArr);
                                byteBufferWrap11.put((byte) 0);
                                byteBufferWrap11.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap11.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap11.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap11.putShort((short) this.zzapy);
                                byteBufferWrap11.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 13:
                str5 = MimeTypes.AUDIO_MPEG_L2;
                str4 = str5;
                listSingletonList = null;
                i2 = 4096;
                i3 = -1;
                int i112 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i112 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap12 = ByteBuffer.wrap(bArr);
                                byteBufferWrap12.put((byte) 0);
                                byteBufferWrap12.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap12.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap12.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap12.putShort((short) this.zzapy);
                                byteBufferWrap12.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 14:
                str5 = MimeTypes.AUDIO_MPEG;
                str4 = str5;
                listSingletonList = null;
                i2 = 4096;
                i3 = -1;
                int i113 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i113 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap13 = ByteBuffer.wrap(bArr);
                                byteBufferWrap13.put((byte) 0);
                                byteBufferWrap13.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap13.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap13.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap13.putShort((short) this.zzapy);
                                byteBufferWrap13.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 15:
                str = MimeTypes.AUDIO_AC3;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i114 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i114 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap14 = ByteBuffer.wrap(bArr);
                                byteBufferWrap14.put((byte) 0);
                                byteBufferWrap14.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap14.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap14.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap14.putShort((short) this.zzapy);
                                byteBufferWrap14.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 16:
                str = MimeTypes.AUDIO_E_AC3;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i115 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i115 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap15 = ByteBuffer.wrap(bArr);
                                byteBufferWrap15.put((byte) 0);
                                byteBufferWrap15.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap15.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap15.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap15.putShort((short) this.zzapy);
                                byteBufferWrap15.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 17:
                str = MimeTypes.AUDIO_TRUEHD;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i116 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i116 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap16 = ByteBuffer.wrap(bArr);
                                byteBufferWrap16.put((byte) 0);
                                byteBufferWrap16.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap16.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap16.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap16.putShort((short) this.zzapy);
                                byteBufferWrap16.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 18:
            case 19:
                str = MimeTypes.AUDIO_DTS;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i117 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i117 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap17 = ByteBuffer.wrap(bArr);
                                byteBufferWrap17.put((byte) 0);
                                byteBufferWrap17.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap17.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap17.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap17.putShort((short) this.zzapy);
                                byteBufferWrap17.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 20:
                str = MimeTypes.AUDIO_DTS_HD;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i118 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i118 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap18 = ByteBuffer.wrap(bArr);
                                byteBufferWrap18.put((byte) 0);
                                byteBufferWrap18.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap18.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap18.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap18.putShort((short) this.zzapy);
                                byteBufferWrap18.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 21:
                listSingletonList = Collections.singletonList(this.zzapq);
                str2 = "audio/x-flac";
                str4 = str2;
                i2 = -1;
                i3 = -1;
                int i119 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i119 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap19 = ByteBuffer.wrap(bArr);
                                byteBufferWrap19.put((byte) 0);
                                byteBufferWrap19.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap19.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap19.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap19.putShort((short) this.zzapy);
                                byteBufferWrap19.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 22:
                if (zzb(new zzoa(this.zzapq))) {
                    iZzbj = zzoh.zzbj(this.zzaqk);
                    if (iZzbj == 0) {
                        int i20 = this.zzaqk;
                        StringBuilder sb = new StringBuilder(MimeTypes.AUDIO_UNKNOWN.length() + 60);
                        sb.append("Unsupported PCM bit depth: ");
                        sb.append(i20);
                        sb.append(". Setting mimeType to ");
                        sb.append(MimeTypes.AUDIO_UNKNOWN);
                        Log.w("MatroskaExtractor", sb.toString());
                    } else {
                        i3 = iZzbj;
                        str4 = MimeTypes.AUDIO_RAW;
                        listSingletonList = null;
                        i2 = -1;
                    }
                    int i1110 = (this.zzaqo ? 1 : 0) | 0;
                    if (this.zzaqn) {
                        i4 = 2;
                    } else {
                        i4 = 0;
                    }
                    i5 = i1110 | i4;
                    if (zznw.zzbc(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                        i10 = 1;
                    } else {
                        if (zznw.zzbd(str4)) {
                            if (this.zzapt == 0) {
                                i8 = this.zzapr;
                                if (i8 == -1) {
                                    i8 = this.width;
                                }
                                this.zzapr = i8;
                                i9 = this.zzaps;
                                if (i9 == -1) {
                                    i9 = this.height;
                                }
                                this.zzaps = i9;
                            }
                            i6 = this.zzapr;
                            if (i6 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            if (this.zzapu) {
                                if (this.zzaqa != -1.0f) {
                                    bArr = new byte[25];
                                    ByteBuffer byteBufferWrap110 = ByteBuffer.wrap(bArr);
                                    byteBufferWrap110.put((byte) 0);
                                    byteBufferWrap110.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                    byteBufferWrap110.putShort((short) (this.zzaqi + 0.5f));
                                    byteBufferWrap110.putShort((short) (this.zzaqj + 0.5f));
                                    byteBufferWrap110.putShort((short) this.zzapy);
                                    byteBufferWrap110.putShort((short) this.zzapz);
                                }
                                zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                            } else {
                                zzoiVar = null;
                            }
                            zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                            i10 = 2;
                        } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                            zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                        } else {
                            if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                            }
                            zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                        }
                        break;
                    }
                    this.zzaqp = zziwVar.zzb(this.number, i10);
                    this.zzaqp.zze(zzgqVarZza);
                    return;
                }
                Log.w("MatroskaExtractor", MimeTypes.AUDIO_UNKNOWN.length() != 0 ? "Non-PCM MS/ACM is unsupported. Setting mimeType to ".concat(MimeTypes.AUDIO_UNKNOWN) : new String("Non-PCM MS/ACM is unsupported. Setting mimeType to "));
                str4 = MimeTypes.AUDIO_UNKNOWN;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i1111 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1111 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap111 = ByteBuffer.wrap(bArr);
                                byteBufferWrap111.put((byte) 0);
                                byteBufferWrap111.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap111.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap111.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap111.putShort((short) this.zzapy);
                                byteBufferWrap111.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 23:
                iZzbj = zzoh.zzbj(this.zzaqk);
                if (iZzbj == 0) {
                    int i21 = this.zzaqk;
                    StringBuilder sb2 = new StringBuilder(MimeTypes.AUDIO_UNKNOWN.length() + 60);
                    sb2.append("Unsupported PCM bit depth: ");
                    sb2.append(i21);
                    sb2.append(". Setting mimeType to ");
                    sb2.append(MimeTypes.AUDIO_UNKNOWN);
                    Log.w("MatroskaExtractor", sb2.toString());
                    str4 = MimeTypes.AUDIO_UNKNOWN;
                    listSingletonList = null;
                    i2 = -1;
                    i3 = -1;
                } else {
                    i3 = iZzbj;
                    str4 = MimeTypes.AUDIO_RAW;
                    listSingletonList = null;
                    i2 = -1;
                }
                int i1112 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1112 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap112 = ByteBuffer.wrap(bArr);
                                byteBufferWrap112.put((byte) 0);
                                byteBufferWrap112.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap112.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap112.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap112.putShort((short) this.zzapy);
                                byteBufferWrap112.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 24:
                str = MimeTypes.APPLICATION_SUBRIP;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i1113 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1113 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap113 = ByteBuffer.wrap(bArr);
                                byteBufferWrap113.put((byte) 0);
                                byteBufferWrap113.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap113.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap113.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap113.putShort((short) this.zzapy);
                                byteBufferWrap113.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 25:
                listSingletonList = Collections.singletonList(this.zzapq);
                str2 = MimeTypes.APPLICATION_VOBSUB;
                str4 = str2;
                i2 = -1;
                i3 = -1;
                int i1114 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1114 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap114 = ByteBuffer.wrap(bArr);
                                byteBufferWrap114.put((byte) 0);
                                byteBufferWrap114.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap114.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap114.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap114.putShort((short) this.zzapy);
                                byteBufferWrap114.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 26:
                str = MimeTypes.APPLICATION_PGS;
                str4 = str;
                listSingletonList = null;
                i2 = -1;
                i3 = -1;
                int i1115 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1115 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap115 = ByteBuffer.wrap(bArr);
                                byteBufferWrap115.put((byte) 0);
                                byteBufferWrap115.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap115.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap115.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap115.putShort((short) this.zzapy);
                                byteBufferWrap115.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            case 27:
                byte[] bArr3 = this.zzapq;
                listSingletonList = Collections.singletonList(new byte[]{bArr3[0], bArr3[1], bArr3[2], bArr3[3]});
                str2 = MimeTypes.APPLICATION_DVBSUBS;
                str4 = str2;
                i2 = -1;
                i3 = -1;
                int i1116 = (this.zzaqo ? 1 : 0) | 0;
                if (this.zzaqn) {
                    i4 = 2;
                } else {
                    i4 = 0;
                }
                i5 = i1116 | i4;
                if (zznw.zzbc(str4)) {
                    zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.zzafl, this.zzafm, i3, listSingletonList, this.zzafe, i5, this.zzafs);
                    i10 = 1;
                } else {
                    if (zznw.zzbd(str4)) {
                        if (this.zzapt == 0) {
                            i8 = this.zzapr;
                            if (i8 == -1) {
                                i8 = this.width;
                            }
                            this.zzapr = i8;
                            i9 = this.zzaps;
                            if (i9 == -1) {
                                i9 = this.height;
                            }
                            this.zzaps = i9;
                        }
                        i6 = this.zzapr;
                        if (i6 != -1) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.zzapu) {
                            if (this.zzaqa != -1.0f) {
                                bArr = new byte[25];
                                ByteBuffer byteBufferWrap116 = ByteBuffer.wrap(bArr);
                                byteBufferWrap116.put((byte) 0);
                                byteBufferWrap116.putShort((short) ((this.zzaqa * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqb * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqc * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqd * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqe * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqf * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqg * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) ((this.zzaqh * 50000.0f) + 0.5f));
                                byteBufferWrap116.putShort((short) (this.zzaqi + 0.5f));
                                byteBufferWrap116.putShort((short) (this.zzaqj + 0.5f));
                                byteBufferWrap116.putShort((short) this.zzapy);
                                byteBufferWrap116.putShort((short) this.zzapz);
                            }
                            zzoiVar = new zzoi(this.zzapv, this.zzapx, this.zzapw, bArr);
                        } else {
                            zzoiVar = null;
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, null, -1, i2, this.width, this.height, -1.0f, listSingletonList, -1, f, this.zzafj, this.zzafi, zzoiVar, this.zzafe);
                        i10 = 2;
                    } else if (MimeTypes.APPLICATION_SUBRIP.equals(str4)) {
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, i5, this.zzafs, this.zzafe);
                    } else {
                        if (!MimeTypes.APPLICATION_VOBSUB.equals(str4)) {
                        }
                        zzgqVarZza = zzgq.zza(Integer.toString(i), str4, (String) null, -1, listSingletonList, this.zzafs, this.zzafe);
                    }
                    break;
                }
                this.zzaqp = zziwVar.zzb(this.number, i10);
                this.zzaqp.zze(zzgqVarZza);
                return;
            default:
                throw new zzgt("Unrecognized codec identifier.");
        }
    }

    private static List<byte[]> zza(zzoa zzoaVar) throws zzgt {
        try {
            zzoaVar.zzbe(16);
            if (zzoaVar.zzih() != 826496599) {
                return null;
            }
            byte[] bArr = zzoaVar.data;
            for (int position = zzoaVar.getPosition() + 20; position < bArr.length - 4; position++) {
                if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                    return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                }
            }
            throw new zzgt("Failed to find FourCC VC1 initialization data");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzgt("Error parsing FourCC VC1 codec private");
        }
    }

    private static List<byte[]> zzd(byte[] bArr) throws zzgt {
        try {
            if (bArr[0] != 2) {
                throw new zzgt("Error parsing vorbis codec private");
            }
            int i = 1;
            int i2 = 0;
            while (bArr[i] == -1) {
                i2 += 255;
                i++;
            }
            int i3 = i + 1;
            int i4 = i2 + bArr[i];
            int i5 = 0;
            while (bArr[i3] == -1) {
                i5 += 255;
                i3++;
            }
            int i6 = i3 + 1;
            int i7 = i5 + bArr[i3];
            if (bArr[i6] != 1) {
                throw new zzgt("Error parsing vorbis codec private");
            }
            byte[] bArr2 = new byte[i4];
            System.arraycopy(bArr, i6, bArr2, 0, i4);
            int i8 = i6 + i4;
            if (bArr[i8] != 3) {
                throw new zzgt("Error parsing vorbis codec private");
            }
            int i9 = i8 + i7;
            if (bArr[i9] != 5) {
                throw new zzgt("Error parsing vorbis codec private");
            }
            byte[] bArr3 = new byte[bArr.length - i9];
            System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(bArr2);
            arrayList.add(bArr3);
            return arrayList;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzgt("Error parsing vorbis codec private");
        }
    }

    private static boolean zzb(zzoa zzoaVar) throws zzgt {
        try {
            int iZzif = zzoaVar.zzif();
            if (iZzif == 1) {
                return true;
            }
            if (iZzif == 65534) {
                zzoaVar.zzbd(24);
                if (zzoaVar.readLong() == zzjk.zzanl.getMostSignificantBits() && zzoaVar.readLong() == zzjk.zzanl.getLeastSignificantBits()) {
                    return true;
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzgt("Error parsing MS/ACM codec private");
        }
    }

    /* synthetic */ zzjp(zzjn zzjnVar) {
        this();
    }
}
