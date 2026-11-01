package com.integralads.avid.library.inmobi.walking.async;

import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.utils.AvidCommand;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidEmptyPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidEmptyPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Object... objArr) {
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.integralads.avid.library.inmobi.walking.async.AvidAsyncTask, android.os.AsyncTask
    public void onPostExecute(String str) {
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(internalAvidAdSession.getAvidAdSessionId())) {
                internalAvidAdSession.publishEmptyNativeViewStateCommand(str, this.timestamp);
            }
        }
        super.onPostExecute(str);
    }
}
