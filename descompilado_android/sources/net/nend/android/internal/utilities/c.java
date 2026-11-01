package net.nend.android.internal.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: IdGenerator.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    static final /* synthetic */ boolean a = !c.class.desiredAssertionStatus();

    public static String a(Context context) {
        try {
            return (String) g.a().a(new g.d(context)).get();
        } catch (InterruptedException e) {
            j.a("Failed to get the Advertising ID", e);
            return "";
        } catch (ExecutionException e2) {
            j.a("Failed to get the Advertising ID", e2);
            return "";
        }
    }

    static String b(Context context) {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null && !advertisingIdInfo.isLimitAdTrackingEnabled()) {
                return advertisingIdInfo.getId();
            }
            j.a("LimitAdTrackingEnabled");
            return "";
        } catch (GooglePlayServicesNotAvailableException e) {
            j.a("Failed to get the Advertising ID", e);
            return "";
        } catch (GooglePlayServicesRepairableException e2) {
            j.a("Failed to get the Advertising ID", e2);
            return "";
        } catch (IOException e3) {
            j.a("Failed to get the Advertising ID", e3);
            return "";
        }
    }

    public static String c(Context context) {
        if (!a && context == null) {
            throw new AssertionError();
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("NENDUUID", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strA = o.a(UUID.randomUUID().toString());
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        editorEdit.putString("NENDUUID", strA);
        editorEdit.commit();
        return strA;
    }
}
