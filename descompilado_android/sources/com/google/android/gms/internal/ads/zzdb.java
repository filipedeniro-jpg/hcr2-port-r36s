package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdb implements zzdc {
    protected static volatile zzdx zzvo;
    protected MotionEvent zzvt;
    protected double zzwc;
    private double zzwd;
    private double zzwe;
    protected float zzwf;
    protected float zzwg;
    protected float zzwh;
    protected float zzwi;
    protected DisplayMetrics zzwl;
    protected LinkedList<MotionEvent> zzvu = new LinkedList<>();
    protected long zzvv = 0;
    protected long zzvw = 0;
    protected long zzvx = 0;
    protected long zzvy = 0;
    protected long zzvz = 0;
    protected long zzwa = 0;
    protected long zzwb = 0;
    private boolean zzwj = false;
    protected boolean zzwk = false;

    protected zzdb(Context context) {
        try {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnb)).booleanValue()) {
                zzci.zzbx();
            } else {
                zzed.zzb(zzvo);
            }
            this.zzwl = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    protected abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzdw;

    protected abstract zzbo.zza.zzb zza(Context context, View view, Activity activity);

    protected abstract zzbo.zza.zzb zza(Context context, zzbk.zza zzaVar);

    protected abstract zzef zza(MotionEvent motionEvent) throws zzdw;

    protected abstract zzbo.zza.zzb zzb(Context context, View view, Activity activity);

    @Override // com.google.android.gms.internal.ads.zzdc
    public void zzb(View view) {
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context) {
        if (zzee.isMainThread()) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnd)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, zzdt.zzxk, null, null, null);
    }

    public final String zza(Context context, byte[] bArr) {
        if (zzee.isMainThread()) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnd)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, zzdt.zzxk, null, null, bArr);
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, zzdt.zzxm, view, activity, null);
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final void zzb(MotionEvent motionEvent) {
        boolean z = false;
        if (this.zzwj) {
            this.zzvy = 0L;
            this.zzvx = 0L;
            this.zzvw = 0L;
            this.zzvv = 0L;
            this.zzvz = 0L;
            this.zzwb = 0L;
            this.zzwa = 0L;
            Iterator<MotionEvent> it = this.zzvu.iterator();
            while (it.hasNext()) {
                it.next().recycle();
            }
            this.zzvu.clear();
            this.zzvt = null;
            this.zzwj = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzwc = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.zzwd = motionEvent.getRawX();
            this.zzwe = motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = motionEvent.getRawX();
            double rawY = motionEvent.getRawY();
            double d = this.zzwd;
            Double.isNaN(rawX);
            double d2 = rawX - d;
            double d3 = this.zzwe;
            Double.isNaN(rawY);
            double d4 = rawY - d3;
            this.zzwc += Math.sqrt((d2 * d2) + (d4 * d4));
            this.zzwd = rawX;
            this.zzwe = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 != 0) {
            try {
                if (action2 == 1) {
                    this.zzvt = MotionEvent.obtain(motionEvent);
                    this.zzvu.add(this.zzvt);
                    if (this.zzvu.size() > 6) {
                        this.zzvu.remove().recycle();
                    }
                    this.zzvx++;
                    this.zzvz = zza(new Throwable().getStackTrace());
                } else if (action2 == 2) {
                    this.zzvw += (long) (motionEvent.getHistorySize() + 1);
                    zzef zzefVarZza = zza(motionEvent);
                    if ((zzefVarZza == null || zzefVarZza.zzyt == null || zzefVarZza.zzyw == null) ? false : true) {
                        this.zzwa += zzefVarZza.zzyt.longValue() + zzefVarZza.zzyw.longValue();
                    }
                    if (this.zzwl != null && zzefVarZza != null && zzefVarZza.zzyu != null && zzefVarZza.zzyx != null) {
                        z = true;
                    }
                    if (z) {
                        this.zzwb += zzefVarZza.zzyu.longValue() + zzefVarZza.zzyx.longValue();
                    }
                } else if (action2 == 3) {
                    this.zzvy++;
                }
            } catch (zzdw unused) {
            }
        } else {
            this.zzwf = motionEvent.getX();
            this.zzwg = motionEvent.getY();
            this.zzwh = motionEvent.getRawX();
            this.zzwi = motionEvent.getRawY();
            this.zzvv++;
        }
        this.zzwk = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final void zza(int i, int i2, int i3) {
        MotionEvent motionEvent = this.zzvt;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        DisplayMetrics displayMetrics = this.zzwl;
        if (displayMetrics != null) {
            this.zzvt = MotionEvent.obtain(0L, i3, 1, i * displayMetrics.density, this.zzwl.density * i2, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzvt = null;
        }
        this.zzwk = false;
    }

    private final String zza(Context context, String str, int i, View view, Activity activity, byte[] bArr) {
        zzbk.zza zzaVarZza;
        zzda zzdaVarZzci;
        String str2;
        int i2;
        int i3;
        int i4;
        int i5;
        zzbo.zza.zzb zzbVarZza = null;
        if (bArr == null || bArr.length <= 0) {
            zzaVarZza = null;
        } else {
            try {
                zzaVarZza = zzbk.zza.zza(bArr, zzdpq.zzayw());
            } catch (zzdqn unused) {
                zzaVarZza = null;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcmq)).booleanValue();
        if (zBooleanValue) {
            zzdaVarZzci = zzvo != null ? zzvo.zzci() : null;
            str2 = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcnb)).booleanValue() ? "be" : "te";
        } else {
            zzdaVarZzci = null;
            str2 = null;
        }
        try {
            if (i == zzdt.zzxm) {
                zzbVarZza = zza(context, view, activity);
                this.zzwj = true;
                i5 = 1002;
            } else if (i == zzdt.zzxl) {
                zzbVarZza = zzb(context, view, activity);
                i5 = 1008;
            } else {
                zzbVarZza = zza(context, zzaVarZza);
                i5 = 1000;
            }
            if (zBooleanValue && zzdaVarZzci != null) {
                zzdaVarZzci.zza(i5, -1, System.currentTimeMillis() - jCurrentTimeMillis, str2);
            }
        } catch (Exception e) {
            if (zBooleanValue && zzdaVarZzci != null) {
                if (i == zzdt.zzxm) {
                    i2 = 1003;
                } else if (i == zzdt.zzxl) {
                    i2 = 1009;
                } else {
                    i2 = i == zzdt.zzxk ? 1001 : -1;
                }
                zzdaVarZzci.zza(i2, -1, System.currentTimeMillis() - jCurrentTimeMillis, str2, e);
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (zzbVarZza != null) {
            try {
                if (((zzbo.zza) ((zzdqd) zzbVarZza.zzazm())).zzazp() != 0) {
                    String strZzj = zzci.zzj((zzbo.zza) ((zzdqd) zzbVarZza.zzazm()), str);
                    if (!zBooleanValue || zzdaVarZzci == null) {
                        return strZzj;
                    }
                    if (i == zzdt.zzxm) {
                        i4 = 1006;
                    } else if (i == zzdt.zzxl) {
                        i4 = 1010;
                    } else {
                        i4 = i == zzdt.zzxk ? 1004 : -1;
                    }
                    zzdaVarZzci.zza(i4, -1, System.currentTimeMillis() - jCurrentTimeMillis2, str2);
                    return strZzj;
                }
            } catch (Exception e2) {
                String string = Integer.toString(7);
                if (!zBooleanValue || zzdaVarZzci == null) {
                    return string;
                }
                if (i == zzdt.zzxm) {
                    i3 = 1007;
                } else if (i == zzdt.zzxl) {
                    i3 = 1011;
                } else {
                    i3 = i == zzdt.zzxk ? 1005 : -1;
                }
                zzdaVarZzci.zza(i3, -1, System.currentTimeMillis() - jCurrentTimeMillis2, str2, e2);
                return string;
            }
        }
        return Integer.toString(5);
    }
}
