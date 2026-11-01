package com.my.target;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.my.target.common.models.ImageData;
import java.util.List;

/* JADX INFO: compiled from: SliderRecyclerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eu extends RecyclerView {

    @NonNull
    private final View.OnClickListener cZ;

    @NonNull
    private final et dO;

    @NonNull
    private final PagerSnapHelper dP;

    @Nullable
    private List<com.my.target.core.models.banners.h> dQ;

    @Nullable
    private c dR;
    private int dS;
    private boolean df;

    /* JADX INFO: compiled from: SliderRecyclerView.java */
    public interface c {
        void a(int i, @NonNull com.my.target.core.models.banners.h hVar);

        void f(@NonNull com.my.target.core.models.banners.h hVar);
    }

    public eu(@NonNull Context context) {
        super(context);
        this.cZ = new a(this, (byte) 0);
        this.dS = -1;
        this.dO = new et(getContext());
        setHasFixedSize(true);
        this.dP = new PagerSnapHelper();
        this.dP.attachToRecyclerView(this);
    }

    public final void a(@NonNull List<com.my.target.core.models.banners.h> list, int i) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        Point point = new Point();
        if (defaultDisplay != null) {
            defaultDisplay.getSize(point);
        }
        int i2 = point.x;
        int i3 = point.y;
        this.dQ = list;
        if (!list.isEmpty()) {
            com.my.target.core.models.banners.h hVar = list.get(0);
            if (i2 > i3) {
                ImageData optimalLandscapeImage = hVar.getOptimalLandscapeImage();
                if (optimalLandscapeImage != null) {
                    this.dO.c(optimalLandscapeImage.getWidth(), optimalLandscapeImage.getHeight());
                }
            } else {
                ImageData optimalPortraitImage = hVar.getOptimalPortraitImage();
                if (optimalPortraitImage != null) {
                    this.dO.c(optimalPortraitImage.getWidth(), optimalPortraitImage.getHeight());
                }
            }
            b bVar = new b(list, i, getResources());
            bVar.setClickListener(this.cZ);
            super.setAdapter(bVar);
            c cVar = this.dR;
            if (cVar != null) {
                cVar.a(0, list.get(0));
            }
        }
        setLayoutManager(this.dO);
    }

    @VisibleForTesting
    final void setBanners(@NonNull List<com.my.target.core.models.banners.h> list) {
        this.dQ = list;
    }

    public final void setSliderCardListener(@Nullable c cVar) {
        this.dR = cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void onScrollStateChanged(int i) {
        int iFindFirstCompletelyVisibleItemPosition;
        List<com.my.target.core.models.banners.h> list;
        super.onScrollStateChanged(i);
        this.df = i != 0;
        if (this.df || (iFindFirstCompletelyVisibleItemPosition = this.dO.findFirstCompletelyVisibleItemPosition()) < 0 || this.dS == iFindFirstCompletelyVisibleItemPosition) {
            return;
        }
        this.dS = iFindFirstCompletelyVisibleItemPosition;
        c cVar = this.dR;
        if (cVar == null || (list = this.dQ) == null) {
            return;
        }
        int i2 = this.dS;
        cVar.a(i2, list.get(i2));
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        List<com.my.target.core.models.banners.h> list = this.dQ;
        if (list != null && !list.isEmpty()) {
            com.my.target.core.models.banners.h hVar = this.dQ.get(0);
            if (configuration.orientation == 2) {
                ImageData optimalLandscapeImage = hVar.getOptimalLandscapeImage();
                if (optimalLandscapeImage != null) {
                    this.dO.c(optimalLandscapeImage.getWidth(), optimalLandscapeImage.getHeight());
                }
            } else {
                ImageData optimalPortraitImage = hVar.getOptimalPortraitImage();
                if (optimalPortraitImage != null) {
                    this.dO.c(optimalPortraitImage.getWidth(), optimalPortraitImage.getHeight());
                }
            }
        }
        super.onConfigurationChanged(configuration);
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        postDelayed(new Runnable() { // from class: com.my.target.eu.1
            @Override // java.lang.Runnable
            public final void run() {
                et etVar = eu.this.dO;
                int i = eu.this.dS;
                et etVar2 = eu.this.dO;
                etVar.scrollToPositionWithOffset(i, (((etVar2.getWidth() + etVar2.getPaddingRight()) + etVar2.getPaddingLeft()) - etVar2.findViewByPosition(etVar2.findFirstVisibleItemPosition()).getWidth()) / 2);
            }
        }, 100L);
    }

    /* JADX INFO: compiled from: SliderRecyclerView.java */
    static class b extends RecyclerView.Adapter<d> {
        private final int backgroundColor;

        @Nullable
        private View.OnClickListener cZ;

        @NonNull
        private final List<com.my.target.core.models.banners.h> dU;

        @NonNull
        private final Resources dV;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            d dVar = (d) viewHolder;
            com.my.target.core.models.banners.h hVar = this.dU.get(i);
            es esVarW = dVar.W();
            ImageData optimalLandscapeImage = hVar.getOptimalLandscapeImage();
            ImageData optimalPortraitImage = hVar.getOptimalPortraitImage();
            ImageData imageData = this.dV.getConfiguration().orientation == 2 ? optimalLandscapeImage : optimalPortraitImage;
            if (imageData != null) {
                optimalLandscapeImage = imageData;
            } else if (optimalLandscapeImage == null) {
                optimalLandscapeImage = optimalPortraitImage;
            }
            if (optimalLandscapeImage != null) {
                esVarW.setImage(optimalLandscapeImage);
            }
            if (!TextUtils.isEmpty(hVar.getAgeRestrictions())) {
                esVarW.setAgeRestrictions(hVar.getAgeRestrictions());
            }
            dVar.W().setOnClickListener(this.cZ);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final /* synthetic */ void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
            d dVar = (d) viewHolder;
            dVar.W().setOnClickListener(null);
            super.onViewRecycled(dVar);
        }

        b(@NonNull List<com.my.target.core.models.banners.h> list, int i, @NonNull Resources resources) {
            this.dU = list;
            this.backgroundColor = i;
            this.dV = resources;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemViewType(int i) {
            if (i == 0) {
                return 1;
            }
            return i == this.dU.size() - 1 ? 2 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return this.dU.size();
        }

        final void setClickListener(@Nullable View.OnClickListener onClickListener) {
            this.cZ = onClickListener;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public final /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            es esVar = new es(viewGroup.getContext(), this.backgroundColor);
            esVar.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new d(esVar);
        }
    }

    /* JADX INFO: compiled from: SliderRecyclerView.java */
    static class d extends RecyclerView.ViewHolder {

        @NonNull
        private final es dW;

        d(@NonNull es esVar) {
            super(esVar);
            this.dW = esVar;
        }

        final es W() {
            return this.dW;
        }
    }

    /* JADX INFO: compiled from: SliderRecyclerView.java */
    private class a implements View.OnClickListener {
        private a() {
        }

        /* synthetic */ a(eu euVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            View viewFindContainingItemView;
            if (eu.this.df || (viewFindContainingItemView = eu.this.dO.findContainingItemView(view)) == null) {
                return;
            }
            et etVar = eu.this.dO;
            if (etVar.findViewByPosition(etVar.findFirstCompletelyVisibleItemPosition()) == viewFindContainingItemView) {
                if (eu.this.dR == null || eu.this.dQ == null) {
                    return;
                }
                eu.this.dR.f((com.my.target.core.models.banners.h) eu.this.dQ.get(eu.this.dO.getPosition(viewFindContainingItemView)));
                return;
            }
            int[] iArrCalculateDistanceToFinalSnap = eu.this.dP.calculateDistanceToFinalSnap(eu.this.dO, viewFindContainingItemView);
            if (iArrCalculateDistanceToFinalSnap != null) {
                eu.this.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], 0);
            }
        }
    }
}
