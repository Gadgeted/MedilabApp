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
import com.collins.medilabsapp.helpers.SQLiteCartHelper
import com.collins.medilabsapp.models.Lab
import com.collins.medilabsapp.models.LabTests
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class LabTestsCartAdapter (var context: Context):
    RecyclerView.Adapter<LabTestsCartAdapter.ViewHolder>() {


    //Create a list and connect it with our model
    var itemList : List<LabTests> = listOf() //Its empty

    //Create a class here, will hold our views in single_lab xml
    inner class ViewHolder(itemViews: View): RecyclerView.ViewHolder(itemViews)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabTestsCartAdapter.ViewHolder {
        //access/inflate the single_lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_labtests_cart,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: LabTestsCartAdapter.ViewHolder, position: Int) {
        //Find your 3 text view
        val test_name = holder.itemView.findViewById<MaterialTextView>(R.id.test_name)
        val  test_description = holder.itemView.findViewById<MaterialTextView>(R.id.test_description)
        val  test_cost = holder.itemView.findViewById<MaterialTextView>(R.id.test_cost)
        // Assume one Lab
        val item = itemList[position]
        test_name.text = item.test_name
        test_description.text = item.test_description
        test_cost.text = item.test_cost+"KES"

        val remove = holder.itemView.findViewById<MaterialButton>(R.id.remove)
        remove.setOnClickListener {
            val test_id = item.test_id
            val helper = SQLiteCartHelper(context)
            helper.clearCartById(test_id)
            //Item Removed
            //Go tyo Helper an reload MyCart Activity in clearCartById fun
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