package uz.abbosbek.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.abbosbek.contact_app.adapters.CustomerEmployeeAdapters
import uz.abbosbek.contact_app.databinding.ActivityCustomerBinding
import uz.abbosbek.contact_app.db.MyDbHelper
import uz.abbosbek.contact_app.models.MyCustomer

class CustomerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCustomerBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<MyCustomer>
    private lateinit var customerEmployeeAdapters: CustomerEmployeeAdapters<MyCustomer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        list = myDbHelper.addAllCustomers()
        customerEmployeeAdapters = CustomerEmployeeAdapters(list)

        binding.apply {
            binding.rv.adapter = customerEmployeeAdapters
            binding.btnSave.setOnClickListener {
                val customer = MyCustomer(
                    edtName.text.toString(),
                    edtNumber.text.toString()
                )
                myDbHelper.addCustomer(customer)
                list.add(customer)
                customerEmployeeAdapters.notifyItemInserted(list.size - 1)
                Toast.makeText(this@CustomerActivity, "Saved", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
