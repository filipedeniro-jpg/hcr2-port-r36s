package net.nend.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.internal.FacebookRequestErrorClassification;
import net.nend.android.internal.c.b.b;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.m;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeClient {
    private net.nend.android.internal.c.b.a a;
    private a b;

    public interface Callback {
        void onFailure(NendError nendError);

        void onSuccess(NendAdNative nendAdNative);
    }

    public NendAdNativeClient(Context context, int i, String str) {
        Context context2 = (Context) m.a(context);
        e.a(context2);
        this.a = new net.nend.android.internal.c.b.a(context2, new b(context2, i, str));
    }

    public void loadAd(Callback callback) {
        this.a.a(callback);
    }

    public void enableAutoReload(long j, Callback callback) {
        a aVar = this.b;
        if (aVar != null && aVar.b()) {
            this.b.a();
        }
        if (j < 30000) {
            callback.onFailure(NendError.INVALID_INTERVAL_MILLIS);
        } else {
            this.b = new a(Looper.getMainLooper(), callback);
            this.b.a(Long.valueOf(j));
        }
    }

    public void disableAutoReload() {
        a aVar = this.b;
        if (aVar == null) {
            j.c("First, you should call 'enableAutoReload'.");
        } else {
            aVar.a();
        }
    }

    public enum NendError {
        FAILED_AD_REQUEST(340, "Failed to Ad request."),
        INVALID_RESPONSE_TYPE(FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, "Response type is invalid."),
        INVALID_INTERVAL_MILLIS(342, "Interval millis set 30000 or more.");

        private final int a;
        private final String b;

        NendError(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public String getMessage() {
            return this.b;
        }

        public int getCode() {
            return this.a;
        }
    }

    private class a extends Handler {
        private boolean b;
        private Callback c;
        private long d;

        a(Looper looper, Callback callback) {
            super(looper);
            this.c = callback;
            this.b = false;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            NendAdNativeClient.this.a.a(this.c);
            if (this.b) {
                sendEmptyMessageDelayed(113, this.d);
            }
        }

        public void a() {
            this.b = false;
            removeMessages(113);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            return this.b;
        }

        public void a(Long l) {
            this.d = l.longValue();
            this.b = true;
            sendEmptyMessageDelayed(113, l.longValue());
        }
    }
}
