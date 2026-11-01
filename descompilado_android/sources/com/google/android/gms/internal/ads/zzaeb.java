package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import com.appsflyer.share.Constants;
import com.facebook.internal.ServerProtocol;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbcc;
import com.google.android.gms.internal.ads.zzbcd;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzbcl;
import com.google.android.gms.internal.ads.zzbcn;
import com.my.target.ai;
import com.my.target.i;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.net.URISyntaxException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaeb<T extends zzbcd & zzbcc & zzbcg & zzbcl & zzbcn> implements zzadx<T> {
    private final com.google.android.gms.ads.internal.zza zzcxl;
    private final zzamf zzcxm;

    public zzaeb(com.google.android.gms.ads.internal.zza zzaVar, zzamf zzamfVar) {
        this.zzcxl = zzaVar;
        this.zzcxm = zzamfVar;
    }

    private static boolean zzc(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzd(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if (TtmlNode.TAG_P.equalsIgnoreCase(str)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            return 7;
        }
        if ("l".equalsIgnoreCase(str)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            return 6;
        }
        if (Constants.URL_CAMPAIGN.equalsIgnoreCase(str)) {
            return com.google.android.gms.ads.internal.zzp.zzka().zzvm();
        }
        return -1;
    }

    private final void zzu(boolean z) {
        zzamf zzamfVar = this.zzcxm;
        if (zzamfVar != null) {
            zzamfVar.zzv(z);
        }
    }

    @VisibleForTesting
    static String zza(Context context, zzdf zzdfVar, String str, View view, @Nullable Activity activity) {
        if (zzdfVar == null) {
            return str;
        }
        try {
            Uri uriZza = Uri.parse(str);
            if (zzdfVar.zzd(uriZza)) {
                uriZza = zzdfVar.zza(uriZza, context, view, activity);
            }
            return uriZza.toString();
        } catch (zzdi unused) {
            return str;
        } catch (Exception e) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbcd zzbcdVar = (zzbcd) obj;
        String strZzd = zzask.zzd((String) map.get("u"), zzbcdVar.getContext(), true);
        String str = (String) map.get(i.I);
        if (str == null) {
            zzatm.zzeu("Action missing from an open GMSG.");
            return;
        }
        com.google.android.gms.ads.internal.zza zzaVar = this.zzcxl;
        if (zzaVar != null && !zzaVar.zzjh()) {
            this.zzcxl.zzbl(strZzd);
            return;
        }
        if ("expand".equalsIgnoreCase(str)) {
            if (((zzbcc) zzbcdVar).zzzd()) {
                zzatm.zzeu("Cannot expand WebView that is already expanded.");
                return;
            } else {
                zzu(false);
                ((zzbcg) zzbcdVar).zzc(zzc(map), zzd(map));
                return;
            }
        }
        if ("webapp".equalsIgnoreCase(str)) {
            zzu(false);
            if (strZzd != null) {
                ((zzbcg) zzbcdVar).zza(zzc(map), zzd(map), strZzd);
                return;
            } else {
                ((zzbcg) zzbcdVar).zza(zzc(map), zzd(map), (String) map.get(ai.a.da), (String) map.get("baseurl"));
                return;
            }
        }
        if ("app".equalsIgnoreCase(str) && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase((String) map.get("system_browser"))) {
            zzu(true);
            if (TextUtils.isEmpty(strZzd)) {
                zzatm.zzeu("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzbcg) zzbcdVar).zza(new com.google.android.gms.ads.internal.overlay.zzb(new zzaee(zzbcdVar.getContext(), ((zzbcl) zzbcdVar).zzzb(), ((zzbcn) zzbcdVar).getView()).zze(map)));
                return;
            } catch (ActivityNotFoundException e) {
                zzatm.zzeu(e.getMessage());
                return;
            }
        }
        zzu(true);
        String str2 = (String) map.get(VungleWebViewActivity.INTENT_URL);
        Intent uri = null;
        if (!TextUtils.isEmpty(str2)) {
            try {
                uri = Intent.parseUri(str2, 0);
            } catch (URISyntaxException e2) {
                String strValueOf = String.valueOf(str2);
                zzatm.zzc(strValueOf.length() != 0 ? "Error parsing the url: ".concat(strValueOf) : new String("Error parsing the url: "), e2);
            }
        }
        if (uri != null && uri.getData() != null) {
            Uri data = uri.getData();
            String string = data.toString();
            if (!TextUtils.isEmpty(string)) {
                try {
                    string = zza(zzbcdVar.getContext(), ((zzbcl) zzbcdVar).zzzb(), string, ((zzbcn) zzbcdVar).getView(), zzbcdVar.zzxl());
                } catch (Exception e3) {
                    zzatm.zzc("Error occurred while adding signals.", e3);
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e3, "OpenGmsgHandler.onGmsg");
                }
                try {
                    data = Uri.parse(string);
                } catch (Exception e4) {
                    String strValueOf2 = String.valueOf(string);
                    zzatm.zzc(strValueOf2.length() != 0 ? "Error parsing the uri: ".concat(strValueOf2) : new String("Error parsing the uri: "), e4);
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e4, "OpenGmsgHandler.onGmsg");
                }
            }
            uri.setData(data);
        }
        if (uri != null) {
            ((zzbcg) zzbcdVar).zza(new com.google.android.gms.ads.internal.overlay.zzb(uri));
            return;
        }
        if (!TextUtils.isEmpty(strZzd)) {
            strZzd = zza(zzbcdVar.getContext(), ((zzbcl) zzbcdVar).zzzb(), strZzd, ((zzbcn) zzbcdVar).getView(), zzbcdVar.zzxl());
        }
        ((zzbcg) zzbcdVar).zza(new com.google.android.gms.ads.internal.overlay.zzb((String) map.get("i"), strZzd, (String) map.get("m"), (String) map.get(TtmlNode.TAG_P), (String) map.get(Constants.URL_CAMPAIGN), (String) map.get("f"), (String) map.get("e")));
    }
}
