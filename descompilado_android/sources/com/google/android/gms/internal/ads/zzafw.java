package com.google.android.gms.internal.ads;

import android.content.Context;
import com.amazon.device.ads.WebRequest;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzafw extends zzagf<zzahr> implements zzagb, zzagk {
    private final zzbdh zzcym;
    private zzagj zzcyn;

    public zzafw(Context context, zzawv zzawvVar) throws zzbbp {
        try {
            this.zzcym = new zzbdh(context, new zzagc(this));
            this.zzcym.setWillNotDraw(true);
            this.zzcym.addJavascriptInterface(new zzafz(this), "GoogleJsInterface");
            com.google.android.gms.ads.internal.zzp.zzjy().zza(context, zzawvVar.zzbnh, this.zzcym.getSettings());
            super.zzg(this);
        } catch (Throwable th) {
            throw new zzbbp("Init failed.", th);
        }
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

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcq(String str) {
        zzcr(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", str));
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcr(final String str) {
        zzawx.zzdwa.execute(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzafv
            private final zzafw zzcyk;
            private final String zzcyl;

            {
                this.zzcyk = this;
                this.zzcyl = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzcyk.zzcw(this.zzcyl);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzcs(final String str) {
        zzawx.zzdwa.execute(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzafy
            private final zzafw zzcyk;
            private final String zzcyl;

            {
                this.zzcyk = this;
                this.zzcyl = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzcyk.zzcv(this.zzcyl);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zza(zzagj zzagjVar) {
        this.zzcyn = zzagjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final void destroy() {
        this.zzcym.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final boolean isDestroyed() {
        return this.zzcym.isDestroyed();
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    public final zzahu zzqz() {
        return new zzaht(this);
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzagu
    public final void zzct(final String str) {
        zzawx.zzdwa.execute(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzafx
            private final zzafw zzcyk;
            private final String zzcyl;

            {
                this.zzcyk = this;
                this.zzcyl = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzcyk.zzcu(this.zzcyl);
            }
        });
    }

    final /* synthetic */ void zzcu(String str) {
        this.zzcym.zzct(str);
    }

    final /* synthetic */ void zzcv(String str) {
        this.zzcym.loadUrl(str);
    }

    final /* synthetic */ void zzcw(String str) {
        this.zzcym.loadData(str, WebRequest.CONTENT_TYPE_HTML, "UTF-8");
    }
}
