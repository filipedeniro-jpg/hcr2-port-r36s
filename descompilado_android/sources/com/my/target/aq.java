package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: Stat.java */
/* JADX INFO: loaded from: classes2.dex */
public class aq {

    @NonNull
    private final String type;

    @NonNull
    private final String url;

    /* JADX INFO: compiled from: Stat.java */
    public interface a {
        public static final String ERROR = "error";
        public static final String dA = "fullscreenOff";
        public static final String dB = "impression";
        public static final String dC = "volumeOn";
        public static final String dD = "volumeOff";
        public static final String dE = "rewind";
        public static final String dF = "click";
        public static final String dG = "deeplinkClick";
        public static final String dH = "playheadReachedValue";
        public static final String dI = "playheadViewabilityValue";
        public static final String dJ = "playheadTimerValue";
        public static final String dK = "ovvStat";
        public static final String dL = "mrcStat";
        public static final String dM = "creativeView";
        public static final String dN = "skip";
        public static final String dO = "serviceRequested";
        public static final String dP = "serviceAnswerEmpty";
        public static final String du = "playbackStarted";
        public static final String dv = "playbackPaused";
        public static final String dw = "playbackResumed";
        public static final String dx = "playbackStopped";
        public static final String dy = "closedByUser";
        public static final String dz = "fullscreenOn";
    }

    @NonNull
    public static aq b(@NonNull String str, @NonNull String str2) {
        return new aq(str, str2);
    }

    protected aq(@NonNull String str, @NonNull String str2) {
        this.type = str;
        this.url = str2;
    }

    @NonNull
    public String getType() {
        return this.type;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }
}
