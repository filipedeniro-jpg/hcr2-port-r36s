package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;

/* JADX INFO: compiled from: StandardAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dh extends e<com.my.target.core.models.sections.b> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        ImageData image;
        com.my.target.core.models.sections.b bVar2 = (com.my.target.core.models.sections.b) alVar;
        com.my.target.core.models.banners.c cVarG = bVar2.G();
        if (cVarG == null) {
            return null;
        }
        if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(cVarG.getViewSettings().l())) {
            ArrayList arrayList = new ArrayList();
            ImageData icon = cVarG.getIcon();
            if (icon != null) {
                arrayList.add(icon);
            }
            ImageData image2 = cVarG.getImage();
            if (image2 != null) {
                arrayList.add(image2);
            }
            if (arrayList.size() > 0) {
                cf.b(arrayList).v(context);
            }
            if (ai.a.cW.equals(cVarG.getType()) && ((image = cVarG.getImage()) == null || image.getBitmap() == null)) {
                return null;
            }
        }
        return bVar2;
    }

    @NonNull
    public static dh e() {
        return new dh();
    }

    private dh() {
    }
}
