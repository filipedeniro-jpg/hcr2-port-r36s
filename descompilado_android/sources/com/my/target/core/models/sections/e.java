package com.my.target.core.models.sections;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import com.my.target.core.models.banners.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAppwallAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends al {
    private boolean ck;

    @Nullable
    private JSONObject g;

    @Nullable
    private String h;

    @Nullable
    private String i;

    @Nullable
    private String j;

    @Nullable
    private String k;

    @Nullable
    private String l;

    @NonNull
    private final String name;

    @Nullable
    private String title;

    @NonNull
    private final ArrayList<j> banners = new ArrayList<>();

    @NonNull
    private final ArrayList<Pair<String, String>> f = new ArrayList<>();

    @NonNull
    public static e a(@NonNull String str) {
        return new e(str);
    }

    private e(@NonNull String str) {
        this.name = str;
    }

    public final void setRawData(@Nullable JSONObject jSONObject) {
        this.g = jSONObject;
    }

    @Nullable
    public final JSONObject getRawData() {
        return this.g;
    }

    public final boolean D() {
        return this.ck;
    }

    public final void d(boolean z) {
        this.ck = z;
    }

    @NonNull
    public final String getName() {
        return this.name;
    }

    public final void a(@NonNull j jVar) {
        this.banners.add(jVar);
    }

    @NonNull
    public final List<j> R() {
        return new ArrayList(this.banners);
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.banners.size();
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void b(@Nullable String str) {
        this.h = str;
    }

    public final void c(@Nullable String str) {
        this.i = str;
    }

    public final void d(@Nullable String str) {
        this.j = str;
    }

    public final void e(@Nullable String str) {
        this.k = str;
    }

    public final void f(@Nullable String str) {
        this.l = str;
    }

    @NonNull
    public final ArrayList<Pair<String, String>> f() {
        return this.f;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String g() {
        return this.h;
    }

    @Nullable
    public final String h() {
        return this.i;
    }

    @Nullable
    public final String i() {
        return this.j;
    }

    @Nullable
    public final String j() {
        return this.k;
    }

    @Nullable
    public final String k() {
        return this.l;
    }

    @Nullable
    public final String g(@NonNull String str) {
        for (Pair<String, String> pair : this.f) {
            if (str.equals(pair.first)) {
                return (String) pair.second;
            }
        }
        return null;
    }
}
