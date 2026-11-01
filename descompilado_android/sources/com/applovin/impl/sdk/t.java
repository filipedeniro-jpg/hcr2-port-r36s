package com.applovin.impl.sdk;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.animation.Animation;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
public class t {
    private final h a;
    private final n b;
    private final MaxAdView c;

    public t(MaxAdView maxAdView, h hVar) {
        this.a = hVar;
        this.b = hVar.w();
        this.c = maxAdView;
    }

    public long a(com.applovin.impl.mediation.b.b bVar) {
        long j;
        this.b.a("ViewabilityTracker", "Checking visibility...");
        if (this.c.isShown()) {
            j = 0;
        } else {
            this.b.d("ViewabilityTracker", "View is hidden");
            j = 2;
        }
        if (this.c.getAlpha() < bVar.o()) {
            this.b.d("ViewabilityTracker", "View is transparent");
            j |= 4;
        }
        Animation animation = this.c.getAnimation();
        if (animation != null && animation.hasStarted() && !animation.hasEnded()) {
            this.b.d("ViewabilityTracker", "View is animating");
            j |= 8;
        }
        if (this.c.getParent() == null) {
            this.b.d("ViewabilityTracker", "No parent view found");
            j |= 16;
        }
        int iPxToDp = AppLovinSdkUtils.pxToDp(this.c.getContext(), this.c.getWidth());
        if (iPxToDp < bVar.m()) {
            this.b.d("ViewabilityTracker", "View has width (" + iPxToDp + ") below threshold");
            j |= 32;
        }
        int iPxToDp2 = AppLovinSdkUtils.pxToDp(this.c.getContext(), this.c.getHeight());
        if (iPxToDp2 < bVar.n()) {
            this.b.d("ViewabilityTracker", "View has height (" + iPxToDp2 + ") below threshold");
            j |= 64;
        }
        Point pointA = com.applovin.impl.sdk.utils.e.a(this.c.getContext());
        Rect rect = new Rect(0, 0, pointA.x, pointA.y);
        int[] iArr = {-1, -1};
        this.c.getLocationOnScreen(iArr);
        Rect rect2 = new Rect(iArr[0], iArr[1], iArr[0] + this.c.getWidth(), iArr[1] + this.c.getHeight());
        if (!Rect.intersects(rect, rect2)) {
            this.b.d("ViewabilityTracker", "Rect (" + rect2 + ") outside of screen's bounds (" + rect + ")");
            j |= 128;
        }
        Activity activityA = this.a.V().a();
        if (activityA != null && !com.applovin.impl.sdk.utils.n.a(this.c, activityA)) {
            this.b.d("ViewabilityTracker", "View is not in top activity's view hierarchy");
            j |= 256;
        }
        this.b.a("ViewabilityTracker", "Returning flags: " + Long.toBinaryString(j));
        return j;
    }
}
