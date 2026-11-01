package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbad implements zzadx<zzayt> {
    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzayt zzaytVar, Map map) {
        zzayt zzaytVar2 = zzaytVar;
        zzbbu zzbbuVarZzxj = zzaytVar2.zzxj();
        if (zzbbuVarZzxj == null) {
            try {
                zzbbu zzbbuVar = new zzbbu(zzaytVar2, Float.parseFloat((String) map.get("duration")), "1".equals(map.get("customControlsAllowed")), "1".equals(map.get("clickToExpandAllowed")));
                zzaytVar2.zza(zzbbuVar);
                zzbbuVarZzxj = zzbbuVar;
            } catch (NullPointerException | NumberFormatException e) {
                zzatm.zzc("Unable to parse videoMeta message.", e);
                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoMetaGmsgHandler.onGmsg");
                return;
            }
        }
        float f = Float.parseFloat((String) map.get("duration"));
        boolean zEquals = "1".equals(map.get("muted"));
        float f2 = Float.parseFloat((String) map.get("currentTime"));
        int i = Integer.parseInt((String) map.get("playbackState"));
        int i2 = (i < 0 || 3 < i) ? 0 : i;
        String str = (String) map.get(InMobiNetworkValues.ASPECT_RATIO);
        float f3 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
        if (zzatm.isLoggable(3)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + IronSourceConstants.USING_CACHE_FOR_INIT_EVENT);
            sb.append("Video Meta GMSG: currentTime : ");
            sb.append(f2);
            sb.append(" , duration : ");
            sb.append(f);
            sb.append(" , isMuted : ");
            sb.append(zEquals);
            sb.append(" , playbackState : ");
            sb.append(i2);
            sb.append(" , aspectRatio : ");
            sb.append(str);
            zzatm.zzdv(sb.toString());
        }
        zzbbuVarZzxj.zza(f2, f, i2, zEquals, f3);
    }
}
