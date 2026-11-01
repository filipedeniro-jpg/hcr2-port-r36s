package com.integralads.avid.library.adcolony.processing;

import android.view.View;
import com.integralads.avid.library.adcolony.activity.AvidActivityStack;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor a;

    public AvidSceenProcessor(IAvidNodeProcessor iAvidNodeProcessor) {
        this.a = iAvidNodeProcessor;
    }

    @Override // com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor
    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    @Override // com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor
    public void iterateChildren(View view, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker, boolean z) {
        Iterator<View> it = AvidActivityStack.getInstance().getRootViews().iterator();
        while (it.hasNext()) {
            iAvidViewWalker.walkView(it.next(), this.a, jSONObject);
        }
    }
}
