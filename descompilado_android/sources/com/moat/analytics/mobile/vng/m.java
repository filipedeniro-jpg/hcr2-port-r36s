package com.moat.analytics.mobile.vng;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
class m extends Exception {
    m() {
    }

    static void a(Exception exc) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        }
    }
}
