package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: MraidOrientation.java */
/* JADX INFO: loaded from: classes2.dex */
public class ac {
    public static final String bN = "none";
    public static final String bO = "portrait";
    public static final String bP = "landscape";
    private final int bQ;

    @NonNull
    private final String bR;

    /* JADX INFO: compiled from: MraidOrientation.java */
    /* JADX INFO: loaded from: classes.dex */
    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0036  */
    @Nullable
    public static ac m(@NonNull String str) {
        byte b;
        int iHashCode = str.hashCode();
        int i = 0;
        if (iHashCode != 3387192) {
            if (iHashCode != 729267099) {
                if (iHashCode == 1430647483 && str.equals("landscape")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("portrait")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals("none")) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0) {
            i = -1;
        } else if (b == 1) {
            i = 1;
        } else if (b != 2) {
            return null;
        }
        return new ac(str, i);
    }

    public static ac s() {
        return new ac();
    }

    private ac() {
        this.bQ = -1;
        this.bR = "none";
    }

    private ac(@NonNull String str, int i) {
        this.bR = str;
        this.bQ = i;
    }

    public String toString() {
        return this.bR;
    }

    public int t() {
        return this.bQ;
    }
}
