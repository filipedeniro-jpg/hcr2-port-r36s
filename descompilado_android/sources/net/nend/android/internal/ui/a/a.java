package net.nend.android.internal.ui.a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: CheckHtmlBridgeInterface.java */
/* JADX INFO: loaded from: classes2.dex */
class a {
    final BlockingQueue<d> a;
    final String b;

    a(BlockingQueue<d> blockingQueue, String str) {
        this.a = blockingQueue;
        this.b = str;
    }

    @JavascriptInterface
    public void viewSource(String str) {
        this.a.add(new d(d.a.VIEW_SOURCE, this.b, str));
    }
}
