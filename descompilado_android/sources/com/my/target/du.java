package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;

/* JADX INFO: compiled from: InterstitialSliderAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class du extends e<com.my.target.core.models.sections.d> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        com.my.target.core.models.sections.d dVar = (com.my.target.core.models.sections.d) alVar;
        ArrayList arrayList = new ArrayList();
        for (com.my.target.core.models.banners.h hVar : dVar.R()) {
            if (hVar.getPortraitImages().size() > 0) {
                ImageData imageData = hVar.getPortraitImages().get(0);
                arrayList.add(imageData);
                hVar.setOptimalPortraitImage(imageData);
            }
            if (hVar.getLandscapeImages().size() > 0) {
                ImageData imageData2 = hVar.getLandscapeImages().get(0);
                arrayList.add(imageData2);
                hVar.setOptimalLandscapeImage(imageData2);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        if (dVar.getCloseIcon() != null) {
            arrayList.add(dVar.getCloseIcon());
        }
        cf.b(arrayList).k(true).v(context);
        cd cdVarBi = cd.bi();
        if (cdVarBi == null) {
            g.a("Disk cache is not available");
            return null;
        }
        for (com.my.target.core.models.banners.h hVar2 : dVar.R()) {
            ImageData optimalLandscapeImage = hVar2.getOptimalLandscapeImage();
            if (optimalLandscapeImage == null || cdVarBi.O(optimalLandscapeImage.getUrl()) == null) {
                ImageData optimalPortraitImage = hVar2.getOptimalPortraitImage();
                if (optimalPortraitImage == null || cdVarBi.O(optimalPortraitImage.getUrl()) == null) {
                    dVar.d(hVar2);
                }
            }
        }
        if (dVar.getBannersCount() > 0) {
            return dVar;
        }
        return null;
    }

    @NonNull
    public static du f() {
        return new du();
    }

    private du() {
    }
}
