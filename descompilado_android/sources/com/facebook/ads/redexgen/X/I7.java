package com.facebook.ads.redexgen.X;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.drive.DriveFile;
import java.util.Iterator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class I7 {
    public static void B(I7 uriLauncher, Context context, Uri uri, String clientToken) {
        boolean z = F(uri.getScheme()) && uri.getHost().equals("play.google.com");
        if (!uri.getScheme().equals("market") && !z) {
            uriLauncher.J(context, uri, clientToken);
            return;
        }
        try {
            uriLauncher.H(context, uri);
        } catch (IC e) {
            uriLauncher.J(context, uri, clientToken);
        }
    }

    private Intent C(Context context, Uri uri) {
        Intent intentD = D(uri);
        intentD.addCategory("android.intent.category.BROWSABLE");
        intentD.addFlags(DriveFile.MODE_READ_ONLY);
        intentD.putExtra("com.android.browser.application_id", context.getPackageName());
        intentD.putExtra("create_new_tab", false);
        return intentD;
    }

    private Intent D(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (Build.VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        return intent;
    }

    private boolean E(Context context) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0).iterator();
        while (it.hasNext()) {
            if (it.next().activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                return true;
            }
        }
        return false;
    }

    private static boolean F(String scheme) {
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void G(Context context, Uri uri) {
        context.startActivity(C(context, uri));
    }

    private final void H(Context context, Uri uri) throws IC {
        if (!E(context)) {
            throw new IC();
        }
        Intent intentC = C(context, uri);
        intentC.setPackage("com.android.vending");
        context.startActivity(intentC);
    }

    private void I(Context context, Uri uri, String clientToken) {
        Intent intent = new Intent(context, (Class<?>) MH.B());
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.putExtra("viewType", L2.BROWSER);
        intent.putExtra("browserURL", uri.toString());
        intent.putExtra("clientToken", clientToken);
        intent.putExtra("handlerTime", System.currentTimeMillis());
        try {
            MH.E(context, intent);
        } catch (ActivityNotFoundException e) {
            C0585Hx.D(context, "an_activity", C0584Hw.F, new C0583Hv(e));
            Log.e(AudienceNetworkAds.TAG, "Can't start AudienceNetworkActivity. Make sure that it's in your AndroidManifest.xml file.", e);
        }
    }

    private final void J(Context context, Uri uri, String clientToken) {
        if (F(uri.getScheme()) && JA.t(context)) {
            I(context, uri, clientToken);
        } else {
            G(context, uri);
        }
    }
}
