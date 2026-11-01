package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdIconLayout extends LinearLayout implements NendAdIconLoader.OnClickListener, NendAdIconLoader.OnFailedListener, NendAdIconLoader.OnInformationClickListener, NendAdIconLoader.OnReceiveListener {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int a;
    private String b;
    private int c;
    private boolean d;
    private int e;
    private boolean f;
    private NendAdIconLoader g;
    private NendAdIconLoader.OnClickListener h;
    private NendAdIconLoader.OnInformationClickListener i;
    private NendAdIconLoader.OnFailedListener j;
    private NendAdIconLoader.OnReceiveListener k;

    public NendAdIconLayout(Context context, int i, String str, int i2) {
        super(context);
        this.c = 0;
        this.d = true;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = true;
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public NendAdIconLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdIconLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.c = 0;
        this.d = true;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = true;
        if (attributeSet == null) {
            throw new NullPointerException(k.ERR_INVALID_ATTRIBUTE_SET.b());
        }
        TypedArray typedArray = null;
        try {
            TypedArray typedArrayA = n.a(context, attributeSet, i);
            this.c = typedArrayA.getInt(n.a(context, "NendIconCount"), 0);
            if (this.c <= 0) {
                throw new NullPointerException(k.ERR_INVALID_ICON_COUNT.b());
            }
            setOrientation(typedArrayA.getInt(n.a(context, "NendOrientation"), 0));
            this.e = typedArrayA.getColor(n.a(context, "NendTitleColor"), ViewCompat.MEASURED_STATE_MASK);
            this.d = typedArrayA.getBoolean(n.a(context, "NendTitleVisible"), true);
            this.f = typedArrayA.getBoolean(n.a(context, "NendIconSpaceEnabled"), true);
            this.a = typedArrayA.getInt(n.a(context, "NendSpotId"), 0);
            this.b = typedArrayA.getString(n.a(context, "NendApiKey"));
            if (typedArrayA != null) {
                typedArrayA.recycle();
            }
            loadAd();
        } catch (Throwable th) {
            if (0 != 0) {
                typedArray.recycle();
            }
            throw th;
        }
    }

    public void loadAd() {
        this.g = new NendAdIconLoader(getContext(), this.a, this.b);
        for (int i = 0; i < this.c && i <= 7; i++) {
            NendAdIconView nendAdIconView = new NendAdIconView(getContext());
            nendAdIconView.setTitleColor(this.e);
            nendAdIconView.setTitleVisible(this.d);
            nendAdIconView.setIconSpaceEnabled(this.f);
            this.g.addIconView(nendAdIconView);
            addView(nendAdIconView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.g.loadAd();
        this.g.setOnClickListener(this);
        this.g.setOnInformationClickListener(this);
        this.g.setOnFailedListener(this);
        this.g.setOnReceiveListener(this);
    }

    public void resume() {
        this.g.resume();
    }

    public void pause() {
        this.g.pause();
    }

    public void setIconOrientation(int i) {
        setOrientation(i);
    }

    public void setTitleColor(int i) {
        this.e = i;
    }

    public void setTitleVisible(boolean z) {
        this.d = z;
    }

    public void setIconSpaceEnabled(boolean z) {
        this.f = z;
    }

    @Override // net.nend.android.NendAdIconLoader.OnReceiveListener
    public void onReceiveAd(NendAdIconView nendAdIconView) {
        NendAdIconLoader.OnReceiveListener onReceiveListener = this.k;
        if (onReceiveListener != null) {
            onReceiveListener.onReceiveAd(nendAdIconView);
        }
    }

    public void setOnReceiveListener(NendAdIconLoader.OnReceiveListener onReceiveListener) {
        this.k = onReceiveListener;
    }

    @Override // net.nend.android.NendAdIconLoader.OnFailedListener
    public void onFailedToReceiveAd(NendIconError nendIconError) {
        NendAdIconLoader.OnFailedListener onFailedListener = this.j;
        if (onFailedListener != null) {
            onFailedListener.onFailedToReceiveAd(nendIconError);
        }
    }

    public void setOnFailedListener(NendAdIconLoader.OnFailedListener onFailedListener) {
        this.j = onFailedListener;
    }

    @Override // net.nend.android.NendAdIconLoader.OnClickListener
    public void onClick(NendAdIconView nendAdIconView) {
        NendAdIconLoader.OnClickListener onClickListener = this.h;
        if (onClickListener != null) {
            onClickListener.onClick(nendAdIconView);
        }
    }

    public void setOnClickListener(NendAdIconLoader.OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    @Override // net.nend.android.NendAdIconLoader.OnInformationClickListener
    public void onClickInformation(NendAdIconView nendAdIconView) {
        NendAdIconLoader.OnInformationClickListener onInformationClickListener = this.i;
        if (onInformationClickListener != null) {
            onInformationClickListener.onClickInformation(nendAdIconView);
        }
    }

    public void setOnInformationClickListener(NendAdIconLoader.OnInformationClickListener onInformationClickListener) {
        this.i = onInformationClickListener;
    }
}
