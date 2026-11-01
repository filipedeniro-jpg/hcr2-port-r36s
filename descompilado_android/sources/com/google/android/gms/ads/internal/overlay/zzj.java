package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.ads.zzati;
import com.google.android.gms.internal.ads.zzatv;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzj extends zzati {
    final /* synthetic */ zze zzdhh;

    private zzj(zze zzeVar) {
        this.zzdhh = zzeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzati
    public final void zzst() {
        Bitmap bitmapZza = com.google.android.gms.ads.internal.zzp.zzkr().zza(Integer.valueOf(this.zzdhh.zzdgo.zzdht.zzbld));
        if (bitmapZza != null) {
            final Drawable drawableZza = com.google.android.gms.ads.internal.zzp.zzka().zza(this.zzdhh.zzzr, bitmapZza, this.zzdhh.zzdgo.zzdht.zzblb, this.zzdhh.zzdgo.zzdht.zzblc);
            zzatv.zzdsk.post(new Runnable(this, drawableZza) { // from class: com.google.android.gms.ads.internal.overlay.zzm
                private final zzj zzdhj;
                private final Drawable zzdhk;

                {
                    this.zzdhj = this;
                    this.zzdhk = drawableZza;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzj zzjVar = this.zzdhj;
                    zzjVar.zzdhh.zzzr.getWindow().setBackgroundDrawable(this.zzdhk);
                }
            });
        }
    }
}
