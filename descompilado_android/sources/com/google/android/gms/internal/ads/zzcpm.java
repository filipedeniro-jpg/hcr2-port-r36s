package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.my.target.bj;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpm implements zzcra<zzcpn> {
    private final PackageInfo zzdij;
    private final zzato zzdqz;
    private final zzcvk zzffn;
    private final zzdcs zzfnq;

    public zzcpm(zzdcs zzdcsVar, zzcvk zzcvkVar, @Nullable PackageInfo packageInfo, zzato zzatoVar) {
        this.zzfnq = zzdcsVar;
        this.zzffn = zzcvkVar;
        this.zzdij = packageInfo;
        this.zzdqz = zzatoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcpn> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcpp
            private final zzcpm zzgex;

            {
                this.zzgex = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgex.zzaly();
            }
        });
    }

    final /* synthetic */ void zza(ArrayList arrayList, Bundle bundle) {
        JSONArray jSONArrayOptJSONArray;
        String str;
        bundle.putInt("native_version", 3);
        bundle.putStringArrayList("native_templates", arrayList);
        bundle.putStringArrayList("native_custom_templates", this.zzffn.zzgjv);
        String str2 = "landscape";
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcny)).booleanValue() && this.zzffn.zzddy.versionCode > 3) {
            bundle.putBoolean("enable_native_media_orientation", true);
            int i = this.zzffn.zzddy.zzbju;
            if (i == 1) {
                str = bj.gJ;
            } else if (i == 2) {
                str = "landscape";
            } else if (i != 3) {
                str = i != 4 ? "unknown" : MessengerShareContentUtility.IMAGE_RATIO_SQUARE;
            } else {
                str = "portrait";
            }
            if (!"unknown".equals(str)) {
                bundle.putString("native_media_orientation", str);
            }
        }
        int i2 = this.zzffn.zzddy.zzbjt;
        if (i2 == 0) {
            str2 = bj.gJ;
        } else if (i2 == 1) {
            str2 = "portrait";
        } else if (i2 != 2) {
            str2 = "unknown";
        }
        if (!"unknown".equals(str2)) {
            bundle.putString("native_image_orientation", str2);
        }
        bundle.putBoolean("native_multiple_images", this.zzffn.zzddy.zzbjv);
        bundle.putBoolean("use_custom_mute", this.zzffn.zzddy.zzbjy);
        PackageInfo packageInfo = this.zzdij;
        int i3 = packageInfo == null ? 0 : packageInfo.versionCode;
        if (i3 > this.zzdqz.zzux()) {
            this.zzdqz.zzvd();
            this.zzdqz.zzcn(i3);
        }
        JSONObject jSONObjectZzvc = this.zzdqz.zzvc();
        String string = (jSONObjectZzvc == null || (jSONArrayOptJSONArray = jSONObjectZzvc.optJSONArray(this.zzffn.zzgjt)) == null) ? null : jSONArrayOptJSONArray.toString();
        if (!TextUtils.isEmpty(string)) {
            bundle.putString("native_advanced_settings", string);
        }
        if (this.zzffn.zzgcq > 1) {
            bundle.putInt("max_num_ads", this.zzffn.zzgcq);
        }
        if (this.zzffn.zzdjz != null) {
            zzafj zzafjVar = this.zzffn.zzdjz;
            int i4 = zzafjVar.zzcyh;
            String str3 = "l";
            if (i4 != 1) {
                if (i4 != 2) {
                    int i5 = zzafjVar.zzcyh;
                    StringBuilder sb = new StringBuilder(52);
                    sb.append("Instream ad video aspect ratio ");
                    sb.append(i5);
                    sb.append(" is wrong.");
                    zzawo.zzes(sb.toString());
                } else {
                    str3 = TtmlNode.TAG_P;
                }
            }
            bundle.putString("ia_var", str3);
            bundle.putBoolean("instr", true);
        }
        if (this.zzffn.zzamv() != null) {
            bundle.putBoolean("has_delayed_banner_listener", true);
        }
    }

    final /* synthetic */ zzcpn zzaly() throws Exception {
        final ArrayList<String> arrayList = this.zzffn.zzgju;
        if (arrayList == null) {
            return zzcpo.zzgew;
        }
        if (arrayList.isEmpty()) {
            return zzcpr.zzgew;
        }
        return new zzcpn(this, arrayList) { // from class: com.google.android.gms.internal.ads.zzcpq
            private final zzcpm zzgex;
            private final ArrayList zzgey;

            {
                this.zzgex = this;
                this.zzgey = arrayList;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Bundle bundle) {
                this.zzgex.zza(this.zzgey, bundle);
            }
        };
    }
}
