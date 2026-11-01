package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class CleanupJob implements Job {
    static final String TAG = "com.vungle.warren.tasks.CleanupJob";
    private Designer designer;
    private Storage storage;

    CleanupJob(@NonNull Designer designer, @NonNull Storage storage) {
        this.designer = designer;
        this.storage = storage;
    }

    @Override // com.vungle.warren.tasks.Job
    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        if (this.designer == null || this.storage == null) {
            return 1;
        }
        Log.d(TAG, "CleanupJob: Current directory snapshot");
        FileUtility.printDirectoryTree(this.designer.getCacheDirectory());
        File[] fileArrListFiles = this.designer.getCacheDirectory().listFiles();
        List<Advertisement> listLoadAll = this.storage.loadAll(Advertisement.class);
        List<Placement> listLoadAll2 = this.storage.loadAll(Placement.class);
        if (listLoadAll2.size() == 0) {
            return 0;
        }
        Collection<Placement> collectionLoadValidPlacements = this.storage.loadValidPlacements();
        HashSet hashSet = new HashSet();
        for (Placement placement : listLoadAll2) {
            if (!collectionLoadValidPlacements.isEmpty() && !collectionLoadValidPlacements.contains(placement)) {
                Log.d(TAG, String.format(Locale.ENGLISH, "Placement %s is no longer valid, deleting it and its advertisement", placement.getId()));
                this.storage.delete(placement);
            } else {
                for (String str : placement.getAdvertisementIDs()) {
                    Advertisement advertisement = (Advertisement) this.storage.load(str, Advertisement.class);
                    if (advertisement != null) {
                        if (advertisement.getExpireTime() <= System.currentTimeMillis() && advertisement.getState() != 2) {
                            this.storage.removeAdvertisementFromPlacement(placement.getId(), str);
                            try {
                                this.designer.deleteAssets(advertisement.getId());
                            } catch (IOException e) {
                                Log.e(TAG, Log.getStackTraceString(e));
                            }
                            this.storage.delete(advertisement);
                            if (placement.isAutoCached()) {
                                jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true).setDelay(1000L));
                            }
                        } else {
                            hashSet.add(advertisement.getId());
                            Log.w(TAG, "setting valid adv " + str + " for placement " + placement.getId());
                        }
                    } else {
                        Log.w(TAG, "removing adv " + str + " from placement " + placement.getId());
                        this.storage.removeAdvertisementFromPlacement(placement.getId(), str);
                    }
                }
            }
        }
        for (Advertisement advertisement2 : listLoadAll) {
            if (advertisement2.getState() == 2) {
                hashSet.add(advertisement2.getId());
                Log.d(TAG, "found adv in viewing state " + advertisement2.getId());
            } else if (!hashSet.contains(advertisement2.getId())) {
                Log.e(TAG, "delete ad " + advertisement2.getId());
                this.storage.delete(advertisement2);
            }
        }
        for (File file : fileArrListFiles) {
            if (!hashSet.contains(file.getName())) {
                Log.v(TAG, String.format(Locale.ENGLISH, "Deleting assets under directory %s", file.getName()));
                try {
                    FileUtility.delete(file);
                } catch (Throwable th) {
                    Log.e(TAG, "Failed to delete asset directory!", th);
                }
            }
        }
        return 0;
    }

    public static JobInfo makeJobInfo() {
        return new JobInfo(TAG).setPriority(0).setUpdateCurrent(true);
    }
}
