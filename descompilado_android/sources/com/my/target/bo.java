package com.my.target;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.ironsource.sdk.constants.Constants;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: GoogleAIdDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public class bo extends bm {
    private boolean gX = false;

    bo() {
    }

    @Override // com.my.target.bm
    @WorkerThread
    public synchronized void collectData(@NonNull Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            g.a("You must not call collectData method from main thread");
        } else {
            if (this.gX) {
                return;
            }
            p(context);
            this.gX = true;
        }
    }

    private void p(@NonNull Context context) {
        Object objInvoke;
        g.a("send google AId");
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null) {
                boolean z = true;
                Method method = cls.getMethod("getAdvertisingIdInfo", Context.class);
                if (method == null || (objInvoke = method.invoke(null, context)) == null) {
                    return;
                }
                Method method2 = objInvoke.getClass().getMethod("getId", new Class[0]);
                if (method2 != null) {
                    String str = (String) method2.invoke(objInvoke, new Object[0]);
                    addParam("advertising_id", str);
                    g.a("google AId: " + str);
                }
                Method method3 = objInvoke.getClass().getMethod(Constants.RequestParameters.isLAT, new Class[0]);
                if (method3 != null) {
                    boolean zBooleanValue = ((Boolean) method3.invoke(objInvoke, new Object[0])).booleanValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append(zBooleanValue ? 0 : 1);
                    sb.append("");
                    addParam(i.am, sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("ad tracking enabled: ");
                    if (zBooleanValue) {
                        z = false;
                    }
                    sb2.append(z);
                    g.a(sb2.toString());
                }
            }
        } catch (Throwable th) {
            g.a(th.toString());
            g.a("failed to send google AId");
        }
    }
}
