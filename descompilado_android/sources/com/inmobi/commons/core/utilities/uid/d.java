package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: UidMap.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private Map<String, Boolean> a;

    public d(Map<String, Boolean> map) {
        this.a = map;
    }

    public final Map<String, String> a(String str, boolean z) {
        HashMap map = new HashMap();
        String strA = null;
        try {
            if (this.a.get("GPID").booleanValue()) {
                c.a();
                a aVarF = c.f();
                if (aVarF != null && (strA = aVarF.a) != null) {
                    if (z) {
                        strA = a(strA, str);
                    }
                    map.put("GPID", strA);
                }
            }
            a(str, z, map, strA);
        } catch (Exception unused) {
            d.class.getSimpleName();
            a(str, z, map, strA);
        }
        return map;
    }

    private void a(String str, boolean z, Map<String, String> map, String str2) {
        try {
            if (this.a.get("UM5").booleanValue() && str2 == null) {
                c.a();
                c.a();
                String strA = c.a(c.e(), "MD5");
                if (z) {
                    strA = a(strA, str);
                }
                map.put("UM5", strA);
            }
            if (this.a.get("O1").booleanValue() && str2 == null) {
                c.a();
                c.a();
                String strA2 = c.a(c.e(), CommonUtils.SHA1_INSTANCE);
                if (z) {
                    strA2 = a(strA2, str);
                }
                map.put("O1", strA2);
            }
        } catch (Exception unused) {
            d.class.getSimpleName();
        }
    }

    private static String a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, 2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }
}
