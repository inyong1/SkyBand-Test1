package id.im.skybandtest1

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.skyband.ecr.sdk.BuildConfig
import id.im.skybandtest1.databinding.ActivityMainBinding
import id.im.skybandtest1.model.ActiveTxnData
import id.im.skybandtest1.transaction.TransactionType
import id.im.skybandtest1.ui.fragment.home.HomeViewModel
import id.im.skybandtest1.util.ECRUtils
import java.security.AccessController.getContext
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {
    private val homeViewModel = HomeViewModel()
    private lateinit var binding: ActivityMainBinding
    private val paymentReceiver:BroadcastReceiver =object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.tv.append( intent.toString())

        }
    }
    private var reciverRegistered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.enableEdgeToEdge()
        setContentView(binding!!.root)
//        ViewCompat.setOnApplyWindowInsetsListener(
//            binding.main
//        ) { v: View, insets: WindowInsetsCompat ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(16, systemBars.top, 16, systemBars.bottom+8)
//            insets
//        }

        val filter = IntentFilter("payment-response")
        LocalBroadcastManager.getInstance(this).registerReceiver(paymentReceiver, filter)
        reciverRegistered = true
        binding.bt.setOnClickListener {
            onButtonPayClick()
        }

        ECRUtils.sendAndReceiveBroadcast(this)
    }

    private fun onButtonPayClick() {
        val amount = binding.et.text.toString().trim().toDoubleOrNull() ?: 0.0
        if(amount == 0.0){
            Toast.makeText(this, "Zero amount not allowed", Toast.LENGTH_SHORT).show()
            return
        }
        try {

            val amountInt = (amount * 100).toInt()
            homeViewModel.setReqData(amountInt,this)
//            val date = SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
//            val command = "$date;$amountInt;1;123456780001!;"

            val packData: ByteArray = homeViewModel.getPackData()
            val intent: Intent = packageManager.getLaunchIntentForPackage("com.skyband.pos.app") ?: Intent()
            intent.putExtra("message", "ecr-txn-event")
            intent.putExtra("request", packData)
            intent.putExtra("packageName",  "id.im.skybandtest1")
            startActivity(intent)
        } catch (e: Exception) {
            binding.tv.text = e.message
        }
    }

    override fun onDestroy() {
        if(reciverRegistered){
            reciverRegistered = false
            LocalBroadcastManager.getInstance(this).unregisterReceiver(paymentReceiver)
        }
        ECRUtils.unRegisterBroadcast(this)
        super.onDestroy()
    }




}