package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvu {
    public static ParcelFileDescriptor zze(final InputStream inputStream) throws IOException {
        ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArrCreatePipe[0];
        final ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptorArrCreatePipe[1];
        zzawx.zzdvx.execute(new Runnable(inputStream, parcelFileDescriptor2) { // from class: com.google.android.gms.internal.ads.zzcvx
            private final InputStream zzgkf;
            private final ParcelFileDescriptor zzgkg;

            {
                this.zzgkf = inputStream;
                this.zzgkg = parcelFileDescriptor2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzcvu.zza(this.zzgkf, this.zzgkg);
            }
        });
        return parcelFileDescriptor;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0026 A[Catch: all -> 0x002a, Throwable -> 0x002c, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0009, B:21:0x0029, B:20:0x0026, B:19:0x0022), top: B:31:0x0001, outer: #6 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x001d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static final /* synthetic */ void zza(InputStream inputStream, ParcelFileDescriptor parcelFileDescriptor) {
        Throwable th;
        Throwable th2;
        try {
            try {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
                try {
                    IOUtils.copyStream(inputStream, autoCloseOutputStream);
                    autoCloseOutputStream.close();
                    if (inputStream != null) {
                        zza((Throwable) null, inputStream);
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        th = th3;
                        th2 = th4;
                        if (th != null) {
                            autoCloseOutputStream.close();
                            throw th2;
                        }
                        autoCloseOutputStream.close();
                        throw th2;
                    }
                }
            } catch (Throwable th5) {
                if (inputStream != null) {
                    zza((Throwable) null, inputStream);
                }
                throw th5;
            }
        } catch (IOException unused) {
        }
    }

    private static /* synthetic */ void zza(Throwable th, InputStream inputStream) throws IOException {
        if (th == null) {
            inputStream.close();
            return;
        }
        try {
            inputStream.close();
        } catch (Throwable th2) {
            zzdof.zza(th, th2);
        }
    }
}
