package com.applovin.impl.mediation.a.a;

import android.text.SpannedString;

/* JADX INFO: loaded from: classes.dex */
public class d extends a {
    public d(String str) {
        this.a = new SpannedString(str);
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public int b() {
        return a.EnumC0015a.SECTION.a();
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public SpannedString c() {
        return this.a;
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public SpannedString d() {
        return null;
    }

    public String toString() {
        return "SectionListItemViewModel{text=" + ((Object) this.a) + "}";
    }
}
