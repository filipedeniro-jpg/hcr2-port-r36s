package com.google.android.gms.internal.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxb extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(@NonNull Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        Bundle bundleZzh = zzh(context);
        zzaiv zzaivVarZzrj = zzaiv.zzrj();
        if (bundleZzh == null) {
            zzawo.zzes("Metadata was null.");
        } else {
            try {
                String str = (String) bundleZzh.get("com.google.android.gms.ads.APPLICATION_ID");
                try {
                    Boolean bool = (Boolean) bundleZzh.get("com.google.android.gms.ads.AD_MANAGER_APP");
                    try {
                        Boolean bool2 = (Boolean) bundleZzh.get("com.google.android.gms.ads.DELAY_APP_MEASUREMENT_INIT");
                        if (str == null || str.matches("^/\\d+~.+$")) {
                            if (bool == null || !bool.booleanValue()) {
                                throw new IllegalStateException("\n\n******************************************************************************\n* The Google Mobile Ads SDK was initialized incorrectly. AdMob publishers    *\n* should follow the instructions here:                                       *\n* https://googlemobileadssdk.page.link/admob-android-update-manifest         *\n* to add a valid App ID inside the AndroidManifest.                          *\n* Google Ad Manager publishers should follow instructions here:              *\n* https://googlemobileadssdk.page.link/ad-manager-android-update-manifest.   *\n******************************************************************************\n\n");
                            }
                            if (bool2 == null || !bool2.booleanValue()) {
                                zzaivVarZzrj.zzl(context);
                            }
                        } else {
                            if (!str.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$")) {
                                throw new IllegalStateException("\n\n******************************************************************************\n* Invalid application ID. Follow instructions here:                          *\n* https://googlemobileadssdk.page.link/admob-android-update-manifest         *\n* to find your app ID.                                                       *\n******************************************************************************\n\n");
                            }
                            String strValueOf = String.valueOf(str);
                            zzawo.zzdv(strValueOf.length() != 0 ? "Publisher provided Google AdMob App ID in manifest: ".concat(strValueOf) : new String("Publisher provided Google AdMob App ID in manifest: "));
                            if (bool2 == null || !bool2.booleanValue()) {
                                zzaivVarZzrj.zzc(context, str);
                            }
                        }
                    } catch (ClassCastException e) {
                        throw new IllegalStateException("The com.google.android.gms.ads.DELAY_APP_MEASUREMENT_INIT metadata must have a boolean value.", e);
                    }
                } catch (ClassCastException e2) {
                    throw new IllegalStateException("The com.google.android.gms.ads.AD_MANAGER_APP metadata must have a boolean value.", e2);
                }
            } catch (ClassCastException e3) {
                throw new IllegalStateException("The com.google.android.gms.ads.APPLICATION_ID metadata must have a String value.", e3);
            }
        }
        super.attachInfo(context, providerInfo);
    }

    @Nullable
    private static Bundle zzh(Context context) {
        try {
            return Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            zzawo.zzc("Failed to load metadata: Package name not found.", e);
            return null;
        } catch (NullPointerException e2) {
            zzawo.zzc("Failed to load metadata: Null pointer exception.", e2);
            return null;
        }
    }
}
