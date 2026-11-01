package com.inmobi.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.inmobi.commons.core.network.NetworkError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: VastResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bx implements by {
    List<bv> a;
    String b;
    String c;
    List<NativeTracker> d;
    List<bt> e;
    int f;
    private String g;
    private bt h;
    private c.j i;
    private bv j;

    private static boolean a(double d, double d2, double d3) {
        return d3 > d && d3 <= d2;
    }

    public bx(c.j jVar) {
        this.j = null;
        this.a = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.i = jVar;
        this.f = 0;
    }

    private bx(List<NativeTracker> list, c.j jVar) {
        this(jVar);
        if (list.size() != 0) {
            this.d = new ArrayList(list);
        }
    }

    public bx(String str, String str2, String str3, List<NativeTracker> list, List<bt> list2, c.j jVar) {
        this(list, jVar);
        if (list2.size() != 0) {
            this.e = new ArrayList(list2);
        }
        this.g = str;
        this.a.add(new bv(str));
        this.b = str2;
        this.c = str3;
    }

    @Override // com.inmobi.ads.by
    public final String a() {
        return this.c;
    }

    @Override // com.inmobi.ads.by
    public final String b() {
        bv bvVarA;
        Iterator<bv> it;
        boolean zHasNext;
        boolean zA;
        boolean zA2;
        int i;
        String str = this.g;
        if (str != null) {
            return str;
        }
        com.inmobi.ads.cache.d.a();
        List<String> listD = com.inmobi.ads.cache.d.d();
        bv bvVarB = null;
        if (listD.isEmpty()) {
            bvVarA = null;
            break;
        }
        Iterator<bv> it2 = this.a.iterator();
        do {
            if (!it2.hasNext()) {
                bvVarA = null;
                break;
            }
            bvVarA = it2.next();
        } while (!listD.contains(bvVarA.a));
        if (bvVarA != null) {
            this.j = bvVarA;
            this.g = bvVarA.a;
            return this.g;
        }
        double d = this.i.b;
        Double.isNaN(d);
        double d2 = (d * 2.0d) / 1048576.0d;
        double d3 = this.i.c;
        double d4 = 1.0d;
        Double.isNaN(d3);
        double d5 = (d3 * 1.0d) / 1048576.0d;
        for (bv bvVar : this.a) {
            String[] strArrSplit = this.b.split(":");
            try {
                i = (Integer.parseInt(strArrSplit[1]) * 60) + Integer.parseInt(strArrSplit[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                i = 0;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            double d6 = bvVar.b;
            Double.isNaN(d6);
            double d7 = i;
            Double.isNaN(d7);
            double d8 = ((d6 * d4) * d7) / 8192.0d;
            bvVar.c = d8;
            if (a(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d2, d8)) {
                bvVarA = a(bvVarA, bvVar, d8);
            } else if (a(d2, d5, d8)) {
                bvVarB = b(bvVarB, bvVar, d8);
            }
            d4 = 1.0d;
        }
        a(bvVarA, bvVarB);
        if (TextUtils.isEmpty(this.g)) {
            c.C0072c c0072c = this.i.d;
            if (c0072c.a || this.a.size() == 0) {
                return this.g;
            }
            CountDownLatch countDownLatch = new CountDownLatch(this.a.size());
            try {
                a(c0072c, countDownLatch);
                countDownLatch.await(c0072c.b, TimeUnit.MILLISECONDS);
                while (zHasNext) {
                    if (!zA) {
                        if (zA2) {
                            bvVarB = b(bvVarB, it.next(), r2.c);
                        }
                    }
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered an unexpected error in getting vast header response; ").append(e2.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                while (zHasNext) {
                    if (!zA) {
                        if (zA2) {
                            bvVarB = b(bvVarB, it.next(), r2.c);
                        }
                    }
                }
            } finally {
                for (bv bvVar2 : this.a) {
                    double d9 = bvVar2.c;
                    if (a(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d2, d9)) {
                        bvVarA = a(bvVarA, bvVar2, d9);
                    } else if (a(d2, d5, d9)) {
                        bvVarB = b(bvVarB, bvVar2, d9);
                    }
                }
                a(bvVarA, bvVarB);
            }
        }
        return this.g;
    }

    private void a(c.C0072c c0072c, CountDownLatch countDownLatch) {
        Iterator<bv> it = this.a.iterator();
        while (it.hasNext()) {
            final bw bwVar = new bw(it.next(), c0072c.b, countDownLatch);
            bwVar.c = SystemClock.elapsedRealtime();
            bw.d.execute(new Runnable() { // from class: com.inmobi.ads.bw.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.inmobi.commons.core.network.d dVarA = new com.inmobi.commons.core.network.f(bw.this.a).a();
                        if (dVarA != null) {
                            if (dVarA.a()) {
                                bw.this.a(dVarA);
                                return;
                            }
                            bw bwVar2 = bw.this;
                            try {
                                com.inmobi.a.n.a().a(bwVar2.a.g());
                                com.inmobi.a.n.a().b(dVarA.c());
                                com.inmobi.a.n.a().c(SystemClock.elapsedRealtime() - bwVar2.c);
                                if (bwVar2.b.get() != null) {
                                    bwVar2.b.get().c = dVarA.c / 1048576;
                                }
                            } catch (Exception e) {
                                new StringBuilder("Handling Vast Media Header Request success encountered an unexpected error: ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            } finally {
                                bwVar2.a();
                            }
                        }
                    } catch (Exception e2) {
                        String unused = bw.e;
                        new StringBuilder("Network request failed with unexpected error: ").append(e2.getMessage());
                        NetworkError networkError = new NetworkError(NetworkError.ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                        com.inmobi.commons.core.network.d dVar = new com.inmobi.commons.core.network.d();
                        dVar.b = networkError;
                        bw.this.a(dVar);
                    }
                }
            });
        }
    }

    private void a(bv bvVar, bv bvVar2) {
        if (bvVar != null) {
            this.j = bvVar;
            this.g = bvVar.a;
        } else if (bvVar2 != null) {
            this.j = bvVar2;
            this.g = bvVar2.a;
        }
    }

    @Override // com.inmobi.ads.by
    public final List<bv> c() {
        return this.a;
    }

    @Override // com.inmobi.ads.by
    public final List<NativeTracker> d() {
        return this.d;
    }

    @Override // com.inmobi.ads.by
    public final List<bt> e() {
        return this.e;
    }

    @Override // com.inmobi.ads.by
    public final void a(bt btVar) {
        this.h = btVar;
    }

    @Override // com.inmobi.ads.by
    public final bt f() {
        return this.h;
    }

    final void a(NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    private static bv a(bv bvVar, bv bvVar2, double d) {
        return (bvVar != null && d <= bvVar.c) ? bvVar : bvVar2;
    }

    private static bv b(bv bvVar, bv bvVar2, double d) {
        return (bvVar != null && d >= bvVar.c) ? bvVar : bvVar2;
    }
}
