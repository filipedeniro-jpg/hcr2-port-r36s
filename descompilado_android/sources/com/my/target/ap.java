package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ProgressStat.java */
/* JADX INFO: loaded from: classes2.dex */
public class ap extends aq {
    public static final float ds = -1.0f;
    private float dt;
    private float value;

    @NonNull
    public static ap w(@NonNull String str) {
        return new ap(str);
    }

    private ap(@NonNull String str) {
        super(aq.a.dH, str);
        this.value = -1.0f;
        this.dt = -1.0f;
    }

    public float Y() {
        return this.value;
    }

    public void c(float f) {
        this.value = f;
    }

    public float Z() {
        return this.dt;
    }

    public void d(float f) {
        this.dt = f;
    }

    public String toString() {
        return "ProgressStat{value=" + this.value + ", pvalue=" + this.dt + '}';
    }
}
