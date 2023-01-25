package uz.abbosbek.contact_app.models

import java.text.SimpleDateFormat
import java.util.*

class MyOrders {
    var id: Int? = null
    var name: String? = null
    var date = SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").format(Date())
    var myCustomer:MyCustomer? = null
    var myEmployee:MyEmployee? = null

    constructor(
        id: Int?,
        name: String?,
        date: String?,
        myCustomer: MyCustomer?,
        myEmployee: MyEmployee?
    ) {
        this.id = id
        this.name = name
        this.date = date
        this.myCustomer = myCustomer
        this.myEmployee = myEmployee
    }

    constructor(name: String?, myCustomer: MyEmployee, myEmployee: MyCustomer) {
        this.name = name
        this.myCustomer = MyCustomer(id,"sad","asd")
        this.myEmployee = MyEmployee(id,"sad","asd")
    }
}