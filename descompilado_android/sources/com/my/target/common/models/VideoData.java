package com.my.target.common.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.my.target.ah;
import com.my.target.g;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class VideoData extends ah<String> {
    public static final String M3U8 = ".m3u8";
    private int bitrate;
    private final boolean cacheable;

    @Nullable
    public static VideoData chooseBest(@NonNull List<VideoData> list, int i) {
        VideoData videoData = null;
        int i2 = 0;
        for (VideoData videoData2 : list) {
            int height = videoData2.getHeight();
            if (videoData == null || ((height <= i && i2 > i) || ((height <= i && height > i2) || (height > i && height < i2)))) {
                videoData = videoData2;
                i2 = height;
            }
        }
        g.a("Accepted videoData quality = " + i2 + TtmlNode.TAG_P);
        return videoData;
    }

    @NonNull
    public static VideoData newVideoData(@NonNull String str, int i, int i2) {
        return new VideoData(str, i, i2);
    }

    private VideoData(@NonNull String str, int i, int i2) {
        super(str);
        this.width = i;
        this.height = i2;
        this.cacheable = !this.url.endsWith(M3U8);
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int i) {
        this.bitrate = i;
    }

    public boolean isCacheable() {
        return this.cacheable;
    }
}
