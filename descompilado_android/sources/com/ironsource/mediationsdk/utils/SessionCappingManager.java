package com.ironsource.mediationsdk.utils;

import com.ironsource.mediationsdk.ProgSmash;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SessionCappingManager {
    private Map<String, Integer> mShowCountMap = new HashMap();

    public SessionCappingManager(List<ProgSmash> list) {
        Iterator<ProgSmash> it = list.iterator();
        while (it.hasNext()) {
            this.mShowCountMap.put(it.next().getInstanceName(), 0);
        }
    }

    public void increaseShowCounter(ProgSmash progSmash) {
        synchronized (this) {
            String instanceName = progSmash.getInstanceName();
            if (this.mShowCountMap.containsKey(instanceName)) {
                this.mShowCountMap.put(instanceName, Integer.valueOf(this.mShowCountMap.get(instanceName).intValue() + 1));
            }
        }
    }

    public boolean isCapped(ProgSmash progSmash) {
        synchronized (this) {
            String instanceName = progSmash.getInstanceName();
            if (this.mShowCountMap.containsKey(instanceName)) {
                return this.mShowCountMap.get(instanceName).intValue() >= progSmash.getMaxAdsPerSession();
            }
            return false;
        }
    }
}
