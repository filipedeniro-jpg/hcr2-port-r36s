package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public class zzagf<ReferenceT> implements zzagg {

    @GuardedBy("this")
    private final Map<String, CopyOnWriteArrayList<zzadx<? super ReferenceT>>> zzcyq = new HashMap();
    private ReferenceT zzcyr;

    public final void zzg(ReferenceT referencet) {
        this.zzcyr = referencet;
    }

    @Override // com.google.android.gms.internal.ads.zzagg
    public final boolean zzcx(@Nullable String str) {
        return str != null && zzg(Uri.parse(str));
    }

    public final boolean zzg(Uri uri) {
        if (!"gmsg".equalsIgnoreCase(uri.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            return false;
        }
        zzh(uri);
        return true;
    }

    public final void zzh(Uri uri) {
        String path = uri.getPath();
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzb(path, zzatv.zzi(uri));
    }

    private final synchronized void zzb(final String str, final Map<String, String> map) {
        if (zzatm.isLoggable(2)) {
            String strValueOf = String.valueOf(str);
            zzatm.zzdy(strValueOf.length() != 0 ? "Received GMSG: ".concat(strValueOf) : new String("Received GMSG: "));
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 4 + String.valueOf(str3).length());
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(str3);
                zzatm.zzdy(sb.toString());
            }
        }
        CopyOnWriteArrayList<zzadx<? super ReferenceT>> copyOnWriteArrayList = this.zzcyq.get(str);
        if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
            for (final zzadx<? super ReferenceT> zzadxVar : copyOnWriteArrayList) {
                zzawx.zzdwa.execute(new Runnable(this, zzadxVar, map) { // from class: com.google.android.gms.internal.ads.zzagi
                    private final zzagf zzcyt;
                    private final zzadx zzcyu;
                    private final Map zzcyv;

                    {
                        this.zzcyt = this;
                        this.zzcyu = zzadxVar;
                        this.zzcyv = map;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcyt.zza(this.zzcyu, this.zzcyv);
                    }
                });
            }
            return;
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzctn)).booleanValue() && com.google.android.gms.ads.internal.zzp.zzkc().zzud() != null) {
            zzawx.zzdvx.execute(new Runnable(str) { // from class: com.google.android.gms.internal.ads.zzagh
                private final String zzcys;

                {
                    this.zzcys = str;
                }

                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    com.google.android.gms.ads.internal.zzp.zzkc().zzud().zzcm(this.zzcys.substring(1));
                }
            });
        }
    }

    public final synchronized void zza(String str, zzadx<? super ReferenceT> zzadxVar) {
        CopyOnWriteArrayList<zzadx<? super ReferenceT>> copyOnWriteArrayList = this.zzcyq.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.zzcyq.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(zzadxVar);
    }

    public final synchronized void zzb(String str, zzadx<? super ReferenceT> zzadxVar) {
        CopyOnWriteArrayList<zzadx<? super ReferenceT>> copyOnWriteArrayList = this.zzcyq.get(str);
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.remove(zzadxVar);
    }

    public final synchronized void zza(String str, Predicate<zzadx<? super ReferenceT>> predicate) {
        CopyOnWriteArrayList<zzadx<? super ReferenceT>> copyOnWriteArrayList = this.zzcyq.get(str);
        if (copyOnWriteArrayList == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (zzadx<? super ReferenceT> zzadxVar : copyOnWriteArrayList) {
            if (predicate.apply(zzadxVar)) {
                arrayList.add(zzadxVar);
            }
        }
        copyOnWriteArrayList.removeAll(arrayList);
    }

    public final synchronized void reset() {
        this.zzcyq.clear();
    }

    final /* synthetic */ void zza(zzadx zzadxVar, Map map) {
        zzadxVar.zza(this.zzcyr, map);
    }
}
