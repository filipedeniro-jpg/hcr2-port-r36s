package com.facebook.ads.redexgen.X;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum HY {
    UNKNOWN(GamesStatusCodes.STATUS_VIDEO_NOT_ACTIVE, "An unknown error has occurred."),
    DATABASE_SELECT(3001, "Failed to read from database."),
    DATABASE_INSERT(3002, "Failed to insert row into database."),
    DATABASE_UPDATE(3003, "Failed to update row in database."),
    DATABASE_DELETE(AuthApiStatusCodes.AUTH_TOKEN_ERROR, "Failed to delete row from database.");

    private final int B;
    private final String C;

    HY(int mCode, String mMessage) {
        this.B = mCode;
        this.C = mMessage;
    }

    public final int A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }
}
