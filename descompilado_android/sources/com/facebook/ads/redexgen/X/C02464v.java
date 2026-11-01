package com.facebook.ads.redexgen.X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4v, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02464v {
    private static C02464v G;
    private static final Object H = new Object();
    private final Context C;
    private final Handler D;
    private final HashMap<BroadcastReceiver, ArrayList<C02454u>> F = new HashMap<>();
    private final HashMap<String, ArrayList<C02454u>> B = new HashMap<>();
    private final ArrayList<C02444t> E = new ArrayList<>();

    private C02464v(Context context) {
        this.C = context;
        final Looper mainLooper = context.getMainLooper();
        this.D = new Handler(mainLooper) { // from class: com.facebook.ads.redexgen.X.4s
            @Override // android.os.Handler
            public final void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        this.B.D();
                        break;
                    default:
                        super.handleMessage(msg);
                        break;
                }
            }
        };
    }

    public final void A(BroadcastReceiver receiver, IntentFilter filter) {
        synchronized (this.F) {
            C02454u c02454u = new C02454u(filter, receiver);
            ArrayList<C02454u> arrayList = this.F.get(receiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.F.put(receiver, arrayList);
            }
            arrayList.add(c02454u);
            for (int i = 0; i < filter.countActions(); i++) {
                String action = filter.getAction(i);
                ArrayList<C02454u> arrayList2 = this.B.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.B.put(action, arrayList2);
                }
                arrayList2.add(c02454u);
            }
        }
    }

    public final boolean B(Intent intent) {
        String str;
        synchronized (this.F) {
            String scheme = intent.getAction();
            String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.C.getContentResolver());
            Uri data = intent.getData();
            String scheme2 = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme2 + " of intent " + intent);
            }
            ArrayList<C02454u> arrayList = this.B.get(intent.getAction());
            if (arrayList != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList);
                }
                ArrayList arrayList2 = null;
                for (int i = 0; i < arrayList.size(); i++) {
                    C02454u c02454u = arrayList.get(i);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + c02454u.D);
                    }
                    if (c02454u.B) {
                        if (z) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                    } else {
                        int iMatch = c02454u.D.match(scheme, strResolveTypeIfNeeded, scheme2, data, categories, "LocalBroadcastManager");
                        if (iMatch >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                            }
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            arrayList2.add(c02454u);
                            c02454u.B = true;
                        } else if (z) {
                            switch (iMatch) {
                                case -4:
                                    str = "category";
                                    break;
                                case -3:
                                    str = "action";
                                    break;
                                case -2:
                                    str = "data";
                                    break;
                                case -1:
                                    str = "type";
                                    break;
                                default:
                                    str = "unknown reason";
                                    break;
                            }
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + str);
                        }
                    }
                }
                if (arrayList2 != null) {
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        ((C02454u) arrayList2.get(i2)).B = false;
                    }
                    this.E.add(new C02444t(intent, arrayList2));
                    if (!this.D.hasMessages(1)) {
                        this.D.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public static C02464v C(Context context) {
        C02464v c02464v;
        synchronized (H) {
            if (G == null) {
                G = new C02464v(context.getApplicationContext());
            }
            c02464v = G;
        }
        return c02464v;
    }

    public final void C(BroadcastReceiver receiver) {
        synchronized (this.F) {
            ArrayList<C02454u> arrayListRemove = this.F.remove(receiver);
            if (arrayListRemove != null) {
                for (int size = arrayListRemove.size() - 1; size >= 0; size--) {
                    C02454u c02454u = arrayListRemove.get(size);
                    c02454u.C = true;
                    for (int i = 0; i < i; i++) {
                        String action = c02454u.D.getAction(i);
                        ArrayList<C02454u> arrayList = this.B.get(action);
                        if (arrayList != null) {
                            int i2 = arrayList.size();
                            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                                C02454u c02454u2 = arrayList.get(i3);
                                if (c02454u2.E == receiver) {
                                    c02454u2.C = true;
                                    arrayList.remove(i3);
                                }
                            }
                            if (arrayList.size() <= 0) {
                                this.B.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        C02444t[] c02444tArr;
        while (true) {
            synchronized (this.F) {
                int size = this.E.size();
                if (size > 0) {
                    c02444tArr = new C02444t[size];
                    this.E.toArray(c02444tArr);
                    this.E.clear();
                } else {
                    return;
                }
            }
            for (C02444t c02444t : c02444tArr) {
                int size2 = c02444t.C.size();
                for (int i = 0; i < size2; i++) {
                    C02454u c02454u = c02444t.C.get(i);
                    if (!c02454u.C) {
                        c02454u.E.onReceive(this.C, c02444t.B);
                    }
                }
            }
        }
    }
}
