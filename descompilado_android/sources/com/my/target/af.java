package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/* JADX INFO: compiled from: AdService.java */
/* JADX INFO: loaded from: classes2.dex */
public class af {

    @Nullable
    private ArrayList<aq> cf;

    @Nullable
    private af cg;

    @Nullable
    private String ch;
    private int ci;
    private boolean ck;
    private boolean cl;
    private boolean cm;

    @Nullable
    private Boolean cn;

    @Nullable
    private Boolean co;

    @Nullable
    private ArrayList<aj> companionBanners;

    @Nullable
    private Boolean cp;

    @Nullable
    private Boolean cq;

    @Nullable
    private Boolean cr;

    @Nullable
    private String ctaText;

    @NonNull
    private final String url;

    @NonNull
    private final ArrayList<af> cc = new ArrayList<>();

    @NonNull
    private final ArrayList<aq> cd = new ArrayList<>();

    @NonNull
    private final ar ce = ar.aa();
    private int id = -1;
    private int position = -1;
    private int cj = -1;
    private float point = -1.0f;
    private float pointP = -1.0f;
    private float allowCloseDelay = -1.0f;

    public static af n(@NonNull String str) {
        return new af(str);
    }

    private af(@NonNull String str) {
        this.url = str;
    }

    public boolean z() {
        return this.cl;
    }

    public void c(boolean z) {
        this.cl = z;
    }

    public int A() {
        return this.cj;
    }

    public boolean B() {
        return this.cm;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void a(@Nullable af afVar) {
        this.cg = afVar;
        if (afVar != null) {
            afVar.setPosition(this.position);
        }
    }

    @Nullable
    public af C() {
        return this.cg;
    }

    public boolean D() {
        return this.ck;
    }

    public void d(boolean z) {
        this.ck = z;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    @NonNull
    public ArrayList<af> E() {
        return this.cc;
    }

    public void a(aq aqVar) {
        this.cd.add(aqVar);
    }

    @NonNull
    public ArrayList<aq> o(@NonNull String str) {
        ArrayList<aq> arrayList = new ArrayList<>();
        for (aq aqVar : this.cd) {
            if (str.equals(aqVar.getType())) {
                arrayList.add(aqVar);
            }
        }
        return arrayList;
    }

    @Nullable
    public ArrayList<aq> F() {
        ArrayList<aq> arrayList = this.cf;
        if (arrayList != null) {
            return new ArrayList<>(arrayList);
        }
        return null;
    }

    @Nullable
    public ArrayList<aj> getCompanionBanners() {
        return this.companionBanners;
    }

    public void b(@Nullable ArrayList<aj> arrayList) {
        this.companionBanners = arrayList;
    }

    public void c(@Nullable ArrayList<aq> arrayList) {
        this.cf = arrayList;
    }

    public int G() {
        return this.ci;
    }

    public void a(int i) {
        this.ci = i;
    }

    public float getPoint() {
        return this.point;
    }

    public void setPoint(float f) {
        this.point = f;
    }

    public float getPointP() {
        return this.pointP;
    }

    public void setPointP(float f) {
        this.pointP = f;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
        af afVar = this.cg;
        if (afVar != null) {
            afVar.setPosition(i);
        }
    }

    @Nullable
    public String H() {
        return this.ch;
    }

    public void p(@Nullable String str) {
        this.ch = str;
    }

    public void b(int i) {
        this.cj = i;
    }

    public void e(boolean z) {
        this.cm = z;
    }

    public void b(@NonNull af afVar) {
        this.cc.add(afVar);
    }

    public void d(@Nullable ArrayList<aq> arrayList) {
        ArrayList<aq> arrayList2 = this.cf;
        if (arrayList2 == null) {
            this.cf = arrayList;
        } else if (arrayList != null) {
            arrayList2.addAll(arrayList);
        }
    }

    @Nullable
    public String getCtaText() {
        return this.ctaText;
    }

    public void setCtaText(@Nullable String str) {
        this.ctaText = str;
    }

    public float getAllowCloseDelay() {
        return this.allowCloseDelay;
    }

    public void setAllowCloseDelay(float f) {
        this.allowCloseDelay = f;
    }

    @Nullable
    public Boolean I() {
        return this.cn;
    }

    public void a(@Nullable Boolean bool) {
        this.cn = bool;
    }

    @Nullable
    public Boolean J() {
        return this.co;
    }

    public void b(@Nullable Boolean bool) {
        this.co = bool;
    }

    @Nullable
    public Boolean K() {
        return this.cp;
    }

    public void c(@Nullable Boolean bool) {
        this.cp = bool;
    }

    @Nullable
    public Boolean L() {
        return this.cq;
    }

    public void d(@Nullable Boolean bool) {
        this.cq = bool;
    }

    @Nullable
    public Boolean M() {
        return this.cr;
    }

    public void e(@Nullable Boolean bool) {
        this.cr = bool;
    }

    @NonNull
    public ar N() {
        return this.ce;
    }
}
