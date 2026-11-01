package com.inmobi.commons.core.utilities;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;

/* JADX INFO: compiled from: PlayServicesUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {
    private static final String a = "f";

    public static boolean a(@NonNull String str) {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return false;
        }
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(contextB) == 0;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error in connecting to GooglePlayServices API for component ");
            sb.append(str);
            sb.append(" : (");
            sb.append(e.getMessage());
            sb.append(")");
            return false;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }
}
