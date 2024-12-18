package id.im.skybandtest1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.skyband.ecr.sdk.CLibraryLoad
import id.im.skybandtest1.databinding.ActivityMainBinding
import id.im.skybandtest1.util.ECRUtils
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val paymentReceiver:BroadcastReceiver =object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.tv.append( "\n")
            binding.tv.append( intent.toString())
            val data = intent?.getStringExtra("response")
            if(data?.isNotEmpty() == true){
                binding.tv.append( "\n")
                binding.tv.append( data)
            }

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
            val date = SimpleDateFormat("ddMMyyHHmmss").format(System.currentTimeMillis())
            val command = "$date;$amountInt;0;12345678000001!"
            val packData =CLibraryLoad.getInstance().getPackData(command,0,"")
            binding.tv.append("\nData: ")
            binding.tv.append(command)
            binding.tv.append("\nPackData: ")
            binding.tv.append(String(packData))
            val intent: Intent? = packageManager.getLaunchIntentForPackage("com.skyband.pos.app")
            intent?.let {
                intent.putExtra("message", "ecr-txn-event")
                intent.putExtra("request", packData)
                intent.putExtra("packageName",  "id.im.skybandtest1")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }?:run{
                throw Exception("target com.skyband.pos.app not found")
            }
        } catch (e: Exception) {
            binding.tv.append("\n")
            binding.tv.append(e.toString())
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