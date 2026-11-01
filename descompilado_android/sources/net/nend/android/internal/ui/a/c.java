package net.nend.android.internal.ui.a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: HtmlOnPlayingJavascriptInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends a {
    @Override // net.nend.android.internal.ui.a.a
    @JavascriptInterface
    public /* bridge */ /* synthetic */ void viewSource(String str) {
        super.viewSource(str);
    }

    public c(BlockingQueue<d> blockingQueue, String str) {
        super(blockingQueue, str);
    }

    @JavascriptInterface
    public void setVideoRect(String str) {
        this.a.add(new d(d.a.VIDEO_RECT, this.b, str));
    }

    @JavascriptInterface
    public void onClickAd() {
        this.a.add(new d(d.a.CLICK_AD, this.b));
    }
}
