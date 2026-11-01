package net.nend.android.internal.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Arrays;

/* JADX INFO: compiled from: ResourcesUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static int a(Context context, String str) {
        return Arrays.binarySearch(a(context), h(context, str));
    }

    @VisibleForTesting
    static int[] a(Context context) {
        int[] iArr = {h(context, "NendSpotId"), h(context, "NendApiKey"), h(context, "NendReloadable"), h(context, "NendAdjustSize"), h(context, "NendTitleColor"), h(context, "NendTitleVisible"), h(context, "NendIconCount"), h(context, "NendIconSpaceEnabled"), h(context, "NendOrientation")};
        Arrays.sort(iArr);
        return iArr;
    }

    public static TypedArray a(Context context, AttributeSet attributeSet, int i) {
        return context.getTheme().obtainStyledAttributes(attributeSet, a(context), i, 0);
    }

    private static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static int b(Context context, String str) {
        return a(context, str, "id");
    }

    public static int c(Context context, String str) {
        return a(context, str, TtmlNode.TAG_LAYOUT);
    }

    public static int d(Context context, String str) {
        return a(context, str, "string");
    }

    public static int e(Context context, String str) {
        return a(context, str, "drawable");
    }

    public static int f(Context context, String str) {
        return a(context, str, "dimen");
    }

    private static int h(Context context, String str) {
        return a(context, str, "attr");
    }

    public static TypedArray b(Context context, AttributeSet attributeSet, int i) {
        return context.getTheme().obtainStyledAttributes(attributeSet, b(context), i, 0);
    }

    public static int g(Context context, String str) {
        return Arrays.binarySearch(b(context), h(context, str));
    }

    @VisibleForTesting
    static int[] b(Context context) {
        int[] iArr = {h(context, "boardPreventCornerOverlap")};
        Arrays.sort(iArr);
        return iArr;
    }
}
