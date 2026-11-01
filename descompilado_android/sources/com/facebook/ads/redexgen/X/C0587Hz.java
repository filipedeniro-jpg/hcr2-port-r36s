package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0587Hz implements SensorEventListener {
    private final Context B;
    private long C;
    private long D;
    private long E;

    @Nullable
    private SensorManager J;
    private int K = 0;
    private float F = -1.0f;
    private float G = -1.0f;
    private float H = -1.0f;
    private final Set<I2> I = new CopyOnWriteArraySet();

    public C0587Hz(Context context) {
        this.B = context;
    }

    public final void A(I2 listener) {
        if (this.I.isEmpty()) {
            B();
        } else if (this.I.contains(listener)) {
            return;
        }
        this.I.add(listener);
    }

    private void B() {
        this.J = (SensorManager) this.B.getSystemService("sensor");
        if (this.J == null) {
            Toast.makeText(this.B, "Sensors not supported", 1).show();
        }
        boolean zRegisterListener = false;
        try {
            zRegisterListener = this.J.registerListener(this, this.J.getDefaultSensor(1), 3);
        } catch (Exception e) {
            Toast.makeText(this.B, "Shaking not supported", 1).show();
        }
        if (!zRegisterListener && this.J != null) {
            this.J.unregisterListener(this);
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != 1) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.C > 500) {
            this.K = 0;
        }
        if (jElapsedRealtime - this.E <= 100) {
            return;
        }
        if ((Math.abs(((((event.values[0] + event.values[1]) + event.values[2]) - this.F) - this.G) - this.H) / (jElapsedRealtime - this.E)) * 10000.0f > 800.0f) {
            int i = this.K + 1;
            this.K = i;
            if (i >= 3 && jElapsedRealtime - this.D > 1000) {
                this.D = jElapsedRealtime;
                this.K = 0;
                Iterator<I2> it = this.I.iterator();
                while (it.hasNext()) {
                    it.next().IF();
                }
            }
            this.C = jElapsedRealtime;
        }
        this.E = jElapsedRealtime;
        this.F = event.values[0];
        this.G = event.values[1];
        this.H = event.values[2];
    }
}
