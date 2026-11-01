package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaym implements SensorEventListener {
    private final SensorManager zzdyn;
    private final Display zzdyp;

    @GuardedBy("sensorThreadLock")
    private float[] zzdys;
    private Handler zzdyt;
    private zzayo zzdyu;
    private final float[] zzdyq = new float[9];
    private final float[] zzdyr = new float[9];
    private final Object zzdyo = new Object();

    zzaym(Context context) {
        this.zzdyn = (SensorManager) context.getSystemService("sensor");
        this.zzdyp = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    final void start() {
        if (this.zzdyt != null) {
            return;
        }
        Sensor defaultSensor = this.zzdyn.getDefaultSensor(11);
        if (defaultSensor == null) {
            zzatm.zzes("No Sensor of TYPE_ROTATION_VECTOR");
            return;
        }
        HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
        handlerThread.start();
        this.zzdyt = new zzczj(handlerThread.getLooper());
        if (this.zzdyn.registerListener(this, defaultSensor, 0, this.zzdyt)) {
            return;
        }
        zzatm.zzes("SensorManager.registerListener failed.");
        stop();
    }

    final void stop() {
        if (this.zzdyt == null) {
            return;
        }
        this.zzdyn.unregisterListener(this);
        this.zzdyt.post(new zzayp(this));
        this.zzdyt = null;
    }

    final void zza(zzayo zzayoVar) {
        this.zzdyu = zzayoVar;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] == 0.0f && fArr[1] == 0.0f && fArr[2] == 0.0f) {
            return;
        }
        synchronized (this.zzdyo) {
            if (this.zzdys == null) {
                this.zzdys = new float[9];
            }
        }
        SensorManager.getRotationMatrixFromVector(this.zzdyq, fArr);
        int rotation = this.zzdyp.getRotation();
        if (rotation == 1) {
            SensorManager.remapCoordinateSystem(this.zzdyq, 2, TsExtractor.TS_STREAM_TYPE_AC3, this.zzdyr);
        } else if (rotation == 2) {
            SensorManager.remapCoordinateSystem(this.zzdyq, TsExtractor.TS_STREAM_TYPE_AC3, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, this.zzdyr);
        } else if (rotation == 3) {
            SensorManager.remapCoordinateSystem(this.zzdyq, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 1, this.zzdyr);
        } else {
            System.arraycopy(this.zzdyq, 0, this.zzdyr, 0, 9);
        }
        zzk(1, 3);
        zzk(2, 6);
        zzk(5, 7);
        synchronized (this.zzdyo) {
            System.arraycopy(this.zzdyr, 0, this.zzdys, 0, 9);
        }
        zzayo zzayoVar = this.zzdyu;
        if (zzayoVar != null) {
            zzayoVar.zzsp();
        }
    }

    final boolean zza(float[] fArr) {
        synchronized (this.zzdyo) {
            if (this.zzdys == null) {
                return false;
            }
            System.arraycopy(this.zzdys, 0, fArr, 0, this.zzdys.length);
            return true;
        }
    }

    private final void zzk(int i, int i2) {
        float[] fArr = this.zzdyr;
        float f = fArr[i];
        fArr[i] = fArr[i2];
        fArr[i2] = f;
    }
}
