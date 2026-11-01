package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.internal.ads.zzaad;
import com.google.android.gms.internal.ads.zzaaf;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class MediaView extends FrameLayout {
    private UnifiedNativeAd.MediaContent zzbjm;
    private boolean zzbjn;
    private zzaad zzbjo;
    private ImageView.ScaleType zzbjp;
    private boolean zzbjq;
    private zzaaf zzbjr;

    public MediaView(Context context) {
        super(context);
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setMediaContent(UnifiedNativeAd.MediaContent mediaContent) {
        this.zzbjn = true;
        this.zzbjm = mediaContent;
        zzaad zzaadVar = this.zzbjo;
        if (zzaadVar != null) {
            zzaadVar.setMediaContent(mediaContent);
        }
    }

    protected final synchronized void zza(zzaad zzaadVar) {
        this.zzbjo = zzaadVar;
        if (this.zzbjn) {
            zzaadVar.setMediaContent(this.zzbjm);
        }
    }

    public void setImageScaleType(ImageView.ScaleType scaleType) {
        this.zzbjq = true;
        this.zzbjp = scaleType;
        zzaaf zzaafVar = this.zzbjr;
        if (zzaafVar != null) {
            zzaafVar.setImageScaleType(this.zzbjp);
        }
    }

    protected final synchronized void zza(zzaaf zzaafVar) {
        this.zzbjr = zzaafVar;
        if (this.zzbjq) {
            zzaafVar.setImageScaleType(this.zzbjp);
        }
    }
}
