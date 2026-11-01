package com.facebook.ads.redexgen.X;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class M4 implements Comparator<File> {
    private M4() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final int compare(File lhs, File rhs) {
        return C(lhs.lastModified(), rhs.lastModified());
    }

    private int C(long first, long second) {
        if (first < second) {
            return -1;
        }
        return first == second ? 0 : 1;
    }
}
