package com.my.target;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.Map;

/* JADX INFO: compiled from: FingerprintDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bn extends bm {

    @NonNull
    private static final bn hV = new bn();

    @NonNull
    private final bk hW = new bk();

    @NonNull
    private final bl hX = new bl();

    @NonNull
    private final bo hY = new bo();

    @NonNull
    private final bp hZ = new bp();

    @NonNull
    public static bn aO() {
        return hV;
    }

    @NonNull
    public bk aP() {
        return this.hW;
    }

    @NonNull
    public bl aQ() {
        return this.hX;
    }

    @NonNull
    public bo aR() {
        return this.hY;
    }

    @NonNull
    public bp aS() {
        return this.hZ;
    }

    private bn() {
    }

    @Override // com.my.target.bm
    @WorkerThread
    public synchronized void collectData(@NonNull Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            g.a("FingerprintDataProvider: You must not call collectData method from main thread");
            return;
        }
        removeAll();
        this.hW.collectData(context);
        this.hX.collectData(context);
        this.hY.collectData(context);
        this.hZ.collectData(context);
        Map<String, String> map = getMap();
        this.hW.putDataTo(map);
        this.hX.putDataTo(map);
        this.hY.putDataTo(map);
        this.hZ.putDataTo(map);
    }
}
