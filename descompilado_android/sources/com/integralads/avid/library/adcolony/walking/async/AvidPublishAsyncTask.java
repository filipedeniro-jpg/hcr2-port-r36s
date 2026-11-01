package com.integralads.avid.library.adcolony.walking.async;

import android.text.TextUtils;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Object... objArr) {
        if (AvidJSONUtil.equalStates(this.state, this.stateProvider.getPreviousState())) {
            return null;
        }
        this.stateProvider.setPreviousState(this.state);
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask, android.os.AsyncTask
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
        }
        super.onPostExecute(str);
    }

    private void b(String str) {
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(internalAvidAdSession.getAvidAdSessionId())) {
                internalAvidAdSession.publishNativeViewStateCommand(str, this.timestamp);
            }
        }
    }
}
