package id.im.skybandtest1.ui.fragment.response.buffer;

import androidx.lifecycle.ViewModel;

import id.im.skybandtest1.constant.Constant;
import com.skyband.ecr.sdk.logger.Logger;

public class BufferResponseViewModel extends ViewModel {

    private static String htmlStart = "<html><head></head><body><table style='border-collapse: collapse; width: 100%;'>";
    private static String htmlSeperator1 = "<tr style = 'border: 1px solid #ddd; border: 1px solid #ddd; padding: 8px;'><td>";
    private static String htmlSeperator2 = "</td><td>:</td><td>";
    private static String htmlSeperator3 = "</td></tr>";
    private static String htmlEnd = "</table></body></html>";
    private Logger logger = Logger.getNewLogger(BufferResponseViewModel.class.getName());

    private static String responseToHtml(StringBuilder htmlString) {
        StringBuilder html = new StringBuilder();
        html.append(htmlStart);
        html.append(htmlString);
        html.append(htmlEnd);
        return html.toString();
    }

    public String printResponsePurchase(String[] resp) {
        String pan;
        StringBuilder htmlString = new StringBuilder();
        if (resp[4].length() > 14) {
            pan = maskPAn(resp[4]);
        } else {
            pan = resp[4];
        }
        String[] purchaseArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                "PanNumber", "TransactionAmount", Constant.BUZZ_CODE, "StanNo", Constant.DATE_TIME, "CardExpriyDate",
                "RRN", "AuthCode", "TID", "MID", "BatchNo", "AID", "ApplicationCryptogram", "CID", "CVR", "TVR", "TSI", "KERNEL-Id", "PAR", "PANSUFFIX",
                "CardEntryMode", "MerchantCategoryCode", "TerminalTransactionType", "Schemelabel", "ProductInfo",
                Constant.APPLICATION_VERSION, Constant.NO_VERIFICATION_REQUIRED,
                Constant.MERCHANT_NAME, Constant.MERCHANT_ADDRESS, Constant.MERCHANT_NAME_ARABIC, Constant.MERCHANT_ADDRESS_ARABIC,
                Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};
        for (int i = 1; i < purchaseArray.length; i++) {
            if (i != 4) {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[i]);
                htmlString.append(htmlSeperator3);
            } else {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(pan);
                htmlString.append(htmlSeperator3);
            }
        }
        return responseToHtml(htmlString);
    }

    public String printResponseRegister(String[] resp) {
        StringBuilder htmlString = new StringBuilder();

        String[] registerArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, "Terminal id"};

        for (int i = 1; i < registerArray.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(registerArray[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    public String printResponseStartSession(String[] resp) {
        StringBuilder htmlString = new StringBuilder();
        String[] sessionArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE};
        for (int i = 1; i < sessionArray.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(sessionArray[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    public String printResponsePurchaseCashBack(String[] resp) {
        logger.info("purchase cashback: {0}" + resp.length);
        String pan;
        StringBuilder htmlString = new StringBuilder();
        if (resp[4].length() > 14) {
            pan = maskPAn(resp[4]);
        } else {
            pan = resp[4];
        }
        String[] purchaseCashBackArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE,
                Constant.RESPONSE_MESSAGE, "PanNumber", "TransactionAmount", "CashBackAmount", "TotalAmount",
                Constant.BUZZ_CODE, "StanNo", Constant.DATE_TIME, "CardExpriyDate", "RRN", "AuthCode", "TID", "MID",
                "BatchNo", "AID", "ApplicationCryptogram", "CID", "CVR", "TVR", "TSI", "KERNEL-Id", "PAR", "PANSUFFIX", "CardEntryMode",
                "MerchantCategoryCode", "TerminalTransactionType", "Schemelabel", "ProductInfo",
                Constant.APPLICATION_VERSION, Constant.NO_VERIFICATION_REQUIRED,
                Constant.MERCHANT_NAME, Constant.MERCHANT_ADDRESS, Constant.MERCHANT_NAME_ARABIC, Constant.MERCHANT_ADDRESS_ARABIC,
                Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};

        for (int i = 1; i < purchaseCashBackArray.length; i++) {
            if (i != 4) {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseCashBackArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[i]);
                htmlString.append(htmlSeperator3);
            } else {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseCashBackArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(pan);
                htmlString.append(htmlSeperator3);
            }

        }
        return responseToHtml(htmlString);

    }

    public String printResponseReconcilation(String[] resp) {
        if (resp[2].equalsIgnoreCase("500") || resp[2].equalsIgnoreCase("501")) {

            StringBuilder htmlString = new StringBuilder();
            String[] posTerminalDetails = {"", "TransactionAvailableFlag", Constant.SCHEME_NAME, "POFFCount",
                    "POFFAmount", "PONCount", "PONAmount", "NAQDCount", "NAQDAmount", "ReversalCount", "ReversalAmount",
                    "RefundCount", "RefundAmount", "CompCount", "CompAmount"};
            String[] posTerminal = {"", "TransactionAvailableFlag", Constant.SCHEME_NAME, "TotalDebitCount",
                    "TotalDebitAmount", "TotalCreditCount", "TotalCreditAmount", "NAQDCount", "NAQDAmount", "CADVCount",
                    "CADVAmount", "AuthCount", "AuthAmount", "TotalCount", "TotalAmount"};
            String[] printSettlement = {"", Constant.SCHEME_NAME, Constant.TRANSACTION_AVAILABLE_FLAG, "SchemeHost",
                    "Total Debit Count", "Total Debit Amount", "Total Credit Count", "Total Credit Amount",
                    Constant.NAQD_COUNT, Constant.NAQD_AMOUNT, "C/ADV Count", "C/ADV Amount", "Auth Count",
                    "Auth Amount", "Total Count", "Total Amount"};
            String[] reconcilation = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                    Constant.DATE_TIME, "MerchantId", Constant.BUZZ_CODE, "TraceNumber", Constant.APPLICATION_VERSION,
                    "TotalSchemeLength"};
            int k = 9;
            int totalSchemeLength = Integer.parseInt(resp[9]);
            for (int i = 1; i <= 9; i++) {
                htmlString.append(htmlSeperator1);
                htmlString.append(reconcilation[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[i]);
                htmlString.append(htmlSeperator3);
            }
            for (int i = 1; i <= totalSchemeLength; i++) {

                if (resp[k + 2].equals("0")) {
                    htmlString.append(htmlSeperator1);
                    htmlString.append(Constant.SCHEME_NAME);
                    htmlString.append(htmlSeperator2);
                    htmlString.append(resp[k + 1]);
                    htmlString.append(htmlSeperator3);

                    htmlString.append(htmlSeperator1);
                    htmlString.append(Constant.NO_TRANSACTION);
                    htmlString.append(" ");
                    htmlString.append(htmlSeperator3);
                    k = k + 3;
                } else {
                    if (resp[k + 3].equalsIgnoreCase("mada HOST")) {
                        i = i - 1;
                        for (int j = 1; j < printSettlement.length; j++) {

                            htmlString.append(htmlSeperator1);
                            htmlString.append(printSettlement[j]);
                            htmlString.append(htmlSeperator2);
                            htmlString.append(resp[k + j]);
                            htmlString.append(htmlSeperator3);

                        }
                        k = k + 15;
                    } else if (resp[k + 2].equalsIgnoreCase("POS TERMINAL")) {
                        i = i - 1;
                        for (int j = 1; j < posTerminal.length; j++) {
                            htmlString.append(htmlSeperator1);
                            htmlString.append(posTerminal[j]);
                            htmlString.append(htmlSeperator2);
                            htmlString.append(resp[k + j]);
                            htmlString.append(htmlSeperator3);

                        }
                        k = k + 14;
                    } else if (resp[k + 2].equalsIgnoreCase("POS TERMINAL DETAILS")) {
                        i = i - 1;
                        for (int j = 1; j < posTerminalDetails.length; j++) {
                            htmlString.append(htmlSeperator1);
                            htmlString.append(posTerminalDetails[j]);
                            htmlString.append(htmlSeperator2);
                            htmlString.append(resp[k + j]);
                            htmlString.append(htmlSeperator3);

                        }
                        k = k + 14;
                    } else if (resp[k + 1].equals("0")) {

                        htmlString.append(htmlSeperator1);
                        htmlString.append(Constant.SCHEME_NAME);
                        htmlString.append(htmlSeperator2);
                        htmlString.append("PosTerminal");
                        htmlString.append(htmlSeperator3);

                        htmlString.append(htmlSeperator1);
                        htmlString.append(Constant.NO_TRANSACTION);
                        htmlString.append(" ");
                        htmlString.append(htmlSeperator3);

                        htmlString.append(htmlSeperator1);
                        htmlString.append(Constant.SCHEME_NAME);
                        htmlString.append(htmlSeperator2);
                        htmlString.append("PosTerminalDetails");
                        htmlString.append(htmlSeperator3);

                        htmlString.append(htmlSeperator1);
                        htmlString.append(Constant.NO_TRANSACTION);
                        htmlString.append(" ");
                        htmlString.append(htmlSeperator3);
                        k = k + 1;
                    }
                }
            }
            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.MERCHANT_NAME);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 1]);
            htmlString.append(htmlSeperator3);

            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.MERCHANT_ADDRESS);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 2]);
            htmlString.append(htmlSeperator3);

            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.MERCHANT_NAME_ARABIC);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 3]);
            htmlString.append(htmlSeperator3);

            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.MERCHANT_ADDRESS_ARABIC);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 4]);
            htmlString.append(htmlSeperator3);

            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.ECR_TRANSACTION_REFERENCE_NUMBER);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 5]);
            htmlString.append(htmlSeperator3);

            htmlString.append(htmlSeperator1);
            htmlString.append(Constant.SIGNATURE);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[k + 6]);
            htmlString.append(htmlSeperator3);

            return responseToHtml(htmlString);
        } else {
            StringBuilder htmlString = new StringBuilder();
            String[] runningDetail = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                    Constant.MERCHANT_NAME, Constant.MERCHANT_ADDRESS, Constant.MERCHANT_NAME_ARABIC, Constant.MERCHANT_ADDRESS_ARABIC,
                    Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};
            for (int i = 1; i < runningDetail.length; i++) {
                htmlString.append(htmlSeperator1);
                htmlString.append(runningDetail[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[i]);
                htmlString.append(htmlSeperator3);
            }
            return responseToHtml(htmlString);
        }
    }

    public String printResponseParameterDownload(String[] resp) {
        StringBuilder htmlString = new StringBuilder();
        String[] parameterArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.DATE_TIME, "EcrTransactionRefNo", Constant.SIGNATURE};
        for (int i = 1; i < parameterArray.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(parameterArray[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }


    public String printResponseGetSetting(String[] resp) {
        StringBuilder htmlString = new StringBuilder();
        String[] getParameter = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.DATE_TIME, "VendorId", "VendorTerminalType", "TrsmId", "VendorKeyIndex", "SamaKeyIndex",
                Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};
        for (int i = 1; i < getParameter.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(getParameter[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    String printResponseRepeat(String[] resp) {
        StringBuilder htmlString = new StringBuilder();
        String[] repeatArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.DATE_TIME, "Previous Transaction Response", "Previous ECR Number",
                Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};
        for (int i = 1; i < repeatArray.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(repeatArray[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    public String printResponseDefault(String[] resp) {
        StringBuilder htmlString = new StringBuilder();

        String[] otherResponse = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE};

        for (int i = 1; i < otherResponse.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(otherResponse[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);

    }

    public String printResponseOtherTransaction(String[] resp) {

        StringBuilder htmlString = new StringBuilder();

        String[] otherResponse = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE};

        for (int i = 2; i < otherResponse.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(otherResponse[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);

    }

    public String printResponseRunningTotal(String[] resp) {

        StringBuilder htmlString = new StringBuilder();
        String[] runningTotal = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.DATE_TIME, "TraceNumber", Constant.BUZZ_CODE, Constant.APPLICATION_VERSION,
                "TotalSchemeLength"};
        String[] printSettlementPos = {"", "Scheme Name", Constant.TRANSACTION_AVAILABLE_FLAG, "Scheme Host",
                "Total Debit Count", "Total Debit Amount", "Total Credit Count", "Total Credit Amount",
                Constant.NAQD_COUNT, Constant.NAQD_AMOUNT, "C/ADV Count", "C/ADV Amount", "Auth Count",
                "Auth Amount", "Total Count", "Total Amount"};
        String[] printSettlmentPosDetails = {"", Constant.TRANSACTION_AVAILABLE_FLAG, "Scheme Name", "P/OFF Count",
                "P/OFF Amount", "P/ON Count", "P/ON Amount", Constant.NAQD_COUNT, Constant.NAQD_AMOUNT,
                "REVERSAL Count", "REVERSAL Amount", "Refund Count", "Refund Amount", "Comp Count", "Comp Amount"};
        int k = 8;
        int totalSchemeLength = Integer.parseInt(resp[8]);
        for (int i = 1; i <= 8; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(runningTotal[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        for (int i = 1; i <= totalSchemeLength; i++) {

            if (resp[k + 2].equalsIgnoreCase("0")) {

                htmlString.append(htmlSeperator1);
                htmlString.append(Constant.SCHEME_NAME);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[k + 1]);
                htmlString.append(htmlSeperator3);

                htmlString.append(htmlSeperator1);
                htmlString.append(Constant.NO_TRANSACTION);
                htmlString.append(" ");
                htmlString.append(htmlSeperator3);
                k = k + 2;
            } else {
                if (resp[k + 3].equalsIgnoreCase("POS TERMINAL")) {
                    i = i - 1;
                    for (int j = 1; j < printSettlementPos.length; j++) {

                        htmlString.append(htmlSeperator1);
                        htmlString.append(printSettlementPos[j]);
                        htmlString.append(htmlSeperator2);
                        htmlString.append(resp[k + j]);
                        htmlString.append(htmlSeperator3);
                    }
                    k = k + 15;
                } else if (resp[k + 2].equalsIgnoreCase("POS TERMINAL DETAILS")) {
                    i = i - 1;
                    for (int j = 1; j < printSettlmentPosDetails.length; j++) {

                        htmlString.append(htmlSeperator1);
                        htmlString.append(printSettlmentPosDetails[j]);
                        htmlString.append(htmlSeperator2);
                        htmlString.append(resp[k + j]);
                        htmlString.append(htmlSeperator3);

                    }
                    k = k + 14;
                }
            }
        }

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_NAME);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 1]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_ADDRESS);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 2]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_NAME_ARABIC);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 3]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_ADDRESS_ARABIC);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 4]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.ECR_TRANSACTION_REFERENCE_NUMBER);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 5]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.SIGNATURE);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 6]);
        htmlString.append(htmlSeperator3);

        return responseToHtml(htmlString);
    }

    public String printResponseRunningTotalDefault(String[] resp) {
        StringBuilder htmlString = new StringBuilder();
        String[] runningDetail = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.MERCHANT_NAME, Constant.MERCHANT_ADDRESS, Constant.MERCHANT_NAME_ARABIC, Constant.MERCHANT_ADDRESS_ARABIC,
                "EcrReferenceNumber", Constant.SIGNATURE};
        for (int i = 1; i < runningDetail.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(runningDetail[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    public String printResponseSummaryReport(String[] resp) {

        StringBuilder htmlString = new StringBuilder();

        String[] printSummaryReport = {"Transaction Type", "Date", "RRN", "Transaction Amount", "Claim", "State",
                "Time", "PAN Number", "authCode", "transactionNumber"};
        String[] printSummaryReturn = {"Transaction type", "Response Code", "Response Message", Constant.DATE_TIME,
                "Transaction Requests Count", "Total Transactions Length"};

        int k = 6;
        int transactionsLength = Integer.parseInt(resp[5]);

        for (int i = 0; i <= 5; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(printSummaryReturn[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }

        for (int j = 1; j <= transactionsLength; j++) {
            htmlString.append(htmlSeperator1);
            htmlString.append("Transaction");
            htmlString.append(htmlSeperator2);
            htmlString.append(j);
            htmlString.append(htmlSeperator3);
            for (int m = 0; m <= 9; m++) {
                htmlString.append(htmlSeperator1);
                htmlString.append(printSummaryReport[m]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[k + m]);
                htmlString.append(htmlSeperator3);
            }
            k = k + 10;
        }

        htmlString.append("Topic");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append("DB Count");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 1]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append("DB Amount");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 2]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append("CR Count");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 3]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append("CR Amount");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 4]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append("Total Amount");
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 5]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_NAME);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 6]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_ADDRESS);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 7]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_NAME_ARABIC);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 8]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.MERCHANT_ADDRESS_ARABIC);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 9]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.ECR_TRANSACTION_REFERENCE_NUMBER);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 10]);
        htmlString.append(htmlSeperator3);

        htmlString.append(htmlSeperator1);
        htmlString.append(Constant.SIGNATURE);
        htmlString.append(htmlSeperator2);
        htmlString.append(resp[k + 11]);
        htmlString.append(htmlSeperator3);

        logger.info("PrintResponse | PrintResponseSummary | Exited");

        return responseToHtml(htmlString);

    }

    public String printResponseReversal(String[] resp) {

        String pan;
        StringBuilder htmlString = new StringBuilder();
        if (resp[4].length() > 14) {
            pan = maskPAn(resp[4]);
        } else {
            pan = resp[4];
        }
        String[] purchaseArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                "PanNumber", "TransactionAmount", Constant.BUZZ_CODE, "StanNo", Constant.DATE_TIME, "CardExpriyDate",
                "RRN", "AuthCode", "TID", "MID", "BatchNo", "AID", "ApplicationCryptogram", "CID", "CVR", "TVR", "TSI", "KERNEL-Id", "PAR", "PANSUFFIX",
                "CardEntryMode", "MerchantCategoryCode", "TerminalTransactionType", "Schemelabel",
                "ProductInfo", Constant.APPLICATION_VERSION, Constant.NO_VERIFICATION_REQUIRED,
                Constant.MERCHANT_NAME, Constant.MERCHANT_ADDRESS, Constant.MERCHANT_NAME_ARABIC,
                Constant.MERCHANT_ADDRESS_ARABIC, Constant.ECR_TRANSACTION_REFERENCE_NUMBER, Constant.SIGNATURE};
        for (int i = 1; i < purchaseArray.length; i++) {
            if (i != 4) {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(resp[i]);
                htmlString.append(htmlSeperator3);
            } else {
                htmlString.append(htmlSeperator1);
                htmlString.append(purchaseArray[i]);
                htmlString.append(htmlSeperator2);
                htmlString.append(pan);
                htmlString.append(htmlSeperator3);
            }
        }
        return responseToHtml(htmlString);
    }

    public String printResponseCheckStatus(String[] resp) {

        StringBuilder htmlString = new StringBuilder();
        String[] parameterArray = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE,
                Constant.DATE_TIME, "EcrTransactionRefNo", Constant.SIGNATURE};
        for (int i = 1; i < parameterArray.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(parameterArray[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(resp[i]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }

    private String maskPAn(String s) {
        return s.substring(0, 5) + "******" + s.substring(s.length() - 4);
    }

    public String printResponseEmptySummary(String[] summaryReportArray) {
        StringBuilder htmlString = new StringBuilder();

        String[] otherResponse = {"", Constant.TRANSACTION_TYPE, Constant.RESPONSE_CODE, Constant.RESPONSE_MESSAGE};

        for (int i = 1; i < otherResponse.length; i++) {
            htmlString.append(htmlSeperator1);
            htmlString.append(otherResponse[i]);
            htmlString.append(htmlSeperator2);
            htmlString.append(summaryReportArray[i-1]);
            htmlString.append(htmlSeperator3);
        }
        return responseToHtml(htmlString);
    }
}