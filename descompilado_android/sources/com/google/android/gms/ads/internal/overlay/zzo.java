package com.google.android.gms.ads.internal.overlay;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzawe;
import com.google.android.gms.internal.ads.zzuo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzo extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzdhu;
    private final zzw zzdhv;

    public zzo(Context context, zzr zzrVar, @Nullable zzw zzwVar) {
        super(context);
        this.zzdhv = zzwVar;
        setOnClickListener(this);
        this.zzdhu = new ImageButton(context);
        this.zzdhu.setImageResource(R.drawable.btn_dialog);
        this.zzdhu.setBackgroundColor(0);
        this.zzdhu.setOnClickListener(this);
        ImageButton imageButton = this.zzdhu;
        zzuo.zzof();
        int iZza = zzawe.zza(context, zzrVar.paddingLeft);
        zzuo.zzof();
        int iZza2 = zzawe.zza(context, 0);
        zzuo.zzof();
        int iZza3 = zzawe.zza(context, zzrVar.paddingRight);
        zzuo.zzof();
        imageButton.setPadding(iZza, iZza2, iZza3, zzawe.zza(context, zzrVar.paddingBottom));
        this.zzdhu.setContentDescription("Interstitial close button");
        ImageButton imageButton2 = this.zzdhu;
        zzuo.zzof();
        int iZza4 = zzawe.zza(context, zzrVar.size + zzrVar.paddingLeft + zzrVar.paddingRight);
        zzuo.zzof();
        addView(imageButton2, new FrameLayout.LayoutParams(iZza4, zzawe.zza(context, zzrVar.size + zzrVar.paddingBottom), 17));
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        zzw zzwVar = this.zzdhv;
        if (zzwVar != null) {
            zzwVar.zzsk();
        }
    }

    public final void zzae(boolean z) {
        if (z) {
            this.zzdhu.setVisibility(8);
        } else {
            this.zzdhu.setVisibility(0);
        }
    }
}
