package com.inmobi.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: IceCollector.java */
/* JADX INFO: loaded from: classes2.dex */
class h {
    private static final String b = "h";
    a a;

    h() {
        HandlerThread handlerThread = new HandlerThread("DataCollectionHandler");
        handlerThread.start();
        this.a = new a(handlerThread.getLooper());
    }

    public final synchronized void a() {
        this.a.b = false;
        if (!this.a.hasMessages(3)) {
            this.a.removeMessages(2);
            this.a.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: compiled from: IceCollector.java */
    static class a extends Handler {
        private List<l> a;
        private boolean b;

        a(Looper looper) {
            super(looper);
            this.a = new ArrayList();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                String unused = h.b;
                sendEmptyMessage(3);
                return;
            }
            if (i == 2) {
                String unused2 = h.b;
                removeMessages(3);
                sendEmptyMessage(4);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                k kVar = new k();
                kVar.a = m.a().d();
                kVar.c = this.a;
                n.a();
                kVar.b = n.b();
                p.b bVar = o.a().a.a;
                final i iVar = new i(new j(bVar.e, bVar.f, bVar.g, o.a().d(), kVar));
                new Thread(new Runnable() { // from class: com.inmobi.a.i.1
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i2 = 0;
                        while (i2 <= i.this.b.a) {
                            String unused3 = i.a;
                            long jElapsedRealtime = SystemClock.elapsedRealtime();
                            com.inmobi.commons.core.network.d dVarA = new com.inmobi.commons.core.network.e(i.this.b).a();
                            try {
                                n.a().a(i.this.b.g());
                                n.a().b(dVarA.c());
                                n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
                            } catch (Exception e) {
                                String unused4 = i.a;
                                new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                            }
                            if (dVarA.a()) {
                                String unused5 = i.a;
                                i2++;
                                if (i2 > i.this.b.a) {
                                    try {
                                        com.inmobi.commons.core.e.b.a().a(new com.inmobi.commons.core.e.f("signals", "RetryCountExceeded"));
                                        return;
                                    } catch (Exception e2) {
                                        String unused6 = i.a;
                                        StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                        sb.append(e2.getMessage());
                                        sb.append(")");
                                        return;
                                    }
                                }
                                try {
                                    Thread.sleep(i.this.b.b * 1000);
                                } catch (InterruptedException unused7) {
                                    String unused8 = i.a;
                                }
                            } else {
                                String unused9 = i.a;
                                try {
                                    HashMap map = new HashMap();
                                    map.put("url", i.this.b.q);
                                    map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - 0));
                                    map.put("payloadSize", Long.valueOf(i.this.b.g() + dVarA.c()));
                                    com.inmobi.commons.core.e.b.a();
                                    com.inmobi.commons.core.e.b.a("signals", "NICElatency", map);
                                    HashMap map2 = new HashMap();
                                    map2.put("sessionId", com.inmobi.commons.core.utilities.b.h.a().a);
                                    n nVarA = n.a();
                                    map2.put("totalWifiSentBytes", Long.valueOf(nVarA.a));
                                    map2.put("totalWifiReceivedBytes", Long.valueOf(nVarA.b));
                                    map2.put("totalCarrierSentBytes", Long.valueOf(nVarA.c));
                                    map2.put("totalCarrierReceivedBytes", Long.valueOf(nVarA.d));
                                    map2.put("totalNetworkTime", Long.valueOf(nVarA.e));
                                    com.inmobi.commons.core.e.b.a();
                                    com.inmobi.commons.core.e.b.a("signals", "SDKNetworkStats", map2);
                                    return;
                                } catch (Exception e3) {
                                    String unused10 = i.a;
                                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                                    sb2.append(e3.getMessage());
                                    sb2.append(")");
                                    return;
                                }
                            }
                        }
                    }
                }).start();
                this.a = new ArrayList();
                return;
            }
            String unused3 = h.b;
            if (this.b) {
                sendEmptyMessage(2);
                return;
            }
            final l lVar = new l();
            lVar.a = com.inmobi.a.b.b.a();
            lVar.c = m.a().e();
            p.b bVar2 = o.a().a.a;
            boolean z = false;
            if (!(bVar2.k && bVar2.a)) {
                a(lVar);
            } else {
                if (com.inmobi.commons.a.a.a()) {
                    String[] strArr = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"};
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 3) {
                            z = true;
                            break;
                        } else if (!com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "signals", strArr[i2])) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                if (!z || !com.inmobi.a.b.c.a(new com.inmobi.a.b.c.a() { // from class: com.inmobi.a.h.a.1
                    @Override // com.inmobi.a.b.c.a
                    public final void a() {
                        String unused4 = h.b;
                        a.this.a(lVar);
                    }

                    @Override // com.inmobi.a.b.c.a
                    public final void a(List<com.inmobi.a.b.a> list) {
                        String unused4 = h.b;
                        l lVar2 = lVar;
                        lVar2.b = list;
                        a.this.a(lVar2);
                    }
                })) {
                    a(lVar);
                }
            }
            sendEmptyMessageDelayed(3, o.a().a.a.b * 1000);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(l lVar) {
            if (this.a != null) {
                if ((lVar.a == null && lVar.b == null) ? false : true) {
                    this.a.add(lVar);
                    if (this.a.size() > o.a().a.a.d) {
                        try {
                            com.inmobi.commons.core.e.b.a().a(new com.inmobi.commons.core.e.f("signals", "SampleSizeExceeded"));
                        } catch (Exception e) {
                            String unused = h.b;
                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                            sb.append(e.getMessage());
                            sb.append(")");
                        }
                        while (this.a.size() > o.a().a.a.d) {
                            this.a.remove(0);
                        }
                    }
                }
            }
        }
    }
}
