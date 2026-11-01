package com.facebook.ads.redexgen.X;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5F, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5F {
    private C5F() {
    }

    @Nullable
    public static Float B(View targetView) {
        Activity activityB = MI.B();
        if (activityB == null) {
            return null;
        }
        View viewFindViewById = activityB.findViewById(R.id.content);
        if (viewFindViewById == null) {
            viewFindViewById = activityB.getWindow().getDecorView().findViewById(R.id.content);
        }
        if (viewFindViewById == null || targetView == null || targetView.getId() == -1) {
            return null;
        }
        if (viewFindViewById.findViewById(targetView.getId()) == null) {
            return Float.valueOf(-1.0f);
        }
        return E(viewFindViewById, targetView);
    }

    @VisibleForTesting
    private static Set<Rect> C(Rect big, Rect small) {
        Set<Rect> resultSet = new HashSet<>();
        if (!small.intersect(big)) {
            resultSet.add(big);
            return resultSet;
        }
        resultSet.add(new Rect(big.left, big.top, small.left, big.bottom));
        resultSet.add(new Rect(small.left, big.top, small.right, small.top));
        resultSet.add(new Rect(small.right, big.top, big.right, big.bottom));
        resultSet.add(new Rect(small.left, small.bottom, small.right, big.bottom));
        Set<Rect> resultSet2 = new HashSet<>();
        for (Rect rect : resultSet) {
            if (rect.width() > 0 && rect.height() > 0) {
                resultSet2.add(rect);
            }
        }
        return resultSet2;
    }

    private static List<View> D(View rootView, View targetView) {
        LinkedList linkedList = new LinkedList();
        Stack stack = new Stack();
        stack.push(rootView);
        boolean z = false;
        while (!stack.empty()) {
            View view = (View) stack.pop();
            if (view.getVisibility() == 0) {
                if (view == targetView) {
                    z = true;
                } else if (!(view instanceof ViewGroup)) {
                    if (z || (Build.VERSION.SDK_INT >= 21 && view.getZ() > targetView.getZ())) {
                        linkedList.add(view);
                    }
                } else {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        stack.push(viewGroup.getChildAt(childCount));
                    }
                }
            }
        }
        return linkedList;
    }

    @VisibleForTesting
    @Nullable
    private static Float E(View rootView, View targetView) {
        if (rootView == null || targetView == null) {
            return null;
        }
        if (targetView.getVisibility() != 0) {
            return Float.valueOf(0.0f);
        }
        List<View> listD = D(rootView, targetView);
        if (listD.isEmpty()) {
            return Float.valueOf(1.0f);
        }
        Rect rect = new Rect();
        if (!targetView.getGlobalVisibleRect(rect)) {
            return Float.valueOf(0.0f);
        }
        int iWidth = rect.width() * rect.height();
        HashSet<Rect> hashSet = new HashSet();
        hashSet.add(rect);
        for (View view : listD) {
            Rect rect2 = new Rect();
            if (!(view instanceof C8N) && view.getGlobalVisibleRect(rect2)) {
                HashSet hashSet2 = new HashSet();
                for (Rect r : hashSet) {
                    Set<Rect> nextUncoveredSurfaces = C(r, rect2);
                    hashSet2.addAll(nextUncoveredSurfaces);
                }
                hashSet = hashSet2;
            }
        }
        int i = 0;
        for (Rect rect3 : hashSet) {
            int iWidth2 = rect3.width();
            int uncoveredArea = rect3.height();
            i += iWidth2 * uncoveredArea;
        }
        return Float.valueOf(i / iWidth);
    }
}
