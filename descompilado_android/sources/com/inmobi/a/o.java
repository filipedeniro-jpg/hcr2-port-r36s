package com.inmobi.a;

import android.content.Context;
import android.location.Criteria;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: compiled from: SignalsComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public class o implements com.inmobi.commons.core.configs.b.c {
    private static final String b = "o";
    private static final Object c = new Object();
    private static volatile o d;
    private h e;
    private g f;
    private boolean g = false;
    public p a = new p();

    public static o a() {
        o oVar = d;
        if (oVar == null) {
            synchronized (c) {
                oVar = d;
                if (oVar == null) {
                    oVar = new o();
                    d = oVar;
                }
            }
        }
        return oVar;
    }

    private o() {
        com.inmobi.commons.core.configs.b.a().a(this.a, this);
        com.inmobi.commons.core.utilities.b.h.a().a(this.a.a.b());
        m.a();
        m.a(this.a.a.a());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.a = (p) aVar;
        m.a();
        m.a(this.a.a.a());
        com.inmobi.commons.core.utilities.b.h.a().a(this.a.a.b());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            f();
            final m mVarA = m.a();
            try {
                if (m.a && m.c() && mVarA.g()) {
                    if (m.a && m.c() && mVarA.g() && mVarA.b != null) {
                        Criteria criteria = new Criteria();
                        criteria.setBearingAccuracy(2);
                        criteria.setPowerRequirement(2);
                        criteria.setCostAllowed(false);
                        String bestProvider = mVarA.b.getBestProvider(criteria, true);
                        if (bestProvider != null) {
                            mVarA.b.requestSingleUpdate(bestProvider, mVarA, mVarA.c.getLooper());
                        }
                    }
                    if (m.b() && com.inmobi.commons.core.utilities.f.a("signals")) {
                        return;
                    }
                    Context contextB = com.inmobi.commons.a.a.b();
                    try {
                        if (mVarA.d == null) {
                            mVarA.d = new GoogleApiClient.Builder(contextB).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() { // from class: com.inmobi.a.m.2
                                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                                public final void onConnected(@Nullable Bundle bundle) {
                                    String unused = m.e;
                                    boolean unused2 = m.h = true;
                                }

                                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                                public final void onConnectionSuspended(int i) {
                                    boolean unused = m.h = false;
                                    String unused2 = m.e;
                                }
                            }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() { // from class: com.inmobi.a.m.1
                                @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
                                public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                    boolean unused = m.h = false;
                                }
                            }).addApi(LocationServices.API).build();
                            mVarA.d.connect();
                        } else {
                            mVarA.d.connect();
                        }
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder("Error in connecting to GooglePlayServices API : (");
                        sb.append(e.getMessage());
                        sb.append(")");
                    }
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered unexpected error in initializing location collection; ").append(e2.getMessage());
            }
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            n nVarA = n.a();
            if (a().a.a.b()) {
                com.inmobi.commons.core.utilities.b.h.a().c = System.currentTimeMillis();
                try {
                    HashMap map = new HashMap();
                    map.put("sessionId", com.inmobi.commons.core.utilities.b.h.a().a);
                    map.put("totalNetworkTime", Long.valueOf(nVarA.e));
                    map.put("sessionDuration", Long.valueOf(SystemClock.elapsedRealtime() - nVarA.f));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("signals", "SDKSessionEnded", map);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                    sb.append(e.getMessage());
                    sb.append(")");
                }
            }
            if (this.e != null) {
                h hVar = this.e;
                hVar.a.b = true;
                hVar.a.sendEmptyMessageDelayed(2, a().a.a.c * 1000);
            }
            m mVarA = m.a();
            if (m.a && m.c()) {
                if (mVarA.b != null) {
                    mVarA.b.removeUpdates(mVarA);
                }
                if (mVarA.d != null) {
                    mVarA.d.disconnect();
                }
            }
            mVarA.d = null;
        }
    }

    final com.inmobi.commons.core.utilities.uid.d d() {
        return new com.inmobi.commons.core.utilities.uid.d(this.a.p.a);
    }

    private synchronized void f() {
        if (this.g) {
            if (this.a.a.a) {
                n nVarA = n.a();
                if (a().a.a.b()) {
                    String string = UUID.randomUUID().toString();
                    com.inmobi.commons.core.utilities.b.h.a().a = string;
                    com.inmobi.commons.core.utilities.b.h.a().b = System.currentTimeMillis();
                    com.inmobi.commons.core.utilities.b.h.a().c = 0L;
                    nVarA.f = SystemClock.elapsedRealtime();
                    nVarA.a = 0L;
                    nVarA.b = 0L;
                    nVarA.c = 0L;
                    nVarA.d = 0L;
                    nVarA.e = 0L;
                    nVarA.f = 0L;
                    HashMap map = new HashMap();
                    map.put("sessionId", string);
                    try {
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("signals", "SDKSessionStarted", map);
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                        sb.append(e.getMessage());
                        sb.append(")");
                    }
                }
                if (this.e == null) {
                    this.e = new h();
                }
                this.e.a();
            }
        }
    }

    public final void e() {
        if (this.g && this.a.b.a) {
            if (this.f == null) {
                this.f = new g();
            }
            this.f.a(this.a.b);
        }
    }
}
