package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtp extends zzbju {
    private final zzawv zzblh;
    private final zzdf zzefm;
    private final Executor zzfbm;
    private final zzbui zzfdc;
    private final zzars zzfeq;
    private final zzbtx zzfjc;
    private final zzbuf zzfkb;
    private final zzbup zzfkc;
    private final zzbub zzfkd;
    private final zzdvc<zzbxf> zzfke;
    private final zzdvc<zzbxd> zzfkf;
    private final zzdvc<zzbxm> zzfkg;
    private final zzdvc<zzbxc> zzfkh;
    private final zzdvc<zzbxh> zzfki;
    private zzbvf zzfkj;
    private boolean zzfkk;
    private final zzbtv zzfkl;
    private final Context zzlk;

    public zzbtp(zzbjt zzbjtVar, Executor executor, zzbtx zzbtxVar, zzbuf zzbufVar, zzbup zzbupVar, zzbub zzbubVar, zzbui zzbuiVar, zzdvc<zzbxf> zzdvcVar, zzdvc<zzbxd> zzdvcVar2, zzdvc<zzbxm> zzdvcVar3, zzdvc<zzbxc> zzdvcVar4, zzdvc<zzbxh> zzdvcVar5, zzars zzarsVar, zzdf zzdfVar, zzawv zzawvVar, Context context, zzbtv zzbtvVar) {
        super(zzbjtVar);
        this.zzfbm = executor;
        this.zzfjc = zzbtxVar;
        this.zzfkb = zzbufVar;
        this.zzfkc = zzbupVar;
        this.zzfkd = zzbubVar;
        this.zzfdc = zzbuiVar;
        this.zzfke = zzdvcVar;
        this.zzfkf = zzdvcVar2;
        this.zzfkg = zzdvcVar3;
        this.zzfkh = zzdvcVar4;
        this.zzfki = zzdvcVar5;
        this.zzfeq = zzarsVar;
        this.zzefm = zzdfVar;
        this.zzblh = zzawvVar;
        this.zzlk = context;
        this.zzfkl = zzbtvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbju
    @AnyThread
    public final void zzaew() {
        this.zzfbm.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbts
            private final zzbtp zzfkn;

            {
                this.zzfkn = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfkn.zzahk();
            }
        });
        if (this.zzfjc.zzahl() != 7) {
            Executor executor = this.zzfbm;
            zzbuf zzbufVar = this.zzfkb;
            zzbufVar.getClass();
            executor.execute(zzbtr.zza(zzbufVar));
        }
        super.zzaew();
    }

    public final synchronized void zzfp(String str) {
        this.zzfkb.zzfp(str);
    }

    public final synchronized void zzagz() {
        if (this.zzfkk) {
            return;
        }
        this.zzfkb.zzagz();
    }

    public final synchronized void zzf(Bundle bundle) {
        this.zzfkb.zzf(bundle);
    }

    public final synchronized boolean zzh(Bundle bundle) {
        if (this.zzfkk) {
            return true;
        }
        boolean zZzh = this.zzfkb.zzh(bundle);
        this.zzfkk = zZzh;
        return zZzh;
    }

    public final synchronized void zzg(Bundle bundle) {
        this.zzfkb.zzg(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbju
    public final synchronized void destroy() {
        this.zzfbm.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbtu
            private final zzbtp zzfkn;

            {
                this.zzfkn = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfkn.zzahj();
            }
        });
        super.destroy();
    }

    public final synchronized void zza(zzbvf zzbvfVar) {
        zzdc zzdcVarZzcc;
        this.zzfkj = zzbvfVar;
        this.zzfkc.zza(zzbvfVar);
        this.zzfkb.zza(zzbvfVar.zzaer(), zzbvfVar.zzail(), zzbvfVar.zzaim(), zzbvfVar, zzbvfVar);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmu)).booleanValue() && (zzdcVarZzcc = this.zzefm.zzcc()) != null) {
            zzdcVarZzcc.zzb(zzbvfVar.zzaer());
        }
        if (zzbvfVar.zzaip() != null) {
            zzbvfVar.zzaip().zza(this.zzfeq);
        }
    }

    public final synchronized void zzb(zzbvf zzbvfVar) {
        this.zzfkb.zza(zzbvfVar.zzaer(), zzbvfVar.zzaik());
        if (zzbvfVar.zzaio() != null) {
            zzbvfVar.zzaio().setClickable(false);
            zzbvfVar.zzaio().removeAllViews();
        }
        if (zzbvfVar.zzaip() != null) {
            zzbvfVar.zzaip().zzb(this.zzfeq);
        }
        this.zzfkj = null;
    }

    public final synchronized void zza(View view, View view2, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcso)).booleanValue()) {
            this.zzfkc.zzc(this.zzfkj);
        }
        this.zzfkb.zza(view, view2, map, map2, z);
    }

    public final synchronized void zza(View view, MotionEvent motionEvent, View view2) {
        this.zzfkb.zza(view, motionEvent, view2);
    }

    public final synchronized void zzb(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        if (this.zzfkk) {
            return;
        }
        if (z) {
            this.zzfkb.zza(view, map, map2);
            this.zzfkk = true;
            return;
        }
        if (!z) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcoe)).booleanValue() && map != null) {
                Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    View view2 = it.next().getValue().get();
                    if (view2 != null && zzx(view2)) {
                        this.zzfkb.zza(view, map, map2);
                        this.zzfkk = true;
                        return;
                    }
                }
            }
        }
    }

    public final synchronized void setClickConfirmingView(View view) {
        this.zzfkb.setClickConfirmingView(view);
    }

    public final synchronized void zza(zzacl zzaclVar) {
        this.zzfkb.zza(zzaclVar);
    }

    public final synchronized void cancelUnconfirmedClick() {
        this.zzfkb.cancelUnconfirmedClick();
    }

    public final synchronized void zza(@Nullable zzwb zzwbVar) {
        this.zzfkb.zza(zzwbVar);
    }

    public final synchronized void zza(zzvx zzvxVar) {
        this.zzfkb.zza(zzvxVar);
    }

    public final synchronized void zzqs() {
        this.zzfkb.zzqs();
    }

    public final synchronized void recordCustomClickGesture() {
        if (this.zzfkj == null) {
            zzatm.zzdv("Ad should be associated with an ad view before calling recordCustomClickGesture()");
        } else {
            final boolean z = this.zzfkj instanceof zzbuo;
            this.zzfbm.execute(new Runnable(this, z) { // from class: com.google.android.gms.internal.ads.zzbtt
                private final boolean zzdye;
                private final zzbtp zzfkn;

                {
                    this.zzfkn = this;
                    this.zzdye = z;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfkn.zzaz(this.zzdye);
                }
            });
        }
    }

    public final synchronized boolean isCustomClickGestureEnabled() {
        return this.zzfkb.isCustomClickGestureEnabled();
    }

    public static boolean zzx(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    public final boolean zzahg() {
        return this.zzfkd.zzahy();
    }

    public final void zzg(String str, boolean z) {
        String str2;
        View view;
        if (this.zzfkd.zzahh()) {
            zzbbc zzbbcVarZzahr = this.zzfjc.zzahr();
            zzbbc zzbbcVarZzahq = this.zzfjc.zzahq();
            if (zzbbcVarZzahr == null && zzbbcVarZzahq == null) {
                return;
            }
            boolean z2 = zzbbcVarZzahr != null;
            boolean z3 = zzbbcVarZzahq != null;
            String str3 = null;
            if (z2) {
                str2 = str3;
            } else if (z3) {
                str3 = "javascript";
                zzbbcVarZzahr = zzbbcVarZzahq;
                str2 = str3;
            } else {
                zzbbcVarZzahr = null;
                str2 = null;
            }
            if (zzbbcVarZzahr.getWebView() != null && com.google.android.gms.ads.internal.zzp.zzkn().zzo(this.zzlk)) {
                int i = this.zzblh.zzdvs;
                int i2 = this.zzblh.zzdvt;
                StringBuilder sb = new StringBuilder(23);
                sb.append(i);
                sb.append(".");
                sb.append(i2);
                IObjectWrapper iObjectWrapperZza = com.google.android.gms.ads.internal.zzp.zzkn().zza(sb.toString(), zzbbcVarZzahr.getWebView(), "", "javascript", str2, str);
                if (iObjectWrapperZza == null) {
                    return;
                }
                this.zzfjc.zzas(iObjectWrapperZza);
                zzbbcVarZzahr.zzaq(iObjectWrapperZza);
                if (z3 && (view = zzbbcVarZzahq.getView()) != null) {
                    com.google.android.gms.ads.internal.zzp.zzkn().zza(iObjectWrapperZza, view);
                }
                if (z) {
                    com.google.android.gms.ads.internal.zzp.zzkn().zzae(iObjectWrapperZza);
                }
            }
        }
    }

    public final boolean zzahh() {
        return this.zzfkd.zzahh();
    }

    public final void zzy(View view) {
        IObjectWrapper iObjectWrapperZzahs = this.zzfjc.zzahs();
        boolean z = this.zzfjc.zzahr() != null;
        if (!this.zzfkd.zzahh() || iObjectWrapperZzahs == null || !z || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzkn().zza(iObjectWrapperZzahs, view);
    }

    public final void zzz(View view) {
        IObjectWrapper iObjectWrapperZzahs = this.zzfjc.zzahs();
        if (!this.zzfkd.zzahh() || iObjectWrapperZzahs == null || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzkn().zzb(iObjectWrapperZzahs, view);
    }

    public final zzbtv zzahi() {
        return this.zzfkl;
    }

    final /* synthetic */ void zzaz(boolean z) {
        this.zzfkb.zza(this.zzfkj.zzaer(), this.zzfkj.zzaik(), this.zzfkj.zzail(), z);
    }

    final /* synthetic */ void zzahj() {
        this.zzfkb.destroy();
        this.zzfjc.destroy();
    }

    final /* synthetic */ void zzahk() {
        try {
            int iZzahl = this.zzfjc.zzahl();
            if (iZzahl == 1) {
                if (this.zzfdc.zzaia() != null) {
                    zzg("Google", true);
                    this.zzfdc.zzaia().zza(this.zzfke.get());
                    return;
                }
                return;
            }
            if (iZzahl == 2) {
                if (this.zzfdc.zzaib() != null) {
                    zzg("Google", true);
                    this.zzfdc.zzaib().zza(this.zzfkf.get());
                    return;
                }
                return;
            }
            if (iZzahl == 3) {
                if (this.zzfdc.zzfu(this.zzfjc.getCustomTemplateId()) != null) {
                    if (this.zzfjc.zzahq() != null) {
                        zzg("Google", true);
                    }
                    this.zzfdc.zzfu(this.zzfjc.getCustomTemplateId()).zzb(this.zzfki.get());
                    return;
                }
                return;
            }
            if (iZzahl == 6) {
                if (this.zzfdc.zzaic() != null) {
                    zzg("Google", true);
                    this.zzfdc.zzaic().zza(this.zzfkg.get());
                    return;
                }
                return;
            }
            if (iZzahl == 7) {
                if (this.zzfdc.zzaie() != null) {
                    this.zzfdc.zzaie().zza(this.zzfkh.get());
                    return;
                }
                return;
            }
            zzatm.zzes("Wrong native template id!");
        } catch (RemoteException e) {
            zzatm.zzc("RemoteException when notifyAdLoad is called", e);
        }
    }
}
