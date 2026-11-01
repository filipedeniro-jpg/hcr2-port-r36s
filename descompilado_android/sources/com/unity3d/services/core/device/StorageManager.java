package com.unity3d.services.core.device;

import android.content.Context;
import com.appsflyer.share.Constants;
import com.unity3d.services.core.properties.SdkProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class StorageManager {
    protected static final Map<StorageType, String> _storageFileMap = new HashMap();
    protected static final List<Storage> _storages = new ArrayList();

    public enum StorageType {
        PRIVATE,
        PUBLIC
    }

    public static boolean init(Context context) {
        File filesDir;
        if (context == null || (filesDir = context.getFilesDir()) == null) {
            return false;
        }
        addStorageLocation(StorageType.PUBLIC, filesDir + Constants.URL_PATH_DELIMITER + SdkProperties.getLocalStorageFilePrefix() + "public-data.json");
        if (!setupStorage(StorageType.PUBLIC)) {
            return false;
        }
        addStorageLocation(StorageType.PRIVATE, filesDir + Constants.URL_PATH_DELIMITER + SdkProperties.getLocalStorageFilePrefix() + "private-data.json");
        return setupStorage(StorageType.PRIVATE);
    }

    public static void initStorage(StorageType storageType) {
        if (hasStorage(storageType)) {
            Storage storage = getStorage(storageType);
            if (storage != null) {
                storage.initStorage();
                return;
            }
            return;
        }
        if (_storageFileMap.containsKey(storageType)) {
            Storage storage2 = new Storage(_storageFileMap.get(storageType), storageType);
            storage2.initStorage();
            _storages.add(storage2);
        }
    }

    private static boolean setupStorage(StorageType storageType) {
        if (hasStorage(storageType)) {
            return true;
        }
        initStorage(storageType);
        Storage storage = getStorage(storageType);
        if (storage != null && !storage.storageFileExists()) {
            storage.writeStorage();
        }
        return storage != null;
    }

    public static Storage getStorage(StorageType storageType) {
        List<Storage> list = _storages;
        if (list == null) {
            return null;
        }
        for (Storage storage : list) {
            if (storage.getType().equals(storageType)) {
                return storage;
            }
        }
        return null;
    }

    public static boolean hasStorage(StorageType storageType) {
        List<Storage> list = _storages;
        if (list == null) {
            return false;
        }
        Iterator<Storage> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getType().equals(storageType)) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void addStorageLocation(StorageType storageType, String str) {
        if (!_storageFileMap.containsKey(storageType)) {
            _storageFileMap.put(storageType, str);
        }
    }

    public static synchronized void removeStorage(StorageType storageType) {
        if (getStorage(storageType) != null) {
            _storages.remove(getStorage(storageType));
        }
        if (_storageFileMap != null) {
            _storageFileMap.remove(storageType);
        }
    }
}
