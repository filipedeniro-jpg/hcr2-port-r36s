package com.inmobi.commons.core.configs;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: ConfigNetworkClient.java */
/* JADX INFO: loaded from: classes2.dex */
class e implements Runnable {
    private static final String a = "com.inmobi.commons.core.configs.e";
    private f b;
    private a c;
    private final f d;

    /* JADX INFO: compiled from: ConfigNetworkClient.java */
    public interface a {
        void a();

        void a(ConfigNetworkResponse.ConfigResponse configResponse);
    }

    e(a aVar, f fVar, f fVar2) {
        this.c = aVar;
        this.b = fVar;
        this.d = fVar2;
    }

    private void a(f fVar, Map<String, ConfigNetworkResponse.ConfigResponse> map) {
        for (Map.Entry<String, ConfigNetworkResponse.ConfigResponse> entry : map.entrySet()) {
            ConfigNetworkResponse.ConfigResponse value = entry.getValue();
            String key = entry.getKey();
            if (!value.a()) {
                this.c.a(value);
                fVar.c.remove(key);
            }
        }
    }

    @NonNull
    private static ConfigNetworkResponse a(f fVar) {
        com.inmobi.commons.core.network.e eVar = new com.inmobi.commons.core.network.e(fVar);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        return new ConfigNetworkResponse(fVar.c, eVar.a(), SystemClock.elapsedRealtime() - jElapsedRealtime);
    }

    private boolean a(f fVar, int i, Map<String, ConfigNetworkResponse.ConfigResponse> map) throws InterruptedException {
        if (i > fVar.a) {
            Iterator<Map.Entry<String, com.inmobi.commons.core.configs.a>> it = fVar.c.entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                if (map.containsKey(key)) {
                    this.c.a(map.get(key));
                }
            }
            return true;
        }
        Thread.sleep(fVar.b * 1000);
        return false;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        int i2 = 0;
        while (i2 <= this.b.a) {
            try {
                ConfigNetworkResponse configNetworkResponseA = a(this.b);
                Map<String, ConfigNetworkResponse.ConfigResponse> map = configNetworkResponseA.a;
                if (!(configNetworkResponseA.a() && this.d != null)) {
                    a(this.b, map);
                    if (this.b.c.isEmpty()) {
                        break;
                    }
                    i2++;
                    if (a(this.b, i2, map)) {
                        break;
                    }
                } else {
                    while (i <= this.d.a) {
                        ConfigNetworkResponse configNetworkResponseA2 = a(this.d);
                        Map<String, ConfigNetworkResponse.ConfigResponse> map2 = configNetworkResponseA2.a;
                        if (!configNetworkResponseA2.a()) {
                            a(this.d, map2);
                            if (this.d.c.isEmpty()) {
                                break;
                            }
                            i++;
                            if (a(this.d, i, map2)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    this.c.a();
                    return;
                }
            } catch (InterruptedException unused) {
                return;
            }
        }
        this.c.a();
    }
}
