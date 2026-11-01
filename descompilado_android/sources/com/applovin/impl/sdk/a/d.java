package com.applovin.impl.sdk.a;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.applovin.impl.adview.j;
import com.applovin.impl.sdk.h;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private final h a;
    private final j b;
    private AlertDialog c;

    public d(j jVar, h hVar) {
        this.a = hVar;
        this.b = jVar;
    }

    public void a() {
        this.b.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.d.1
            @Override // java.lang.Runnable
            public void run() {
                if (d.this.c != null) {
                    d.this.c.dismiss();
                }
            }
        });
    }

    public void b() {
        this.b.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.d.2
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(d.this.b);
                builder.setTitle((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.bU));
                builder.setMessage((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.bV));
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.bX), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.a.d.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.continueVideo();
                        d.this.b.resumeReportRewardTask();
                    }
                });
                builder.setNegativeButton((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.bW), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.a.d.2.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.skipVideo();
                        d.this.b.resumeReportRewardTask();
                    }
                });
                d.this.c = builder.show();
            }
        });
    }

    public void c() {
        this.b.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.d.3
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(d.this.b);
                builder.setTitle((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.bZ));
                builder.setMessage((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.ca));
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.cc), (DialogInterface.OnClickListener) null);
                builder.setNegativeButton((CharSequence) d.this.a.a(com.applovin.impl.sdk.b.b.cb), new DialogInterface.OnClickListener() { // from class: com.applovin.impl.sdk.a.d.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        d.this.b.dismiss();
                    }
                });
                d.this.c = builder.show();
            }
        });
    }

    public boolean d() {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null) {
            return alertDialog.isShowing();
        }
        return false;
    }
}
