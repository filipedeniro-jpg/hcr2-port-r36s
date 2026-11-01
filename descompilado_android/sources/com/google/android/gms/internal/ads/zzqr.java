package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqr {
    private final int zzbqd;
    private final zzqh zzbqf;
    private String zzbqn;
    private String zzbqo;
    private final boolean zzbqp = false;
    private final int zzbqq;
    private final int zzbqr;

    public zzqr(int i, int i2, int i3) {
        this.zzbqd = i;
        if (i2 > 64 || i2 < 0) {
            this.zzbqq = 64;
        } else {
            this.zzbqq = i2;
        }
        if (i3 <= 0) {
            this.zzbqr = 1;
        } else {
            this.zzbqr = i3;
        }
        this.zzbqf = new zzqo(this.zzbqq);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0094  */
    /* JADX WARN: Code duplicated, block: B:37:0x00aa  */
    public final String zza(ArrayList<String> arrayList, ArrayList<zzqe> arrayList2) {
        boolean z;
        String string;
        boolean z2;
        int i;
        Collections.sort(arrayList2, new zzqq(this));
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            String[] strArrSplit = Normalizer.normalize(arrayList.get(arrayList2.get(i2).zzma()), Normalizer.Form.NFKC).toLowerCase(Locale.US).split("\n");
            if (strArrSplit.length != 0) {
                int i3 = 0;
                while (true) {
                    if (i3 < strArrSplit.length) {
                        String str = strArrSplit[i3];
                        if (str.indexOf("'") != -1) {
                            StringBuilder sb = new StringBuilder(str);
                            int i4 = 1;
                            boolean z3 = false;
                            while (true) {
                                int i5 = i4 + 2;
                                if (i5 > sb.length()) {
                                    break;
                                }
                                if (sb.charAt(i4) == '\'') {
                                    if (sb.charAt(i4 - 1) != ' ') {
                                        int i6 = i4 + 1;
                                        if ((sb.charAt(i6) == 's' || sb.charAt(i6) == 'S') && (i5 == sb.length() || sb.charAt(i5) == ' ')) {
                                            sb.insert(i4, ' ');
                                            i4 = i5;
                                        } else {
                                            sb.setCharAt(i4, ' ');
                                        }
                                    } else {
                                        sb.setCharAt(i4, ' ');
                                    }
                                    i = 1;
                                    z3 = true;
                                } else {
                                    i = 1;
                                }
                                i4 += i;
                            }
                            string = z3 ? sb.toString() : null;
                            if (string != null) {
                                this.zzbqo = string;
                            } else {
                                string = str;
                            }
                        } else {
                            string = str;
                        }
                        String[] strArrZze = zzql.zze(string, true);
                        if (strArrZze.length >= this.zzbqr) {
                            int i7 = 0;
                            while (true) {
                                if (i7 < strArrZze.length) {
                                    String strConcat = "";
                                    int i8 = 0;
                                    while (true) {
                                        if (i8 >= this.zzbqr) {
                                            z2 = true;
                                            break;
                                        }
                                        int i9 = i7 + i8;
                                        if (i9 >= strArrZze.length) {
                                            z2 = false;
                                            break;
                                        }
                                        if (i8 > 0) {
                                            strConcat = String.valueOf(strConcat).concat(" ");
                                        }
                                        String strValueOf = String.valueOf(strConcat);
                                        String strValueOf2 = String.valueOf(strArrZze[i9]);
                                        strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                                        i8++;
                                    }
                                    if (z2) {
                                        hashSet.add(strConcat);
                                        if (hashSet.size() < this.zzbqd) {
                                            i7++;
                                        }
                                    }
                                    z = false;
                                }
                                if (hashSet.size() >= this.zzbqd) {
                                    z = false;
                                }
                            }
                        }
                        i3++;
                    } else {
                        z = true;
                    }
                }
            } else {
                z = true;
            }
            if (!z) {
                break;
            }
        }
        zzqi zzqiVar = new zzqi();
        this.zzbqn = "";
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzqiVar.write(this.zzbqf.zzbr((String) it.next()));
            } catch (IOException e) {
                zzatm.zzc("Error while writing hash to byteStream", e);
            }
        }
        return zzqiVar.toString();
    }
}
