package com.fingersoft.account_sdk;

import android.content.Context;
import android.content.SharedPreferences;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String SETTINGS_NAME = "ACCOUNT_SETTINGS";
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String sha256Hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            byte[] bytes = str.getBytes("UTF-8");
            messageDigest.update(bytes, 0, bytes.length);
            return bytesToHex(messageDigest.digest()).toLowerCase(Locale.ENGLISH);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String sha1Hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            byte[] bytes = str.getBytes("UTF-8");
            messageDigest.update(bytes, 0, bytes.length);
            return bytesToHex(messageDigest.digest()).toLowerCase(Locale.ENGLISH);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean clearSettings(Context context) {
        AccountManager.log("Clearing settings");
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).edit().clear().commit();
        } catch (Exception e) {
            AccountManager.log("Could not clear application settings: " + e.getMessage());
            return false;
        }
    }

    public static void setValue(Context context, String str, String str2) {
        AccountManager.log("Settings setting value: name = " + str + " with values = " + str2);
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SETTINGS_NAME, 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception e) {
            AccountManager.log("ERROR SETTING VALUE " + str + " -> " + e);
        }
    }

    public static int getValueInt(Context context, String str, int i) {
        try {
            return Integer.parseInt(context.getSharedPreferences(SETTINGS_NAME, 0).getString(str, ""));
        } catch (Exception unused) {
            return i;
        }
    }

    public static String getValueString(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).getString(str, str2);
        } catch (Exception e) {
            AccountManager.log("ERROR GETTING VALUE " + str + " -> " + e);
            return str2;
        }
    }

    public static boolean hasValue(Context context, String str) {
        try {
            return context.getSharedPreferences(SETTINGS_NAME, 0).contains(str);
        } catch (Exception unused) {
            return false;
        }
    }
}
