package com.ironsource.mediationsdk.model;

import com.my.target.i;

/* JADX INFO: loaded from: classes2.dex */
public enum PlacementCappingType {
    PER_DAY("d"),
    PER_HOUR(i.HEIGHT);

    public String value;

    PlacementCappingType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
