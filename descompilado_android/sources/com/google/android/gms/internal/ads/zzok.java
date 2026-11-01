package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(17)
public final class zzok extends Surface {
    private static boolean zzbgy;
    private static boolean zzbgz;
    private final boolean zzaxk;
    private final zzom zzbha;
    private boolean zzbhb;

    public static synchronized boolean zzc(Context context) {
        if (!zzbgz) {
            if (zzoh.SDK_INT >= 17) {
                boolean z = false;
                String strEglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                if (strEglQueryString != null && strEglQueryString.contains("EGL_EXT_protected_content")) {
                    if (!(zzoh.SDK_INT == 24 && (zzoh.MODEL.startsWith("SM-G950") || zzoh.MODEL.startsWith("SM-G955")) && !context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"))) {
                        z = true;
                    }
                }
                zzbgy = z;
            }
            zzbgz = true;
        }
        return zzbgy;
    }

    public static zzok zzc(Context context, boolean z) {
        if (zzoh.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
        zznt.checkState(!z || zzc(context));
        return new zzom().zzl(z);
    }

    private zzok(zzom zzomVar, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.zzbha = zzomVar;
        this.zzaxk = z;
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.zzbha) {
            if (!this.zzbhb) {
                this.zzbha.release();
                this.zzbhb = true;
            }
        }
    }
}
