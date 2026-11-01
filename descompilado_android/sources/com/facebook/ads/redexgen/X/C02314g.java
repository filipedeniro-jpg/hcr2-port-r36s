package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4g, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02314g implements SensorEventListener {
    private static final String K = C02314g.class.getSimpleName();
    private final EnumC02053g B;
    private final C02354k C;
    private final Context D;
    private final List<Sensor> E;
    private final Handler F;
    private final C02334i H;
    private final SensorManager J;
    private final Map<Integer, EnumC02274c> I = new HashMap();
    private final HandlerThread G = new HandlerThread("bd_sm_thread_handler");

    @SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    public C02314g(Context context, C02354k biometricSignalValueHandler, EnumC02053g bdIntegrationSite) {
        this.D = context;
        this.G.start();
        this.F = new Handler(this.G.getLooper());
        this.J = (SensorManager) context.getSystemService("sensor");
        this.E = this.J.getSensorList(-1);
        this.C = biometricSignalValueHandler;
        this.H = new C02334i(this.C, bdIntegrationSite);
        this.B = bdIntegrationSite;
    }

    @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
    public final synchronized void A(List<EnumC02274c> list) {
        int iA;
        try {
            for (EnumC02274c enumC02274c : list) {
                if (enumC02274c != null && (iA = enumC02274c.A()) != -1) {
                    Map<Integer, EnumC02274c> map = this.I;
                    int type = enumC02274c.ordinal();
                    if (map.get(Integer.valueOf(type)) == null) {
                        if (iA != 0) {
                            for (Sensor sensor : this.E) {
                                int type2 = sensor.getType();
                                if (iA == type2) {
                                    this.J.registerListener(this, sensor, 3, this.F);
                                    Map<Integer, EnumC02274c> map2 = this.I;
                                    int type3 = enumC02274c.ordinal();
                                    map2.put(Integer.valueOf(type3), enumC02274c);
                                }
                            }
                        } else {
                            int[] iArr = C02324h.B;
                            int type4 = enumC02274c.ordinal();
                            switch (iArr[type4]) {
                                case 1:
                                    this.H.B(this.D);
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(K, "Error registering", e);
        }
    }

    @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
    public final synchronized void B(List<EnumC02274c> list) {
        try {
            if (this.I != null) {
                if (list != null) {
                    for (EnumC02274c enumC02274c : list) {
                        if (enumC02274c.A() != -1) {
                            if (enumC02274c.A() == 0) {
                                this.I.remove(Integer.valueOf(enumC02274c.ordinal()));
                                switch (enumC02274c) {
                                    case ORIENTATION_SENSOR:
                                        this.H.A();
                                        continue;
                                }
                            }
                            for (Sensor sensor : this.E) {
                                if (enumC02274c.A() == sensor.getType()) {
                                    try {
                                        this.J.unregisterListener(this, sensor);
                                        this.I.remove(Integer.valueOf(enumC02274c.ordinal()));
                                        break;
                                    } catch (Exception e) {
                                        Log.e(K, "Error unregistering", e);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.J.unregisterListener(this);
                    this.H.A();
                    this.I.clear();
                }
            }
        } catch (Exception e2) {
            Log.e(K, "Error unregistering", e2);
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
    public final void onSensorChanged(SensorEvent event) {
        if (event == null) {
        }
        try {
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            final C3T c3t = this.B == EnumC02053g.OFFSITE ? null : new C3T(this.D == null ? "" : this.D.getPackageName());
            switch (event.sensor.getType()) {
                case 1:
                    final C02284d c02284d = new C02284d(new float[]{event.values[0], event.values[1], event.values[2]});
                    this.C.A(new AbstractC02043f<C02284d>(jElapsedRealtime, c3t, c02284d) { // from class: com.facebook.ads.redexgen.X.3X
                        {
                            C3S c3s = C3S.SENSOR;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.SensorSignalRawValue> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<C02284d> abstractC02043f) {
                            return ((C02284d) B()).A(abstractC02043f.m231B());
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return ((C02284d) B()).m236B();
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            return ((C02284d) B()).C(inputObject);
                        }
                    }, EnumC02374m.ADD_ACCELEROMETER_SENSOR_VALUE);
                    break;
                case 2:
                    final C02284d c02284d2 = new C02284d(new float[]{event.values[0], event.values[1], event.values[2]});
                    this.C.A(new AbstractC02043f<C02284d>(jElapsedRealtime, c3t, c02284d2) { // from class: com.facebook.ads.redexgen.X.3X
                        {
                            C3S c3s = C3S.SENSOR;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.SensorSignalRawValue> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<C02284d> abstractC02043f) {
                            return ((C02284d) B()).A(abstractC02043f.m231B());
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return ((C02284d) B()).m236B();
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            return ((C02284d) B()).C(inputObject);
                        }
                    }, EnumC02374m.ADD_MAGNETOMETER_SENSOR_VALUE);
                    break;
                case 3:
                case 7:
                case 9:
                case 10:
                case 11:
                case 14:
                case 15:
                case 16:
                case 17:
                case 19:
                    break;
                case 4:
                    final C02284d c02284d3 = new C02284d(new float[]{event.values[0], event.values[1], event.values[2]});
                    this.C.A(new AbstractC02043f<C02284d>(jElapsedRealtime, c3t, c02284d3) { // from class: com.facebook.ads.redexgen.X.3X
                        {
                            C3S c3s = C3S.SENSOR;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.SensorSignalRawValue> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<C02284d> abstractC02043f) {
                            return ((C02284d) B()).A(abstractC02043f.m231B());
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return ((C02284d) B()).m236B();
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            return ((C02284d) B()).C(inputObject);
                        }
                    }, EnumC02374m.ADD_GYROSCOPE_SENSOR_VALUE);
                    break;
                case 5:
                    final Float fValueOf = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_LIGHT_SENSOR_VALUE);
                    break;
                case 6:
                    final Float fValueOf2 = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf2) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_BAROMETER_SENSOR_VALUE);
                    break;
                case 8:
                    final Float fValueOf3 = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf3) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_PROXIMITY_SENSOR_VALUE);
                    break;
                case 12:
                    final Float fValueOf4 = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf4) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_AMBIENT_TEMPERATURE_SENSOR_VALUE);
                    break;
                case 13:
                    final Float fValueOf5 = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf5) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_AMBIENT_TEMPERATURE_SENSOR_VALUE);
                    break;
                case 18:
                    final Float fValueOf6 = Float.valueOf(event.values[0]);
                    this.C.A(new AbstractC02043f<Float>(jElapsedRealtime, c3t, fValueOf6) { // from class: com.facebook.ads.redexgen.X.3c
                        {
                            C3S c3s = C3S.FLOAT;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Float> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<Float> abstractC02043f) {
                            return Math.abs(((Float) B()).floatValue() - abstractC02043f.m231B().floatValue()) < 1.0E-7f;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return 4;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            inputObject.put("v", B());
                            return inputObject;
                        }
                    }, EnumC02374m.ADD_STEP_DETECTOR_SENSOR_VALUE);
                    break;
                case 20:
                    final C02284d c02284d4 = new C02284d(new float[]{event.values[0], event.values[1], event.values[2], event.values[3]});
                    this.C.A(new AbstractC02043f<C02284d>(jElapsedRealtime, c3t, c02284d4) { // from class: com.facebook.ads.redexgen.X.3X
                        {
                            C3S c3s = C3S.SENSOR;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.SensorSignalRawValue> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<C02284d> abstractC02043f) {
                            return ((C02284d) B()).A(abstractC02043f.m231B());
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return ((C02284d) B()).m236B();
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            return ((C02284d) B()).C(inputObject);
                        }
                    }, EnumC02374m.ADD_GEOMAGNETIC_ROTATION_SENSOR_VALUE);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.e(K, "Error on change", e);
        }
    }
}
