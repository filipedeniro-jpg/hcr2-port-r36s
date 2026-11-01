package com.integralads.avid.library.adcolony.processing;

/* JADX INFO: loaded from: classes2.dex */
public class AvidProcessorFactory {
    private AvidViewProcessor b = new AvidViewProcessor();
    private AvidSceenProcessor a = new AvidSceenProcessor(this.b);

    public IAvidNodeProcessor getRootProcessor() {
        return this.a;
    }
}
