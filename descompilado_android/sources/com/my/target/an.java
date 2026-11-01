package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: MrcStat.java */
/* JADX INFO: loaded from: classes2.dex */
public class an extends as {
    private float dq;
    private float duration;

    @NonNull
    public static an u(@NonNull String str) {
        return new an(aq.a.dL, str);
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public float W() {
        return this.dq;
    }

    public void b(float f) {
        this.dq = f;
    }

    protected an(@NonNull String str, @NonNull String str2) {
        super(str, str2);
        this.duration = -1.0f;
        this.dq = 0.0f;
    }
}
