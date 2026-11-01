package com.inmobi.commons.core.f;

import android.content.ContentValues;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.commons.core.d.c;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: TRCDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends com.inmobi.commons.core.b.b {
    private static final String a = "a";

    public a() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("trc", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, eventId TEXT NOT NULL, adMarkup TEXT NOT NULL, eventName TEXT NOT NULL, imPlid INTEGER NOT NULL, requestId TEXT NOT NULL, eventType TEXT NOT NULL, dNettypeRaw TEXT NOT NULL, ts TEXT NOT NULL, adtype TEXT NOT NULL, timestamp TEXT NOT NULL)");
        bVarA.b();
    }

    public static void a(b bVar) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", bVar.b);
        contentValues.put("adMarkup", bVar.c);
        contentValues.put(Constants.ParametersKeys.EVENT_NAME, bVar.d);
        contentValues.put("imPlid", Long.valueOf(bVar.e));
        contentValues.put("requestId", bVar.f);
        contentValues.put("eventType", bVar.g);
        contentValues.put("dNettypeRaw", bVar.h);
        contentValues.put("ts", String.valueOf(bVar.i));
        contentValues.put("adtype", bVar.j);
        contentValues.put("timestamp", String.valueOf(bVar.k));
        bVarA.a("trc", contentValues);
        bVarA.b();
    }

    public static List<b> a(int i, String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", String.valueOf(i));
        bVarA.b();
        ArrayList arrayList = new ArrayList();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(b.a(it.next()));
        }
        return arrayList;
    }

    @Override // com.inmobi.commons.core.b.b
    public final boolean a(long j, String str) {
        List<b> listA = a(1, str);
        return listA.size() > 0 && TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - listA.get(0).i) > j;
    }

    public static boolean c(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iB = bVarA.b("trc", "adtype=?", new String[]{str});
        bVarA.b();
        return iB > 0;
    }

    public static void d(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", "1");
        if (!listA.isEmpty()) {
            bVarA.a("trc", "id IN (" + listA.get(0).getAsString("id") + ")", null);
        }
        bVarA.b();
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
        bVarA.a("trc", "id IN (" + ((Object) stringBuffer) + ")", null);
        bVarA.b();
    }

    @Override // com.inmobi.commons.core.b.b
    public final int a(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iB = bVarA.b("trc", "adtype=?", new String[]{str});
        bVarA.b();
        return iB;
    }

    @Override // com.inmobi.commons.core.b.b
    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = bVarA.a("trc", "ts<? and adtype=?", new String[]{String.valueOf(System.currentTimeMillis() - (j * 1000)), str});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(iA);
        sb.append(" expired events from trc DB");
        bVarA.b();
        return iA;
    }

    @Override // com.inmobi.commons.core.b.b
    public final String b(int i) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("trc", null, "id=?", new String[]{String.valueOf(i)}, null, null, "timestamp ASC", "1");
        bVarA.b();
        if (listA.isEmpty()) {
            return null;
        }
        return b.a(listA.get(0)).j;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0033  */
    private static String e(String str) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != -1396342996) {
            if (iHashCode != -1052618729) {
                if (iHashCode == 104431 && str.equals("int")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                b = 2;
            } else {
                b = -1;
            }
        } else if (str.equals(ai.a.cW)) {
            b = 0;
        } else {
            b = -1;
        }
        if (b != 0) {
            return b != 1 ? "trc_last_native_batch_process" : "trc_last_int_batch_process";
        }
        return "trc_last_banner_batch_process";
    }

    @Override // com.inmobi.commons.core.b.b
    public final void c(long j, String str) {
        String strE = e(str);
        if (com.inmobi.commons.a.a.a()) {
            c.b("batch_processing_info").a(strE, j);
        }
    }

    @Override // com.inmobi.commons.core.b.b
    public final long b(String str) {
        String strE = e(str);
        if (com.inmobi.commons.a.a.a()) {
            return c.b("batch_processing_info").b(strE, -1L);
        }
        return -1L;
    }
}
