package com.ironsource.sdk.utils;

import android.content.Context;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.data.SSAFile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceStorageUtils {
    private static final String SSA_DIRECTORY_NAME = "supersonicads";

    public static String initializeCacheDirectory(Context context) {
        createRootDirectory(context);
        return refreshRootDirectory(context);
    }

    private static String refreshRootDirectory(Context context) {
        String currentSDKVersion = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCurrentSDKVersion();
        String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
        if (!currentSDKVersion.equalsIgnoreCase(supersonicSdkVersion)) {
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCurrentSDKVersion(supersonicSdkVersion);
            File externalCacheDir = DeviceStatus.getExternalCacheDir(context);
            if (externalCacheDir != null) {
                deleteAllFiles(externalCacheDir.getAbsolutePath() + File.separator + SSA_DIRECTORY_NAME + File.separator);
            }
            deleteAllFiles(DeviceStatus.getInternalCacheDirPath(context) + File.separator + SSA_DIRECTORY_NAME + File.separator);
            return createRootDirectory(context);
        }
        return getDiskCacheDir(context, SSA_DIRECTORY_NAME).getPath();
    }

    private static void deleteAllFiles(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.isDirectory()) {
                deleteAllFiles(file.getAbsolutePath());
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    private static File getDiskCacheDir(Context context, String str) {
        return new File(getDiskCacheDirPath(context) + File.separator + str);
    }

    public static String getDiskCacheDirPath(Context context) {
        if (SDKUtils.isExternalStorageAvailable()) {
            File externalCacheDir = DeviceStatus.getExternalCacheDir(context);
            if (externalCacheDir != null && externalCacheDir.canWrite()) {
                return externalCacheDir.getPath();
            }
            return DeviceStatus.getInternalCacheDirPath(context);
        }
        return DeviceStatus.getInternalCacheDirPath(context);
    }

    private static String createRootDirectory(Context context) {
        File diskCacheDir = getDiskCacheDir(context, SSA_DIRECTORY_NAME);
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdir();
        }
        return diskCacheDir.getPath();
    }

    public static String makeDir(String str, String str2) {
        File file = new File(str, str2);
        if (file.exists() || file.mkdirs()) {
            return file.getPath();
        }
        return null;
    }

    public static synchronized boolean deleteFile(String str, String str2, String str3) {
        File file = new File(str, str2);
        if (!file.exists()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && file2.getName().equalsIgnoreCase(str3)) {
                return file2.delete();
            }
        }
        return false;
    }

    public static synchronized boolean isFileCached(String str, SSAFile sSAFile) {
        File file = new File(str, sSAFile.getPath());
        if (file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile() && file2.getName().equalsIgnoreCase(SDKUtils.getFileName(sSAFile.getFile()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPathExist(String str, String str2) {
        return new File(str, str2).exists();
    }

    public static synchronized boolean deleteFolder(String str, String str2) {
        File file;
        file = new File(str, str2);
        return deleteFolderContentRecursive(file) && file.delete();
    }

    private static boolean deleteFolderContentRecursive(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return true;
        }
        boolean zDeleteFolderContentRecursive = true;
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                zDeleteFolderContentRecursive &= deleteFolderContentRecursive(file2);
            }
            if (!file2.delete()) {
                zDeleteFolderContentRecursive = false;
            }
        }
        return zDeleteFolderContentRecursive;
    }

    public static String getCachedFilesMap(String str, String str2) {
        JSONObject jSONObjectBuildFilesMap = buildFilesMap(str, str2);
        try {
            jSONObjectBuildFilesMap.put("path", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObjectBuildFilesMap.toString();
    }

    private static JSONObject buildFilesMap(String str, String str2) {
        File file = new File(str, str2);
        JSONObject jSONObject = new JSONObject();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                try {
                    Object objLooping = looping(file2);
                    if (objLooping instanceof JSONArray) {
                        jSONObject.put("files", looping(file2));
                    } else if (objLooping instanceof JSONObject) {
                        jSONObject.put(file2.getName(), looping(file2));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
                }
            }
        }
        return jSONObject;
    }

    private static Object looping(File file) {
        String campaignLastUpdate;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            if (file.isFile()) {
                jSONArray.put(file.getName());
                return jSONArray;
            }
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    jSONObject.put(file2.getName(), looping(file2));
                } else {
                    jSONArray.put(file2.getName());
                    jSONObject.put("files", jSONArray);
                }
            }
            if (file.isDirectory() && (campaignLastUpdate = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getCampaignLastUpdate(file.getName())) != null) {
                jSONObject.put("lastUpdateTime", campaignLastUpdate);
            }
            String lowerCase = file.getName().toLowerCase();
            SSAEnums.ProductType productType = null;
            if (lowerCase.startsWith(SSAEnums.ProductType.RewardedVideo.toString().toLowerCase())) {
                productType = SSAEnums.ProductType.RewardedVideo;
            } else if (lowerCase.startsWith(SSAEnums.ProductType.OfferWall.toString().toLowerCase())) {
                productType = SSAEnums.ProductType.OfferWall;
            } else if (lowerCase.startsWith(SSAEnums.ProductType.Interstitial.toString().toLowerCase())) {
                productType = SSAEnums.ProductType.Interstitial;
            }
            if (productType != null) {
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_USER_ID), SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId(productType)));
                jSONObject.put(SDKUtils.encodeString(Constants.RequestParameters.APPLICATION_KEY), SDKUtils.encodeString(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getApplicationKey(productType)));
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
        }
    }

    public static boolean renameFile(String str, String str2) throws Exception {
        return new File(str).renameTo(new File(str2));
    }

    public static int saveFile(byte[] bArr, String str) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            byte[] bArr2 = new byte[102400];
            int i = 0;
            while (true) {
                int i2 = byteArrayInputStream.read(bArr2);
                if (i2 != -1) {
                    fileOutputStream.write(bArr2, 0, i2);
                    i += i2;
                } else {
                    fileOutputStream.close();
                    byteArrayInputStream.close();
                    return i;
                }
            }
        } catch (Throwable th) {
            fileOutputStream.close();
            byteArrayInputStream.close();
            throw th;
        }
    }
}
