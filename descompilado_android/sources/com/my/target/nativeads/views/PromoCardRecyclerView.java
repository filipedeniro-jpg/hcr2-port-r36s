package com.my.target.nativeads.views;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.my.target.by;
import com.my.target.cf;
import com.my.target.common.models.ImageData;
import com.my.target.cw;
import com.my.target.dd;
import com.my.target.g;
import com.my.target.nativeads.banners.NativePromoCard;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PromoCardRecyclerView extends RecyclerView implements dd {

    @NonNull
    private final View.OnClickListener cardClickListener;

    @Nullable
    private List<NativePromoCard> cards;
    private int displayedCardNum;

    @NonNull
    private final cw layoutManager;
    private boolean moving;

    @Nullable
    private dd.a onPromoCardSliderListener;

    @Nullable
    private PromoCardAdapter promoCardAdapter;

    @NonNull
    private final PagerSnapHelper snapHelper;

    public PromoCardRecyclerView(Context context) {
        super(context);
        this.cardClickListener = new View.OnClickListener() { // from class: com.my.target.nativeads.views.PromoCardRecyclerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View viewFindContainingItemView;
                if (PromoCardRecyclerView.this.moving || (viewFindContainingItemView = PromoCardRecyclerView.this.layoutManager.findContainingItemView(view)) == null) {
                    return;
                }
                cw cwVar = PromoCardRecyclerView.this.layoutManager;
                if (cwVar.findViewByPosition(cwVar.findFirstCompletelyVisibleItemPosition()) == viewFindContainingItemView) {
                    if (PromoCardRecyclerView.this.onPromoCardSliderListener == null || PromoCardRecyclerView.this.cards == null) {
                        return;
                    }
                    PromoCardRecyclerView.this.onPromoCardSliderListener.b(viewFindContainingItemView, PromoCardRecyclerView.this.layoutManager.getPosition(viewFindContainingItemView));
                    return;
                }
                int[] iArrCalculateDistanceToFinalSnap = PromoCardRecyclerView.this.snapHelper.calculateDistanceToFinalSnap(PromoCardRecyclerView.this.layoutManager, viewFindContainingItemView);
                if (iArrCalculateDistanceToFinalSnap != null) {
                    PromoCardRecyclerView.this.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], 0);
                }
            }
        };
        this.displayedCardNum = -1;
        this.layoutManager = new cw(getContext());
        setHasFixedSize(true);
        this.snapHelper = new PagerSnapHelper();
        this.snapHelper.attachToRecyclerView(this);
    }

    public PromoCardRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cardClickListener = new View.OnClickListener() { // from class: com.my.target.nativeads.views.PromoCardRecyclerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View viewFindContainingItemView;
                if (PromoCardRecyclerView.this.moving || (viewFindContainingItemView = PromoCardRecyclerView.this.layoutManager.findContainingItemView(view)) == null) {
                    return;
                }
                cw cwVar = PromoCardRecyclerView.this.layoutManager;
                if (cwVar.findViewByPosition(cwVar.findFirstCompletelyVisibleItemPosition()) == viewFindContainingItemView) {
                    if (PromoCardRecyclerView.this.onPromoCardSliderListener == null || PromoCardRecyclerView.this.cards == null) {
                        return;
                    }
                    PromoCardRecyclerView.this.onPromoCardSliderListener.b(viewFindContainingItemView, PromoCardRecyclerView.this.layoutManager.getPosition(viewFindContainingItemView));
                    return;
                }
                int[] iArrCalculateDistanceToFinalSnap = PromoCardRecyclerView.this.snapHelper.calculateDistanceToFinalSnap(PromoCardRecyclerView.this.layoutManager, viewFindContainingItemView);
                if (iArrCalculateDistanceToFinalSnap != null) {
                    PromoCardRecyclerView.this.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], 0);
                }
            }
        };
        this.displayedCardNum = -1;
        this.layoutManager = new cw(getContext());
        setHasFixedSize(true);
        this.snapHelper = new PagerSnapHelper();
        this.snapHelper.attachToRecyclerView(this);
    }

    public PromoCardRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.cardClickListener = new View.OnClickListener() { // from class: com.my.target.nativeads.views.PromoCardRecyclerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View viewFindContainingItemView;
                if (PromoCardRecyclerView.this.moving || (viewFindContainingItemView = PromoCardRecyclerView.this.layoutManager.findContainingItemView(view)) == null) {
                    return;
                }
                cw cwVar = PromoCardRecyclerView.this.layoutManager;
                if (cwVar.findViewByPosition(cwVar.findFirstCompletelyVisibleItemPosition()) == viewFindContainingItemView) {
                    if (PromoCardRecyclerView.this.onPromoCardSliderListener == null || PromoCardRecyclerView.this.cards == null) {
                        return;
                    }
                    PromoCardRecyclerView.this.onPromoCardSliderListener.b(viewFindContainingItemView, PromoCardRecyclerView.this.layoutManager.getPosition(viewFindContainingItemView));
                    return;
                }
                int[] iArrCalculateDistanceToFinalSnap = PromoCardRecyclerView.this.snapHelper.calculateDistanceToFinalSnap(PromoCardRecyclerView.this.layoutManager, viewFindContainingItemView);
                if (iArrCalculateDistanceToFinalSnap != null) {
                    PromoCardRecyclerView.this.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], 0);
                }
            }
        };
        this.displayedCardNum = -1;
        this.layoutManager = new cw(getContext());
        setHasFixedSize(true);
        this.snapHelper = new PagerSnapHelper();
        this.snapHelper.attachToRecyclerView(this);
    }

    @Override // com.my.target.dd
    @NonNull
    public int[] getVisibleCardNumbers() {
        int iFindFirstCompletelyVisibleItemPosition = this.layoutManager.findFirstCompletelyVisibleItemPosition();
        int iFindLastCompletelyVisibleItemPosition = this.layoutManager.findLastCompletelyVisibleItemPosition();
        List<NativePromoCard> list = this.cards;
        if (list == null || iFindFirstCompletelyVisibleItemPosition > iFindLastCompletelyVisibleItemPosition || iFindFirstCompletelyVisibleItemPosition < 0 || iFindLastCompletelyVisibleItemPosition >= list.size()) {
            return new int[0];
        }
        int[] iArr = new int[(iFindLastCompletelyVisibleItemPosition - iFindFirstCompletelyVisibleItemPosition) + 1];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = iFindFirstCompletelyVisibleItemPosition;
            iFindFirstCompletelyVisibleItemPosition++;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter instanceof PromoCardAdapter) {
            setPromoCardAdapter((PromoCardAdapter) adapter);
        } else {
            g.a("You must use setPromoCardAdapter(PromoCardAdapter) method with custom CardRecyclerView");
        }
    }

    public void setPromoCardAdapter(@Nullable PromoCardAdapter promoCardAdapter) {
        if (promoCardAdapter == null) {
            return;
        }
        this.cards = promoCardAdapter.getNativePromoCards();
        this.promoCardAdapter = promoCardAdapter;
        this.promoCardAdapter.setClickListener(this.cardClickListener);
        setLayoutManager(this.layoutManager);
        super.swapAdapter(this.promoCardAdapter, true);
    }

    @Override // com.my.target.dd
    public void setPromoCardSliderListener(@Nullable dd.a aVar) {
        this.onPromoCardSliderListener = aVar;
    }

    @Override // com.my.target.dd
    public void dispose() {
        PromoCardAdapter promoCardAdapter = this.promoCardAdapter;
        if (promoCardAdapter != null) {
            promoCardAdapter.dispose();
        }
    }

    @Override // com.my.target.dd
    @Nullable
    public Parcelable getState() {
        return this.layoutManager.onSaveInstanceState();
    }

    @Override // com.my.target.dd
    public void restoreState(@NonNull Parcelable parcelable) {
        this.layoutManager.onRestoreInstanceState(parcelable);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        this.moving = i != 0;
        if (this.moving) {
            return;
        }
        checkCardChanged();
    }

    private void checkCardChanged() {
        View viewFindViewByPosition;
        int iFindFirstCompletelyVisibleItemPosition = this.layoutManager.findFirstCompletelyVisibleItemPosition();
        if (iFindFirstCompletelyVisibleItemPosition >= 0 && this.displayedCardNum != iFindFirstCompletelyVisibleItemPosition) {
            this.displayedCardNum = iFindFirstCompletelyVisibleItemPosition;
            if (this.onPromoCardSliderListener == null || this.cards == null || (viewFindViewByPosition = this.layoutManager.findViewByPosition(this.displayedCardNum)) == null) {
                return;
            }
            this.onPromoCardSliderListener.b(viewFindViewByPosition, new int[]{this.displayedCardNum});
        }
    }

    public static abstract class PromoCardAdapter extends RecyclerView.Adapter<PromoCardViewHolder> {

        @Nullable
        private View.OnClickListener cardClickListener;

        @NonNull
        private final List<NativePromoCard> nativePromoCards = new ArrayList();

        @NonNull
        public abstract PromoCardView getPromoCardView();

        public void setCards(@NonNull List<NativePromoCard> list) {
            this.nativePromoCards.clear();
            this.nativePromoCards.addAll(list);
            notifyDataSetChanged();
        }

        public void setClickListener(View.OnClickListener onClickListener) {
            this.cardClickListener = onClickListener;
        }

        @NonNull
        public List<NativePromoCard> getNativePromoCards() {
            return this.nativePromoCards;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public PromoCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PromoCardViewHolder(getPromoCardView());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull PromoCardViewHolder promoCardViewHolder, int i) {
            NativePromoCard nativePromoCard;
            if (i < this.nativePromoCards.size() && (nativePromoCard = this.nativePromoCards.get(i)) != null) {
                setBannerToView(nativePromoCard, promoCardViewHolder.getPromoCardView());
            }
            promoCardViewHolder.getPromoCardView().getView().setContentDescription("card_".concat(String.valueOf(i)));
            promoCardViewHolder.getPromoCardView().getView().setOnClickListener(this.cardClickListener);
            promoCardViewHolder.getPromoCardView().getCtaButtonView().setOnClickListener(this.cardClickListener);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(@NonNull PromoCardViewHolder promoCardViewHolder) {
            NativePromoCard nativePromoCard;
            ImageData image;
            int layoutPosition = promoCardViewHolder.getLayoutPosition();
            by byVar = (by) promoCardViewHolder.getPromoCardView().getMediaAdView().getImageView();
            byVar.setImageData(null);
            if (layoutPosition > 0 && layoutPosition < this.nativePromoCards.size() && (nativePromoCard = this.nativePromoCards.get(layoutPosition)) != null && (image = nativePromoCard.getImage()) != null) {
                cf.b(image, byVar);
            }
            promoCardViewHolder.getPromoCardView().getView().setOnClickListener(null);
            promoCardViewHolder.getPromoCardView().getCtaButtonView().setOnClickListener(null);
            super.onViewRecycled(promoCardViewHolder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.nativePromoCards.size();
        }

        public void dispose() {
            this.nativePromoCards.clear();
            notifyDataSetChanged();
            this.cardClickListener = null;
        }

        private void setBannerToView(@NonNull NativePromoCard nativePromoCard, @NonNull PromoCardView promoCardView) {
            if (nativePromoCard.getImage() != null) {
                promoCardView.getMediaAdView().setPlaceHolderDimension(nativePromoCard.getImage().getWidth(), nativePromoCard.getImage().getHeight());
                if (nativePromoCard.getImage().getData() != null) {
                    promoCardView.getMediaAdView().getImageView().setImageBitmap(nativePromoCard.getImage().getData());
                } else {
                    cf.a(nativePromoCard.getImage(), promoCardView.getMediaAdView().getImageView());
                }
            }
            promoCardView.getTitleTextView().setText(nativePromoCard.getTitle());
            promoCardView.getDescriptionTextView().setText(nativePromoCard.getDescription());
            String ctaText = nativePromoCard.getCtaText();
            promoCardView.getCtaButtonView().setText(ctaText);
            promoCardView.getCtaButtonView().setContentDescription(ctaText);
        }
    }

    static class PromoCardViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final PromoCardView promoCardView;

        PromoCardViewHolder(@NonNull PromoCardView promoCardView) {
            super(promoCardView.getView());
            promoCardView.getView().setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            this.promoCardView = promoCardView;
        }

        @NonNull
        PromoCardView getPromoCardView() {
            return this.promoCardView;
        }
    }
}
