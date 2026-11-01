package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import net.nend.android.internal.d.g;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.d.m;
import net.nend.android.internal.utilities.j;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendSensorUtility.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private final Context a;

    public d(Context context) {
        this.a = context;
    }

    public k<JSONObject> a() {
        final net.nend.android.internal.d.e eVarA = l.a();
        l.a(a(this.a, 13), a(this.a, 12)).a(new net.nend.android.internal.d.d<m<Integer, Integer>>() { // from class: net.nend.android.internal.utilities.video.d.1
            @Override // net.nend.android.internal.d.d
            public void a(m<Integer, Integer> mVar) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (mVar.a != null) {
                        jSONObject.put("temperature", mVar.a.intValue());
                    }
                    if (mVar.b != null) {
                        jSONObject.put("humidity", mVar.b.intValue());
                    }
                    if (jSONObject.length() == 0) {
                        eVarA.a((Throwable) new Exception("Failed to get the sensor values."));
                    } else {
                        eVarA.a(jSONObject);
                    }
                } catch (JSONException e) {
                    eVarA.a(e.getCause());
                }
            }
        });
        return eVarA.a();
    }

    public k<JSONObject> b() {
        final net.nend.android.internal.d.e eVarA = l.a();
        l.a(b(this.a, 1), b(this.a, 2)).a(new net.nend.android.internal.d.d<m<float[], float[]>>() { // from class: net.nend.android.internal.utilities.video.d.2
            @Override // net.nend.android.internal.d.d
            public void a(m<float[], float[]> mVar) {
                if (mVar.a != null && mVar.b != null) {
                    float[] fArr = new float[9];
                    float[] fArr2 = new float[3];
                    float[] fArr3 = new float[3];
                    float[] fArr4 = new float[3];
                    System.arraycopy(mVar.a, 0, fArr2, 0, fArr2.length);
                    System.arraycopy(mVar.b, 0, fArr3, 0, fArr3.length);
                    if (SensorManager.getRotationMatrix(fArr, null, fArr2, fArr3)) {
                        SensorManager.getOrientation(fArr, fArr4);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("yaw", fArr4[0]);
                            jSONObject.put("pitch", fArr4[1]);
                            jSONObject.put("roll", fArr4[2]);
                            eVarA.a(jSONObject);
                            return;
                        } catch (JSONException e) {
                            eVarA.a(e.getCause());
                            return;
                        }
                    }
                    eVarA.a((Throwable) new Exception("Failed to getRotationMatrix."));
                    return;
                }
                eVarA.a((Throwable) new Exception("Failed to get the sensor values."));
            }
        });
        return eVarA.a();
    }

    public k<JSONObject> a(boolean z) {
        if (!z) {
            j.b("Do not use location information this ad.");
            return l.a((Object) null);
        }
        try {
            Class.forName("com.google.android.gms.location.LocationServices");
            return new c().a(this.a);
        } catch (ClassNotFoundException e) {
            return l.a((Throwable) e);
        }
    }

    private k<Integer> a(Context context, int i) {
        return b(context, i).b((g<? super float[], ? extends k<? extends R>>) new g<float[], k<? extends Integer>>() { // from class: net.nend.android.internal.utilities.video.d.3
            @Override // net.nend.android.internal.d.g
            public k<Integer> a(float[] fArr) {
                return l.a(fArr == null ? null : Integer.valueOf((int) fArr[0]));
            }
        });
    }

    private k<float[]> b(Context context, int i) {
        final net.nend.android.internal.d.e eVarA = l.a();
        final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        final Sensor defaultSensor = sensorManager.getDefaultSensor(i);
        if (defaultSensor != null) {
            sensorManager.registerListener(new SensorEventListener() { // from class: net.nend.android.internal.utilities.video.d.4
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i2) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    sensorManager.unregisterListener(this, defaultSensor);
                    float[] fArr = new float[3];
                    if (sensorEvent.values.length <= fArr.length) {
                        System.arraycopy(sensorEvent.values, 0, fArr, 0, sensorEvent.values.length);
                        eVarA.a(fArr);
                    } else {
                        eVarA.a((Object) null);
                    }
                }
            }, defaultSensor, 2);
        } else {
            eVarA.a((Object) null);
        }
        return eVarA.a();
    }
}
