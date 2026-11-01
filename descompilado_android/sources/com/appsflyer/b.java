package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import com.facebook.internal.FacebookRequestErrorClassification;

/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private IntentFilter f99 = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    static final class a {

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        static final b f100 = new b();
    }

    b() {
    }

    @NonNull
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final c m96(Context context) {
        String str = null;
        float f = 0.0f;
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, this.f99);
            if (intentRegisterReceiver != null) {
                if (2 == intentRegisterReceiver.getIntExtra("status", -1)) {
                    int intExtra = intentRegisterReceiver.getIntExtra("plugged", -1);
                    if (intExtra == 1) {
                        str = "ac";
                    } else if (intExtra != 2) {
                        str = intExtra != 4 ? FacebookRequestErrorClassification.KEY_OTHER : "wireless";
                    } else {
                        str = "usb";
                    }
                } else {
                    str = "no";
                }
                int intExtra2 = intentRegisterReceiver.getIntExtra("level", -1);
                int intExtra3 = intentRegisterReceiver.getIntExtra("scale", -1);
                if (-1 != intExtra2 && -1 != intExtra3) {
                    f = (intExtra2 * 100.0f) / intExtra3;
                }
            }
        } catch (Throwable unused) {
        }
        return new c(f, str);
    }

    static final class c {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final String f101;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private final float f102;

        c(float f, String str) {
            this.f102 = f;
            this.f101 = str;
        }

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        final float m97() {
            return this.f102;
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final String m98() {
            return this.f101;
        }

        c() {
        }
    }
}
