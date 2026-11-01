package com.facebook.ads.redexgen.X;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BU implements View.OnClickListener {
    public final /* synthetic */ BT B;

    public BU(BT this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (TextUtils.isEmpty(this.B.F) || "about:blank".equals(this.B.F)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.B.F));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        this.B.getContext().startActivity(intent);
    }
}
