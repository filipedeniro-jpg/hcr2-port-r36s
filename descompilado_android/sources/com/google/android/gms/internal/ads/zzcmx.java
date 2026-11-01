package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmx implements zzcra<zzcmu> {
    private final zzcvk zzffn;
    private final zzdcs zzgdj;
    private final View zzgdk;
    private final Context zzlk;

    public zzcmx(zzdcs zzdcsVar, Context context, zzcvk zzcvkVar, @Nullable ViewGroup viewGroup) {
        this.zzgdj = zzdcsVar;
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzgdk = viewGroup;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcmu> zzalr() {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzciy)).booleanValue()) {
            return zzdcf.zzi(new Exception("Ad Key signal disabled."));
        }
        return this.zzgdj.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcmw
            private final zzcmx zzgdi;

            {
                this.zzgdi = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgdi.zzals();
            }
        });
    }

    final /* synthetic */ zzcmu zzals() throws Exception {
        Context context = this.zzlk;
        zztw zztwVar = this.zzffn.zzbli;
        ArrayList arrayList = new ArrayList();
        View view = this.zzgdk;
        while (view != null) {
            Object parent = view.getParent();
            if (parent == null) {
                break;
            }
            int iIndexOfChild = parent instanceof ViewGroup ? ((ViewGroup) parent).indexOfChild(view) : -1;
            Bundle bundle = new Bundle();
            bundle.putString("type", parent.getClass().getName());
            bundle.putInt("index_of_child", iIndexOfChild);
            arrayList.add(bundle);
            if (!(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return new zzcmu(context, zztwVar, arrayList);
    }
}
