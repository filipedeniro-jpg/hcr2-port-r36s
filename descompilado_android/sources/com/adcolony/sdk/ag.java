package com.adcolony.sdk;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ag {
    private ArrayList<ai> a = new ArrayList<>();
    private HashMap<Integer, ai> b = new HashMap<>();
    private int c = 2;
    private JSONArray e = y.b();
    private int f = 1;
    private HashMap<String, ArrayList<ah>> d = new HashMap<>();

    ag() {
    }

    void a(String str, ah ahVar) {
        ArrayList<ah> arrayList = this.d.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.d.put(str, arrayList);
        }
        arrayList.add(ahVar);
    }

    void b(String str, ah ahVar) {
        synchronized (this.d) {
            ArrayList<ah> arrayList = this.d.get(str);
            if (arrayList != null) {
                arrayList.remove(ahVar);
            }
        }
    }

    void a() {
        final Activity activityC;
        l lVarA = a.a();
        if (lVarA.g() || lVarA.h() || (activityC = a.c()) == null) {
            return;
        }
        final ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.adcolony.sdk.ag.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectD = a.a().d().d();
                y.a(jSONObjectD, "os_name", "android");
                ag.this.a(new ADCVMModule(activityC, 1, a.a().o().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5", jSONObjectD, executorServiceNewSingleThreadExecutor));
            }
        });
    }

    ai a(ai aiVar) {
        synchronized (this.a) {
            this.a.add(aiVar);
            this.b.put(Integer.valueOf(aiVar.a()), aiVar);
        }
        return aiVar;
    }

    ai a(int i) {
        synchronized (this.a) {
            ai aiVar = this.b.get(Integer.valueOf(i));
            if (aiVar == null) {
                return null;
            }
            this.a.remove(aiVar);
            this.b.remove(Integer.valueOf(i));
            aiVar.b();
            return aiVar;
        }
    }

    synchronized void b() {
        synchronized (this.a) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                this.a.get(size).c();
            }
        }
        JSONArray jSONArray = null;
        if (this.e.length() > 0) {
            jSONArray = this.e;
            this.e = y.b();
        }
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    final JSONObject jSONObject = jSONArray.getJSONObject(i);
                    final String string = jSONObject.getString("m_type");
                    if (jSONObject.getInt("m_origin") >= 2) {
                        aw.a(new Runnable() { // from class: com.adcolony.sdk.ag.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ag.this.a(string, jSONObject);
                            }
                        });
                    } else {
                        a(string, jSONObject);
                    }
                } catch (JSONException e) {
                    new aa.a().a("JSON error from message dispatcher's updateModules(): ").a(e.toString()).a(aa.h);
                }
            }
        }
    }

    void a(String str, JSONObject jSONObject) {
        synchronized (this.d) {
            ArrayList<ah> arrayList = this.d.get(str);
            if (arrayList != null) {
                af afVar = new af(jSONObject);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    try {
                        arrayList.get(i).a(afVar);
                    } catch (RuntimeException e) {
                        new aa.a().a(e).a(aa.h);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("m_id")) {
                int i = this.f;
                this.f = i + 1;
                jSONObject.put("m_id", i);
            }
            if (!jSONObject.has("m_origin")) {
                jSONObject.put("m_origin", 0);
            }
            int i2 = jSONObject.getInt("m_target");
            if (i2 == 0) {
                synchronized (this) {
                    this.e.put(jSONObject);
                }
            } else {
                ai aiVar = this.b.get(Integer.valueOf(i2));
                if (aiVar != null) {
                    aiVar.a(jSONObject);
                }
            }
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCMessageDispatcher's sendMessage(): ").a(e.toString()).a(aa.h);
        }
    }

    ArrayList<ai> c() {
        return this.a;
    }

    int d() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }

    HashMap<Integer, ai> e() {
        return this.b;
    }
}
