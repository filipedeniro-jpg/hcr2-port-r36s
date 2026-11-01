package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MX {
    private MX() {
    }

    public static void B(DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener, @Nullable Context context) {
        if (context == null || !(context instanceof Activity)) {
            confirmListener.onClick(null, 0);
        } else {
            new AlertDialog.Builder(context).setTitle(JA.k(context)).setMessage(JA.i(context)).setPositiveButton(JA.j(context), new MW(confirmListener)).setNegativeButton(JA.h(context), new MV(cancelListener)).show();
        }
    }
}
