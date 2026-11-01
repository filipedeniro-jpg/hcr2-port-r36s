package com.my.target;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.my.target.common.models.ImageData;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AdChoicesHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class de {

    @Nullable
    private b aW;

    @Nullable
    private a aX;

    @Nullable
    private WeakReference<bs> aZ;

    @Nullable
    private final ae adChoices;
    private int bc;

    public static de a(@Nullable ae aeVar) {
        return new de(aeVar);
    }

    private de(@Nullable ae aeVar) {
        this.adChoices = aeVar;
        if (aeVar != null) {
            this.aX = new a(aeVar.y());
            this.aW = new b(this, (byte) 0);
        }
    }

    public final void a(@NonNull ViewGroup viewGroup, @Nullable bs bsVar, int i) {
        this.bc = i;
        if (this.adChoices == null) {
            if (bsVar != null) {
                bsVar.setImageBitmap(null);
                bsVar.setVisibility(8);
                return;
            }
            return;
        }
        if (bsVar == null) {
            Context context = viewGroup.getContext();
            bs bsVar2 = new bs(context);
            bsVar2.setId(ck.br());
            ck.a(bsVar2, "ad_choices");
            bsVar2.setFixedHeight(ck.a(20, context));
            int iA = ck.a(2, context);
            bsVar2.setPadding(iA, iA, iA, iA);
            bsVar = bsVar2;
        }
        this.aZ = new WeakReference<>(bsVar);
        ae aeVar = this.adChoices;
        bsVar.setVisibility(0);
        bsVar.setOnClickListener(this.aX);
        viewGroup.addOnLayoutChangeListener(this.aW);
        if (bsVar.getParent() == null) {
            try {
                viewGroup.addView(bsVar);
            } catch (Exception e) {
                g.a("Unable to add AdChoices View: " + e.getMessage());
            }
        }
        ImageData icon = aeVar.getIcon();
        Bitmap bitmap = icon.getBitmap();
        if (icon.getBitmap() != null) {
            bsVar.setImageBitmap(bitmap);
        } else {
            cf.a(icon, bsVar);
        }
    }

    public final void e(@NonNull View view) {
        b bVar = this.aW;
        if (bVar != null) {
            view.removeOnLayoutChangeListener(bVar);
        }
        WeakReference<bs> weakReference = this.aZ;
        bs bsVar = weakReference != null ? weakReference.get() : null;
        if (bsVar != null) {
            ae aeVar = this.adChoices;
            if (aeVar != null) {
                cf.b(aeVar.getIcon(), bsVar);
            }
            bsVar.setOnClickListener(null);
            bsVar.setImageBitmap(null);
            bsVar.setVisibility(8);
            this.aZ.clear();
        }
        this.aZ = null;
    }

    /* JADX INFO: compiled from: AdChoicesHelper.java */
    private class b implements View.OnLayoutChangeListener {
        private b() {
        }

        /* synthetic */ b(de deVar, byte b) {
            this();
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int paddingLeft;
            int paddingLeft2;
            int paddingTop;
            int paddingBottom;
            int paddingRight;
            bs bsVar = de.this.aZ != null ? (bs) de.this.aZ.get() : null;
            if (bsVar == null) {
                return;
            }
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            int measuredWidth2 = bsVar.getMeasuredWidth();
            int measuredHeight2 = bsVar.getMeasuredHeight();
            int i9 = de.this.bc;
            if (i9 == 1) {
                paddingLeft = view.getPaddingLeft();
                int paddingTop2 = view.getPaddingTop();
                paddingLeft2 = measuredWidth2 + view.getPaddingLeft();
                paddingTop = measuredHeight2 + view.getPaddingTop();
                paddingBottom = paddingTop2;
            } else {
                if (i9 == 2) {
                    int paddingLeft3 = (measuredWidth - measuredWidth2) - view.getPaddingLeft();
                    paddingBottom = (measuredHeight - view.getPaddingBottom()) - measuredHeight2;
                    paddingRight = measuredWidth - view.getPaddingRight();
                    paddingTop = measuredHeight - view.getPaddingBottom();
                    paddingLeft = paddingLeft3;
                } else if (i9 != 3) {
                    paddingLeft = (measuredWidth - measuredWidth2) - view.getPaddingLeft();
                    int paddingTop3 = view.getPaddingTop();
                    paddingRight = measuredWidth - view.getPaddingRight();
                    paddingTop = measuredHeight2 + view.getPaddingTop();
                    paddingBottom = paddingTop3;
                } else {
                    int paddingLeft4 = view.getPaddingLeft();
                    paddingBottom = (measuredHeight - view.getPaddingBottom()) - measuredHeight2;
                    paddingLeft2 = measuredWidth2 + view.getPaddingLeft();
                    paddingTop = measuredHeight - view.getPaddingBottom();
                    paddingLeft = paddingLeft4;
                }
                bsVar.layout(paddingLeft, paddingBottom, paddingRight, paddingTop);
            }
            paddingRight = paddingLeft2;
            bsVar.layout(paddingLeft, paddingBottom, paddingRight, paddingTop);
        }
    }

    /* JADX INFO: compiled from: AdChoicesHelper.java */
    private static class a implements View.OnClickListener {

        @NonNull
        private final String be;

        a(@NonNull String str) {
            this.be = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.be));
            Context context = view.getContext();
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                g.a("Unable to open AdChoices link: " + e.getMessage());
            }
        }
    }
}
