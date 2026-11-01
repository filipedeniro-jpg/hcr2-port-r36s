package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;

/* JADX INFO: compiled from: ResearchProgressTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fr {

    @NonNull
    private final ArrayList<fv> b;

    @NonNull
    private final ArrayList<ap> c;
    private int d = -1;

    @NonNull
    public static fr a(@NonNull ar arVar) {
        return new fr(arVar);
    }

    private fr(@NonNull ar arVar) {
        ArrayList<fv> arrayList = new ArrayList<>();
        for (aq aqVar : arVar.x(aq.a.dJ)) {
            if (aqVar instanceof fv) {
                arrayList.add((fv) aqVar);
            }
        }
        this.b = arrayList;
        this.c = new ArrayList<>();
        arVar.a(this.c);
    }

    public final void a(int i, int i2, @NonNull Context context) {
        if (i2 < 0 || i < 0 || i == this.d) {
            return;
        }
        this.d = i;
        if (!this.b.isEmpty() && i != 0) {
            for (fv fvVar : this.b) {
                int iG = fvVar.g();
                int iH = fvVar.h();
                if ((iG <= i && (iH == 0 || iH >= i)) && (i - iG) % fvVar.i() == 0) {
                    String strReplace = fvVar.getUrl().replace("[CONTENTPLAYHEAD]", String.valueOf(i));
                    if (!TextUtils.isEmpty(strReplace)) {
                        cj.o(strReplace, context);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        while (!this.c.isEmpty()) {
            ArrayList<ap> arrayList2 = this.c;
            if (arrayList2.get(arrayList2.size() - 1).Y() > i) {
                break;
            }
            ArrayList<ap> arrayList3 = this.c;
            arrayList.add(arrayList3.remove(arrayList3.size() - 1));
        }
        if (arrayList.isEmpty()) {
            return;
        }
        cj.a(arrayList, context);
    }
}
