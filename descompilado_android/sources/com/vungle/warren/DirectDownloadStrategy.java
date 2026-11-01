package com.vungle.warren;

/* JADX INFO: loaded from: classes2.dex */
public class DirectDownloadStrategy implements DownloadStrategy {
    private PublisherDirectDownload directDownload;

    public DirectDownloadStrategy(PublisherDirectDownload publisherDirectDownload) {
        this.directDownload = publisherDirectDownload;
    }

    @Override // com.vungle.warren.DownloadStrategy
    public void isApplicationAvailable(String str, final DownloadStrategy.VerificationCallback verificationCallback) {
        DirectDownloadAdapter directDownloadAdapter = new DirectDownloadAdapter(this.directDownload, str);
        directDownloadAdapter.getSdkDownloadClient().setAppMarketValidation(new SDKDownloadClient.ValidationCheck() { // from class: com.vungle.warren.DirectDownloadStrategy.1
            @Override // com.vungle.warren.SDKDownloadClient.ValidationCheck
            public void validateAppPresenceInMarket(boolean z) {
                verificationCallback.onResult(z);
            }
        });
        directDownloadAdapter.getSdkDownloadClient().sendValidation(str);
    }
}
