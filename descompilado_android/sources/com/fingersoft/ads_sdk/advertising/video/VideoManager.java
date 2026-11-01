package com.fingersoft.ads_sdk.advertising.video;

import android.app.Activity;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.Comparators.VideoAdProviderComparator;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class VideoManager implements IVideoAdListener {
    private AdManager mAdManager;
    private VideoAdProvider mCurrentVideoAdProvider;
    private IVideoAdListener mVideoAdListener;
    private ArrayList<VideoAdProviderGroup> mVideoAdProviderGroups = new ArrayList<>();
    private ArrayList<VideoAdProvider> mAllVideoAdProviders = new ArrayList<>();

    private int getProviderCountByID(int i) {
        return 0;
    }

    public void _debugForceNextProvider() {
    }

    public void _debugForceStayOnCurrentProvider(boolean z) {
    }

    public VideoManager(AdManager adManager, IVideoAdListener iVideoAdListener) {
        this.mVideoAdListener = iVideoAdListener;
        this.mAdManager = adManager;
    }

    public void reorderProvidersByPriority(String str, int i) {
        VideoAdProviderGroup groupByID;
        AdUtils.log("New video priorities set " + str);
        if (this.mVideoAdProviderGroups.size() <= 0 || (groupByID = getGroupByID(i)) == null || groupByID.getProviders() == null) {
            return;
        }
        groupByID.setPriorityString(str);
        AdUtils.log("Providers before sort " + groupByID.getProviders().size());
        AdUtils.log("providers priority " + str);
        groupByID.setProviders(new VideoAdProviderComparator(str).sort(groupByID.getProviders()));
        AdUtils.log("Providers after sort " + groupByID.getProviders().size());
    }

    public void addVideoAdProvider(VideoAdProvider videoAdProvider) {
        videoAdProvider.updateName(getProviderCountByID(videoAdProvider.getProviderID()));
        videoAdProvider.setAdManager(this.mAdManager);
        videoAdProvider.setListener(this);
        this.mAllVideoAdProviders.add(videoAdProvider);
    }

    public void addVideoAdProviderToGroup(int i, int i2) {
        VideoAdProvider providerByID = getProviderByID(i);
        VideoAdProviderGroup groupByID = getGroupByID(i2);
        AdUtils.log("Adding vidprovider " + providerByID + " to group " + i2);
        if (groupByID == null) {
            this.mVideoAdProviderGroups.add(new VideoAdProviderGroup(i2));
            groupByID = getGroupByID(i2);
        }
        groupByID.getProviders().add(providerByID);
    }

    private VideoAdProvider getProviderByID(int i) {
        for (VideoAdProvider videoAdProvider : this.mAllVideoAdProviders) {
            if (videoAdProvider.getProviderID() == i) {
                return videoAdProvider;
            }
        }
        return null;
    }

    public boolean checkIfProviderExists(int i) {
        Iterator<VideoAdProvider> it = this.mAllVideoAdProviders.iterator();
        while (it.hasNext()) {
            if (it.next().getProviderID() == i) {
                return true;
            }
        }
        return false;
    }

    private VideoAdProviderGroup getGroupByID(int i) {
        for (VideoAdProviderGroup videoAdProviderGroup : this.mVideoAdProviderGroups) {
            if (videoAdProviderGroup.getGroupID() == i) {
                return videoAdProviderGroup;
            }
        }
        return null;
    }

    public boolean isEnabled() {
        return this.mVideoAdProviderGroups.size() > 0;
    }

    public boolean showVideoAd(int i) {
        VideoAdProviderGroup groupByID = getGroupByID(i);
        if (groupByID == null) {
            AdUtils.log("Show video ad failed: no such group " + i + "!");
            IVideoAdListener iVideoAdListener = this.mVideoAdListener;
            if (iVideoAdListener != null) {
                iVideoAdListener.onAdFailed();
            }
            return false;
        }
        if (groupByID.getProviders().size() == 0) {
            AdUtils.log("Show video ad failed: no providers added!");
            IVideoAdListener iVideoAdListener2 = this.mVideoAdListener;
            if (iVideoAdListener2 != null) {
                iVideoAdListener2.onAdFailed();
            }
            return false;
        }
        if (groupByID.playVideoAd()) {
            return true;
        }
        IVideoAdListener iVideoAdListener3 = this.mVideoAdListener;
        if (iVideoAdListener3 != null) {
            iVideoAdListener3.onAdFailed();
        }
        return false;
    }

    public void restartProviders() {
        Iterator<VideoAdProviderGroup> it = this.mVideoAdProviderGroups.iterator();
        while (it.hasNext()) {
            it.next().restartProviders();
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
    public void onAdFailed() {
        IVideoAdListener iVideoAdListener = this.mVideoAdListener;
        if (iVideoAdListener != null) {
            iVideoAdListener.onAdFailed();
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
    public void onAdViewed() {
        IVideoAdListener iVideoAdListener = this.mVideoAdListener;
        if (iVideoAdListener != null) {
            iVideoAdListener.onAdViewed();
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
    public void onAdCancelled() {
        IVideoAdListener iVideoAdListener = this.mVideoAdListener;
        if (iVideoAdListener != null) {
            iVideoAdListener.onAdCancelled();
        }
    }

    public void pause() {
        Iterator<VideoAdProviderGroup> it = this.mVideoAdProviderGroups.iterator();
        while (it.hasNext()) {
            it.next().pause();
        }
    }

    public void resume(Activity activity) {
        Iterator<VideoAdProviderGroup> it = this.mVideoAdProviderGroups.iterator();
        while (it.hasNext()) {
            it.next().resume(activity);
        }
    }

    public boolean hasCampaigns(int i) {
        VideoAdProviderGroup groupByID = getGroupByID(i);
        if (groupByID == null) {
            AdUtils.log("No such group");
            return false;
        }
        return groupByID.hasCampaigns();
    }

    public void setGDPRConsentStatus(int i, boolean z) {
        VideoAdProvider providerByID = getProviderByID(i);
        if (providerByID != null) {
            providerByID.setGDPRConsentStatus(z);
        }
    }
}
