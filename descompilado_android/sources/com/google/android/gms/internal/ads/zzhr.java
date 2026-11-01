package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(19)
final class zzhr extends zzhs {
    private final AudioTimestamp zzajq;
    private long zzajr;
    private long zzajs;
    private long zzajt;

    public zzhr() {
        super(null);
        this.zzajq = new AudioTimestamp();
    }

    @Override // com.google.android.gms.internal.ads.zzhs
    public final void zza(AudioTrack audioTrack, boolean z) {
        super.zza(audioTrack, z);
        this.zzajr = 0L;
        this.zzajs = 0L;
        this.zzajt = 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzhs
    public final boolean zzff() {
        boolean timestamp = this.zzahz.getTimestamp(this.zzajq);
        if (timestamp) {
            long j = this.zzajq.framePosition;
            if (this.zzajs > j) {
                this.zzajr++;
            }
            this.zzajs = j;
            this.zzajt = j + (this.zzajr << 32);
        }
        return timestamp;
    }

    @Override // com.google.android.gms.internal.ads.zzhs
    public final long zzfg() {
        return this.zzajq.nanoTime;
    }

    @Override // com.google.android.gms.internal.ads.zzhs
    public final long zzfh() {
        return this.zzajt;
    }
}
