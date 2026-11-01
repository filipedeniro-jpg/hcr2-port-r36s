package com.integralads.avid.library.inmobi.session.internal;

import android.view.View;
import com.integralads.avid.library.inmobi.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class ObstructionsWhiteList {
    private final ArrayList<AvidView> whiteList = new ArrayList<>();

    public void add(View view) {
        this.whiteList.add(new AvidView(view));
    }

    public boolean contains(View view) {
        Iterator<AvidView> it = this.whiteList.iterator();
        while (it.hasNext()) {
            if (it.next().contains(view)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AvidView> getWhiteList() {
        return this.whiteList;
    }
}
