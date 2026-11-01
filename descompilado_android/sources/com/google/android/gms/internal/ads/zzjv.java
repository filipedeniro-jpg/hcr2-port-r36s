package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.appsflyer.BuildConfig;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.games.Notifications;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjv {
    private static final int zzaus = zzoh.zzbi("vide");
    private static final int zzaut = zzoh.zzbi("soun");
    private static final int zzauu = zzoh.zzbi(MimeTypes.BASE_TYPE_TEXT);
    private static final int zzauv = zzoh.zzbi("sbtl");
    private static final int zzauw = zzoh.zzbi("subt");
    private static final int zzaux = zzoh.zzbi("clcp");
    private static final int zzauy = zzoh.zzbi(C.CENC_TYPE_cenc);
    private static final int zzats = zzoh.zzbi("meta");

    /* JADX WARN: Code duplicated, block: B:273:0x044b  */
    /* JADX WARN: Code duplicated, block: B:275:0x0461  */
    /* JADX WARN: Code duplicated, block: B:276:0x047a  */
    /* JADX WARN: Code duplicated, block: B:290:0x04c9  */
    /* JADX WARN: Code duplicated, block: B:292:0x04f6  */
    /* JADX WARN: Code duplicated, block: B:296:0x050c  */
    /* JADX WARN: Code duplicated, block: B:298:0x0519  */
    /* JADX WARN: Code duplicated, block: B:301:0x0522  */
    /* JADX WARN: Code duplicated, block: B:302:0x0524  */
    /* JADX WARN: Code duplicated, block: B:305:0x0530 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:306:0x0532  */
    /* JADX WARN: Code duplicated, block: B:307:0x0534  */
    /* JADX WARN: Code duplicated, block: B:310:0x0549  */
    /* JADX WARN: Code duplicated, block: B:312:0x0550  */
    /* JADX WARN: Code duplicated, block: B:314:0x0554 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:315:0x0556  */
    /* JADX WARN: Code duplicated, block: B:316:0x0558  */
    /* JADX WARN: Code duplicated, block: B:320:0x0574  */
    /* JADX WARN: Code duplicated, block: B:322:0x0578 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:323:0x057a  */
    /* JADX WARN: Code duplicated, block: B:324:0x057c  */
    /* JADX WARN: Code duplicated, block: B:327:0x0584  */
    /* JADX WARN: Code duplicated, block: B:328:0x0587  */
    /* JADX WARN: Code duplicated, block: B:329:0x058a  */
    /* JADX WARN: Code duplicated, block: B:331:0x058e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:332:0x0590  */
    /* JADX WARN: Code duplicated, block: B:333:0x0592  */
    /* JADX WARN: Code duplicated, block: B:336:0x059b  */
    /* JADX WARN: Code duplicated, block: B:338:0x059f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:339:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:340:0x05a3  */
    /* JADX WARN: Code duplicated, block: B:342:0x05bc  */
    /* JADX WARN: Code duplicated, block: B:344:0x05c0  */
    /* JADX WARN: Code duplicated, block: B:345:0x05d5  */
    /* JADX WARN: Code duplicated, block: B:347:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:350:0x05df  */
    /* JADX WARN: Code duplicated, block: B:353:0x05f8 A[LOOP:5: B:348:0x05db->B:353:0x05f8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:356:0x0607  */
    /* JADX WARN: Code duplicated, block: B:358:0x060d  */
    /* JADX WARN: Code duplicated, block: B:360:0x0617  */
    /* JADX WARN: Code duplicated, block: B:362:0x061d  */
    /* JADX WARN: Code duplicated, block: B:364:0x0620  */
    /* JADX WARN: Code duplicated, block: B:366:0x0623 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:368:0x0626  */
    /* JADX WARN: Code duplicated, block: B:369:0x062b  */
    /* JADX WARN: Code duplicated, block: B:370:0x0630  */
    /* JADX WARN: Code duplicated, block: B:371:0x0635  */
    /* JADX WARN: Code duplicated, block: B:372:0x063a  */
    /* JADX WARN: Code duplicated, block: B:377:0x0646  */
    /* JADX WARN: Code duplicated, block: B:400:0x06e5  */
    /* JADX WARN: Code duplicated, block: B:403:0x06ee A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:404:0x06ef  */
    /* JADX WARN: Code duplicated, block: B:412:0x065e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:416:0x047b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:429:0x05fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:430:0x05f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x00cb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x00cd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:63:0x00d8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00da A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:68:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:72:0x0101  */
    /* JADX WARN: Code duplicated, block: B:76:0x010f  */
    /* JADX WARN: Code duplicated, block: B:79:0x013b  */
    /* JADX WARN: Code duplicated, block: B:80:0x013e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0149  */
    /* JADX WARN: Code duplicated, block: B:84:0x014b  */
    /* JADX WARN: Code duplicated, block: B:87:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:89:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:90:0x01b4  */
    public static zzkj zza(zzjt zzjtVar, zzjs zzjsVar, long j, zzil zzilVar, boolean z) throws zzgt {
        int i;
        boolean z2;
        long jZzig;
        int i2;
        int i3;
        zzkb zzkbVar;
        long j2;
        zzoa zzoaVar;
        int i4;
        long jZzig2;
        int iZzaj;
        int i5;
        int i6;
        Pair pairCreate;
        zzoa zzoaVar2;
        int i7;
        int i8;
        int i9;
        zzjw zzjwVar;
        int i10;
        Pair pair;
        zzkb zzkbVar2;
        long j3;
        int i11;
        zzjt zzjtVarZzao;
        zzkj zzkjVar;
        Pair pairCreate2;
        zzjs zzjsVarZzan;
        int position;
        int i12;
        boolean z3;
        int iZza;
        Pair pair2;
        zzkb zzkbVar3;
        int i13;
        int i14;
        int unsignedShort;
        int unsignedShort2;
        int position2;
        boolean z4;
        String str;
        List<byte[]> listSingletonList;
        float fZzij;
        byte[] bArr;
        int i15;
        int position3;
        int i16;
        boolean z5;
        int i17;
        boolean z6;
        int unsignedByte;
        int unsignedByte2;
        int i18;
        boolean z7;
        byte[] bArrCopyOfRange;
        int i19;
        boolean z8;
        boolean z9;
        String str2;
        boolean z10;
        boolean z11;
        List<byte[]> list;
        String str3;
        boolean z12;
        zzoj zzojVarZzf;
        int unsignedShort3;
        int unsignedShort4;
        int iRound;
        String str4;
        int position4;
        int i20;
        int i21;
        String str5;
        String str6;
        String str7;
        long j4;
        List listSingletonList2;
        zzjt zzjtVarZzao2 = zzjtVar.zzao(zzjq.zzarv);
        zzoa zzoaVar3 = zzjtVarZzao2.zzan(zzjq.zzasj).zzauh;
        zzoaVar3.zzbd(16);
        int i22 = zzoaVar3.readInt();
        if (i22 == zzaut) {
            i = 1;
        } else if (i22 == zzaus) {
            i = 2;
        } else if (i22 == zzauu || i22 == zzauv || i22 == zzauw || i22 == zzaux) {
            i = 3;
        } else {
            i = i22 == zzats ? 4 : -1;
        }
        if (i == -1) {
            return null;
        }
        zzoa zzoaVar4 = zzjtVar.zzan(zzjq.zzasf).zzauh;
        zzoaVar4.zzbd(8);
        int iZzaj2 = zzjq.zzaj(zzoaVar4.readInt());
        zzoaVar4.zzbe(iZzaj2 == 0 ? 8 : 16);
        int i23 = zzoaVar4.readInt();
        zzoaVar4.zzbe(4);
        int position5 = zzoaVar4.getPosition();
        int i24 = iZzaj2 == 0 ? 4 : 8;
        int i25 = 0;
        while (true) {
            if (i25 >= i24) {
                z2 = true;
                break;
            }
            if (zzoaVar4.data[position5 + i25] != -1) {
                z2 = false;
                break;
            }
            i25++;
        }
        long jZza = C.TIME_UNSET;
        if (!z2) {
            jZzig = iZzaj2 == 0 ? zzoaVar4.zzig() : zzoaVar4.zzik();
            if (jZzig == 0) {
            }
            zzoaVar4.zzbe(16);
            i2 = zzoaVar4.readInt();
            int i26 = zzoaVar4.readInt();
            zzoaVar4.zzbe(4);
            int i27 = zzoaVar4.readInt();
            int i28 = zzoaVar4.readInt();
            if (i2 != 0 && i26 == 65536 && i27 == -65536 && i28 == 0) {
                i3 = 90;
            } else if (i2 != 0 && i26 == -65536 && i27 == 65536 && i28 == 0) {
                i3 = 270;
            } else if (i2 != -65536 && i26 == 0 && i27 == 0 && i28 == -65536) {
                i3 = 180;
            } else {
                i3 = 0;
            }
            zzkbVar = new zzkb(i23, jZzig, i3);
            j2 = zzkbVar.zzcu;
            zzoaVar = zzjsVar.zzauh;
            zzoaVar.zzbd(8);
            if (zzjq.zzaj(zzoaVar.readInt()) == 0) {
                i4 = 8;
            } else {
                i4 = 16;
            }
            zzoaVar.zzbe(i4);
            jZzig2 = zzoaVar.zzig();
            if (j2 != C.TIME_UNSET) {
                jZza = zzoh.zza(j2, 1000000L, jZzig2);
            }
            zzjt zzjtVarZzao3 = zzjtVarZzao2.zzao(zzjq.zzarw).zzao(zzjq.zzarx);
            zzoa zzoaVar5 = zzjtVarZzao2.zzan(zzjq.zzasi).zzauh;
            zzoaVar5.zzbd(8);
            iZzaj = zzjq.zzaj(zzoaVar5.readInt());
            if (iZzaj == 0) {
                i5 = 8;
            } else {
                i5 = 16;
            }
            zzoaVar5.zzbe(i5);
            long jZzig3 = zzoaVar5.zzig();
            if (iZzaj == 0) {
                i6 = 4;
            } else {
                i6 = 8;
            }
            zzoaVar5.zzbe(i6);
            int unsignedShort5 = zzoaVar5.readUnsignedShort();
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) (((unsignedShort5 >> 10) & 31) + 96));
            sb.append((char) (((unsignedShort5 >> 5) & 31) + 96));
            sb.append((char) ((unsignedShort5 & 31) + 96));
            pairCreate = Pair.create(Long.valueOf(jZzig3), sb.toString());
            zzoaVar2 = zzjtVarZzao3.zzan(zzjq.zzask).zzauh;
            i7 = zzkbVar.id;
            i8 = zzkbVar.zzafg;
            String str8 = (String) pairCreate.second;
            zzoaVar2.zzbd(12);
            i9 = zzoaVar2.readInt();
            zzjwVar = new zzjw(i9);
            i10 = 0;
            while (i10 < i9) {
                position = zzoaVar2.getPosition();
                i12 = zzoaVar2.readInt();
                if (i12 > 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                zznt.checkArgument(z3, "childAtomSize should be positive");
                iZza = zzoaVar2.readInt();
                long j5 = jZzig2;
                if (iZza != zzjq.zzaqs || iZza == zzjq.zzaqt || iZza == zzjq.zzasq || iZza == zzjq.zzatc || iZza == zzjq.zzaqu || iZza == zzjq.zzaqv || iZza == zzjq.zzaqw || iZza == zzjq.zzaub || iZza == zzjq.zzauc) {
                    pair2 = pairCreate;
                    zzkbVar3 = zzkbVar;
                    i13 = i8;
                    i14 = i;
                    zzoaVar2.zzbd(position + 8 + 8);
                    zzoaVar2.zzbe(16);
                    unsignedShort = zzoaVar2.readUnsignedShort();
                    unsignedShort2 = zzoaVar2.readUnsignedShort();
                    zzoaVar2.zzbe(50);
                    position2 = zzoaVar2.getPosition();
                    if (iZza == zzjq.zzasq) {
                        iZza = zza(zzoaVar2, position, i12, zzjwVar, i10);
                        zzoaVar2.zzbd(position2);
                    }
                    z4 = false;
                    str = null;
                    listSingletonList = null;
                    fZzij = 1.0f;
                    bArr = null;
                    i15 = -1;
                    while (position2 - position < i12) {
                        zzoaVar2.zzbd(position2);
                        position3 = zzoaVar2.getPosition();
                        i16 = zzoaVar2.readInt();
                        if (i16 != 0 && zzoaVar2.getPosition() - position == i12) {
                            break;
                        }
                        if (i16 > 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        zznt.checkArgument(z5, "childAtomSize should be positive");
                        i17 = zzoaVar2.readInt();
                        if (i17 == zzjq.zzary) {
                            if (str == null) {
                                z12 = true;
                            } else {
                                z12 = false;
                            }
                            zznt.checkState(z12);
                            zzoaVar2.zzbd(position3 + 8);
                            zzojVarZzf = zzoj.zzf(zzoaVar2);
                            list = zzojVarZzf.zzafd;
                            zzjwVar.zzaqq = zzojVarZzf.zzaqq;
                            if (!z4) {
                                fZzij = zzojVarZzf.zzbgc;
                            }
                            str3 = MimeTypes.VIDEO_H264;
                        } else {
                            if (i17 == zzjq.zzarz) {
                                if (str == null) {
                                    z11 = true;
                                } else {
                                    z11 = false;
                                }
                                zznt.checkState(z11);
                                zzoaVar2.zzbd(position3 + 8);
                                zzop zzopVarZzh = zzop.zzh(zzoaVar2);
                                list = zzopVarZzh.zzafd;
                                zzjwVar.zzaqq = zzopVarZzh.zzaqq;
                                str3 = MimeTypes.VIDEO_H265;
                            } else {
                                if (i17 == zzjq.zzaud) {
                                    if (str == null) {
                                        z10 = true;
                                    } else {
                                        z10 = false;
                                    }
                                    zznt.checkState(z10);
                                    if (iZza == zzjq.zzaub) {
                                        str2 = MimeTypes.VIDEO_VP8;
                                    } else {
                                        str2 = MimeTypes.VIDEO_VP9;
                                    }
                                } else if (i17 == zzjq.zzaqx) {
                                    if (str == null) {
                                        z9 = true;
                                    } else {
                                        z9 = false;
                                    }
                                    zznt.checkState(z9);
                                    str2 = MimeTypes.VIDEO_H263;
                                } else if (i17 == zzjq.zzasa) {
                                    if (str == null) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    zznt.checkState(z8);
                                    Pair<String, byte[]> pairZzb = zzb(zzoaVar2, position3);
                                    String str9 = (String) pairZzb.first;
                                    listSingletonList = Collections.singletonList((byte[]) pairZzb.second);
                                    str = str9;
                                } else if (i17 == zzjq.zzasz) {
                                    zzoaVar2.zzbd(position3 + 8);
                                    fZzij = zzoaVar2.zzij() / zzoaVar2.zzij();
                                    z4 = true;
                                } else if (i17 == zzjq.zzatz) {
                                    i18 = position3 + 8;
                                    while (true) {
                                        if (i18 - position3 >= i16) {
                                            z7 = z4;
                                            bArrCopyOfRange = null;
                                            break;
                                        }
                                        zzoaVar2.zzbd(i18);
                                        i19 = zzoaVar2.readInt();
                                        z7 = z4;
                                        if (zzoaVar2.readInt() == zzjq.zzaua) {
                                            bArrCopyOfRange = Arrays.copyOfRange(zzoaVar2.data, i18, i19 + i18);
                                            break;
                                        }
                                        i18 += i19;
                                        z4 = z7;
                                    }
                                    bArr = bArrCopyOfRange;
                                    z4 = z7;
                                } else {
                                    z6 = z4;
                                    if (i17 == zzjq.zzaty) {
                                        unsignedByte = zzoaVar2.readUnsignedByte();
                                        zzoaVar2.zzbe(3);
                                        if (unsignedByte == 0) {
                                            unsignedByte2 = zzoaVar2.readUnsignedByte();
                                            if (unsignedByte2 == 0) {
                                                z4 = z6;
                                                i15 = 0;
                                            } else if (unsignedByte2 == 1) {
                                                z4 = z6;
                                                i15 = 1;
                                            } else if (unsignedByte2 == 2) {
                                                z4 = z6;
                                                i15 = 2;
                                            } else if (unsignedByte2 == 3) {
                                                z4 = z6;
                                                i15 = 3;
                                            }
                                        }
                                    }
                                    z4 = z6;
                                }
                                str = str2;
                            }
                            position2 += i16;
                        }
                        str = str3;
                        listSingletonList = list;
                        position2 += i16;
                    }
                    if (str != null) {
                        zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i13, fZzij, bArr, i15, null, null);
                    }
                } else {
                    if (iZza == zzjq.zzaqz || iZza == zzjq.zzasr || iZza == zzjq.zzare || iZza == zzjq.zzarg || iZza == zzjq.zzari || iZza == zzjq.zzarl || iZza == zzjq.zzarj || iZza == zzjq.zzark || iZza == zzjq.zzatp || iZza == zzjq.zzatq || iZza == zzjq.zzarc || iZza == zzjq.zzard || iZza == zzjq.zzara || iZza == zzjq.zzauf) {
                        zzoaVar2.zzbd(position + 8 + 8);
                        if (z) {
                            unsignedShort3 = zzoaVar2.readUnsignedShort();
                            zzoaVar2.zzbe(6);
                        } else {
                            zzoaVar2.zzbe(8);
                            unsignedShort3 = 0;
                        }
                        if (unsignedShort3 == 0 || unsignedShort3 == 1) {
                            unsignedShort4 = zzoaVar2.readUnsignedShort();
                            zzoaVar2.zzbe(6);
                            int iZzii = zzoaVar2.zzii();
                            if (unsignedShort3 == 1) {
                                zzoaVar2.zzbe(16);
                            }
                            iRound = iZzii;
                        } else if (unsignedShort3 == 2) {
                            zzoaVar2.zzbe(16);
                            iRound = (int) Math.round(Double.longBitsToDouble(zzoaVar2.readLong()));
                            unsignedShort4 = zzoaVar2.zzij();
                            zzoaVar2.zzbe(20);
                        }
                        int position6 = zzoaVar2.getPosition();
                        int i29 = iRound;
                        if (iZza == zzjq.zzasr) {
                            iZza = zza(zzoaVar2, position, i12, zzjwVar, i10);
                            zzoaVar2.zzbd(position6);
                        }
                        if (iZza == zzjq.zzare) {
                            str4 = MimeTypes.AUDIO_AC3;
                        } else if (iZza == zzjq.zzarg) {
                            str4 = MimeTypes.AUDIO_E_AC3;
                        } else if (iZza == zzjq.zzari) {
                            str4 = MimeTypes.AUDIO_DTS;
                        } else if (iZza == zzjq.zzarj || iZza == zzjq.zzark) {
                            str4 = MimeTypes.AUDIO_DTS_HD;
                        } else if (iZza == zzjq.zzarl) {
                            str4 = MimeTypes.AUDIO_DTS_EXPRESS;
                        } else if (iZza == zzjq.zzatp) {
                            str4 = MimeTypes.AUDIO_AMR_NB;
                        } else if (iZza == zzjq.zzatq) {
                            str4 = MimeTypes.AUDIO_AMR_WB;
                        } else if (iZza == zzjq.zzarc || iZza == zzjq.zzard) {
                            str4 = MimeTypes.AUDIO_RAW;
                        } else if (iZza == zzjq.zzara) {
                            str4 = MimeTypes.AUDIO_MPEG;
                        } else {
                            str4 = iZza == zzjq.zzauf ? MimeTypes.AUDIO_ALAC : null;
                        }
                        int i30 = position6;
                        i14 = i;
                        int iIntValue = unsignedShort4;
                        int iIntValue2 = i29;
                        byte[] bArr2 = null;
                        while (i30 - position < i12) {
                            zzoaVar2.zzbd(i30);
                            int i31 = zzoaVar2.readInt();
                            zznt.checkArgument(i31 > 0, "childAtomSize should be positive");
                            int i32 = zzoaVar2.readInt();
                            zzkb zzkbVar4 = zzkbVar;
                            if (i32 == zzjq.zzasa || (z && i32 == zzjq.zzarb)) {
                                if (i32 == zzjq.zzasa) {
                                    position4 = i30;
                                    i20 = i8;
                                } else {
                                    position4 = zzoaVar2.getPosition();
                                    while (true) {
                                        if (position4 - i30 >= i31) {
                                            i20 = i8;
                                            i21 = -1;
                                            position4 = -1;
                                            break;
                                        }
                                        zzoaVar2.zzbd(position4);
                                        int i33 = zzoaVar2.readInt();
                                        zznt.checkArgument(i33 > 0, "childAtomSize should be positive");
                                        i20 = i8;
                                        if (zzoaVar2.readInt() != zzjq.zzasa) {
                                            position4 += i33;
                                            i8 = i20;
                                        }
                                    }
                                    if (position4 != i21) {
                                        Pair<String, byte[]> pairZzb2 = zzb(zzoaVar2, position4);
                                        str5 = (String) pairZzb2.first;
                                        bArr2 = (byte[]) pairZzb2.second;
                                        if (MimeTypes.AUDIO_AAC.equals(str5)) {
                                            Pair<Integer, Integer> pairZze = zzns.zze(bArr2);
                                            str4 = str5;
                                            iIntValue2 = ((Integer) pairZze.first).intValue();
                                            iIntValue = ((Integer) pairZze.second).intValue();
                                        } else {
                                            str4 = str5;
                                        }
                                    }
                                }
                                i21 = -1;
                                if (position4 != i21) {
                                    Pair<String, byte[]> pairZzb3 = zzb(zzoaVar2, position4);
                                    str5 = (String) pairZzb3.first;
                                    bArr2 = (byte[]) pairZzb3.second;
                                    if (MimeTypes.AUDIO_AAC.equals(str5)) {
                                        Pair<Integer, Integer> pairZze2 = zzns.zze(bArr2);
                                        str4 = str5;
                                        iIntValue2 = ((Integer) pairZze2.first).intValue();
                                        iIntValue = ((Integer) pairZze2.second).intValue();
                                    } else {
                                        str4 = str5;
                                    }
                                }
                            } else {
                                if (i32 == zzjq.zzarf) {
                                    zzoaVar2.zzbd(i30 + 8);
                                    zzjwVar.zzafw = zzhe.zza(zzoaVar2, Integer.toString(i7), str8, null);
                                } else if (i32 == zzjq.zzarh) {
                                    zzoaVar2.zzbd(i30 + 8);
                                    zzjwVar.zzafw = zzhe.zzb(zzoaVar2, Integer.toString(i7), str8, null);
                                } else if (i32 == zzjq.zzarm) {
                                    zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str4, null, -1, -1, iIntValue, iIntValue2, null, null, 0, str8);
                                } else {
                                    if (i32 == zzjq.zzauf) {
                                        byte[] bArr3 = new byte[i31];
                                        zzoaVar2.zzbd(i30);
                                        zzoaVar2.zze(bArr3, 0, i31);
                                        bArr2 = bArr3;
                                    }
                                    i20 = i8;
                                }
                                i20 = i8;
                            }
                            i30 += i31;
                            pairCreate = pairCreate;
                            zzkbVar = zzkbVar4;
                            i8 = i20;
                        }
                        pair2 = pairCreate;
                        zzkbVar3 = zzkbVar;
                        i13 = i8;
                        if (zzjwVar.zzafw == null && str4 != null) {
                            zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str4, null, -1, -1, iIntValue, iIntValue2, MimeTypes.AUDIO_RAW.equals(str4) ? 2 : -1, bArr2 == null ? null : Collections.singletonList(bArr2), null, 0, str8);
                        }
                    } else if (iZza == zzjq.zzata || iZza == zzjq.zzatl || iZza == zzjq.zzatm || iZza == zzjq.zzatn || iZza == zzjq.zzato) {
                        zzoaVar2.zzbd(position + 8 + 8);
                        if (iZza == zzjq.zzata) {
                            str6 = MimeTypes.APPLICATION_TTML;
                        } else {
                            if (iZza == zzjq.zzatl) {
                                int i34 = (i12 - 8) - 8;
                                byte[] bArr4 = new byte[i34];
                                zzoaVar2.zze(bArr4, 0, i34);
                                listSingletonList2 = Collections.singletonList(bArr4);
                                j4 = Long.MAX_VALUE;
                                str7 = MimeTypes.APPLICATION_TX3G;
                            } else if (iZza == zzjq.zzatm) {
                                str6 = MimeTypes.APPLICATION_MP4VTT;
                            } else if (iZza == zzjq.zzatn) {
                                str7 = MimeTypes.APPLICATION_TTML;
                                j4 = 0;
                                listSingletonList2 = null;
                            } else {
                                if (iZza != zzjq.zzato) {
                                    throw new IllegalStateException();
                                }
                                zzjwVar.zzava = 1;
                                str6 = MimeTypes.APPLICATION_MP4CEA608;
                            }
                            zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str7, null, -1, 0, str8, -1, null, j4, listSingletonList2);
                        }
                        j4 = Long.MAX_VALUE;
                        listSingletonList2 = null;
                        str7 = str6;
                        zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str7, null, -1, 0, str8, -1, null, j4, listSingletonList2);
                    } else if (iZza == zzjq.zzaue) {
                        zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
                    }
                    pair2 = pairCreate;
                    zzkbVar3 = zzkbVar;
                    i13 = i8;
                    i14 = i;
                }
                zzoaVar2.zzbd(position + i12);
                i10++;
                i9 = i9;
                jZzig2 = j5;
                i = i14;
                pairCreate = pair2;
                zzkbVar = zzkbVar3;
                i8 = i13;
            }
            pair = pairCreate;
            zzkbVar2 = zzkbVar;
            j3 = jZzig2;
            i11 = i;
            zzjtVarZzao = zzjtVar.zzao(zzjq.zzasg);
            if (zzjtVarZzao != null || (zzjsVarZzan = zzjtVarZzao.zzan(zzjq.zzash)) == null) {
                zzkjVar = null;
                pairCreate2 = Pair.create(null, null);
            } else {
                zzoa zzoaVar6 = zzjsVarZzan.zzauh;
                zzoaVar6.zzbd(8);
                int iZzaj3 = zzjq.zzaj(zzoaVar6.readInt());
                int iZzij = zzoaVar6.zzij();
                long[] jArr = new long[iZzij];
                long[] jArr2 = new long[iZzij];
                for (int i35 = 0; i35 < iZzij; i35++) {
                    jArr[i35] = iZzaj3 == 1 ? zzoaVar6.zzik() : zzoaVar6.zzig();
                    jArr2[i35] = iZzaj3 == 1 ? zzoaVar6.readLong() : zzoaVar6.readInt();
                    if (zzoaVar6.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    zzoaVar6.zzbe(2);
                }
                pairCreate2 = Pair.create(jArr, jArr2);
                zzkjVar = null;
            }
            if (zzjwVar.zzafw == null) {
                return zzkjVar;
            }
            return new zzkj(zzkbVar2.id, i11, ((Long) pair.first).longValue(), j3, jZza, zzjwVar.zzafw, zzjwVar.zzava, zzjwVar.zzauz, zzjwVar.zzaqq, (long[]) pairCreate2.first, (long[]) pairCreate2.second);
        }
        zzoaVar4.zzbe(i24);
        jZzig = -9223372036854775807L;
        zzoaVar4.zzbe(16);
        i2 = zzoaVar4.readInt();
        int i210 = zzoaVar4.readInt();
        zzoaVar4.zzbe(4);
        int i211 = zzoaVar4.readInt();
        int i212 = zzoaVar4.readInt();
        if (i2 != 0) {
            if (i2 != 0) {
                if (i2 != -65536) {
                    i3 = 0;
                } else {
                    i3 = 0;
                }
            } else if (i2 != -65536) {
                i3 = 0;
            } else {
                i3 = 0;
            }
        } else if (i2 != 0) {
            if (i2 != -65536) {
                i3 = 0;
            } else {
                i3 = 0;
            }
        } else if (i2 != -65536) {
            i3 = 0;
        } else {
            i3 = 0;
        }
        zzkbVar = new zzkb(i23, jZzig, i3);
        j2 = zzkbVar.zzcu;
        zzoaVar = zzjsVar.zzauh;
        zzoaVar.zzbd(8);
        if (zzjq.zzaj(zzoaVar.readInt()) == 0) {
            i4 = 8;
        } else {
            i4 = 16;
        }
        zzoaVar.zzbe(i4);
        jZzig2 = zzoaVar.zzig();
        if (j2 != C.TIME_UNSET) {
            jZza = zzoh.zza(j2, 1000000L, jZzig2);
        }
        zzjt zzjtVarZzao4 = zzjtVarZzao2.zzao(zzjq.zzarw).zzao(zzjq.zzarx);
        zzoa zzoaVar7 = zzjtVarZzao2.zzan(zzjq.zzasi).zzauh;
        zzoaVar7.zzbd(8);
        iZzaj = zzjq.zzaj(zzoaVar7.readInt());
        if (iZzaj == 0) {
            i5 = 8;
        } else {
            i5 = 16;
        }
        zzoaVar7.zzbe(i5);
        long jZzig4 = zzoaVar7.zzig();
        if (iZzaj == 0) {
            i6 = 4;
        } else {
            i6 = 8;
        }
        zzoaVar7.zzbe(i6);
        int unsignedShort6 = zzoaVar7.readUnsignedShort();
        StringBuilder sb2 = new StringBuilder(3);
        sb2.append((char) (((unsignedShort6 >> 10) & 31) + 96));
        sb2.append((char) (((unsignedShort6 >> 5) & 31) + 96));
        sb2.append((char) ((unsignedShort6 & 31) + 96));
        pairCreate = Pair.create(Long.valueOf(jZzig4), sb2.toString());
        zzoaVar2 = zzjtVarZzao4.zzan(zzjq.zzask).zzauh;
        i7 = zzkbVar.id;
        i8 = zzkbVar.zzafg;
        String str10 = (String) pairCreate.second;
        zzoaVar2.zzbd(12);
        i9 = zzoaVar2.readInt();
        zzjwVar = new zzjw(i9);
        i10 = 0;
        while (i10 < i9) {
            position = zzoaVar2.getPosition();
            i12 = zzoaVar2.readInt();
            if (i12 > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            zznt.checkArgument(z3, "childAtomSize should be positive");
            iZza = zzoaVar2.readInt();
            long j6 = jZzig2;
            if (iZza != zzjq.zzaqs) {
                pair2 = pairCreate;
                zzkbVar3 = zzkbVar;
                i13 = i8;
                i14 = i;
                zzoaVar2.zzbd(position + 8 + 8);
                zzoaVar2.zzbe(16);
                unsignedShort = zzoaVar2.readUnsignedShort();
                unsignedShort2 = zzoaVar2.readUnsignedShort();
                zzoaVar2.zzbe(50);
                position2 = zzoaVar2.getPosition();
                if (iZza == zzjq.zzasq) {
                    iZza = zza(zzoaVar2, position, i12, zzjwVar, i10);
                    zzoaVar2.zzbd(position2);
                }
                z4 = false;
                str = null;
                listSingletonList = null;
                fZzij = 1.0f;
                bArr = null;
                i15 = -1;
                while (position2 - position < i12) {
                    zzoaVar2.zzbd(position2);
                    position3 = zzoaVar2.getPosition();
                    i16 = zzoaVar2.readInt();
                    if (i16 != 0) {
                    }
                    if (i16 > 0) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zznt.checkArgument(z5, "childAtomSize should be positive");
                    i17 = zzoaVar2.readInt();
                    if (i17 == zzjq.zzary) {
                        if (str == null) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        zznt.checkState(z12);
                        zzoaVar2.zzbd(position3 + 8);
                        zzojVarZzf = zzoj.zzf(zzoaVar2);
                        list = zzojVarZzf.zzafd;
                        zzjwVar.zzaqq = zzojVarZzf.zzaqq;
                        if (!z4) {
                            fZzij = zzojVarZzf.zzbgc;
                        }
                        str3 = MimeTypes.VIDEO_H264;
                    } else {
                        if (i17 == zzjq.zzarz) {
                            if (str == null) {
                                z11 = true;
                            } else {
                                z11 = false;
                            }
                            zznt.checkState(z11);
                            zzoaVar2.zzbd(position3 + 8);
                            zzop zzopVarZzh2 = zzop.zzh(zzoaVar2);
                            list = zzopVarZzh2.zzafd;
                            zzjwVar.zzaqq = zzopVarZzh2.zzaqq;
                            str3 = MimeTypes.VIDEO_H265;
                        } else {
                            if (i17 == zzjq.zzaud) {
                                if (str == null) {
                                    z10 = true;
                                } else {
                                    z10 = false;
                                }
                                zznt.checkState(z10);
                                if (iZza == zzjq.zzaub) {
                                    str2 = MimeTypes.VIDEO_VP8;
                                } else {
                                    str2 = MimeTypes.VIDEO_VP9;
                                }
                            } else if (i17 == zzjq.zzaqx) {
                                if (str == null) {
                                    z9 = true;
                                } else {
                                    z9 = false;
                                }
                                zznt.checkState(z9);
                                str2 = MimeTypes.VIDEO_H263;
                            } else if (i17 == zzjq.zzasa) {
                                if (str == null) {
                                    z8 = true;
                                } else {
                                    z8 = false;
                                }
                                zznt.checkState(z8);
                                Pair<String, byte[]> pairZzb4 = zzb(zzoaVar2, position3);
                                String str11 = (String) pairZzb4.first;
                                listSingletonList = Collections.singletonList((byte[]) pairZzb4.second);
                                str = str11;
                            } else if (i17 == zzjq.zzasz) {
                                zzoaVar2.zzbd(position3 + 8);
                                fZzij = zzoaVar2.zzij() / zzoaVar2.zzij();
                                z4 = true;
                            } else if (i17 == zzjq.zzatz) {
                                i18 = position3 + 8;
                                while (true) {
                                    if (i18 - position3 >= i16) {
                                        z7 = z4;
                                        bArrCopyOfRange = null;
                                        break;
                                    }
                                    zzoaVar2.zzbd(i18);
                                    i19 = zzoaVar2.readInt();
                                    z7 = z4;
                                    if (zzoaVar2.readInt() == zzjq.zzaua) {
                                        bArrCopyOfRange = Arrays.copyOfRange(zzoaVar2.data, i18, i19 + i18);
                                        break;
                                    }
                                    i18 += i19;
                                    z4 = z7;
                                }
                                bArr = bArrCopyOfRange;
                                z4 = z7;
                            } else {
                                z6 = z4;
                                if (i17 == zzjq.zzaty) {
                                    unsignedByte = zzoaVar2.readUnsignedByte();
                                    zzoaVar2.zzbe(3);
                                    if (unsignedByte == 0) {
                                        unsignedByte2 = zzoaVar2.readUnsignedByte();
                                        if (unsignedByte2 == 0) {
                                            z4 = z6;
                                            i15 = 0;
                                        } else if (unsignedByte2 == 1) {
                                            z4 = z6;
                                            i15 = 1;
                                        } else if (unsignedByte2 == 2) {
                                            z4 = z6;
                                            i15 = 2;
                                        } else if (unsignedByte2 == 3) {
                                            z4 = z6;
                                            i15 = 3;
                                        }
                                    }
                                }
                                z4 = z6;
                            }
                            str = str2;
                        }
                        position2 += i16;
                    }
                    str = str3;
                    listSingletonList = list;
                    position2 += i16;
                }
                if (str != null) {
                    zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i13, fZzij, bArr, i15, null, null);
                }
            } else {
                pair2 = pairCreate;
                zzkbVar3 = zzkbVar;
                i13 = i8;
                i14 = i;
                zzoaVar2.zzbd(position + 8 + 8);
                zzoaVar2.zzbe(16);
                unsignedShort = zzoaVar2.readUnsignedShort();
                unsignedShort2 = zzoaVar2.readUnsignedShort();
                zzoaVar2.zzbe(50);
                position2 = zzoaVar2.getPosition();
                if (iZza == zzjq.zzasq) {
                    iZza = zza(zzoaVar2, position, i12, zzjwVar, i10);
                    zzoaVar2.zzbd(position2);
                }
                z4 = false;
                str = null;
                listSingletonList = null;
                fZzij = 1.0f;
                bArr = null;
                i15 = -1;
                while (position2 - position < i12) {
                    zzoaVar2.zzbd(position2);
                    position3 = zzoaVar2.getPosition();
                    i16 = zzoaVar2.readInt();
                    if (i16 != 0) {
                    }
                    if (i16 > 0) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    zznt.checkArgument(z5, "childAtomSize should be positive");
                    i17 = zzoaVar2.readInt();
                    if (i17 == zzjq.zzary) {
                        if (str == null) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        zznt.checkState(z12);
                        zzoaVar2.zzbd(position3 + 8);
                        zzojVarZzf = zzoj.zzf(zzoaVar2);
                        list = zzojVarZzf.zzafd;
                        zzjwVar.zzaqq = zzojVarZzf.zzaqq;
                        if (!z4) {
                            fZzij = zzojVarZzf.zzbgc;
                        }
                        str3 = MimeTypes.VIDEO_H264;
                    } else {
                        if (i17 == zzjq.zzarz) {
                            if (str == null) {
                                z11 = true;
                            } else {
                                z11 = false;
                            }
                            zznt.checkState(z11);
                            zzoaVar2.zzbd(position3 + 8);
                            zzop zzopVarZzh3 = zzop.zzh(zzoaVar2);
                            list = zzopVarZzh3.zzafd;
                            zzjwVar.zzaqq = zzopVarZzh3.zzaqq;
                            str3 = MimeTypes.VIDEO_H265;
                        } else {
                            if (i17 == zzjq.zzaud) {
                                if (str == null) {
                                    z10 = true;
                                } else {
                                    z10 = false;
                                }
                                zznt.checkState(z10);
                                if (iZza == zzjq.zzaub) {
                                    str2 = MimeTypes.VIDEO_VP8;
                                } else {
                                    str2 = MimeTypes.VIDEO_VP9;
                                }
                            } else if (i17 == zzjq.zzaqx) {
                                if (str == null) {
                                    z9 = true;
                                } else {
                                    z9 = false;
                                }
                                zznt.checkState(z9);
                                str2 = MimeTypes.VIDEO_H263;
                            } else if (i17 == zzjq.zzasa) {
                                if (str == null) {
                                    z8 = true;
                                } else {
                                    z8 = false;
                                }
                                zznt.checkState(z8);
                                Pair<String, byte[]> pairZzb5 = zzb(zzoaVar2, position3);
                                String str12 = (String) pairZzb5.first;
                                listSingletonList = Collections.singletonList((byte[]) pairZzb5.second);
                                str = str12;
                            } else if (i17 == zzjq.zzasz) {
                                zzoaVar2.zzbd(position3 + 8);
                                fZzij = zzoaVar2.zzij() / zzoaVar2.zzij();
                                z4 = true;
                            } else if (i17 == zzjq.zzatz) {
                                i18 = position3 + 8;
                                while (true) {
                                    if (i18 - position3 >= i16) {
                                        z7 = z4;
                                        bArrCopyOfRange = null;
                                        break;
                                    }
                                    zzoaVar2.zzbd(i18);
                                    i19 = zzoaVar2.readInt();
                                    z7 = z4;
                                    if (zzoaVar2.readInt() == zzjq.zzaua) {
                                        bArrCopyOfRange = Arrays.copyOfRange(zzoaVar2.data, i18, i19 + i18);
                                        break;
                                    }
                                    i18 += i19;
                                    z4 = z7;
                                }
                                bArr = bArrCopyOfRange;
                                z4 = z7;
                            } else {
                                z6 = z4;
                                if (i17 == zzjq.zzaty) {
                                    unsignedByte = zzoaVar2.readUnsignedByte();
                                    zzoaVar2.zzbe(3);
                                    if (unsignedByte == 0) {
                                        unsignedByte2 = zzoaVar2.readUnsignedByte();
                                        if (unsignedByte2 == 0) {
                                            z4 = z6;
                                            i15 = 0;
                                        } else if (unsignedByte2 == 1) {
                                            z4 = z6;
                                            i15 = 1;
                                        } else if (unsignedByte2 == 2) {
                                            z4 = z6;
                                            i15 = 2;
                                        } else if (unsignedByte2 == 3) {
                                            z4 = z6;
                                            i15 = 3;
                                        }
                                    }
                                }
                                z4 = z6;
                            }
                            str = str2;
                        }
                        position2 += i16;
                    }
                    str = str3;
                    listSingletonList = list;
                    position2 += i16;
                }
                if (str != null) {
                    zzjwVar.zzafw = zzgq.zza(Integer.toString(i7), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i13, fZzij, bArr, i15, null, null);
                }
            }
            zzoaVar2.zzbd(position + i12);
            i10++;
            i9 = i9;
            jZzig2 = j6;
            i = i14;
            pairCreate = pair2;
            zzkbVar = zzkbVar3;
            i8 = i13;
        }
        pair = pairCreate;
        zzkbVar2 = zzkbVar;
        j3 = jZzig2;
        i11 = i;
        zzjtVarZzao = zzjtVar.zzao(zzjq.zzasg);
        if (zzjtVarZzao != null) {
            zzkjVar = null;
            pairCreate2 = Pair.create(null, null);
        } else {
            zzkjVar = null;
            pairCreate2 = Pair.create(null, null);
        }
        if (zzjwVar.zzafw == null) {
            return zzkjVar;
        }
        return new zzkj(zzkbVar2.id, i11, ((Long) pair.first).longValue(), j3, jZza, zzjwVar.zzafw, zzjwVar.zzava, zzjwVar.zzauz, zzjwVar.zzaqq, (long[]) pairCreate2.first, (long[]) pairCreate2.second);
    }

    public static zzkl zza(zzkj zzkjVar, zzjt zzjtVar, zziy zziyVar) throws zzgt {
        zzjx zzjyVar;
        boolean z;
        int iZzij;
        int iZzij2;
        int i;
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        int i2;
        long j;
        int[] iArr3;
        long[] jArr3;
        int[] iArr4;
        int[] iArr5;
        int i3;
        zzkj zzkjVar2 = zzkjVar;
        zzjs zzjsVarZzan = zzjtVar.zzan(zzjq.zzath);
        if (zzjsVarZzan != null) {
            zzjyVar = new zzjz(zzjsVarZzan);
        } else {
            zzjs zzjsVarZzan2 = zzjtVar.zzan(zzjq.zzati);
            if (zzjsVarZzan2 == null) {
                throw new zzgt("Track has no sample table size information");
            }
            zzjyVar = new zzjy(zzjsVarZzan2);
        }
        int iZzgh = zzjyVar.zzgh();
        if (iZzgh == 0) {
            return new zzkl(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        zzjs zzjsVarZzan3 = zzjtVar.zzan(zzjq.zzatj);
        if (zzjsVarZzan3 == null) {
            zzjsVarZzan3 = zzjtVar.zzan(zzjq.zzatk);
            z = true;
        } else {
            z = false;
        }
        zzoa zzoaVar = zzjsVarZzan3.zzauh;
        zzoa zzoaVar2 = zzjtVar.zzan(zzjq.zzatg).zzauh;
        zzoa zzoaVar3 = zzjtVar.zzan(zzjq.zzatd).zzauh;
        zzjs zzjsVarZzan4 = zzjtVar.zzan(zzjq.zzate);
        zzoa zzoaVar4 = zzjsVarZzan4 != null ? zzjsVarZzan4.zzauh : null;
        zzjs zzjsVarZzan5 = zzjtVar.zzan(zzjq.zzatf);
        zzoa zzoaVar5 = zzjsVarZzan5 != null ? zzjsVarZzan5.zzauh : null;
        zzju zzjuVar = new zzju(zzoaVar2, zzoaVar, z);
        zzoaVar3.zzbd(12);
        int iZzij3 = zzoaVar3.zzij() - 1;
        int iZzij4 = zzoaVar3.zzij();
        int iZzij5 = zzoaVar3.zzij();
        if (zzoaVar5 != null) {
            zzoaVar5.zzbd(12);
            iZzij = zzoaVar5.zzij();
        } else {
            iZzij = 0;
        }
        int iZzij6 = -1;
        if (zzoaVar4 != null) {
            zzoaVar4.zzbd(12);
            iZzij2 = zzoaVar4.zzij();
            if (iZzij2 > 0) {
                iZzij6 = zzoaVar4.zzij() - 1;
            } else {
                zzoaVar4 = null;
            }
        } else {
            iZzij2 = 0;
        }
        long j2 = 0;
        if (!(zzjyVar.zzgj() && MimeTypes.AUDIO_RAW.equals(zzkjVar2.zzafw.zzafb) && iZzij3 == 0 && iZzij == 0 && iZzij2 == 0)) {
            jArr = new long[iZzgh];
            iArr = new int[iZzgh];
            jArr2 = new long[iZzgh];
            int i4 = iZzij2;
            iArr2 = new int[iZzgh];
            int i5 = i4;
            int i6 = iZzij5;
            int i7 = iZzij;
            int iZzij7 = iZzij6;
            long j3 = 0;
            long j4 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int iZzij8 = 0;
            int iZzij9 = iZzij4;
            int i11 = iZzij3;
            int i12 = 0;
            while (i12 < iZzgh) {
                long j5 = j3;
                int i13 = i8;
                while (i13 == 0) {
                    zznt.checkState(zzjuVar.zzgg());
                    j5 = zzjuVar.zzaum;
                    i13 = zzjuVar.zzaul;
                    i11 = i11;
                    i6 = i6;
                }
                int i14 = i11;
                int i15 = i6;
                if (zzoaVar5 != null) {
                    while (iZzij8 == 0 && i7 > 0) {
                        iZzij8 = zzoaVar5.zzij();
                        i10 = zzoaVar5.readInt();
                        i7--;
                    }
                    iZzij8--;
                }
                int i16 = i10;
                jArr[i12] = j5;
                iArr[i12] = zzjyVar.zzgi();
                if (iArr[i12] > i9) {
                    i9 = iArr[i12];
                }
                zzjx zzjxVar = zzjyVar;
                jArr2[i12] = j4 + ((long) i16);
                iArr2[i12] = zzoaVar4 == null ? 1 : 0;
                if (i12 == iZzij7) {
                    iArr2[i12] = 1;
                    i5--;
                    if (i5 > 0) {
                        iZzij7 = zzoaVar4.zzij() - 1;
                    }
                }
                int i17 = i5;
                int i18 = iZzij7;
                int iZzij10 = i15;
                j4 += (long) iZzij10;
                iZzij9--;
                if (iZzij9 != 0 || i14 <= 0) {
                    i3 = i14;
                } else {
                    i3 = i14 - 1;
                    iZzij9 = zzoaVar3.zzij();
                    iZzij10 = zzoaVar3.zzij();
                }
                int i19 = i3;
                long j6 = j5 + ((long) iArr[i12]);
                i12++;
                iZzij7 = i18;
                iZzgh = iZzgh;
                i8 = i13 - 1;
                i10 = i16;
                i11 = i19;
                j3 = j6;
                i6 = iZzij10;
                i5 = i17;
                zzjyVar = zzjxVar;
            }
            i = iZzgh;
            int i20 = i11;
            zznt.checkArgument(iZzij8 == 0);
            while (i7 > 0) {
                zznt.checkArgument(zzoaVar5.zzij() == 0);
                zzoaVar5.readInt();
                i7--;
            }
            if (i5 == 0 && iZzij9 == 0 && i8 == 0 && i20 == 0) {
                zzkjVar2 = zzkjVar;
            } else {
                int i21 = i5;
                zzkjVar2 = zzkjVar;
                int i22 = zzkjVar2.id;
                StringBuilder sb = new StringBuilder(215);
                sb.append("Inconsistent stbl box for track ");
                sb.append(i22);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i21);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(iZzij9);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i8);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i20);
                Log.w("AtomParsers", sb.toString());
            }
            j = j4;
            i2 = i9;
        } else {
            i = iZzgh;
            zzjx zzjxVar2 = zzjyVar;
            long[] jArr4 = new long[zzjuVar.length];
            int[] iArr6 = new int[zzjuVar.length];
            while (zzjuVar.zzgg()) {
                jArr4[zzjuVar.index] = zzjuVar.zzaum;
                iArr6[zzjuVar.index] = zzjuVar.zzaul;
            }
            int iZzgi = zzjxVar2.zzgi();
            long j7 = iZzij5;
            int i23 = 8192 / iZzgi;
            int iZze = 0;
            for (int i24 : iArr6) {
                iZze += zzoh.zze(i24, i23);
            }
            long[] jArr5 = new long[iZze];
            int[] iArr7 = new int[iZze];
            long[] jArr6 = new long[iZze];
            int[] iArr8 = new int[iZze];
            int i25 = 0;
            int i26 = 0;
            int i27 = 0;
            int i28 = 0;
            while (i25 < iArr6.length) {
                int i29 = iArr6[i25];
                long j8 = jArr4[i25];
                int i30 = i26;
                int iMax = i28;
                while (i29 > 0) {
                    int iMin = Math.min(i23, i29);
                    jArr5[i27] = j8;
                    iArr7[i27] = iZzgi * iMin;
                    iMax = Math.max(iMax, iArr7[i27]);
                    jArr6[i27] = ((long) i30) * j7;
                    iArr8[i27] = 1;
                    j8 += (long) iArr7[i27];
                    i30 += iMin;
                    i29 -= iMin;
                    i27++;
                    jArr4 = jArr4;
                    iArr6 = iArr6;
                }
                i25++;
                i28 = iMax;
                i26 = i30;
            }
            zzkd zzkdVar = new zzkd(jArr5, iArr7, i28, jArr6, iArr8);
            jArr = zzkdVar.zzamm;
            iArr = zzkdVar.zzaml;
            int i31 = zzkdVar.zzawk;
            jArr2 = zzkdVar.zzawl;
            iArr2 = zzkdVar.zzawm;
            i2 = i31;
            j = 0;
        }
        if (zzkjVar2.zzaxh == null || zziyVar.zzgb()) {
            int[] iArr9 = iArr;
            zzoh.zza(jArr2, 1000000L, zzkjVar2.zzct);
            return new zzkl(jArr, iArr9, i2, jArr2, iArr2);
        }
        if (zzkjVar2.zzaxh.length == 1 && zzkjVar2.type == 1 && jArr2.length >= 2) {
            long j9 = zzkjVar2.zzaxi[0];
            long jZza = zzoh.zza(zzkjVar2.zzaxh[0], zzkjVar2.zzct, zzkjVar2.zzaxe) + j9;
            if (jArr2[0] <= j9 && j9 < jArr2[1] && jArr2[jArr2.length - 1] < jZza && jZza <= j) {
                long j10 = j - jZza;
                long jZza2 = zzoh.zza(j9 - jArr2[0], zzkjVar2.zzafw.zzafm, zzkjVar2.zzct);
                long jZza3 = zzoh.zza(j10, zzkjVar2.zzafw.zzafm, zzkjVar2.zzct);
                if ((jZza2 != 0 || jZza3 != 0) && jZza2 <= 2147483647L && jZza3 <= 2147483647L) {
                    zziyVar.zzafo = (int) jZza2;
                    zziyVar.zzafp = (int) jZza3;
                    zzoh.zza(jArr2, 1000000L, zzkjVar2.zzct);
                    return new zzkl(jArr, iArr, i2, jArr2, iArr2);
                }
            }
        }
        if (zzkjVar2.zzaxh.length == 1) {
            char c = 0;
            if (zzkjVar2.zzaxh[0] == 0) {
                int i32 = 0;
                while (i32 < jArr2.length) {
                    jArr2[i32] = zzoh.zza(jArr2[i32] - zzkjVar2.zzaxi[c], 1000000L, zzkjVar2.zzct);
                    i32++;
                    c = 0;
                }
                return new zzkl(jArr, iArr, i2, jArr2, iArr2);
            }
        }
        boolean z2 = zzkjVar2.type == 1;
        int i33 = 0;
        boolean z3 = false;
        int i34 = 0;
        int i35 = 0;
        while (i33 < zzkjVar2.zzaxh.length) {
            long j11 = zzkjVar2.zzaxi[i33];
            if (j11 != -1) {
                long jZza4 = zzoh.zza(zzkjVar2.zzaxh[i33], zzkjVar2.zzct, zzkjVar2.zzaxe);
                int iZzb = zzoh.zzb(jArr2, j11, true, true);
                int iZzb2 = zzoh.zzb(jArr2, j11 + jZza4, z2, false);
                i34 += iZzb2 - iZzb;
                z3 |= i35 != iZzb;
                i35 = iZzb2;
            }
            i33++;
            iArr = iArr;
        }
        int[] iArr10 = iArr;
        boolean z4 = (i34 != i) | z3;
        long[] jArr7 = z4 ? new long[i34] : jArr;
        int[] iArr11 = z4 ? new int[i34] : iArr10;
        if (z4) {
            i2 = 0;
        }
        int[] iArr12 = z4 ? new int[i34] : iArr2;
        long[] jArr8 = new long[i34];
        int i36 = i2;
        int i37 = 0;
        int i38 = 0;
        while (i37 < zzkjVar2.zzaxh.length) {
            long j12 = zzkjVar2.zzaxi[i37];
            long j13 = zzkjVar2.zzaxh[i37];
            if (j12 != -1) {
                int[] iArr13 = iArr12;
                long jZza5 = zzoh.zza(j13, zzkjVar2.zzct, zzkjVar2.zzaxe) + j12;
                int iZzb3 = zzoh.zzb(jArr2, j12, true, true);
                int iZzb4 = zzoh.zzb(jArr2, jZza5, z2, false);
                if (z4) {
                    int i39 = iZzb4 - iZzb3;
                    System.arraycopy(jArr, iZzb3, jArr7, i38, i39);
                    iArr3 = iArr10;
                    System.arraycopy(iArr3, iZzb3, iArr11, i38, i39);
                    iArr5 = iArr13;
                    System.arraycopy(iArr2, iZzb3, iArr5, i38, i39);
                } else {
                    iArr3 = iArr10;
                    iArr5 = iArr13;
                }
                int i40 = i36;
                while (iZzb3 < iZzb4) {
                    long[] jArr9 = jArr;
                    int[] iArr14 = iArr2;
                    long j14 = j12;
                    jArr8[i38] = zzoh.zza(j2, 1000000L, zzkjVar2.zzaxe) + zzoh.zza(jArr2[iZzb3] - j12, 1000000L, zzkjVar2.zzct);
                    if (z4 && iArr11[i38] > i40) {
                        i40 = iArr3[iZzb3];
                    }
                    i38++;
                    iZzb3++;
                    jArr = jArr9;
                    j12 = j14;
                    iArr2 = iArr14;
                }
                jArr3 = jArr;
                iArr4 = iArr2;
                i36 = i40;
            } else {
                iArr3 = iArr10;
                z2 = z2;
                jArr3 = jArr;
                iArr4 = iArr2;
                iArr5 = iArr12;
            }
            j2 += j13;
            i37++;
            iArr12 = iArr5;
            jArr = jArr3;
            iArr2 = iArr4;
            z2 = z2;
            iArr10 = iArr3;
        }
        int[] iArr15 = iArr12;
        boolean z5 = false;
        for (int i41 = 0; i41 < iArr15.length && !z5; i41++) {
            z5 |= (iArr15[i41] & 1) != 0;
        }
        if (!z5) {
            throw new zzgt("The edited sample sequence does not contain a sync sample.");
        }
        return new zzkl(jArr7, iArr11, i36, jArr8, iArr15);
    }

    public static zzkv zza(zzjs zzjsVar, boolean z) {
        if (z) {
            return null;
        }
        zzoa zzoaVar = zzjsVar.zzauh;
        zzoaVar.zzbd(8);
        while (zzoaVar.zzie() >= 8) {
            int position = zzoaVar.getPosition();
            int i = zzoaVar.readInt();
            if (zzoaVar.readInt() == zzjq.zzats) {
                zzoaVar.zzbd(position);
                int i2 = position + i;
                zzoaVar.zzbe(12);
                while (zzoaVar.getPosition() < i2) {
                    int position2 = zzoaVar.getPosition();
                    int i3 = zzoaVar.readInt();
                    if (zzoaVar.readInt() == zzjq.zzatt) {
                        zzoaVar.zzbd(position2);
                        int i4 = position2 + i3;
                        zzoaVar.zzbe(8);
                        ArrayList arrayList = new ArrayList();
                        while (zzoaVar.getPosition() < i4) {
                            zzkv.zza zzaVarZzd = zzkc.zzd(zzoaVar);
                            if (zzaVarZzd != null) {
                                arrayList.add(zzaVarZzd);
                            }
                        }
                        if (arrayList.isEmpty()) {
                            break;
                        }
                        return new zzkv(arrayList);
                    }
                    zzoaVar.zzbe(i3 - 8);
                }
                return null;
            }
            zzoaVar.zzbe(i - 8);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0077  */
    private static Pair<String, byte[]> zzb(zzoa zzoaVar, int i) {
        zzoaVar.zzbd(i + 8 + 4);
        zzoaVar.zzbe(1);
        zzc(zzoaVar);
        zzoaVar.zzbe(2);
        int unsignedByte = zzoaVar.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            zzoaVar.zzbe(2);
        }
        if ((unsignedByte & 64) != 0) {
            zzoaVar.zzbe(zzoaVar.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            zzoaVar.zzbe(2);
        }
        zzoaVar.zzbe(1);
        zzc(zzoaVar);
        int unsignedByte2 = zzoaVar.readUnsignedByte();
        String str = null;
        if (unsignedByte2 == 32) {
            str = MimeTypes.VIDEO_MP4V;
        } else if (unsignedByte2 == 33) {
            str = MimeTypes.VIDEO_H264;
        } else if (unsignedByte2 == 35) {
            str = MimeTypes.VIDEO_H265;
        } else if (unsignedByte2 == 64) {
            str = MimeTypes.AUDIO_AAC;
        } else {
            if (unsignedByte2 == 107) {
                return Pair.create(MimeTypes.AUDIO_MPEG, null);
            }
            if (unsignedByte2 == 165) {
                str = MimeTypes.AUDIO_AC3;
            } else if (unsignedByte2 != 166) {
                switch (unsignedByte2) {
                    case 102:
                    case 103:
                    case 104:
                        str = MimeTypes.AUDIO_AAC;
                        break;
                    default:
                        switch (unsignedByte2) {
                            case 169:
                            case 172:
                                return Pair.create(MimeTypes.AUDIO_DTS, null);
                            case BuildConfig.VERSION_CODE /* 170 */:
                            case 171:
                                return Pair.create(MimeTypes.AUDIO_DTS_HD, null);
                        }
                }
            } else {
                str = MimeTypes.AUDIO_E_AC3;
            }
        }
        zzoaVar.zzbe(12);
        zzoaVar.zzbe(1);
        int iZzc = zzc(zzoaVar);
        byte[] bArr = new byte[iZzc];
        zzoaVar.zze(bArr, 0, iZzc);
        return Pair.create(str, bArr);
    }

    private static int zza(zzoa zzoaVar, int i, int i2, zzjw zzjwVar, int i3) {
        zzki zzkiVar;
        int position = zzoaVar.getPosition();
        while (true) {
            if (position - i >= i2) {
                return 0;
            }
            zzoaVar.zzbd(position);
            int i4 = zzoaVar.readInt();
            zznt.checkArgument(i4 > 0, "childAtomSize should be positive");
            if (zzoaVar.readInt() == zzjq.zzasm) {
                int i5 = position + 8;
                Pair pairCreate = null;
                Integer numValueOf = null;
                zzki zzkiVar2 = null;
                boolean z = false;
                while (i5 - position < i4) {
                    zzoaVar.zzbd(i5);
                    int i6 = zzoaVar.readInt();
                    int i7 = zzoaVar.readInt();
                    if (i7 == zzjq.zzass) {
                        numValueOf = Integer.valueOf(zzoaVar.readInt());
                    } else if (i7 == zzjq.zzasn) {
                        zzoaVar.zzbe(4);
                        z = zzoaVar.readInt() == zzauy;
                    } else if (i7 == zzjq.zzaso) {
                        int i8 = i5 + 8;
                        while (true) {
                            if (i8 - i5 >= i6) {
                                zzkiVar = null;
                                break;
                            }
                            zzoaVar.zzbd(i8);
                            int i9 = zzoaVar.readInt();
                            if (zzoaVar.readInt() == zzjq.zzasp) {
                                zzoaVar.zzbe(6);
                                boolean z2 = zzoaVar.readUnsignedByte() == 1;
                                int unsignedByte = zzoaVar.readUnsignedByte();
                                byte[] bArr = new byte[16];
                                zzoaVar.zze(bArr, 0, 16);
                                zzkiVar = new zzki(z2, unsignedByte, bArr);
                                break;
                            }
                            i8 += i9;
                        }
                        zzkiVar2 = zzkiVar;
                    }
                    i5 += i6;
                }
                if (z) {
                    zznt.checkArgument(numValueOf != null, "frma atom is mandatory");
                    zznt.checkArgument(zzkiVar2 != null, "schi->tenc atom is mandatory");
                    pairCreate = Pair.create(numValueOf, zzkiVar2);
                }
                if (pairCreate != null) {
                    zzjwVar.zzauz[i3] = (zzki) pairCreate.second;
                    return ((Integer) pairCreate.first).intValue();
                }
            }
            position += i4;
        }
    }

    private static int zzc(zzoa zzoaVar) {
        int unsignedByte = zzoaVar.readUnsignedByte();
        int i = unsignedByte & Notifications.NOTIFICATION_TYPES_ALL;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = zzoaVar.readUnsignedByte();
            i = (i << 7) | (unsignedByte & Notifications.NOTIFICATION_TYPES_ALL);
        }
        return i;
    }
}
