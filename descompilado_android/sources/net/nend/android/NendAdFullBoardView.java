package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdFullBoardView extends ConstraintLayout {
    private boolean a;
    private NendAdNative b;
    private View c;
    private OnAdClickListener d;
    private final View.OnClickListener e;
    private final View.OnClickListener f;

    public interface FullBoardAdClickListener {
        void onClickAd(NendAdFullBoard nendAdFullBoard);
    }

    public interface OnAdClickListener {
        void onClickAd();
    }

    public NendAdFullBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        this.e = new View.OnClickListener() { // from class: net.nend.android.NendAdFullBoardView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                d.a(view.getContext(), NendAdFullBoardView.this.b.getClickUrl());
                if (NendAdFullBoardView.this.d != null) {
                    NendAdFullBoardView.this.d.onClickAd();
                }
            }
        };
        this.f = new View.OnClickListener() { // from class: net.nend.android.NendAdFullBoardView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NendAdFullBoardView.this.a(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Animation b(float f) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        return translateAnimation;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        this.c = findViewById(n.b(getContext(), "nend_full_board_ad_close_button"));
        this.c.setVisibility(8);
        findViewById(n.b(getContext(), "nend_full_board_ad_card")).setOnClickListener(this.e);
        findViewById(n.b(getContext(), "nend_full_board_ad_information_button")).setOnClickListener(this.f);
        View viewFindViewById = findViewById(n.b(getContext(), "nend_full_board_ad_action_button"));
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: net.nend.android.NendAdFullBoardView.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    NendAdFullBoardView.this.b.onImpression();
                    ViewTreeObserver viewTreeObserver2 = NendAdFullBoardView.this.getViewTreeObserver();
                    if (!viewTreeObserver2.isAlive()) {
                        return true;
                    }
                    viewTreeObserver2.removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
    }

    public void enableCloseButton(View.OnClickListener onClickListener) {
        View view = this.c;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setVisibility(0);
        this.c.setOnClickListener(onClickListener);
    }

    public void setOnAdClickListener(OnAdClickListener onAdClickListener) {
        this.d = onAdClickListener;
    }

    public void setNativeAd(NendAdNative nendAdNative, Bitmap bitmap, Bitmap bitmap2) {
        this.b = nendAdNative;
        ((ImageView) findViewById(n.b(getContext(), "nend_full_board_ad_image"))).setImageBitmap(bitmap);
        ((ImageView) findViewById(n.b(getContext(), "nend_full_board_ad_logo"))).setImageBitmap(bitmap2);
        ((TextView) findViewById(n.b(getContext(), "nend_full_board_ad_promotion"))).setText(nendAdNative.getPromotionName());
        TextView textView = (TextView) findViewById(n.b(getContext(), "nend_full_board_ad_content"));
        if (textView != null) {
            textView.setText(nendAdNative.getContentText());
        }
        TextView textView2 = (TextView) findViewById(n.b(getContext(), "nend_full_board_ad_action_button"));
        if (textView2 != null) {
            textView2.setText(getContext().getString(n.d(getContext(), "nend_full_board_ad_action_button_text")));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        if (view.getAnimation() != null) {
            return;
        }
        int left = view.getLeft();
        if (left != 0) {
            this.a = false;
            Animation animationB = b(0 - left);
            animationB.setAnimationListener(new AnonymousClass4(view, left));
            view.startAnimation(animationB);
            return;
        }
        this.a = true;
        this.b.onClickInformation(getContext());
    }

    /* JADX INFO: renamed from: net.nend.android.NendAdFullBoardView$4, reason: invalid class name */
    class AnonymousClass4 extends a {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(View view, int i) {
            super();
            this.a = view;
            this.b = i;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            View view = this.a;
            view.layout(0, view.getTop(), this.a.getWidth(), this.a.getBottom());
            this.a.setAnimation(null);
            NendAdFullBoardView.this.postDelayed(new Runnable() { // from class: net.nend.android.NendAdFullBoardView.4.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!NendAdFullBoardView.this.a) {
                        Animation animationB = NendAdFullBoardView.b(AnonymousClass4.this.b);
                        animationB.setAnimationListener(new a() { // from class: net.nend.android.NendAdFullBoardView.4.1.1
                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationEnd(Animation animation2) {
                                AnonymousClass4.this.a.layout(AnonymousClass4.this.b, AnonymousClass4.this.a.getTop(), AnonymousClass4.this.a.getWidth() - Math.abs(AnonymousClass4.this.b), AnonymousClass4.this.a.getBottom());
                                AnonymousClass4.this.a.setAnimation(null);
                            }
                        });
                        AnonymousClass4.this.a.startAnimation(animationB);
                        return;
                    }
                    AnonymousClass4.this.a.layout(AnonymousClass4.this.b, AnonymousClass4.this.a.getTop(), AnonymousClass4.this.a.getWidth() - Math.abs(AnonymousClass4.this.b), AnonymousClass4.this.a.getBottom());
                }
            }, 1000L);
        }
    }

    private static abstract class a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private a() {
        }
    }

    public static class Builder {
        private final Context a;
        private final NendAdFullBoard b;
        private View.OnClickListener c;
        private FullBoardAdClickListener d;

        private Builder(Context context, NendAdFullBoard nendAdFullBoard) {
            this.a = context;
            this.b = nendAdFullBoard;
        }

        public static Builder with(Context context, NendAdFullBoard nendAdFullBoard) {
            if (context == null || nendAdFullBoard == null) {
                throw new IllegalArgumentException("A null-argument was passed.");
            }
            return new Builder(context, nendAdFullBoard);
        }

        public Builder enableCloseButton(View.OnClickListener onClickListener) {
            this.c = onClickListener;
            return this;
        }

        public Builder adClickListener(FullBoardAdClickListener fullBoardAdClickListener) {
            this.d = fullBoardAdClickListener;
            return this;
        }

        public View build() {
            NendAdFullBoardView nendAdFullBoardView = (NendAdFullBoardView) LayoutInflater.from(this.a).inflate(n.c(this.a, "activity_nend_ad_full_board"), (ViewGroup) null);
            nendAdFullBoardView.enableCloseButton(this.c);
            nendAdFullBoardView.setNativeAd(this.b.a(), this.b.b(), this.b.c());
            if (this.d != null) {
                nendAdFullBoardView.setOnAdClickListener(new OnAdClickListener() { // from class: net.nend.android.NendAdFullBoardView.Builder.1
                    @Override // net.nend.android.NendAdFullBoardView.OnAdClickListener
                    public void onClickAd() {
                        Builder.this.d.onClickAd(Builder.this.b);
                    }
                });
            }
            return nendAdFullBoardView;
        }
    }
}
