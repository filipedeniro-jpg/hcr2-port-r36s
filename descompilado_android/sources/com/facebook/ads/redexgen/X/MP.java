package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.VisibleForTesting;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MP {
    private static int B = -1;
    public static volatile MO C = MO.NOT_INITIALIZED;

    private MP() {
    }

    public static int D(Context context) {
        if (C == MO.NOT_INITIALIZED) {
            H(context);
        }
        return B;
    }

    public static int E(Context context) {
        try {
            return I(context.getAssets().openXmlResourceParser("AndroidManifest.xml"));
        } catch (IOException | XmlPullParserException e) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int F(Context context) {
        int i = 0;
        try {
            i = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
            return i;
        } catch (PackageManager.NameNotFoundException e) {
            return i;
        }
    }

    private static boolean G() {
        return C == MO.INITIALIZED;
    }

    private static void H(Context context) {
        if (G()) {
            return;
        }
        J(context);
    }

    @VisibleForTesting(otherwise = 2)
    private static int I(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (parser.next() != 1) {
            if (parser.getEventType() == 2 && parser.getName().equals("uses-sdk")) {
                for (int i = 0; i < parser.getAttributeCount(); i++) {
                    if (parser.getAttributeName(i).equals("minSdkVersion")) {
                        return Integer.parseInt(parser.getAttributeValue(i));
                    }
                }
            }
        }
        return 0;
    }

    private static void J(Context context) {
        if (C != MO.NOT_INITIALIZED) {
            return;
        }
        C = MO.INITIALIZING;
        Executors.newSingleThreadExecutor().execute(new MN(context));
    }
}
