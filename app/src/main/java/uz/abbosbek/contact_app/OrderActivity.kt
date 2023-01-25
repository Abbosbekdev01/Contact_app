package uz.abbosbek.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import uz.abbosbek.contact_app.adapters.MyOrderAdapter
import uz.abbosbek.contact_app.databinding.ActivityOrderBinding
import uz.abbosbek.contact_app.db.MyDbHelper
import uz.abbosbek.contact_app.models.MyCustomer
import uz.abbosbek.contact_app.models.MyEmployee
import uz.abbosbek.contact_app.models.MyOrders

class OrderActivity : AppCompatActivity() {
    private val binding by lazy { ActivityOrderBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<MyOrders>
    private lateinit var employeeList:ArrayList<MyEmployee>
    private lateinit var customerList:ArrayList<MyCustomer>
    private lateinit var myOrderAdapter: MyOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        list = ArrayList()
        list.addAll(myDbHelper.addAllOrders())

        myOrderAdapter = MyOrderAdapter(list)
        binding.rv.adapter = myOrderAdapter

        employeeList = myDbHelper.addAllEmployees()
        customerList = myDbHelper.addAllCustomers()

        val listEmpName = ArrayList<String>()
        employeeList.forEach {
            listEmpName.add(it.name!!)
        }
        val empAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listEmpName)

        val listCustomerName = ArrayList<String>()
        customerList.forEach {
            listCustomerName.add(it.name!!)
        }
        val cusAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listCustomerName)


        binding.apply {
            spinnerEmployee.adapter = empAdapter
            spinnerCustomers.adapter = cusAdapter
            btnSave.setOnClickListener {
                val myOrder = MyOrders(
                    edtName.text.toString(),
                    employeeList[spinnerEmployee.selectedItemPosition],
                    customerList[spinnerCustomers.selectedItemPosition]
                )
                myDbHelper.addOrders(myOrder)
                Toast.makeText(this@OrderActivity, "Saved order", Toast.LENGTH_SHORT).show()
            }
        }
    }
}