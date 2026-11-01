package com.my.target;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: compiled from: PromoCardImageRecyclerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dc extends RecyclerView implements dd {

    @NonNull
    private final b aR;

    @NonNull
    private final db aS;

    @Nullable
    private dd.a aT;

    @NonNull
    private final View.OnClickListener cardClickListener;

    @Nullable
    private List<com.my.target.core.models.banners.b> cards;
    private boolean moving;

    public dc(@NonNull Context context) {
        this(context, (byte) 0);
    }

    private dc(@NonNull Context context, byte b2) {
        this(context, (char) 0);
    }

    private dc(@NonNull Context context, char c) {
        super(context, null, 0);
        this.cardClickListener = new a(this, (byte) 0);
        this.aR = new b(context);
        this.aR.setDividerPadding(ck.a(4, context));
        this.aS = new db(getContext());
        setHasFixedSize(true);
    }

    public final void setupCards(@NonNull List<com.my.target.core.models.banners.b> list) {
        this.cards = list;
        this.aS.setCards(list);
        if (isClickable()) {
            this.aS.setCardClickListener(this.cardClickListener);
        }
        setCardLayoutManager(this.aR);
        swapAdapter(this.aS, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        this.moving = i != 0;
        if (this.moving) {
            return;
        }
        checkCardChanged();
    }

    @Override // com.my.target.dd
    @NonNull
    public final int[] getVisibleCardNumbers() {
        int iFindFirstCompletelyVisibleItemPosition = this.aR.findFirstCompletelyVisibleItemPosition();
        int iFindLastCompletelyVisibleItemPosition = this.aR.findLastCompletelyVisibleItemPosition();
        List<com.my.target.core.models.banners.b> list = this.cards;
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

    @Override // com.my.target.dd
    public final Parcelable getState() {
        return this.aR.onSaveInstanceState();
    }

    @Override // com.my.target.dd
    public final void restoreState(@NonNull Parcelable parcelable) {
        this.aR.onRestoreInstanceState(parcelable);
    }

    @Override // com.my.target.dd
    public final void setPromoCardSliderListener(@Nullable dd.a aVar) {
        this.aT = aVar;
    }

    @Override // com.my.target.dd
    public final void dispose() {
        this.aS.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCardChanged() {
        dd.a aVar = this.aT;
        if (aVar != null) {
            aVar.b(this, getVisibleCardNumbers());
        }
    }

    private void setCardLayoutManager(b bVar) {
        bVar.a(new cw.a() { // from class: com.my.target.dc.1
            @Override // com.my.target.cw.a
            public final void onLayoutCompleted() {
                dc.this.checkCardChanged();
            }
        });
        super.setLayoutManager(bVar);
    }

    /* JADX INFO: compiled from: PromoCardImageRecyclerView.java */
    static class b extends LinearLayoutManager {

        @Nullable
        private cw.a aV;
        private int dividerPadding;

        public b(Context context) {
            super(context, 0, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void measureChildWithMargins(View view, int i, int i2) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int width = getWidth();
            if (getHeight() <= 0 || width <= 0) {
                return;
            }
            if (getItemViewType(view) == 1) {
                layoutParams.rightMargin = this.dividerPadding;
            } else if (getItemViewType(view) == 2) {
                layoutParams.leftMargin = this.dividerPadding;
            } else {
                int i3 = this.dividerPadding;
                layoutParams.leftMargin = i3;
                layoutParams.rightMargin = i3;
            }
            super.measureChildWithMargins(view, i, i2);
        }

        public final void a(@Nullable cw.a aVar) {
            this.aV = aVar;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onLayoutCompleted(RecyclerView.State state) {
            super.onLayoutCompleted(state);
            cw.a aVar = this.aV;
            if (aVar != null) {
                aVar.onLayoutCompleted();
            }
        }

        public final void setDividerPadding(int i) {
            this.dividerPadding = i;
        }
    }

    /* JADX INFO: compiled from: PromoCardImageRecyclerView.java */
    private class a implements View.OnClickListener {
        private a() {
        }

        /* synthetic */ a(dc dcVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            View viewFindContainingItemView;
            if (dc.this.moving || !dc.this.isClickable() || (viewFindContainingItemView = dc.this.aR.findContainingItemView(view)) == null || dc.this.aT == null || dc.this.cards == null) {
                return;
            }
            dc.this.aT.b(viewFindContainingItemView, dc.this.aR.getPosition(viewFindContainingItemView));
        }
    }
}
