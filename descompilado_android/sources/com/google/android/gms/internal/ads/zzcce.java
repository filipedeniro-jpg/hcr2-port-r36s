package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzcce extends Exception {
    private final int errorCode;

    public static int zzd(Throwable th) {
        if (th instanceof zzcce) {
            return ((zzcce) th).errorCode;
        }
        if (th instanceof zzauz) {
            return ((zzauz) th).getErrorCode();
        }
        return 0;
    }

    public zzcce(int i) {
        this.errorCode = i;
    }

    public zzcce(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public zzcce(String str, Throwable th, int i) {
        super(str, th);
        this.errorCode = 0;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
