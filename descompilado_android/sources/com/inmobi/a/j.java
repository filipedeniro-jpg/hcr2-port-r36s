package com.inmobi.a;

import com.facebook.share.internal.MessengerShareContentUtility;
import io.fabric.sdk.android.services.network.HttpRequest;

/* JADX INFO: compiled from: IceNetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends com.inmobi.commons.core.network.c {
    private static final String c = "j";
    int a;
    int b;

    j(String str, int i, int i2, com.inmobi.commons.core.utilities.uid.d dVar, k kVar) {
        super(HttpRequest.METHOD_POST, str, true, dVar);
        this.a = i;
        this.b = i2;
        this.o.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, kVar.a().toString());
    }
}
