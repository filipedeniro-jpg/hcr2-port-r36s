package com.my.target;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: AdDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class br extends Dialog {

    @NonNull
    private final a ic;

    /* JADX INFO: compiled from: AdDialog.java */
    public interface a {
        void a(@NonNull br brVar, @NonNull FrameLayout frameLayout);

        void aV();

        void i(boolean z);
    }

    @NonNull
    public static br a(@NonNull a aVar, @NonNull Context context) {
        return new br(aVar, context);
    }

    private br(@NonNull a aVar, @NonNull Context context) {
        super(context);
        this.ic = aVar;
    }

    public void aU() {
        Window window = getWindow();
        if (window != null) {
            window.setFlags(1024, 1024);
        }
    }

    public void setDimAmount(float f) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = f;
            window.setAttributes(attributes);
            window.addFlags(2);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.ic.aV();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        FrameLayout frameLayout = new FrameLayout(getContext());
        setContentView(frameLayout);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setLayout(-1, -1);
        }
        this.ic.a(this, frameLayout);
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        this.ic.i(z);
        super.onWindowFocusChanged(z);
    }
}
