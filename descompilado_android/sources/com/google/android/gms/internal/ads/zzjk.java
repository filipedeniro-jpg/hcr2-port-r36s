package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.FirebaseError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzjk implements zziu {
    private static final zziz zzani = new zzjn();
    private static final byte[] zzanj = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] zzank = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID zzanl = new UUID(72057594037932032L, -9223371306706625679L);
    private long zzagm;
    private final zzjr zzanc;
    private final zzji zzanm;
    private final SparseArray<zzjp> zzann;
    private final boolean zzano;
    private final zzoa zzanp;
    private final zzoa zzanq;
    private final zzoa zzanr;
    private final zzoa zzans;
    private final zzoa zzant;
    private final zzoa zzanu;
    private final zzoa zzanv;
    private final zzoa zzanw;
    private final zzoa zzanx;
    private ByteBuffer zzany;
    private long zzanz;
    private long zzaoa;
    private long zzaob;
    private long zzaoc;
    private zzjp zzaod;
    private boolean zzaoe;
    private int zzaof;
    private long zzaog;
    private boolean zzaoh;
    private long zzaoi;
    private long zzaoj;
    private long zzaok;
    private zznu zzaol;
    private zznu zzaom;
    private boolean zzaon;
    private int zzaoo;
    private long zzaop;
    private long zzaoq;
    private int zzaor;
    private int zzaos;
    private int[] zzaot;
    private int zzaou;
    private int zzaov;
    private int zzaow;
    private int zzaox;
    private boolean zzaoy;
    private boolean zzaoz;
    private boolean zzapa;
    private boolean zzapb;
    private byte zzapc;
    private int zzapd;
    private int zzape;
    private int zzapf;
    private boolean zzapg;
    private boolean zzaph;
    private zziw zzapi;

    public zzjk() {
        this(0);
    }

    static int zzag(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
            case FirebaseError.ERROR_WEAK_PASSWORD /* 17026 */:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    static boolean zzah(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final void release() {
    }

    private zzjk(int i) {
        this(new zzjh(), 0);
    }

    private zzjk(zzji zzjiVar, int i) {
        this.zzaoa = -1L;
        this.zzaob = C.TIME_UNSET;
        this.zzaoc = C.TIME_UNSET;
        this.zzagm = C.TIME_UNSET;
        this.zzaoi = -1L;
        this.zzaoj = -1L;
        this.zzaok = C.TIME_UNSET;
        this.zzanm = zzjiVar;
        this.zzanm.zza(new zzjm(this, null));
        this.zzano = true;
        this.zzanc = new zzjr();
        this.zzann = new SparseArray<>();
        this.zzanr = new zzoa(4);
        this.zzans = new zzoa(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzant = new zzoa(4);
        this.zzanp = new zzoa(zznz.zzbfx);
        this.zzanq = new zzoa(4);
        this.zzanu = new zzoa();
        this.zzanv = new zzoa();
        this.zzanw = new zzoa(8);
        this.zzanx = new zzoa();
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final boolean zza(zzix zzixVar) throws InterruptedException, IOException {
        return new zzjo().zza(zzixVar);
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final void zza(zziw zziwVar) {
        this.zzapi = zziwVar;
    }

    @Override // com.google.android.gms.internal.ads.zziu
    public final void zzc(long j, long j2) {
        this.zzaok = C.TIME_UNSET;
        this.zzaoo = 0;
        this.zzanm.reset();
        this.zzanc.reset();
        zzgd();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0005 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zziu
    public final int zza(zzix zzixVar, zzja zzjaVar) throws InterruptedException, IOException {
        boolean z;
        this.zzapg = false;
        boolean zZzb = true;
        while (zZzb && !this.zzapg) {
            zZzb = this.zzanm.zzb(zzixVar);
            if (zZzb) {
                long position = zzixVar.getPosition();
                if (this.zzaoh) {
                    this.zzaoj = position;
                    zzjaVar.zzams = this.zzaoi;
                    this.zzaoh = false;
                } else {
                    if (this.zzaoe) {
                        long j = this.zzaoj;
                        if (j != -1) {
                            zzjaVar.zzams = j;
                            this.zzaoj = -1L;
                        }
                        if (z) {
                            return 1;
                        }
                    }
                    z = false;
                    if (z) {
                        return 1;
                    }
                }
                z = true;
                if (z) {
                    return 1;
                }
            }
        }
        return zZzb ? 0 : -1;
    }

    final void zzd(int i, long j, long j2) throws zzgt {
        if (i == 160) {
            this.zzaph = false;
            return;
        }
        if (i == 174) {
            this.zzaod = new zzjp(null);
            return;
        }
        if (i == 187) {
            this.zzaon = false;
            return;
        }
        if (i == 19899) {
            this.zzaof = -1;
            this.zzaog = -1L;
            return;
        }
        if (i == 20533) {
            this.zzaod.zzapn = true;
            return;
        }
        if (i == 21968) {
            this.zzaod.zzapu = true;
            return;
        }
        if (i != 25152) {
            if (i == 408125543) {
                long j3 = this.zzaoa;
                if (j3 != -1 && j3 != j) {
                    throw new zzgt("Multiple Segment elements not supported");
                }
                this.zzaoa = j;
                this.zzanz = j2;
                return;
            }
            if (i == 475249515) {
                this.zzaol = new zznu();
                this.zzaom = new zznu();
            } else if (i == 524531317 && !this.zzaoe) {
                if (this.zzano && this.zzaoi != -1) {
                    this.zzaoh = true;
                } else {
                    this.zzapi.zza(new zzjc(this.zzagm));
                    this.zzaoe = true;
                }
            }
        }
    }

    final void zzai(int i) throws zzgt {
        zzjd zzjcVar;
        zznu zznuVar;
        zznu zznuVar2;
        int i2;
        int i3 = 0;
        if (i == 160) {
            if (this.zzaoo != 2) {
                return;
            }
            if (!this.zzaph) {
                this.zzaow |= 1;
            }
            zza(this.zzann.get(this.zzaou), this.zzaop);
            this.zzaoo = 0;
            return;
        }
        if (i == 174) {
            String str = this.zzaod.zzapl;
            if ((("V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) ? 1 : 0) != 0) {
                zzjp zzjpVar = this.zzaod;
                zzjpVar.zza(this.zzapi, zzjpVar.number);
                this.zzann.put(this.zzaod.number, this.zzaod);
            }
            this.zzaod = null;
            return;
        }
        if (i == 19899) {
            int i4 = this.zzaof;
            if (i4 != -1) {
                long j = this.zzaog;
                if (j != -1) {
                    if (i4 == 475249515) {
                        this.zzaoi = j;
                        return;
                    }
                    return;
                }
            }
            throw new zzgt("Mandatory element SeekID or SeekPosition not found");
        }
        if (i == 25152) {
            if (this.zzaod.zzapn) {
                if (this.zzaod.zzapp == null) {
                    throw new zzgt("Encrypted Track found but ContentEncKeyID was not found");
                }
                this.zzaod.zzafe = new zzil(new zzil.zza(zzgc.zzacf, MimeTypes.VIDEO_WEBM, this.zzaod.zzapp.zzamz));
                return;
            }
            return;
        }
        if (i == 28032) {
            if (this.zzaod.zzapn && this.zzaod.zzapo != null) {
                throw new zzgt("Combining encryption and compression is not supported");
            }
            return;
        }
        if (i == 357149030) {
            if (this.zzaob == C.TIME_UNSET) {
                this.zzaob = 1000000L;
            }
            long j2 = this.zzaoc;
            if (j2 != C.TIME_UNSET) {
                this.zzagm = zzdu(j2);
                return;
            }
            return;
        }
        if (i != 374648427) {
            if (i == 475249515 && !this.zzaoe) {
                zziw zziwVar = this.zzapi;
                if (this.zzaoa == -1 || this.zzagm == C.TIME_UNSET || (zznuVar = this.zzaol) == null || zznuVar.size() == 0 || (zznuVar2 = this.zzaom) == null || zznuVar2.size() != this.zzaol.size()) {
                    this.zzaol = null;
                    this.zzaom = null;
                    zzjcVar = new zzjc(this.zzagm);
                } else {
                    int size = this.zzaol.size();
                    int[] iArr = new int[size];
                    long[] jArr = new long[size];
                    long[] jArr2 = new long[size];
                    long[] jArr3 = new long[size];
                    for (int i5 = 0; i5 < size; i5++) {
                        jArr3[i5] = this.zzaol.get(i5);
                        jArr[i5] = this.zzaoa + this.zzaom.get(i5);
                    }
                    while (true) {
                        i2 = size - 1;
                        if (i3 >= i2) {
                            break;
                        }
                        int i6 = i3 + 1;
                        iArr[i3] = (int) (jArr[i6] - jArr[i3]);
                        jArr2[i3] = jArr3[i6] - jArr3[i3];
                        i3 = i6;
                    }
                    iArr[i2] = (int) ((this.zzaoa + this.zzanz) - jArr[i2]);
                    jArr2[i2] = this.zzagm - jArr3[i2];
                    this.zzaol = null;
                    this.zzaom = null;
                    zzjcVar = new zzis(iArr, jArr, jArr2, jArr3);
                }
                zziwVar.zza(zzjcVar);
                this.zzaoe = true;
                return;
            }
            return;
        }
        if (this.zzann.size() == 0) {
            throw new zzgt("No valid tracks were found");
        }
        this.zzapi.zzga();
    }

    final void zzc(int i, long j) throws zzgt {
        if (i == 20529) {
            if (j == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder(55);
            sb.append("ContentEncodingOrder ");
            sb.append(j);
            sb.append(" not supported");
            throw new zzgt(sb.toString());
        }
        if (i == 20530) {
            if (j == 1) {
                return;
            }
            StringBuilder sb2 = new StringBuilder(55);
            sb2.append("ContentEncodingScope ");
            sb2.append(j);
            sb2.append(" not supported");
            throw new zzgt(sb2.toString());
        }
        switch (i) {
            case 131:
                this.zzaod.type = (int) j;
                return;
            case 136:
                this.zzaod.zzaqn = j == 1;
                return;
            case 155:
                this.zzaoq = zzdu(j);
                return;
            case 159:
                this.zzaod.zzafl = (int) j;
                return;
            case 176:
                this.zzaod.width = (int) j;
                return;
            case 179:
                this.zzaol.add(zzdu(j));
                return;
            case 186:
                this.zzaod.height = (int) j;
                return;
            case 215:
                this.zzaod.number = (int) j;
                return;
            case 231:
                this.zzaok = zzdu(j);
                return;
            case 241:
                if (this.zzaon) {
                    return;
                }
                this.zzaom.add(j);
                this.zzaon = true;
                return;
            case 251:
                this.zzaph = true;
                return;
            case 16980:
                if (j == 3) {
                    return;
                }
                StringBuilder sb3 = new StringBuilder(50);
                sb3.append("ContentCompAlgo ");
                sb3.append(j);
                sb3.append(" not supported");
                throw new zzgt(sb3.toString());
            case 17029:
                if (j < 1 || j > 2) {
                    StringBuilder sb4 = new StringBuilder(53);
                    sb4.append("DocTypeReadVersion ");
                    sb4.append(j);
                    sb4.append(" not supported");
                    throw new zzgt(sb4.toString());
                }
                return;
            case 17143:
                if (j == 1) {
                    return;
                }
                StringBuilder sb5 = new StringBuilder(50);
                sb5.append("EBMLReadVersion ");
                sb5.append(j);
                sb5.append(" not supported");
                throw new zzgt(sb5.toString());
            case 18401:
                if (j == 5) {
                    return;
                }
                StringBuilder sb6 = new StringBuilder(49);
                sb6.append("ContentEncAlgo ");
                sb6.append(j);
                sb6.append(" not supported");
                throw new zzgt(sb6.toString());
            case 18408:
                if (j == 1) {
                    return;
                }
                StringBuilder sb7 = new StringBuilder(56);
                sb7.append("AESSettingsCipherMode ");
                sb7.append(j);
                sb7.append(" not supported");
                throw new zzgt(sb7.toString());
            case 21420:
                this.zzaog = j + this.zzaoa;
                return;
            case 21432:
                int i2 = (int) j;
                if (i2 == 0) {
                    this.zzaod.zzafi = 0;
                    return;
                }
                if (i2 == 1) {
                    this.zzaod.zzafi = 2;
                    return;
                } else if (i2 == 3) {
                    this.zzaod.zzafi = 1;
                    return;
                } else {
                    if (i2 != 15) {
                        return;
                    }
                    this.zzaod.zzafi = 3;
                    return;
                }
            case 21680:
                this.zzaod.zzapr = (int) j;
                return;
            case 21682:
                this.zzaod.zzapt = (int) j;
                return;
            case 21690:
                this.zzaod.zzaps = (int) j;
                return;
            case 21930:
                this.zzaod.zzaqo = j == 1;
                return;
            case 22186:
                this.zzaod.zzaql = j;
                return;
            case 22203:
                this.zzaod.zzaqm = j;
                return;
            case 25188:
                this.zzaod.zzaqk = (int) j;
                return;
            case 2352003:
                this.zzaod.zzapm = (int) j;
                return;
            case 2807729:
                this.zzaob = j;
                return;
            default:
                switch (i) {
                    case 21945:
                        int i3 = (int) j;
                        if (i3 == 1) {
                            this.zzaod.zzapx = 2;
                            return;
                        } else {
                            if (i3 != 2) {
                                return;
                            }
                            this.zzaod.zzapx = 1;
                            return;
                        }
                    case 21946:
                        int i4 = (int) j;
                        if (i4 != 1) {
                            if (i4 == 16) {
                                this.zzaod.zzapw = 6;
                                return;
                            } else if (i4 == 18) {
                                this.zzaod.zzapw = 7;
                                return;
                            } else if (i4 != 6 && i4 != 7) {
                                return;
                            }
                        }
                        this.zzaod.zzapw = 3;
                        return;
                    case 21947:
                        zzjp zzjpVar = this.zzaod;
                        zzjpVar.zzapu = true;
                        int i5 = (int) j;
                        if (i5 == 1) {
                            zzjpVar.zzapv = 1;
                            return;
                        }
                        if (i5 == 9) {
                            zzjpVar.zzapv = 6;
                            return;
                        } else {
                            if (i5 == 4 || i5 == 5 || i5 == 6 || i5 == 7) {
                                this.zzaod.zzapv = 2;
                                return;
                            }
                            return;
                        }
                    case 21948:
                        this.zzaod.zzapy = (int) j;
                        return;
                    case 21949:
                        this.zzaod.zzapz = (int) j;
                        return;
                    default:
                        return;
                }
        }
    }

    final void zza(int i, double d) {
        if (i == 181) {
            this.zzaod.zzafm = (int) d;
            return;
        }
        if (i == 17545) {
            this.zzaoc = (long) d;
            return;
        }
        switch (i) {
            case 21969:
                this.zzaod.zzaqa = (float) d;
                break;
            case 21970:
                this.zzaod.zzaqb = (float) d;
                break;
            case 21971:
                this.zzaod.zzaqc = (float) d;
                break;
            case 21972:
                this.zzaod.zzaqd = (float) d;
                break;
            case 21973:
                this.zzaod.zzaqe = (float) d;
                break;
            case 21974:
                this.zzaod.zzaqf = (float) d;
                break;
            case 21975:
                this.zzaod.zzaqg = (float) d;
                break;
            case 21976:
                this.zzaod.zzaqh = (float) d;
                break;
            case 21977:
                this.zzaod.zzaqi = (float) d;
                break;
            case 21978:
                this.zzaod.zzaqj = (float) d;
                break;
        }
    }

    final void zza(int i, String str) throws zzgt {
        if (i == 134) {
            this.zzaod.zzapl = str;
            return;
        }
        if (i != 17026) {
            if (i != 2274716) {
                return;
            }
            this.zzaod.zzafs = str;
        } else {
            if ("webm".equals(str) || "matroska".equals(str)) {
                return;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new zzgt(sb.toString());
        }
    }

    final void zza(int i, int i2, zzix zzixVar) throws InterruptedException, IOException {
        int i3;
        int i4;
        int[] iArr;
        int i5 = 0;
        int i6 = 1;
        if (i != 161 && i != 163) {
            if (i == 16981) {
                zzjp zzjpVar = this.zzaod;
                zzjpVar.zzapo = new byte[i2];
                zzixVar.readFully(zzjpVar.zzapo, 0, i2);
                return;
            }
            if (i == 18402) {
                byte[] bArr = new byte[i2];
                zzixVar.readFully(bArr, 0, i2);
                this.zzaod.zzapp = new zzje(1, bArr);
                return;
            }
            if (i == 21419) {
                Arrays.fill(this.zzant.data, (byte) 0);
                zzixVar.readFully(this.zzant.data, 4 - i2, i2);
                this.zzant.zzbd(0);
                this.zzaof = (int) this.zzant.zzig();
                return;
            }
            if (i == 25506) {
                zzjp zzjpVar2 = this.zzaod;
                zzjpVar2.zzapq = new byte[i2];
                zzixVar.readFully(zzjpVar2.zzapq, 0, i2);
                return;
            } else if (i == 30322) {
                zzjp zzjpVar3 = this.zzaod;
                zzjpVar3.zzafj = new byte[i2];
                zzixVar.readFully(zzjpVar3.zzafj, 0, i2);
                return;
            } else {
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unexpected id: ");
                sb.append(i);
                throw new zzgt(sb.toString());
            }
        }
        if (this.zzaoo == 0) {
            this.zzaou = (int) this.zzanc.zza(zzixVar, false, true, 8);
            this.zzaov = this.zzanc.zzgf();
            this.zzaoq = C.TIME_UNSET;
            this.zzaoo = 1;
            this.zzanr.reset();
        }
        zzjp zzjpVar4 = this.zzann.get(this.zzaou);
        if (zzjpVar4 == null) {
            zzixVar.zzab(i2 - this.zzaov);
            this.zzaoo = 0;
            return;
        }
        if (this.zzaoo == 1) {
            zzb(zzixVar, 3);
            int i7 = (this.zzanr.data[2] & 6) >> 1;
            byte b = 255;
            if (i7 == 0) {
                this.zzaos = 1;
                this.zzaot = zza(this.zzaot, 1);
                this.zzaot[0] = (i2 - this.zzaov) - 3;
            } else {
                if (i != 163) {
                    throw new zzgt("Lacing only supported in SimpleBlocks.");
                }
                zzb(zzixVar, 4);
                this.zzaos = (this.zzanr.data[3] & 255) + 1;
                this.zzaot = zza(this.zzaot, this.zzaos);
                if (i7 == 2) {
                    int i8 = (i2 - this.zzaov) - 4;
                    int i9 = this.zzaos;
                    Arrays.fill(this.zzaot, 0, i9, i8 / i9);
                } else if (i7 == 1) {
                    int i10 = 0;
                    int i11 = 4;
                    int i12 = 0;
                    while (true) {
                        i3 = this.zzaos;
                        if (i10 >= i3 - 1) {
                            break;
                        }
                        this.zzaot[i10] = 0;
                        do {
                            i11++;
                            zzb(zzixVar, i11);
                            i4 = this.zzanr.data[i11 - 1] & 255;
                            iArr = this.zzaot;
                            iArr[i10] = iArr[i10] + i4;
                        } while (i4 == 255);
                        i12 += iArr[i10];
                        i10++;
                    }
                    this.zzaot[i3 - 1] = ((i2 - this.zzaov) - i11) - i12;
                } else {
                    if (i7 != 3) {
                        StringBuilder sb2 = new StringBuilder(36);
                        sb2.append("Unexpected lacing value: ");
                        sb2.append(i7);
                        throw new zzgt(sb2.toString());
                    }
                    int i13 = 0;
                    int i14 = 4;
                    int i15 = 0;
                    while (true) {
                        int i16 = this.zzaos;
                        if (i13 < i16 - 1) {
                            this.zzaot[i13] = i5;
                            i14++;
                            zzb(zzixVar, i14);
                            int i17 = i14 - 1;
                            if (this.zzanr.data[i17] == 0) {
                                throw new zzgt("No valid varint length mask found");
                            }
                            long j = 0;
                            int i18 = 0;
                            while (i18 < 8) {
                                int i19 = i6 << (7 - i18);
                                if ((this.zzanr.data[i17] & i19) != 0) {
                                    i14 += i18;
                                    zzb(zzixVar, i14);
                                    long j2 = this.zzanr.data[i17] & b & (i19 ^ (-1));
                                    int i20 = i17 + 1;
                                    while (true) {
                                        j = j2;
                                        if (i20 >= i14) {
                                            break;
                                        }
                                        j2 = (j << 8) | ((long) (this.zzanr.data[i20] & b));
                                        i20++;
                                        b = 255;
                                    }
                                    if (i13 <= 0) {
                                        break;
                                    }
                                    j -= (1 << ((i18 * 7) + 6)) - 1;
                                    break;
                                }
                                i18++;
                                i6 = 1;
                                b = 255;
                            }
                            long j3 = j;
                            if (j3 < -2147483648L || j3 > 2147483647L) {
                                throw new zzgt("EBML lacing sample size out of range.");
                            }
                            int i21 = (int) j3;
                            int[] iArr2 = this.zzaot;
                            if (i13 != 0) {
                                i21 += iArr2[i13 - 1];
                            }
                            iArr2[i13] = i21;
                            i15 += this.zzaot[i13];
                            i13++;
                            i5 = 0;
                            i6 = 1;
                            b = 255;
                        } else {
                            this.zzaot[i16 - 1] = ((i2 - this.zzaov) - i14) - i15;
                            break;
                        }
                    }
                }
            }
            this.zzaop = this.zzaok + zzdu((this.zzanr.data[0] << 8) | (this.zzanr.data[1] & 255));
            this.zzaow = ((zzjpVar4.type == 2 || (i == 163 && (this.zzanr.data[2] & 128) == 128)) ? 1 : 0) | ((this.zzanr.data[2] & 8) == 8 ? Integer.MIN_VALUE : 0);
            this.zzaoo = 2;
            this.zzaor = 0;
        }
        if (i != 163) {
            zza(zzixVar, zzjpVar4, this.zzaot[0]);
            return;
        }
        while (true) {
            int i22 = this.zzaor;
            if (i22 < this.zzaos) {
                zza(zzixVar, zzjpVar4, this.zzaot[i22]);
                zza(zzjpVar4, this.zzaop + ((long) ((this.zzaor * zzjpVar4.zzapm) / 1000)));
                this.zzaor++;
            } else {
                this.zzaoo = 0;
                return;
            }
        }
    }

    private final void zza(zzjp zzjpVar, long j) {
        byte[] bArrZzbh;
        if ("S_TEXT/UTF8".equals(zzjpVar.zzapl)) {
            byte[] bArr = this.zzanv.data;
            long j2 = this.zzaoq;
            if (j2 == C.TIME_UNSET) {
                bArrZzbh = zzank;
            } else {
                int i = (int) (j2 / 3600000000L);
                long j3 = j2 - (((long) i) * 3600000000L);
                int i2 = (int) (j3 / 60000000);
                long j4 = j3 - ((long) (60000000 * i2));
                int i3 = (int) (j4 / 1000000);
                bArrZzbh = zzoh.zzbh(String.format(Locale.US, "%02d:%02d:%02d,%03d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - ((long) (1000000 * i3))) / 1000))));
            }
            System.arraycopy(bArrZzbh, 0, bArr, 19, 12);
            zzjf zzjfVar = zzjpVar.zzaqp;
            zzoa zzoaVar = this.zzanv;
            zzjfVar.zza(zzoaVar, zzoaVar.limit());
            this.zzapf += this.zzanv.limit();
        }
        zzjpVar.zzaqp.zza(j, this.zzaow, this.zzapf, 0, zzjpVar.zzapp);
        this.zzapg = true;
        zzgd();
    }

    private final void zzgd() {
        this.zzaox = 0;
        this.zzapf = 0;
        this.zzape = 0;
        this.zzaoy = false;
        this.zzaoz = false;
        this.zzapb = false;
        this.zzapd = 0;
        this.zzapc = (byte) 0;
        this.zzapa = false;
        this.zzanu.reset();
    }

    private final void zzb(zzix zzixVar, int i) throws InterruptedException, IOException {
        if (this.zzanr.limit() >= i) {
            return;
        }
        if (this.zzanr.capacity() < i) {
            zzoa zzoaVar = this.zzanr;
            zzoaVar.zzb(Arrays.copyOf(zzoaVar.data, Math.max(this.zzanr.data.length << 1, i)), this.zzanr.limit());
        }
        zzixVar.readFully(this.zzanr.data, this.zzanr.limit(), i - this.zzanr.limit());
        this.zzanr.zzbc(i);
    }

    private final void zza(zzix zzixVar, zzjp zzjpVar, int i) throws InterruptedException, IOException {
        int i2;
        if ("S_TEXT/UTF8".equals(zzjpVar.zzapl)) {
            int length = zzanj.length + i;
            if (this.zzanv.capacity() < length) {
                this.zzanv.data = Arrays.copyOf(zzanj, length + i);
            }
            zzixVar.readFully(this.zzanv.data, zzanj.length, i);
            this.zzanv.zzbd(0);
            this.zzanv.zzbc(length);
            return;
        }
        zzjf zzjfVar = zzjpVar.zzaqp;
        if (!this.zzaoy) {
            if (zzjpVar.zzapn) {
                this.zzaow &= -1073741825;
                if (!this.zzaoz) {
                    zzixVar.readFully(this.zzanr.data, 0, 1);
                    this.zzaox++;
                    if ((this.zzanr.data[0] & 128) == 128) {
                        throw new zzgt("Extension bit is set in signal byte");
                    }
                    this.zzapc = this.zzanr.data[0];
                    this.zzaoz = true;
                }
                byte b = this.zzapc;
                if ((b & 1) == 1) {
                    boolean z = (b & 2) == 2;
                    this.zzaow |= 1073741824;
                    if (!this.zzapa) {
                        zzixVar.readFully(this.zzanw.data, 0, 8);
                        this.zzaox += 8;
                        this.zzapa = true;
                        this.zzanr.data[0] = (byte) ((z ? 128 : 0) | 8);
                        this.zzanr.zzbd(0);
                        zzjfVar.zza(this.zzanr, 1);
                        this.zzapf++;
                        this.zzanw.zzbd(0);
                        zzjfVar.zza(this.zzanw, 8);
                        this.zzapf += 8;
                    }
                    if (z) {
                        if (!this.zzapb) {
                            zzixVar.readFully(this.zzanr.data, 0, 1);
                            this.zzaox++;
                            this.zzanr.zzbd(0);
                            this.zzapd = this.zzanr.readUnsignedByte();
                            this.zzapb = true;
                        }
                        int i3 = this.zzapd << 2;
                        this.zzanr.reset(i3);
                        zzixVar.readFully(this.zzanr.data, 0, i3);
                        this.zzaox += i3;
                        short s = (short) ((this.zzapd / 2) + 1);
                        int i4 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.zzany;
                        if (byteBuffer == null || byteBuffer.capacity() < i4) {
                            this.zzany = ByteBuffer.allocate(i4);
                        }
                        this.zzany.position(0);
                        this.zzany.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            i2 = this.zzapd;
                            if (i5 >= i2) {
                                break;
                            }
                            int iZzij = this.zzanr.zzij();
                            if (i5 % 2 == 0) {
                                this.zzany.putShort((short) (iZzij - i6));
                            } else {
                                this.zzany.putInt(iZzij - i6);
                            }
                            i5++;
                            i6 = iZzij;
                        }
                        int i7 = (i - this.zzaox) - i6;
                        if (i2 % 2 == 1) {
                            this.zzany.putInt(i7);
                        } else {
                            this.zzany.putShort((short) i7);
                            this.zzany.putInt(0);
                        }
                        this.zzanx.zzb(this.zzany.array(), i4);
                        zzjfVar.zza(this.zzanx, i4);
                        this.zzapf += i4;
                    }
                }
            } else if (zzjpVar.zzapo != null) {
                this.zzanu.zzb(zzjpVar.zzapo, zzjpVar.zzapo.length);
            }
            this.zzaoy = true;
        }
        int iLimit = i + this.zzanu.limit();
        if (!"V_MPEG4/ISO/AVC".equals(zzjpVar.zzapl) && !"V_MPEGH/ISO/HEVC".equals(zzjpVar.zzapl)) {
            while (true) {
                int i8 = this.zzaox;
                if (i8 >= iLimit) {
                    break;
                } else {
                    zza(zzixVar, zzjfVar, iLimit - i8);
                }
            }
        } else {
            byte[] bArr = this.zzanq.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i9 = zzjpVar.zzaqq;
            int i10 = 4 - zzjpVar.zzaqq;
            while (this.zzaox < iLimit) {
                int i11 = this.zzape;
                if (i11 == 0) {
                    int iMin = Math.min(i9, this.zzanu.zzie());
                    zzixVar.readFully(bArr, i10 + iMin, i9 - iMin);
                    if (iMin > 0) {
                        this.zzanu.zze(bArr, i10, iMin);
                    }
                    this.zzaox += i9;
                    this.zzanq.zzbd(0);
                    this.zzape = this.zzanq.zzij();
                    this.zzanp.zzbd(0);
                    zzjfVar.zza(this.zzanp, 4);
                    this.zzapf += 4;
                } else {
                    this.zzape = i11 - zza(zzixVar, zzjfVar, i11);
                }
            }
        }
        if ("A_VORBIS".equals(zzjpVar.zzapl)) {
            this.zzans.zzbd(0);
            zzjfVar.zza(this.zzans, 4);
            this.zzapf += 4;
        }
    }

    private final int zza(zzix zzixVar, zzjf zzjfVar, int i) throws InterruptedException, IOException {
        int iZza;
        int iZzie = this.zzanu.zzie();
        if (iZzie > 0) {
            iZza = Math.min(i, iZzie);
            zzjfVar.zza(this.zzanu, iZza);
        } else {
            iZza = zzjfVar.zza(zzixVar, i, false);
        }
        this.zzaox += iZza;
        this.zzapf += iZza;
        return iZza;
    }

    private final long zzdu(long j) throws zzgt {
        long j2 = this.zzaob;
        if (j2 == C.TIME_UNSET) {
            throw new zzgt("Can't scale timecode prior to timecodeScale being set.");
        }
        return zzoh.zza(j, j2, 1000L);
    }

    private static int[] zza(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return iArr.length >= i ? iArr : new int[Math.max(iArr.length << 1, i)];
    }
}
