package com.adcolony.sdk;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class o {
    static AlertDialog a;
    private af b;
    private AlertDialog c;
    private boolean d;

    o() {
        a.a("Alert.show", new ah() { // from class: com.adcolony.sdk.o.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (!a.d()) {
                    new aa.a().a("Null Activity reference, can't build AlertDialog.").a(aa.g);
                } else if (y.d(afVar.c(), "on_resume")) {
                    o.this.b = afVar;
                } else {
                    o.this.a(afVar);
                }
            }
        });
    }

    void a(final af afVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return;
        }
        final AlertDialog.Builder builder = a.a().n().r() >= 21 ? new AlertDialog.Builder(activityC, R.style.Theme.Material.Dialog.Alert) : new AlertDialog.Builder(activityC, R.style.Theme.DeviceDefault.Dialog);
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "message");
        String strB2 = y.b(jSONObjectC, "title");
        String strB3 = y.b(jSONObjectC, "positive");
        String strB4 = y.b(jSONObjectC, "negative");
        builder.setMessage(strB);
        builder.setTitle(strB2);
        builder.setPositiveButton(strB3, new DialogInterface.OnClickListener() { // from class: com.adcolony.sdk.o.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                o.this.c = null;
                dialogInterface.dismiss();
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "positive", true);
                o.this.d = false;
                afVar.a(jSONObjectA).b();
            }
        });
        if (!strB4.equals("")) {
            builder.setNegativeButton(strB4, new DialogInterface.OnClickListener() { // from class: com.adcolony.sdk.o.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    o.this.c = null;
                    dialogInterface.dismiss();
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "positive", false);
                    o.this.d = false;
                    afVar.a(jSONObjectA).b();
                }
            });
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.adcolony.sdk.o.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                o.this.c = null;
                o.this.d = false;
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "positive", false);
                afVar.a(jSONObjectA).b();
            }
        });
        aw.a(new Runnable() { // from class: com.adcolony.sdk.o.5
            @Override // java.lang.Runnable
            public void run() {
                o.this.d = true;
                o.this.c = builder.show();
            }
        });
    }

    void a() {
        af afVar = this.b;
        if (afVar != null) {
            a(afVar);
            this.b = null;
        }
    }

    AlertDialog b() {
        return this.c;
    }

    void a(AlertDialog alertDialog) {
        this.c = alertDialog;
    }

    boolean c() {
        return this.d;
    }
}
