package uz.abbosbek.contact_app.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.abbosbek.contact_app.db.MyConst.CUSTOMER_ID
import uz.abbosbek.contact_app.db.MyConst.CUSTOMER_TABLE
import uz.abbosbek.contact_app.db.MyConst.DATE
import uz.abbosbek.contact_app.db.MyConst.DB_NAME
import uz.abbosbek.contact_app.db.MyConst.DB_VERSION
import uz.abbosbek.contact_app.db.MyConst.EMPLOYEE_ID
import uz.abbosbek.contact_app.db.MyConst.EMPLOYEE_TABLE
import uz.abbosbek.contact_app.db.MyConst.ID
import uz.abbosbek.contact_app.db.MyConst.NAME
import uz.abbosbek.contact_app.db.MyConst.NUMBER
import uz.abbosbek.contact_app.db.MyConst.ORDERS_TABLE
import uz.abbosbek.contact_app.models.MyCustomer
import uz.abbosbek.contact_app.models.MyEmployee
import uz.abbosbek.contact_app.models.MyOrders

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInteface {

    override fun onCreate(p0: SQLiteDatabase?) {
        val customerQuery =
            "create table $CUSTOMER_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $NUMBER text not null)"
        p0?.execSQL(customerQuery)

        val employeeQuery =
            "create table $EMPLOYEE_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $NUMBER text not null)"
        p0?.execSQL(employeeQuery)

        val orderQuery =
            "create table  $ORDERS_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $DATE text not null, $CUSTOMER_ID integer not null, $EMPLOYEE_ID integer not null, foreign key($CUSTOMER_ID) references $CUSTOMER_TABLE($ID), foreign key($EMPLOYEE_ID) references $EMPLOYEE_TABLE($ID))"

        p0?.execSQL(orderQuery)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addCustomer(myCustomer: MyCustomer) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, myCustomer.name)
        contentValues.put(NUMBER, myCustomer.number)
        database.insert(CUSTOMER_TABLE, null, contentValues)
        database.close()
    }

    override fun addAllCustomers(): ArrayList<MyCustomer> {
        val list = ArrayList<MyCustomer>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from $CUSTOMER_TABLE", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addEmployee(myEmployee: MyEmployee) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, myEmployee.name)
        contentValues.put(NUMBER, myEmployee.number)
        database.insert(EMPLOYEE_TABLE, null, contentValues)
        database.close()
    }

    override fun addAllEmployees(): ArrayList<MyEmployee> {
        val list = ArrayList<MyEmployee>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from $EMPLOYEE_TABLE", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyEmployee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addOrders(myOrders: MyOrders) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, myOrders.name)
        contentValues.put(DATE, myOrders.date)
        contentValues.put(EMPLOYEE_ID, myOrders.myEmployee?.id)
        contentValues.put(CUSTOMER_ID, myOrders.myCustomer?.id)
        database.insert(ORDERS_TABLE, null, contentValues)
        database.close()
    }

    override fun addAllOrders(): ArrayList<MyOrders> {

        val list = ArrayList<MyOrders>()

        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from $ORDERS_TABLE", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyOrders(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        getCustomerById(cursor.getInt(3)),
                        getEmployeeById(cursor.getInt(4))

                    )
                )

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getCustomerById(id: Int): MyCustomer {
        val database = this.readableDatabase
        val cursor = database.query(
            CUSTOMER_TABLE,
            arrayOf(ID, NAME, NUMBER),
            "$ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val customer = MyCustomer(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )

        return customer
    }

    override fun getEmployeeById(id: Int): MyEmployee {
        val database = this.readableDatabase
        val cursor = database.query(
            EMPLOYEE_TABLE,
            arrayOf(ID, NAME, NUMBER),
            "$ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val customer = MyEmployee(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )

        return customer
    }
}