package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxh extends zzabn {
    private final zzbtx zzfjc;
    private final zzbtp zzfma;
    private final zzbup zzfoy;
    private final Context zzzc;

    public zzbxh(Context context, zzbtx zzbtxVar, zzbup zzbupVar, zzbtp zzbtpVar) {
        this.zzzc = context;
        this.zzfjc = zzbtxVar;
        this.zzfoy = zzbupVar;
        this.zzfma = zzbtpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final IObjectWrapper zzqi() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final String zzco(String str) {
        return this.zzfjc.zzahv().get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final zzaas zzcp(String str) {
        return this.zzfjc.zzaht().get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final List<String> getAvailableAssetNames() {
        SimpleArrayMap<String, zzaae> simpleArrayMapZzaht = this.zzfjc.zzaht();
        SimpleArrayMap<String, String> simpleArrayMapZzahv = this.zzfjc.zzahv();
        String[] strArr = new String[simpleArrayMapZzaht.size() + simpleArrayMapZzahv.size()];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < simpleArrayMapZzaht.size()) {
            strArr[i3] = simpleArrayMapZzaht.keyAt(i2);
            i2++;
            i3++;
        }
        while (i < simpleArrayMapZzahv.size()) {
            strArr[i3] = simpleArrayMapZzahv.keyAt(i);
            i++;
            i3++;
        }
        return Arrays.asList(strArr);
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final String getCustomTemplateId() {
        return this.zzfjc.getCustomTemplateId();
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final void performClick(String str) {
        this.zzfma.zzfp(str);
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final void recordImpression() {
        this.zzfma.zzagz();
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final zzwk getVideoController() {
        return this.zzfjc.getVideoController();
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final void destroy() {
        this.zzfma.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final IObjectWrapper zzqn() {
        return ObjectWrapper.wrap(this.zzzc);
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final boolean zzt(IObjectWrapper iObjectWrapper) {
        Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(objUnwrap instanceof ViewGroup) || !this.zzfoy.zza((ViewGroup) objUnwrap)) {
            return false;
        }
        this.zzfjc.zzahq().zza(new zzbxk(this));
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final boolean zzqo() {
        return this.zzfma.zzahh() && this.zzfjc.zzahr() != null && this.zzfjc.zzahq() == null;
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final boolean zzqp() {
        IObjectWrapper iObjectWrapperZzahs = this.zzfjc.zzahs();
        if (iObjectWrapperZzahs != null) {
            com.google.android.gms.ads.internal.zzp.zzkn().zzae(iObjectWrapperZzahs);
            return true;
        }
        zzatm.zzeu("Trying to start OMID session before creation.");
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final void zzu(IObjectWrapper iObjectWrapper) {
        Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if ((objUnwrap instanceof View) && this.zzfjc.zzahs() != null) {
            this.zzfma.zzy((View) objUnwrap);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzabo
    public final void zzqq() {
        String strZzahu = this.zzfjc.zzahu();
        if ("Google".equals(strZzahu)) {
            zzatm.zzeu("Illegal argument specified for omid partner name.");
        } else {
            this.zzfma.zzg(strZzahu, false);
        }
    }
}
