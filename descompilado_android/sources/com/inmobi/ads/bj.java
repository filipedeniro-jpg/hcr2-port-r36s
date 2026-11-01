package com.inmobi.ads;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: PlacementDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class bj {
    private static final String b = "bj";
    private static bj c;
    private static final Object d = new Object();
    public static final String[] a = {"id", FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER, "tp_key", "last_accessed_ts", AppEventsConstants.EVENT_PARAM_AD_TYPE, "m10_context"};

    public static bj a() {
        bj bjVar = c;
        if (bjVar == null) {
            synchronized (d) {
                bjVar = c;
                if (bjVar == null) {
                    bjVar = new bj();
                    c = bjVar;
                }
            }
        }
        return bjVar;
    }

    private bj() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("placement", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL,tp_key TEXT,last_accessed_ts INTEGER NOT NULL,ad_type TEXT NOT NULL,m10_context TEXT NOT NULL,UNIQUE(placement_id,m10_context,tp_key))");
        bVarA.b();
    }

    public static int a(long j, String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = bVarA.a("placement", "ad_type=? AND last_accessed_ts<?", new String[]{str, String.valueOf(System.currentTimeMillis() - (j * 1000))});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(iA);
        sb.append(" expired pids from cache");
        bVarA.b();
        return iA;
    }

    public final synchronized int a(List<bi> list, int i) {
        if (list.size() == 0) {
            return 0;
        }
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        for (int i2 = 0; i2 < list.size(); i2++) {
            bi biVar = list.get(i2);
            String[] strArr = {String.valueOf(biVar.a), biVar.f.toString(), biVar.b};
            ContentValues contentValues = new ContentValues();
            contentValues.put(FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER, Long.valueOf(biVar.a));
            contentValues.put("last_accessed_ts", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("tp_key", biVar.b);
            contentValues.put(AppEventsConstants.EVENT_PARAM_AD_TYPE, biVar.e);
            contentValues.put("m10_context", biVar.f.toString());
            bVarA.a("placement", contentValues, "placement_id = ? AND m10_context = ? AND tp_key=?", strArr);
        }
        int iA = bVarA.a("placement") - i;
        if (iA > 0) {
            List<ContentValues> listA = bVarA.a("placement", new String[]{"id"}, null, null, null, null, "last_accessed_ts ASC", String.valueOf(iA));
            String[] strArr2 = new String[listA.size()];
            for (int i3 = 0; i3 < listA.size(); i3++) {
                strArr2[i3] = String.valueOf(listA.get(i3).getAsInteger("id"));
            }
            bVarA.a("placement", "id IN " + Arrays.toString(strArr2).replace(Constants.RequestParameters.LEFT_BRACKETS, "(").replace(Constants.RequestParameters.RIGHT_BRACKETS, ")"), null);
        }
        bVarA.b();
        return iA;
    }

    public static List<bi> a(String str) {
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("placement", a, "ad_type=? ", new String[]{str}, null, null, null, null);
        bVarA.b();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(new bi(it.next()));
        }
        return arrayList;
    }
}
