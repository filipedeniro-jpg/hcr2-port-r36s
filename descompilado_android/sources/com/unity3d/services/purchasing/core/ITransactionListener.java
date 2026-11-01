package com.unity3d.services.purchasing.core;

/* JADX INFO: loaded from: classes2.dex */
public interface ITransactionListener {
    void onTransactionComplete(TransactionDetails transactionDetails);

    void onTransactionError(TransactionErrorDetails transactionErrorDetails);
}
