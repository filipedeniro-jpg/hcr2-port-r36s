package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzom extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener, Handler.Callback {
    private Handler handler;
    private final int[] zzbhc;
    private SurfaceTexture zzbhd;
    private Error zzbhe;
    private RuntimeException zzbhf;
    private zzok zzbhg;

    public zzom() {
        super("dummySurface");
        this.zzbhc = new int[1];
    }

    public final zzok zzl(boolean z) {
        boolean z2;
        start();
        this.handler = new Handler(getLooper(), this);
        synchronized (this) {
            z2 = false;
            this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
            while (this.zzbhg == null && this.zzbhf == null && this.zzbhe == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    z2 = true;
                }
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        RuntimeException runtimeException = this.zzbhf;
        if (runtimeException != null) {
            throw runtimeException;
        }
        Error error = this.zzbhe;
        if (error != null) {
            throw error;
        }
        return this.zzbhg;
    }

    public final void release() {
        this.handler.sendEmptyMessage(3);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.handler.sendEmptyMessage(2);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int[] iArr;
        int[] iArr2;
        int i = message.what;
        try {
            if (i == 1) {
                try {
                    boolean z = message.arg1 != 0;
                    EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
                    zznt.checkState(eGLDisplayEglGetDisplay != null, "eglGetDisplay failed");
                    int[] iArr3 = new int[2];
                    zznt.checkState(EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr3, 0, iArr3, 1), "eglInitialize failed");
                    EGLConfig[] eGLConfigArr = new EGLConfig[1];
                    int[] iArr4 = new int[1];
                    zznt.checkState(EGL14.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr4, 0) && iArr4[0] > 0 && eGLConfigArr[0] != null, "eglChooseConfig failed");
                    EGLConfig eGLConfig = eGLConfigArr[0];
                    if (z) {
                        iArr = new int[]{12440, 2, 12992, 1, 12344};
                    } else {
                        iArr = new int[]{12440, 2, 12344};
                    }
                    EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(eGLDisplayEglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
                    zznt.checkState(eGLContextEglCreateContext != null, "eglCreateContext failed");
                    if (z) {
                        iArr2 = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
                    } else {
                        iArr2 = new int[]{12375, 1, 12374, 1, 12344};
                    }
                    EGLSurface eGLSurfaceEglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplayEglGetDisplay, eGLConfig, iArr2, 0);
                    zznt.checkState(eGLSurfaceEglCreatePbufferSurface != null, "eglCreatePbufferSurface failed");
                    zznt.checkState(EGL14.eglMakeCurrent(eGLDisplayEglGetDisplay, eGLSurfaceEglCreatePbufferSurface, eGLSurfaceEglCreatePbufferSurface, eGLContextEglCreateContext), "eglMakeCurrent failed");
                    GLES20.glGenTextures(1, this.zzbhc, 0);
                    this.zzbhd = new SurfaceTexture(this.zzbhc[0]);
                    this.zzbhd.setOnFrameAvailableListener(this);
                    this.zzbhg = new zzok(this, this.zzbhd, z);
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e) {
                    Log.e("DummySurface", "Failed to initialize dummy surface", e);
                    this.zzbhe = e;
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e2) {
                    Log.e("DummySurface", "Failed to initialize dummy surface", e2);
                    this.zzbhf = e2;
                    synchronized (this) {
                        notify();
                    }
                }
                return true;
            }
            if (i == 2) {
                this.zzbhd.updateTexImage();
                return true;
            }
            try {
                if (i != 3) {
                    return true;
                }
                try {
                    this.zzbhd.release();
                    this.zzbhg = null;
                    this.zzbhd = null;
                    GLES20.glDeleteTextures(1, this.zzbhc, 0);
                    quit();
                    return true;
                } catch (Throwable th) {
                    this.zzbhg = null;
                    this.zzbhd = null;
                    GLES20.glDeleteTextures(1, this.zzbhc, 0);
                    throw th;
                }
            } catch (Throwable th2) {
                Log.e("DummySurface", "Failed to release dummy surface", th2);
            } finally {
                quit();
            }
        } catch (Throwable th3) {
            synchronized (this) {
                notify();
                throw th3;
            }
        }
    }
}
