package com.inmobi.ads;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: AnimationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {
    private static final String c = "n";
    List<a> a = new ArrayList();
    boolean b = false;

    /* JADX INFO: compiled from: AnimationManager.java */
    class a {
        Animator a;
        long b;
        boolean c;

        a(Animator animator) {
            this.a = animator;
        }
    }

    final List<a> a(final View view, ak akVar) {
        LinkedList linkedList = new LinkedList();
        try {
            float fC = NativeViewFactory.c(akVar.c.c.x);
            float fC2 = NativeViewFactory.c(akVar.c.d.x);
            if (fC != fC2) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat((int) fC, (int) fC2);
                final NativeContainerLayout.a aVar = (NativeContainerLayout.a) view.getLayoutParams();
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.inmobi.ads.n.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar.a = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar);
                        view.requestLayout();
                    }
                });
                linkedList.add(a(valueAnimatorOfFloat, akVar));
            }
            float fC3 = NativeViewFactory.c(akVar.c.c.y);
            float fC4 = NativeViewFactory.c(akVar.c.d.y);
            if (fC3 != fC4) {
                ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat((int) fC3, (int) fC4);
                final NativeContainerLayout.a aVar2 = (NativeContainerLayout.a) view.getLayoutParams();
                valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.inmobi.ads.n.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        aVar2.b = ((Float) valueAnimator.getAnimatedValue()).intValue();
                        view.setLayoutParams(aVar2);
                        view.requestLayout();
                    }
                });
                linkedList.add(a(valueAnimatorOfFloat2, akVar));
            }
            float fC5 = NativeViewFactory.c(akVar.c.a.x);
            float fC6 = NativeViewFactory.c(akVar.c.b.x);
            if (fC5 != fC6) {
                linkedList.add(a(a(view, "scaleX", fC5, fC6), akVar));
            }
            float fC7 = NativeViewFactory.c(akVar.c.a.y);
            float fC8 = NativeViewFactory.c(akVar.c.b.y);
            if (fC7 != fC8) {
                linkedList.add(a(a(view, "scaleY", fC7, fC8), akVar));
            }
        } catch (Exception unused) {
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        return linkedList;
    }

    private static Animator a(View view, String str, float f, float f2) {
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        return ObjectAnimator.ofFloat(view, str, f2 / f);
    }

    final void a(@Nullable List<a> list) {
        if (list == null) {
            return;
        }
        for (a aVar : list) {
            if (!aVar.c) {
                ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                valueAnimator.setCurrentPlayTime(aVar.b);
                valueAnimator.start();
            }
            if (!this.a.contains(aVar)) {
                this.a.add(aVar);
            }
        }
    }

    private a a(Animator animator, ak akVar) {
        animator.setDuration(0L);
        animator.setStartDelay(0L);
        ba baVarG = akVar.c.g();
        if (baVarG != null) {
            ba.a aVar = baVarG.a;
            ba.a aVar2 = baVarG.b;
            if (aVar2 != null) {
                animator.setDuration(aVar2.a() * 1000);
            }
            if (aVar != null) {
                animator.setStartDelay(aVar.a() * 1000);
            }
        }
        return new a(animator);
    }
}
