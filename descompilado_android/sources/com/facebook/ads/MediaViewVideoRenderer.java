package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.internal.api.AdComponentView;
import com.facebook.ads.internal.api.AdViewConstructorParams;
import com.facebook.ads.internal.api.MediaViewVideoRendererApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
@Keep
public abstract class MediaViewVideoRenderer extends AdComponentView {
    private MediaViewVideoRendererApi mMediaViewVideoRendererApi;

    @Nullable
    @Deprecated
    protected NativeAd nativeAd;

    @Deprecated
    protected VideoAutoplayBehavior videoAutoplayBehavior;

    @Override // android.view.ViewGroup
    public void addView(View view) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, int i2) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Deprecated
    public void destroy() {
    }

    public void onCompleted() {
    }

    public void onError() {
    }

    public void onPaused() {
    }

    public void onPlayed() {
    }

    public void onPrepared() {
    }

    public void onSeek() {
    }

    public void onSeekDisengaged() {
    }

    public void onSeekEngaged() {
    }

    public void onVolumeChanged() {
    }

    @Deprecated
    final void setListener(Object obj) {
    }

    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }

    public MediaViewVideoRenderer(Context context) {
        super(context);
        initialize(new AdViewConstructorParams(context));
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(new AdViewConstructorParams(context, attributeSet));
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(new AdViewConstructorParams(context, attributeSet, i));
    }

    @TargetApi(21)
    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize(new AdViewConstructorParams(context, attributeSet, i, i2));
    }

    private void initialize(AdViewConstructorParams adViewConstructorParams) {
        this.mMediaViewVideoRendererApi = DynamicLoaderFactory.makeLoader(adViewConstructorParams.getContext()).createMediaViewVideoRendererApi();
        attachAdComponentViewApi(this.mMediaViewVideoRendererApi);
        this.mMediaViewVideoRendererApi.initialize(adViewConstructorParams, this);
    }

    public MediaViewVideoRendererApi getMediaViewVideoRendererApi() {
        return this.mMediaViewVideoRendererApi;
    }

    @Deprecated
    public void setNativeAd(NativeAd nativeAd) {
        this.nativeAd = nativeAd;
        this.videoAutoplayBehavior = nativeAd.getVideoAutoplayBehavior();
    }

    @Deprecated
    public void unsetNativeAd() {
        this.nativeAd = null;
        this.videoAutoplayBehavior = VideoAutoplayBehavior.DEFAULT;
    }

    public final void play(VideoStartReason videoStartReason) {
        this.mMediaViewVideoRendererApi.play(videoStartReason);
    }

    public final void pause(boolean z) {
        this.mMediaViewVideoRendererApi.pause(z);
    }

    @IntRange(from = 0)
    public final int getCurrentTimeMs() {
        return this.mMediaViewVideoRendererApi.getCurrentTimeMs();
    }

    public final void engageSeek() {
        this.mMediaViewVideoRendererApi.engageSeek();
    }

    public final void disengageSeek(VideoStartReason videoStartReason) {
        this.mMediaViewVideoRendererApi.disengageSeek(videoStartReason);
    }

    public final void seekTo(@IntRange(from = 0) int i) {
        this.mMediaViewVideoRendererApi.seekTo(i);
    }

    @IntRange(from = 0)
    public final int getDuration() {
        return this.mMediaViewVideoRendererApi.getDuration();
    }

    public final void setVolume(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d) float f) {
        this.mMediaViewVideoRendererApi.setVolume(f);
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    public final float getVolume() {
        return this.mMediaViewVideoRendererApi.getVolume();
    }

    public final boolean shouldAutoplay() {
        return this.mMediaViewVideoRendererApi.shouldAutoplay();
    }

    @Deprecated
    final View getVideoView() {
        return this.mMediaViewVideoRendererApi.getVideoView();
    }
}
