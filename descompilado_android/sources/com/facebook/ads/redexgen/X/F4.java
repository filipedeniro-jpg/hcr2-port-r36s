package com.facebook.ads.redexgen.X;

import android.net.Uri;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.PopupMenu;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class F4 implements PopupMenu.OnMenuItemClickListener {
    public final /* synthetic */ C0510Ez B;
    public final /* synthetic */ String C;
    public final /* synthetic */ C01551i D;

    public F4(C0510Ez this$0, C01551i c01551i, String str) {
        this.B = this$0;
        this.D = c01551i;
        this.C = str;
    }

    @Override // android.widget.PopupMenu.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem item) {
        this.B.I = false;
        if (!TextUtils.isEmpty(this.D.A())) {
            I7.B(new I7(), this.B.getContext(), Uri.parse(this.D.A()), this.C);
            return true;
        }
        return true;
    }
}
