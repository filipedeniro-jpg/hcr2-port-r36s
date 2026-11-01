package com.facebook.ads.redexgen.X;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0539Gc implements InterfaceC0538Gb {
    public final /* synthetic */ C0542Gf B;
    public final /* synthetic */ ImageView C;
    public final /* synthetic */ KF D;

    public C0539Gc(C0542Gf this$0, ImageView imageView, KF kf) {
        this.B = this$0;
        this.C = imageView;
        this.D = kf;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0538Gb
    public final void GE(@Nullable Drawable loadedDrawable) {
        if (loadedDrawable != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.C.setBackground(loadedDrawable);
            } else {
                this.C.setBackgroundDrawable(loadedDrawable);
            }
        }
        this.D.R(loadedDrawable);
    }
}
