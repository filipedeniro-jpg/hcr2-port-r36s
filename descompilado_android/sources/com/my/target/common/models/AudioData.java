package com.my.target.common.models;

import androidx.annotation.NonNull;
import com.my.target.ah;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioData extends ah<String> {
    private int bitrate;

    @NonNull
    public static AudioData newAudioData(@NonNull String str) {
        return new AudioData(str);
    }

    private AudioData(@NonNull String str) {
        super(str);
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int i) {
        this.bitrate = i;
    }
}
