package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzazl implements zznc {
    private boolean isOpen;
    private Uri uri;
    private InputStream zzebl;
    private final zznc zzebm;

    @Nullable
    private final zznq<zznc> zzebn;
    private final zzazk zzebo;
    private final Context zzlk;

    public zzazl(Context context, zznc zzncVar, zznq<zznc> zznqVar, zzazk zzazkVar) {
        this.zzlk = context;
        this.zzebm = zzncVar;
        this.zzebn = zznqVar;
        this.zzebo = zzazkVar;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final void close() throws IOException {
        if (!this.isOpen) {
            throw new IOException("Attempt to close an already closed CacheDataSource.");
        }
        this.isOpen = false;
        this.uri = null;
        InputStream inputStream = this.zzebl;
        if (inputStream != null) {
            IOUtils.closeQuietly(inputStream);
            this.zzebl = null;
        } else {
            this.zzebm.close();
        }
        zznq<zznc> zznqVar = this.zzebn;
        if (zznqVar != null) {
            zznqVar.zze(this);
        }
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final long zza(zznh zznhVar) throws IOException {
        Long l;
        zznh zznhVar2 = zznhVar;
        if (this.isOpen) {
            throw new IOException("Attempt to open an already open CacheDataSource.");
        }
        boolean z = true;
        this.isOpen = z;
        this.uri = zznhVar2.uri;
        zznq<zznc> zznqVar = this.zzebn;
        if (zznqVar != null) {
            zznqVar.zza(this, zznhVar2);
        }
        zzrl zzrlVarZze = zzrl.zze(zznhVar2.uri);
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcpk)).booleanValue()) {
            zzrg zzrgVarZza = null;
            if (zzrlVarZze != null) {
                zzrlVarZze.zzbrf = zznhVar2.zzams;
                zzrgVarZza = com.google.android.gms.ads.internal.zzp.zzke().zza(zzrlVarZze);
            }
            if (zzrgVarZza != null && zzrgVarZza.zzmg()) {
                this.zzebl = zzrgVarZza.zzmh();
                return -1L;
            }
        } else if (zzrlVarZze != null) {
            zzrlVarZze.zzbrf = zznhVar2.zzams;
            if (zzrlVarZze.zzbre) {
                l = (Long) zzuo.zzoj().zzd(zzyt.zzcpm);
            } else {
                l = (Long) zzuo.zzoj().zzd(zzyt.zzcpl);
            }
            long jLongValue = l.longValue();
            long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
            com.google.android.gms.ads.internal.zzp.zzks();
            Future<InputStream> futureZza = zzrw.zza(this.zzlk, zzrlVarZze);
            z = false;
            try {
                this.zzebl = futureZza.get(jLongValue, TimeUnit.MILLISECONDS);
                return -1L;
            } catch (InterruptedException unused) {
                futureZza.cancel(z);
                Thread.currentThread().interrupt();
            } catch (ExecutionException | TimeoutException unused2) {
                futureZza.cancel(z);
            } finally {
                long jElapsedRealtime2 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
                this.zzebo.zzb(z, jElapsedRealtime2);
                StringBuilder sb = new StringBuilder(44);
                sb.append("Cache connection took ");
                sb.append(jElapsedRealtime2);
                sb.append("ms");
                zzatm.zzdy(sb.toString());
            }
        }
        if (zzrlVarZze != null) {
            zznhVar2 = new zznh(Uri.parse(zzrlVarZze.url), zznhVar2.zzbeq, zznhVar2.zzber, zznhVar2.zzams, zznhVar2.zzcb, zznhVar2.zzce, zznhVar2.flags);
        }
        return this.zzebm.zza(zznhVar2);
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (!this.isOpen) {
            throw new IOException("Attempt to read closed CacheDataSource.");
        }
        InputStream inputStream = this.zzebl;
        if (inputStream != null) {
            i3 = inputStream.read(bArr, i, i2);
        } else {
            i3 = this.zzebm.read(bArr, i, i2);
        }
        zznq<zznc> zznqVar = this.zzebn;
        if (zznqVar != null) {
            zznqVar.zzc(this, i3);
        }
        return i3;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final Uri getUri() {
        return this.uri;
    }
}
