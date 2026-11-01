package com.google.android.gms.internal.drive;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmw extends RuntimeException {
    private final List<String> zzvq;

    public zzmw(zzlq zzlqVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzvq = null;
    }
}
