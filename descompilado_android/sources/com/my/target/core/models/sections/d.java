package com.my.target.core.models.sections;

import androidx.annotation.NonNull;
import com.my.target.al;
import com.my.target.common.models.ImageData;
import com.my.target.core.models.banners.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: InterstitialSliderAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d extends al {
    private ImageData closeIcon;

    @NonNull
    private final ArrayList<h> s = new ArrayList<>();
    private int t = -1;
    private int u = -14696781;
    private int backgroundColor = -16368537;

    @NonNull
    public static d n() {
        return new d();
    }

    public final int o() {
        return this.u;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final ImageData getCloseIcon() {
        return this.closeIcon;
    }

    public final int s() {
        return this.t;
    }

    public final void a(int i) {
        this.u = i;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final void setCloseIcon(ImageData imageData) {
        this.closeIcon = imageData;
    }

    public final void b(int i) {
        this.t = i;
    }

    private d() {
    }

    public final void c(@NonNull h hVar) {
        this.s.add(hVar);
    }

    public final void d(@NonNull h hVar) {
        this.s.remove(hVar);
    }

    @NonNull
    public final List<h> R() {
        return new ArrayList(this.s);
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.s.size();
    }
}
