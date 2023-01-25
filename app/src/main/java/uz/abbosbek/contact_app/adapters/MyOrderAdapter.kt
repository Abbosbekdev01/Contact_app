package uz.abbosbek.contact_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.contact_app.databinding.ItemRvBinding
import uz.abbosbek.contact_app.models.MyOrders

class MyOrderAdapter(val list: ArrayList<MyOrders>):RecyclerView.Adapter<MyOrderAdapter.VH>() {

    inner class VH( val itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(position:Int, myOrders: MyOrders){
            itemRvBinding.tvName.text = myOrders.name
            itemRvBinding.tvData.text = myOrders.date
            itemRvBinding.tvNumber.text = "Customer: ${myOrders.myCustomer?.name}"
            itemRvBinding.tvId.text = "Employee : ${myOrders.myEmployee?.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
    }

    override fun getItemCount(): Int {
        return list.size
    }
}