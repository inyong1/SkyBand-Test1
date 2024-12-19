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
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Locale


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

    fun computeSha256Hash(combinedValue: String): String {
        // Last Six digit of ecr reference number + terminal id == combined value

        val md = MessageDigest.getInstance("SHA-256")
        val hashInBytes = md.digest(combinedValue.toByteArray())

        val sb = StringBuilder()

        for (b in hashInBytes) {
            sb.append(String.format("%02x", b))
        }

        val resultSHA = sb.toString()

        return resultSHA
    }

    private fun onButtonPayClick() {
        val amount = binding.et.text.toString().trim().toDoubleOrNull() ?: 0.0
        if(amount == 0.0){
            Toast.makeText(this, "Zero amount not allowed", Toast.LENGTH_SHORT).show()
            return
        }
        try {

            val amountInt = (amount * 100).toInt()
            val date = SimpleDateFormat("ddMMyyHHmmss", Locale.getDefault()).format(System.currentTimeMillis())
            val combined = "12345678000001"
            val signature = computeSha256Hash(combined)
            val command = "$date;$amountInt;0;$combined!"
            val packData =CLibraryLoad.getInstance().getPackData(command,0,signature)
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