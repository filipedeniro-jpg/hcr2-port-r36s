package com.my.target;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: AdTitle.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fc extends RelativeLayout {

    @NonNull
    private final TextView n;

    @NonNull
    private final View o;

    @Nullable
    private a p;

    @Nullable
    private String title;

    /* JADX INFO: compiled from: AdTitle.java */
    public interface a {
        void bf();
    }

    public fc(@NonNull Context context) {
        super(context);
        this.n = new TextView(context);
        this.n.setTextColor(-1);
        this.n.setTypeface(null, 1);
        this.n.setTextSize(2, 20.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = (int) TypedValue.applyDimension(1, 8.0f, getContext().getResources().getDisplayMetrics());
        layoutParams.addRule(15);
        layoutParams.addRule(1, 256);
        addView(this.n, layoutParams);
        setBackgroundColor(-7829368);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (context.getResources().getDisplayMetrics().density + 0.5f));
        layoutParams2.addRule(12);
        this.o = new View(context);
        this.o.setBackgroundColor(-10066330);
        addView(this.o, layoutParams2);
        bw bwVar = new bw(context);
        byte[] bArrDecode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAARNJREFUeNrs2osNgCAMhGFK3H/lGhcwKC30jmOD/1PkEc3d28mjt8OHAAQgAAEIQAACEMCO4ScDeBWEXuDJ+0kAXm069EJz3pkBRuKMFaBs/AqA0vHZAOXjMwEg4rMAYOIzAKDiowHg4iMBIOOjAGDjIwCg42cB4ONnACji/wLQxD/jSojfer7/+iB6QjzU6CfHr7oPoAAwvQGkCF9XAWNbBv98A0binBmACmFmFaBAmF0G4REi9gHQCFEbIViEyJ0gJEL0VhgOIeMsAIWQdRiCQcg8DUIgZB+HyyOsuA8ojbDqQqQswsobIQtAggZ4izT2KfAWS/uLzAgC7S8yUR9GaoAmAAEIQAACEIAABLBz3AIMAMj9LYGJKTxCAAAAAElFTkSuQmCC", 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        bwVar.a(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options), false);
        bwVar.setId(256);
        bwVar.setOnClickListener(new View.OnClickListener() { // from class: com.my.target.fc.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (fc.this.p != null) {
                    fc.this.p.bf();
                }
            }
        });
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = (int) TypedValue.applyDimension(1, 5.0f, getContext().getResources().getDisplayMetrics());
        layoutParams3.addRule(15);
        layoutParams3.addRule(9);
        bwVar.setLayoutParams(layoutParams3);
        addView(bwVar);
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
        this.n.setText(str);
    }

    public final void setStripeColor(int i) {
        this.o.setBackgroundColor(i);
    }

    public final void setMainColor(int i) {
        setBackgroundColor(i);
    }

    public final void setTitleColor(int i) {
        this.n.setTextColor(i);
    }

    @Nullable
    public final a getOnCloseClickListener() {
        return this.p;
    }

    public final void setOnCloseClickListener(@Nullable a aVar) {
        this.p = aVar;
    }
}
