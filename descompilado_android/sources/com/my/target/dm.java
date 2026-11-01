package com.my.target;

import android.graphics.Paint;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: StandardNativeStringUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dm {
    @NonNull
    static List<String> a(@NonNull String str, float f, @NonNull Paint paint) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : str.split("\\s")) {
            if (paint.measureText(str2) < f) {
                a(f, paint, arrayList, arrayList2, str2);
            } else {
                Iterator<String> it = b(str2, f, paint).iterator();
                while (it.hasNext()) {
                    a(f, paint, arrayList, arrayList2, it.next());
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList.add(TextUtils.join(" ", arrayList2));
        }
        return arrayList;
    }

    private static List<String> b(@NonNull String str, float f, @NonNull Paint paint) {
        if (TextUtils.isEmpty(str) || paint.measureText(str) <= f) {
            return Collections.singletonList(str);
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 1; i2 <= str.length(); i2++) {
            if (paint.measureText(str.substring(i, i2)) >= f) {
                int i3 = i2 - 1;
                arrayList.add(str.substring(i, i3));
                i = i3;
            }
            if (i2 == str.length()) {
                arrayList.add(str.substring(i, i2));
            }
        }
        return arrayList;
    }

    private static void a(float f, @NonNull Paint paint, @NonNull ArrayList<String> arrayList, @NonNull ArrayList<String> arrayList2, @NonNull String str) {
        arrayList2.add(str);
        if (paint.measureText(TextUtils.join(" ", arrayList2)) >= f) {
            arrayList2.remove(arrayList2.size() - 1);
            arrayList.add(TextUtils.join(" ", arrayList2));
            arrayList2.clear();
            arrayList2.add(str);
        }
    }
}
