package id.im.skybandtest1.transaction;

import lombok.Getter;
import lombok.Setter;

public enum TransactionType {

    PURCHASE("Purchase"),
    PURCHASE_WITH_NAQD("Purchase with Naqd"),
    REFUND("Refund"),
    AUTHORIZATION("Authorization"),
    PURCHASE_ADVICE_FULL("Purchase Advice(Full Capture)"),
    AUTHORIZATION_EXTENSION("Authorization Extension"),
    AUTHORIZATION_VOID("Authorization Void"),
    ADVICE("Advice"),
    CASH_ADVANCE("Cash Advance"),
    REVERSAL("Reversal"),
    RECONCILIATION("Reconciliation"),
    FULL_DOWNLOAD("Full Download"),
    SET_SETTINGS("SET Settings"),
    GET_SETTINGS("GET Settings"),
    SET_TERMINAL_LANGUAGE("Set Terminal Language"),
    TERMINAL_STATUS("Terminal Status"),
    PREVIOUS_TRANSACTION_DETAILS("Previous Transaction Details"),
    REGISTER("Register"),
    START_SESSION("Start Session"),
    END_SESSION("End Session"),
    BILL_PAYMENT("Bill Payment"),
    RUNNING_TOTAL("Running Total"),
    PRINT_SUMMARY_REPORT("Print Summary Report"),
    DUPLICATE("Duplicate"),
    CHECK_STATUS("Check Status"),
    PARTIAL_DOWNLOAD("Partial Download"),
    SNAPSHOT_TOTAL("Snapshot Total");

    @Getter
    @Setter
    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
