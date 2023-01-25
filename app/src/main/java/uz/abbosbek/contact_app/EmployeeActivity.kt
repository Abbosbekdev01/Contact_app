package uz.abbosbek.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.abbosbek.contact_app.adapters.CustomerEmployeeAdapters
import uz.abbosbek.contact_app.databinding.ActivityEmployeeBinding
import uz.abbosbek.contact_app.db.MyDbHelper
import uz.abbosbek.contact_app.models.MyEmployee

class EmployeeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEmployeeBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var customerEmployeeAdapters: CustomerEmployeeAdapters<MyEmployee>
    private lateinit var list: ArrayList<MyEmployee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        myDbHelper = MyDbHelper(this)
        list = myDbHelper.addAllEmployees()
        customerEmployeeAdapters = CustomerEmployeeAdapters(list)

        binding.apply {
            binding.rv.adapter = customerEmployeeAdapters
            binding.btnSave.setOnClickListener {
                val myEmployee = MyEmployee(
                    edtName.text.toString(),
                    edtNumber.text.toString()
                )
                myDbHelper.addEmployee(myEmployee)
                list.add(myEmployee)
                customerEmployeeAdapters.notifyItemInserted(list.size - 1)

            }
        }
    }
}