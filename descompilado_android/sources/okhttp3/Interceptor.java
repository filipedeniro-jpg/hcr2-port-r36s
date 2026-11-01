package okhttp3;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface Interceptor {

    public interface Chain {
        Connection connection();

        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
