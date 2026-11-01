package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.RewardItem;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaqu implements RewardItem {
    private final zzaqf zzdnq;

    public zzaqu(zzaqf zzaqfVar) {
        this.zzdnq = zzaqfVar;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public final String getType() {
        zzaqf zzaqfVar = this.zzdnq;
        if (zzaqfVar == null) {
            return null;
        }
        try {
            return zzaqfVar.getType();
        } catch (RemoteException e) {
            zzawo.zzd("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public final int getAmount() {
        zzaqf zzaqfVar = this.zzdnq;
        if (zzaqfVar == null) {
            return 0;
        }
        try {
            return zzaqfVar.getAmount();
        } catch (RemoteException e) {
            zzawo.zzd("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
