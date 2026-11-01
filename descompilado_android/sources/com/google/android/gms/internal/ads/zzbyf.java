package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyf extends zzbju {
    private final zzaqf zzdnq;
    private final WeakReference<zzbbc> zzfht;
    private final zzbps zzfhu;
    private final zzbko zzfhw;
    private final zzcym zzfhx;
    private final zzbse zzfhz;
    private boolean zzfow;
    private final zzbnp zzfpk;
    private final zzbmk zzfpv;
    private final Context zzlk;

    zzbyf(zzbjt zzbjtVar, Context context, @Nullable zzbbc zzbbcVar, zzbse zzbseVar, zzbps zzbpsVar, zzbmk zzbmkVar, zzbnp zzbnpVar, zzbko zzbkoVar, zzcvb zzcvbVar, zzcym zzcymVar) {
        super(zzbjtVar);
        this.zzfow = false;
        this.zzlk = context;
        this.zzfhz = zzbseVar;
        this.zzfht = new WeakReference<>(zzbbcVar);
        this.zzfhu = zzbpsVar;
        this.zzfpv = zzbmkVar;
        this.zzfpk = zzbnpVar;
        this.zzfhw = zzbkoVar;
        this.zzfhx = zzcymVar;
        this.zzdnq = new zzarc(zzcvbVar.zzdks);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void zzb(boolean z, @Nullable Activity activity) {
        Context context;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjn)).booleanValue()) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            if (zzatv.zzat(this.zzlk)) {
                zzatm.zzeu("Rewarded ad can not be shown when app is not in foreground.");
                this.zzfpv.zzcm(3);
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjo)).booleanValue()) {
                    this.zzfhx.zzgl(this.zzfaq.zzgjp.zzgjm.zzbzx);
                    return;
                }
                return;
            }
        }
        if (this.zzfow) {
            zzatm.zzeu("The rewarded ad have been showed.");
            this.zzfpv.zzcm(1);
            return;
        }
        this.zzfow = true;
        this.zzfhu.zzagj();
        if (activity == null) {
            context = activity;
            context = this.zzlk;
        }
        context = activity;
        this.zzfhz.zza(z, context);
    }

    public final boolean zzajd() {
        return this.zzfow;
    }

    public final zzaqf zzpg() {
        return this.zzdnq;
    }

    public final boolean isClosed() {
        return this.zzfhw.isClosed();
    }

    public final boolean zzph() {
        zzbbc zzbbcVar = this.zzfht.get();
        return (zzbbcVar == null || zzbbcVar.zzzn()) ? false : true;
    }

    public final Bundle getAdMetadata() {
        return this.zzfpk.getAdMetadata();
    }

    public final void finalize() throws Throwable {
        try {
            zzbbc zzbbcVar = this.zzfht.get();
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzctm)).booleanValue()) {
                if (!this.zzfow && zzbbcVar != null) {
                    zzdcs zzdcsVar = zzawx.zzdwa;
                    zzbbcVar.getClass();
                    zzdcsVar.execute(zzbyi.zzh(zzbbcVar));
                }
            } else if (zzbbcVar != null) {
                zzbbcVar.destroy();
            }
        } finally {
            super.finalize();
        }
    }
}
