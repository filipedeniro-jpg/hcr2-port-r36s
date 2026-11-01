package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzapt implements RewardItem {
    private final zzape zzdnk;

    public zzapt(zzape zzapeVar) {
        this.zzdnk = zzapeVar;
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public final String getType() {
        zzape zzapeVar = this.zzdnk;
        if (zzapeVar == null) {
            return null;
        }
        try {
            return zzapeVar.getType();
        } catch (RemoteException e) {
            zzawo.zzd("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public final int getAmount() {
        zzape zzapeVar = this.zzdnk;
        if (zzapeVar == null) {
            return 0;
        }
        try {
            return zzapeVar.getAmount();
        } catch (RemoteException e) {
            zzawo.zzd("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
