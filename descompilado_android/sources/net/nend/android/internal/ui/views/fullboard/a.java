package net.nend.android.internal.ui.views.fullboard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(21)
class a implements d {
    @Override // net.nend.android.internal.ui.views.fullboard.d
    public void a() {
    }

    a() {
    }

    @Override // net.nend.android.internal.ui.views.fullboard.d
    public void a(c cVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        e eVar = new e(colorStateList, f);
        cVar.a(eVar);
        View viewB = cVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f2);
        eVar.a(f3);
        int iCeil = (int) Math.ceil(f.b(f3, f, true));
        int iCeil2 = (int) Math.ceil(f.a(f3, f, true));
        cVar.a(iCeil, iCeil2, iCeil, iCeil2);
    }
}
