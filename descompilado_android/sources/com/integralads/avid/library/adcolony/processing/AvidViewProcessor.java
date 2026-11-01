package com.integralads.avid.library.adcolony.processing;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidViewProcessor implements IAvidNodeProcessor {
    private final int[] a = new int[2];

    @Override // com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor
    public JSONObject getState(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.a);
        int[] iArr = this.a;
        return AvidJSONUtil.getViewState(iArr[0], iArr[1], width, height);
    }

    @Override // com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor
    public void iterateChildren(View view, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || Build.VERSION.SDK_INT < 21) {
                a(viewGroup, jSONObject, iAvidViewWalker);
            } else {
                b(viewGroup, jSONObject, iAvidViewWalker);
            }
        }
    }

    private void a(ViewGroup viewGroup, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            iAvidViewWalker.walkView(viewGroup.getChildAt(i), this, jSONObject);
        }
    }

    @TargetApi(21)
    private void b(ViewGroup viewGroup, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker) {
        HashMap map = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) map.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        ArrayList arrayList2 = new ArrayList(map.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) map.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                iAvidViewWalker.walkView((View) it2.next(), this, jSONObject);
            }
        }
    }
}
