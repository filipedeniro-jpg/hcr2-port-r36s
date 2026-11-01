package com.facebook.ads.redexgen.X;

import android.transition.Transition;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JD implements Transition.TransitionListener {
    public final /* synthetic */ View B;

    public JD(View view) {
        this.B = view;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition trans) {
        J6.M(this.B);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
    }
}
