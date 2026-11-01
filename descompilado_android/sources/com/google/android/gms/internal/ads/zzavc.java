package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;
import com.my.target.i;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavc {
    public final int count;
    public final String name;
    private final double zzdtq;
    private final double zzdtr;
    public final double zzdts;

    public zzavc(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzdtr = d;
        this.zzdtq = d2;
        this.zzdts = d3;
        this.count = i;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("minBound", Double.valueOf(this.zzdtr)).add("maxBound", Double.valueOf(this.zzdtq)).add("percent", Double.valueOf(this.zzdts)).add(i.aa, Integer.valueOf(this.count)).toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzavc)) {
            return false;
        }
        zzavc zzavcVar = (zzavc) obj;
        return Objects.equal(this.name, zzavcVar.name) && this.zzdtq == zzavcVar.zzdtq && this.zzdtr == zzavcVar.zzdtr && this.count == zzavcVar.count && Double.compare(this.zzdts, zzavcVar.zzdts) == 0;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, Double.valueOf(this.zzdtq), Double.valueOf(this.zzdtr), Double.valueOf(this.zzdts), Integer.valueOf(this.count));
    }
}
