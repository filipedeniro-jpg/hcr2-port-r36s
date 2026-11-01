package com.applovin.impl.adview;

import android.net.Uri;
import android.os.Bundle;
import com.my.target.bj;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class o extends j {
    private static final String COUNTDOWN_IDENTIFIER_PROGRESS_TRACKING = "PROGRESS_TRACKING";
    private static final String TAG = "InterstitialActivity";
    private final Set<com.applovin.impl.a.g> videoProgressTrackers = new HashSet();

    private com.applovin.impl.a.a getVastAd() {
        if (this.currentAd instanceof com.applovin.impl.a.a) {
            return (com.applovin.impl.a.a) this.currentAd;
        }
        return null;
    }

    private void maybeFireRemainingCompletionTrackers() {
        if (!isFullyWatched() || this.videoProgressTrackers.isEmpty()) {
            return;
        }
        this.logger.c(TAG, "Firing " + this.videoProgressTrackers.size() + " un-fired video progress trackers when video was completed.");
        maybeFireTrackers(this.videoProgressTrackers);
    }

    private void maybeFireTrackers(com.applovin.impl.a.a.c cVar) {
        maybeFireTrackers(cVar, com.applovin.impl.a.d.UNSPECIFIED);
    }

    private void maybeFireTrackers(com.applovin.impl.a.a.c cVar, com.applovin.impl.a.d dVar) {
        maybeFireTrackers(cVar, "", dVar);
    }

    private void maybeFireTrackers(com.applovin.impl.a.a.c cVar, String str) {
        maybeFireTrackers(cVar, str, com.applovin.impl.a.d.UNSPECIFIED);
    }

    private void maybeFireTrackers(com.applovin.impl.a.a.c cVar, String str, com.applovin.impl.a.d dVar) {
        if (isVastAd()) {
            maybeFireTrackers(((com.applovin.impl.a.a) this.currentAd).a(cVar, str), dVar);
        }
    }

    private void maybeFireTrackers(Set<com.applovin.impl.a.g> set) {
        maybeFireTrackers(set, com.applovin.impl.a.d.UNSPECIFIED);
    }

    private void maybeFireTrackers(Set<com.applovin.impl.a.g> set, com.applovin.impl.a.d dVar) {
        if (!isVastAd() || set == null || set.isEmpty()) {
            return;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(this.videoView.getCurrentPosition());
        com.applovin.impl.a.k kVarC = getVastAd().c();
        Uri uriA = kVarC != null ? kVarC.a() : null;
        this.logger.a(TAG, "Firing " + set.size() + " tracker(s): " + set);
        com.applovin.impl.a.i.a(set, seconds, uriA, dVar, this.sdk);
    }

    @Override // com.applovin.impl.adview.j
    public void clickThroughFromVideo() {
        super.clickThroughFromVideo();
        maybeFireTrackers(com.applovin.impl.a.a.c.VIDEO_CLICK);
    }

    @Override // com.applovin.impl.adview.j, com.applovin.impl.adview.h, android.content.DialogInterface
    public void dismiss() {
        if (isVastAd()) {
            maybeFireTrackers(com.applovin.impl.a.a.c.VIDEO, "close");
            maybeFireTrackers(com.applovin.impl.a.a.c.COMPANION, "close");
        }
        super.dismiss();
    }

    public void handleCountdownStep() {
        if (isVastAd()) {
            long seconds = ((long) this.computedLengthSeconds) - TimeUnit.MILLISECONDS.toSeconds(this.videoView.getDuration() - this.videoView.getCurrentPosition());
            HashSet hashSet = new HashSet();
            for (com.applovin.impl.a.g gVar : new HashSet(this.videoProgressTrackers)) {
                if (gVar.a(seconds, getVideoPercentViewed())) {
                    hashSet.add(gVar);
                    this.videoProgressTrackers.remove(gVar);
                }
            }
            maybeFireTrackers(hashSet);
        }
    }

    @Override // com.applovin.impl.adview.j
    public void handleMediaError() {
        maybeFireTrackers(com.applovin.impl.a.a.c.ERROR, com.applovin.impl.a.d.MEDIA_FILE_ERROR);
        super.handleMediaError();
    }

    @Override // com.applovin.impl.adview.j, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isVastAd()) {
            this.videoProgressTrackers.addAll(getVastAd().a(com.applovin.impl.a.a.c.VIDEO, com.applovin.impl.a.h.a));
            maybeFireTrackers(com.applovin.impl.a.a.c.IMPRESSION);
            maybeFireTrackers(com.applovin.impl.a.a.c.VIDEO, "creativeView");
        }
    }

    @Override // com.applovin.impl.adview.j
    public void playVideo() {
        this.countdownManager.a(COUNTDOWN_IDENTIFIER_PROGRESS_TRACKING, ((Long) this.sdk.a(com.applovin.impl.sdk.b.b.eN)).longValue(), new g.a() { // from class: com.applovin.impl.adview.o.1
            @Override // com.applovin.impl.adview.g.a
            public void a() {
                o.this.handleCountdownStep();
            }

            @Override // com.applovin.impl.adview.g.a
            public boolean b() {
                return o.this.shouldContinueFullLengthVideoCountdown();
            }
        });
        super.playVideo();
    }

    @Override // com.applovin.impl.adview.j
    public void showPoststitial() {
        if (isVastAd()) {
            maybeFireRemainingCompletionTrackers();
            if (!com.applovin.impl.a.i.c(getVastAd())) {
                dismiss();
                return;
            } else if (this.poststitialWasDisplayed) {
                return;
            } else {
                maybeFireTrackers(com.applovin.impl.a.a.c.COMPANION, "creativeView");
            }
        }
        super.showPoststitial();
    }

    @Override // com.applovin.impl.adview.j
    public void skipVideo() {
        maybeFireTrackers(com.applovin.impl.a.a.c.VIDEO, "skip");
        super.skipVideo();
    }

    @Override // com.applovin.impl.adview.j
    public void toggleMute() {
        com.applovin.impl.a.a.c cVar;
        String str;
        super.toggleMute();
        if (this.videoMuted) {
            cVar = com.applovin.impl.a.a.c.VIDEO;
            str = bj.gv;
        } else {
            cVar = com.applovin.impl.a.a.c.VIDEO;
            str = bj.gw;
        }
        maybeFireTrackers(cVar, str);
    }
}
