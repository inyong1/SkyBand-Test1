package id.im.skybandtest1.ui.fragment.setting.transaction;

import androidx.lifecycle.ViewModel;

import id.im.skybandtest1.cache.GeneralParamCache;
import id.im.skybandtest1.constant.Constant;


public class TransactionSettingViewModel extends ViewModel {
    public static int getPrint() {
        return GeneralParamCache.getInstance().getInt(Constant.ENABLE_PRINT);
    }

    public static int getEcr() {
        return GeneralParamCache.getInstance().getInt(Constant.ENABLE_ECR);
    }

    public static int getAppToAPPCommunication() {
        return GeneralParamCache.getInstance().getInt(Constant.ENABLE_APP_APP_COMMUNICATION);
    }

    public void setData(/*TransactionSettingFragmentBinding transactionSettingFragmentBinding*/) {
        GeneralParamCache.getInstance().putInt(Constant.ENABLE_PRINT, 0);
        GeneralParamCache.getInstance().putInt(Constant.ENABLE_ECR, 0);
//        if (transactionSettingFragmentBinding.ecrNo.isChecked()) {
//
//        } else {
//            GeneralParamCache.getInstance().putInt(Constant.ENABLE_ECR, 0);
//        }
//
//        if (transactionSettingFragmentBinding.terminalPrinter.isChecked()) {
//
//        } else {
//            GeneralParamCache.getInstance().putInt(Constant.ENABLE_PRINT, 0);
//        }

    }
}
