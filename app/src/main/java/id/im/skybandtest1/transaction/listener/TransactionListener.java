package com.skyband.ecr.transaction.listener;

public interface TransactionListener {

    void onSuccess();

    void onError(Exception e);
}
