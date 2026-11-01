package com.ironsource.mediationsdk.server;

import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class Server {
    /* JADX INFO: Access modifiers changed from: private */
    public static void callRequestURL(String str, boolean z, int i) {
        try {
            new JSONObject(HttpFunctions.getStringFromURL(ServerURL.getRequestURL(str, z, i)));
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.NETWORK, "callRequestURL(reqUrl:" + str + ", hit:" + z + ")", 1);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("callRequestURL(reqUrl:");
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            sb.append(", hit:");
            sb.append(z);
            sb.append(")");
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.NETWORK, sb.toString() + ", e:" + Log.getStackTraceString(th), 0);
        }
    }

    public static void callAsyncRequestURL(final String str, final boolean z, final int i) {
        Thread thread = new Thread(new Runnable() { // from class: com.ironsource.mediationsdk.server.Server.1
            @Override // java.lang.Runnable
            public void run() {
                Server.callRequestURL(str, z, i);
            }
        }, "callAsyncRequestURL");
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        thread.start();
    }
}
