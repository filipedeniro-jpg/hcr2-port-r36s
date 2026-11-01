package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02153q extends C4K {
    private final Context B;

    public C02153q(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        this.B = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Nullable Dereference"})
    public AbstractC02043f D(HashMap<String, C02163r> map) {
        int iA = 0;
        Set<String> keySet = map.keySet();
        for (String str : keySet) {
            iA += map.get(str).A() + str.getBytes().length;
        }
        return new C3Y(SystemClock.elapsedRealtime(), K(), map, C3S.MAP, iA);
    }

    public final C4S L(final List<C02093k> list, final EnumC02173s settingType) {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.3u
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                HashMap map = new HashMap();
                for (C02093k c02093k : list) {
                    if (c02093k.B()) {
                        String str = (String) c02093k.A();
                        map.put(str, new C02163r(this.B.B, str, settingType));
                    }
                }
                return this.B.D((HashMap<String, C02163r>) map);
            }
        };
    }
}
