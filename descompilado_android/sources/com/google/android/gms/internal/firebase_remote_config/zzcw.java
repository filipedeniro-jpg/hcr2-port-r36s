package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzcw extends ThreadLocal<char[]> {
    zzcw() {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ char[] initialValue() {
        return new char[1024];
    }
}
