package com.my.target;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: MyTargetMediaSourceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public class cq {
    @NonNull
    public static MediaSource a(@NonNull VideoData videoData, @NonNull Context context) {
        String data = videoData.getData();
        if (data != null) {
            return a(Uri.parse(data), context);
        }
        return a(Uri.parse(videoData.getUrl()), context);
    }

    @NonNull
    public static MediaSource a(@NonNull Uri uri, @NonNull Context context) {
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "myTarget"));
        if (Util.inferContentType(uri) == 2) {
            return new HlsMediaSource.Factory(new DefaultHlsDataSourceFactory(defaultDataSourceFactory)).createMediaSource(uri);
        }
        return new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(uri);
    }
}
