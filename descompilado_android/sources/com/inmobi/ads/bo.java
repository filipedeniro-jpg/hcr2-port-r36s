package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollableDeckFreeContainer.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
class bo extends NativeScrollableContainer {
    private RecyclerView a;
    private boolean b;

    public bo(Context context) {
        super(context, 1);
        this.b = false;
    }

    @Override // com.inmobi.ads.NativeScrollableContainer
    public final void a(@NonNull am amVar, ax axVar, int i, int i2, NativeScrollableContainer.a aVar) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) NativeViewFactory.a(amVar.a(0), this);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.a = new RecyclerView(getContext());
        this.a.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.a.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        addView(this.a);
        this.a.setAdapter((NativeRecyclerViewAdapter) axVar);
    }
}
