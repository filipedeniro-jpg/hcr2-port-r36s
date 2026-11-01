package com.fingersoft.ads_sdk.advertising.Comparators;

import com.fingersoft.ads_sdk.advertising.providers.AdProvider;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AdProviderComparator implements Comparator<AdProvider> {
    List<AdProvider> list;
    List<String> providers = new ArrayList();

    public AdProviderComparator(String str) {
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

    public List<AdProvider> sort(List<AdProvider> list) {
        ArrayList arrayList = new ArrayList();
        for (AdProvider adProvider : list) {
            if (this.providers.indexOf(adProvider.getName()) > -1) {
                arrayList.add(adProvider);
            }
        }
        Collections.sort(arrayList, this);
        return arrayList;
    }

    @Override // java.util.Comparator
    public int compare(AdProvider adProvider, AdProvider adProvider2) {
        if (this.providers.indexOf(adProvider.getName()) == -1 || this.providers.indexOf(adProvider2.getName()) == -1) {
            return -1;
        }
        int iIndexOf = this.providers.indexOf(adProvider.getName()) - this.providers.indexOf(adProvider2.getName());
        if (iIndexOf > 0) {
            return 1;
        }
        return iIndexOf < 0 ? -1 : 0;
    }
}
