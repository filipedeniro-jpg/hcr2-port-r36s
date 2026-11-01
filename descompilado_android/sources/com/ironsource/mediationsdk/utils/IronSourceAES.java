package com.ironsource.mediationsdk.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceAES {
    private static boolean mDidSendEncryptionFailEventInSession = false;

    public static synchronized String encode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            SecretKeySpec key = getKey(str);
            byte[] bytes = str2.getBytes(UrlUtils.UTF8);
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, key, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(bytes), 0).replaceAll(System.getProperty("line.separator"), "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized String decode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            SecretKeySpec key = getKey(str);
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            byte[] bArrDecode = Base64.decode(str2, 0);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, key, ivParameterSpec);
            return new String(cipher.doFinal(bArrDecode));
        } catch (Exception e) {
            e.printStackTrace();
            if (mDidSendEncryptionFailEventInSession) {
                return "";
            }
            mDidSendEncryptionFailEventInSession = true;
            JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            try {
                mediationAdditionalData.put("status", "false");
                mediationAdditionalData.put(IronSourceConstants.EVENTS_ERROR_REASON, 1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            InterstitialEventsManager.getInstance().sendEventToUrl(new EventData(114, mediationAdditionalData), "https://outcome-ssp.supersonicads.com/mediation?adUnit=2");
            return "";
        }
    }

    private static SecretKeySpec getKey(String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] bytes = str.getBytes("UTF-8");
        System.arraycopy(bytes, 0, bArr, 0, bytes.length < bArr.length ? bytes.length : bArr.length);
        return new SecretKeySpec(bArr, "AES");
    }
}
