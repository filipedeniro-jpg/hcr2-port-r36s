package com.vungle.warren;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.presenter.LocalAdPresenter;
import com.vungle.warren.presenter.WebAdPresenter;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class AdvertisementPresenterFactory {
    private static final String EXTRA_ADVERTISEMENT = "ADV_FACTORY_ADVERTISEMENT";
    private static final String TAG = "AdvertisementPresenterFactory";
    private Advertisement advertisement;
    private final Storage storage = Vungle._instance.storage;

    public AdvertisementPresenter getAdPresenter(String str, Bundle bundle, String str2) throws IllegalStateException, IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Missing placementID! Cannot play advertisement.");
        }
        if (this.storage == null || !Vungle.isInitialized()) {
            throw new IllegalStateException("Vungle SDK is not initialized");
        }
        Placement placement = (Placement) this.storage.load(str, Placement.class);
        if (placement == null) {
            throw new IllegalArgumentException("No placement for ID " + str + " found. Please use a valid placement ID");
        }
        if (bundle == null) {
            this.advertisement = this.storage.findValidAdvertisementForPlacement(str);
        } else {
            String string = bundle.getString(EXTRA_ADVERTISEMENT);
            if (!TextUtils.isEmpty(string)) {
                this.advertisement = (Advertisement) this.storage.load(string, Advertisement.class);
            }
        }
        if (!Vungle.canPlayAd(this.advertisement)) {
            Log.e(TAG, "Advertisement is null or assets are missing");
            return null;
        }
        File advertisementAssetDirectory = this.storage.getAdvertisementAssetDirectory(this.advertisement.getId());
        if (!advertisementAssetDirectory.isDirectory()) {
            throw new IllegalStateException("No asset directory for " + str + " exists!");
        }
        int adType = this.advertisement.getAdType();
        if (adType == 0) {
            return new LocalAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, str2);
        }
        if (adType == 1) {
            return new WebAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, str2);
        }
        throw new IllegalArgumentException("No presenter available for ad type!");
    }

    public void saveState(Bundle bundle) {
        Advertisement advertisement = this.advertisement;
        bundle.putString(EXTRA_ADVERTISEMENT, advertisement == null ? null : advertisement.getId());
    }
}
