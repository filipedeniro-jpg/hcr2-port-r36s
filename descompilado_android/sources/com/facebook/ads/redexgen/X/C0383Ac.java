package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ac, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0383Ac implements InterfaceC0382Ab {
    public final /* synthetic */ C0384Ad B;
    public final /* synthetic */ C0385Ae C;

    public C0383Ac(C0384Ad this$0, C0385Ae c0385Ae) {
        this.B = this$0;
        this.C = c0385Ae;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0382Ab
    public final boolean XF(int virtualViewId, int action, Bundle arguments) {
        return this.C.E(virtualViewId, action, arguments);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0382Ab
    public final Object kB(int virtualViewId) {
        AT atA = this.C.A(virtualViewId);
        if (atA == null) {
            return null;
        }
        return atA.B();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0382Ab
    public final List<Object> pB(String text, int virtualViewId) {
        List<Object> infos = this.C.B(text, virtualViewId);
        if (infos == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((AT) infos.get(i)).B());
        }
        return arrayList;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0382Ab
    public final Object qB(int focus) {
        AT atC = this.C.C(focus);
        if (atC == null) {
            return null;
        }
        return atC.B();
    }
}
