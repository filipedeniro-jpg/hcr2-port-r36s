package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: AnimatedDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l implements t {
    AnimatedImageDrawable a;
    private t.a b;

    @Override // com.inmobi.ads.t
    public final void a(boolean z) {
    }

    @Override // com.inmobi.ads.t
    public final void e() {
    }

    @SuppressLint({"NewApi"})
    public l(String str) throws IOException {
        this.a = (AnimatedImageDrawable) ImageDecoder.decodeDrawable(ImageDecoder.createSource(new File(str)));
    }

    @Override // com.inmobi.ads.t
    @SuppressLint({"NewApi"})
    public final void a() {
        this.a.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: com.inmobi.ads.l.1
            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public final void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public final void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                l.this.a.start();
            }
        });
        this.a.start();
    }

    @Override // com.inmobi.ads.t
    public final int b() {
        return this.a.getIntrinsicWidth();
    }

    @Override // com.inmobi.ads.t
    public final int c() {
        return this.a.getIntrinsicHeight();
    }

    @Override // com.inmobi.ads.t
    public final void a(Canvas canvas, float f, float f2) {
        this.a.draw(canvas);
    }

    @Override // com.inmobi.ads.t
    public final boolean d() {
        return this.a.isRunning();
    }

    @Override // com.inmobi.ads.t
    public final void a(t.a aVar) {
        this.b = aVar;
    }
}
