package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzagm implements zzagb, zzagk {
    private final zzbbc zzcyx;
    private final Context zzlk;

    public zzagm(Context context, zzawv zzawvVar, @Nullable zzdf zzdfVar, com.google.android.gms.ads.internal.zzb zzbVar) throws zzbbp {
        this.zzlk = context;
        com.google.android.gms.ads.internal.zzp.zzjz();
        this.zzcyx = zzbbl.zza(context, zzbct.zzaan(), "", false, false, zzdfVar, zzawvVar, null, null, null, zzrz.zzmk(), null, false);
        this.zzcyx.getView().setWillNotDraw(true);
    }

    @Override // com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, Map map) {
        zzage.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, JSONObject jSONObject) {
        zzage.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagu
    public final void zzb(String str, JSONObject jSONObject) {
        zzage.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagb
    public final void zzk(String str, String str2) {
        zzage.zza(this, str, str2);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzuo.zzof();
        if (zzawe.zzwc()) {
            runnable.run();
        } else {
            zzatv.zzdsk.post(runnable);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzagu
    public final void zzct(final String str) {
        runOnUiThread(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzagl
            private final String zzcyl;
            private final zzagm zzcyw;

            {
                this.zzcyw = this;
                this.zzcyl = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzcyw.zzcy(this.zzcyl);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcq(String str) {
        runOnUiThread(new zzagq(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", str)));
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcr(String str) {
        runOnUiThread(new zzagp(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcs(String str) {
        runOnUiThread(new zzags(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzahr
    public final void zza(String str, zzadx<? super zzahr> zzadxVar) {
        this.zzcyx.zza(str, new zzagr(this, zzadxVar));
    }

    @Override // com.google.android.gms.internal.ads.zzahr
    public final void zzb(String str, final zzadx<? super zzahr> zzadxVar) {
        this.zzcyx.zza(str, new Predicate(zzadxVar) { // from class: com.google.android.gms.internal.ads.zzago
            private final zzadx zzcyz;

            {
                this.zzcyz = zzadxVar;
            }

            @Override // com.google.android.gms.common.util.Predicate
            public final boolean apply(Object obj) {
                zzadx zzadxVar2 = (zzadx) obj;
                return (zzadxVar2 instanceof zzagr) && ((zzagr) zzadxVar2).zzczc.equals(this.zzcyz);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zza(zzagj zzagjVar) {
        zzbcm zzbcmVarZzyy = this.zzcyx.zzyy();
        zzagjVar.getClass();
        zzbcmVarZzyy.zza(zzagn.zzb(zzagjVar));
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final zzahu zzqz() {
        return new zzaht(this);
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void destroy() {
        this.zzcyx.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final boolean isDestroyed() {
        return this.zzcyx.isDestroyed();
    }

    final /* synthetic */ void zzcy(String str) {
        this.zzcyx.zzct(str);
    }
}
