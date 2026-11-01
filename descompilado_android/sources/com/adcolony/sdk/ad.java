package com.adcolony.sdk;

/* JADX INFO: loaded from: classes.dex */
class ad {
    static float[] a = new float[16];
    static ad b = new ad();
    double[] c;
    boolean d;

    ad() {
        this.c = new double[16];
        b();
    }

    ad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.c = new double[16];
        b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
    }

    ad a() {
        ad adVar = new ad();
        adVar.b(this);
        return adVar;
    }

    ad a(ad adVar) {
        if (adVar.d) {
            return this;
        }
        double[] dArr = adVar.c;
        return a(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6], dArr[7], dArr[8], dArr[9], dArr[10], dArr[11], dArr[12], dArr[13], dArr[14], dArr[15]);
    }

    ad a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        return a(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, this);
    }

    ad a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, ad adVar) {
        if (this.d) {
            return adVar.b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
        }
        double[] dArr = this.c;
        double d17 = dArr[0];
        double d18 = dArr[1];
        double d19 = dArr[2];
        double d20 = dArr[3];
        double d21 = dArr[4];
        double d22 = dArr[5];
        double d23 = dArr[6];
        double d24 = dArr[7];
        double d25 = dArr[8];
        double d26 = dArr[9];
        double d27 = dArr[10];
        double d28 = dArr[11];
        double d29 = dArr[12];
        double d30 = dArr[13];
        double d31 = dArr[14];
        double d32 = dArr[15];
        double[] dArr2 = adVar.c;
        dArr2[0] = (d17 * d) + (d18 * d5) + (d19 * d9) + (d20 * d13);
        dArr2[1] = (d17 * d2) + (d18 * d6) + (d19 * d10) + (d20 * d14);
        dArr2[2] = (d17 * d3) + (d18 * d7) + (d19 * d11) + (d20 * d15);
        dArr2[3] = (d17 * d4) + (d18 * d8) + (d19 * d12) + (d20 * d16);
        dArr2[4] = (d21 * d) + (d22 * d5) + (d23 * d9) + (d24 * d13);
        dArr2[5] = (d21 * d2) + (d22 * d6) + (d23 * d10) + (d24 * d14);
        dArr2[6] = (d21 * d3) + (d22 * d7) + (d23 * d11) + (d24 * d15);
        dArr2[7] = (d21 * d4) + (d22 * d8) + (d23 * d12) + (d24 * d16);
        dArr2[8] = (d25 * d) + (d26 * d5) + (d27 * d9) + (d28 * d13);
        dArr2[9] = (d25 * d2) + (d26 * d6) + (d27 * d10) + (d28 * d14);
        dArr2[10] = (d25 * d3) + (d26 * d7) + (d27 * d11) + (d28 * d15);
        dArr2[11] = (d25 * d4) + (d26 * d8) + (d27 * d12) + (d28 * d16);
        dArr2[12] = (d29 * d) + (d30 * d5) + (d31 * d9) + (d32 * d13);
        dArr2[13] = (d29 * d2) + (d30 * d6) + (d31 * d10) + (d32 * d14);
        dArr2[14] = (d29 * d3) + (d30 * d7) + (d31 * d11) + (d32 * d15);
        dArr2[15] = (d29 * d4) + (d30 * d8) + (d31 * d12) + (d32 * d16);
        this.d = false;
        return adVar;
    }

    ad a(ad adVar, ad adVar2) {
        if (adVar.d) {
            return adVar2.b(this);
        }
        double[] dArr = adVar.c;
        return a(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6], dArr[7], dArr[8], dArr[9], dArr[10], dArr[11], dArr[12], dArr[13], dArr[14], dArr[15], adVar2);
    }

    ad a(double d) {
        b.b();
        double dCos = Math.cos(d);
        double dSin = Math.sin(d);
        ad adVar = b;
        double[] dArr = adVar.c;
        dArr[0] = dCos;
        dArr[1] = dSin;
        dArr[4] = -dSin;
        dArr[5] = dCos;
        adVar.d = false;
        return a(adVar);
    }

    ad b(double d) {
        return a((d * 3.141592653589793d) / 180.0d);
    }

    ad a(double d, double d2, double d3) {
        b.b();
        ad adVar = b;
        double[] dArr = adVar.c;
        dArr[0] = d;
        dArr[5] = d2;
        dArr[10] = d3;
        adVar.d = false;
        return a(adVar);
    }

    ad b(ad adVar) {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = adVar.c[i];
        }
        this.d = adVar.d;
        return this;
    }

    ad b(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.d = false;
        double[] dArr = this.c;
        dArr[0] = d;
        dArr[1] = d2;
        dArr[2] = d3;
        dArr[3] = d4;
        dArr[4] = d5;
        dArr[5] = d6;
        dArr[6] = d7;
        dArr[7] = d8;
        dArr[8] = d9;
        dArr[9] = d10;
        dArr[10] = d11;
        dArr[11] = d12;
        dArr[12] = d13;
        dArr[13] = d14;
        dArr[14] = d15;
        dArr[15] = d16;
        return this;
    }

    ad b() {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = 0.0d;
        }
        double[] dArr = this.c;
        dArr[0] = 1.0d;
        dArr[5] = 1.0d;
        dArr[10] = 1.0d;
        dArr[15] = 1.0d;
        this.d = true;
        return this;
    }

    ad a(int i, int i2, double d) {
        b();
        double[] dArr = this.c;
        double d2 = i;
        Double.isNaN(d2);
        dArr[0] = 2.0d / d2;
        double d3 = i2;
        Double.isNaN(d3);
        dArr[5] = (-2.0d) / d3;
        dArr[10] = (-2.0d) / d;
        dArr[12] = -1.0d;
        dArr[13] = 1.0d;
        dArr[14] = -1.0d;
        this.d = false;
        return this;
    }

    ad b(double d, double d2, double d3) {
        b.b();
        ad adVar = b;
        double[] dArr = adVar.c;
        dArr[12] = d;
        dArr[13] = d2;
        dArr[14] = d3;
        adVar.d = false;
        return a(adVar);
    }

    ad c(double d) {
        for (int i = 15; i >= 0; i--) {
            this.c[i] = d;
        }
        this.d = false;
        return this;
    }

    float[] c() {
        return a(a);
    }

    float[] a(float[] fArr) {
        for (int i = 15; i >= 0; i--) {
            fArr[i] = (float) this.c[i];
        }
        return fArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.c[0]);
        sb.append(' ');
        sb.append(this.c[1]);
        sb.append(' ');
        sb.append(this.c[2]);
        sb.append(' ');
        sb.append(this.c[3]);
        sb.append('\n');
        sb.append(this.c[4]);
        sb.append(' ');
        sb.append(this.c[5]);
        sb.append(' ');
        sb.append(this.c[6]);
        sb.append(' ');
        sb.append(this.c[7]);
        sb.append('\n');
        sb.append(this.c[8]);
        sb.append(' ');
        sb.append(this.c[9]);
        sb.append(' ');
        sb.append(this.c[10]);
        sb.append(' ');
        sb.append(this.c[11]);
        sb.append('\n');
        sb.append(this.c[12]);
        sb.append(' ');
        sb.append(this.c[13]);
        sb.append(' ');
        sb.append(this.c[14]);
        sb.append(' ');
        sb.append(this.c[15]);
        sb.append('\n');
        return sb.toString();
    }
}
