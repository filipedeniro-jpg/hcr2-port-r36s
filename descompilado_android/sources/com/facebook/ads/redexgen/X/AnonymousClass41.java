package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.41, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass41 extends C4K {
    private static PackageManager B;

    public AnonymousClass41(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        B = context.getPackageManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Nullable Dereference"})
    public AbstractC02043f D(HashMap<String, AnonymousClass42> map) {
        int iA = 0;
        Set<String> keySet = map.keySet();
        for (String str : keySet) {
            iA += map.get(str).A() + str.getBytes().length;
        }
        return new C3Y(SystemClock.elapsedRealtime(), K(), map, C3S.MAP, iA);
    }

    public final C4S L(final List<C02093k> list) {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.43
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                HashMap map = new HashMap();
                for (C02093k c02093k : list) {
                    if (c02093k.B()) {
                        String str = (String) c02093k.A();
                        AnonymousClass42 deviceFeatures = new AnonymousClass42(str);
                        map.put(str, deviceFeatures);
                    }
                }
                return this.B.D((HashMap<String, AnonymousClass42>) map);
            }
        };
    }
}
