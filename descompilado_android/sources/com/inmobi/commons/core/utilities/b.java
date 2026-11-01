package com.inmobi.commons.core.utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/* JADX INFO: compiled from: ClickUrlHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final String a = "b";

    public static boolean a(Context context, String str) {
        if (str == null) {
            return false;
        }
        if (context == null) {
            return a(Uri.parse(str));
        }
        try {
            return new Intent("android.intent.action.VIEW", Uri.parse(str)).resolveActivity(context.getPackageManager()) != null;
        } catch (Exception unused) {
        }
    }

    public static void b(Context context, @NonNull String str) throws ActivityNotFoundException, URISyntaxException {
        while (context != null) {
            try {
                Intent uri = Intent.parseUri(str, 0);
                uri.setFlags(DriveFile.MODE_READ_ONLY);
                context.startActivity(uri);
                return;
            } catch (ActivityNotFoundException e) {
                if ("intent".equals(Uri.parse(str).getScheme())) {
                    str = b(str);
                    if (!TextUtils.isEmpty(str)) {
                    }
                }
                throw e;
            }
        }
    }

    @Nullable
    public static String a(Context context, @NonNull String str, @Nullable String str2) {
        if (context == null) {
            return null;
        }
        try {
            Intent uri = Intent.parseUri(str, 0);
            if (uri.resolveActivity(context.getPackageManager()) != null) {
                uri.setFlags(DriveFile.MODE_READ_ONLY);
                context.startActivity(uri);
                return str;
            }
            StringBuilder sb = new StringBuilder("No app can handle the:");
            sb.append(str);
            sb.append(", trying with fallback URL if any");
            if (!TextUtils.isEmpty(str2)) {
                return a(context, str2, null);
            }
            if ("intent".equals(Uri.parse(str).getScheme())) {
                String strB = b(str);
                if (!TextUtils.isEmpty(strB)) {
                    return a(context, URLDecoder.decode(strB, "UTF-8"), null);
                }
            }
            return null;
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("No app can handle the url:");
            sb2.append(str);
            sb2.append(", Log : ");
            sb2.append(e.getMessage());
        }
    }

    @Nullable
    private static String b(String str) {
        try {
            return Intent.parseUri(str, 1).getStringExtra("browser_fallback_url");
        } catch (URISyntaxException e) {
            new StringBuilder("Exception while getting Fallback Url :").append(e.getMessage());
            return null;
        }
    }

    private static boolean a(Uri uri) {
        return "http".equals(uri.getScheme()) || "https".equals(uri.getScheme());
    }

    public static boolean a(@NonNull String str) {
        Uri uri = Uri.parse(str);
        return (!a(uri) || "play.google.com".equals(uri.getHost()) || "market.android.com".equals(uri.getHost()) || "market".equals(uri.getScheme())) ? false : true;
    }
}
