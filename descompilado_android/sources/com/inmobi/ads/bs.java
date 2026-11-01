package com.inmobi.ads;

import android.graphics.Point;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: VastBestFitCompanionFinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bs {
    /* JADX WARN: Code duplicated, block: B:31:0x00d0  */
    @Nullable
    static bt a(be beVar, ak akVar) {
        double d;
        double d2;
        by byVarB = beVar.b();
        Point point = akVar.c.a;
        List<bt> listE = byVarB.e();
        float f = com.inmobi.commons.core.utilities.b.c.a().c;
        double d3 = point.y / f;
        double d4 = point.x / f;
        Double.isNaN(d4);
        Double.isNaN(d3);
        double d5 = d4 / d3;
        Double.isNaN(d4);
        Double.isNaN(d3);
        double d6 = d4 * d3;
        Iterator<bt> it = listE.iterator();
        double d7 = -1.0d;
        double d8 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        bt btVar = null;
        while (it.hasNext()) {
            bt next = it.next();
            int i = next.b;
            it = it;
            bt btVar2 = btVar;
            double d9 = next.a;
            double d10 = d8;
            double d11 = i;
            Double.isNaN(d9);
            Double.isNaN(d11);
            if (d5 > d9 / d11) {
                Double.isNaN(d3);
                Double.isNaN(d11);
                Double.isNaN(d9);
                d2 = (d3 / d11) * d9;
                d = d3;
            } else {
                Double.isNaN(d4);
                Double.isNaN(d9);
                Double.isNaN(d11);
                d = (d4 / d9) * d11;
                d2 = d4;
            }
            if (d11 >= d * 0.33d && d9 >= 0.33d * d2) {
                double d12 = d2 * d;
                if (d12 > 0.5d * d6) {
                    if (d12 > d7) {
                        Double.isNaN(d11);
                        d8 = d11 / d;
                        d7 = d12;
                        btVar = next;
                    } else if (d12 == d7) {
                        float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
                        Double.isNaN(d11);
                        d8 = d11 / d;
                        if (d8 <= d10 || d10 >= f2) {
                            double d13 = f2;
                            if (d10 <= d13 || d8 >= d10 || d8 <= d13) {
                                btVar = btVar2;
                                d8 = d10;
                            }
                        }
                        btVar = next;
                    } else {
                        btVar = btVar2;
                        d8 = d10;
                    }
                }
            }
            btVar = btVar2;
            d8 = d10;
        }
        return btVar;
    }
}
