package com.my.target;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: StatHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public class ar {
    private final Set<aq> dQ = new HashSet();
    private final Set<ap> dR = new HashSet();
    private final ArrayList<ao> dS = new ArrayList<>();
    private final ArrayList<an> dT = new ArrayList<>();

    @NonNull
    public static ar aa() {
        return new ar();
    }

    private ar() {
    }

    public void b(@NonNull aq aqVar) {
        if (aqVar instanceof ap) {
            this.dR.add((ap) aqVar);
            return;
        }
        if (aqVar instanceof ao) {
            ao aoVar = (ao) aqVar;
            if (this.dS.isEmpty()) {
                this.dS.add(aoVar);
                return;
            }
            int size = this.dS.size();
            while (size > 0 && this.dS.get(size - 1).Y() < aoVar.Y()) {
                size--;
            }
            this.dS.add(size, aoVar);
            return;
        }
        if (aqVar instanceof an) {
            this.dT.add((an) aqVar);
        } else {
            this.dQ.add(aqVar);
        }
    }

    @NonNull
    public ArrayList<ao> ab() {
        return new ArrayList<>(this.dS);
    }

    @NonNull
    public ArrayList<an> ac() {
        return new ArrayList<>(this.dT);
    }

    @NonNull
    public Set<ap> ad() {
        return new HashSet(this.dR);
    }

    @NonNull
    public Set<aq> ae() {
        return new HashSet(this.dQ);
    }

    @NonNull
    public ArrayList<aq> x(@NonNull String str) {
        ArrayList<aq> arrayList = new ArrayList<>();
        for (aq aqVar : this.dQ) {
            if (str.equals(aqVar.getType())) {
                arrayList.add(aqVar);
            }
        }
        return arrayList;
    }

    public void a(@NonNull List<ap> list) {
        list.addAll(this.dR);
        Collections.sort(list, new Comparator<ap>() { // from class: com.my.target.ar.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ap apVar, ap apVar2) {
                return (int) (apVar2.Y() - apVar.Y());
            }
        });
    }

    public void a(@NonNull ar arVar, float f) {
        this.dQ.addAll(arVar.ae());
        this.dT.addAll(arVar.ac());
        if (f <= 0.0f) {
            this.dR.addAll(arVar.ad());
            this.dS.addAll(arVar.ab());
            return;
        }
        for (ap apVar : arVar.ad()) {
            float fZ = apVar.Z();
            if (fZ >= 0.0f) {
                apVar.c((fZ * f) / 100.0f);
                apVar.d(-1.0f);
            }
            b(apVar);
        }
        for (ao aoVar : arVar.ab()) {
            float fZ2 = aoVar.Z();
            if (fZ2 >= 0.0f) {
                aoVar.c((fZ2 * f) / 100.0f);
                aoVar.d(-1.0f);
            }
            b(aoVar);
        }
    }

    public void e(@NonNull ArrayList<aq> arrayList) {
        this.dQ.addAll(arrayList);
    }

    public void f(@NonNull ArrayList<ap> arrayList) {
        this.dR.addAll(arrayList);
    }

    public boolean af() {
        return (this.dQ.isEmpty() && this.dR.isEmpty() && this.dS.isEmpty() && this.dT.isEmpty()) ? false : true;
    }
}
