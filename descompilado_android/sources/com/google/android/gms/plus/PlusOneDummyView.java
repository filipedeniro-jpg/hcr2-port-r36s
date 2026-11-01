package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class PlusOneDummyView extends FrameLayout {

    @Deprecated
    public static final String TAG = "PlusOneDummyView";

    private static class zza implements zzd {
        private Context mContext;

        private zza(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i) {
            return this.mContext.getResources().getDrawable(android.R.drawable.btn_default);
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            return true;
        }
    }

    private static class zzb implements zzd {
        private Context mContext;

        private zzb(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i) {
            String str;
            try {
                Resources resources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                if (i == 0) {
                    str = "ic_plusone_small";
                } else if (i != 1) {
                    str = i != 2 ? "ic_plusone_standard" : "ic_plusone_tall";
                } else {
                    str = "ic_plusone_medium";
                }
                return resources.getDrawable(resources.getIdentifier(str, "drawable", "com.google.android.gms"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            try {
                this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
    }

    private static class zzc implements zzd {
        private Context mContext;

        private zzc(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i) {
            String str;
            if (i == 0) {
                str = "ic_plusone_small_off_client";
            } else if (i != 1) {
                str = i != 2 ? "ic_plusone_standard_off_client" : "ic_plusone_tall_off_client";
            } else {
                str = "ic_plusone_medium_off_client";
            }
            return this.mContext.getResources().getDrawable(this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName()));
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            return (this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName()) == 0) ? false : true;
        }
    }

    private interface zzd {
        Drawable getDrawable(int i);

        boolean isValid();
    }

    @Deprecated
    public PlusOneDummyView(Context context, int i) {
        super(context);
        Button button = new Button(context);
        button.setEnabled(false);
        zzd zzbVar = new zzb(getContext());
        zzbVar = zzbVar.isValid() ? zzbVar : new zzc(getContext());
        button.setBackgroundDrawable((zzbVar.isValid() ? zzbVar : new zza(getContext())).getDrawable(i));
        Point point = new Point();
        int i2 = 20;
        int i3 = 24;
        if (i == 0) {
            i2 = 14;
        } else if (i == 1) {
            i3 = 32;
        } else if (i != 2) {
            i2 = 24;
            i3 = 38;
        } else {
            i3 = 50;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float fApplyDimension = TypedValue.applyDimension(1, i3, displayMetrics);
        float fApplyDimension2 = TypedValue.applyDimension(1, i2, displayMetrics);
        double d = fApplyDimension;
        Double.isNaN(d);
        point.x = (int) (d + 0.5d);
        double d2 = fApplyDimension2;
        Double.isNaN(d2);
        point.y = (int) (d2 + 0.5d);
        addView(button, new FrameLayout.LayoutParams(point.x, point.y, 17));
    }
}
