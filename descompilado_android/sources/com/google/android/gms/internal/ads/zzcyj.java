package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzcyj implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final String packageName;
    private final HandlerThread zzdui = new HandlerThread("GassClient");

    @VisibleForTesting
    private zzcyx zzgmu;
    private final String zzgmv;
    private final LinkedBlockingQueue<zzbo.zza> zzgmw;

    public zzcyj(Context context, String str, String str2) {
        this.packageName = str;
        this.zzgmv = str2;
        this.zzdui.start();
        this.zzgmu = new zzcyx(context, this.zzdui.getLooper(), this, this);
        this.zzgmw = new LinkedBlockingQueue<>();
        this.zzgmu.checkAvailabilityAndConnect();
    }

    public final zzbo.zza zzdl(int i) {
        zzbo.zza zzaVarPoll;
        try {
            zzaVarPoll = this.zzgmw.poll(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zzaVarPoll = null;
        }
        return zzaVarPoll == null ? zzanq() : zzaVarPoll;
    }

    private final zzcze zzanp() {
        try {
            return this.zzgmu.zzanw();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        try {
            this.zzgmw.put(zzanq());
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zzcze zzczeVarZzanp = zzanp();
        try {
            if (zzczeVarZzanp != null) {
                try {
                    try {
                        this.zzgmw.put(zzczeVarZzanp.zza(new zzcza(this.packageName, this.zzgmv)).zzanx());
                    } catch (InterruptedException unused) {
                        zzakf();
                        this.zzdui.quit();
                    }
                } catch (Throwable unused2) {
                    this.zzgmw.put(zzanq());
                    zzakf();
                    this.zzdui.quit();
                }
            }
        } finally {
            zzakf();
            this.zzdui.quit();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzgmw.put(zzanq());
        } catch (InterruptedException unused) {
        }
    }

    private final void zzakf() {
        zzcyx zzcyxVar = this.zzgmu;
        if (zzcyxVar != null) {
            if (zzcyxVar.isConnected() || this.zzgmu.isConnecting()) {
                this.zzgmu.disconnect();
            }
        }
    }

    @VisibleForTesting
    private static zzbo.zza zzanq() {
        return (zzbo.zza) ((zzdqd) zzbo.zza.zzal().zzau(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID).zzazm());
    }
}
