package com.applovin.impl.mediation.a.c;

import android.R;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class a extends Activity {
    private b a;
    private DataSetObserver b;
    private FrameLayout c;
    private ListView d;
    private com.applovin.impl.adview.a e;

    private void a() {
        b();
        this.e = new com.applovin.impl.adview.a(this, 50, R.attr.progressBarStyleLarge);
        this.e.setColor(-3355444);
        this.c.addView(this.e, new FrameLayout.LayoutParams(-1, -1, 17));
        this.c.bringChildToFront(this.e);
        this.e.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.applovin.impl.adview.a aVar = this.e;
        if (aVar != null) {
            aVar.b();
            this.c.removeView(this.e);
            this.e = null;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("MAX Mediation Debugger");
        setContentView(com.applovin.sdk.R.layout.mediation_debugger_activity);
        this.c = (FrameLayout) findViewById(R.id.content);
        this.d = (ListView) findViewById(com.applovin.sdk.R.id.listView);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.a.unregisterDataSetObserver(this.b);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.d.setAdapter((ListAdapter) this.a);
        if (this.a.a()) {
            return;
        }
        a();
    }

    public void setListAdapter(b bVar) {
        DataSetObserver dataSetObserver;
        b bVar2 = this.a;
        if (bVar2 != null && (dataSetObserver = this.b) != null) {
            bVar2.unregisterDataSetObserver(dataSetObserver);
        }
        this.a = bVar;
        this.b = new DataSetObserver() { // from class: com.applovin.impl.mediation.a.c.a.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                a.this.b();
            }
        };
        this.a.registerDataSetObserver(this.b);
    }
}
