package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcz implements Runnable {
    private final /* synthetic */ zzda zzvn;

    zzcz(zzda zzdaVar) {
        this.zzvn = zzdaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zBooleanValue;
        if (this.zzvn.zzvr != null) {
            return;
        }
        synchronized (zzda.zzvp) {
            if (this.zzvn.zzvr != null) {
                return;
            }
            boolean z = false;
            try {
                zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcmp)).booleanValue();
            } catch (IllegalStateException unused) {
                zBooleanValue = false;
            }
            if (zBooleanValue) {
                try {
                    zzda.zzvq = new zzse(this.zzvn.zzvo.zzlk, "ADSHIELD", null);
                } catch (Throwable unused2) {
                }
            }
            z = zBooleanValue;
            this.zzvn.zzvr = Boolean.valueOf(z);
            zzda.zzvp.open();
        }
    }
}
