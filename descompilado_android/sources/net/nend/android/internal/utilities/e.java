package net.nend.android.internal.utilities;

import android.content.Context;
import android.util.Log;
import net.nend.android.NendAdLogger;

/* JADX INFO: compiled from: InternalDebuggable.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static void a(Context context) {
        if (NendAdLogger.getLogLevel().getInt() == NendAdLogger.LogLevel.OFF.getInt()) {
            NendAdLogger.setLogLevel(f.a(context, "NendDebuggable", false) ? NendAdLogger.LogLevel.DEBUG : NendAdLogger.LogLevel.OFF);
            if (f.a(context, "NendDebuggable")) {
                Log.i("nend_SDK", "NendDebuggable is deprecated. Use NendAdLogger#setLogLevel(NendAdLogger.LogLevel) instead.");
            }
        }
    }
}
