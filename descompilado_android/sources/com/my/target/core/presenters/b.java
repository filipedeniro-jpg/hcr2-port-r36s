package com.my.target.core.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.Cdo;
import com.my.target.ai;
import com.my.target.by;
import com.my.target.ck;
import com.my.target.common.models.ImageData;
import com.my.target.dk;
import com.my.target.dl;
import com.my.target.dn;

/* JADX INFO: compiled from: NativeViewPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements c {

    @Nullable
    private com.my.target.core.models.banners.c O;

    @NonNull
    private final dk V;

    @NonNull
    private final ck aF;

    @NonNull
    private final dn aG;

    @Nullable
    private c.a af;

    @NonNull
    public static b a(@NonNull String str, @NonNull Context context) {
        return new b(str, context);
    }

    @VisibleForTesting
    private b(@NonNull dk dkVar, @NonNull ck ckVar, @NonNull dn dnVar) {
        this.V = dkVar;
        this.aF = ckVar;
        this.aG = dnVar;
    }

    private b(@NonNull String str, @NonNull Context context) {
        this(new dk(context), ck.x(context), str.equals(com.my.target.b.a.b) ? new Cdo(context) : new dl(str, context));
    }

    public final void b(@NonNull com.my.target.core.models.sections.b bVar) {
        this.O = bVar.G();
        com.my.target.core.models.banners.c cVar = this.O;
        if (cVar == null) {
            return;
        }
        com.my.target.core.models.banners.d viewSettings = cVar.getViewSettings();
        dn dnVar = this.aG;
        dnVar.getTitleTextView().setTextColor(viewSettings.getTitleColor());
        if (viewSettings.m()) {
            dnVar.getTitleTextView().setTypeface(null, 1);
        } else {
            dnVar.getTitleTextView().setTypeface(null, 0);
        }
        dnVar.getDomainTextView().setTextColor(viewSettings.A());
        if (viewSettings.o()) {
            dnVar.getDomainTextView().setTypeface(null, 1);
        } else {
            dnVar.getDomainTextView().setTypeface(null, 0);
        }
        dnVar.getRatingTextView().setTextColor(viewSettings.B());
        if (viewSettings.s()) {
            dnVar.getRatingTextView().setTypeface(null, 1);
        } else {
            dnVar.getRatingTextView().setTypeface(null, 0);
        }
        dnVar.b(viewSettings.getBackgroundColor(), viewSettings.v());
        dnVar.getAgeRestrictionsView().setTextColor(viewSettings.x());
        dnVar.getAgeRestrictionsView().c(1, viewSettings.y());
        dnVar.getAgeRestrictionsView().setBackgroundColor(viewSettings.w());
        ck.a(dnVar.getCtaButton(), viewSettings.getCtaButtonColor(), viewSettings.getCtaButtonTouchColor());
        dnVar.getCtaButton().setTextColor(viewSettings.getCtaButtonTextColor());
        if (viewSettings.u()) {
            dnVar.getCtaButton().setTypeface(null, 1);
        } else {
            dnVar.getCtaButton().setTypeface(null, 0);
        }
        TextView descriptionTextView = dnVar.getDescriptionTextView();
        if (descriptionTextView != null) {
            descriptionTextView.setTextColor(viewSettings.z());
            if (viewSettings.n()) {
                descriptionTextView.setTypeface(null, 1);
            } else {
                descriptionTextView.setTypeface(null, 0);
            }
        }
        TextView disclaimerTextView = dnVar.getDisclaimerTextView();
        if (disclaimerTextView != null) {
            disclaimerTextView.setTextColor(viewSettings.C());
            if (viewSettings.t()) {
                disclaimerTextView.setTypeface(null, 1);
            } else {
                disclaimerTextView.setTypeface(null, 0);
            }
        }
        final com.my.target.core.models.banners.c cVar2 = this.O;
        dn dnVar2 = this.aG;
        dnVar2.getTitleTextView().setText(cVar2.getTitle());
        TextView disclaimerTextView2 = dnVar2.getDisclaimerTextView();
        if (disclaimerTextView2 != null) {
            disclaimerTextView2.setText(cVar2.getDisclaimer());
        }
        TextView descriptionTextView2 = dnVar2.getDescriptionTextView();
        if (descriptionTextView2 != null) {
            descriptionTextView2.setText(cVar2.getDescription());
        }
        dnVar2.getCtaButton().setText(cVar2.getCtaText());
        if (TextUtils.isEmpty(cVar2.getAgeRestrictions())) {
            dnVar2.getAgeRestrictionsView().setVisibility(8);
        } else {
            dnVar2.getAgeRestrictionsView().setVisibility(0);
            dnVar2.getAgeRestrictionsView().setText(cVar2.getAgeRestrictions());
        }
        dnVar2.getDomainTextView().setText(cVar2.getDomain());
        ImageData image = cVar2.getImage();
        if (ai.a.cW.equals(cVar2.getType())) {
            by bannerImage = dnVar2.getBannerImage();
            if (image != null && bannerImage != null) {
                bannerImage.setImageData(image);
            }
        } else {
            ImageData icon = cVar2.getIcon();
            if (icon != null) {
                dnVar2.getIconImage().setImageData(icon);
            }
            by mainImage = dnVar2.getMainImage();
            if (image != null && mainImage != null) {
                mainImage.setImageData(image);
            }
        }
        dnVar2.getRatingTextView().setText(String.valueOf(cVar2.getVotes()));
        dnVar2.getStarsRatingView().setRating(cVar2.getRating());
        dnVar2.b(cVar2);
        dnVar2.a(cVar2.getClickArea(), cVar2.getType().equals(ai.a.cW), new View.OnClickListener() { // from class: com.my.target.core.presenters.b.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (b.this.af != null) {
                    b.this.af.a(cVar2, null);
                }
            }
        });
        this.aG.start();
        this.V.addView(this.aG.N());
    }

    @Override // com.my.target.core.presenters.c
    public final void start() {
        com.my.target.core.models.banners.c cVar;
        this.aG.N().setVisibility(0);
        c.a aVar = this.af;
        if (aVar == null || (cVar = this.O) == null) {
            return;
        }
        aVar.a(cVar);
    }

    @Override // com.my.target.core.presenters.c
    public final void stop() {
        this.aG.N().setVisibility(8);
        com.my.target.g.a("Stop native banner");
        this.aG.stop();
    }

    @Override // com.my.target.core.presenters.c
    public final void pause() {
        com.my.target.g.a("Pause native banner");
        this.aG.stop();
    }

    @Override // com.my.target.core.presenters.c
    public final void resume() {
        com.my.target.g.a("Resume native banner");
        this.aG.start();
    }

    @Override // com.my.target.core.presenters.c
    public final void destroy() {
        stop();
        this.af = null;
    }

    @NonNull
    public final dk I() {
        return this.V;
    }

    @Override // com.my.target.core.presenters.c
    public final void a(@Nullable c.a aVar) {
        this.af = aVar;
    }
}
