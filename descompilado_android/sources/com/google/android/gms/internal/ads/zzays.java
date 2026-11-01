package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzays {
    private final zzawv zzdid;
    private final String zzdis;

    @Nullable
    private final zzzh zzdxo;
    private boolean zzdxs;

    @Nullable
    private final zzzf zzeag;
    private final long[] zzeai;
    private final String[] zzeaj;
    private zzaya zzeao;
    private boolean zzeap;
    private boolean zzeaq;
    private final Context zzlk;
    private final zzava zzeah = new zzavf().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzvy();
    private boolean zzeak = false;
    private boolean zzeal = false;
    private boolean zzeam = false;
    private boolean zzean = false;
    private long zzear = -1;

    public zzays(Context context, zzawv zzawvVar, String str, @Nullable zzzh zzzhVar, @Nullable zzzf zzzfVar) {
        this.zzlk = context;
        this.zzdid = zzawvVar;
        this.zzdis = str;
        this.zzdxo = zzzhVar;
        this.zzeag = zzzfVar;
        String str2 = (String) zzuo.zzoj().zzd(zzyt.zzchh);
        if (str2 == null) {
            this.zzeaj = new String[0];
            this.zzeai = new long[0];
            return;
        }
        String[] strArrSplit = TextUtils.split(str2, ",");
        this.zzeaj = new String[strArrSplit.length];
        this.zzeai = new long[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            try {
                this.zzeai[i] = Long.parseLong(strArrSplit[i]);
            } catch (NumberFormatException e) {
                zzatm.zzd("Unable to parse frame hash target time number.", e);
                this.zzeai[i] = -1;
            }
        }
    }

    public final void zzb(zzaya zzayaVar) {
        zzzc.zza(this.zzdxo, this.zzeag, "vpc2");
        this.zzeak = true;
        zzzh zzzhVar = this.zzdxo;
        if (zzzhVar != null) {
            zzzhVar.zzj("vpn", zzayaVar.zzwm());
        }
        this.zzeao = zzayaVar;
    }

    public final void zzei() {
        if (!this.zzeak || this.zzeal) {
            return;
        }
        zzzc.zza(this.zzdxo, this.zzeag, "vfr2");
        this.zzeal = true;
    }

    public final void onStop() {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzchg)).booleanValue() || this.zzeap) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("type", "native-player-metrics");
        bundle.putString(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.zzdis);
        bundle.putString("player", this.zzeao.zzwm());
        for (zzavc zzavcVar : this.zzeah.zzvx()) {
            String strValueOf = String.valueOf(zzavcVar.name);
            bundle.putString(strValueOf.length() != 0 ? "fps_c_".concat(strValueOf) : new String("fps_c_"), Integer.toString(zzavcVar.count));
            String strValueOf2 = String.valueOf(zzavcVar.name);
            bundle.putString(strValueOf2.length() != 0 ? "fps_p_".concat(strValueOf2) : new String("fps_p_"), Double.toString(zzavcVar.zzdts));
        }
        int i = 0;
        while (true) {
            long[] jArr = this.zzeai;
            if (i < jArr.length) {
                String str = this.zzeaj[i];
                if (str != null) {
                    String strValueOf3 = String.valueOf(Long.valueOf(jArr[i]));
                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf3).length() + 3);
                    sb.append("fh_");
                    sb.append(strValueOf3);
                    bundle.putString(sb.toString(), str);
                }
                i++;
            } else {
                com.google.android.gms.ads.internal.zzp.zzjy().zza(this.zzlk, this.zzdid.zzbnh, "gmob-apps", bundle, true);
                this.zzeap = true;
                return;
            }
        }
    }

    public final void zzc(zzaya zzayaVar) {
        if (this.zzeam && !this.zzean) {
            if (zzatm.zzuq() && !this.zzean) {
                zzatm.zzdy("VideoMetricsMixin first frame");
            }
            zzzc.zza(this.zzdxo, this.zzeag, "vff2");
            this.zzean = true;
        }
        long jNanoTime = com.google.android.gms.ads.internal.zzp.zzkf().nanoTime();
        if (this.zzdxs && this.zzeaq && this.zzear != -1) {
            double nanos = TimeUnit.SECONDS.toNanos(1L);
            double d = jNanoTime - this.zzear;
            Double.isNaN(nanos);
            Double.isNaN(d);
            this.zzeah.zza(nanos / d);
        }
        this.zzeaq = this.zzdxs;
        this.zzear = jNanoTime;
        long jLongValue = ((Long) zzuo.zzoj().zzd(zzyt.zzchi)).longValue();
        long currentPosition = zzayaVar.getCurrentPosition();
        int i = 0;
        while (true) {
            String[] strArr = this.zzeaj;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i] == null && jLongValue > Math.abs(currentPosition - this.zzeai[i])) {
                String[] strArr2 = this.zzeaj;
                int i2 = 8;
                Bitmap bitmap = zzayaVar.getBitmap(8, 8);
                long j = 63;
                int i3 = 0;
                long j2 = 0;
                while (i3 < i2) {
                    long j3 = j;
                    int i4 = 0;
                    while (i4 < i2) {
                        int pixel = bitmap.getPixel(i4, i3);
                        j2 |= ((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1L : 0L) << ((int) j3);
                        i4++;
                        j3--;
                        i2 = 8;
                    }
                    i3++;
                    j = j3;
                    i2 = 8;
                }
                strArr2[i] = String.format("%016X", Long.valueOf(j2));
                return;
            }
            i++;
        }
    }

    public final void zzxg() {
        this.zzdxs = true;
        if (!this.zzeal || this.zzeam) {
            return;
        }
        zzzc.zza(this.zzdxo, this.zzeag, "vfp2");
        this.zzeam = true;
    }

    public final void zzxh() {
        this.zzdxs = false;
    }
}
