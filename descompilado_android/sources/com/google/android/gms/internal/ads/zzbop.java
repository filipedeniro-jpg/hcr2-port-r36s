package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbop extends zzbos<AppEventListener> implements zzadi {
    public zzbop(Set<zzbqc<AppEventListener>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzadi
    public final synchronized void onAppEvent(final String str, final String str2) {
        zza(new zzbou(str, str2) { // from class: com.google.android.gms.internal.ads.zzboo
            private final String zzcyl;
            private final String zzcys;

            {
                this.zzcys = str;
                this.zzcyl = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((AppEventListener) obj).onAppEvent(this.zzcys, this.zzcyl);
            }
        });
    }
}
