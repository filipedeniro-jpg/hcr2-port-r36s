package com.google.android.gms.internal.firebase_remote_config;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzjy extends RuntimeException {
    private final List<String> zzxf;

    public zzjy(zziq zziqVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzxf = null;
    }
}
