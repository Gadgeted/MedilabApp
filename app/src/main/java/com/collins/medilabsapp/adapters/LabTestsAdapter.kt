package com.collins.medilabsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.collins.medilabsapp.LabTestsActivity
import com.collins.medilabsapp.R
import com.collins.medilabsapp.SingleLabTest
import com.collins.medilabsapp.models.Lab
import com.collins.medilabsapp.models.LabTests
import com.google.android.material.textview.MaterialTextView

class LabTestsAdapter (var context: Context):
    RecyclerView.Adapter<LabTestsAdapter.ViewHolder>() {


    //Create a list and connect it with our model
    var itemList : List<LabTests> = listOf() //Its empty

    //Create a class here, will hold our views in single_lab xml
    inner class ViewHolder(itemViews: View): RecyclerView.ViewHolder(itemViews)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabTestsAdapter.ViewHolder {
        //access/inflate the single_lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_labtests,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: LabTestsAdapter.ViewHolder, position: Int) {
        //Find your 3 text view
        val test_name = holder.itemView.findViewById<MaterialTextView>(R.id.test_name)
        val  test_description = holder.itemView.findViewById<MaterialTextView>(R.id.test_description)
        val  test_cost = holder.itemView.findViewById<MaterialTextView>(R.id.test_cost)
        // Assume one Lab
        val item = itemList[position]
        test_name.text = item.test_name
        test_description.text = item.test_description
        test_cost.text = item.test_cost+"KES"
        holder.itemView.setOnClickListener {
            val i = Intent(context, SingleLabTest::class.java)
            i.putExtra("lab_id",item.lab_id)
            i.putExtra("test_id",item.test_id)
            i.putExtra("test_discount",item.test_discount)
            i.putExtra("test_cost",item.test_cost)
            i.putExtra("test_name",item.test_name)
            i.putExtra("test_description",item.test_description)
            i.putExtra("availability",item.availability)
            i.putExtra("more_info",item.more_info)
            i.putExtra("reg_date",item.reg_date)

            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size // Count how many Items in the list

    }

    fun filterList(filterList: List<LabTests>){
        itemList = filterList
        notifyDataSetChanged()
    }

    //Earlier we mentioned item List is Empty!
    // We will get data from our API, then bring it to below function
    fun setListItems(data: List<LabTests>){
        itemList = data //map/link the data to itemList
        notifyDataSetChanged()
    //Tell this adapter class that now itemList i loaded with data
    }
}