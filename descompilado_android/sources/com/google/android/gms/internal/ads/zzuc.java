package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzuc {
    private final zztn zzccr;
    private final zzto zzccs;
    private final zzxg zzcct;
    private final zzact zzccu;
    private final zzaps zzccv;
    private final zzaqw zzccw;
    private final zzamv zzccx;
    private final zzacw zzccy;

    public zzuc(zztn zztnVar, zzto zztoVar, zzxg zzxgVar, zzact zzactVar, zzaps zzapsVar, zzaqw zzaqwVar, zzamv zzamvVar, zzacw zzacwVar) {
        this.zzccr = zztnVar;
        this.zzccs = zztoVar;
        this.zzcct = zzxgVar;
        this.zzccu = zzactVar;
        this.zzccv = zzapsVar;
        this.zzccw = zzaqwVar;
        this.zzccx = zzamvVar;
        this.zzccy = zzacwVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzuo.zzof().zza(context, zzuo.zzol().zzbnh, "gmob-apps", bundle, true);
    }

    public final zzux zzb(Context context, String str, zzajd zzajdVar) {
        return new zzui(this, context, str, zzajdVar).zzd(context, false);
    }

    public final zzaaw zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return new zzuk(this, frameLayout, frameLayout2, context).zzd(context, false);
    }

    public final zzaaz zza(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        return new zzuj(this, view, map, map2).zzd(view.getContext(), false);
    }

    public final zzaqg zzc(Context context, String str, zzajd zzajdVar) {
        return new zzue(this, context, str, zzajdVar).zzd(context, false);
    }

    @Nullable
    public final zzamu zzb(Activity activity) {
        zzud zzudVar = new zzud(this, activity);
        Intent intent = activity.getIntent();
        boolean booleanExtra = false;
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzawo.zzes("useClientJar flag not found in activity intent extras.");
        } else {
            booleanExtra = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return zzudVar.zzd(activity, booleanExtra);
    }
}
