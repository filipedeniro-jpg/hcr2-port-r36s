package com.vungle.warren.persistence;

import android.util.Log;
import androidx.annotation.NonNull;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class GraphicDesigner implements Designer {
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "GraphicDesigner";
    private File cacheDir;

    public GraphicDesigner(@NonNull File file) {
        this.cacheDir = file;
        FileUtility.printDirectoryTree(getCacheDirectory());
    }

    @Override // com.vungle.warren.persistence.Designer
    public boolean hasAssetsFor(String str, int i) throws IllegalStateException {
        File[] fileArrListFiles = getCacheDirectory().listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file : fileArrListFiles) {
            if (file.isDirectory() && file.getName().equals(str)) {
                return file.list().length >= i;
            }
        }
        return false;
    }

    @Override // com.vungle.warren.persistence.Designer
    public File getAssetDirectory(String str) throws IllegalStateException {
        File file = new File(getCacheDirectory().getPath() + File.separator + str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    @Override // com.vungle.warren.persistence.Designer
    public void deleteAssets(String str) throws IllegalStateException, IOException {
        File[] fileArrListFiles = getCacheDirectory().listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.isDirectory() && file.getName().equals(str)) {
                FileUtility.delete(file);
            }
        }
    }

    @Override // com.vungle.warren.persistence.Designer
    public File getCacheDirectory() throws IllegalStateException {
        if (this.cacheDir == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        File file = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    @Override // com.vungle.warren.persistence.Designer
    public void clearCache() {
        if (this.cacheDir == null) {
            return;
        }
        File file = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
        if (file.exists()) {
            try {
                FileUtility.delete(file);
            } catch (IOException e) {
                Log.e(TAG, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
            }
        }
        if (file.exists()) {
            return;
        }
        file.mkdir();
    }
}
