package com.google.android.gms.internal.firebase_remote_config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfe implements FirebaseRemoteConfigValue {
    private final String value;
    private final int zzmc;

    zzfe(String str, int i) {
        this.value = str;
        this.zzmc = i;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final long asLong() {
        if (this.zzmc == 0) {
            return 0L;
        }
        String strTrim = asString().trim();
        try {
            return Long.valueOf(strTrim).longValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", strTrim, "long"), e);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final double asDouble() {
        if (this.zzmc == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        String strTrim = asString().trim();
        try {
            return Double.valueOf(strTrim).doubleValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", strTrim, "double"), e);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final String asString() {
        if (this.zzmc == 0) {
            return "";
        }
        String str = this.value;
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final byte[] asByteArray() {
        if (this.zzmc == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
        }
        return this.value.getBytes(zzew.zzln);
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final boolean asBoolean() throws IllegalArgumentException {
        if (this.zzmc == 0) {
            return false;
        }
        String strTrim = asString().trim();
        if (zzew.zzlo.matcher(strTrim).matches()) {
            return true;
        }
        if (zzew.zzlp.matcher(strTrim).matches()) {
            return false;
        }
        throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", strTrim, "boolean"));
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public final int getSource() {
        return this.zzmc;
    }
}
