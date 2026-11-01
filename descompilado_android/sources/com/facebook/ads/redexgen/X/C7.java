package com.facebook.ads.redexgen.X;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.ads.AudienceNetworkActivity;
import com.my.target.bf;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C7 implements InterfaceC0578Hq {
    private final C0531Fu B;
    private final JV C;
    private final InterfaceC0525Fo D;
    private C7G I;
    private int J;
    private final C0447Co K;
    private final GE H = new CD(this);
    private final GG G = new CC(this);
    private final GK E = new CB(this);
    private final GO F = new CA(this);

    public C7(C0531Fu activityImpl, JV adEventManager, InterfaceC0525Fo listener) {
        this.B = activityImpl;
        this.C = adEventManager;
        this.K = new C0447Co(activityImpl.B());
        this.K.A(new C6T(activityImpl.B()));
        this.K.getEventBus().B(this.H, this.G, this.E, this.F);
        this.D = listener;
        this.K.setIsFullScreen(true);
        this.K.setVolume(1.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        listener.WB(this.K, layoutParams);
        FU fu = new FU(activityImpl.B());
        fu.setOnClickListener(new C9(this, activityImpl));
        listener.WB(fu, fu.getDefaultLayoutParams());
    }

    public final void A(View parentView) {
        this.K.setControlsAnchorView(parentView);
    }

    public final void B(int videoProgressReportIntervalMs) {
        this.K.setVideoProgressReportIntervalMs(videoProgressReportIntervalMs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.View, com.facebook.ads.redexgen.X.8g] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        final String ctaText = intent.getStringExtra("useNativeCtaButton");
        if (ctaText != null && !ctaText.isEmpty()) {
            final AudienceNetworkActivity audienceNetworkActivityB = activityImpl.B();
            ?? r4 = new RelativeLayout(audienceNetworkActivityB, ctaText) { // from class: com.facebook.ads.redexgen.X.8g
                private final Paint B;
                private final RectF C;

                {
                    super(audienceNetworkActivityB);
                    float f = audienceNetworkActivityB.getResources().getDisplayMetrics().density;
                    TextView textView = new TextView(audienceNetworkActivityB);
                    textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView.setTextSize(16.0f);
                    textView.setText(ctaText);
                    textView.setTypeface(Typeface.defaultFromStyle(1));
                    setGravity(17);
                    int i = (int) (6.0f * f);
                    textView.setPadding(i, i, i, i);
                    addView(textView);
                    this.B = new Paint();
                    this.B.setStyle(Paint.Style.FILL);
                    this.B.setColor(-1);
                    this.C = new RectF();
                    J6.O(this, 0);
                }

                @Override // android.view.View
                public final void onDraw(Canvas canvas) {
                    float f = getContext().getResources().getDisplayMetrics().density;
                    this.C.set(0.0f, 0.0f, getWidth(), getHeight());
                    canvas.drawRoundRect(this.C, 10.0f * f, 10.0f * f, this.B);
                    super.onDraw(canvas);
                }
            };
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            float density = 16.0f * J6.B;
            int margin = (int) density;
            layoutParams.setMargins(margin, margin, margin, margin);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            r4.setOnClickListener(new C8(this));
            this.D.WB(r4, layoutParams);
        }
        this.J = intent.getIntExtra("videoSeekTime", 0);
        this.I = new C7G(activityImpl.B(), this.C, this.K, intent.getStringExtra("clientToken"), intent.getBundleExtra("videoLogger"));
        this.K.setVideoMPD(intent.getStringExtra("videoMPD"));
        this.K.setVideoURI(intent.getStringExtra("videoURL"));
        if (this.J > 0) {
            this.K.L(this.J);
        }
        if (intent.getBooleanExtra(bf.a.fy, false)) {
            this.K.O(C7A.USER_STARTED);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        this.D.bB("videoInterstitalEvent", new It() { // from class: com.facebook.ads.redexgen.X.74
        });
        this.K.C();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        this.D.bB("videoInterstitalEvent", new C03016y(this.J, this.K.getCurrentPositionInMillis()));
        this.I.H(this.K.getCurrentPositionInMillis());
        this.K.P();
        this.K.B();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        this.D.bB("videoInterstitalEvent", new It() { // from class: com.facebook.ads.redexgen.X.73
        });
        this.K.O(C7A.USER_STARTED);
    }
}
