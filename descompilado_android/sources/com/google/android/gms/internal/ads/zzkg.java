package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkg {
    private static final int[] zzawx = {zzoh.zzbi("isom"), zzoh.zzbi("iso2"), zzoh.zzbi("iso3"), zzoh.zzbi("iso4"), zzoh.zzbi("iso5"), zzoh.zzbi("iso6"), zzoh.zzbi("avc1"), zzoh.zzbi("hvc1"), zzoh.zzbi("hev1"), zzoh.zzbi("mp41"), zzoh.zzbi("mp42"), zzoh.zzbi("3g2a"), zzoh.zzbi("3g2b"), zzoh.zzbi("3gr6"), zzoh.zzbi("3gs6"), zzoh.zzbi("3ge6"), zzoh.zzbi("3gg6"), zzoh.zzbi("M4V "), zzoh.zzbi("M4A "), zzoh.zzbi("f4v "), zzoh.zzbi("kddi"), zzoh.zzbi("M4VP"), zzoh.zzbi("qt  "), zzoh.zzbi("MSNV")};

    public static boolean zzd(zzix zzixVar) throws InterruptedException, IOException {
        boolean z;
        boolean z2;
        long length = zzixVar.getLength();
        if (length == -1 || length > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            length = 4096;
        }
        int i = (int) length;
        zzoa zzoaVar = new zzoa(64);
        int i2 = 0;
        boolean z3 = false;
        while (true) {
            if (i2 < i) {
                zzoaVar.reset(8);
                zzixVar.zza(zzoaVar.data, 0, 8);
                long jZzig = zzoaVar.zzig();
                int i3 = zzoaVar.readInt();
                int i4 = 16;
                if (jZzig == 1) {
                    zzixVar.zza(zzoaVar.data, 8, 8);
                    zzoaVar.zzbc(16);
                    jZzig = zzoaVar.zzik();
                } else {
                    i4 = 8;
                }
                long j = i4;
                if (jZzig >= j) {
                    i2 += i4;
                    if (i3 != zzjq.zzars) {
                        if (i3 == zzjq.zzasb || i3 == zzjq.zzasd) {
                            z = true;
                            if (!z3 && !z) {
                                return true;
                            }
                        } else if ((((long) i2) + jZzig) - j < i) {
                            int i5 = (int) (jZzig - j);
                            i2 += i5;
                            if (i3 == zzjq.zzaqr) {
                                if (i5 >= 8) {
                                    zzoaVar.reset(i5);
                                    zzixVar.zza(zzoaVar.data, 0, i5);
                                    int i6 = i5 / 4;
                                    for (int i7 = 0; i7 < i6; i7++) {
                                        if (i7 != 1) {
                                            int i8 = zzoaVar.readInt();
                                            if ((i8 >>> 8) == zzoh.zzbi("3gp")) {
                                                z2 = true;
                                                break;
                                            }
                                            int[] iArr = zzawx;
                                            int length2 = iArr.length;
                                            int i9 = 0;
                                            while (true) {
                                                if (i9 >= length2) {
                                                    z2 = false;
                                                    break;
                                                }
                                                if (iArr[i9] == i8) {
                                                    z2 = true;
                                                    break;
                                                }
                                                i9++;
                                            }
                                            if (z2) {
                                                z3 = true;
                                                break;
                                            }
                                        } else {
                                            zzoaVar.zzbe(4);
                                        }
                                    }
                                    if (z3) {
                                    }
                                }
                            } else if (i5 != 0) {
                                zzixVar.zzac(i5);
                            }
                        }
                    }
                }
                return false;
            }
            z = false;
            if (!z3) {
            }
            return false;
        }
    }
}
