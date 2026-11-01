package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R;
import com.my.target.ab;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamk extends zzamp {
    private final Map<String, String> zzcue;
    private final Context zzlk;

    public zzamk(zzbbc zzbbcVar, Map<String, String> map) {
        super(zzbbcVar, ab.bF);
        this.zzcue = map;
        this.zzlk = zzbbcVar.zzxl();
    }

    public final void execute() {
        if (this.zzlk == null) {
            zzdn("Activity context is not available");
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (!zzatv.zzar(this.zzlk).zzpk()) {
            zzdn("Feature is not supported by the device.");
            return;
        }
        String str = this.zzcue.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzdn("Image url cannot be empty.");
            return;
        }
        if (!URLUtil.isValidUrl(str)) {
            String strValueOf = String.valueOf(str);
            zzdn(strValueOf.length() != 0 ? "Invalid image url: ".concat(strValueOf) : new String("Invalid image url: "));
            return;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (!zzatv.zzee(lastPathSegment)) {
            String strValueOf2 = String.valueOf(lastPathSegment);
            zzdn(strValueOf2.length() != 0 ? "Image type not recognized: ".concat(strValueOf2) : new String("Image type not recognized: "));
            return;
        }
        Resources resources = com.google.android.gms.ads.internal.zzp.zzkc().getResources();
        com.google.android.gms.ads.internal.zzp.zzjy();
        AlertDialog.Builder builderZzaq = zzatv.zzaq(this.zzlk);
        builderZzaq.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
        builderZzaq.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
        builderZzaq.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzamj(this, str, lastPathSegment));
        builderZzaq.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzamm(this));
        builderZzaq.create().show();
    }
}
