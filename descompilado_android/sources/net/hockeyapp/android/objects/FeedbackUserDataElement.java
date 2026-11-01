package net.hockeyapp.android.objects;

/* JADX INFO: loaded from: classes2.dex */
public enum FeedbackUserDataElement {
    DONT_SHOW(0),
    OPTIONAL(1),
    REQUIRED(2);

    private final int value;

    FeedbackUserDataElement(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
