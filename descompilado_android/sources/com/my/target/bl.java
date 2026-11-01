package com.my.target;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: EnvironmentParamsDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bl extends bm {
    private boolean hp = true;
    private boolean hq = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean k(@NonNull Context context) {
        return h("android.permission.ACCESS_FINE_LOCATION", context) || h("android.permission.ACCESS_COARSE_LOCATION", context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h(@NonNull String str, @NonNull Context context) {
        int iCheckCallingOrSelfPermission;
        try {
            iCheckCallingOrSelfPermission = context.checkCallingOrSelfPermission(str);
        } catch (Throwable th) {
            g.a("unable to check " + str + " permission! Unexpected throwable in Context.checkCallingOrSelfPermission() method: " + th.getMessage());
            iCheckCallingOrSelfPermission = -1;
        }
        return iCheckCallingOrSelfPermission == 0;
    }

    public boolean aM() {
        return this.hq;
    }

    public boolean aN() {
        return this.hp;
    }

    public void g(boolean z) {
        this.hq = z;
    }

    public void h(boolean z) {
        this.hp = z;
    }

    @Override // com.my.target.bm
    public synchronized void collectData(@NonNull Context context) {
        removeAll();
        if (this.hp) {
            if (h("android.permission.ACCESS_FINE_LOCATION", context) || h("android.permission.ACCESS_COARSE_LOCATION", context)) {
                l(context);
            }
            if (this.hq) {
                if (h("android.permission.ACCESS_WIFI_STATE", context)) {
                    m(context);
                }
                if (h("android.permission.ACCESS_COARSE_LOCATION", context)) {
                    n(context);
                }
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    private void l(@NonNull Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null) {
            return;
        }
        Location location = null;
        long j = 0;
        String str = null;
        float f = Float.MAX_VALUE;
        for (String str2 : locationManager.getAllProviders()) {
            try {
                Location lastKnownLocation = locationManager.getLastKnownLocation(str2);
                if (lastKnownLocation != null) {
                    g.a("locationProvider: " + str2);
                    float accuracy = lastKnownLocation.getAccuracy();
                    long time = lastKnownLocation.getTime();
                    if (location == null || (time > j && accuracy < f)) {
                        str = str2;
                        location = lastKnownLocation;
                        f = accuracy;
                        j = time;
                    }
                }
            } catch (SecurityException unused) {
                g.a("No permissions for get geo data");
            }
        }
        if (location != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(location.getLatitude());
            sb.append(",");
            sb.append(location.getLongitude());
            sb.append(",");
            sb.append(location.getAccuracy());
            sb.append(",");
            sb.append(location.getSpeed());
            sb.append(",");
            long j2 = j / 1000;
            sb.append(j2);
            addParam("location", sb.toString());
            addParam(i.V, str);
            g.a("location: " + location.getLatitude() + ", " + location.getLongitude() + " accuracy = " + location.getAccuracy() + " speed = " + location.getSpeed() + " time = " + j2 + "  provider: " + str);
        }
    }

    @SuppressLint({"HardwareIds"})
    private void m(@NonNull Context context) {
        c cVar = new c(context);
        if (cVar.hS != null) {
            WifiInfo wifiInfo = cVar.hS;
            String bssid = wifiInfo.getBSSID();
            if (bssid == null) {
                bssid = "";
            }
            int linkSpeed = wifiInfo.getLinkSpeed();
            int networkId = wifiInfo.getNetworkId();
            int rssi = wifiInfo.getRssi();
            String ssid = wifiInfo.getSSID();
            if (ssid == null) {
                ssid = "";
            }
            addParam("wifi", bssid + "," + ssid + "," + rssi + "," + networkId + "," + linkSpeed);
            StringBuilder sb = new StringBuilder();
            sb.append("mac: ");
            sb.append(wifiInfo.getMacAddress());
            g.a(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ip: ");
            sb2.append(wifiInfo.getIpAddress());
            g.a(sb2.toString());
            g.a("wifi: " + bssid + "," + ssid + "," + rssi + "," + networkId + "," + linkSpeed);
        }
        if (cVar.hT != null) {
            int i = 1;
            for (ScanResult scanResult : cVar.hT) {
                if (i < 6) {
                    g.a(scanResult.level + "");
                    String str = scanResult.BSSID;
                    if (str == null) {
                        str = "";
                    }
                    String str2 = scanResult.SSID;
                    if (str2 == null) {
                        str2 = "";
                    }
                    addParam("wifi" + i, str + "," + str2 + "," + scanResult.level);
                    g.a("wifi" + i + ": " + str + "," + str2 + "," + scanResult.level);
                    i++;
                }
            }
        }
    }

    private void n(@NonNull Context context) {
        a aVar = new a(context);
        if (aVar.hs != null) {
            int i = 0;
            while (i < aVar.hs.size()) {
                StringBuilder sb = new StringBuilder();
                b bVar = (b) aVar.hs.get(i);
                if (!"cdma".equals(bVar.type)) {
                    sb.append(bVar.type);
                    sb.append(",");
                    sb.append(bVar.hx);
                    sb.append(",");
                    sb.append(bVar.hy);
                    sb.append(",");
                    sb.append(bVar.mcc);
                    sb.append(",");
                    sb.append(bVar.mnc);
                    sb.append(",");
                    sb.append(bVar.level);
                    sb.append(",");
                    sb.append(bVar.hz);
                    sb.append(",");
                    sb.append(bVar.hA);
                    sb.append(",");
                    sb.append(bVar.hB);
                    sb.append(",");
                    sb.append(bVar.hC);
                    sb.append(",");
                    sb.append(bVar.hD);
                    sb.append(",");
                    sb.append(bVar.hE);
                    sb.append(",");
                    sb.append(bVar.hF);
                } else {
                    sb.append(bVar.hG);
                    sb.append(",");
                    sb.append(bVar.hH);
                    sb.append(",");
                    sb.append(bVar.hI);
                    sb.append(",");
                    sb.append(bVar.hJ);
                    sb.append(",");
                    sb.append(bVar.hK);
                    sb.append(",");
                    sb.append(bVar.hL);
                    sb.append(",");
                    sb.append(bVar.level);
                    sb.append(",");
                    sb.append(bVar.hM);
                    sb.append(",");
                    sb.append(bVar.hA);
                    sb.append(",");
                    sb.append(bVar.hN);
                    sb.append(",");
                    sb.append(bVar.hz);
                    sb.append(",");
                    sb.append(bVar.hO);
                    sb.append(",");
                    sb.append(bVar.hP);
                    sb.append(",");
                    sb.append(bVar.hQ);
                    sb.append(",");
                    sb.append(bVar.hR);
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i.T);
                sb2.append(i != 0 ? Integer.valueOf(i) : "");
                addParam(sb2.toString(), sb.toString());
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: EnvironmentParamsDataProvider.java */
    private static class a {

        @Nullable
        private String hr;

        @Nullable
        private ArrayList<b> hs;

        a(@NonNull Context context) {
            this.hs = null;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return;
            }
            try {
                if (Build.VERSION.SDK_INT >= 17 && bl.h("android.permission.ACCESS_COARSE_LOCATION", context)) {
                    this.hs = b(telephonyManager);
                }
                if (this.hs == null || this.hs.isEmpty()) {
                    if (bl.h("android.permission.ACCESS_FINE_LOCATION", context) || bl.h("android.permission.ACCESS_COARSE_LOCATION", context)) {
                        this.hs = a(telephonyManager);
                    }
                }
            } catch (Exception e) {
                g.a("Environment provider exception " + e.getMessage());
            }
        }

        @Nullable
        @SuppressLint({"MissingPermission"})
        private ArrayList<b> a(@NonNull TelephonyManager telephonyManager) {
            CellLocation cellLocation = telephonyManager.getCellLocation();
            if (cellLocation == null || !(cellLocation instanceof GsmCellLocation)) {
                return null;
            }
            ArrayList<b> arrayList = new ArrayList<>();
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            b bVar = new b("gsm");
            arrayList.add(bVar);
            bVar.hx = gsmCellLocation.getCid();
            bVar.hy = gsmCellLocation.getLac();
            this.hr = telephonyManager.getNetworkOperator();
            if (!TextUtils.isEmpty(this.hr)) {
                try {
                    bVar.mcc = Integer.parseInt(this.hr.substring(0, 3));
                    bVar.mnc = Integer.parseInt(this.hr.substring(3));
                } catch (Exception unused) {
                    g.a("unable to substring network operator " + this.hr);
                }
            }
            g.a("current cell: " + bVar.hx + "," + bVar.hy + "," + bVar.mcc + "," + bVar.mnc);
            return arrayList;
        }

        @Nullable
        @SuppressLint({"MissingPermission"})
        @TargetApi(17)
        private ArrayList<b> b(@NonNull TelephonyManager telephonyManager) {
            b bVar;
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo == null) {
                return null;
            }
            ArrayList<b> arrayList = new ArrayList<>();
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo.isRegistered()) {
                    if (cellInfo instanceof CellInfoLte) {
                        bVar = new b("lte");
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
                        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
                        bVar.hx = cellIdentity.getCi();
                        bVar.hy = Integer.MAX_VALUE;
                        bVar.mcc = cellIdentity.getMcc();
                        bVar.mnc = cellIdentity.getMnc();
                        bVar.level = cellSignalStrength.getLevel();
                        bVar.hz = cellSignalStrength.getDbm();
                        bVar.hA = cellSignalStrength.getAsuLevel();
                        bVar.hB = cellSignalStrength.getTimingAdvance();
                        if (Build.VERSION.SDK_INT >= 24) {
                            bVar.hC = cellIdentity.getEarfcn();
                        }
                        bVar.hD = Integer.MAX_VALUE;
                        bVar.hE = Integer.MAX_VALUE;
                        bVar.hF = cellIdentity.getTac();
                    } else if (cellInfo instanceof CellInfoGsm) {
                        bVar = new b("gsm");
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        CellSignalStrengthGsm cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
                        bVar.hx = cellIdentity2.getCid();
                        bVar.hy = cellIdentity2.getLac();
                        bVar.mcc = cellIdentity2.getMcc();
                        bVar.mnc = cellIdentity2.getMnc();
                        bVar.level = cellSignalStrength2.getLevel();
                        bVar.hz = cellSignalStrength2.getDbm();
                        bVar.hA = cellSignalStrength2.getAsuLevel();
                        if (Build.VERSION.SDK_INT >= 26) {
                            bVar.hB = cellSignalStrength2.getTimingAdvance();
                        } else {
                            bVar.hB = Integer.MAX_VALUE;
                        }
                        bVar.hC = Integer.MAX_VALUE;
                        if (Build.VERSION.SDK_INT >= 24) {
                            bVar.hD = cellIdentity2.getBsic();
                        }
                        bVar.hE = cellIdentity2.getPsc();
                        bVar.hF = Integer.MAX_VALUE;
                    } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                        bVar = new b("wcdma");
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                        CellSignalStrengthWcdma cellSignalStrength3 = cellInfoWcdma.getCellSignalStrength();
                        bVar.hx = cellIdentity3.getCid();
                        bVar.hy = cellIdentity3.getLac();
                        bVar.mcc = cellIdentity3.getMcc();
                        bVar.mnc = cellIdentity3.getMnc();
                        bVar.level = cellSignalStrength3.getLevel();
                        bVar.hz = cellSignalStrength3.getDbm();
                        bVar.hA = cellSignalStrength3.getAsuLevel();
                        bVar.hB = Integer.MAX_VALUE;
                        if (Build.VERSION.SDK_INT >= 24) {
                            bVar.hC = cellIdentity3.getUarfcn();
                        }
                        bVar.hD = Integer.MAX_VALUE;
                        bVar.hE = cellIdentity3.getPsc();
                        bVar.hF = Integer.MAX_VALUE;
                    } else if (cellInfo instanceof CellInfoCdma) {
                        bVar = new b("cdma");
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        CellIdentityCdma cellIdentity4 = cellInfoCdma.getCellIdentity();
                        CellSignalStrengthCdma cellSignalStrength4 = cellInfoCdma.getCellSignalStrength();
                        bVar.hG = cellIdentity4.getNetworkId();
                        bVar.hH = cellIdentity4.getSystemId();
                        bVar.hI = cellIdentity4.getBasestationId();
                        bVar.hJ = cellIdentity4.getLatitude();
                        bVar.hK = cellIdentity4.getLongitude();
                        bVar.hL = cellSignalStrength4.getCdmaLevel();
                        bVar.level = cellSignalStrength4.getLevel();
                        bVar.hM = cellSignalStrength4.getEvdoLevel();
                        bVar.hA = cellSignalStrength4.getAsuLevel();
                        bVar.hN = cellSignalStrength4.getCdmaDbm();
                        bVar.hz = cellSignalStrength4.getDbm();
                        bVar.hO = cellSignalStrength4.getEvdoDbm();
                        bVar.hP = cellSignalStrength4.getEvdoEcio();
                        bVar.hQ = cellSignalStrength4.getCdmaEcio();
                        bVar.hR = cellSignalStrength4.getEvdoSnr();
                    }
                    arrayList.add(bVar);
                }
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: EnvironmentParamsDataProvider.java */
    private static class b {
        static final String ht = "cdma";
        static final String hu = "gsm";
        static final String hv = "wcdma";
        static final String hw = "lte";
        public final String type;
        int hx = Integer.MAX_VALUE;
        int hy = Integer.MAX_VALUE;
        int mcc = Integer.MAX_VALUE;
        int mnc = Integer.MAX_VALUE;
        int level = Integer.MAX_VALUE;
        int hz = Integer.MAX_VALUE;
        int hA = Integer.MAX_VALUE;
        int hB = Integer.MAX_VALUE;
        int hC = Integer.MAX_VALUE;
        int hD = Integer.MAX_VALUE;
        int hE = Integer.MAX_VALUE;
        int hF = Integer.MAX_VALUE;
        int hG = Integer.MAX_VALUE;
        int hH = Integer.MAX_VALUE;
        int hI = Integer.MAX_VALUE;
        int hJ = Integer.MAX_VALUE;
        int hK = Integer.MAX_VALUE;
        int hL = Integer.MAX_VALUE;
        int hM = Integer.MAX_VALUE;
        int hN = Integer.MAX_VALUE;
        int hO = Integer.MAX_VALUE;
        int hP = Integer.MAX_VALUE;
        int hQ = Integer.MAX_VALUE;
        int hR = Integer.MAX_VALUE;

        b(String str) {
            this.type = str;
        }
    }

    /* JADX INFO: compiled from: EnvironmentParamsDataProvider.java */
    private static class c {
        WifiInfo hS;
        List<ScanResult> hT;

        @SuppressLint({"MissingPermission"})
        c(@NonNull Context context) {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null && wifiManager.isWifiEnabled()) {
                    this.hS = wifiManager.getConnectionInfo();
                    if (Build.VERSION.SDK_INT < 24 || bl.k(context)) {
                        this.hT = wifiManager.getScanResults();
                    }
                    if (this.hT != null) {
                        Collections.sort(this.hT, new Comparator<ScanResult>() { // from class: com.my.target.bl.c.1
                            @Override // java.util.Comparator
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public int compare(ScanResult scanResult, ScanResult scanResult2) {
                                if (scanResult.level < scanResult2.level) {
                                    return 1;
                                }
                                return scanResult.level > scanResult2.level ? -1 : 0;
                            }
                        });
                    }
                }
            } catch (SecurityException unused) {
                g.a("No permissions for access to wifi state");
            }
        }
    }
}
