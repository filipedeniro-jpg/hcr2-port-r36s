package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ViewabilityOverlapCalculator {
    private static final String LOGTAG = "ViewabilityOverlapCalculator";
    private final AdController adController;
    private final MobileAdsLogger logger;
    private View rootView;
    private Rect visibleAdRectangle;

    public ViewabilityOverlapCalculator(AdController adController) {
        this(adController, new MobileAdsLoggerFactory());
    }

    ViewabilityOverlapCalculator(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public float calculateViewablePercentage(View view, Rect rect) {
        int width = view.getWidth() * view.getHeight();
        float f = width;
        if (f == 0.0f) {
            return 0.0f;
        }
        this.visibleAdRectangle = rect;
        if (this.rootView == null) {
            this.rootView = this.adController.getRootView();
        }
        ArrayList arrayList = new ArrayList();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null) {
            this.logger.d("AdContainer is null");
            return 0.0f;
        }
        findOverlapppingViews(new Rectangle(rect), viewGroup.indexOfChild(view) + 1, viewGroup, arrayList, true);
        int iWidth = (rect.width() * rect.height()) - getTotalAreaOfSetOfRectangles(arrayList);
        this.logger.d("Visible area: %s , Total area: %s", Integer.valueOf(iWidth), Integer.valueOf(width));
        return (iWidth / f) * 100.0f;
    }

    @TargetApi(11)
    private void findOverlapppingViews(Rectangle rectangle, int i, ViewGroup viewGroup, List<Rectangle> list, boolean z) {
        ViewParent parent;
        if (viewGroup != null && z && AndroidTargetUtils.isAdTransparent(viewGroup)) {
            list.add(new Rectangle(this.visibleAdRectangle));
            return;
        }
        for (int i2 = i; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            boolean z2 = childAt != null && (childAt instanceof ListView);
            if (childAt.isShown() && (!AndroidTargetUtils.isAtLeastAndroidAPI(11) || childAt.getAlpha() != 0.0f)) {
                Rectangle viewRectangle = getViewRectangle(childAt);
                if (viewRectangle.intersect(rectangle)) {
                    if (!z2 && (childAt instanceof ViewGroup)) {
                        findOverlapppingViews(rectangle, 0, (ViewGroup) childAt, list, false);
                    } else {
                        this.logger.d("Overlap found with View: %s", childAt);
                        list.add(viewRectangle);
                    }
                }
            }
        }
        if (z && !this.rootView.equals(viewGroup) && (parent = viewGroup.getParent()) != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup2 = (ViewGroup) parent;
            findOverlapppingViews(rectangle, viewGroup2.indexOfChild(viewGroup) + 1, viewGroup2, list, true);
        }
    }

    private Rectangle getViewRectangle(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Rectangle(iArr[0], iArr[1], view.getWidth() + iArr[0], view.getHeight() + iArr[1]);
    }

    protected int getTotalAreaOfSetOfRectangles(List<Rectangle> list) {
        int[] iArr = new int[list.size() * 2];
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Rectangle rectangle = list.get(i2);
            int i3 = i2 * 2;
            iArr[i3] = rectangle.getLeft();
            iArr[i3 + 1] = rectangle.getRight();
        }
        Arrays.sort(iArr);
        Collections.sort(list);
        int iComputeArea = 0;
        while (i < iArr.length - 1) {
            int i4 = iArr[i];
            i++;
            int i5 = iArr[i];
            if (i4 != i5) {
                Range range = new Range(i4, i5);
                iComputeArea += computeArea(range, getYRanges(range, list));
            }
        }
        return iComputeArea;
    }

    private int computeArea(Range range, List<Range> list) {
        int i = range.high - range.low;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Range range2 = list.get(i3);
            i2 += (range2.high - range2.low) * i;
        }
        return i2;
    }

    protected List<Range> getYRanges(Range range, List<Rectangle> list) {
        ArrayList arrayList = new ArrayList();
        Range range2 = null;
        for (int i = 0; i < list.size(); i++) {
            Rectangle rectangle = list.get(i);
            if (range.low < rectangle.getRight() && range.high > rectangle.getLeft()) {
                Range range3 = new Range(rectangle.getTop(), rectangle.getBottom());
                if (range2 != null && range3.isOverlap(range2)) {
                    range2.mergeRange(range3);
                } else {
                    arrayList.add(range3);
                    range2 = range3;
                }
            }
        }
        return arrayList;
    }

    class Range {
        int high;
        int low;

        public Range(int i, int i2) {
            this.low = i;
            this.high = i2;
        }

        public boolean isOverlap(Range range) {
            return this.low <= range.high && this.high >= range.low;
        }

        public void mergeRange(Range range) {
            int i = this.low;
            int i2 = range.low;
            if (i > i2) {
                i = i2;
            }
            this.low = i;
            int i3 = this.high;
            int i4 = range.high;
            if (i3 >= i4) {
                i4 = i3;
            }
            this.high = i4;
        }
    }

    class Rectangle implements Comparable<Rectangle> {
        private final Rect rect;

        public Rectangle(Rect rect) {
            this.rect = rect;
        }

        public Rectangle(int i, int i2, int i3, int i4) {
            this.rect = new Rect();
            Rect rect = this.rect;
            rect.left = i;
            rect.top = i2;
            rect.right = i3;
            rect.bottom = i4;
        }

        public int getLeft() {
            return this.rect.left;
        }

        public int getTop() {
            return this.rect.top;
        }

        public int getRight() {
            return this.rect.right;
        }

        public int getBottom() {
            return this.rect.bottom;
        }

        public boolean intersect(Rectangle rectangle) {
            if (this.rect.width() == 0 || this.rect.height() == 0) {
                return false;
            }
            return this.rect.intersect(rectangle.rect);
        }

        public boolean contains(Rectangle rectangle) {
            return this.rect.contains(rectangle.rect);
        }

        public int getWidth() {
            return this.rect.width();
        }

        public int getHeight() {
            return this.rect.height();
        }

        @Override // java.lang.Comparable
        public int compareTo(Rectangle rectangle) {
            if (this.rect.top < rectangle.rect.top) {
                return 1;
            }
            return this.rect.top == rectangle.rect.top ? 0 : -1;
        }
    }
}
