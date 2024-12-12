package id.im.skybandtest1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import id.im.skybandtest1.cache.GeneralParamCache;
import id.im.skybandtest1.constant.Constant;
import id.im.skybandtest1.model.ActiveTxnData;
import lombok.extern.java.Log;

@Log
public class GetPortBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String portNo = intent.getStringExtra("PortNo");
        String connectionType = intent.getStringExtra("ConnectionType");
        // Receive and save payment app response
        byte[] receivedDataByte = intent.getByteArrayExtra("app-to-app-response");
        if (receivedDataByte != null && receivedDataByte.length > 0) {
            System.out.println("GetPortBroadcastReceiver Response " + new String(receivedDataByte));
            String receivedIntentData = new String(receivedDataByte);
            System.out.println("Received ECR Response >>" + receivedIntentData);
            receivedIntentData = receivedIntentData.replace("�", ";");
            ActiveTxnData.getInstance().setReceivedIntentData(receivedIntentData);
            Intent itn =new Intent("payment-response");
            itn.putExtra("response",receivedIntentData);
            LocalBroadcastManager.getInstance(context).sendBroadcast(itn);
        }
        System.out.println("Getting Port No" + portNo);
        System.out.println("Getting Connection Type" + connectionType);
        GeneralParamCache.getInstance().putString(Constant.PORT,portNo);
        GeneralParamCache.getInstance().putString(Constant.MADA_CONNECTION_TYPE,connectionType);

    }
}