package com.inmobi.ads;

/* JADX INFO: compiled from: AdNetworkResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    com.inmobi.commons.core.network.d a;
    InMobiAdRequestStatus b;
    f c;

    public g(f fVar, com.inmobi.commons.core.network.d dVar) {
        this.c = fVar;
        this.a = dVar;
        if (this.a.b != null) {
            switch (this.a.b.a) {
                case NETWORK_UNAVAILABLE_ERROR:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE);
                    break;
                case BAD_REQUEST:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_INVALID);
                    if (this.a.b.b != null) {
                        this.b.setCustomMessage(this.a.b.b);
                    }
                    break;
                case HTTP_GATEWAY_TIMEOUT:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_TIMED_OUT);
                    break;
                case HTTP_INTERNAL_SERVER_ERROR:
                case HTTP_NOT_IMPLEMENTED:
                case HTTP_BAD_GATEWAY:
                case HTTP_SERVER_NOT_AVAILABLE:
                case HTTP_VERSION_NOT_SUPPORTED:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.SERVER_ERROR);
                    break;
                case GDPR_COMPLIANCE_ENFORCED:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GDPR_COMPLIANCE_ENFORCED);
                    break;
                default:
                    this.b = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR);
                    break;
            }
        }
    }
}
