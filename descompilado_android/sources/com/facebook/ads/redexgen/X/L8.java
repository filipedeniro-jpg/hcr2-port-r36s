package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L8 implements LA {
    public final Collection<String> B;

    private L8() {
        this.B = new ArrayList();
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void aF(String line) {
        this.B.add(line);
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void flush() {
    }

    public final String toString() {
        StringBuilder outputString = new StringBuilder();
        Iterator<String> it = this.B.iterator();
        while (it.hasNext()) {
            outputString.append(it.next());
            outputString.append('\n');
        }
        return outputString.toString();
    }
}
