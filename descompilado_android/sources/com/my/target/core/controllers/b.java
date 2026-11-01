package com.my.target.core.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.my.target.ak;
import com.my.target.bs;
import com.my.target.by;
import com.my.target.cf;
import com.my.target.ck;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import com.my.target.da;
import com.my.target.dc;
import com.my.target.dd;
import com.my.target.de;
import com.my.target.g;
import com.my.target.nativeads.views.MediaAdView;
import com.my.target.nativeads.views.PromoCardRecyclerView;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: NativeAdViewController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    @Nullable
    private WeakReference<da> A;

    @Nullable
    private HashSet<WeakReference<View>> B;

    @Nullable
    private com.my.target.core.controllers.a C;
    private boolean D;

    @Nullable
    private Parcelable E;
    private boolean F;

    @Nullable
    private WeakReference<MediaAdView> g;
    private final boolean s;
    private final boolean t;

    @NonNull
    private final a u;

    @NonNull
    private final com.my.target.core.models.banners.a v;

    @NonNull
    private final de w;
    private int x = 0;

    @Nullable
    private WeakReference<View> y;

    @Nullable
    private WeakReference<dd> z;

    /* JADX INFO: compiled from: NativeAdViewController.java */
    public interface a extends View.OnClickListener, com.my.target.core.controllers.a.b, dd.a {
    }

    public static b a(@NonNull com.my.target.core.models.banners.a aVar, @NonNull a aVar2) {
        return new b(aVar, aVar2);
    }

    private b(@NonNull com.my.target.core.models.banners.a aVar, @NonNull a aVar2) {
        boolean z = false;
        this.u = aVar2;
        this.v = aVar;
        this.s = aVar.getNativeAdCards().size() > 0;
        ak<VideoData> videoBanner = aVar.getVideoBanner();
        if (videoBanner != null && videoBanner.getMediaData() != null) {
            z = true;
        }
        this.t = z;
        this.w = de.a(aVar.getAdChoices());
    }

    @Nullable
    public final Context getContext() {
        View view;
        WeakReference<View> weakReference = this.y;
        if (weakReference == null || (view = weakReference.get()) == null) {
            return null;
        }
        return view.getContext();
    }

    public final boolean v() {
        return this.F;
    }

    public final int w() {
        return this.x;
    }

    public final void a(boolean z) {
        com.my.target.core.controllers.a aVar = this.C;
        if (aVar != null) {
            if (z) {
                aVar.g();
            } else {
                aVar.h();
            }
        }
    }

    public final int x() {
        WeakReference<View> weakReference = this.y;
        View view = weakReference != null ? weakReference.get() : null;
        if (view == null) {
            return -1;
        }
        if (view.getVisibility() == 0 && view.getParent() != null && view.getAlpha() >= 0.5f) {
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                double dWidth = rect.width() * rect.height();
                double width = view.getWidth() * view.getHeight();
                double viewabilitySquare = this.v.getViewabilitySquare();
                Double.isNaN(width);
                Double.isNaN(viewabilitySquare);
                if (dWidth >= width * viewabilitySquare) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public final void y() {
        com.my.target.core.controllers.a aVar = this.C;
        if (aVar != null) {
            aVar.unregister();
        }
    }

    public final void unregisterView() {
        View view;
        WeakReference<View> weakReference = this.y;
        View view2 = weakReference != null ? weakReference.get() : null;
        y();
        WeakReference<dd> weakReference2 = this.z;
        if (weakReference2 != null) {
            dd ddVar = weakReference2.get();
            if (ddVar != null) {
                ddVar.setPromoCardSliderListener(null);
                this.E = ddVar.getState();
                ddVar.dispose();
            }
            this.z = null;
        }
        WeakReference<MediaAdView> weakReference3 = this.g;
        if (weakReference3 != null) {
            MediaAdView mediaAdView = weakReference3.get();
            if (mediaAdView != null) {
                ImageData image = this.v.getImage();
                by byVar = (by) mediaAdView.getImageView();
                if (image != null) {
                    cf.b(image, byVar);
                }
                mediaAdView.getProgressBarView().setVisibility(8);
                mediaAdView.getPlayButtonView().setVisibility(8);
                byVar.setImageData(null);
                mediaAdView.setPlaceHolderDimension(0, 0);
                mediaAdView.setOnClickListener(null);
                mediaAdView.setBackgroundColor(MediaAdView.COLOR_PLACEHOLDER_GRAY);
                dc dcVarC = c(mediaAdView);
                if (dcVarC != null) {
                    this.E = dcVarC.getState();
                    dcVarC.dispose();
                    dcVarC.setVisibility(8);
                }
            }
            this.g = null;
        }
        z();
        HashSet<WeakReference<View>> hashSet = this.B;
        if (hashSet != null) {
            for (WeakReference<View> weakReference4 : hashSet) {
                if (weakReference4 != null && (view = weakReference4.get()) != null) {
                    view.setOnClickListener(null);
                }
            }
            this.B = null;
        } else if (view2 != null) {
            c(view2);
        }
        if (view2 != null) {
            this.w.e(view2);
        }
        WeakReference<View> weakReference5 = this.y;
        if (weakReference5 != null) {
            weakReference5.clear();
            this.y = null;
        }
    }

    public final void z() {
        WeakReference<da> weakReference = this.A;
        if (weakReference != null) {
            da daVar = weakReference.get();
            if (daVar != null) {
                daVar.setViewabilityListener(null);
            }
            this.A.clear();
            this.A = null;
        }
    }

    @Nullable
    public final int[] A() {
        WeakReference<MediaAdView> weakReference;
        MediaAdView mediaAdView;
        dc dcVarC;
        dd ddVar;
        int i = this.x;
        if (i == 2) {
            WeakReference<dd> weakReference2 = this.z;
            if (weakReference2 == null || (ddVar = weakReference2.get()) == null) {
                return null;
            }
            return ddVar.getVisibleCardNumbers();
        }
        if (i != 3 || (weakReference = this.g) == null || (mediaAdView = weakReference.get()) == null || (dcVarC = c(mediaAdView)) == null) {
            return null;
        }
        return dcVarC.getVisibleCardNumbers();
    }

    public final void a(@NonNull View view, @Nullable List<View> list, @Nullable da.a aVar, int i) {
        if (list != null) {
            this.B = new HashSet<>();
            for (View view2 : list) {
                this.B.add(new WeakReference<>(view2));
                if (view2 instanceof MediaAdView) {
                    this.D = true;
                } else {
                    view2.setOnClickListener(this.u);
                }
            }
        }
        this.y = new WeakReference<>(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            da daVar = null;
            bs bsVar = null;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof bs) {
                    bsVar = (bs) childAt;
                } else if (childAt instanceof da) {
                    daVar = (da) childAt;
                }
            }
            if (daVar == null) {
                daVar = new da(viewGroup.getContext());
                daVar.setId(ck.br());
                ck.a(daVar, "viewability_view");
                try {
                    viewGroup.addView(daVar);
                    daVar.setViewabilityListener(aVar);
                    this.A = new WeakReference<>(daVar);
                } catch (Exception e) {
                    g.a("Unable to add Viewability View: " + e.getMessage());
                    this.F = true;
                }
            } else {
                daVar.setViewabilityListener(aVar);
                this.A = new WeakReference<>(daVar);
            }
            this.w.a(viewGroup, bsVar, i);
        }
        b(view);
    }

    public final boolean B() {
        da daVar;
        WeakReference<da> weakReference = this.A;
        if (weakReference == null || (daVar = weakReference.get()) == null) {
            return false;
        }
        return daVar.V();
    }

    private void b(@NonNull View view) {
        if (view instanceof ViewGroup) {
            a((ViewGroup) view);
        } else if (!(view instanceof bs) && this.B == null) {
            view.setOnClickListener(this.u);
        }
    }

    private void a(@NonNull ViewGroup viewGroup) {
        if (this.s && (viewGroup instanceof PromoCardRecyclerView)) {
            a((dd) viewGroup);
            return;
        }
        if (viewGroup instanceof MediaAdView) {
            b((MediaAdView) viewGroup);
            return;
        }
        if (this.B == null) {
            viewGroup.setOnClickListener(this.u);
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                b(childAt);
            }
        }
    }

    private void a(@NonNull dd ddVar) {
        this.x = 2;
        ddVar.setPromoCardSliderListener(this.u);
        Parcelable parcelable = this.E;
        if (parcelable != null) {
            ddVar.restoreState(parcelable);
        }
        this.z = new WeakReference<>(ddVar);
    }

    private void b(@NonNull MediaAdView mediaAdView) {
        int width;
        int height;
        this.g = new WeakReference<>(mediaAdView);
        ImageData image = this.v.getImage();
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
            mediaAdView.setPlaceHolderDimension(width, height);
        } else {
            width = 0;
            height = 0;
        }
        if (this.s) {
            if (this.x != 2) {
                this.x = 3;
                a(this.v, mediaAdView);
                return;
            }
            return;
        }
        a(mediaAdView, image);
        if (this.t) {
            this.x = 1;
            ak<VideoData> videoBanner = this.v.getVideoBanner();
            VideoData videoData = null;
            if (videoBanner != null) {
                if (height == 0 || width == 0) {
                    mediaAdView.setPlaceHolderDimension(videoBanner.getWidth(), videoBanner.getHeight());
                }
                videoData = (VideoData) videoBanner.getMediaData();
            }
            if (this.C == null && videoData != null) {
                this.x = 1;
                this.C = new com.my.target.core.controllers.a(this.v, videoBanner, videoData);
            }
            if (this.C != null) {
                a(mediaAdView, this.u);
                return;
            }
            return;
        }
        this.x = 0;
        mediaAdView.getImageView().setVisibility(0);
        mediaAdView.getPlayButtonView().setVisibility(8);
        mediaAdView.getProgressBarView().setVisibility(8);
        if (this.B == null || this.D) {
            mediaAdView.setOnClickListener(this.u);
        }
    }

    private void a(@NonNull com.my.target.core.models.banners.a aVar, @NonNull MediaAdView mediaAdView) {
        Context context = mediaAdView.getContext();
        dc dcVarC = c(mediaAdView);
        if (dcVarC == null) {
            dcVarC = new dc(context);
            mediaAdView.addView(dcVarC, new ViewGroup.LayoutParams(-1, -1));
        }
        Parcelable parcelable = this.E;
        if (parcelable != null) {
            dcVarC.restoreState(parcelable);
        }
        dcVarC.setClickable(this.B == null || this.D);
        dcVarC.setupCards(aVar.getNativeAdCards());
        dcVarC.setPromoCardSliderListener(this.u);
        mediaAdView.setBackgroundColor(0);
        dcVarC.setVisibility(0);
    }

    @Nullable
    private static dc c(@NonNull MediaAdView mediaAdView) {
        for (int i = 0; i < mediaAdView.getChildCount(); i++) {
            View childAt = mediaAdView.getChildAt(i);
            if (childAt instanceof dc) {
                return (dc) childAt;
            }
        }
        return null;
    }

    private static void a(@NonNull MediaAdView mediaAdView, @Nullable ImageData imageData) {
        by byVar = (by) mediaAdView.getImageView();
        if (imageData != null) {
            Bitmap bitmap = imageData.getBitmap();
            if (bitmap != null) {
                byVar.setImageBitmap(bitmap);
                return;
            } else {
                byVar.setImageBitmap(null);
                cf.a(imageData, byVar);
                return;
            }
        }
        byVar.setImageBitmap(null);
    }

    private void a(@NonNull MediaAdView mediaAdView, @NonNull com.my.target.core.controllers.a.b bVar) {
        com.my.target.core.controllers.a aVar = this.C;
        if (aVar != null) {
            aVar.a(bVar);
            a(mediaAdView, this.C);
        }
    }

    private void a(@NonNull MediaAdView mediaAdView, @NonNull com.my.target.core.controllers.a aVar) {
        aVar.a((View.OnClickListener) this.u);
        aVar.a(mediaAdView);
    }

    private void c(@NonNull View view) {
        if (view instanceof ViewGroup) {
            if ((view instanceof RecyclerView) || (view instanceof MediaAdView)) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    c(childAt);
                }
            }
            viewGroup.setOnClickListener(null);
            return;
        }
        view.setOnClickListener(null);
    }
}
