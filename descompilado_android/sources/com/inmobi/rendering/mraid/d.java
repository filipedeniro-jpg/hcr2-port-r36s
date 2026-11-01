package com.inmobi.rendering.mraid;

/* JADX INFO: compiled from: MraidJsDao.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public com.inmobi.commons.core.d.c a = com.inmobi.commons.core.d.c.b("mraid_js_store");

    public final void a(String str) {
        this.a.a("mraid_js_string", str);
        this.a.a("last_updated_ts", System.currentTimeMillis() / 1000);
    }
}
