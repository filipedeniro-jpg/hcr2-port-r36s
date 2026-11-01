package com.applovin.impl.mediation.a.a;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private final b c;

    public c(b bVar) {
        this.c = bVar;
    }

    private SpannedString a(String str, int i) {
        return a(str, i, 16);
    }

    private SpannedString a(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(i), 0, spannableString.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, spannableString.length(), 33);
        return new SpannedString(spannableString);
    }

    private SpannedString e() {
        int i;
        String str;
        if (this.c.b()) {
            if (TextUtils.isEmpty(this.c.e())) {
                str = "SDK Found";
            } else {
                str = "SDK " + this.c.e();
            }
            i = -7829368;
        } else {
            i = SupportMenu.CATEGORY_MASK;
            str = "SDK Missing";
        }
        return a(str, i);
    }

    private SpannedString f() {
        int i;
        String str;
        if (this.c.c()) {
            if (TextUtils.isEmpty(this.c.f())) {
                str = "Adapter Found";
            } else {
                str = "Adapter " + this.c.f();
            }
            i = -7829368;
        } else {
            i = SupportMenu.CATEGORY_MASK;
            str = "Adapter Missing";
        }
        return a(str, i);
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public int b() {
        return (this.c.a() == b.a.MISSING ? a.EnumC0015a.MISSING : a.EnumC0015a.NETWORK).a();
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public SpannedString c() {
        if (this.a != null) {
            return this.a;
        }
        this.a = a(this.c.d(), this.c.a() == b.a.MISSING ? -7829368 : ViewCompat.MEASURED_STATE_MASK, 18);
        return this.a;
    }

    @Override // com.applovin.impl.mediation.a.a.a
    public SpannedString d() {
        if (this.b != null) {
            return this.b;
        }
        if (this.c.a() != b.a.MISSING) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) e());
            spannableStringBuilder.append((CharSequence) a(", ", -7829368));
            spannableStringBuilder.append((CharSequence) f());
            this.b = new SpannedString(spannableStringBuilder);
        } else {
            this.b = new SpannedString("");
        }
        return this.b;
    }

    public String toString() {
        return "MediatedNetworkListItemViewModel{text=" + ((Object) this.a) + ", detailText=" + ((Object) this.b) + ", network=" + this.c + "}";
    }
}
