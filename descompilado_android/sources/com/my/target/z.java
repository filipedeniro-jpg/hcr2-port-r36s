package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: JsStatEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public class z extends q {

    @NonNull
    private final List<String> aQ;

    @Nullable
    private String aR;

    public z(@NonNull List<String> list) {
        super(v.aN);
        this.aQ = list;
    }

    public z(@NonNull List<String> list, @Nullable String str) {
        super(v.aN);
        this.aQ = list;
        this.aR = str;
    }

    @NonNull
    public List<String> k() {
        return this.aQ;
    }

    @Nullable
    public String l() {
        return this.aR;
    }
}
