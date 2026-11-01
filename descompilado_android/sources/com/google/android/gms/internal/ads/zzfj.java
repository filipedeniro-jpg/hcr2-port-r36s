package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzfj {
    private static final String TAG = "zzfj";
    private final String className;
    private final String zzaaj;
    private final Class<?>[] zzaam;
    private final zzdx zzvo;
    private final int zzaak = 2;
    private volatile Method zzaal = null;
    private CountDownLatch zzaan = new CountDownLatch(1);

    public zzfj(zzdx zzdxVar, String str, String str2, Class<?>... clsArr) {
        this.zzvo = zzdxVar;
        this.className = str;
        this.zzaaj = str2;
        this.zzaam = clsArr;
        this.zzvo.zzcd().submit(new zzfi(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcx() {
        try {
            Class clsLoadClass = this.zzvo.zzce().loadClass(zzb(this.zzvo.zzcg(), this.className));
            if (clsLoadClass == null) {
                this.zzaan.countDown();
                return;
            }
            this.zzaal = clsLoadClass.getMethod(zzb(this.zzvo.zzcg(), this.zzaaj), this.zzaam);
            if (this.zzaal == null) {
                this.zzaan.countDown();
            } else {
                this.zzaan.countDown();
            }
        } catch (zzdk unused) {
            this.zzaan.countDown();
        } catch (UnsupportedEncodingException unused2) {
            this.zzaan.countDown();
        } catch (ClassNotFoundException unused3) {
            this.zzaan.countDown();
        } catch (NoSuchMethodException unused4) {
            this.zzaan.countDown();
        } catch (NullPointerException unused5) {
            this.zzaan.countDown();
        } catch (Throwable th) {
            this.zzaan.countDown();
            throw th;
        }
    }

    private final String zzb(byte[] bArr, String str) throws zzdk, UnsupportedEncodingException {
        return new String(this.zzvo.zzcf().zza(bArr, str), "UTF-8");
    }

    public final Method zzcy() {
        if (this.zzaal != null) {
            return this.zzaal;
        }
        try {
            if (this.zzaan.await(2L, TimeUnit.SECONDS)) {
                return this.zzaal;
            }
            return null;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
