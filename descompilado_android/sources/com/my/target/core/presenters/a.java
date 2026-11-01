package com.my.target.core.presenters;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ab;
import com.my.target.ac;
import com.my.target.ad;
import com.my.target.at;
import com.my.target.be;
import com.my.target.br;
import com.my.target.bv;
import com.my.target.bx;
import com.my.target.ck;
import com.my.target.dk;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: MraidPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements br.a, com.my.target.core.presenters.c {

    @Nullable
    com.my.target.core.models.banners.c O;

    @NonNull
    private final aa U;

    @NonNull
    private final dk V;

    @NonNull
    private final WeakReference<Activity> W;

    @NonNull
    final ad X;

    @NonNull
    private final ViewOnLayoutChangeListenerC0096a Y;

    @NonNull
    private final aa.b Z;

    @NonNull
    final bv.a aa;

    @NonNull
    String ab;

    @Nullable
    aa ac;

    @Nullable
    bx ad;

    @Nullable
    private bx ae;

    @Nullable
    com.my.target.core.presenters.c.a af;

    @Nullable
    c ag;
    boolean ah;
    boolean ai;

    @Nullable
    private Uri aj;

    @Nullable
    bv ak;

    @Nullable
    br al;

    @Nullable
    ViewGroup am;

    @Nullable
    private e an;

    @Nullable
    f ao;

    @NonNull
    final Context context;

    /* JADX INFO: compiled from: MraidPresenter.java */
    public interface c {
        void a(float f, float f2, @NonNull com.my.target.core.models.banners.c cVar, @NonNull Context context);

        void a(@NonNull String str, @NonNull com.my.target.core.models.banners.c cVar, @NonNull Context context);

        void bk();

        void onNoAd(@NonNull String str);

        void pause();

        void resume();
    }

    @NonNull
    public static a a(@NonNull ViewGroup viewGroup) {
        return new a(viewGroup);
    }

    @VisibleForTesting
    private a(@NonNull aa aaVar, @NonNull bx bxVar, @NonNull dk dkVar, @NonNull ViewGroup viewGroup) {
        this.aa = new b(this, (byte) 0);
        this.U = aaVar;
        this.ad = bxVar;
        this.V = dkVar;
        this.context = viewGroup.getContext();
        Context context = this.context;
        if (context instanceof Activity) {
            this.W = new WeakReference<>((Activity) context);
            this.am = (ViewGroup) ((Activity) this.context).getWindow().getDecorView().findViewById(R.id.content);
        } else {
            this.W = new WeakReference<>(null);
            View rootView = viewGroup.getRootView();
            if (rootView != null) {
                this.am = (ViewGroup) rootView.findViewById(R.id.content);
                if (this.am == null) {
                    this.am = (ViewGroup) rootView;
                }
            }
        }
        this.ab = aa.f.bp;
        this.X = ad.d(this.context);
        a(bxVar);
        this.Z = new e(aaVar, aa.e.bn);
        aaVar.a(this.Z);
        this.Y = new ViewOnLayoutChangeListenerC0096a(aaVar);
        bxVar.addOnLayoutChangeListener(this.Y);
    }

    private a(@NonNull ViewGroup viewGroup) {
        this(aa.e(aa.e.bn), new bx(viewGroup.getContext()), new dk(viewGroup.getContext()), viewGroup);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        Uri uri;
        this.al = brVar;
        this.ak = new bv(this.context);
        bv bvVar = this.ak;
        this.V.setVisibility(8);
        frameLayout.addView(bvVar, new ViewGroup.LayoutParams(-1, -1));
        if (this.aj != null) {
            this.ac = aa.e(aa.e.bn);
            this.ae = new bx(this.context);
            aa aaVar = this.ac;
            bx bxVar = this.ae;
            this.an = new e(aaVar, aa.e.bn);
            aaVar.a(this.an);
            bvVar.addView(bxVar, new ViewGroup.LayoutParams(-1, -1));
            aaVar.a(bxVar);
            br brVar2 = this.al;
            if (brVar2 != null) {
                com.my.target.core.models.banners.c cVar = this.O;
                if (cVar != null && (uri = this.aj) != null) {
                    com.my.target.h.a(new d(cVar, brVar2, uri, aaVar, this.context));
                } else {
                    this.al.dismiss();
                }
            }
        } else {
            bx bxVar2 = this.ad;
            if (bxVar2 != null && bxVar2.getParent() != null) {
                ((ViewGroup) this.ad.getParent()).removeView(this.ad);
                bvVar.addView(this.ad, new ViewGroup.LayoutParams(-1, -1));
                d(aa.f.bt);
            }
        }
        bvVar.setCloseVisible(!this.ai);
        bvVar.setOnCloseListener(this.aa);
        c cVar2 = this.ag;
        if (cVar2 != null && this.aj == null) {
            cVar2.pause();
        }
        com.my.target.g.a("MRAIDMRAID dialog create");
    }

    @Override // com.my.target.br.a
    public final void i(boolean z) {
        com.my.target.g.a("MRAIDMRAID dialog focus".concat(String.valueOf(z)));
        aa aaVar = this.ac;
        if (aaVar != null) {
            aaVar.a(z);
        } else {
            this.U.a(z);
        }
        bx bxVar = this.ae;
        if (bxVar != null) {
            if (z) {
                bxVar.onResume();
            } else {
                bxVar.j(false);
            }
        }
    }

    @Override // com.my.target.br.a
    public final void aV() {
        this.V.setVisibility(0);
        if (this.aj != null) {
            this.aj = null;
            aa aaVar = this.ac;
            if (aaVar != null) {
                aaVar.a(false);
                this.ac.h(aa.f.br);
                this.ac.detach();
                this.ac = null;
                this.U.a(true);
            }
            bx bxVar = this.ae;
            if (bxVar != null) {
                bxVar.j(true);
                if (this.ae.getParent() != null) {
                    ((ViewGroup) this.ae.getParent()).removeView(this.ae);
                }
                this.ae.destroy();
                this.ae = null;
            }
        } else {
            bx bxVar2 = this.ad;
            if (bxVar2 != null) {
                if (bxVar2.getParent() != null) {
                    ((ViewGroup) this.ad.getParent()).removeView(this.ad);
                }
                a(this.ad);
            }
        }
        bv bvVar = this.ak;
        if (bvVar != null && bvVar.getParent() != null) {
            ((ViewGroup) this.ak.getParent()).removeView(this.ak);
        }
        this.ak = null;
        d(aa.f.bq);
        c cVar = this.ag;
        if (cVar != null) {
            cVar.resume();
        }
        J();
        this.U.a(this.X);
        this.ad.onResume();
    }

    public final void b(@NonNull com.my.target.core.models.sections.b bVar) {
        String mraidSource;
        this.O = bVar.G();
        com.my.target.core.models.banners.c cVar = this.O;
        if (cVar == null || (mraidSource = cVar.getMraidSource()) == null) {
            e("failed to load, failed MRAID initialization");
            return;
        }
        bx bxVar = this.ad;
        if (bxVar != null) {
            this.U.a(bxVar);
            this.U.f(mraidSource);
        } else {
            e("unable to find MRAID webview");
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void start() {
        com.my.target.core.models.banners.c cVar;
        com.my.target.core.presenters.c.a aVar = this.af;
        if (aVar == null || (cVar = this.O) == null) {
            return;
        }
        aVar.a(cVar);
    }

    @Override // com.my.target.core.presenters.c
    public final void stop() {
        bx bxVar;
        if ((this.al == null || this.ac != null) && (bxVar = this.ad) != null) {
            bxVar.j(true);
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void pause() {
        bx bxVar;
        if ((this.al == null || this.ac != null) && (bxVar = this.ad) != null) {
            bxVar.j(false);
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void resume() {
        bx bxVar;
        if ((this.al == null || this.ac != null) && (bxVar = this.ad) != null) {
            bxVar.onResume();
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void destroy() {
        d(aa.f.br);
        this.ag = null;
        this.af = null;
        this.U.detach();
        bv bvVar = this.ak;
        if (bvVar != null) {
            bvVar.removeAllViews();
            this.ak.setOnCloseListener(null);
            ViewParent parent = this.ak.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.ak);
            }
            this.ak = null;
        }
        bx bxVar = this.ad;
        if (bxVar != null) {
            bxVar.j(true);
            if (this.ad.getParent() != null) {
                ((ViewGroup) this.ad.getParent()).removeView(this.ad);
            }
            this.ad.destroy();
            this.ad = null;
        }
        aa aaVar = this.ac;
        if (aaVar != null) {
            aaVar.detach();
            this.ac = null;
        }
        bx bxVar2 = this.ae;
        if (bxVar2 != null) {
            bxVar2.j(true);
            if (this.ae.getParent() != null) {
                ((ViewGroup) this.ae.getParent()).removeView(this.ae);
            }
            this.ae.destroy();
            this.ae = null;
        }
    }

    @NonNull
    public final dk I() {
        return this.V;
    }

    @Override // com.my.target.core.presenters.c
    public final void a(@Nullable com.my.target.core.presenters.c.a aVar) {
        this.af = aVar;
    }

    public final void a(@Nullable c cVar) {
        this.ag = cVar;
    }

    @VisibleForTesting
    final void J() {
        int[] iArr = new int[2];
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        this.X.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        ViewGroup viewGroup = this.am;
        if (viewGroup != null) {
            viewGroup.getLocationOnScreen(iArr);
            this.X.c(iArr[0], iArr[1], iArr[0] + this.am.getMeasuredWidth(), iArr[1] + this.am.getMeasuredHeight());
        }
        if (!this.ab.equals(aa.f.bt) && !this.ab.equals(aa.f.bs)) {
            this.V.getLocationOnScreen(iArr);
            this.X.a(iArr[0], iArr[1], iArr[0] + this.V.getMeasuredWidth(), iArr[1] + this.V.getMeasuredHeight());
        }
        bx bxVar = this.ae;
        if (bxVar != null) {
            bxVar.getLocationOnScreen(iArr);
            this.X.b(iArr[0], iArr[1], iArr[0] + this.ae.getMeasuredWidth(), iArr[1] + this.ae.getMeasuredHeight());
            return;
        }
        bx bxVar2 = this.ad;
        if (bxVar2 != null) {
            bxVar2.getLocationOnScreen(iArr);
            this.X.b(iArr[0], iArr[1], iArr[0] + this.ad.getMeasuredWidth(), iArr[1] + this.ad.getMeasuredHeight());
        }
    }

    final void a(@NonNull bx bxVar) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 1;
        this.V.addView(bxVar);
        bxVar.setLayoutParams(layoutParams);
    }

    final boolean K() {
        bx bxVar;
        Activity activity = this.W.get();
        if (activity == null || (bxVar = this.ad) == null) {
            return false;
        }
        return ck.a(activity, bxVar);
    }

    final void d(@NonNull String str) {
        com.my.target.g.a("MRAID state set to ".concat(String.valueOf(str)));
        this.ab = str;
        this.U.h(str);
        aa aaVar = this.ac;
        if (aaVar != null) {
            aaVar.h(str);
        }
        if (aa.f.br.equals(str)) {
            com.my.target.g.a("MraidPresenter: Mraid on close");
        }
    }

    final boolean a(@Nullable Uri uri) {
        if (this.ad == null) {
            com.my.target.g.a("Cannot expand: webview destroyed");
            return false;
        }
        if (!this.ab.equals(aa.f.bq) && !this.ab.equals(aa.f.bs)) {
            return false;
        }
        this.aj = uri;
        br.a(this, this.context).show();
        return true;
    }

    private void e(@NonNull String str) {
        c cVar = this.ag;
        if (cVar != null) {
            cVar.onNoAd(str);
        }
    }

    /* JADX INFO: compiled from: MraidPresenter.java */
    @VisibleForTesting
    static class d implements Runnable {

        @NonNull
        private com.my.target.core.models.banners.c O;

        @NonNull
        aa ac;

        @NonNull
        private Uri aj;

        @NonNull
        private br al;

        @NonNull
        private Context context;

        d(@NonNull com.my.target.core.models.banners.c cVar, @NonNull br brVar, @NonNull Uri uri, @NonNull aa aaVar, @NonNull Context context) {
            this.O = cVar;
            this.context = context.getApplicationContext();
            this.al = brVar;
            this.aj = uri;
            this.ac = aaVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            at atVarAh = at.ah();
            atVarAh.f(this.aj.toString(), this.context);
            final String strF = be.f(this.O.getMraidJs(), atVarAh.am());
            if (!TextUtils.isEmpty(strF)) {
                com.my.target.h.c(new Runnable() { // from class: com.my.target.core.presenters.a.d.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.this.ac.f(strF);
                    }
                });
            } else {
                this.ac.a("expand", "Failed to handling mraid");
                this.al.dismiss();
            }
        }
    }

    /* JADX INFO: renamed from: com.my.target.core.presenters.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MraidPresenter.java */
    class ViewOnLayoutChangeListenerC0096a implements View.OnLayoutChangeListener {

        @NonNull
        private final aa U;

        ViewOnLayoutChangeListenerC0096a(@NonNull aa aaVar) {
            this.U = aaVar;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            a aVar = a.this;
            aVar.ao = null;
            aVar.J();
            this.U.a(a.this.X);
        }
    }

    /* JADX INFO: compiled from: MraidPresenter.java */
    private class b implements bv.a {
        private b() {
        }

        /* synthetic */ b(a aVar, byte b) {
            this();
        }

        @Override // com.my.target.bv.a
        public final void onClose() {
            if (a.this.al != null) {
                a.this.al.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: MraidPresenter.java */
    private class e implements aa.b {

        @NonNull
        private final aa as;
        private final String at;

        @Override // com.my.target.aa.b
        public final void p() {
        }

        e(@NonNull aa aaVar, String str) {
            this.as = aaVar;
            this.at = str;
        }

        @Override // com.my.target.aa.b
        public final void c(@NonNull aa aaVar) {
            StringBuilder sb = new StringBuilder("onPageLoaded callback from ");
            sb.append(aaVar == a.this.ac ? " second " : " primary ");
            sb.append(Constants.ParametersKeys.WEB_VIEW);
            com.my.target.g.a(sb.toString());
            ArrayList<String> arrayList = new ArrayList<>();
            if (a.this.K()) {
                arrayList.add("'inlineVideo'");
            }
            arrayList.add("'vpaid'");
            aaVar.a(arrayList);
            aaVar.g(this.at);
            aaVar.a(aaVar.isVisible());
            if (a.this.al != null && a.this.al.isShowing()) {
                a.this.d(aa.f.bt);
            } else {
                a.this.d(aa.f.bq);
            }
            aaVar.m();
            if (aaVar == a.this.ac || a.this.ag == null) {
                return;
            }
            a.this.ag.bk();
        }

        @Override // com.my.target.aa.b
        public final void onVisibilityChanged(boolean z) {
            if (!z || a.this.al == null) {
                this.as.a(z);
            }
        }

        @Override // com.my.target.aa.b
        public final boolean a(@NonNull String str, @NonNull JsResult jsResult) {
            com.my.target.g.a("JS Alert: ".concat(String.valueOf(str)));
            jsResult.confirm();
            return true;
        }

        @Override // com.my.target.aa.b
        public final boolean a(@NonNull ConsoleMessage consoleMessage, @NonNull aa aaVar) {
            StringBuilder sb = new StringBuilder("Console message: from ");
            sb.append(aaVar == a.this.ac ? " second " : " primary ");
            sb.append("webview: ");
            sb.append(consoleMessage.message());
            com.my.target.g.a(sb.toString());
            return true;
        }

        @Override // com.my.target.aa.b
        public final void onClose() {
            if (a.this.al != null) {
                a.this.al.dismiss();
            }
        }

        @Override // com.my.target.aa.b
        public final void b(boolean z) {
            a aVar = a.this;
            aVar.ai = z;
            if (!aVar.ab.equals(aa.f.bt) || a.this.ak == null) {
                return;
            }
            a.this.ak.setCloseVisible(!z);
            if (z) {
                return;
            }
            a.this.ak.setOnCloseListener(a.this.aa);
        }

        @Override // com.my.target.aa.b
        public final boolean a(boolean z, ac acVar) {
            com.my.target.g.a("Orientation properties isn't supported in standard banners");
            return false;
        }

        @Override // com.my.target.aa.b
        public final void b(@NonNull Uri uri) {
            if (a.this.af == null || a.this.O == null) {
                return;
            }
            a.this.af.a(a.this.O, uri.toString());
        }

        @Override // com.my.target.aa.b
        public final void q() {
            a.this.ah = true;
        }

        @Override // com.my.target.aa.b
        public final boolean l(@NonNull String str) {
            if (!a.this.ah) {
                this.as.a(ab.bI, "Calling VPAID command before VPAID init");
                return false;
            }
            if (a.this.ag == null || a.this.O == null) {
                return true;
            }
            a.this.ag.a(str, a.this.O, a.this.context);
            return true;
        }

        @Override // com.my.target.aa.b
        public final boolean a(float f, float f2) {
            if (!a.this.ah) {
                this.as.a(ab.bJ, "Calling VPAID command before VPAID init");
                return false;
            }
            if (f < 0.0f || f2 < 0.0f || a.this.ag == null || a.this.O == null) {
                return true;
            }
            a.this.ag.a(f, f2, a.this.O, a.this.context);
            return true;
        }

        @Override // com.my.target.aa.b
        public final boolean c(@Nullable Uri uri) {
            return a.this.a(uri);
        }

        @Override // com.my.target.aa.b
        public final boolean a(int i, int i2, int i3, int i4, boolean z, int i5) {
            a.this.ao = new f();
            if (a.this.am == null) {
                com.my.target.g.a("Unable to set resize properties: container view for resize is not defined");
                this.as.a(ab.bB, "container view for resize is not defined");
                a.this.ao = null;
                return false;
            }
            if (i < 50 || i2 < 50) {
                com.my.target.g.a("Unable to set resize properties: properties cannot be less than closeable container");
                this.as.a(ab.bB, "properties cannot be less than closeable container");
                a.this.ao = null;
                return false;
            }
            ck ckVarX = ck.x(a.this.context);
            a.this.ao.o(z);
            a.this.ao.a(ckVarX.l(i), ckVarX.l(i2), ckVarX.l(i3), ckVarX.l(i4), i5);
            if (z) {
                return true;
            }
            Rect rect = new Rect();
            a.this.am.getGlobalVisibleRect(rect);
            if (a.this.ao.a(rect)) {
                return true;
            }
            com.my.target.g.a("Unable to set resize properties: allowOffscreen is false, maxSize is (" + rect.width() + "," + rect.height() + ") resize properties: (" + a.this.ao.L() + "," + a.this.ao.M() + ")");
            this.as.a(ab.bB, "resize properties with allowOffscreen false out of viewport");
            a.this.ao = null;
            return false;
        }

        @Override // com.my.target.aa.b
        public final boolean r() {
            if (!a.this.ab.equals(aa.f.bq)) {
                com.my.target.g.a("Unable to resize: wrong state for resize: " + a.this.ab);
                this.as.a(ab.bA, "wrong state for resize " + a.this.ab);
                return false;
            }
            if (a.this.ao == null) {
                com.my.target.g.a("Unable to resize: resize properties not set");
                this.as.a(ab.bA, "resize properties not set");
                return false;
            }
            if (a.this.am == null || a.this.ad == null) {
                com.my.target.g.a("Unable to resize: views not initialized");
                this.as.a(ab.bA, "views not initialized");
                return false;
            }
            if (!a.this.ao.a(a.this.am, a.this.ad)) {
                com.my.target.g.a("Unable to resize: views not visible");
                this.as.a(ab.bA, "views not visible");
                return false;
            }
            a aVar = a.this;
            aVar.ak = new bv(aVar.context);
            a.this.ao.a(a.this.ak);
            if (!a.this.ao.b(a.this.ak)) {
                com.my.target.g.a("Unable to resize: close button is out of visible range");
                this.as.a(ab.bA, "close button is out of visible range");
                a.this.ak = null;
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) a.this.ad.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(a.this.ad);
            }
            a.this.ak.addView(a.this.ad, new FrameLayout.LayoutParams(-1, -1));
            a.this.ak.setOnCloseListener(new bv.a() { // from class: com.my.target.core.presenters.a.e.1
                @Override // com.my.target.bv.a
                public final void onClose() {
                    e eVar = e.this;
                    if (a.this.ak == null || a.this.ad == null) {
                        return;
                    }
                    if (a.this.ak.getParent() != null) {
                        ((ViewGroup) a.this.ak.getParent()).removeView(a.this.ak);
                        a.this.ak.removeAllViews();
                        a.this.a(a.this.ad);
                        a.this.d(aa.f.bq);
                        a.this.ak.setOnCloseListener(null);
                        a.this.ak = null;
                    }
                    if (a.this.ag != null) {
                        a.this.ag.resume();
                    }
                }
            });
            a.this.am.addView(a.this.ak);
            a.this.d(aa.f.bs);
            if (a.this.ag == null) {
                return true;
            }
            a.this.ag.pause();
            return true;
        }
    }

    /* JADX INFO: compiled from: MraidPresenter.java */
    public static class f {
        private int aA;

        @Nullable
        private Rect aB;

        @Nullable
        private Rect aC;
        private int aD;
        private int aE;
        private boolean av = true;
        private int aw;
        private int ax;
        private int ay;
        private int az;

        public final int L() {
            return this.ay;
        }

        final void o(boolean z) {
            this.av = z;
        }

        final void a(int i, int i2, int i3, int i4, int i5) {
            this.ay = i;
            this.az = i2;
            this.aw = i3;
            this.ax = i4;
            this.aA = i5;
        }

        final boolean a(@NonNull Rect rect) {
            return this.ay <= rect.width() && this.az <= rect.height();
        }

        final void a(@NonNull bv bvVar) {
            Rect rect = this.aC;
            if (rect == null || this.aB == null) {
                com.my.target.g.a("Setup views before resizing");
                return;
            }
            this.aD = (rect.top - this.aB.top) + this.ax;
            this.aE = (this.aC.left - this.aB.left) + this.aw;
            if (!this.av) {
                if (this.aD + this.az > this.aB.height()) {
                    com.my.target.g.a("Try to reposition creative vertically because of resize allowOffscreen:false and out of max size properties");
                    this.aD = this.aB.height() - this.az;
                }
                if (this.aE + this.ay > this.aB.width()) {
                    com.my.target.g.a("Try to reposition creative horizontally because of resize allowOffscreen:false and out of max size properties");
                    this.aE = this.aB.width() - this.ay;
                }
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.ay, this.az);
            layoutParams.topMargin = this.aD;
            layoutParams.leftMargin = this.aE;
            bvVar.setLayoutParams(layoutParams);
            bvVar.setCloseGravity(this.aA);
            bvVar.setCloseVisible(false);
        }

        public final int M() {
            return this.az;
        }

        final boolean a(@NonNull ViewGroup viewGroup, @NonNull bx bxVar) {
            this.aB = new Rect();
            this.aC = new Rect();
            return viewGroup.getGlobalVisibleRect(this.aB) && bxVar.getGlobalVisibleRect(this.aC);
        }

        final boolean b(@NonNull bv bvVar) {
            Rect rect = this.aB;
            if (rect == null) {
                return false;
            }
            Rect rect2 = new Rect(this.aE, this.aD, rect.right, this.aB.bottom);
            int i = this.aE;
            int i2 = this.aD;
            Rect rect3 = new Rect(i, i2, this.ay + i, this.az + i2);
            Rect rect4 = new Rect();
            bvVar.a(this.aA, rect3, rect4);
            return rect2.contains(rect4);
        }
    }
}
