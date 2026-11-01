package com.facebook.ads.redexgen.X;

import java.util.Comparator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JJ implements Comparator<JE> {
    public final /* synthetic */ JM B;

    public JJ(JM this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final int compare(JE o1, JE o2) {
        return o1.name().compareTo(o2.name());
    }
}
