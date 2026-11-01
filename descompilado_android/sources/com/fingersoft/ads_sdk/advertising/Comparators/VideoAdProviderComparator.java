package com.fingersoft.ads_sdk.advertising.Comparators;

import com.fingersoft.ads_sdk.advertising.video.VideoAdProvider;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VideoAdProviderComparator implements Comparator<VideoAdProvider> {
    private List<VideoAdProvider> list;
    private List<String> providers = new ArrayList();

    public VideoAdProviderComparator(String str) {
        processList(str);
    }

    private void processList(String str) {
        for (String str2 : str.split(",")) {
            if (str2.contains(Constants.RequestParameters.EQUAL)) {
                this.providers.add(str2.split(Constants.RequestParameters.EQUAL)[0]);
            } else {
                this.providers.add(str2);
            }
        }
    }

    public ArrayList<VideoAdProvider> sort(ArrayList<VideoAdProvider> arrayList) {
        ArrayList<VideoAdProvider> arrayList2 = new ArrayList<>();
        for (VideoAdProvider videoAdProvider : arrayList) {
            if (this.providers.indexOf(videoAdProvider.getName()) > -1) {
                arrayList2.add(videoAdProvider);
            }
        }
        Collections.sort(arrayList2, this);
        return arrayList2;
    }

    @Override // java.util.Comparator
    public int compare(VideoAdProvider videoAdProvider, VideoAdProvider videoAdProvider2) {
        if (this.providers.indexOf(videoAdProvider.getName()) == -1 || this.providers.indexOf(videoAdProvider2.getName()) == -1) {
            return -1;
        }
        int iIndexOf = this.providers.indexOf(videoAdProvider.getName()) - this.providers.indexOf(videoAdProvider2.getName());
        if (iIndexOf > 0) {
            return 1;
        }
        return iIndexOf < 0 ? -1 : 0;
    }
}
