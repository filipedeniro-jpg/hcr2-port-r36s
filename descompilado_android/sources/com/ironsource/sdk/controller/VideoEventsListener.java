package com.ironsource.sdk.controller;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoEventsListener {
    void onVideoEnded();

    void onVideoPaused();

    void onVideoResumed();

    void onVideoStarted();

    void onVideoStopped();
}
