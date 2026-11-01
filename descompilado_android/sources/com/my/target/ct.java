package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NativeAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ct extends e<com.my.target.core.models.sections.a> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        VideoData videoData;
        com.my.target.core.models.sections.a aVar = (com.my.target.core.models.sections.a) alVar;
        List<com.my.target.core.models.banners.a> listR = aVar.R();
        ArrayList arrayList = new ArrayList();
        for (com.my.target.core.models.banners.a aVar2 : listR) {
            ak<VideoData> videoBanner = aVar2.getVideoBanner();
            if (videoBanner != null && (videoData = (VideoData) videoBanner.getMediaData()) != null && bVar.isAutoLoadVideo() && videoData.isCacheable()) {
                videoData.setData(ay.ao().f(videoData.getUrl(), context));
            }
            ImageData image = aVar2.getImage();
            if (image != null) {
                image.useCache(true);
                arrayList.add(image);
            }
            ImageData icon = aVar2.getIcon();
            if (icon != null) {
                icon.useCache(true);
                arrayList.add(icon);
            }
            Iterator<com.my.target.core.models.banners.b> it = aVar2.getNativeAdCards().iterator();
            while (it.hasNext()) {
                ImageData image2 = it.next().getImage();
                if (image2 != null) {
                    image2.useCache(true);
                    arrayList.add(image2);
                }
            }
            ae adChoices = aVar2.getAdChoices();
            if (adChoices != null) {
                ImageData icon2 = adChoices.getIcon();
                icon2.useCache(true);
                arrayList.add(icon2);
            }
        }
        if (bVar.isAutoLoadImages() && arrayList.size() > 0) {
            cf.b(arrayList).v(context);
        }
        return aVar;
    }

    @NonNull
    public static ct e() {
        return new ct();
    }

    private ct() {
    }
}
