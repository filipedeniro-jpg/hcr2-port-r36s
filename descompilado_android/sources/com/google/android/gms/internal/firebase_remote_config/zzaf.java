package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzaf extends IOException {
    private final int statusCode;
    private final transient zzw zzbc;
    private final String zzbv;
    private final String zzby;

    public zzaf(zzac zzacVar) {
        this(new zzae(zzacVar));
    }

    protected zzaf(zzae zzaeVar) {
        super(zzaeVar.message);
        this.statusCode = zzaeVar.statusCode;
        this.zzbv = zzaeVar.zzbv;
        this.zzbc = zzaeVar.zzbc;
        this.zzby = zzaeVar.zzby;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public static StringBuilder zzc(zzac zzacVar) {
        StringBuilder sb = new StringBuilder();
        int statusCode = zzacVar.getStatusCode();
        if (statusCode != 0) {
            sb.append(statusCode);
        }
        String statusMessage = zzacVar.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                sb.append(' ');
            }
            sb.append(statusMessage);
        }
        return sb;
    }
}
