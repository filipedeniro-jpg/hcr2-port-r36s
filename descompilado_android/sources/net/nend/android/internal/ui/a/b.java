package net.nend.android.internal.ui.a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: EndCardJavascriptInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends a {
    @JavascriptInterface
    public void isVersion402Later() {
    }

    @Override // net.nend.android.internal.ui.a.a
    @JavascriptInterface
    public /* bridge */ /* synthetic */ void viewSource(String str) {
        super.viewSource(str);
    }

    public b(BlockingQueue<d> blockingQueue, String str) {
        super(blockingQueue, str);
    }

    @JavascriptInterface
    public void onClickAd() {
        this.a.add(new d(d.a.CLICK_AD, this.b));
    }

    @JavascriptInterface
    public void onClickInformation() {
        this.a.add(d.a);
    }

    @JavascriptInterface
    public void onClickClose() {
        this.a.add(d.b);
    }
}
