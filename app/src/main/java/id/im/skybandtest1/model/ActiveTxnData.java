package id.im.skybandtest1.model;

import android.bluetooth.BluetoothDevice;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActiveTxnData {

    private String reqData;
//    private TransactionType transactionType;
    private String terminalID;
    private String szSignature;
    private String ecrReferenceNo="12345678";
    private boolean registered;
    private boolean sessionStarted;
    private String resData;
    private String[] replacedArray;
    private String[] summaryReportArray;
    private int position;
    private int connectPosition;
    private boolean isLocalHostConnectionType;
    private boolean isPartialCapture;
    private BluetoothDevice device;
    private String receivedIntentData;
    private String cashRegisterNo;
    private boolean isLastTxnSummary;
    private String connectionMode;

    private static ActiveTxnData activeTxnData;

    private ActiveTxnData() {

    }

    public static ActiveTxnData getInstance() {

        if (activeTxnData == null) {
            activeTxnData = new ActiveTxnData();
        }

        return activeTxnData;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

//    public TransactionType getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(TransactionType transactionType) {
//        this.transactionType = transactionType;
//    }

    public String getEcrReferenceNo() {
        return ecrReferenceNo;
    }

    public void setEcrReferenceNo(String ecrReferenceNo) {
        this.ecrReferenceNo = ecrReferenceNo;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isSessionStarted() {
        return sessionStarted;
    }

    public void setSessionStarted(boolean sessionStarted) {
        this.sessionStarted = sessionStarted;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public String[] getReplacedArray() {
        return replacedArray;
    }

    public void setReplacedArray(String[] replacedArray) {
        this.replacedArray = replacedArray;
    }

    public String[] getSummaryReportArray() {
        return summaryReportArray;
    }

    public void setSummaryReportArray(String[] summaryReportArray) {
        this.summaryReportArray = summaryReportArray;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getConnectPosition() {
        return connectPosition;
    }

    public void setConnectPosition(int connectPosition) {
        this.connectPosition = connectPosition;
    }

    public boolean isLocalHostConnectionType() {
        return isLocalHostConnectionType;
    }

    public void setLocalHostConnectionType(boolean localHostConnectionType) {
        isLocalHostConnectionType = localHostConnectionType;
    }

    public boolean isPartialCapture() {
        return isPartialCapture;
    }

    public void setPartialCapture(boolean partialCapture) {
        isPartialCapture = partialCapture;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public String getReceivedIntentData() {
        return receivedIntentData;
    }

    public void setReceivedIntentData(String receivedIntentData) {
        this.receivedIntentData = receivedIntentData;
    }

    public String getCashRegisterNo() {
        return cashRegisterNo;
    }

    public void setCashRegisterNo(String cashRegisterNo) {
        this.cashRegisterNo = cashRegisterNo;
    }

    public boolean isLastTxnSummary() {
        return isLastTxnSummary;
    }

    public void setLastTxnSummary(boolean lastTxnSummary) {
        isLastTxnSummary = lastTxnSummary;
    }

    public String getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(String connectionMode) {
        this.connectionMode = connectionMode;
    }

    public String getSzSignature() {
        return szSignature;
    }

    public void setSzSignature(String szSignature) {
        this.szSignature = szSignature;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }
}
