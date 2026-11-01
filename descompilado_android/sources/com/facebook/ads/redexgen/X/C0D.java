package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0D, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0D {
    private C0D() {
    }

    public static boolean B(Context context, String packageName) {
        boolean z = false;
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(packageName, 1);
            z = true;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return z;
        } catch (RuntimeException e2) {
            return z;
        }
    }

    public static Collection<String> C(JSONArray detectionStrings) {
        if (detectionStrings == null || detectionStrings.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < detectionStrings.length(); i++) {
            hashSet.add(detectionStrings.optString(i));
        }
        return hashSet;
    }

    public static boolean D(Context context, C0C dataModel, JV adEventManager) {
        C0B c0bEC = dataModel.EC();
        if (c0bEC == null || c0bEC == C0B.NONE) {
            return false;
        }
        boolean z = false;
        Collection<String> collectionBC = dataModel.BC();
        if (collectionBC == null || collectionBC.isEmpty()) {
            return false;
        }
        Iterator<String> it = collectionBC.iterator();
        while (it.hasNext()) {
            if (B(context, it.next())) {
                z = true;
                break;
            }
        }
        if (z != (c0bEC == C0B.INSTALLED)) {
            return false;
        }
        String strAC = dataModel.AC();
        if (!TextUtils.isEmpty(strAC)) {
            adEventManager.mC(strAC, null);
            return true;
        }
        C0585Hx.D(context, "api", C0584Hw.X, new C0583Hv("Ad is invalidated without token."));
        return true;
    }
}
