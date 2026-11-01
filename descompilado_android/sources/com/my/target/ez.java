package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;

/* JADX INFO: compiled from: NativeAppwallAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ez extends e<com.my.target.core.models.sections.e> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        com.my.target.core.models.sections.e eVar = (com.my.target.core.models.sections.e) alVar;
        if (bVar.getCachePeriod() > 0 && !eVar.D() && eVar.getRawData() != null) {
            cd cdVarT = cd.t(context);
            int slotId = bVar.getSlotId();
            if (cdVarT != null) {
                cdVarT.a(slotId, eVar.getRawData().toString(), false);
            } else {
                g.a("unable to open disk cache and save data for slotId ".concat(String.valueOf(slotId)));
            }
        }
        if (bVar.isAutoLoadImages()) {
            ArrayList arrayList = new ArrayList();
            for (com.my.target.core.models.banners.j jVar : eVar.R()) {
                ImageData statusIcon = jVar.getStatusIcon();
                ImageData coinsIcon = jVar.getCoinsIcon();
                ImageData gotoAppIcon = jVar.getGotoAppIcon();
                ImageData icon = jVar.getIcon();
                ImageData labelIcon = jVar.getLabelIcon();
                ImageData bubbleIcon = jVar.getBubbleIcon();
                ImageData itemHighlightIcon = jVar.getItemHighlightIcon();
                ImageData crossNotifIcon = jVar.getCrossNotifIcon();
                if (statusIcon != null) {
                    arrayList.add(statusIcon);
                }
                if (coinsIcon != null) {
                    arrayList.add(coinsIcon);
                }
                if (gotoAppIcon != null) {
                    arrayList.add(gotoAppIcon);
                }
                if (icon != null) {
                    arrayList.add(icon);
                }
                if (labelIcon != null) {
                    arrayList.add(labelIcon);
                }
                if (bubbleIcon != null) {
                    arrayList.add(bubbleIcon);
                }
                if (itemHighlightIcon != null) {
                    arrayList.add(itemHighlightIcon);
                }
                if (crossNotifIcon != null) {
                    arrayList.add(crossNotifIcon);
                }
            }
            if (arrayList.size() > 0) {
                cf.b(arrayList).v(context);
            }
        }
        return eVar;
    }

    @NonNull
    public static ez e() {
        return new ez();
    }

    private ez() {
    }
}
