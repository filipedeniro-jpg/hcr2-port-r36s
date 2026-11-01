package com.inmobi.commons.core.a;

import android.content.ContentValues;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CrashDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends com.inmobi.commons.core.b.b {
    private static final String a = "c";

    public c() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("crash", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventId TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        bVarA.b();
    }

    public static void a(d dVar) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", dVar.b);
        contentValues.put("componentType", dVar.d);
        contentValues.put("eventType", dVar.c);
        contentValues.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, dVar.a());
        contentValues.put("ts", String.valueOf(dVar.e));
        bVarA.a("crash", contentValues);
        bVarA.b();
    }

    public static List<d> a(int i) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("crash", null, null, null, null, null, "ts ASC", String.valueOf(i));
        ArrayList arrayList = new ArrayList();
        bVarA.b();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(d.a(it.next()));
        }
        return arrayList;
    }

    @Override // com.inmobi.commons.core.b.b
    public final boolean a(long j, String str) {
        List<d> listA = a(1);
        return listA.size() > 0 && TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - listA.get(0).e) > j;
    }

    @Override // com.inmobi.commons.core.b.b
    public final int a(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = bVarA.a("crash");
        bVarA.b();
        return iA;
    }

    @Override // com.inmobi.commons.core.b.b
    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = bVarA.a("crash", "ts<?", new String[]{String.valueOf(System.currentTimeMillis() - (j * 1000))});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(iA);
        sb.append(" expired events from crash DB");
        bVarA.b();
        return iA;
    }

    @Override // com.inmobi.commons.core.b.b
    public final void a(List<Integer> list) {
        if (list.isEmpty()) {
            return;
        }
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < list.size() - 1; i++) {
            stringBuffer.append(list.get(i));
            stringBuffer.append(",");
        }
        stringBuffer.append(String.valueOf(list.get(list.size() - 1)));
        bVarA.a("crash", "id IN (" + ((Object) stringBuffer) + ")", null);
        bVarA.b();
    }

    public static void a() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("crash", null, null, null, null, null, "ts ASC", "1");
        if (!listA.isEmpty()) {
            bVarA.a("crash", "id IN (" + listA.get(0).getAsString("id") + ")", null);
        }
        bVarA.b();
    }

    @Override // com.inmobi.commons.core.b.b
    public final void c(long j, String str) {
        if (com.inmobi.commons.a.a.a()) {
            com.inmobi.commons.core.d.c.b("batch_processing_info").a("crash_last_batch_process", j);
        }
    }

    @Override // com.inmobi.commons.core.b.b
    public final long b(String str) {
        if (com.inmobi.commons.a.a.a()) {
            return com.inmobi.commons.core.d.c.b("batch_processing_info").b("crash_last_batch_process", -1L);
        }
        return -1L;
    }
}
