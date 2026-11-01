package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamq extends zzamp implements zzadx<zzbbc> {
    private float density;
    private int maxHeight;
    private int maxWidth;
    private int rotation;
    private final WindowManager zzbnm;
    private final zzbbc zzcyx;
    private final zzye zzdgb;
    private int zzdgc;
    private int zzdgd;
    private int zzdge;
    private int zzdgf;
    private final Context zzlk;
    private DisplayMetrics zzwl;

    public zzamq(zzbbc zzbbcVar, Context context, zzye zzyeVar) {
        super(zzbbcVar);
        this.zzdgc = -1;
        this.zzdgd = -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.zzdge = -1;
        this.zzdgf = -1;
        this.zzcyx = zzbbcVar;
        this.zzlk = context;
        this.zzdgb = zzyeVar;
        this.zzbnm = (WindowManager) context.getSystemService("window");
    }

    public final void zzi(int i, int i2) {
        int i3 = this.zzlk instanceof Activity ? com.google.android.gms.ads.internal.zzp.zzjy().zzf((Activity) this.zzlk)[0] : 0;
        if (this.zzcyx.zzyw() == null || !this.zzcyx.zzyw().zzaaq()) {
            int width = this.zzcyx.getWidth();
            int height = this.zzcyx.getHeight();
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcid)).booleanValue()) {
                if (width == 0 && this.zzcyx.zzyw() != null) {
                    width = this.zzcyx.zzyw().widthPixels;
                }
                if (height == 0 && this.zzcyx.zzyw() != null) {
                    height = this.zzcyx.zzyw().heightPixels;
                }
            }
            this.zzdge = zzuo.zzof().zzb(this.zzlk, width);
            this.zzdgf = zzuo.zzof().zzb(this.zzlk, height);
        }
        zzb(i, i2 - i3, this.zzdge, this.zzdgf);
        this.zzcyx.zzyy().zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        this.zzwl = new DisplayMetrics();
        Display defaultDisplay = this.zzbnm.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzwl);
        this.density = this.zzwl.density;
        this.rotation = defaultDisplay.getRotation();
        zzuo.zzof();
        DisplayMetrics displayMetrics = this.zzwl;
        this.zzdgc = zzawe.zzb(displayMetrics, displayMetrics.widthPixels);
        zzuo.zzof();
        DisplayMetrics displayMetrics2 = this.zzwl;
        this.zzdgd = zzawe.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity activityZzxl = this.zzcyx.zzxl();
        if (activityZzxl == null || activityZzxl.getWindow() == null) {
            this.maxWidth = this.zzdgc;
            this.maxHeight = this.zzdgd;
        } else {
            com.google.android.gms.ads.internal.zzp.zzjy();
            int[] iArrZzd = zzatv.zzd(activityZzxl);
            zzuo.zzof();
            this.maxWidth = zzawe.zzb(this.zzwl, iArrZzd[0]);
            zzuo.zzof();
            this.maxHeight = zzawe.zzb(this.zzwl, iArrZzd[1]);
        }
        if (this.zzcyx.zzyw().zzaaq()) {
            this.zzdge = this.zzdgc;
            this.zzdgf = this.zzdgd;
        } else {
            this.zzcyx.measure(0, 0);
        }
        zza(this.zzdgc, this.zzdgd, this.maxWidth, this.maxHeight, this.density, this.rotation);
        this.zzcyx.zza("onDeviceFeaturesReceived", new zzaml(new zzamn().zzx(this.zzdgb.zzpi()).zzw(this.zzdgb.zzpj()).zzy(this.zzdgb.zzpl()).zzz(this.zzdgb.zzpk()).zzaa(true)).toJson());
        int[] iArr = new int[2];
        this.zzcyx.getLocationOnScreen(iArr);
        zzi(zzuo.zzof().zzb(this.zzlk, iArr[0]), zzuo.zzof().zzb(this.zzlk, iArr[1]));
        if (zzatm.isLoggable(2)) {
            zzatm.zzet("Dispatching Ready Event.");
        }
        zzdo(this.zzcyx.zzxp().zzbnh);
    }
}
