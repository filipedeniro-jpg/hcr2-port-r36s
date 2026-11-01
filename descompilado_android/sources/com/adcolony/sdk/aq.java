package com.adcolony.sdk;

import android.media.SoundPool;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class aq {
    final String a;
    private final int b;
    private HashMap<Integer, Integer> c = new HashMap<>();
    private HashMap<Integer, Integer> d = new HashMap<>();
    private HashMap<Integer, Integer> f = new HashMap<>();
    private HashMap<Integer, Boolean> e = new HashMap<>();
    private HashMap<Integer, Integer> g = new HashMap<>();
    private HashMap<String, Integer> h = new HashMap<>();
    private SoundPool i = new SoundPool(50, 3, 0);

    aq(final String str, final int i) {
        this.a = str;
        this.b = i;
        this.i.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.adcolony.sdk.aq.1
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public void onLoadComplete(SoundPool soundPool, int i2, int i3) {
                JSONObject jSONObjectA = y.a();
                y.b(jSONObjectA, "id", ((Integer) aq.this.f.get(Integer.valueOf(i2))).intValue());
                y.a(jSONObjectA, "ad_session_id", str);
                if (i3 == 0) {
                    new af("AudioPlayer.on_ready", i, jSONObjectA).b();
                    aq.this.g.put(aq.this.f.get(Integer.valueOf(i2)), Integer.valueOf(i2));
                } else {
                    new af("AudioPlayer.on_error", i, jSONObjectA).b();
                }
            }
        });
    }

    void a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        int iLoad = this.i.load(y.b(jSONObjectC, "filepath"), 1);
        int i = y.d(jSONObjectC, "repeats") ? -1 : 0;
        this.f.put(Integer.valueOf(iLoad), Integer.valueOf(y.c(jSONObjectC, "id")));
        new aa.a().a("Load audio with id = ").a(iLoad).a(aa.d);
        this.d.put(Integer.valueOf(iLoad), Integer.valueOf(i));
        this.e.put(Integer.valueOf(iLoad), false);
    }

    void b(af afVar) {
        this.i.unload(this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue());
    }

    void c(af afVar) {
        int iIntValue = this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue();
        if (!this.e.get(Integer.valueOf(iIntValue)).booleanValue()) {
            int iPlay = this.i.play(iIntValue, 1.0f, 1.0f, 0, this.d.get(Integer.valueOf(iIntValue)).intValue(), 1.0f);
            if (iPlay != 0) {
                this.c.put(Integer.valueOf(iIntValue), Integer.valueOf(iPlay));
                return;
            }
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", y.c(afVar.c(), "id"));
            y.a(jSONObjectA, "ad_session_id", this.a);
            new af("AudioPlayer.on_error", this.b, jSONObjectA).b();
            return;
        }
        this.i.resume(this.c.get(Integer.valueOf(iIntValue)).intValue());
    }

    void d(af afVar) {
        int iIntValue = this.g.get(Integer.valueOf(y.c(afVar.c(), "id"))).intValue();
        this.i.pause(this.c.get(Integer.valueOf(iIntValue)).intValue());
        this.e.put(Integer.valueOf(iIntValue), true);
    }

    void e(af afVar) {
        this.i.stop(this.c.get(this.g.get(Integer.valueOf(y.c(afVar.c(), "id")))).intValue());
    }

    SoundPool a() {
        return this.i;
    }
}
