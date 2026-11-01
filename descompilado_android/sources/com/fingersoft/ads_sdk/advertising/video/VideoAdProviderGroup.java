package com.fingersoft.ads_sdk.advertising.video;

import android.app.Activity;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class VideoAdProviderGroup {
    private int mGroupID;
    private String mPriorityString = "";
    private ArrayList<VideoAdProvider> mVideoAdProviders = new ArrayList<>();
    private VideoAdProvider mCurrentProvider = null;
    private int mCurrentProviderIndex = 0;

    public VideoAdProviderGroup(int i) {
        this.mGroupID = i;
    }

    public void addProvider(VideoAdProvider videoAdProvider) {
        this.mVideoAdProviders.add(videoAdProvider);
    }

    public ArrayList<VideoAdProvider> getProviders() {
        return this.mVideoAdProviders;
    }

    public void setProviders(ArrayList<VideoAdProvider> arrayList) {
        this.mVideoAdProviders = arrayList;
    }

    public int getGroupID() {
        return this.mGroupID;
    }

    public void setPriorityString(String str) {
        this.mPriorityString = str;
    }

    public String getPriorityString() {
        return this.mPriorityString;
    }

    private VideoAdProvider getNextProvider() {
        ArrayList<VideoAdProvider> arrayList = this.mVideoAdProviders;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        this.mCurrentProviderIndex = (this.mCurrentProviderIndex + 1) % this.mVideoAdProviders.size();
        return this.mVideoAdProviders.get(this.mCurrentProviderIndex);
    }

    public boolean playVideoAd() {
        int i;
        int size = getProviders().size();
        if (this.mCurrentProvider == null && size > (i = this.mCurrentProviderIndex)) {
            this.mCurrentProvider = this.mVideoAdProviders.get(i);
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (showAd()) {
                AdUtils.log("Video: ad successful");
                return true;
            }
            AdUtils.log("Video: Ad failed, get next provider");
            this.mCurrentProvider = getNextProvider();
            this.mCurrentProvider.resetShowCount();
        }
        return false;
    }

    private boolean showAd() {
        if (this.mVideoAdProviders == null) {
            return false;
        }
        AdUtils.log("Video:  prov size " + this.mVideoAdProviders.size() + " index " + this.mCurrentProviderIndex);
        if (this.mVideoAdProviders.size() <= this.mCurrentProviderIndex) {
            return false;
        }
        VideoAdProvider videoAdProvider = this.mCurrentProvider;
        if (videoAdProvider == null || !videoAdProvider.canShow()) {
            this.mCurrentProvider = getNextProvider();
            this.mCurrentProvider.resetShowCount();
            AdUtils.log("Video: show count exceeded, next");
        }
        VideoAdProvider videoAdProvider2 = this.mCurrentProvider;
        if (videoAdProvider2 == null) {
            AdUtils.log("Video: provider null");
            return false;
        }
        if (!videoAdProvider2.hasCampaigns()) {
            return false;
        }
        AdUtils.log("Video: playing from " + this.mCurrentProvider.getName());
        if (!this.mCurrentProvider.playVideo()) {
            return false;
        }
        this.mCurrentProvider.increaseShowCount();
        return true;
    }

    public void restartProviders() {
        this.mCurrentProviderIndex = 0;
    }

    public void pause() {
        VideoAdProvider videoAdProvider;
        ArrayList<VideoAdProvider> arrayList = this.mVideoAdProviders;
        if (arrayList == null || arrayList.size() >= this.mCurrentProviderIndex || (videoAdProvider = this.mCurrentProvider) == null) {
            return;
        }
        videoAdProvider.pause();
    }

    public void resume(Activity activity) {
        ArrayList<VideoAdProvider> arrayList = this.mVideoAdProviders;
        if (arrayList == null || arrayList.size() >= this.mCurrentProviderIndex || this.mCurrentProvider == null) {
            return;
        }
        AdUtils.log("Resuming video Ad ");
        this.mCurrentProvider.resume(activity);
    }

    public boolean hasCampaigns() {
        if (this.mVideoAdProviders == null) {
            AdUtils.log("No video providers");
            return false;
        }
        AdUtils.log("Providers found " + this.mVideoAdProviders.size());
        for (VideoAdProvider videoAdProvider : this.mVideoAdProviders) {
            AdUtils.log(videoAdProvider.getName() + " has campaigns: " + videoAdProvider.hasCampaigns());
            if (videoAdProvider.hasCampaigns()) {
                return true;
            }
        }
        return false;
    }
}
