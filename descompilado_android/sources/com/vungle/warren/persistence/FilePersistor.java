package com.vungle.warren.persistence;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.share.Constants;
import com.vungle.warren.utility.FileUtility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class FilePersistor implements Persistor {
    private static final String TAG = "FilePersistor";
    static final String VUNGLE = "vungle";
    private static final String V_PREFIX = "V";
    private Integer currentVersion;
    private File filesDir;

    private interface TransformationReader {
        @NonNull
        byte[] readBytes(@NonNull File file);
    }

    public FilePersistor(File file) {
        this.filesDir = new File(file, VUNGLE);
    }

    @Override // com.vungle.warren.persistence.Persistor
    public File getStorageDirectory() throws IllegalStateException {
        return makeWorkingDir();
    }

    @NonNull
    private File makeWorkingDir() {
        checkInitialized();
        File file = new File(this.filesDir, V_PREFIX + this.currentVersion);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private void checkInitialized() {
        if (this.filesDir == null || this.currentVersion == null) {
            throw new IllegalStateException("Working dir is null");
        }
    }

    @Override // com.vungle.warren.persistence.Persistor
    public void init(int i, Persistor.MigrationCallback migrationCallback) throws Throwable {
        this.currentVersion = Integer.valueOf(i);
        checkInitialized();
        Version version = (Version) find(Version.ID, Version.class);
        if (version != null && version.getVersion() == i) {
            scanAndDeleteNonRelevant();
            return;
        }
        int version2 = version == null ? 0 : version.getVersion();
        if (version2 > i) {
            Log.e(TAG, "downgrade is not supported performing destructive migration, old version = " + version2 + " current = " + i);
            migrationCallback.onDowngrade(version2, i);
        } else {
            migrationCallback.onUpgrade(version2, i);
        }
        save(new Version(i, "upgrade/new", System.currentTimeMillis()));
        scanAndDeleteNonRelevant();
    }

    private void scanAndDeleteNonRelevant() {
        final File fileMakeWorkingDir = makeWorkingDir();
        File[] fileArrListFiles = this.filesDir.listFiles(new FileFilter() { // from class: com.vungle.warren.persistence.FilePersistor.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return !fileMakeWorkingDir.getName().equals(file.getName());
            }
        });
        if (fileArrListFiles == null) {
            Log.d(TAG, "nothing was found for deletion during scanning");
            return;
        }
        for (File file : fileArrListFiles) {
            try {
                FileUtility.delete(file);
            } catch (IOException e) {
                Log.d(TAG, "error deletion during scanning " + e.getLocalizedMessage());
            }
        }
    }

    @Override // com.vungle.warren.persistence.Persistor
    public boolean save(Memorable memorable) throws Throwable {
        Log.d(TAG, " Saving " + memorable);
        File storageDirectory = getStorageDirectory();
        if (storageDirectory == null || !storageDirectory.isDirectory()) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(getStorageDirectory(), memorable.getId() + "." + memorable.getClass().getSimpleName());
                if (file.exists() && !file.delete()) {
                    throw new IOException("Failed to delete previous version of memorable file!");
                }
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file for memorable!");
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(memorable.toByteArray());
                    FileUtility.closeQuietly(fileOutputStream2);
                    return true;
                } catch (FileNotFoundException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    FileUtility.closeQuietly(fileOutputStream);
                    return false;
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    FileUtility.closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    FileUtility.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }

    @Override // com.vungle.warren.persistence.Persistor
    public boolean save(@NonNull List<Memorable> list) {
        if (list == null) {
            return false;
        }
        boolean zSave = true;
        Iterator<Memorable> it = list.iterator();
        while (it.hasNext()) {
            zSave &= save(it.next());
        }
        return zSave;
    }

    @Override // com.vungle.warren.persistence.Persistor
    @NonNull
    public <T extends Memorable> List<T> extractAll(@NonNull Class<T> cls) {
        return extractAll(cls, null);
    }

    @NonNull
    private <T extends Memorable> List<T> extractAll(@NonNull final Class<T> cls, @Nullable final FilenameFilter filenameFilter) {
        File storageDirectory = getStorageDirectory();
        if (storageDirectory == null || !storageDirectory.isDirectory() || !storageDirectory.exists()) {
            return Collections.emptyList();
        }
        File[] fileArrListFiles = storageDirectory.listFiles(new FilenameFilter() { // from class: com.vungle.warren.persistence.FilePersistor.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                FilenameFilter filenameFilter2 = filenameFilter;
                return (filenameFilter2 == null ? true : filenameFilter2.accept(file, str)) && str.endsWith(cls.getSimpleName());
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file != null && file.exists()) {
                try {
                    Memorable memorableExtractFrom = extractFrom(file, cls);
                    if (memorableExtractFrom != null) {
                        arrayList.add(memorableExtractFrom);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    @Override // com.vungle.warren.persistence.Persistor
    @Nullable
    public <T extends Memorable> T extractFrom(@NonNull File file, @NonNull Class<T> cls) throws IOException {
        try {
            byte[] bArrExtractBytes = FileUtility.extractBytes(file);
            if (bArrExtractBytes.length == 0) {
                return null;
            }
            return cls.getDeclaredConstructor(byte[].class).newInstance(bArrExtractBytes);
        } catch (FileNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.vungle.warren.persistence.Persistor
    public <T extends Memorable> T find(@NonNull String str, @NonNull Class<T> cls) {
        if (!str.contains(".")) {
            str = str + "." + cls.getSimpleName();
        }
        File file = new File(getStorageDirectory() + Constants.URL_PATH_DELIMITER + str);
        if (file.exists()) {
            try {
                return (T) extractFrom(file, cls);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.vungle.warren.persistence.Persistor
    @NonNull
    public <T extends Memorable> List<T> findAll(@NonNull Set<String> set, Class<T> cls) {
        if (set == null || set.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            Memorable memorableFind = find(it.next(), cls);
            if (memorableFind != null) {
                arrayList.add(memorableFind);
            }
        }
        return arrayList;
    }

    @Override // com.vungle.warren.persistence.Persistor
    public boolean delete(Memorable memorable) {
        Log.d(TAG, " Delete " + memorable);
        File file = new File(getStorageDirectory() + File.separator + memorable.getId() + "." + memorable.getClass().getSimpleName());
        return file.exists() && file.delete();
    }

    @Override // com.vungle.warren.persistence.Persistor
    public void clearCache() {
        File file = this.filesDir;
        if (file.exists()) {
            try {
                FileUtility.delete(file);
            } catch (IOException e) {
                Log.e(TAG, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
            }
        }
        makeWorkingDir();
    }

    @Override // com.vungle.warren.persistence.Persistor
    public <T extends Memorable> void migrateData(int i, int i2, @NonNull final Class<T> cls, @NonNull Persistor.Transformation<T> transformation) {
        if (i < 1) {
            TransformationReader transformationReader = new TransformationReader() { // from class: com.vungle.warren.persistence.FilePersistor.3
                @Override // com.vungle.warren.persistence.FilePersistor.TransformationReader
                public byte[] readBytes(@NonNull File file) {
                    byte[] bArrExtractBytes;
                    try {
                        bArrExtractBytes = FileUtility.extractBytes(file);
                    } catch (IOException e) {
                        Log.e(FilePersistor.TAG, "Failed restore " + file.getName() + " " + e.getLocalizedMessage());
                        bArrExtractBytes = null;
                    }
                    if (bArrExtractBytes == null || bArrExtractBytes.length <= 1) {
                        return new byte[0];
                    }
                    return Arrays.copyOfRange(bArrExtractBytes, 1, bArrExtractBytes.length);
                }
            };
            File[] fileArrListFiles = this.filesDir.listFiles(new FilenameFilter() { // from class: com.vungle.warren.persistence.FilePersistor.4
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return str.endsWith(cls.getSimpleName());
                }
            });
            if (fileArrListFiles == null) {
                Log.e(TAG, "Cannot read files during migration for " + cls.getSimpleName());
                return;
            }
            for (File file : fileArrListFiles) {
                try {
                    byte[] bytes = transformationReader.readBytes(file);
                    FileUtility.delete(file);
                    if (bytes.length != 0) {
                        try {
                            T tTransform = transformation.transform(i, i2, bytes);
                            if (tTransform != null) {
                                save(tTransform);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Version implements Memorable {
        public static final String ID = "Data";
        private final String reason;
        private final long timestamp;
        private final int versionInt;

        @Override // com.vungle.warren.persistence.Memorable
        @NonNull
        public String getId() {
            return ID;
        }

        public Version(int i, @NonNull String str, long j) {
            this.versionInt = i;
            this.reason = str;
            this.timestamp = j;
        }

        public Version(byte[] bArr) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.versionInt = byteBufferWrap.getInt();
            this.reason = MemoryUtils.extractString(byteBufferWrap);
            this.timestamp = byteBufferWrap.getLong();
        }

        @Override // com.vungle.warren.persistence.Memorable
        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.versionInt));
                MemoryUtils.write(this.reason, byteArrayOutputStream);
                MemoryUtils.write(Long.valueOf(this.timestamp), byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Version#toByteArray()", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        public int getVersion() {
            return this.versionInt;
        }
    }
}
