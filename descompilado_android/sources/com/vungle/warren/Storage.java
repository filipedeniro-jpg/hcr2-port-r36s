package com.vungle.warren;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.Persistor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class Storage {
    public static final int DB_VERSION = 1;
    private static final String TAG = "Storage";
    private final Designer designer;
    private final Persistor persistor;
    private final Map<String, Placement> placementsMap = new ConcurrentHashMap();
    private final Map<String, Advertisement> advMap = new ConcurrentHashMap();
    private List<String> validPlacements = new ArrayList();

    private Storage(@NonNull Persistor persistor, @NonNull Designer designer) {
        this.persistor = persistor;
        this.designer = designer;
    }

    static Storage makeInstance(@NonNull Persistor persistor, @NonNull Designer designer) {
        return new Storage(persistor, designer);
    }

    public void init(int i) {
        this.placementsMap.clear();
        this.advMap.clear();
        this.persistor.init(i, new Migrator());
        List<Advertisement> listExtractAll = this.persistor.extractAll(Advertisement.class);
        if (listExtractAll == null || listExtractAll.size() <= 0) {
            return;
        }
        for (Advertisement advertisement : listExtractAll) {
            if (advertisement != null) {
                if (advertisement.getState() == 2) {
                    advertisement.setState(3);
                    save(advertisement);
                    Log.i(TAG, "Advertisement " + advertisement.getId() + " state marked as DONE, it stuck in VIEWING state");
                } else if (advertisement.getState() == 1 && !hasValidAssets(advertisement)) {
                    delete(advertisement);
                    try {
                        this.designer.deleteAssets(advertisement.getId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void save(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.put(memorable.getId(), (Placement) memorable);
        } else if (memorable instanceof Advertisement) {
            this.advMap.put(memorable.getId(), (Advertisement) memorable);
        }
        this.persistor.save(memorable);
    }

    @Nullable
    public <T extends Memorable> T load(@NonNull String str, @NonNull Class<T> cls) {
        if (Placement.class.isAssignableFrom(cls)) {
            Placement placement = this.placementsMap.get(str);
            if (placement != null) {
                return placement.copy();
            }
            Placement placement2 = (Placement) this.persistor.find(str, cls);
            if (placement2 != null) {
                this.placementsMap.put(str, placement2);
            }
            return placement2;
        }
        if (Advertisement.class.isAssignableFrom(cls)) {
            Advertisement advertisement = this.advMap.get(str);
            if (advertisement != null) {
                return advertisement.copy();
            }
            Advertisement advertisement2 = (Advertisement) this.persistor.find(str, cls);
            if (advertisement2 != null) {
                this.advMap.put(str, advertisement2);
            }
            return advertisement2;
        }
        return (T) this.persistor.find(str, cls);
    }

    @NonNull
    public <T extends Memorable> List<T> loadAll(@NonNull Class<T> cls) {
        return this.persistor.extractAll(cls);
    }

    public void delete(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.remove(memorable.getId());
        } else if (memorable instanceof Advertisement) {
            this.advMap.remove(memorable.getId());
        }
        this.persistor.delete(memorable);
    }

    @Nullable
    public Advertisement findValidAdvertisementForPlacement(@Nullable String str) {
        Advertisement advertisement;
        String str2;
        Placement placement = (Placement) load(str, Placement.class);
        Log.i(TAG, " Searching for valid adv for pl " + str);
        if (placement == null || placement.getAdvertisementIDs() == null || placement.getAdvertisementIDs().isEmpty()) {
            return null;
        }
        Log.i(TAG, " Searching for valid adv for pl " + str + " all ids " + placement.getAdvertisementIDs());
        Iterator<String> it = placement.getAdvertisementIDs().iterator();
        while (true) {
            if (!it.hasNext()) {
                advertisement = null;
                break;
            }
            advertisement = (Advertisement) load(it.next(), Advertisement.class);
            if (advertisement != null) {
                boolean z = advertisement.getState() == 1 || advertisement.getState() == 0;
                if ((advertisement.getExpireTime() > System.currentTimeMillis()) && z) {
                    break;
                }
            }
        }
        String str3 = TAG;
        if (advertisement == null) {
            str2 = "Didn't find valid adv";
        } else {
            str2 = "Found valid adv " + advertisement.getId();
        }
        Log.i(str3, str2);
        return advertisement;
    }

    public boolean hasValidAssets(Advertisement advertisement) {
        return this.designer.hasAssetsFor(advertisement.getId(), advertisement.getDownloadableUrls().size());
    }

    public synchronized void setValidPlacements(@NonNull List<Placement> list) {
        this.validPlacements.clear();
        for (Placement placement : list) {
            Placement placement2 = (Placement) load(placement.getId(), Placement.class);
            if (placement2 != null && !placement2.equalsIgnoreAdvertisements(placement)) {
                Log.w(TAG, "Placements data for " + placement.getId() + " is different from disc, deleting old");
                try {
                    for (String str : placement.getAdvertisementIDs()) {
                        Memorable memorable = (Advertisement) load(str, Advertisement.class);
                        if (memorable != null) {
                            delete(memorable);
                        }
                        this.designer.deleteAssets(str);
                    }
                    delete(placement2);
                } catch (IOException e) {
                    Log.e("Vungle", "Failed to delete old assets, this could lead to disk space errors");
                    Log.e("Vungle", e.getMessage());
                }
            } else if (placement2 != null) {
                placement = placement2;
            }
            save(placement);
            this.validPlacements.add(placement.getId());
        }
    }

    public void saveAndApplyState(@NonNull Advertisement advertisement, @NonNull String str, @Advertisement.State int i) {
        Log.i(TAG, "Setting " + i + " for adv " + advertisement.getId() + " and pl " + str);
        advertisement.setState(i);
        save(advertisement);
        String id = advertisement.getId();
        if (i == 0 || i == 1) {
            addAdvertisementToPlacement(str, id);
            return;
        }
        if (i == 2) {
            removeAdvertisementFromPlacement(str, id);
            return;
        }
        if (i == 3 || i == 4) {
            removeAdvertisementFromPlacement(str, id);
            delete(advertisement);
            try {
                this.designer.deleteAssets(id);
            } catch (IOException e) {
                Log.e(TAG, "error on deleting assets for " + advertisement.getId(), e);
            }
        }
    }

    public void clearAllData() {
        this.designer.clearCache();
        this.persistor.clearCache();
        this.advMap.clear();
        this.placementsMap.clear();
        Log.d(TAG, "Cache cleared.");
    }

    public synchronized Collection<Placement> loadValidPlacements() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.validPlacements.iterator();
        while (it.hasNext()) {
            Placement placement = (Placement) load(it.next(), Placement.class);
            if (placement != null) {
                arrayList.add(placement.copy());
            }
        }
        return arrayList;
    }

    public synchronized Collection<String> getValidPlacements() {
        return new ArrayList(this.validPlacements);
    }

    public File getAdvertisementAssetDirectory(String str) {
        return this.designer.getAssetDirectory(str);
    }

    public synchronized void removeAdvertisementFromPlacement(String str, String str2) {
        Placement placement = (Placement) load(str, Placement.class);
        if (placement != null && !TextUtils.isEmpty(str2)) {
            placement.removeAdvertisementID(str2);
            save(placement);
        }
    }

    public synchronized void addAdvertisementToPlacement(String str, String str2) {
        Placement placement = (Placement) load(str, Placement.class);
        if (placement != null && !TextUtils.isEmpty(str2)) {
            placement.addAdvertisementID(str2);
            save(placement);
        }
    }

    private class Migrator implements Persistor.MigrationCallback {
        private Migrator() {
        }

        @Override // com.vungle.warren.persistence.Persistor.MigrationCallback
        public void onUpgrade(int i, int i2) {
            if (i < 1) {
                Storage.this.persistor.migrateData(i, i2, Report.class, new Persistor.Transformation<Report>() { // from class: com.vungle.warren.Storage.Migrator.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.vungle.warren.persistence.Persistor.Transformation
                    public Report transform(int i3, int i4, byte[] bArr) {
                        return Report.restore(i3, i4, bArr);
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Cookie.class, new Persistor.Transformation<Cookie>() { // from class: com.vungle.warren.Storage.Migrator.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.vungle.warren.persistence.Persistor.Transformation
                    public Cookie transform(int i3, int i4, byte[] bArr) {
                        return Cookie.restore(i3, i4, bArr);
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Placement.class, new Persistor.Transformation<Placement>() { // from class: com.vungle.warren.Storage.Migrator.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.vungle.warren.persistence.Persistor.Transformation
                    public Placement transform(int i3, int i4, byte[] bArr) {
                        Placement placementRestore = Placement.restore(i3, i4, bArr);
                        if (placementRestore != null) {
                            Iterator<String> it = placementRestore.getAdvertisementIDs().iterator();
                            while (it.hasNext()) {
                                placementRestore.removeAdvertisementID(it.next());
                            }
                        }
                        return placementRestore;
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Advertisement.class, new Persistor.Transformation<Advertisement>() { // from class: com.vungle.warren.Storage.Migrator.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.vungle.warren.persistence.Persistor.Transformation
                    public Advertisement transform(int i3, int i4, byte[] bArr) {
                        return null;
                    }
                });
            }
        }

        @Override // com.vungle.warren.persistence.Persistor.MigrationCallback
        public void onDowngrade(int i, int i2) {
            Storage.this.clearAllData();
        }
    }
}
