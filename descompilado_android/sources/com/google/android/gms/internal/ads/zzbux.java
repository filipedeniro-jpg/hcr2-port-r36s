package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.amazon.device.ads.WebRequest;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbux {
    private final zzbuf zzfkb;
    private final zzbxn zzfms;
    private final zzbyr zzfmy;
    private final zzbhd zzfmz;
    private final Context zzlk;

    public zzbux(Context context, zzbyr zzbyrVar, zzbxn zzbxnVar, zzbhd zzbhdVar, zzbuf zzbufVar) {
        this.zzlk = context;
        this.zzfmy = zzbyrVar;
        this.zzfms = zzbxnVar;
        this.zzfmz = zzbhdVar;
        this.zzfkb = zzbufVar;
    }

    public final View zzaiu() throws zzbbp {
        zzbbc zzbbcVarZza = this.zzfmy.zza(zztw.zzg(this.zzlk), false);
        zzbbcVarZza.getView().setVisibility(8);
        zzbbcVarZza.zza("/sendMessageToSdk", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbva
            private final zzbux zzfna;

            {
                this.zzfna = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfna.zzd((zzbbc) obj, map);
            }
        });
        zzbbcVarZza.zza("/adMuted", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbuz
            private final zzbux zzfna;

            {
                this.zzfna = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfna.zzc((zzbbc) obj, map);
            }
        });
        this.zzfms.zza(new WeakReference(zzbbcVarZza), "/loadHtml", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbvc
            private final zzbux zzfna;

            {
                this.zzfna = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, final Map map) {
                final zzbux zzbuxVar = this.zzfna;
                zzbbc zzbbcVar = (zzbbc) obj;
                zzbbcVar.zzyy().zza(new zzbcp(zzbuxVar, map) { // from class: com.google.android.gms.internal.ads.zzbvd
                    private final Map zzdvp;
                    private final zzbux zzfna;

                    {
                        this.zzfna = zzbuxVar;
                        this.zzdvp = map;
                    }

                    @Override // com.google.android.gms.internal.ads.zzbcp
                    public final void zzab(boolean z) {
                        this.zzfna.zza(this.zzdvp, z);
                    }
                });
                String str = (String) map.get("overlayHtml");
                String str2 = (String) map.get("baseUrl");
                if (TextUtils.isEmpty(str2)) {
                    zzbbcVar.loadData(str, WebRequest.CONTENT_TYPE_HTML, "UTF-8");
                } else {
                    zzbbcVar.loadDataWithBaseURL(str2, str, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
                }
            }
        });
        this.zzfms.zza(new WeakReference(zzbbcVarZza), "/showOverlay", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbvb
            private final zzbux zzfna;

            {
                this.zzfna = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfna.zzb((zzbbc) obj, map);
            }
        });
        this.zzfms.zza(new WeakReference(zzbbcVarZza), "/hideOverlay", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbve
            private final zzbux zzfna;

            {
                this.zzfna = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfna.zza((zzbbc) obj, map);
            }
        });
        return zzbbcVarZza.getView();
    }

    final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzatm.zzet("Hiding native ads overlay.");
        zzbbcVar.getView().setVisibility(8);
        this.zzfmz.zzax(false);
    }

    final /* synthetic */ void zzb(zzbbc zzbbcVar, Map map) {
        zzatm.zzet("Showing native ads overlay.");
        zzbbcVar.getView().setVisibility(0);
        this.zzfmz.zzax(true);
    }

    final /* synthetic */ void zza(Map map, boolean z) {
        HashMap map2 = new HashMap();
        map2.put("messageType", "htmlLoaded");
        map2.put("id", (String) map.get("id"));
        this.zzfms.zza("sendMessageToNativeJs", map2);
    }

    final /* synthetic */ void zzc(zzbbc zzbbcVar, Map map) {
        this.zzfkb.zzaha();
    }

    final /* synthetic */ void zzd(zzbbc zzbbcVar, Map map) {
        this.zzfms.zza("sendMessageToNativeJs", (Map<String, ?>) map);
    }
}
