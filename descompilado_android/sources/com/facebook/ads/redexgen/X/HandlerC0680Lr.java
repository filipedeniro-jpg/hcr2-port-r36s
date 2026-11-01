package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HandlerC0680Lr extends Handler implements InterfaceC0665Lc {
    private final List<InterfaceC0665Lc> B;
    private final String C;

    public HandlerC0680Lr(String url, List<InterfaceC0665Lc> list) {
        super(Looper.getMainLooper());
        this.C = url;
        this.B = list;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0665Lc
    public final void cD(File file, String url, int percentsAvailable) {
        Message messageObtainMessage = obtainMessage();
        messageObtainMessage.arg1 = percentsAvailable;
        messageObtainMessage.obj = file;
        sendMessage(messageObtainMessage);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        Iterator<InterfaceC0665Lc> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().cD((File) msg.obj, this.C, msg.arg1);
        }
    }
}
