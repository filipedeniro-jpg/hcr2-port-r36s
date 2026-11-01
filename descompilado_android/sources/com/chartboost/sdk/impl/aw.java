package com.chartboost.sdk.impl;

import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Libraries.CBLogging;

/* JADX INFO: loaded from: classes.dex */
public class aw {
    private final Handler a;

    public static Integer a(int i) {
        if (i < 1 || i > 9) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public aw(Handler handler) {
        this.a = handler;
    }

    public void a(int i, com.chartboost.sdk.Model.c cVar, Runnable runnable, com.chartboost.sdk.d dVar) {
        a(i, cVar, runnable, true, dVar);
    }

    public void a(int i, com.chartboost.sdk.Model.c cVar, Runnable runnable) {
        a(i, cVar, runnable, false);
    }

    private void a(final int i, final com.chartboost.sdk.Model.c cVar, final Runnable runnable, final boolean z, com.chartboost.sdk.d dVar) {
        if (i == 7) {
            if (runnable != null) {
                runnable.run();
            }
        } else {
            if (cVar == null || cVar.s == null) {
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
                return;
            }
            final View viewD = cVar.s.d();
            if (viewD == null) {
                dVar.d(cVar);
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
            } else {
                ViewTreeObserver viewTreeObserver = viewD.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.chartboost.sdk.impl.aw.1
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            viewD.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            aw.this.a(i, cVar, runnable, z);
                        }
                    });
                }
            }
        }
    }

    void a(int i, com.chartboost.sdk.Model.c cVar, Runnable runnable, boolean z) {
        ba baVar;
        ScaleAnimation scaleAnimation;
        TranslateAnimation translateAnimation;
        ba baVar2;
        ScaleAnimation scaleAnimation2;
        TranslateAnimation translateAnimation2;
        AlphaAnimation alphaAnimation;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (cVar == null || cVar.s == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        View viewD = cVar.s.d();
        if (viewD == null) {
            if (runnable != null) {
                runnable.run();
            }
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        if (cVar.n == 2 || cVar.n == 1) {
            viewD = cVar.s;
        }
        float width = viewD.getWidth();
        float height = viewD.getHeight();
        int i2 = cVar.p.b;
        switch (i) {
            case 1:
                if (z) {
                    baVar = new ba(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                } else {
                    baVar = new ba(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                }
                baVar.setDuration(500L);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                if (z) {
                    scaleAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    scaleAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                scaleAnimation.setDuration(500L);
                scaleAnimation.setFillAfter(true);
                animationSet.addAnimation(scaleAnimation);
                if (z) {
                    translateAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * 0.3f, 0.0f);
                } else {
                    translateAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * 0.3f);
                }
                translateAnimation.setDuration(500L);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                break;
            case 2:
                if (z) {
                    ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                    float f = 500L;
                    float f2 = 0.6f * f;
                    scaleAnimation3.setDuration(Math.round(f2));
                    scaleAnimation3.setStartOffset(0L);
                    scaleAnimation3.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation3);
                    ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation4.setDuration(Math.round(0.19999999f * f));
                    scaleAnimation4.setStartOffset(Math.round(f2));
                    scaleAnimation4.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation4);
                    ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation5.setDuration(Math.round(0.099999964f * f));
                    scaleAnimation5.setStartOffset(Math.round(f * 0.8f));
                    scaleAnimation5.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation5);
                } else {
                    ScaleAnimation scaleAnimation6 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation6.setDuration(500L);
                    scaleAnimation6.setStartOffset(0L);
                    scaleAnimation6.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation6);
                }
                break;
            case 3:
                if (z) {
                    baVar2 = new ba(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                } else {
                    baVar2 = new ba(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                }
                baVar2.setDuration(500L);
                baVar2.setFillAfter(true);
                animationSet.addAnimation(baVar2);
                if (z) {
                    scaleAnimation2 = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    scaleAnimation2 = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                scaleAnimation2.setDuration(500L);
                scaleAnimation2.setFillAfter(true);
                animationSet.addAnimation(scaleAnimation2);
                if (z) {
                    translateAnimation2 = new TranslateAnimation(width * 0.3f, 0.0f, (-height) * 0.4f, 0.0f);
                } else {
                    translateAnimation2 = new TranslateAnimation(0.0f, width * 0.3f, 0.0f, height);
                }
                translateAnimation2.setDuration(500L);
                translateAnimation2.setFillAfter(true);
                animationSet.addAnimation(translateAnimation2);
                break;
            case 4:
                TranslateAnimation translateAnimation3 = new TranslateAnimation(0.0f, 0.0f, z ? -height : 0.0f, z ? 0.0f : -height);
                translateAnimation3.setDuration(500L);
                translateAnimation3.setFillAfter(true);
                animationSet.addAnimation(translateAnimation3);
                break;
            case 5:
                float f3 = z ? height : 0.0f;
                if (z) {
                    height = 0.0f;
                }
                TranslateAnimation translateAnimation4 = new TranslateAnimation(0.0f, 0.0f, f3, height);
                translateAnimation4.setDuration(500L);
                translateAnimation4.setFillAfter(true);
                animationSet.addAnimation(translateAnimation4);
                break;
            case 6:
                if (z) {
                    alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                }
                alphaAnimation.setDuration(500L);
                alphaAnimation.setFillAfter(true);
                animationSet = new AnimationSet(true);
                animationSet.addAnimation(alphaAnimation);
                break;
            case 8:
                float f4 = z ? width : 0.0f;
                if (z) {
                    width = 0.0f;
                }
                TranslateAnimation translateAnimation5 = new TranslateAnimation(f4, width, 0.0f, 0.0f);
                translateAnimation5.setDuration(500L);
                translateAnimation5.setFillAfter(true);
                animationSet.addAnimation(translateAnimation5);
                break;
            case 9:
                TranslateAnimation translateAnimation6 = new TranslateAnimation(z ? -width : 0.0f, z ? 0.0f : -width, 0.0f, 0.0f);
                translateAnimation6.setDuration(500L);
                translateAnimation6.setFillAfter(true);
                animationSet.addAnimation(translateAnimation6);
                break;
        }
        if (i != 7) {
            if (runnable != null) {
                this.a.postDelayed(runnable, 500L);
            }
            viewD.startAnimation(animationSet);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void a(boolean z, View view, com.chartboost.sdk.Model.a aVar) {
        int i = aVar.b;
        a(z, view, 500L);
    }

    public void a(boolean z, View view, long j) {
        view.clearAnimation();
        if (z) {
            view.setVisibility(0);
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }
}
