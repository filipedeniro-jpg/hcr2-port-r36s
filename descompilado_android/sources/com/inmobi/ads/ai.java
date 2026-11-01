package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: NativeAdTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public class ai {
    private static final String b = "ai";
    private boolean g;
    private int h;
    static final Map<Context, y> a = new WeakHashMap();
    private static final Map<Context, ce> c = new WeakHashMap();
    private static final Map<View, a> d = new HashMap();
    private static final y.a e = new y.a() { // from class: com.inmobi.ads.ai.1
        @Override // com.inmobi.ads.y.a
        public final void a(View view, Object obj) {
            ((ah) obj).a(view);
        }
    };
    private static final ce.a f = new ce.a() { // from class: com.inmobi.ads.ai.2
        private final Rect a = new Rect();

        @Override // com.inmobi.ads.ce.a
        public final boolean a(@Nullable View view, @Nullable View view2, int i, @NonNull Object obj) {
            av mediaPlayer;
            if (!(obj instanceof ah) || ((ah) obj).l) {
                return false;
            }
            if (((view2 instanceof NativeVideoView) && (mediaPlayer = ((NativeVideoView) view2).getMediaPlayer()) != null && 3 != mediaPlayer.a) || view2 == null || view == null || !view2.isShown() || view.getParent() == null || !view2.getGlobalVisibleRect(this.a)) {
                return false;
            }
            long jHeight = ((long) this.a.height()) * ((long) this.a.width());
            long width = ((long) view.getWidth()) * ((long) view.getHeight());
            return width > 0 && jHeight * 100 >= ((long) i) * width;
        }
    };

    /* JADX INFO: compiled from: NativeAdTracker.java */
    public interface a {
        void a(View view, boolean z);
    }

    ai(int i) {
        this.h = i;
    }

    final void a(@NonNull Context context, @NonNull ah ahVar) {
        y yVar = a.get(context);
        if (yVar != null) {
            yVar.a(ahVar);
            if (!yVar.a.isEmpty()) {
                return;
            }
            a(context);
        }
    }

    final void a(@NonNull Context context) {
        y yVarRemove = a.remove(context);
        if (yVarRemove != null) {
            yVarRemove.c();
        }
        if ((context instanceof Activity) && Build.VERSION.SDK_INT >= 15 && a.isEmpty() && this.g) {
            this.g = false;
        }
    }

    final void a(@NonNull Context context, View view, @NonNull ah ahVar) {
        ce ceVar = c.get(context);
        if (ceVar != null) {
            ceVar.a(ahVar);
            if (!(!ceVar.b.isEmpty())) {
                ce ceVarRemove = c.remove(context);
                if (ceVarRemove != null) {
                    ceVarRemove.e();
                }
                if ((context instanceof Activity) && Build.VERSION.SDK_INT >= 15 && c.isEmpty() && this.g) {
                    this.g = false;
                }
            }
        }
        d.remove(view);
    }

    static void b(Context context) {
        y yVar = a.get(context);
        if (yVar != null) {
            yVar.b();
        }
    }

    static void c(Context context) {
        y yVar = a.get(context);
        if (yVar != null) {
            yVar.a();
        }
    }

    final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull c.k kVar) {
        y yVar = a.get(context);
        if (yVar == null) {
            if (context instanceof Activity) {
                yVar = new y(kVar, new s(f, (Activity) context), e);
                if (Build.VERSION.SDK_INT >= 15 && !this.g) {
                    this.g = true;
                }
            } else {
                yVar = new y(kVar, new bk(f, kVar), e);
            }
            a.put(context, yVar);
        }
        if (this.h == 0) {
            yVar.a(view, ahVar, kVar.f, kVar.g);
        } else {
            yVar.a(view, ahVar, kVar.a, kVar.b);
        }
    }

    final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull a aVar, @NonNull c.k kVar) {
        ce bkVar;
        ce ceVar = c.get(context);
        if (ceVar == null) {
            boolean z = context instanceof Activity;
            if (z) {
                bkVar = new s(f, (Activity) context);
            } else {
                bkVar = new bk(f, kVar);
            }
            bkVar.c = new ce.c() { // from class: com.inmobi.ads.ai.3
                @Override // com.inmobi.ads.ce.c
                public final void a(List<View> list, List<View> list2) {
                    for (View view2 : list) {
                        a aVar2 = (a) ai.d.get(view2);
                        if (aVar2 != null) {
                            aVar2.a(view2, true);
                        }
                    }
                    for (View view3 : list2) {
                        a aVar3 = (a) ai.d.get(view3);
                        if (aVar3 != null) {
                            aVar3.a(view3, false);
                        }
                    }
                }
            };
            c.put(context, bkVar);
            if (z && Build.VERSION.SDK_INT >= 15 && !this.g) {
                this.g = true;
            }
            ceVar = bkVar;
        }
        d.put(view, aVar);
        if (this.h == 0) {
            ceVar.a(view, ahVar, kVar.h);
        } else {
            ceVar.a(view, ahVar, kVar.e);
        }
    }
}
