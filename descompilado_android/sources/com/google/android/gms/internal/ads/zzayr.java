package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.gcm.Task;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public final class zzayr extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzayo {
    private static final float[] zzdzh = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private int height;
    private int width;
    private final float[] zzdys;
    private final zzaym zzdzi;
    private final float[] zzdzj;
    private final float[] zzdzk;
    private final float[] zzdzl;
    private final float[] zzdzm;
    private final float[] zzdzn;
    private final float[] zzdzo;
    private float zzdzp;
    private float zzdzq;
    private float zzdzr;
    private SurfaceTexture zzdzs;
    private SurfaceTexture zzdzt;
    private int zzdzu;
    private int zzdzv;
    private int zzdzw;
    private FloatBuffer zzdzx;
    private final CountDownLatch zzdzy;
    private final Object zzdzz;
    private EGL10 zzeaa;
    private EGLDisplay zzeab;
    private EGLContext zzeac;
    private EGLSurface zzead;
    private volatile boolean zzeae;
    private volatile boolean zzeaf;

    public zzayr(Context context) {
        super("SphericalVideoProcessor");
        this.zzdzx = ByteBuffer.allocateDirect(zzdzh.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzdzx.put(zzdzh).position(0);
        this.zzdys = new float[9];
        this.zzdzj = new float[9];
        this.zzdzk = new float[9];
        this.zzdzl = new float[9];
        this.zzdzm = new float[9];
        this.zzdzn = new float[9];
        this.zzdzo = new float[9];
        this.zzdzp = Float.NaN;
        this.zzdzi = new zzaym(context);
        this.zzdzi.zza(this);
        this.zzdzy = new CountDownLatch(1);
        this.zzdzz = new Object();
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.width = i;
        this.height = i2;
        this.zzdzt = surfaceTexture;
    }

    public final void zzl(int i, int i2) {
        synchronized (this.zzdzz) {
            this.width = i;
            this.height = i2;
            this.zzeae = true;
            this.zzdzz.notifyAll();
        }
    }

    public final void zzxd() {
        synchronized (this.zzdzz) {
            this.zzeaf = true;
            this.zzdzt = null;
            this.zzdzz.notifyAll();
        }
    }

    public final SurfaceTexture zzxe() {
        if (this.zzdzt == null) {
            return null;
        }
        try {
            this.zzdzy.await();
        } catch (InterruptedException unused) {
        }
        return this.zzdzs;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzdzw++;
        synchronized (this.zzdzz) {
            this.zzdzz.notifyAll();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzayo
    public final void zzsp() {
        synchronized (this.zzdzz) {
            this.zzdzz.notifyAll();
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:8:0x002c  */
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z;
        int iGlCreateProgram;
        if (this.zzdzt == null) {
            zzatm.zzes("SphericalVideoProcessor started with no output texture.");
            this.zzdzy.countDown();
            return;
        }
        this.zzeaa = (EGL10) EGLContext.getEGL();
        this.zzeab = this.zzeaa.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.zzeab != EGL10.EGL_NO_DISPLAY && this.zzeaa.eglInitialize(this.zzeab, new int[2])) {
            int[] iArr = new int[1];
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            EGLConfig eGLConfig = (!this.zzeaa.eglChooseConfig(this.zzeab, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) || iArr[0] <= 0) ? null : eGLConfigArr[0];
            if (eGLConfig == null) {
                z = false;
            } else {
                this.zzeac = this.zzeaa.eglCreateContext(this.zzeab, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                EGLContext eGLContext = this.zzeac;
                if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                    z = false;
                } else {
                    this.zzead = this.zzeaa.eglCreateWindowSurface(this.zzeab, eGLConfig, this.zzdzt, null);
                    EGLSurface eGLSurface = this.zzead;
                    if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                        z = false;
                    } else {
                        EGL10 egl10 = this.zzeaa;
                        EGLDisplay eGLDisplay = this.zzeab;
                        EGLSurface eGLSurface2 = this.zzead;
                        if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.zzeac)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                }
            }
        } else {
            z = false;
        }
        zzyi<String> zzyiVar = zzyt.zzcln;
        int iZzd = zzd(35633, !((String) zzuo.zzoj().zzd(zzyiVar)).equals(zzyiVar.zzpm()) ? (String) zzuo.zzoj().zzd(zzyiVar) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
        if (iZzd == 0) {
            iGlCreateProgram = 0;
        } else {
            zzyi<String> zzyiVar2 = zzyt.zzclo;
            int iZzd2 = zzd(35632, !((String) zzuo.zzoj().zzd(zzyiVar2)).equals(zzyiVar2.zzpm()) ? (String) zzuo.zzoj().zzd(zzyiVar2) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}");
            if (iZzd2 == 0) {
                iGlCreateProgram = 0;
            } else {
                iGlCreateProgram = GLES20.glCreateProgram();
                zzey("createProgram");
                if (iGlCreateProgram != 0) {
                    GLES20.glAttachShader(iGlCreateProgram, iZzd);
                    zzey("attachShader");
                    GLES20.glAttachShader(iGlCreateProgram, iZzd2);
                    zzey("attachShader");
                    GLES20.glLinkProgram(iGlCreateProgram);
                    zzey("linkProgram");
                    int[] iArr2 = new int[1];
                    GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr2, 0);
                    zzey("getProgramiv");
                    if (iArr2[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(iGlCreateProgram));
                        GLES20.glDeleteProgram(iGlCreateProgram);
                        zzey("deleteProgram");
                        iGlCreateProgram = 0;
                    } else {
                        GLES20.glValidateProgram(iGlCreateProgram);
                        zzey("validateProgram");
                    }
                }
            }
        }
        this.zzdzu = iGlCreateProgram;
        GLES20.glUseProgram(this.zzdzu);
        zzey("useProgram");
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.zzdzu, "aPosition");
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 3, 5126, false, 12, (Buffer) this.zzdzx);
        zzey("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        zzey("enableVertexAttribArray");
        int[] iArr3 = new int[1];
        GLES20.glGenTextures(1, iArr3, 0);
        zzey("genTextures");
        int i = iArr3[0];
        GLES20.glBindTexture(36197, i);
        zzey("bindTextures");
        GLES20.glTexParameteri(36197, Task.EXTRAS_LIMIT_BYTES, 9729);
        zzey("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzey("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzey("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzey("texParameteri");
        this.zzdzv = GLES20.glGetUniformLocation(this.zzdzu, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzdzv, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        boolean z2 = this.zzdzu != 0;
        if (!z || !z2) {
            String strValueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzeaa.eglGetError()));
            String strConcat = strValueOf.length() != 0 ? "EGL initialization failed: ".concat(strValueOf) : new String("EGL initialization failed: ");
            zzatm.zzes(strConcat);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(new Throwable(strConcat), "SphericalVideoProcessor.run.1");
            zzxf();
            this.zzdzy.countDown();
            return;
        }
        this.zzdzs = new SurfaceTexture(i);
        this.zzdzs.setOnFrameAvailableListener(this);
        this.zzdzy.countDown();
        this.zzdzi.start();
        try {
            try {
                this.zzeae = true;
                while (!this.zzeaf) {
                    while (this.zzdzw > 0) {
                        this.zzdzs.updateTexImage();
                        this.zzdzw--;
                    }
                    if (this.zzdzi.zza(this.zzdys)) {
                        if (Float.isNaN(this.zzdzp)) {
                            float[] fArr = this.zzdys;
                            float[] fArr2 = {0.0f, 1.0f, 0.0f};
                            float[] fArr3 = {(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[5] * fArr2[2]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[8] * fArr2[2])};
                            this.zzdzp = -(((float) Math.atan2(fArr3[1], fArr3[0])) - 1.5707964f);
                        }
                        zzb(this.zzdzn, this.zzdzp + this.zzdzq);
                    } else {
                        zza(this.zzdys, -1.5707964f);
                        zzb(this.zzdzn, this.zzdzq);
                    }
                    zza(this.zzdzj, 1.5707964f);
                    zza(this.zzdzk, this.zzdzn, this.zzdzj);
                    zza(this.zzdzl, this.zzdys, this.zzdzk);
                    zza(this.zzdzm, this.zzdzr);
                    zza(this.zzdzo, this.zzdzm, this.zzdzl);
                    GLES20.glUniformMatrix3fv(this.zzdzv, 1, false, this.zzdzo, 0);
                    GLES20.glDrawArrays(5, 0, 4);
                    zzey("drawArrays");
                    GLES20.glFinish();
                    this.zzeaa.eglSwapBuffers(this.zzeab, this.zzead);
                    if (this.zzeae) {
                        GLES20.glViewport(0, 0, this.width, this.height);
                        zzey("viewport");
                        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.zzdzu, "uFOVx");
                        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzdzu, "uFOVy");
                        if (this.width > this.height) {
                            GLES20.glUniform1f(iGlGetUniformLocation, 0.87266463f);
                            GLES20.glUniform1f(iGlGetUniformLocation2, (this.height * 0.87266463f) / this.width);
                        } else {
                            GLES20.glUniform1f(iGlGetUniformLocation, (this.width * 0.87266463f) / this.height);
                            GLES20.glUniform1f(iGlGetUniformLocation2, 0.87266463f);
                        }
                        this.zzeae = false;
                    }
                    try {
                        synchronized (this.zzdzz) {
                            try {
                                if (!this.zzeaf && !this.zzeae && this.zzdzw == 0) {
                                    this.zzdzz.wait();
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    } catch (InterruptedException unused) {
                    }
                }
                this.zzdzi.stop();
                this.zzdzs.setOnFrameAvailableListener(null);
                this.zzdzs = null;
                zzxf();
            } catch (Throwable th2) {
                this.zzdzi.stop();
                this.zzdzs.setOnFrameAvailableListener(null);
                this.zzdzs = null;
                zzxf();
                throw th2;
            }
        } catch (IllegalStateException unused2) {
            zzatm.zzeu("SphericalVideoProcessor halted unexpectedly.");
            this.zzdzi.stop();
            this.zzdzs.setOnFrameAvailableListener(null);
            this.zzdzs = null;
            zzxf();
        } catch (Throwable th3) {
            zzatm.zzc("SphericalVideoProcessor died.", th3);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th3, "SphericalVideoProcessor.run.2");
            this.zzdzi.stop();
            this.zzdzs.setOnFrameAvailableListener(null);
            this.zzdzs = null;
            zzxf();
        }
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        float f5;
        int i = this.width;
        int i2 = this.height;
        if (i > i2) {
            f3 = (f * 1.7453293f) / i;
            f4 = f2 * 1.7453293f;
            f5 = i;
        } else {
            f3 = (f * 1.7453293f) / i2;
            f4 = f2 * 1.7453293f;
            f5 = i2;
        }
        this.zzdzq -= f3;
        this.zzdzr -= f4 / f5;
        if (this.zzdzr < -1.5707964f) {
            this.zzdzr = -1.5707964f;
        }
        if (this.zzdzr > 1.5707964f) {
            this.zzdzr = 1.5707964f;
        }
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zzb(float[] fArr, float f) {
        double d = f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zzd(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        zzey("createShader");
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        zzey("shaderSource");
        GLES20.glCompileShader(iGlCreateShader);
        zzey("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        zzey("getShaderiv");
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        zzey("deleteShader");
        return 0;
    }

    @VisibleForTesting
    private final boolean zzxf() {
        EGLSurface eGLSurface = this.zzead;
        boolean zEglDestroyContext = false;
        if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
            zEglDestroyContext = this.zzeaa.eglDestroySurface(this.zzeab, this.zzead) | this.zzeaa.eglMakeCurrent(this.zzeab, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzead = null;
        }
        EGLContext eGLContext = this.zzeac;
        if (eGLContext != null) {
            zEglDestroyContext |= this.zzeaa.eglDestroyContext(this.zzeab, eGLContext);
            this.zzeac = null;
        }
        EGLDisplay eGLDisplay = this.zzeab;
        if (eGLDisplay == null) {
            return zEglDestroyContext;
        }
        boolean zEglTerminate = zEglDestroyContext | this.zzeaa.eglTerminate(eGLDisplay);
        this.zzeab = null;
        return zEglTerminate;
    }

    private static void zzey(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(iGlGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }
}
