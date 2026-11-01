package com.my.target;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CarouselRecyclerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eo extends RecyclerView {
    private final View.OnClickListener cZ;

    @NonNull
    private final en da;

    @NonNull
    private final View.OnClickListener db;

    @NonNull
    private final LinearSnapHelper dc;

    @Nullable
    private List<com.my.target.core.models.banners.f> dd;

    @Nullable
    private ep.a de;
    private boolean df;
    private boolean dg;

    public eo(Context context) {
        this(context, (byte) 0);
    }

    private eo(Context context, byte b2) {
        this(context, (char) 0);
    }

    private eo(Context context, char c) {
        super(context, null, 0);
        this.cZ = new View.OnClickListener() { // from class: com.my.target.eo.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                View viewFindContainingItemView;
                if (eo.this.df || (viewFindContainingItemView = eo.this.getCardLayoutManager().findContainingItemView(view)) == null) {
                    return;
                }
                en cardLayoutManager = eo.this.getCardLayoutManager();
                int iFindFirstCompletelyVisibleItemPosition = cardLayoutManager.findFirstCompletelyVisibleItemPosition();
                int position = cardLayoutManager.getPosition(viewFindContainingItemView);
                if ((iFindFirstCompletelyVisibleItemPosition <= position && position <= cardLayoutManager.findLastCompletelyVisibleItemPosition()) || eo.this.dg) {
                    if (!view.isClickable() || eo.this.de == null || eo.this.dd == null) {
                        return;
                    }
                    eo.this.de.a((com.my.target.core.models.banners.f) eo.this.dd.get(eo.this.getCardLayoutManager().getPosition(viewFindContainingItemView)));
                    return;
                }
                eo.this.a(viewFindContainingItemView);
            }
        };
        this.db = new View.OnClickListener() { // from class: com.my.target.eo.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewParent parent = view.getParent();
                while (parent != 0 && !(parent instanceof em)) {
                    parent = parent.getParent();
                }
                if (eo.this.de == null || eo.this.dd == null || parent == 0) {
                    return;
                }
                eo.this.de.a((com.my.target.core.models.banners.f) eo.this.dd.get(eo.this.getCardLayoutManager().getPosition((View) parent)));
            }
        };
        setOverScrollMode(2);
        this.da = new en(context);
        this.dc = new LinearSnapHelper();
        this.dc.attachToRecyclerView(this);
    }

    @NonNull
    @VisibleForTesting
    public final LinearSnapHelper getSnapHelper() {
        return this.dc;
    }

    public final void b(List<com.my.target.core.models.banners.f> list) {
        a aVar = new a(list, getContext());
        this.dd = list;
        aVar.cZ = this.cZ;
        aVar.db = this.db;
        setCardLayoutManager(this.da);
        setAdapter(aVar);
    }

    public final void setCarouselListener(@Nullable ep.a aVar) {
        this.de = aVar;
    }

    public final void setSideSlidesMargins(int i) {
        getCardLayoutManager().f(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        this.df = i != 0;
        if (this.df) {
            return;
        }
        U();
    }

    public final void g(boolean z) {
        if (z) {
            this.dc.attachToRecyclerView(this);
        } else {
            this.dc.attachToRecyclerView(null);
        }
    }

    @VisibleForTesting(otherwise = 3)
    public final en getCardLayoutManager() {
        return this.da;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (i3 > i4) {
            this.dg = true;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected final void a(@NonNull View view) {
        int[] iArrCalculateDistanceToFinalSnap = this.dc.calculateDistanceToFinalSnap(getCardLayoutManager(), view);
        if (iArrCalculateDistanceToFinalSnap != null) {
            smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], 0);
        }
    }

    private void setCardLayoutManager(@NonNull en enVar) {
        enVar.a(new en.a() { // from class: com.my.target.eo.3
            @Override // com.my.target.en.a
            public final void onLayoutCompleted() {
                eo.this.U();
            }
        });
        super.setLayoutManager(enVar);
    }

    @NonNull
    private List<com.my.target.core.models.banners.f> getVisibleCards() {
        int iFindFirstCompletelyVisibleItemPosition;
        int iFindLastCompletelyVisibleItemPosition;
        ArrayList arrayList = new ArrayList();
        if (this.dd != null && (iFindFirstCompletelyVisibleItemPosition = getCardLayoutManager().findFirstCompletelyVisibleItemPosition()) <= (iFindLastCompletelyVisibleItemPosition = getCardLayoutManager().findLastCompletelyVisibleItemPosition()) && iFindFirstCompletelyVisibleItemPosition >= 0 && iFindLastCompletelyVisibleItemPosition < this.dd.size()) {
            while (iFindFirstCompletelyVisibleItemPosition <= iFindLastCompletelyVisibleItemPosition) {
                arrayList.add(this.dd.get(iFindFirstCompletelyVisibleItemPosition));
                iFindFirstCompletelyVisibleItemPosition++;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        ep.a aVar = this.de;
        if (aVar != null) {
            aVar.a(getVisibleCards());
        }
    }

    /* JADX INFO: compiled from: CarouselRecyclerView.java */
    private static class a extends RecyclerView.Adapter<b> {
        private final boolean bd;

        @Nullable
        View.OnClickListener cZ;

        @NonNull
        final Context context;
        View.OnClickListener db;

        @NonNull
        final List<com.my.target.core.models.banners.f> interstitialAdCards;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            em emVarV = ((b) viewHolder).V();
            com.my.target.core.models.banners.f fVar = this.interstitialAdCards.get(i);
            ImageData image = fVar.getImage();
            if (image != null) {
                by smartImageView = emVarV.getSmartImageView();
                smartImageView.setPlaceholderWidth(image.getWidth());
                smartImageView.setPlaceholderHeight(image.getHeight());
                cf.a(image, smartImageView);
            }
            emVarV.getTitleTextView().setText(fVar.getTitle());
            emVarV.getDescriptionTextView().setText(fVar.getDescription());
            emVarV.getCtaButtonView().setText(fVar.getCtaText());
            TextView domainTextView = emVarV.getDomainTextView();
            String domain = fVar.getDomain();
            bz ratingView = emVarV.getRatingView();
            if ("web".equals(fVar.getNavigationType())) {
                ratingView.setVisibility(8);
                domainTextView.setVisibility(0);
                domainTextView.setText(domain);
            } else {
                domainTextView.setVisibility(8);
                float rating = fVar.getRating();
                if (rating > 0.0f) {
                    ratingView.setVisibility(0);
                    ratingView.setRating(rating);
                } else {
                    ratingView.setVisibility(8);
                }
            }
            emVarV.a(this.cZ, fVar.getClickArea());
            emVarV.getCtaButtonView().setOnClickListener(this.db);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final /* synthetic */ void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
            em emVarV = ((b) viewHolder).V();
            emVarV.a(null, null);
            emVarV.getCtaButtonView().setOnClickListener(null);
        }

        a(@NonNull List<com.my.target.core.models.banners.f> list, @NonNull Context context) {
            this.interstitialAdCards = list;
            this.context = context;
            this.bd = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemViewType(int i) {
            if (i == 0) {
                return 1;
            }
            return i == getItemCount() - 1 ? 2 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return this.interstitialAdCards.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public final /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new b(new em(this.bd, this.context));
        }
    }

    /* JADX INFO: compiled from: CarouselRecyclerView.java */
    static class b extends RecyclerView.ViewHolder {
        private final em di;

        b(em emVar) {
            super(emVar);
            this.di = emVar;
        }

        final em V() {
            return this.di;
        }
    }
}
