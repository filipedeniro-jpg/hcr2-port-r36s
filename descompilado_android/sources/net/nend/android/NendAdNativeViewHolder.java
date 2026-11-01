package net.nend.android;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeViewHolder {
    public TextView actionTextView;
    public ImageView adImageView;
    public TextView contentTextView;
    public View itemView;
    public ImageView logoImageView;
    public String prText;
    public TextView prTextView;
    public TextView promotionNameTextView;
    public TextView promotionUrlTextView;
    public TextView titleTextView;

    public NendAdNativeViewHolder(View view, NendAdNativeViewBinder nendAdNativeViewBinder) {
        if (view == null || nendAdNativeViewBinder == null) {
            return;
        }
        this.itemView = view;
        try {
            this.adImageView = (ImageView) view.findViewById(nendAdNativeViewBinder.getAdImageId());
            this.logoImageView = (ImageView) view.findViewById(nendAdNativeViewBinder.getLogoImageId());
            this.titleTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getTitleId());
            this.contentTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getContentId());
            this.promotionNameTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPromotionNameId());
            this.promotionUrlTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPromotionUrId());
            this.prTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPrId());
            this.actionTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getActionId());
            this.prText = nendAdNativeViewBinder.getPrText();
        } catch (ClassCastException e) {
            j.a(k.ERR_VALIDATION_BINDER_SETTING, e.getMessage());
            this.adImageView = null;
            this.logoImageView = null;
            this.titleTextView = null;
            this.contentTextView = null;
            this.promotionNameTextView = null;
            this.promotionUrlTextView = null;
            this.prTextView = null;
            this.actionTextView = null;
        }
    }
}
