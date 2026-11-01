package com.unity3d.services.monetization.core.placementcontent;

import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.NoFillPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import com.unity3d.services.monetization.placementcontent.purchasing.PromoAdPlacementContent;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PlacementContentResultFactory {

    public enum PlacementContentResultType {
        SHOW_AD,
        PROMO_AD,
        NO_FILL,
        CUSTOM;

        static PlacementContentResultType parse(String str) {
            if (str == null) {
                return CUSTOM;
            }
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                return CUSTOM;
            }
        }
    }

    public static PlacementContent create(String str, Map<String, Object> map) {
        int i = AnonymousClass1.$SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType[PlacementContentResultType.parse((String) map.get("type")).ordinal()];
        if (i == 1) {
            return new ShowAdPlacementContent(str, map);
        }
        if (i == 2) {
            return new PromoAdPlacementContent(str, map);
        }
        if (i == 3) {
            return new NoFillPlacementContent(str, map);
        }
        return new PlacementContent(str, map);
    }

    /* JADX INFO: renamed from: com.unity3d.services.monetization.core.placementcontent.PlacementContentResultFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType = new int[PlacementContentResultType.values().length];

        static {
            try {
                $SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType[PlacementContentResultType.SHOW_AD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType[PlacementContentResultType.PROMO_AD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType[PlacementContentResultType.NO_FILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$unity3d$services$monetization$core$placementcontent$PlacementContentResultFactory$PlacementContentResultType[PlacementContentResultType.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
