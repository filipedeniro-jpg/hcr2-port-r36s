package com.applovin.impl.mediation.a.c;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.applovin.impl.mediation.a.a.c;
import com.applovin.impl.mediation.a.a.d;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class b extends BaseAdapter {
    private final Context a;
    private final AtomicBoolean b = new AtomicBoolean();
    private final List<com.applovin.impl.mediation.a.a.a> c = new ArrayList();
    private final com.applovin.impl.mediation.a.a.a d = new d("COMPLETED INTEGRATIONS");
    private final com.applovin.impl.mediation.a.a.a e = new d("INCOMPLETE INTEGRATIONS");
    private final com.applovin.impl.mediation.a.a.a f = new d("MISSING INTEGRATIONS");
    private final com.applovin.impl.mediation.a.a.a g = new d("");

    private static class a {
        TextView a;
        TextView b;

        private a() {
        }

        void a(com.applovin.impl.mediation.a.a.a aVar) {
            this.a.setText(aVar.c());
            if (this.b == null || TextUtils.isEmpty(aVar.d())) {
                return;
            }
            this.b.setText(aVar.d());
        }
    }

    public b(Context context) {
        this.a = context;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.applovin.impl.mediation.a.a.a getItem(int i) {
        return this.c.get(i);
    }

    public void a(List<com.applovin.impl.mediation.a.a.b> list) {
        if (list != null && this.b.compareAndSet(false, true)) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (com.applovin.impl.mediation.a.a.b bVar : list) {
                c cVar = new c(bVar);
                if (bVar.a() == com.applovin.impl.mediation.a.a.b.a.COMPLETE) {
                    arrayList.add(cVar);
                } else if (bVar.a() == com.applovin.impl.mediation.a.a.b.a.INCOMPLETE) {
                    arrayList2.add(cVar);
                } else if (bVar.a() == com.applovin.impl.mediation.a.a.b.a.MISSING) {
                    arrayList3.add(cVar);
                }
            }
            this.c.add(this.d);
            this.c.addAll(arrayList);
            this.c.add(this.e);
            this.c.addAll(arrayList2);
            this.c.add(this.f);
            this.c.addAll(arrayList3);
            this.c.add(this.g);
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.impl.mediation.a.c.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.this.notifyDataSetChanged();
            }
        });
    }

    public boolean a() {
        return this.b.get();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return getItem(i).b();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        com.applovin.impl.mediation.a.a.a item = getItem(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.a.getSystemService("layout_inflater");
            aVar = new a();
            int iB = item.b();
            if (iB == com.applovin.impl.mediation.a.a.a.EnumC0015a.NETWORK.a()) {
                view = layoutInflater.inflate(R.layout.simple_list_item_2, viewGroup, false);
                aVar.a = (TextView) view.findViewById(R.id.text1);
                aVar.b = (TextView) view.findViewById(R.id.text2);
            } else {
                view = layoutInflater.inflate(iB == com.applovin.impl.mediation.a.a.a.EnumC0015a.MISSING.a() ? R.layout.simple_list_item_1 : com.applovin.sdk.R.layout.mediation_debugger_list_section, viewGroup, false);
                aVar.a = (TextView) view.findViewById(R.id.text1);
            }
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(item);
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return com.applovin.impl.mediation.a.a.a.a();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return getItemViewType(i) != com.applovin.impl.mediation.a.a.a.EnumC0015a.SECTION.a();
    }

    public String toString() {
        return "MediationDebuggerListAdapter{networksInitialized=" + this.b.get() + ", listItems=" + this.c + "}";
    }
}
