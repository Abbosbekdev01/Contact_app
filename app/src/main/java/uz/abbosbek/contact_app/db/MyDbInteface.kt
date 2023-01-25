package uz.abbosbek.contact_app.db

import uz.abbosbek.contact_app.models.MyCustomer
import uz.abbosbek.contact_app.models.MyEmployee
import uz.abbosbek.contact_app.models.MyOrders

interface MyDbInteface {

    fun addCustomer(myCustomer: MyCustomer)
    fun addAllCustomers():ArrayList<MyCustomer>

    fun addEmployee(myEmployee: MyEmployee)
    fun addAllEmployees():ArrayList<MyEmployee>

    fun addOrders(myOrders: MyOrders)
    fun addAllOrders():ArrayList<MyOrders>

    fun getCustomerById(id:Int):MyCustomer
    fun getEmployeeById(id:Int):MyEmployee
}