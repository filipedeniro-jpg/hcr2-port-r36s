package com.inmobi.ads.c;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: InterstitialPreLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends com.inmobi.ads.c.a {
    private static final String d = "b";
    private static volatile b e;
    private static final Object f = new Object();
    private static List<a> g = new LinkedList();

    public static b d() {
        b bVar = e;
        if (bVar == null) {
            synchronized (f) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    private b() {
        super("int");
    }

    public static void a(i iVar) {
        HashMap map = new HashMap();
        map.put("type", iVar.b());
        map.put("plId", Long.valueOf(iVar.d));
        map.put("clientRequestId", iVar.l);
    }

    public static void a(String str, i iVar) {
        HashMap map = new HashMap();
        map.put("errorCode", str);
        map.put("type", iVar.b());
        map.put("plId", Long.valueOf(iVar.d));
        map.put("clientRequestId", iVar.l);
    }

    /* JADX INFO: compiled from: InterstitialPreLoader.java */
    static class a extends i.b {
        private bi a;

        @Override // com.inmobi.ads.i.b
        public final boolean i() {
            return false;
        }

        a(bi biVar) {
            this.a = biVar;
        }

        @Override // com.inmobi.ads.i.b
        public final void a(boolean z) {
            String unused = b.d;
        }

        @Override // com.inmobi.ads.i.b
        public final void a() {
            String unused = b.d;
            b.g.remove(this);
        }

        @Override // com.inmobi.ads.i.b
        public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            String unused = b.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            i iVarRemove = com.inmobi.ads.c.a.a.remove(this.a);
            if (inMobiAdRequestStatus.getStatusCode() == InMobiAdRequestStatus.StatusCode.NO_FILL) {
                iVarRemove.d("PreLoadServerNoFill");
            }
            b.g.remove(this);
        }
    }

    static /* synthetic */ void a(b bVar) {
        if (!b.c(bVar.c).a || a.size() < b.c(bVar.c).c) {
            return;
        }
        bj.a();
        ArrayList arrayList = (ArrayList) bj.a(bVar.c);
        Iterator<Map.Entry<bi, i>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<bi, i> next = it.next();
            if (!arrayList.contains(next.getKey())) {
                next.getValue().v();
                it.remove();
                StringBuilder sb = new StringBuilder("Removing extra ad unit from ad unit cache. Pid:");
                sb.append(next.getKey().a);
                sb.append(" tp:");
                sb.append(next.getKey().b);
            }
        }
    }
}
