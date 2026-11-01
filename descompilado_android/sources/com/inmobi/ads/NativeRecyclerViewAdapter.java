package com.inmobi.ads;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class NativeRecyclerViewAdapter extends RecyclerView.Adapter<a> implements ax {
    private static final String a = "NativeRecyclerViewAdapter";
    private final ao b;
    private au c;
    private boolean e = false;
    private SparseArray<WeakReference<View>> d = new SparseArray<>();

    NativeRecyclerViewAdapter(@NonNull ao aoVar, @NonNull au auVar) {
        this.b = aoVar;
        this.c = auVar;
    }

    class a extends RecyclerView.ViewHolder {
        private ViewGroup b;

        a(View view) {
            super(view);
            this.b = (ViewGroup) view;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(new FrameLayout(this.c.a()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull a aVar, int i) {
        View viewBuildScrollableView;
        am amVarA = this.b.a(i);
        WeakReference<View> weakReference = this.d.get(i);
        if (weakReference == null || (viewBuildScrollableView = weakReference.get()) == null) {
            viewBuildScrollableView = buildScrollableView(i, aVar.b, amVarA);
        }
        if (viewBuildScrollableView != null) {
            if (i != getItemCount() - 1) {
                aVar.b.setPadding(0, 0, 16, 0);
            }
            aVar.b.addView(viewBuildScrollableView);
            this.d.put(i, new WeakReference<>(viewBuildScrollableView));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull a aVar) {
        aVar.b.removeAllViews();
        super.onViewRecycled(aVar);
    }

    public ViewGroup buildScrollableView(int i, @NonNull ViewGroup viewGroup, @NonNull am amVar) {
        ViewGroup viewGroupA = this.c.a(viewGroup, amVar);
        this.c.b(viewGroupA, amVar);
        viewGroupA.setLayoutParams(NativeViewFactory.a(amVar, viewGroup));
        return viewGroupA;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.b();
    }

    @Override // com.inmobi.ads.ax
    public void destroy() {
        this.e = true;
    }
}
