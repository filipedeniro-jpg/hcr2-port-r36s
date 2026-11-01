package com.google.android.gms.internal.firebase_remote_config;

import com.appsflyer.share.Constants;

/* JADX INFO: loaded from: classes2.dex */
enum zzak {
    PLUS('+', "", ",", false, true),
    HASH('#', "#", ",", false, true),
    DOT('.', ".", ".", false, false),
    FORWARD_SLASH('/', Constants.URL_PATH_DELIMITER, Constants.URL_PATH_DELIMITER, false, false),
    SEMI_COLON(';', ";", ";", true, false),
    QUERY('?', "?", com.ironsource.sdk.constants.Constants.RequestParameters.AMPERSAND, true, false),
    AMP('&', com.ironsource.sdk.constants.Constants.RequestParameters.AMPERSAND, com.ironsource.sdk.constants.Constants.RequestParameters.AMPERSAND, true, false),
    SIMPLE(null, "", ",", false, false);

    private final Character zzcl;
    private final String zzcm;
    private final String zzcn;
    private final boolean zzco;
    private final boolean zzcp;

    zzak(Character ch, String str, String str2, boolean z, boolean z2) {
        this.zzcl = ch;
        this.zzcm = (String) zzdt.checkNotNull(str);
        this.zzcn = (String) zzdt.checkNotNull(str2);
        this.zzco = z;
        this.zzcp = z2;
        if (ch != null) {
            zzal.zzcr.put(ch, this);
        }
    }

    final String zzak() {
        return this.zzcm;
    }

    final String zzal() {
        return this.zzcn;
    }

    final boolean zzam() {
        return this.zzco;
    }

    final int zzan() {
        return this.zzcl == null ? 0 : 1;
    }

    final String zzaa(String str) {
        if (this.zzcp) {
            return zzcs.zzaj(str);
        }
        return zzcs.zzah(str);
    }

    final boolean zzao() {
        return this.zzcp;
    }
}
