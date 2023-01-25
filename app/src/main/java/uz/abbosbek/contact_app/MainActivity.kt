package uz.abbosbek.contact_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.abbosbek.contact_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnCustomer.setOnClickListener {  startActivity(Intent(this@MainActivity, CustomerActivity::class.java)) }
            btnEmployee.setOnClickListener { startActivity(Intent(this@MainActivity, EmployeeActivity::class.java)) }
            btnOrder.setOnClickListener { startActivity(Intent(this@MainActivity, OrderActivity::class.java)) }
        }
    }
}