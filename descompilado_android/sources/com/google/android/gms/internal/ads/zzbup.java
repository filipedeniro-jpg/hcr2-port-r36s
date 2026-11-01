package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbup {
    private final Executor executor;
    private final zzaai zzddy;
    private final zzato zzdqz;
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbtx zzfjc;
    private final zzbub zzfkd;
    private final zzbtv zzfkl;

    @Nullable
    private final zzbux zzfmi;
    private final Context zzlk;

    public zzbup(Context context, zzato zzatoVar, zzcvk zzcvkVar, zzbub zzbubVar, zzbtx zzbtxVar, @Nullable zzbux zzbuxVar, Executor executor, Executor executor2, zzbtv zzbtvVar) {
        this.zzlk = context;
        this.zzdqz = zzatoVar;
        this.zzffn = zzcvkVar;
        this.zzddy = zzcvkVar.zzddy;
        this.zzfkd = zzbubVar;
        this.zzfjc = zzbtxVar;
        this.zzfmi = zzbuxVar;
        this.zzfbm = executor;
        this.executor = executor2;
        this.zzfkl = zzbtvVar;
    }

    public final void zza(final zzbvf zzbvfVar) {
        this.zzfbm.execute(new Runnable(this, zzbvfVar) { // from class: com.google.android.gms.internal.ads.zzbus
            private final zzbup zzfmk;
            private final zzbvf zzfmm;

            {
                this.zzfmk = this;
                this.zzfmm = zzbvfVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfmk.zzd(this.zzfmm);
            }
        });
    }

    private static void zza(RelativeLayout.LayoutParams layoutParams, int i) {
        if (i == 0) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        } else if (i == 2) {
            layoutParams.addRule(12);
            layoutParams.addRule(11);
        } else if (i == 3) {
            layoutParams.addRule(12);
            layoutParams.addRule(9);
        } else {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
        }
    }

    public final void zzc(@Nullable zzbvf zzbvfVar) {
        if (zzbvfVar == null || this.zzfmi == null || zzbvfVar.zzaio() == null) {
            return;
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsp)).booleanValue() || this.zzfkd.zzahx()) {
            try {
                zzbvfVar.zzaio().addView(this.zzfmi.zzaiu());
            } catch (zzbbp e) {
                zzatm.zza("web view can not be obtained", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zza(zzbvf zzbvfVar, String[] strArr) {
        Map<String, WeakReference<View>> mapZzail = zzbvfVar.zzail();
        if (mapZzail == null) {
            return false;
        }
        for (String str : strArr) {
            if (mapZzail.get(str) != null) {
                return true;
            }
        }
        return false;
    }

    public final boolean zza(@NonNull ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        View viewZzahp = this.zzfjc.zzahp();
        if (viewZzahp == null) {
            return false;
        }
        viewGroup.removeAllViews();
        if (viewZzahp.getParent() instanceof ViewGroup) {
            ((ViewGroup) viewZzahp.getParent()).removeView(viewZzahp);
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcod)).booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        } else {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        viewGroup.addView(viewZzahp, layoutParams);
        return true;
    }

    final /* synthetic */ void zzb(ViewGroup viewGroup) {
        boolean z = viewGroup != null;
        if (this.zzfjc.zzahp() != null) {
            if (2 == this.zzfjc.zzahl() || 1 == this.zzfjc.zzahl()) {
                this.zzdqz.zzc(this.zzffn.zzgjt, String.valueOf(this.zzfjc.zzahl()), z);
            } else if (6 == this.zzfjc.zzahl()) {
                this.zzdqz.zzc(this.zzffn.zzgjt, "2", z);
                this.zzdqz.zzc(this.zzffn.zzgjt, "1", z);
            }
        }
    }

    final /* synthetic */ void zzd(zzbvf zzbvfVar) {
        ViewGroup viewGroup;
        View viewZzahm;
        final ViewGroup viewGroup2;
        IObjectWrapper iObjectWrapperZzqf;
        Drawable drawable;
        int i = 0;
        if (!(this.zzfkd.zzahz() || this.zzfkd.zzahy())) {
            viewGroup = null;
            break;
        }
        String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
        int i2 = 0;
        while (true) {
            if (i2 < 2) {
                View viewZzfw = zzbvfVar.zzfw(strArr[i2]);
                if (viewZzfw != null && (viewZzfw instanceof ViewGroup)) {
                    viewGroup = (ViewGroup) viewZzfw;
                    break;
                }
                i2++;
            } else {
                viewGroup = null;
                break;
            }
        }
        boolean z = viewGroup != null;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (this.zzfjc.zzahm() != null) {
            viewZzahm = this.zzfjc.zzahm();
            zzaai zzaaiVar = this.zzddy;
            if (zzaaiVar != null && !z) {
                zza(layoutParams, zzaaiVar.zzbjw);
                viewZzahm.setLayoutParams(layoutParams);
            }
        } else if (this.zzfjc.zzqk() instanceof zzzz) {
            zzzz zzzzVar = (zzzz) this.zzfjc.zzqk();
            if (!z) {
                zza(layoutParams, zzzzVar.zzqd());
            }
            View zzaacVar = new zzaac(this.zzlk, zzzzVar, layoutParams);
            zzaacVar.setContentDescription((CharSequence) zzuo.zzoj().zzd(zzyt.zzcoa));
            viewZzahm = zzaacVar;
        } else {
            viewZzahm = null;
        }
        if (viewZzahm != null) {
            if (viewZzahm.getParent() instanceof ViewGroup) {
                ((ViewGroup) viewZzahm.getParent()).removeView(viewZzahm);
            }
            if (z) {
                viewGroup.removeAllViews();
                viewGroup.addView(viewZzahm);
            } else {
                AdChoicesView adChoicesView = new AdChoicesView(zzbvfVar.zzaer().getContext());
                adChoicesView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                adChoicesView.addView(viewZzahm);
                FrameLayout frameLayoutZzaio = zzbvfVar.zzaio();
                if (frameLayoutZzaio != null) {
                    frameLayoutZzaio.addView(adChoicesView);
                }
            }
            zzbvfVar.zza(zzbvfVar.zzain(), viewZzahm, true);
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcso)).booleanValue()) {
            zzc(zzbvfVar);
        }
        String[] strArr2 = zzbun.zzflv;
        int length = strArr2.length;
        while (true) {
            if (i >= length) {
                viewGroup2 = null;
                break;
            }
            View viewZzfw2 = zzbvfVar.zzfw(strArr2[i]);
            if (viewZzfw2 instanceof ViewGroup) {
                viewGroup2 = (ViewGroup) viewZzfw2;
                break;
            }
            i++;
        }
        this.executor.execute(new Runnable(this, viewGroup2) { // from class: com.google.android.gms.internal.ads.zzbur
            private final zzbup zzfmk;
            private final ViewGroup zzfml;

            {
                this.zzfmk = this;
                this.zzfml = viewGroup2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfmk.zzb(this.zzfml);
            }
        });
        if (viewGroup2 != null) {
            if (zza(viewGroup2)) {
                if (this.zzfjc.zzahq() != null) {
                    this.zzfjc.zzahq().zza(new zzbuu(this, zzbvfVar, viewGroup2));
                    return;
                }
                return;
            }
            viewGroup2.removeAllViews();
            View viewZzaer = zzbvfVar.zzaer();
            Context context = viewZzaer != null ? viewZzaer.getContext() : null;
            if (context != null) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnz)).booleanValue()) {
                    zzaan zzaanVarZzqt = this.zzfkl.zzqt();
                    if (zzaanVarZzqt == null) {
                        return;
                    }
                    try {
                        iObjectWrapperZzqf = zzaanVarZzqt.zzqh();
                    } catch (RemoteException unused) {
                        zzatm.zzeu("Could not get main image drawable");
                        return;
                    }
                } else {
                    zzaas zzaasVarZzahn = this.zzfjc.zzahn();
                    if (zzaasVarZzahn == null) {
                        return;
                    }
                    try {
                        iObjectWrapperZzqf = zzaasVarZzahn.zzqf();
                    } catch (RemoteException unused2) {
                        zzatm.zzeu("Could not get drawable from image");
                        return;
                    }
                }
                if (iObjectWrapperZzqf == null || (drawable = (Drawable) ObjectWrapper.unwrap(iObjectWrapperZzqf)) == null) {
                    return;
                }
                ImageView imageView = new ImageView(context);
                imageView.setImageDrawable(drawable);
                IObjectWrapper iObjectWrapperZzaiq = zzbvfVar != null ? zzbvfVar.zzaiq() : null;
                if (iObjectWrapperZzaiq == null || !((Boolean) zzuo.zzoj().zzd(zzyt.zzcsq)).booleanValue()) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                } else {
                    imageView.setScaleType((ImageView.ScaleType) ObjectWrapper.unwrap(iObjectWrapperZzaiq));
                }
                imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                viewGroup2.addView(imageView);
            }
        }
    }
}
