package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ViewabilityStat.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class as extends aq {
    static final float ds = -1.0f;
    private int dV;
    private float dt;
    private float value;

    as(@NonNull String str, @NonNull String str2) {
        super(str, str2);
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

    public int ag() {
        return this.dV;
    }

    public void f(int i) {
        this.dV = i;
    }
}
