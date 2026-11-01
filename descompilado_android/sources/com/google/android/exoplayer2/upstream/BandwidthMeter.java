package com.google.android.exoplayer2.upstream;

/* JADX INFO: loaded from: classes.dex */
public interface BandwidthMeter {

    public interface EventListener {
        void onBandwidthSample(int i, long j, long j2);
    }

    long getBitrateEstimate();
}
