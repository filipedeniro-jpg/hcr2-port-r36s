package net.hockeyapp.android.objects;

/* JADX INFO: loaded from: classes2.dex */
public enum CrashManagerUserInput {
    CrashManagerUserInputDontSend(0),
    CrashManagerUserInputSend(1),
    CrashManagerUserInputAlwaysSend(2);

    private final int value;

    CrashManagerUserInput(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
