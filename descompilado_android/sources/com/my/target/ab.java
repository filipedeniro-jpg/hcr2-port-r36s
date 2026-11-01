package com.my.target;

import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: MraidJsCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public class ab {
    public static final String ar = "expand";
    public static final String bA = "resize";
    public static final String bB = "setResizeProperties";
    public static final String bC = "setExpandProperties";
    public static final String bD = "setOrientationProperties";
    public static final String bE = "playVideo";
    public static final String bF = "storePicture";
    public static final String bG = "createCalendarEvent";
    public static final String bH = "vpaidInit";
    public static final String bI = "vpaidEvent";
    public static final String bJ = "playheadEvent";
    public static final String bK = "";
    public static final String bx = "close";
    public static final String by = "useCustomClose";
    public static final String bz = "open";
    public final boolean bL;

    @NonNull
    private final String bM;

    /* JADX INFO: compiled from: MraidJsCommand.java */
    /* JADX INFO: loaded from: classes.dex */
    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:47:0x00a4  */
    public ab(@NonNull String str, @NonNull String str2) {
        switch (str) {
            case "close":
            case "useCustomClose":
            case "setOrientationProperties":
            case "setResizeProperties":
            case "setExpandProperties":
            case "vpaidInit":
            case "vpaidEvent":
            case "playheadEvent":
                this.bL = false;
                this.bM = str;
                break;
            case "open":
            case "resize":
            case "storePicture":
            case "createCalendarEvent":
                this.bL = true;
                this.bM = str;
                break;
            case "expand":
            case "playVideo":
                this.bL = aa.e.bn.equals(str2);
                this.bM = str;
                break;
            default:
                this.bL = false;
                this.bM = "";
                break;
        }
    }

    public String toString() {
        return this.bM;
    }
}
