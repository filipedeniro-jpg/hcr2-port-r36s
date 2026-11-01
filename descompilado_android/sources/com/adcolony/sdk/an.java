package com.adcolony.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class an {
    static final int a = 1;
    static final int b = 2;
    static final int c = 4;
    static final int d = 1;
    static final int e = 2;
    static final int f = 512;
    static int g = 1;
    static ByteBuffer h;
    static IntBuffer i;
    static BitmapFactory.Options j = new BitmapFactory.Options();
    static int[] k = new int[1];
    ap A;
    ap B;
    ap C;
    ap D;
    ap E;
    ap F;
    int l;
    int m;
    int n;
    int o;
    int q;
    int r;
    a u;
    int v;
    FloatBuffer w;
    FloatBuffer x;
    IntBuffer y;
    ArrayList<a> p = new ArrayList<>();
    boolean s = true;
    boolean t = true;
    av z = new av(this);

    an() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(4096);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.w = byteBufferAllocateDirect.asFloatBuffer();
        this.w.rewind();
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(4096);
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        this.x = byteBufferAllocateDirect2.asFloatBuffer();
        this.x.rewind();
        ByteBuffer byteBufferAllocateDirect3 = ByteBuffer.allocateDirect(Math.max(2048, 4194304));
        byteBufferAllocateDirect3.order(ByteOrder.nativeOrder());
        this.y = byteBufferAllocateDirect3.asIntBuffer();
        this.y.rewind();
    }

    void a(double d2, double d3, double d4, double d5, int i2) {
        int i3 = (i2 >> 24) & 255;
        int i4 = (i2 >> 16) & 255;
        int i5 = (i2 >> 8) & 255;
        int i6 = i2 & 255;
        if (i3 > 0) {
            this.t = false;
        }
        if (i3 < 255) {
            this.s = false;
        }
        this.w.put((float) d2);
        this.w.put((float) d3);
        this.x.put((float) d4);
        this.x.put((float) d5);
        this.y.put((((i6 * i3) / 255) << 16) | (i3 << 24) | (((i5 * i3) / 255) << 8) | ((i4 * i3) / 255));
        this.v++;
    }

    void a() {
        b((a) null);
        this.z.b();
        b();
    }

    void a(int i2, int i3, double d2, int i4) {
        if (i2 == 0) {
            return;
        }
        int i5 = 0;
        d();
        if ((i2 & 1) != 0) {
            i5 = 16384;
            GLES20.glClearColor(((i3 >> 16) & 255) / 255.0f, ((i3 >> 8) & 255) / 255.0f, (i3 & 255) / 255.0f, ((i3 >> 24) & 255) / 255.0f);
        }
        if ((i2 & 2) != 0) {
            i5 |= 256;
            GLES20.glClearDepthf((float) d2);
        }
        if ((i2 & 4) != 0) {
            i5 |= 1024;
            GLES20.glClearStencil(i4);
        }
        GLES20.glClear(i5);
    }

    void b() {
        d();
        GLES20.glDisable(3089);
    }

    a a(Bitmap bitmap) {
        d();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = 1;
        int i3 = 1;
        while (i3 < width) {
            i3 <<= 1;
        }
        while (i2 < height) {
            i2 <<= 1;
        }
        int i4 = i3 * i2 * 4;
        ByteBuffer byteBuffer = h;
        if (byteBuffer == null || byteBuffer.capacity() < i4) {
            h = ByteBuffer.allocateDirect(i4 >= 4194304 ? i4 : 4194304);
            h.order(ByteOrder.nativeOrder());
            i = h.asIntBuffer();
        }
        h.rewind();
        bitmap.copyPixelsToBuffer(h);
        bitmap.recycle();
        a aVar = new a();
        this.p.add(aVar);
        return aVar.a(i, width, height);
    }

    void a(a aVar) {
        this.p.remove(aVar);
        k[0] = aVar.b;
        GLES20.glDeleteTextures(1, k, 0);
    }

    void c() {
        d();
        b();
    }

    a a(int i2) {
        for (int i3 = 0; i3 < this.p.size(); i3++) {
            a aVar = this.p.get(i3);
            if (aVar.b == i2) {
                return aVar;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0026  */
    /* JADX WARN: Code duplicated, block: B:21:0x003d  */
    void d() {
        int i2;
        if (this.v == 0) {
            return;
        }
        this.z.g();
        int i3 = this.q;
        if ((i3 & 1) != 0) {
            int i4 = (i3 >> 8) & 15;
            int i5 = 771;
            if (i4 == 0) {
                i2 = 0;
            } else if (i4 == 1) {
                i2 = 1;
            } else if (i4 == 2) {
                i2 = 770;
            } else if (i4 != 3) {
                i2 = 1;
            } else {
                i2 = 771;
            }
            int i6 = (this.q >> 12) & 15;
            if (i6 == 0) {
                i5 = 0;
            } else if (i6 == 1) {
                i5 = 1;
            } else if (i6 == 2) {
                i5 = 770;
            } else if (i6 != 3) {
                i5 = 0;
            }
            GLES20.glBlendFunc(i2, i5);
            GLES20.glEnable(3042);
        } else {
            GLES20.glDisable(3042);
        }
        if (this.u != null) {
            GLES20.glEnable(3553);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.u.a);
            if ((this.q & 12) != 0) {
                GLES20.glTexParameterf(3553, 10242, 10497.0f);
                GLES20.glTexParameterf(3553, 10243, 10497.0f);
            } else {
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
            }
            if ((this.q & 16) != 0) {
                GLES20.glTexParameterf(3553, 10241, 9728.0f);
                GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9728.0f);
            } else {
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            }
            int i7 = this.q & 16711680;
            if (i7 == 65536) {
                this.C.a();
            } else if (i7 == 131072) {
                this.D.a();
            } else if (i7 == 196608) {
                if (this.s) {
                    this.E.a();
                } else if (this.t) {
                    this.B.a();
                } else {
                    this.F.a();
                }
            } else {
                this.B.a();
            }
        } else {
            GLES20.glDisable(3553);
            this.A.a();
        }
        int i8 = this.r;
        if (i8 == 1) {
            this.A.a();
            GLES20.glDrawArrays(1, 0, this.v);
        } else if (i8 == 2) {
            GLES20.glDrawArrays(4, 0, this.v);
        }
        this.v = 0;
        this.w.rewind();
        this.x.rewind();
        this.y.rewind();
        this.s = true;
        this.t = true;
    }

    a a(String str) {
        d();
        BitmapFactory.Options options = j;
        options.inScaled = false;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        if (bitmapDecodeFile == null) {
            System.out.println("Failed to load " + str);
            bitmapDecodeFile = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
        }
        return a(bitmapDecodeFile);
    }

    a a(InputStream inputStream) {
        d();
        BitmapFactory.Options options = j;
        options.inScaled = false;
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, options);
        if (bitmapDecodeStream == null) {
            Log.w("ADC3", "Failed to decode input stream.");
            bitmapDecodeStream = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888);
        }
        return a(bitmapDecodeStream);
    }

    void e() {
        System.out.println("ADCRenderer on_surface_created()");
        this.A = new ap.a(this);
        this.B = new ap.b(this);
        this.C = new ap.d(this);
        this.D = new ap.c(this);
        this.E = new ap.e(this);
        this.F = new ap.f(this);
    }

    void a(int i2, int i3) {
        System.out.println("ADCRenderer on_surface_changed " + i2 + "x" + i3);
        this.l = i2;
        this.m = i3;
        this.n = i2;
        this.o = i3;
        GLES20.glViewport(0, 0, i2, i3);
    }

    void a(int i2, int i3, int i4, int i5) {
        d();
        double d2 = this.l;
        double d3 = this.n;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        int i6 = this.m;
        double d5 = i6;
        double d6 = this.o;
        Double.isNaN(d5);
        Double.isNaN(d6);
        double d7 = d5 / d6;
        double d8 = i4;
        Double.isNaN(d8);
        int i7 = (int) (d8 * d4);
        double d9 = i5;
        Double.isNaN(d9);
        int i8 = (int) (d9 * d7);
        double d10 = i2;
        Double.isNaN(d10);
        int i9 = (int) (d10 * d4);
        double d11 = i3;
        Double.isNaN(d11);
        GLES20.glScissor(i9, i6 - (((int) (d11 * d7)) + i8), i7, i8);
        GLES20.glEnable(3089);
    }

    void f() {
        if (this.r == 1) {
            return;
        }
        d();
        this.r = 1;
    }

    void a(int i2, int i3, double d2) {
        this.n = i2;
        this.o = i3;
        av avVar = this.z;
        avVar.a(avVar.d.a(i2, i3, d2));
    }

    void b(int i2) {
        if (i2 == this.q) {
            return;
        }
        d();
        this.q = i2;
    }

    void b(int i2, int i3, int i4, int i5) {
        b(i2 | 2 | (i3 << 8) | (i4 << 12) | (i5 << 16));
    }

    void b(a aVar) {
        if (aVar == this.u) {
            return;
        }
        d();
        this.u = aVar;
    }

    void g() {
        if (this.r == 2) {
            return;
        }
        d();
        this.r = 2;
    }

    class a {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        double g;
        double h;

        a() {
            int i = an.g;
            an.g = i + 1;
            this.b = i;
            GLES20.glGenTextures(1, an.k, 0);
            this.a = an.k[0];
        }

        a a(IntBuffer intBuffer, int i, int i2) {
            int i3 = 1;
            int i4 = 1;
            while (i4 < i) {
                i4 <<= 1;
            }
            while (i3 < i2) {
                i3 <<= 1;
            }
            this.c = i;
            this.d = i2;
            this.e = i4;
            this.f = i3;
            double d = this.c;
            double d2 = this.e;
            Double.isNaN(d);
            Double.isNaN(d2);
            this.g = d / d2;
            double d3 = this.d;
            double d4 = this.f;
            Double.isNaN(d3);
            Double.isNaN(d4);
            this.h = d3 / d4;
            intBuffer.rewind();
            int i5 = i * i2;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                int i6 = intBuffer.get(i5);
                intBuffer.put(i5, ((i6 << 16) & 16711680) | ((-16711936) & i6) | ((i6 >> 16) & 255));
            }
            intBuffer.rewind();
            int i7 = this.c;
            int i8 = this.e;
            if (i7 < i8) {
                int i9 = this.d;
                int i10 = ((i9 - 1) * i8) + i7;
                int i11 = i7 * i9;
                int i12 = i8 - i7;
                while (true) {
                    i9--;
                    if (i9 < 0) {
                        break;
                    }
                    intBuffer.put(i10, intBuffer.get(i11 - 1));
                    int i13 = this.c;
                    while (true) {
                        i13--;
                        if (i13 >= 0) {
                            i10--;
                            i11--;
                            intBuffer.put(i10, intBuffer.get(i11));
                        }
                    }
                    i10 -= i12;
                }
            }
            intBuffer.rewind();
            int i14 = this.d;
            if (i14 < this.f) {
                int i15 = this.e;
                int i16 = i14 * i15;
                int i17 = i16 + i15;
                while (true) {
                    i15--;
                    if (i15 < 0) {
                        break;
                    }
                    i17--;
                    i16--;
                    intBuffer.put(i17, intBuffer.get(i16));
                }
            }
            GLES20.glBindTexture(3553, this.a);
            intBuffer.rewind();
            GLES20.glTexImage2D(3553, 0, 6408, this.e, this.f, 0, 6408, 5121, intBuffer);
            System.out.println("ADC3 Texture::set gl_texture_id:" + this.a + " texture_id:" + this.b + " w:" + i + " h:" + i2);
            return this;
        }
    }
}
