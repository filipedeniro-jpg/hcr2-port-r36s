package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzhs {
    private int zzafm;
    protected AudioTrack zzahz;
    private boolean zzaju;
    private long zzajv;
    private long zzajw;
    private long zzajx;
    private long zzajy;
    private long zzajz;
    private long zzaka;

    private zzhs() {
    }

    public boolean zzff() {
        return false;
    }

    public void zza(AudioTrack audioTrack, boolean z) {
        this.zzahz = audioTrack;
        this.zzaju = z;
        this.zzajy = C.TIME_UNSET;
        this.zzajv = 0L;
        this.zzajw = 0L;
        this.zzajx = 0L;
        if (audioTrack != null) {
            this.zzafm = audioTrack.getSampleRate();
        }
    }

    public final void zzds(long j) {
        this.zzajz = zzfi();
        this.zzajy = SystemClock.elapsedRealtime() * 1000;
        this.zzaka = j;
        this.zzahz.stop();
    }

    public final void pause() {
        if (this.zzajy != C.TIME_UNSET) {
            return;
        }
        this.zzahz.pause();
    }

    public final long zzfi() {
        if (this.zzajy != C.TIME_UNSET) {
            return Math.min(this.zzaka, this.zzajz + ((((SystemClock.elapsedRealtime() * 1000) - this.zzajy) * ((long) this.zzafm)) / 1000000));
        }
        int playState = this.zzahz.getPlayState();
        if (playState == 1) {
            return 0L;
        }
        long playbackHeadPosition = 4294967295L & ((long) this.zzahz.getPlaybackHeadPosition());
        if (this.zzaju) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.zzajx = this.zzajv;
            }
            playbackHeadPosition += this.zzajx;
        }
        if (this.zzajv > playbackHeadPosition) {
            this.zzajw++;
        }
        this.zzajv = playbackHeadPosition;
        return playbackHeadPosition + (this.zzajw << 32);
    }

    public final long zzfj() {
        return (zzfi() * 1000000) / ((long) this.zzafm);
    }

    public long zzfg() {
        throw new UnsupportedOperationException();
    }

    public long zzfh() {
        throw new UnsupportedOperationException();
    }

    /* synthetic */ zzhs(zzhp zzhpVar) {
        this();
    }
}
