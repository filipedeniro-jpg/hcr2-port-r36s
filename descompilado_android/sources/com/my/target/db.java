package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PromoCardImageAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class db extends RecyclerView.Adapter<b> {

    @Nullable
    private View.OnClickListener cardClickListener;

    @NonNull
    private final Context context;

    @NonNull
    private final List<com.my.target.core.models.banners.b> nativeAdCards = new ArrayList();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        com.my.target.core.models.banners.b bVar2 = i < this.nativeAdCards.size() ? this.nativeAdCards.get(i) : null;
        ImageData image = bVar2 != null ? bVar2.getImage() : null;
        if (image != null) {
            bVar.aP.setPlaceholderWidth(image.getWidth());
            bVar.aP.setPlaceholderHeight(image.getHeight());
            Bitmap bitmap = image.getBitmap();
            if (bitmap != null) {
                bVar.aP.setImageBitmap(bitmap);
            } else {
                cf.a(image, bVar.aP);
            }
        }
        bVar.aP.setContentDescription("card_".concat(String.valueOf(i)));
        bVar.aQ.setOnClickListener(this.cardClickListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final /* synthetic */ void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        b bVar = (b) viewHolder;
        int adapterPosition = bVar.getAdapterPosition();
        com.my.target.core.models.banners.b bVar2 = (adapterPosition <= 0 || adapterPosition >= this.nativeAdCards.size()) ? null : this.nativeAdCards.get(adapterPosition);
        bVar.aP.setImageData(null);
        ImageData image = bVar2 != null ? bVar2.getImage() : null;
        if (image != null) {
            cf.b(image, bVar.aP);
        }
        bVar.aQ.setOnClickListener(null);
    }

    db(@NonNull Context context) {
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.nativeAdCards.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        return i == this.nativeAdCards.size() - 1 ? 2 : 0;
    }

    public final void setCards(@NonNull List<com.my.target.core.models.banners.b> list) {
        this.nativeAdCards.addAll(list);
    }

    public final void dispose() {
        this.nativeAdCards.clear();
        notifyDataSetChanged();
        this.cardClickListener = null;
    }

    final void setCardClickListener(@Nullable View.OnClickListener onClickListener) {
        this.cardClickListener = onClickListener;
    }

    /* JADX INFO: compiled from: PromoCardImageAdapter.java */
    public static class a extends FrameLayout {
        public int height;
        public int width;

        public a(@NonNull Context context) {
            super(context);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected final void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            if (size == 0) {
                size = this.width;
            }
            if (size2 == 0) {
                size2 = this.height;
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
        }
    }

    /* JADX INFO: compiled from: PromoCardImageAdapter.java */
    static class b extends RecyclerView.ViewHolder {

        @NonNull
        private final by aP;

        @NonNull
        private final FrameLayout aQ;

        b(@NonNull FrameLayout frameLayout, @NonNull by byVar, @NonNull FrameLayout frameLayout2) {
            super(frameLayout);
            this.aP = byVar;
            this.aQ = frameLayout2;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        a aVar = new a(this.context);
        aVar.setLayoutParams(new RelativeLayout.LayoutParams(-2, -1));
        by byVar = new by(this.context);
        ck.a(byVar, "card_media_view");
        aVar.addView(byVar, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(this.context);
        if (viewGroup.isClickable()) {
            ck.a(frameLayout, 0, 1153821432);
        }
        aVar.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        return new b(aVar, byVar, frameLayout);
    }
}
