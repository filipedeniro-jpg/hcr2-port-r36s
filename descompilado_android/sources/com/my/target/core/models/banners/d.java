package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.my.target.ai;

/* JADX INFO: compiled from: ViewSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    private boolean A;
    private boolean B;

    @NonNull
    private String v;
    private boolean x;
    private boolean y;
    private boolean z;
    private int backgroundColor = -1;
    private int C = -3806472;
    private int D = -16755546;
    private int E = -1;
    private int F = -10066330;
    private int G = -5000269;
    private int H = ViewCompat.MEASURED_STATE_MASK;
    private int I = -10066330;
    private int J = -7829368;
    private int K = -7829368;
    private int L = -16732432;
    private int M = -16746839;
    private int N = -1;
    private boolean w = true;

    public d() {
        this.v = ai.a.da;
        this.v = ai.a.da;
    }

    @NonNull
    public final String l() {
        return this.v;
    }

    public final void a(@NonNull String str) {
        this.v = str;
    }

    public final boolean m() {
        return this.w;
    }

    public final void e(boolean z) {
        this.w = z;
    }

    public final boolean n() {
        return this.x;
    }

    public final void f(boolean z) {
        this.x = z;
    }

    public final boolean o() {
        return this.y;
    }

    public final void g(boolean z) {
        this.y = z;
    }

    public final boolean s() {
        return this.z;
    }

    public final void h(boolean z) {
        this.z = z;
    }

    public final boolean t() {
        return this.A;
    }

    public final void j(boolean z) {
        this.A = z;
    }

    public final boolean u() {
        return this.B;
    }

    public final void k(boolean z) {
        this.B = z;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final int v() {
        return this.C;
    }

    public final void a(int i) {
        this.C = i;
    }

    public final int getTitleColor() {
        return this.D;
    }

    public final void setTitleColor(int i) {
        this.D = i;
    }

    public final int w() {
        return this.E;
    }

    public final void b(int i) {
        this.E = i;
    }

    public final int x() {
        return this.F;
    }

    public final void c(int i) {
        this.F = i;
    }

    public final int y() {
        return this.G;
    }

    public final void d(int i) {
        this.G = i;
    }

    public final int z() {
        return this.H;
    }

    public final void e(int i) {
        this.H = i;
    }

    public final int A() {
        return this.I;
    }

    public final void f(int i) {
        this.I = i;
    }

    public final int B() {
        return this.J;
    }

    public final void g(int i) {
        this.J = i;
    }

    public final int C() {
        return this.K;
    }

    public final void h(int i) {
        this.K = i;
    }

    public final int getCtaButtonColor() {
        return this.L;
    }

    public final void setCtaButtonColor(int i) {
        this.L = i;
    }

    public final int getCtaButtonTouchColor() {
        return this.M;
    }

    public final void setCtaButtonTouchColor(int i) {
        this.M = i;
    }

    public final int getCtaButtonTextColor() {
        return this.N;
    }

    public final void setCtaButtonTextColor(int i) {
        this.N = i;
    }

    public final void b(@NonNull String str) {
        if (com.my.target.b.a.b.equals(str)) {
            this.D = ViewCompat.MEASURED_STATE_MASK;
        }
    }
}
