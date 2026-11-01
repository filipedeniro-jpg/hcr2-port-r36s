package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: StatResolver.java */
/* JADX INFO: loaded from: classes2.dex */
public class cj {
    private static final cj ki = new cj();

    public static void a(@Nullable aq aqVar, @NonNull Context context) {
        ki.b(aqVar, context);
    }

    public static void a(@Nullable List<aq> list, @NonNull Context context) {
        ki.c(list, context);
    }

    public static void o(@Nullable String str, @NonNull Context context) {
        ki.p(str, context);
    }

    public static void b(@Nullable List<String> list, @NonNull Context context) {
        ki.d(list, context);
    }

    cj() {
    }

    void b(@Nullable final aq aqVar, @NonNull Context context) {
        if (aqVar != null) {
            final Context applicationContext = context.getApplicationContext();
            h.b(new Runnable() { // from class: com.my.target.cj.1
                @Override // java.lang.Runnable
                public void run() {
                    cj.this.c(aqVar);
                    String strR = cj.this.R(aqVar.getUrl());
                    if (strR != null) {
                        ax.an().f(strR, applicationContext);
                    }
                }
            });
        }
    }

    void c(@Nullable final List<aq> list, @NonNull Context context) {
        if (list == null || list.size() <= 0) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        h.b(new Runnable() { // from class: com.my.target.cj.2
            @Override // java.lang.Runnable
            public void run() {
                ax axVarAn = ax.an();
                for (aq aqVar : list) {
                    cj.this.c(aqVar);
                    String strR = cj.this.R(aqVar.getUrl());
                    if (strR != null) {
                        axVarAn.f(strR, applicationContext);
                    }
                }
            }
        });
    }

    void p(@Nullable final String str, @NonNull Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        h.b(new Runnable() { // from class: com.my.target.cj.3
            @Override // java.lang.Runnable
            public void run() {
                String strR = cj.this.R(str);
                if (strR != null) {
                    ax.an().f(strR, applicationContext);
                }
            }
        });
    }

    void d(@Nullable final List<String> list, @NonNull Context context) {
        if (list == null || list.size() <= 0) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        h.b(new Runnable() { // from class: com.my.target.cj.4
            @Override // java.lang.Runnable
            public void run() {
                ax axVarAn = ax.an();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    String strR = cj.this.R((String) it.next());
                    if (strR != null) {
                        axVarAn.f(strR, applicationContext);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String R(@NonNull String str) {
        String strDecode = cl.decode(str);
        if (URLUtil.isNetworkUrl(strDecode)) {
            return strDecode;
        }
        g.a("invalid stat url: " + strDecode);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(@NonNull aq aqVar) {
        if (aqVar instanceof ap) {
            g.a("tracking progress stat value:" + ((ap) aqVar).Y() + " url:" + aqVar.getUrl());
            return;
        }
        if (aqVar instanceof ao) {
            ao aoVar = (ao) aqVar;
            g.a("tracking ovv stat percent:" + aoVar.ag() + " value:" + aoVar.Y() + " ovv:" + aoVar.X() + " url:" + aqVar.getUrl());
            return;
        }
        if (aqVar instanceof an) {
            an anVar = (an) aqVar;
            int iAg = anVar.ag();
            g.a("tracking mrc stat percent: value:" + anVar.Y() + " percent " + iAg + " duration:" + anVar.getDuration() + " url:" + aqVar.getUrl());
            return;
        }
        g.a("tracking stat type:" + aqVar.getType() + " url:" + aqVar.getUrl());
    }
}
