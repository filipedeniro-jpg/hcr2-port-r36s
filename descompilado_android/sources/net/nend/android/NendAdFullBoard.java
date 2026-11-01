package net.nend.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdFullBoard {
    private final NendAdNative a;
    private final Bitmap b;
    private final Bitmap c;
    private FullBoardAdListener d;
    private NendAdFullBoardActivity.b e = new NendAdFullBoardActivity.b() { // from class: net.nend.android.NendAdFullBoard.1
        @Override // net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity.b
        public void a() {
            if (NendAdFullBoard.this.d != null) {
                NendAdFullBoard.this.d.onShowAd(NendAdFullBoard.this);
            }
        }

        @Override // net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity.b
        public void b() {
            if (NendAdFullBoard.this.d != null) {
                NendAdFullBoard.this.d.onDismissAd(NendAdFullBoard.this);
            }
        }

        @Override // net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity.b
        public void c() {
            if (NendAdFullBoard.this.d != null) {
                NendAdFullBoard.this.d.onClickAd(NendAdFullBoard.this);
            }
        }
    };

    public interface FullBoardAdListener extends NendAdFullBoardView.FullBoardAdClickListener {
        void onDismissAd(NendAdFullBoard nendAdFullBoard);

        void onShowAd(NendAdFullBoard nendAdFullBoard);
    }

    NendAdFullBoard(NendAdNative nendAdNative, Bitmap bitmap, Bitmap bitmap2) {
        this.a = nendAdNative;
        this.b = bitmap;
        this.c = bitmap2;
    }

    public void show(Activity activity) {
        int iHashCode;
        int iA = NendAdFullBoardActivity.d.a(this.b);
        int iA2 = NendAdFullBoardActivity.d.a(this.c);
        if (this.d != null) {
            iHashCode = hashCode();
            NendAdFullBoardActivity.a.a(iHashCode, this.e);
        } else {
            iHashCode = -1;
        }
        activity.startActivity(new Intent(activity, (Class<?>) NendAdFullBoardActivity.class).putExtras(NendAdFullBoardActivity.newBundle(this.a, iA, iA2, iHashCode)));
    }

    public void setAdListener(FullBoardAdListener fullBoardAdListener) {
        this.d = fullBoardAdListener;
    }

    NendAdNative a() {
        return this.a;
    }

    Bitmap b() {
        return this.b;
    }

    Bitmap c() {
        return this.c;
    }
}
