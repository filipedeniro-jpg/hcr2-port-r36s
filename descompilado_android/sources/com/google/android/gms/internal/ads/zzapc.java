package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzapc extends zzaph {
    private final String type;
    private final int zzdng;

    public zzapc(String str, int i) {
        this.type = str;
        this.zzdng = i;
    }

    @Override // com.google.android.gms.internal.ads.zzape
    public final String getType() {
        return this.type;
    }

    @Override // com.google.android.gms.internal.ads.zzape
    public final int getAmount() {
        return this.zzdng;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzapc)) {
            zzapc zzapcVar = (zzapc) obj;
            if (Objects.equal(this.type, zzapcVar.type) && Objects.equal(Integer.valueOf(this.zzdng), Integer.valueOf(zzapcVar.zzdng))) {
                return true;
            }
        }
        return false;
    }
}
