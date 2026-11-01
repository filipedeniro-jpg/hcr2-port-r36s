package com.applovin.impl.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.applovin.adview.AppLovinConfirmationActivity;
import com.applovin.impl.sdk.h;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private final h a;
    private final String b;
    private final Context c;

    public b(h hVar, Context context, String str) {
        this.a = hVar;
        this.b = str;
        this.c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Throwable th) {
        this.a.w().c("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.c, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b() {
        h hVar;
        com.applovin.impl.sdk.b.b<String> bVar;
        if (this.b.equals("accepted")) {
            hVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bO;
        } else if (this.b.equals("quota_exceeded")) {
            hVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bP;
        } else if (this.b.equals("rejected")) {
            hVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bQ;
        } else {
            hVar = this.a;
            bVar = com.applovin.impl.sdk.b.b.bR;
        }
        return (String) hVar.a(bVar);
    }

    void a() {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.sdk.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                String str = (String) b.this.a.a(com.applovin.impl.sdk.b.b.bN);
                String strB = b.this.b();
                String str2 = (String) b.this.a.a(com.applovin.impl.sdk.b.b.bS);
                if (!com.applovin.impl.sdk.utils.e.a((Class<?>) AppLovinConfirmationActivity.class, b.this.c)) {
                    b.this.a(strB, null);
                    return;
                }
                try {
                    Intent intent = new Intent(b.this.c, (Class<?>) AppLovinConfirmationActivity.class);
                    intent.putExtra("dialog_title", str);
                    intent.putExtra("dialog_body", strB);
                    intent.putExtra("dialog_button_text", str2);
                    b.this.c.startActivity(intent);
                } catch (Throwable th) {
                    b.this.a(strB, th);
                }
            }
        });
    }
}
