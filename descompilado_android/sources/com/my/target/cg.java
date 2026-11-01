package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class cg {
    @NonNull
    public static float[] a(float f, @NonNull float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = (f / 100.0f) * fArr[i];
        }
        return fArr2;
    }

    @NonNull
    public static <T extends ah> float[] a(@NonNull am<T> amVar, @Nullable float[] fArr, float f) {
        float point;
        int i;
        float f2;
        float point2;
        float fRound;
        HashSet hashSet = new HashSet();
        if (fArr != null && fArr.length > 0) {
            Arrays.sort(fArr);
        }
        List<ak<T>> listR = amVar.R();
        int i2 = 0;
        int i3 = 0;
        for (ak<T> akVar : listR) {
            if (fArr == null) {
                if (akVar.getPointP() > 0.0f) {
                    point2 = (akVar.getPointP() / 100.0f) * f;
                } else {
                    point2 = (akVar.getPoint() < 0.0f || akVar.getPoint() > f) ? f / 2.0f : akVar.getPoint();
                }
                fRound = Math.round(point2 * 10.0f) / 10.0f;
                akVar.setPoint(fRound);
            } else if (i3 < fArr.length) {
                float f3 = fArr[i3];
                if (!akVar.getType().equals("statistics")) {
                    i3++;
                }
                if (f3 > f) {
                    g.a("Cannot set midroll position " + f3 + ": out of duration");
                    akVar.setPoint(-1.0f);
                } else {
                    akVar.setPoint(f3);
                    fRound = f3;
                }
            } else {
                akVar.setPoint(-1.0f);
            }
            hashSet.add(Float.valueOf(fRound));
        }
        if (fArr == null || fArr.length > listR.size()) {
            for (af afVar : amVar.S()) {
                if (fArr != null) {
                    if (i3 < fArr.length) {
                        i = i3 + 1;
                        f2 = fArr[i3];
                        if (f2 > f) {
                            g.a("Cannot set midroll position " + f2 + ": out of duration");
                            afVar.setPoint(-1.0f);
                        } else {
                            afVar.setPoint(f2);
                        }
                        i3 = i;
                    } else {
                        afVar.setPoint(-1.0f);
                    }
                } else {
                    if (afVar.getPointP() >= 0.0f) {
                        point = (afVar.getPointP() / 100.0f) * f;
                    } else {
                        point = (afVar.getPoint() < 0.0f || afVar.getPoint() > f) ? f / 2.0f : afVar.getPoint();
                    }
                    float fRound2 = Math.round(point * 10.0f) / 10.0f;
                    afVar.setPoint(fRound2);
                    i = i3;
                    f2 = fRound2;
                }
                hashSet.add(Float.valueOf(f2));
                i3 = i;
            }
        }
        float[] fArr2 = new float[hashSet.size()];
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            fArr2[i2] = ((Float) it.next()).floatValue();
            i2++;
        }
        Arrays.sort(fArr2);
        return fArr2;
    }

    public static boolean bl() {
        try {
            Class.forName("com.google.android.exoplayer2.ExoPlayer");
            return true;
        } catch (ClassNotFoundException unused) {
            g.a("ExoPlayer doesn't exist, add ExoPlayer dependency to play video");
            return false;
        }
    }

    public static boolean bm() {
        try {
            Class.forName("com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
