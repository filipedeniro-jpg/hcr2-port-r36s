package com.inmobi.a;

import io.fabric.sdk.android.services.network.HttpRequest;

/* JADX INFO: compiled from: CarbGetListNetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends com.inmobi.commons.core.network.c {
    private int a;
    private int b;

    b(String str, int i, int i2, com.inmobi.commons.core.utilities.uid.d dVar) {
        super(HttpRequest.METHOD_POST, str, true, dVar);
        this.a = i;
        this.b = i2;
    }
}
