package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.rewarded.RewardItem;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzarc extends zzaqe {
    private final String type;
    private final int zzdng;

    public zzarc(@Nullable zzaqd zzaqdVar) {
        this(zzaqdVar != null ? zzaqdVar.type : "", zzaqdVar != null ? zzaqdVar.zzdng : 1);
    }

    public zzarc(@Nullable RewardItem rewardItem) {
        this(rewardItem != null ? rewardItem.getType() : "", rewardItem != null ? rewardItem.getAmount() : 1);
    }

    public zzarc(String str, int i) {
        this.type = str;
        this.zzdng = i;
    }

    @Override // com.google.android.gms.internal.ads.zzaqf
    public final String getType() throws RemoteException {
        return this.type;
    }

    @Override // com.google.android.gms.internal.ads.zzaqf
    public final int getAmount() throws RemoteException {
        return this.zzdng;
    }
}
