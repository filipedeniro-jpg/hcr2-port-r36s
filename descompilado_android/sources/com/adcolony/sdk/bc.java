package com.adcolony.sdk;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
class bc {
    private static int a;
    private static int b;
    private static int c;
    private static int d;

    bc() {
    }

    /* JADX WARN: Code duplicated, block: B:77:0x012e  */
    public static float a(View view, Context context, boolean z, boolean z2, boolean z3) {
        float f;
        if (view == null || context == null || view.getVisibility() != 0 || a(view) == 0.0f || !z) {
            return 0.0f;
        }
        if (z3 && (context instanceof Activity) && !((Activity) context).hasWindowFocus()) {
            return 0.0f;
        }
        if (view.getHeight() > 0 && view.getWidth() > 0) {
            float height = view.getHeight() * view.getWidth();
            Rect rect = new Rect();
            boolean globalVisibleRect = view.getGlobalVisibleRect(rect);
            int[] iArr = {-1, -1};
            view.getLocationInWindow(iArr);
            int[] iArr2 = {-1, -1};
            iArr2[0] = iArr[0] + view.getWidth();
            iArr2[1] = iArr[1] + view.getHeight();
            int iA = a(context);
            int iB = b(context);
            if (iArr2[0] >= 0 && iArr2[1] >= 0 && iArr[0] <= iB && iArr[1] <= iA && ((rect.top != 0 || iArr[1] <= iA / 2) && globalVisibleRect)) {
                float fHeight = rect.height() * rect.width();
                if (fHeight > 0.0f) {
                    if (z2) {
                        try {
                            float fA = a(view, rect, fHeight, false);
                            if (fA > 0.0f && fA <= fHeight) {
                                fHeight -= fA;
                            }
                        } catch (Exception unused) {
                        }
                    }
                    float f2 = (fHeight / height) * 100.0f;
                    if (f2 < 0.0f) {
                        return 0.0f;
                    }
                    if (f2 > 100.0f) {
                        return 100.0f;
                    }
                    return f2;
                }
            }
        } else if (view.getLayoutParams().height == -2) {
            int[] iArr3 = {-1, -1};
            view.getLocationInWindow(iArr3);
            int[] iArr4 = {-1, -1};
            iArr4[0] = iArr3[0] + view.getWidth();
            iArr4[1] = iArr3[1] + 1;
            Rect rect2 = new Rect(iArr3[0], iArr3[1], iArr4[0], iArr4[1]);
            int iA2 = a(context);
            int iB2 = b(context);
            if (iArr4[0] >= 0 && iArr4[1] >= 0 && iArr3[0] <= iB2 && iArr3[1] <= iA2 && (rect2.top != 0 || iArr3[1] <= iA2 / 2)) {
                float fHeight2 = rect2.height() * rect2.width();
                if (z2) {
                    try {
                        float fA2 = a(view, rect2, fHeight2, true);
                        if (fA2 <= 0.0f || fA2 > fHeight2) {
                            f = fHeight2;
                        } else {
                            f = fHeight2 - fA2;
                        }
                    } catch (Exception unused2) {
                    }
                } else {
                    f = fHeight2;
                }
                float f3 = (f / fHeight2) * 100.0f;
                if (f3 < 0.0f) {
                    return 0.0f;
                }
                if (f3 > 100.0f) {
                    return 100.0f;
                }
                return f3;
            }
        }
        return 0.0f;
    }

    static float a(View view, Rect rect, float f, boolean z) {
        View viewFindViewById;
        float fWidth;
        boolean z2;
        LinkedList<View> linkedList = new LinkedList();
        LinkedList<Rect> linkedList2 = new LinkedList();
        LinkedList<Rect> linkedList3 = new LinkedList();
        LinkedList linkedList4 = new LinkedList();
        ArrayList arrayList = new ArrayList();
        linkedList3.add(rect);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        View rootView = view.getRootView();
        try {
            viewFindViewById = ((Activity) view.getContext()).findViewById(R.id.content);
            while (true) {
                fWidth = 0.0f;
                if (viewGroup == null || viewGroup.getParent() == rootView) {
                    break;
                }
                if (viewGroup.getVisibility() != 0 || a(viewGroup) == 0.0f) {
                    return f;
                }
                if (viewFindViewById != null && z && viewGroup != viewFindViewById && (viewGroup.getLayoutParams().height == 0 || viewGroup.getLayoutParams().width == 0)) {
                    return f;
                }
                linkedList.addFirst(viewGroup);
                viewGroup = (ViewGroup) viewGroup.getParent();
            }
        } catch (Exception unused) {
            viewFindViewById = null;
        }
        if (viewGroup == null) {
            return f;
        }
        for (View view2 : linkedList) {
            ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
            if (viewGroup2 == null) {
                return f;
            }
            if (!"viewpager".equalsIgnoreCase(viewGroup2.getClass().getSimpleName()) && (iIndexOfChild = viewGroup2.indexOfChild(view2)) < viewGroup2.getChildCount() - 1) {
                while (true) {
                    int iIndexOfChild = iIndexOfChild + 1;
                    if (iIndexOfChild < viewGroup2.getChildCount()) {
                        View childAt = viewGroup2.getChildAt(iIndexOfChild);
                        if (!c(childAt)) {
                            if (childAt.getVisibility() == 0 && a(childAt) != 0.0f) {
                                linkedList4.addFirst(childAt);
                            }
                        } else {
                            ArrayList<View> arrayListB = b(childAt);
                            if (arrayListB != null) {
                                linkedList4.addAll(0, arrayListB);
                            }
                        }
                    }
                }
            }
        }
        Iterator it = linkedList4.iterator();
        float fWidth2 = 0.0f;
        while (true) {
            if (it.hasNext()) {
                View view3 = (View) it.next();
                if (fWidth2 < f) {
                    if (view3 != null) {
                        try {
                            if (view3.getTag() == null || !((String) view3.getTag()).contains("BTN_CLOSE")) {
                            }
                        } catch (Exception unused2) {
                        }
                    }
                    Rect rect2 = new Rect();
                    if (view3.getGlobalVisibleRect(rect2)) {
                        if (z) {
                            rect2.top++;
                        }
                        if (rect2.intersect(rect)) {
                            linkedList2.add(rect2);
                            fWidth2 = rect2.width() * rect2.height();
                            if (fWidth2 >= f) {
                                z2 = true;
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            z2 = false;
            break;
        }
        if (z2) {
            return f;
        }
        if (linkedList2.isEmpty() || linkedList2.size() == 1) {
            return fWidth2;
        }
        for (Rect rect3 : linkedList2) {
            arrayList.clear();
            arrayList.addAll(linkedList3);
            for (int i = 0; i < arrayList.size(); i++) {
                Rect rect4 = (Rect) arrayList.get(i);
                if (rect3.intersect(rect4)) {
                    linkedList3.remove(arrayList.get(i));
                    for (int i2 = 1; i2 < 9; i2++) {
                        Rect rectA = a(rect4, rect3, i2);
                        if (rectA.height() > 0 && rectA.width() > 0) {
                            linkedList3.add(rectA);
                        }
                    }
                }
            }
        }
        if (!linkedList3.isEmpty()) {
            for (Rect rect5 : linkedList3) {
                fWidth += rect5.width() * rect5.height();
            }
            if (fWidth < f) {
                return f - fWidth;
            }
        }
        return fWidth2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static Rect a(Rect rect, Rect rect2, int i) {
        Rect rect3 = new Rect();
        switch (i) {
            case 1:
                rect3.set(rect.left, rect.top, rect2.left, rect2.top);
                return rect3;
            case 2:
                rect3.set(rect2.left, rect.top, rect2.right, rect2.top);
                return rect3;
            case 3:
                rect3.set(rect2.right, rect.top, rect.right, rect2.top);
                return rect3;
            case 4:
                rect3.set(rect2.right, rect2.top, rect.right, rect2.bottom);
                return rect3;
            case 5:
                rect3.set(rect2.right, rect2.bottom, rect.right, rect.bottom);
                return rect3;
            case 6:
                rect3.set(rect2.left, rect2.bottom, rect2.right, rect.bottom);
                return rect3;
            case 7:
                rect3.set(rect.left, rect2.bottom, rect2.left, rect.bottom);
                return rect3;
            case 8:
                rect3.set(rect.left, rect2.top, rect2.left, rect2.bottom);
                return rect3;
            default:
                return rect3;
        }
    }

    @SuppressLint({"NewApi"})
    static float a(View view) {
        if (view == null) {
            return 0.0f;
        }
        if (a() < 11) {
            return 1.0f;
        }
        return view.getAlpha();
    }

    private static ArrayList<View> b(View view) {
        if ((view instanceof ViewGroup) && view.getVisibility() == 0 && a(view) != 0.0f) {
            LinkedList linkedList = new LinkedList();
            ArrayList<View> arrayList = new ArrayList<>();
            linkedList.add((ViewGroup) view);
            ListIterator listIterator = linkedList.listIterator();
            while (listIterator.hasNext()) {
                ViewGroup viewGroup = (ViewGroup) listIterator.next();
                listIterator.remove();
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0 && a(childAt) != 0.0f) {
                        if (childAt instanceof ViewGroup) {
                            if (c(childAt)) {
                                listIterator.add((ViewGroup) childAt);
                                listIterator.previous();
                            } else {
                                arrayList.add(childAt);
                            }
                        } else if (!c(childAt)) {
                            arrayList.add(childAt);
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private static boolean c(View view) {
        if (view == null) {
            return false;
        }
        return view.getBackground() == null || (a() > 18 && view.getBackground().getAlpha() == 0);
    }

    @SuppressLint({"NewApi"})
    public static int a(Context context) {
        int height;
        int i;
        int i2;
        int i3 = context != null ? context.getResources().getConfiguration().orientation : -1;
        if (i3 == 2 && (i2 = a) > 0) {
            return i2;
        }
        if (i3 == 1 && (i = c) > 0) {
            return i;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (a() >= 13) {
                height = a.a(windowManager);
            } else {
                height = windowManager.getDefaultDisplay().getHeight();
            }
            if (i3 == 2) {
                a = height;
            } else if (i3 == 1) {
                c = height;
            }
            return height;
        } catch (Exception unused) {
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    public static int b(Context context) {
        int width;
        int i;
        int i2;
        int i3 = context != null ? context.getResources().getConfiguration().orientation : -1;
        if (i3 == 2 && (i2 = b) > 0) {
            return i2;
        }
        if (i3 == 1 && (i = d) > 0) {
            return i;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (a() >= 13) {
                width = b.a(windowManager);
            } else {
                width = windowManager.getDefaultDisplay().getWidth();
            }
            if (i3 == 2) {
                b = width;
            } else if (i3 == 1) {
                d = width;
            }
            return width;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int a() {
        return Build.VERSION.SDK_INT;
    }

    private static class b {
        private b() {
        }

        @SuppressLint({"NewApi"})
        public static int a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        }
    }

    private static class a {
        private a() {
        }

        @SuppressLint({"NewApi"})
        public static int a(WindowManager windowManager) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.y;
        }
    }
}
