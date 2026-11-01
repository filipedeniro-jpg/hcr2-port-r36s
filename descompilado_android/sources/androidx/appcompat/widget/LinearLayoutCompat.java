package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.tonyodev.fetch.FetchConst;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = tintTypedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i;
        int left2;
        int virtualChildCount = getVirtualChildCount();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (zIsLayoutRtl) {
                    left2 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    left2 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (zIsLayoutRtl) {
                    left = virtualChildAt2.getLeft() - layoutParams2.leftMargin;
                    i = this.mDividerWidth;
                    right = left - i;
                } else {
                    right = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (zIsLayoutRtl) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.mDividerWidth;
                right = left - i;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:151:0x0330  */
    /* JADX WARN: Code duplicated, block: B:157:0x033d  */
    void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int iCombineMeasuredStates;
        int iMax;
        int i5;
        float f;
        int i6;
        int i7;
        boolean z;
        boolean z2;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int iMax2;
        int i13;
        int i14;
        View view;
        int iMax3;
        boolean z3;
        int iMax4;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i15 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        float f2 = 0.0f;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int childrenSkipCount = 0;
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        while (true) {
            int i21 = 8;
            i19 = i19;
            if (childrenSkipCount < virtualChildCount) {
                View virtualChildAt = getVirtualChildAt(childrenSkipCount);
                if (virtualChildAt == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount);
                } else {
                    int i22 = i16;
                    if (virtualChildAt.getVisibility() == 8) {
                        childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                        i16 = i22;
                    } else {
                        if (hasDividerBeforeChildAt(childrenSkipCount)) {
                            this.mTotalLength += this.mDividerHeight;
                        }
                        LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                        float f3 = f2 + layoutParams.weight;
                        if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                            int i23 = this.mTotalLength;
                            this.mTotalLength = Math.max(i23, layoutParams.topMargin + i23 + layoutParams.bottomMargin);
                            iMax3 = i18;
                            view = virtualChildAt;
                            i13 = i20;
                            virtualChildCount = virtualChildCount;
                            i10 = i22;
                            i11 = i17;
                            z5 = true;
                            i14 = childrenSkipCount;
                            i12 = mode2;
                            iMax2 = i19;
                        } else {
                            int i24 = i17;
                            if (layoutParams.height != 0 || layoutParams.weight <= 0.0f) {
                                i9 = Integer.MIN_VALUE;
                            } else {
                                layoutParams.height = -2;
                                i9 = 0;
                            }
                            i10 = i22;
                            int i25 = i9;
                            i11 = i24;
                            int i26 = i18;
                            virtualChildCount = virtualChildCount;
                            i12 = mode2;
                            iMax2 = i19;
                            i13 = i20;
                            i14 = childrenSkipCount;
                            measureChildBeforeLayout(virtualChildAt, childrenSkipCount, i, 0, i2, f3 == 0.0f ? this.mTotalLength : 0);
                            if (i25 != Integer.MIN_VALUE) {
                                layoutParams.height = i25;
                            }
                            int measuredHeight = virtualChildAt.getMeasuredHeight();
                            int i27 = this.mTotalLength;
                            view = virtualChildAt;
                            this.mTotalLength = Math.max(i27, i27 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
                            iMax3 = z4 ? Math.max(measuredHeight, i26) : i26;
                        }
                        if (i15 >= 0 && i15 == i14 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                        if (i14 < i15 && layoutParams.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        if (mode == 1073741824 || layoutParams.width != -1) {
                            z3 = false;
                        } else {
                            z3 = true;
                            z7 = true;
                        }
                        int i28 = layoutParams.leftMargin + layoutParams.rightMargin;
                        int measuredWidth = view.getMeasuredWidth() + i28;
                        int iMax5 = Math.max(i11, measuredWidth);
                        int iCombineMeasuredStates2 = View.combineMeasuredStates(i10, view.getMeasuredState());
                        boolean z8 = z6 && layoutParams.width == -1;
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i28 = measuredWidth;
                            }
                            iMax2 = Math.max(iMax2, i28);
                            iMax4 = i13;
                        } else {
                            if (!z3) {
                                i28 = measuredWidth;
                            }
                            iMax4 = Math.max(i13, i28);
                        }
                        int childrenSkipCount2 = getChildrenSkipCount(view, i14) + i14;
                        i18 = iMax3;
                        z6 = z8;
                        i19 = iMax2;
                        f2 = f3;
                        i20 = iMax4;
                        i16 = iCombineMeasuredStates2;
                        childrenSkipCount = childrenSkipCount2;
                        i17 = iMax5;
                    }
                    childrenSkipCount++;
                    mode2 = i12;
                    virtualChildCount = virtualChildCount;
                }
                i12 = mode2;
                childrenSkipCount++;
                mode2 = i12;
                virtualChildCount = virtualChildCount;
            } else {
                int i29 = i16;
                int i30 = i18;
                int i31 = i20;
                int i32 = virtualChildCount;
                int iMax6 = i17;
                int i33 = mode2;
                if (this.mTotalLength > 0) {
                    i3 = i32;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = i32;
                }
                if (z4) {
                    i4 = i33;
                    if (i4 == Integer.MIN_VALUE || i4 == 0) {
                        this.mTotalLength = 0;
                        int childrenSkipCount3 = 0;
                        while (childrenSkipCount3 < i3) {
                            View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                            if (virtualChildAt2 == null) {
                                this.mTotalLength += measureNullChild(childrenSkipCount3);
                            } else if (virtualChildAt2.getVisibility() == i21) {
                                childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                            } else {
                                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                                int i34 = this.mTotalLength;
                                this.mTotalLength = Math.max(i34, i34 + i30 + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                            }
                            childrenSkipCount3++;
                            i21 = 8;
                        }
                    }
                } else {
                    i4 = i33;
                }
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i2, 0);
                int i35 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
                if (z5 || (i35 != 0 && f2 > 0.0f)) {
                    float f4 = this.mWeightSum;
                    if (f4 > 0.0f) {
                        f2 = f4;
                    }
                    this.mTotalLength = 0;
                    float f5 = f2;
                    int i36 = 0;
                    int iMax7 = i31;
                    iCombineMeasuredStates = i29;
                    while (i36 < i3) {
                        View virtualChildAt3 = getVirtualChildAt(i36);
                        if (virtualChildAt3.getVisibility() == 8) {
                            f = f5;
                        } else {
                            LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                            float f6 = layoutParams3.weight;
                            if (f6 > 0.0f) {
                                int i37 = (int) ((i35 * f6) / f5);
                                i5 = i35 - i37;
                                f = f5 - f6;
                                int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                                if (layoutParams3.height == 0) {
                                    i8 = 1073741824;
                                    if (i4 == 1073741824) {
                                        if (i37 <= 0) {
                                            i37 = 0;
                                        }
                                        virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i37, 1073741824));
                                    }
                                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                                } else {
                                    i8 = 1073741824;
                                }
                                int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i37;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                                virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i8));
                                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                            } else {
                                i5 = i35;
                                f = f5;
                            }
                            int i38 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i38;
                            iMax6 = Math.max(iMax6, measuredWidth2);
                            if (mode != 1073741824) {
                                i6 = iCombineMeasuredStates;
                                i7 = -1;
                                z = layoutParams3.width == -1;
                                if (!z) {
                                    i38 = measuredWidth2;
                                }
                                iMax7 = Math.max(iMax7, i38);
                                if (z6 || layoutParams3.width != i7) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                int i39 = this.mTotalLength;
                                this.mTotalLength = Math.max(i39, virtualChildAt3.getMeasuredHeight() + i39 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                                z6 = z2;
                                i35 = i5;
                                iCombineMeasuredStates = i6;
                            } else {
                                i6 = iCombineMeasuredStates;
                                i7 = -1;
                            }
                            if (!z) {
                                i38 = measuredWidth2;
                            }
                            iMax7 = Math.max(iMax7, i38);
                            if (z6) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            int i310 = this.mTotalLength;
                            this.mTotalLength = Math.max(i310, virtualChildAt3.getMeasuredHeight() + i310 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                            z6 = z2;
                            i35 = i5;
                            iCombineMeasuredStates = i6;
                        }
                        i36++;
                        f5 = f;
                    }
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    iMax = iMax7;
                } else {
                    iMax = Math.max(i31, i19);
                    if (z4 && i4 != 1073741824) {
                        for (int i40 = 0; i40 < i3; i40++) {
                            View virtualChildAt4 = getVirtualChildAt(i40);
                            if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                                virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i30, 1073741824));
                            }
                        }
                    }
                    iCombineMeasuredStates = i29;
                }
                if (z6 || mode == 1073741824) {
                    iMax = iMax6;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates), iResolveSizeAndState);
                if (z7) {
                    forceUniformWidth(i3, i2);
                    return;
                }
                return;
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:200:0x0476  */
    /* JADX WARN: Code duplicated, block: B:60:0x0174  */
    /* JADX WARN: Code duplicated, block: B:67:0x0196  */
    /* JADX WARN: Code duplicated, block: B:68:0x0199  */
    /* JADX WARN: Code duplicated, block: B:75:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:78:0x01cd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:82:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:84:0x01dc  */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int iMax;
        int iMax2;
        int i4;
        int i5;
        int baseline;
        int i6;
        int i7;
        int i8;
        boolean z;
        boolean z2;
        View view;
        int i9;
        boolean z3;
        int i10;
        int measuredHeight;
        boolean z4;
        int baseline2;
        int i11;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z5 = this.mBaselineAligned;
        boolean z6 = this.mUseLargestChild;
        int i12 = 1073741824;
        boolean z7 = mode == 1073741824;
        float f = 0.0f;
        int childrenSkipCount = 0;
        int iMax3 = 0;
        int i13 = 0;
        int iMax4 = 0;
        int iMax5 = 0;
        boolean z8 = false;
        int i14 = 0;
        boolean z9 = true;
        boolean z10 = false;
        while (true) {
            iArr = iArr3;
            if (childrenSkipCount >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
            } else {
                if (virtualChildAt.getVisibility() == 8) {
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f2 = f + layoutParams.weight;
                    if (mode != i12 || layoutParams.width != 0 || layoutParams.weight <= 0.0f) {
                        if (layoutParams.width != 0 || layoutParams.weight <= 0.0f) {
                            i7 = Integer.MIN_VALUE;
                        } else {
                            layoutParams.width = -2;
                            i7 = 0;
                        }
                        i8 = childrenSkipCount;
                        int i15 = i7;
                        z = z6;
                        z2 = z5;
                        measureChildBeforeLayout(virtualChildAt, i8, i, f2 == 0.0f ? this.mTotalLength : 0, i2, 0);
                        if (i15 != Integer.MIN_VALUE) {
                            layoutParams.width = i15;
                        }
                        int measuredWidth = virtualChildAt.getMeasuredWidth();
                        if (z7) {
                            view = virtualChildAt;
                            this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(view);
                        } else {
                            view = virtualChildAt;
                            int i16 = this.mTotalLength;
                            this.mTotalLength = Math.max(i16, i16 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(view));
                        }
                        if (z) {
                            iMax3 = Math.max(measuredWidth, iMax3);
                        }
                    } else {
                        if (z7) {
                            this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                        } else {
                            int i17 = this.mTotalLength;
                            this.mTotalLength = Math.max(i17, layoutParams.leftMargin + i17 + layoutParams.rightMargin);
                        }
                        if (z5) {
                            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                            i8 = childrenSkipCount;
                            z = z6;
                            z2 = z5;
                            view = virtualChildAt;
                        } else {
                            i8 = childrenSkipCount;
                            z = z6;
                            z2 = z5;
                            view = virtualChildAt;
                            i9 = 1073741824;
                            z8 = true;
                        }
                        if (mode2 == i9 && layoutParams.height == -1) {
                            z3 = true;
                            z10 = true;
                        } else {
                            z3 = false;
                        }
                        i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i10;
                        int iCombineMeasuredStates = View.combineMeasuredStates(i14, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            if (layoutParams.gravity < 0) {
                                i11 = this.mGravity;
                            } else {
                                i11 = layoutParams.gravity;
                            }
                            int i18 = (((i11 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i18] = Math.max(iArr2[i18], baseline2);
                            iArr[i18] = Math.max(iArr[i18], measuredHeight - baseline2);
                        }
                        int iMax6 = Math.max(i13, measuredHeight);
                        if (z9 || layoutParams.height != -1) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i10 = measuredHeight;
                            }
                            iMax5 = Math.max(iMax5, i10);
                        } else {
                            int i19 = iMax5;
                            if (z3) {
                                measuredHeight = i10;
                            }
                            iMax4 = Math.max(iMax4, measuredHeight);
                            iMax5 = i19;
                        }
                        int i20 = i8;
                        i13 = iMax6;
                        i14 = iCombineMeasuredStates;
                        z9 = z4;
                        childrenSkipCount = getChildrenSkipCount(view, i20) + i20;
                        f = f2;
                    }
                    i9 = 1073741824;
                    if (mode2 == i9) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i10;
                    int iCombineMeasuredStates2 = View.combineMeasuredStates(i14, view.getMeasuredState());
                    if (z2) {
                        if (layoutParams.gravity < 0) {
                            i11 = this.mGravity;
                        } else {
                            i11 = layoutParams.gravity;
                        }
                        int i110 = (((i11 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i110] = Math.max(iArr2[i110], baseline2);
                        iArr[i110] = Math.max(iArr[i110], measuredHeight - baseline2);
                    }
                    int iMax7 = Math.max(i13, measuredHeight);
                    if (z9) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                    if (layoutParams.weight > 0.0f) {
                        if (!z3) {
                            i10 = measuredHeight;
                        }
                        iMax5 = Math.max(iMax5, i10);
                    } else {
                        int i111 = iMax5;
                        if (z3) {
                            measuredHeight = i10;
                        }
                        iMax4 = Math.max(iMax4, measuredHeight);
                        iMax5 = i111;
                    }
                    int i21 = i8;
                    i13 = iMax7;
                    i14 = iCombineMeasuredStates2;
                    z9 = z4;
                    childrenSkipCount = getChildrenSkipCount(view, i21) + i21;
                    f = f2;
                }
                childrenSkipCount++;
                iArr3 = iArr;
                z6 = z;
                z5 = z2;
                i12 = 1073741824;
            }
            z = z6;
            z2 = z5;
            childrenSkipCount++;
            iArr3 = iArr;
            z6 = z;
            z5 = z2;
            i12 = 1073741824;
        }
        boolean z11 = z6;
        boolean z12 = z5;
        int iMax8 = i13;
        int i22 = iMax4;
        int i23 = iMax5;
        int i24 = i14;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr2[1] != -1 || iArr2[0] != -1 || iArr2[2] != -1 || iArr2[3] != -1) {
            iMax8 = Math.max(iMax8, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z11 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount2 = 0;
            while (childrenSkipCount2 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount2);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount2);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount2);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z7) {
                        this.mTotalLength += layoutParams2.leftMargin + iMax3 + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i25 = this.mTotalLength;
                        this.mTotalLength = Math.max(i25, i25 + iMax3 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                    childrenSkipCount2++;
                    iMax8 = iMax8;
                }
                childrenSkipCount2++;
                iMax8 = iMax8;
            }
        }
        int i26 = iMax8;
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i, 0);
        int i27 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z8 || (i27 != 0 && f > 0.0f)) {
            float f3 = this.mWeightSum;
            if (f3 > 0.0f) {
                f = f3;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i28 = i22;
            int iCombineMeasuredStates3 = i24;
            int iMax9 = -1;
            float f4 = f;
            int i29 = 0;
            while (i29 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i29);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    i4 = i27;
                    virtualChildCount = virtualChildCount;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f5 = layoutParams3.weight;
                    if (f5 > 0.0f) {
                        int i30 = (int) ((i27 * f5) / f4);
                        float f6 = f4 - f5;
                        int i31 = i27 - i30;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0) {
                            i6 = 1073741824;
                            if (mode == 1073741824) {
                                if (i30 <= 0) {
                                    i30 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i30, 1073741824), childMeasureSpec);
                            }
                            iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, virtualChildAt3.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                            f4 = f6;
                            i4 = i31;
                        } else {
                            i6 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i30;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i6), childMeasureSpec);
                        iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, virtualChildAt3.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        f4 = f6;
                        i4 = i31;
                    } else {
                        i4 = i27;
                    }
                    if (z7) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i32 = this.mTotalLength;
                        this.mTotalLength = Math.max(i32, virtualChildAt3.getMeasuredWidth() + i32 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z13 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i33 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i33;
                    iMax9 = Math.max(iMax9, measuredHeight2);
                    if (!z13) {
                        i33 = measuredHeight2;
                    }
                    int iMax10 = Math.max(i28, i33);
                    if (z9) {
                        i5 = -1;
                        boolean z14 = layoutParams3.height == -1;
                        if (!z12 && (baseline = virtualChildAt3.getBaseline()) != i5) {
                            int i34 = ((((layoutParams3.gravity < 0 ? this.mGravity : layoutParams3.gravity) & 112) >> 4) & (-2)) >> 1;
                            iArr2[i34] = Math.max(iArr2[i34], baseline);
                            iArr[i34] = Math.max(iArr[i34], measuredHeight2 - baseline);
                        }
                        i28 = iMax10;
                        z9 = z14;
                        f4 = f4;
                    } else {
                        i5 = -1;
                    }
                    if (!z12) {
                    }
                    i28 = iMax10;
                    z9 = z14;
                    f4 = f4;
                }
                i29++;
                i27 = i4;
                virtualChildCount = virtualChildCount;
            }
            i3 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            iMax = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax9 : Math.max(iMax9, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            i24 = iCombineMeasuredStates3;
            iMax2 = i28;
        } else {
            iMax2 = Math.max(i22, i23);
            if (z11 && mode != 1073741824) {
                for (int i35 = 0; i35 < virtualChildCount; i35++) {
                    View virtualChildAt4 = getVirtualChildAt(i35);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax3, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = virtualChildCount;
            iMax = i26;
        }
        if (z9 || mode2 == 1073741824) {
            iMax2 = iMax;
        }
        setMeasuredDimension(iResolveSizeAndState | (i24 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(iMax2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, i24 << 16));
        if (z10) {
            forceUniformHeight(i3, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, iMakeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a0  */
    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i3 - i;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i9 = this.mGravity;
        int i10 = i9 & 112;
        int i11 = i9 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i10 == 16) {
            paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        } else if (i10 == 80) {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        } else {
            paddingTop = getPaddingTop();
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i12 = layoutParams.gravity;
                if (i12 < 0) {
                    i12 = i11;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i12, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + layoutParams.leftMargin;
                    i6 = layoutParams.rightMargin;
                } else {
                    if (absoluteGravity == 5) {
                        i5 = paddingRight - measuredWidth;
                        i6 = layoutParams.rightMargin;
                    } else {
                        i7 = layoutParams.leftMargin + paddingLeft;
                    }
                    int i13 = i7;
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        paddingTop += this.mDividerHeight;
                    }
                    int i14 = paddingTop + layoutParams.topMargin;
                    setChildFrame(virtualChildAt, i13, i14 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                    int nextLocationOffset = i14 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    paddingTop = nextLocationOffset;
                }
                i7 = i5 - i6;
                int i15 = i7;
                if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    paddingTop += this.mDividerHeight;
                }
                int i16 = paddingTop + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i15, i16 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                int nextLocationOffset2 = i16 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                paddingTop = nextLocationOffset2;
            }
            childrenSkipCount++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:38:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:44:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:50:0x00fd  */
    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        int i7;
        int i8;
        int baseline;
        int i9;
        int i10;
        int i11;
        int i12;
        int measuredHeight;
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i13 = i4 - i2;
        int paddingBottom = i13 - getPaddingBottom();
        int paddingBottom2 = (i13 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i14 = this.mGravity;
        int i15 = i14 & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(8388615 & i14, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 1) {
            paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        } else if (absoluteGravity == 5) {
            paddingLeft = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
        } else {
            paddingLeft = getPaddingLeft();
        }
        if (zIsLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i16 = i5 + (i6 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i16);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i16);
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    if (z) {
                        i7 = childrenSkipCount;
                        i8 = virtualChildCount;
                        baseline = layoutParams.height != -1 ? virtualChildAt.getBaseline() : -1;
                        i9 = layoutParams.gravity;
                        if (i9 < 0) {
                            i9 = i15;
                        }
                        i10 = i9 & 112;
                        if (i10 != 16) {
                            i11 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        } else if (i10 != 48) {
                            i12 = layoutParams.topMargin + paddingTop;
                            if (baseline != -1) {
                                i12 += iArr[1] - baseline;
                            }
                            i11 = i12;
                        } else if (i10 != 80) {
                            i11 = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                            }
                            i11 = measuredHeight;
                        }
                        if (hasDividerBeforeChildAt(i16)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i17 = layoutParams.leftMargin + paddingLeft;
                        setChildFrame(virtualChildAt, i17 + getLocationOffset(virtualChildAt), i11, measuredWidth, measuredHeight2);
                        int nextLocationOffset = i17 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount = i7 + getChildrenSkipCount(virtualChildAt, i16);
                        paddingLeft = nextLocationOffset;
                    } else {
                        i7 = childrenSkipCount;
                        i8 = virtualChildCount;
                    }
                    i9 = layoutParams.gravity;
                    if (i9 < 0) {
                        i9 = i15;
                    }
                    i10 = i9 & 112;
                    if (i10 != 16) {
                        i11 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                    } else if (i10 != 48) {
                        i12 = layoutParams.topMargin + paddingTop;
                        if (baseline != -1) {
                            i12 += iArr[1] - baseline;
                        }
                        i11 = i12;
                    } else if (i10 != 80) {
                        i11 = paddingTop;
                    } else {
                        measuredHeight = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                        if (baseline != -1) {
                            measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                        }
                        i11 = measuredHeight;
                    }
                    if (hasDividerBeforeChildAt(i16)) {
                        paddingLeft += this.mDividerWidth;
                    }
                    int i18 = layoutParams.leftMargin + paddingLeft;
                    setChildFrame(virtualChildAt, i18 + getLocationOffset(virtualChildAt), i11, measuredWidth, measuredHeight2);
                    int nextLocationOffset2 = i18 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount = i7 + getChildrenSkipCount(virtualChildAt, i16);
                    paddingLeft = nextLocationOffset2;
                }
                childrenSkipCount++;
                virtualChildCount = i8;
                i15 = i15;
                paddingTop = paddingTop;
            }
            i8 = virtualChildCount;
            childrenSkipCount++;
            virtualChildCount = i8;
            i15 = i15;
            paddingTop = paddingTop;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & FetchConst.ERROR_REQUEST_ALREADY_EXIST);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = typedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = typedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
