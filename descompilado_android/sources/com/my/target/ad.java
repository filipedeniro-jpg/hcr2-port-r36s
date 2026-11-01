package com.my.target;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: MraidScreenMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public class ad {

    @NonNull
    private final Rect bS;

    @NonNull
    private final Rect bT;

    @NonNull
    private final Rect bU;

    @NonNull
    private final Rect bV;

    @NonNull
    private final Rect bW;

    @NonNull
    private final Rect bX;

    @NonNull
    private final Rect bY;

    @NonNull
    private final Rect bZ;

    @NonNull
    private final ck ca;

    public static ad d(@NonNull Context context) {
        return new ad(context);
    }

    @VisibleForTesting
    ad(@NonNull ck ckVar) {
        this.ca = ckVar;
        this.bS = new Rect();
        this.bT = new Rect();
        this.bU = new Rect();
        this.bV = new Rect();
        this.bW = new Rect();
        this.bX = new Rect();
        this.bY = new Rect();
        this.bZ = new Rect();
    }

    private ad(@NonNull Context context) {
        this(ck.x(context));
    }

    public void a(int i, int i2) {
        this.bS.set(0, 0, i, i2);
        a(this.bS, this.bT);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.bW.set(i, i2, i3, i4);
        a(this.bW, this.bX);
    }

    public void b(int i, int i2, int i3, int i4) {
        this.bU.set(i, i2, i3, i4);
        a(this.bU, this.bV);
    }

    public void c(int i, int i2, int i3, int i4) {
        this.bY.set(i, i2, i3, i4);
        a(this.bY, this.bZ);
    }

    @NonNull
    public Rect u() {
        return this.bV;
    }

    @NonNull
    public Rect v() {
        return this.bX;
    }

    @NonNull
    public Rect w() {
        return this.bZ;
    }

    @NonNull
    public Rect x() {
        return this.bT;
    }

    private void a(@NonNull Rect rect, @NonNull Rect rect2) {
        rect2.set(this.ca.n(rect.left), this.ca.n(rect.top), this.ca.n(rect.right), this.ca.n(rect.bottom));
    }
}
