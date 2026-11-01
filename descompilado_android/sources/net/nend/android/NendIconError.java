package net.nend.android;

/* JADX INFO: loaded from: classes2.dex */
public class NendIconError {
    public static final int ERROR_ICONVIEW = 1;
    public static final int ERROR_LOADER = 0;
    private NendAdIconLoader a = null;
    private NendAdIconView b = null;
    private NendAdView.NendError c;
    private int d;

    public NendAdIconLoader getLoader() {
        return this.a;
    }

    void a(NendAdIconLoader nendAdIconLoader) {
        this.a = nendAdIconLoader;
    }

    public NendAdIconView getIconView() {
        return this.b;
    }

    void a(NendAdIconView nendAdIconView) {
        this.b = nendAdIconView;
    }

    public NendAdView.NendError getNendError() {
        return this.c;
    }

    public void setNendError(NendAdView.NendError nendError) {
        this.c = nendError;
    }

    public int getErrorType() {
        return this.d;
    }

    public void setErrorType(int i) {
        this.d = i;
    }
}
