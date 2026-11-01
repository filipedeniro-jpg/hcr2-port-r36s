package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaac extends RelativeLayout {
    private static final float[] zzcvj = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    @Nullable
    private AnimationDrawable zzcvk;

    public zzaac(Context context, zzzz zzzzVar, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzzzVar);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzcvj, null, null));
        shapeDrawable.getPaint().setColor(zzzzVar.getBackgroundColor());
        setLayoutParams(layoutParams);
        com.google.android.gms.ads.internal.zzp.zzka();
        setBackground(shapeDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzzzVar.getText())) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzzzVar.getText());
            textView.setTextColor(zzzzVar.getTextColor());
            textView.setTextSize(zzzzVar.getTextSize());
            zzuo.zzof();
            int iZza = zzawe.zza(context, 4);
            zzuo.zzof();
            textView.setPadding(iZza, 0, zzawe.zza(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzaae> listZzqb = zzzzVar.zzqb();
        if (listZzqb != null && listZzqb.size() > 1) {
            this.zzcvk = new AnimationDrawable();
            Iterator<zzaae> it = listZzqb.iterator();
            while (it.hasNext()) {
                try {
                    this.zzcvk.addFrame((Drawable) ObjectWrapper.unwrap(it.next().zzqf()), zzzzVar.zzqc());
                } catch (Exception e) {
                    zzatm.zzc("Error while getting drawable.", e);
                }
            }
            com.google.android.gms.ads.internal.zzp.zzka();
            imageView.setBackground(this.zzcvk);
        } else if (listZzqb.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(listZzqb.get(0).zzqf()));
            } catch (Exception e2) {
                zzatm.zzc("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzcvk;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
