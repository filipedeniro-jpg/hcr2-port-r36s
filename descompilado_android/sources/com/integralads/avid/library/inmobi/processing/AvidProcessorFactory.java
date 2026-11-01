package com.integralads.avid.library.inmobi.processing;

/* JADX INFO: loaded from: classes2.dex */
public class AvidProcessorFactory {
    private AvidViewProcessor viewProcessor = new AvidViewProcessor();
    private AvidSceenProcessor screenProcessor = new AvidSceenProcessor(this.viewProcessor);

    public IAvidNodeProcessor getRootProcessor() {
        return this.screenProcessor;
    }
}
