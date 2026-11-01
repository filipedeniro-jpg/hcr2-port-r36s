package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.ads.zzno;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"HandlerLeak"})
final class zznp<T extends zzno> extends Handler implements Runnable {
    private volatile boolean zzads;
    private final T zzbfp;
    private final zznm<T> zzbfq;
    public final int zzbfr;
    private final long zzbfs;
    private IOException zzbft;
    private int zzbfu;
    private volatile Thread zzbfv;
    private final /* synthetic */ zznn zzbfw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zznp(zznn zznnVar, Looper looper, T t, zznm<T> zznmVar, int i, long j) {
        super(looper);
        this.zzbfw = zznnVar;
        this.zzbfp = t;
        this.zzbfq = zznmVar;
        this.zzbfr = i;
        this.zzbfs = j;
    }

    public final void zzbb(int i) throws IOException {
        IOException iOException = this.zzbft;
        if (iOException != null && this.zzbfu > i) {
            throw iOException;
        }
    }

    public final void zzee(long j) {
        zznt.checkState(this.zzbfw.zzbfn == null);
        this.zzbfw.zzbfn = this;
        if (j > 0) {
            sendEmptyMessageDelayed(0, j);
        } else {
            execute();
        }
    }

    public final void zzk(boolean z) {
        this.zzads = z;
        this.zzbft = null;
        if (hasMessages(0)) {
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
            }
        } else {
            this.zzbfp.cancelLoad();
            if (this.zzbfv != null) {
                this.zzbfv.interrupt();
            }
        }
        if (z) {
            finish();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbfq.zza((zzno) this.zzbfp, jElapsedRealtime, jElapsedRealtime - this.zzbfs, true);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzbfv = Thread.currentThread();
            if (!this.zzbfp.zzhg()) {
                String strValueOf = String.valueOf(this.zzbfp.getClass().getSimpleName());
                zzoe.beginSection(strValueOf.length() != 0 ? "load:".concat(strValueOf) : new String("load:"));
                try {
                    this.zzbfp.zzhh();
                    zzoe.endSection();
                } catch (Throwable th) {
                    zzoe.endSection();
                    throw th;
                }
            }
            if (this.zzads) {
                return;
            }
            sendEmptyMessage(2);
        } catch (IOException e) {
            if (this.zzads) {
                return;
            }
            obtainMessage(3, e).sendToTarget();
        } catch (Exception e2) {
            Log.e("LoadTask", "Unexpected exception loading stream", e2);
            if (this.zzads) {
                return;
            }
            obtainMessage(3, new zznr(e2)).sendToTarget();
        } catch (OutOfMemoryError e3) {
            Log.e("LoadTask", "OutOfMemory error loading stream", e3);
            if (this.zzads) {
                return;
            }
            obtainMessage(3, new zznr(e3)).sendToTarget();
        } catch (Error e4) {
            Log.e("LoadTask", "Unexpected error loading stream", e4);
            if (!this.zzads) {
                obtainMessage(4, e4).sendToTarget();
            }
            throw e4;
        } catch (InterruptedException unused) {
            zznt.checkState(this.zzbfp.zzhg());
            if (this.zzads) {
                return;
            }
            sendEmptyMessage(2);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (this.zzads) {
            return;
        }
        if (message.what == 0) {
            execute();
            return;
        }
        if (message.what == 4) {
            throw ((Error) message.obj);
        }
        finish();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime - this.zzbfs;
        if (this.zzbfp.zzhg()) {
            this.zzbfq.zza((zzno) this.zzbfp, jElapsedRealtime, j, false);
            return;
        }
        int i = message.what;
        if (i == 1) {
            this.zzbfq.zza((zzno) this.zzbfp, jElapsedRealtime, j, false);
            return;
        }
        if (i == 2) {
            this.zzbfq.zza(this.zzbfp, jElapsedRealtime, j);
            return;
        }
        if (i != 3) {
            return;
        }
        this.zzbft = (IOException) message.obj;
        int iZza = this.zzbfq.zza(this.zzbfp, jElapsedRealtime, j, this.zzbft);
        if (iZza == 3) {
            this.zzbfw.zzbfo = this.zzbft;
        } else if (iZza != 2) {
            this.zzbfu = iZza == 1 ? 1 : this.zzbfu + 1;
            zzee(Math.min((this.zzbfu - 1) * 1000, 5000));
        }
    }

    private final void execute() {
        this.zzbft = null;
        this.zzbfw.zzbfm.execute(this.zzbfw.zzbfn);
    }

    private final void finish() {
        this.zzbfw.zzbfn = null;
    }
}
