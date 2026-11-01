package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdf {
    private static final String[] zzwy = {"/aclk", "/pcs/click", "/dbm/clk"};
    private String zzwu = "googleads.g.doubleclick.net";
    private String zzwv = "/pagead/ads";
    private String zzww = "ad.doubleclick.net";
    private String[] zzwx = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzdc zzwz;

    public zzdf(zzdc zzdcVar) {
        this.zzwz = zzdcVar;
    }

    public final void zzb(String str, String str2) {
        this.zzwu = str;
        this.zzwv = str2;
    }

    public final boolean zza(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzwu) && uri.getPath().equals(this.zzwv);
        } catch (NullPointerException unused) {
        }
    }

    private final boolean zzb(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzww);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final boolean zzc(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String str : this.zzwx) {
                if (host.endsWith(str)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public final void zzap(String str) {
        this.zzwx = str.split(",");
    }

    public final zzdc zzcc() {
        return this.zzwz;
    }

    public final Uri zza(Uri uri, Context context) throws zzdi {
        return zza(uri, this.zzwz.zza(context));
    }

    public final void zzb(MotionEvent motionEvent) {
        this.zzwz.zzb(motionEvent);
    }

    @Deprecated
    public final Uri zzb(Uri uri, Context context) throws zzdi {
        return zza(uri, context, null, null);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzdi {
        try {
            return zza(uri, this.zzwz.zza(context, uri.getQueryParameter("ai"), view, activity));
        } catch (UnsupportedOperationException unused) {
            throw new zzdi("Provided Uri is not in a valid state");
        }
    }

    public final boolean zzd(Uri uri) {
        if (zzc(uri)) {
            for (String str : zzwy) {
                if (uri.getPath().endsWith(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Uri zza(Uri uri, String str) throws zzdi {
        try {
            boolean zZzb = zzb(uri);
            if (zZzb) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzdi("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzdi("Query parameter already exists: ms");
            }
            if (zZzb) {
                String string = uri.toString();
                int iIndexOf = string.indexOf(";adurl");
                if (iIndexOf != -1) {
                    int i = iIndexOf + 1;
                    return Uri.parse(string.substring(0, i) + "dc_ms" + Constants.RequestParameters.EQUAL + str + ";" + string.substring(i));
                }
                String encodedPath = uri.getEncodedPath();
                int iIndexOf2 = string.indexOf(encodedPath);
                return Uri.parse(string.substring(0, encodedPath.length() + iIndexOf2) + ";dc_ms" + Constants.RequestParameters.EQUAL + str + ";" + string.substring(iIndexOf2 + encodedPath.length()));
            }
            String string2 = uri.toString();
            int iIndexOf3 = string2.indexOf("&adurl");
            if (iIndexOf3 == -1) {
                iIndexOf3 = string2.indexOf("?adurl");
            }
            if (iIndexOf3 != -1) {
                int i2 = iIndexOf3 + 1;
                return Uri.parse(string2.substring(0, i2) + "ms" + Constants.RequestParameters.EQUAL + str + Constants.RequestParameters.AMPERSAND + string2.substring(i2));
            }
            return uri.buildUpon().appendQueryParameter("ms", str).build();
        } catch (UnsupportedOperationException unused) {
            throw new zzdi("Provided Uri is not in a valid state");
        }
    }
}
