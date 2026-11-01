package com.my.target;

import androidx.annotation.NonNull;
import java.util.ArrayList;

/* JADX INFO: compiled from: AdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class al {

    @NonNull
    private final ArrayList<aq> cf = new ArrayList<>();

    public abstract int getBannersCount();

    public void d(@NonNull ArrayList<aq> arrayList) {
        this.cf.addAll(arrayList);
    }

    @NonNull
    public ArrayList<aq> F() {
        return new ArrayList<>(this.cf);
    }

    @NonNull
    public ArrayList<aq> q(@NonNull String str) {
        ArrayList<aq> arrayList = new ArrayList<>();
        for (aq aqVar : this.cf) {
            if (str.equals(aqVar.getType())) {
                arrayList.add(aqVar);
            }
        }
        return arrayList;
    }
}
