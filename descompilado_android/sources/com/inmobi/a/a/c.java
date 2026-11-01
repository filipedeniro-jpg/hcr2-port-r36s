package com.inmobi.a.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.inmobi.a.o;
import com.inmobi.a.p;
import com.inmobi.commons.core.utilities.e;
import com.tonyodev.fetch.FetchConst;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: compiled from: CellularInfoUtil.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(17)
public class c {
    private static final String a = "c";

    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static Map<String, String> a() {
        String str;
        HashMap map = new HashMap();
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return map;
        }
        p.b bVar = o.a().a.a;
        if (!(bVar.n && bVar.a)) {
            return map;
        }
        int i = o.a().a.a.m;
        boolean zA = a(i, 2);
        boolean zA2 = a(i, 1);
        a aVar = new a();
        TelephonyManager telephonyManager = (TelephonyManager) contextB.getSystemService("phone");
        if (!zA) {
            int[] iArrA = a(telephonyManager.getNetworkOperator());
            aVar.a = iArrA[0];
            aVar.b = iArrA[1];
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (networkCountryIso != null) {
                aVar.e = networkCountryIso.toLowerCase(Locale.ENGLISH);
            }
        }
        if (!zA2) {
            int[] iArrA2 = a(telephonyManager.getSimOperator());
            aVar.c = iArrA2[0];
            aVar.d = iArrA2[1];
        }
        String str2 = null;
        if (aVar.c == -1 && aVar.d == -1) {
            str = null;
        } else {
            str = aVar.c + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + aVar.d;
        }
        map.put("s-ho", str);
        if (aVar.a != -1 || aVar.b != -1) {
            str2 = aVar.a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + aVar.b;
        }
        map.put("s-co", str2);
        map.put("s-iso", aVar.e);
        return map;
    }

    private static int[] a(String str) {
        int[] iArr = {-1, -1};
        if (str != null && !str.equals("")) {
            try {
                int i = Integer.parseInt(str.substring(0, 3));
                int i2 = Integer.parseInt(str.substring(3));
                iArr[0] = i;
                iArr[1] = i2;
            } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            }
        }
        return iArr;
    }

    @SuppressLint({"NewApi"})
    private static boolean d() {
        if (Build.VERSION.SDK_INT < 28) {
            return true;
        }
        LocationManager locationManager = (LocationManager) com.inmobi.commons.a.a.b().getSystemService("location");
        if (locationManager != null) {
            return locationManager.isLocationEnabled();
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0032  */
    /* JADX WARN: Code duplicated, block: B:36:0x0090  */
    /* JADX WARN: Code duplicated, block: B:43:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:44:0x00c5  */
    public static Map<String, String> b() {
        boolean z;
        Context contextB;
        CellLocation cellLocation;
        List<CellInfo> allCellInfo;
        p.b bVar = o.a().a.a;
        b bVar2 = null;
        if (bVar.p && bVar.a) {
            Context contextB2 = com.inmobi.commons.a.a.b();
            if (contextB2 != null) {
                boolean zA = e.a(contextB2, "signals", "android.permission.ACCESS_COARSE_LOCATION");
                boolean zA2 = e.a(contextB2, "signals", "android.permission.ACCESS_FINE_LOCATION");
                if (zA || zA2) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (z && d() && (contextB = com.inmobi.commons.a.a.b()) != null) {
                TelephonyManager telephonyManager = (TelephonyManager) contextB.getSystemService("phone");
                int[] iArrA = a(telephonyManager.getNetworkOperator());
                String strValueOf = String.valueOf(iArrA[0]);
                String strValueOf2 = String.valueOf(iArrA[1]);
                if (Build.VERSION.SDK_INT >= 17 && (allCellInfo = telephonyManager.getAllCellInfo()) != null) {
                    CellInfo cellInfo = null;
                    for (int i = 0; i < allCellInfo.size(); i++) {
                        cellInfo = allCellInfo.get(i);
                        if (cellInfo.isRegistered()) {
                            break;
                        }
                    }
                    if (cellInfo != null) {
                        bVar2 = new b(cellInfo, strValueOf, strValueOf2, telephonyManager.getNetworkType());
                    } else {
                        cellLocation = telephonyManager.getCellLocation();
                        if (cellLocation != null) {
                            bVar2 = new b();
                            if (cellLocation instanceof CdmaCellLocation) {
                                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                                bVar2.b = Integer.MAX_VALUE;
                                bVar2.c = telephonyManager.getNetworkType();
                                bVar2.a = b.a(strValueOf, cdmaCellLocation.getSystemId(), cdmaCellLocation.getNetworkId(), cdmaCellLocation.getBaseStationId());
                            } else {
                                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                                bVar2.b = Integer.MAX_VALUE;
                                bVar2.c = telephonyManager.getNetworkType();
                                bVar2.a = b.a(strValueOf, strValueOf2, gsmCellLocation.getLac(), gsmCellLocation.getCid(), gsmCellLocation.getPsc(), Integer.MAX_VALUE);
                            }
                        }
                    }
                } else {
                    cellLocation = telephonyManager.getCellLocation();
                    if (cellLocation != null && iArrA[0] != -1) {
                        bVar2 = new b();
                        if (cellLocation instanceof CdmaCellLocation) {
                            CdmaCellLocation cdmaCellLocation2 = (CdmaCellLocation) cellLocation;
                            bVar2.b = Integer.MAX_VALUE;
                            bVar2.c = telephonyManager.getNetworkType();
                            bVar2.a = b.a(strValueOf, cdmaCellLocation2.getSystemId(), cdmaCellLocation2.getNetworkId(), cdmaCellLocation2.getBaseStationId());
                        } else {
                            GsmCellLocation gsmCellLocation2 = (GsmCellLocation) cellLocation;
                            bVar2.b = Integer.MAX_VALUE;
                            bVar2.c = telephonyManager.getNetworkType();
                            bVar2.a = b.a(strValueOf, strValueOf2, gsmCellLocation2.getLac(), gsmCellLocation2.getCid(), gsmCellLocation2.getPsc(), Integer.MAX_VALUE);
                        }
                    }
                }
            }
        }
        HashMap map = new HashMap();
        if (bVar2 != null) {
            map.put("c-sc", bVar2.a().toString());
        }
        return map;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:58:0x0115 A[ADDED_TO_REGION, REMOVE] */
    public static Map<String, String> c() {
        ArrayList arrayList;
        Context contextB;
        List<CellInfo> allCellInfo;
        if (!com.inmobi.commons.a.a.a()) {
            arrayList = new ArrayList();
        } else {
            boolean z = false;
            if (!(com.inmobi.commons.a.a.a() && e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) || !d()) {
                arrayList = new ArrayList();
            } else {
                p.b bVar = o.a().a.a;
                if (!(bVar.o && bVar.a) || (contextB = com.inmobi.commons.a.a.b()) == null) {
                    arrayList = new ArrayList();
                } else {
                    TelephonyManager telephonyManager = (TelephonyManager) contextB.getSystemService("phone");
                    ArrayList arrayList2 = new ArrayList();
                    int[] iArrA = a(telephonyManager.getNetworkOperator());
                    String strValueOf = String.valueOf(iArrA[0]);
                    String strValueOf2 = String.valueOf(iArrA[1]);
                    if (Build.VERSION.SDK_INT >= 17 && (allCellInfo = telephonyManager.getAllCellInfo()) != null) {
                        for (CellInfo cellInfo : allCellInfo) {
                            if (!cellInfo.isRegistered()) {
                                arrayList2.add(new b(cellInfo, strValueOf, strValueOf2, telephonyManager.getNetworkType()));
                            }
                        }
                    } else {
                        List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
                        if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
                            arrayList = new ArrayList();
                        } else {
                            Iterator it = neighboringCellInfo.iterator();
                            if (it.hasNext()) {
                                NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) it.next();
                                b bVar2 = new b();
                                int networkType = neighboringCellInfo2.getNetworkType();
                                bVar2.c = networkType;
                                if (neighboringCellInfo2.getRssi() == 99) {
                                    bVar2.b = Integer.MAX_VALUE;
                                } else {
                                    if (networkType != 3 && networkType != 15) {
                                        switch (networkType) {
                                            case 8:
                                            case 9:
                                            case 10:
                                                z = true;
                                                break;
                                        }
                                    } else {
                                        z = true;
                                    }
                                    if (z) {
                                        bVar2.b = neighboringCellInfo2.getRssi() + FetchConst.ERROR_BAD_REQUEST;
                                    } else {
                                        bVar2.b = (neighboringCellInfo2.getRssi() * 2) + FetchConst.ERROR_REQUEST_ALREADY_EXIST;
                                    }
                                }
                                bVar2.a = b.a(strValueOf, strValueOf2, neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid(), -1, Integer.MAX_VALUE);
                                arrayList2.add(bVar2);
                            } else {
                                arrayList = new ArrayList();
                            }
                        }
                    }
                    arrayList = arrayList2;
                }
            }
        }
        HashMap map = new HashMap();
        if (!arrayList.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(((b) arrayList.get(arrayList.size() - 1)).a());
            map.put("v-sc", jSONArray.toString());
        }
        return map;
    }
}
