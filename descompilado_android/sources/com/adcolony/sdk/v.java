package com.adcolony.sdk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class v implements GLSurfaceView.Renderer {
    static BitmapFactory.Options a = new BitmapFactory.Options();
    static ByteBuffer b;
    u c;
    String d;
    int e;
    int f;
    boolean g;
    boolean h;
    c j;
    int k;
    int l;
    ArrayList<af> i = new ArrayList<>();
    ArrayList<a> m = new ArrayList<>();
    HashMap<Integer, a> n = new HashMap<>();

    void a(af afVar) {
    }

    v(u uVar, boolean z, String str) {
        System.out.println("AdColony new ADCGLViewRenderer");
        this.c = uVar;
        this.g = z;
        this.d = str;
        this.j = com.adcolony.sdk.a.a().m().b().get(str);
        this.e = uVar.b;
        this.f = this.j.c();
        this.j.n().add(com.adcolony.sdk.a.a("RenderView.create_image", new ah() { // from class: com.adcolony.sdk.v.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                v.this.a(afVar);
            }
        }, true));
        this.j.n().add(com.adcolony.sdk.a.a("RenderView.load_texture", new ah() { // from class: com.adcolony.sdk.v.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                v.this.b(afVar);
            }
        }, true));
        this.j.o().add("RenderView.create_image");
        this.j.o().add("RenderView.load_texture");
    }

    synchronized void a() {
        if (this.h) {
            return;
        }
        this.h = true;
        synchronized (ADCNative.lock) {
            ADCNative.nativeDeleteSceneController(this.f, this.e);
        }
    }

    protected void finalize() throws Throwable {
        a();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public synchronized void onDrawFrame(GL10 gl10) {
        com.adcolony.sdk.a.f();
        synchronized (ADCNative.lock) {
            if (this.h) {
                return;
            }
            b();
            ADCNative.nativeRender(this.f, this.e, this.k, this.l);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (ADCNative.lock) {
            if (this.h) {
                return;
            }
            ADCNative.nativeCreateSceneController(this.f, this.e);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.k = i;
        this.l = i2;
    }

    synchronized void b(af afVar) {
        this.i.add(afVar);
    }

    synchronized void b() {
        for (int i = 0; i < this.i.size(); i++) {
            af afVar = this.i.get(i);
            JSONObject jSONObjectC = afVar.c();
            a aVarA = null;
            if (jSONObjectC.has("pixels")) {
                String strB = y.b(jSONObjectC, "pixels");
                int[] iArr = new int[strB.length() / 4];
                int length = strB.length() - 4;
                int length2 = iArr.length;
                while (length >= 0) {
                    char cCharAt = strB.charAt(length);
                    char cCharAt2 = strB.charAt(length + 1);
                    char cCharAt3 = strB.charAt(length + 2);
                    char cCharAt4 = strB.charAt(length + 3);
                    length += 4;
                    length2--;
                    iArr[length2] = (cCharAt << 24) | (cCharAt2 << 16) | (cCharAt3 << '\b') | cCharAt4;
                }
                int iC = y.c(jSONObjectC, "width");
                int iC2 = y.c(jSONObjectC, "height");
                if (iC * iC2 == iArr.length) {
                    aVarA = a(y.c(jSONObjectC, "texture_id"), y.b(jSONObjectC, "filepath"), iArr, iC, iC2);
                }
            } else if (jSONObjectC.has("bytes")) {
                String strB2 = y.b(jSONObjectC, "bytes");
                byte[] bArr = new byte[strB2.length()];
                int length3 = strB2.length();
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        break;
                    } else {
                        bArr[length3] = (byte) strB2.charAt(length3);
                    }
                }
                aVarA = a(y.c(jSONObjectC, "texture_id"), y.b(jSONObjectC, "filepath"), bArr);
            } else if (jSONObjectC.has("filepath")) {
                aVarA = a(y.c(jSONObjectC, "texture_id"), y.b(jSONObjectC, "filepath"));
            }
            if (aVarA == null) {
                y.a(jSONObjectC, "success", false);
                return;
            }
            y.a(jSONObjectC, "success", aVarA.d);
            y.b(jSONObjectC, "image_width", aVarA.f);
            y.b(jSONObjectC, "image_height", aVarA.g);
            y.b(jSONObjectC, "texture_width", aVarA.h);
            y.b(jSONObjectC, "texture_height", aVarA.i);
            afVar.a(jSONObjectC).b();
        }
        this.i.clear();
    }

    a a(int i, String str) {
        a.inScaled = false;
        Activity activityC = com.adcolony.sdk.a.c();
        Bitmap bitmapDecodeFile = null;
        if (str.startsWith("file:///android_asset/") && activityC != null) {
            try {
                bitmapDecodeFile = BitmapFactory.decodeStream(activityC.getAssets().open(str.substring(22)), null, a);
            } catch (IOException e) {
                new aa.a().a(e.toString()).a(aa.f);
            }
        } else {
            bitmapDecodeFile = BitmapFactory.decodeFile(str, a);
        }
        if (bitmapDecodeFile == null) {
            new aa.a().a("Failed to load ").a(str).a(" - using white ").a("16x16 as placeholder.").a(aa.f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.eraseColor(-1);
            a aVarA = a(i, str, bitmapCreateBitmap);
            aVarA.d = false;
            return aVarA;
        }
        return a(i, str, bitmapDecodeFile);
    }

    a a(int i, String str, byte[] bArr) {
        a.inScaled = false;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (bitmapDecodeByteArray == null) {
            new aa.a().a("Failed to load ").a(str).a(" bytes - using ").a("white 16x16 as placeholder.").a(aa.f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.eraseColor(-1);
            a aVarA = a(i, str, bitmapCreateBitmap);
            aVarA.d = false;
            return aVarA;
        }
        return a(i, str, bitmapDecodeByteArray);
    }

    a a(int i, String str, int[] iArr, int i2, int i3) {
        return a(i, str, Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888));
    }

    a a(int i, String str, Bitmap bitmap) {
        a aVar = new a(i, str, bitmap.getWidth(), bitmap.getHeight());
        int i2 = aVar.h * aVar.i * 4;
        ByteBuffer byteBuffer = b;
        if (byteBuffer == null || byteBuffer.capacity() < i2) {
            if (i2 < 4194304) {
                i2 = 4194304;
            }
            b = ByteBuffer.allocateDirect(i2);
            b.order(ByteOrder.nativeOrder());
        }
        b.rewind();
        bitmap.copyPixelsToBuffer(b);
        this.m.add(aVar);
        this.n.put(Integer.valueOf(i), aVar);
        synchronized (ADCNative.lock) {
            ADCNative.nativeCreateTexture(this.f, this.e, i, str, b, aVar.f, aVar.g, aVar.h, aVar.i);
        }
        return aVar;
    }

    static class a {
        static int a = 1;
        int b;
        String c;
        int f;
        int g;
        int h;
        int i;
        boolean d = true;
        int e = this.e;
        int e = this.e;

        a(int i, String str, int i2, int i3) {
            this.b = i;
            this.c = str;
            this.f = i2;
            this.g = i3;
            this.h = 1;
            while (true) {
                int i4 = this.h;
                if (i4 >= i2) {
                    break;
                } else {
                    this.h = i4 << 1;
                }
            }
            this.i = 1;
            while (true) {
                int i5 = this.i;
                if (i5 >= i3) {
                    return;
                } else {
                    this.i = i5 << 1;
                }
            }
        }
    }
}
