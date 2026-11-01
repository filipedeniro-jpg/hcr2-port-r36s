package com.my.target.common;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.my.target.bn;
import com.my.target.cj;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MyTargetUtils {
    public static void sendStat(@NonNull String str, @NonNull Context context) {
        cj.o(str, context);
    }

    @WorkerThread
    public static Map<String, String> collectInfo(@NonNull Context context) {
        bn.aO().collectData(context);
        return bn.aO().getData();
    }
}
