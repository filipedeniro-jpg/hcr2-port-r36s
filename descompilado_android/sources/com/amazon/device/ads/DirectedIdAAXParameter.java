package com.amazon.device.ads;

/* JADX INFO: compiled from: AAXParameterGroupParameter.java */
/* JADX INFO: loaded from: classes.dex */
class DirectedIdAAXParameter extends AAXParameterGroupParameter {
    private final Configuration configuration;
    private final DirectedIdRetriever directedIdRetriever;

    DirectedIdAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory(), Configuration.getInstance(), (DirectedIdRetriever) Settings.getInstance().getObject(DirectedIdRetriever.SETTINGS_KEY, null, DirectedIdRetriever.class));
    }

    DirectedIdAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory, Configuration configuration, DirectedIdRetriever directedIdRetriever) {
        super(debugProperties, "directedId", DebugProperties.DEBUG_DIRECTEDID, mobileAdsLoggerFactory);
        this.configuration = configuration;
        this.directedIdRetriever = directedIdRetriever;
    }

    @Override // com.amazon.device.ads.AAXParameterGroupParameter
    protected String getDerivedValue(AAXParameter.ParameterData parameterData) {
        DirectedIdRetriever directedIdRetriever;
        if (!this.configuration.getBoolean(Configuration.ConfigOption.WHITELISTED_CUSTOMER) || (directedIdRetriever = this.directedIdRetriever) == null) {
            return null;
        }
        return directedIdRetriever.getDirectedId();
    }
}
