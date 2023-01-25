package uz.abbosbek.contact_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.contact_app.databinding.ItemRvBinding
import uz.abbosbek.contact_app.models.MyCustomer
import uz.abbosbek.contact_app.models.MyEmployee

class CustomerEmployeeAdapters<T>(val list: List<T>): RecyclerView.Adapter<CustomerEmployeeAdapters<T>.VH>() {

    inner class VH( val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(empCuts:T, position: Int){
            try {
                val customer:MyCustomer = empCuts as MyCustomer
                itemRvBinding.tvId.text = customer.id.toString()
                itemRvBinding.tvName.text = customer.name.toString()
                itemRvBinding.tvNumber.text = customer.number.toString()
            }catch (e:Exception){
                val employee:MyEmployee = empCuts as MyEmployee
                itemRvBinding.tvId.text = employee.id.toString()
                itemRvBinding.tvName.text = employee.name.toString()
                itemRvBinding.tvNumber.text = employee.number.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}