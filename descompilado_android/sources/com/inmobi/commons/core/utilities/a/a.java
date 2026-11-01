package com.inmobi.commons.core.utilities.a;

/* JADX INFO: compiled from: EncryptionDao.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    com.inmobi.commons.core.d.c a = com.inmobi.commons.core.d.c.b("aes_key_store");

    public final void a(String str) {
        this.a.a("aes_public_key", str);
        this.a.a("last_generated_ts", System.currentTimeMillis() / 1000);
    }
}
