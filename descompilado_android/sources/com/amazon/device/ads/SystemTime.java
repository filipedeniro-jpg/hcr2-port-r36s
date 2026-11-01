package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class SystemTime {
    SystemTime() {
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}
