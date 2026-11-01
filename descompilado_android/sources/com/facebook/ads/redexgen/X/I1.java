package com.facebook.ads.redexgen.X;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class I1 implements I2 {
    private IP B;
    private final Executor C;
    private final LI D;

    public I1(Executor threadPoolExecutor, IP adPlacement, Context context) {
        this.D = IB.C(context);
        this.C = threadPoolExecutor;
        this.B = adPlacement;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> F(String message) {
        LW miscInfo = new LW();
        LW requestParameters = new LW();
        LW lw = new LW();
        miscInfo.put("user_identifier", C0551Gp.B);
        miscInfo.put("config_id", "297035420885434");
        miscInfo.put("category_id", "277149136230712");
        miscInfo.put("access_token", "693953940997901|9bf29a1f2745746a6c60d707f5bc23c2");
        miscInfo.put("client_time", (System.currentTimeMillis() / 1000) + "");
        String strD = this.B.D();
        if (strD != null) {
            lw.put("client_token", strD);
        }
        requestParameters.put("description", message);
        requestParameters.put("misc_info", MJ.C(lw));
        miscInfo.A(TtmlNode.TAG_METADATA, MJ.C(requestParameters));
        return miscInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(IP placement) {
        this.B = placement;
    }

    @Override // com.facebook.ads.redexgen.X.I2
    public final void IF() {
        Context context = MI.B();
        if (context == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("What Happened?");
        EditText editText = new EditText(context);
        editText.setSingleLine(false);
        editText.setImeOptions(1073741824);
        editText.setHint("May others login as you to debug? How do you reproduce the issue?");
        editText.setMaxLines(2);
        editText.setMinLines(2);
        builder.setView(editText);
        builder.setNegativeButton("Cancel", new I5(this));
        builder.setPositiveButton("Send Report", new I3(this, editText));
        builder.create().show();
    }
}
