package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ah;
import com.my.target.common.models.AudioData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MediaSection.java */
/* JADX INFO: loaded from: classes2.dex */
public class am<T extends ah> extends al {
    public static final int de = 10;
    public static final int df = -1;

    @NonNull
    private final ArrayList<ak<T>> dg = new ArrayList<>();

    @NonNull
    private final ArrayList<af> dh = new ArrayList<>();

    @NonNull
    private final ArrayList<af> di = new ArrayList<>();

    @NonNull
    private final ArrayList<af> dj = new ArrayList<>();
    private int dk = 10;
    private int dl = -1;

    @NonNull
    private final String name;

    /* JADX INFO: compiled from: MediaSection.java */
    public interface a {
        public static final String dm = "preroll";
        public static final String dn = "postroll";

        /* JADX INFO: renamed from: do, reason: not valid java name */
        public static final String f703do = "pauseroll";
        public static final String dp = "midroll";
    }

    @NonNull
    public static am<VideoData> r(@NonNull String str) {
        return t(str);
    }

    @NonNull
    public static am<AudioData> s(@NonNull String str) {
        return t(str);
    }

    @NonNull
    private static <T extends ah> am<T> t(@NonNull String str) {
        return new am<>(str);
    }

    private am(@NonNull String str) {
        this.name = str;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public void d(int i) {
        this.dk = i;
    }

    public int P() {
        return this.dk;
    }

    public int Q() {
        return this.dl;
    }

    public void e(int i) {
        this.dl = i;
    }

    public void a(@NonNull ak<T> akVar) {
        this.dg.add(akVar);
    }

    public void a(@NonNull ak<T> akVar, int i) {
        int size = this.dg.size();
        if (i < 0 || i > size) {
            return;
        }
        this.dg.add(i, akVar);
        for (af afVar : this.dj) {
            int position = afVar.getPosition();
            if (position >= i) {
                afVar.setPosition(position + 1);
            }
        }
    }

    @NonNull
    public List<ak<T>> R() {
        return new ArrayList(this.dg);
    }

    @NonNull
    public ArrayList<af> S() {
        return new ArrayList<>(this.di);
    }

    @Override // com.my.target.al
    public int getBannersCount() {
        return this.dg.size();
    }

    @Nullable
    public af T() {
        if (this.dh.size() > 0) {
            return this.dh.remove(0);
        }
        return null;
    }

    @NonNull
    public ArrayList<af> a(float f) {
        ArrayList<af> arrayList = new ArrayList<>();
        for (af afVar : this.di) {
            if (afVar.getPoint() == f) {
                arrayList.add(afVar);
            }
        }
        if (arrayList.size() > 0) {
            this.di.removeAll(arrayList);
        }
        return arrayList;
    }

    public void c(@NonNull af afVar) {
        if (afVar.B()) {
            this.di.add(afVar);
        } else if (afVar.z()) {
            this.dh.add(afVar);
        } else {
            this.dj.add(afVar);
        }
    }

    public void U() {
        this.dj.clear();
    }

    public void a(@NonNull am<T> amVar) {
        this.dg.addAll(amVar.dg);
        this.dh.addAll(amVar.dh);
        this.di.addAll(amVar.di);
        d(amVar.F());
    }

    public boolean V() {
        return (this.di.isEmpty() && this.dh.isEmpty()) ? false : true;
    }
}
