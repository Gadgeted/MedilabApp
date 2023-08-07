package com.collins.medilabsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collins.medilabsapp.LabTestsActivity
import com.collins.medilabsapp.R
import com.collins.medilabsapp.models.Lab
import com.google.android.material.textview.MaterialTextView

class LabAdapter (var context: Context):
    RecyclerView.Adapter<LabAdapter.ViewHolder>() {


    //Create a list and connect it with our model
    var itemList : List<Lab> = listOf() //Its empty

    //Create a class here, will hold our views in single_lab xml
    inner class ViewHolder(itemViews: View): RecyclerView.ViewHolder(itemViews)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabAdapter.ViewHolder {
        //access/inflate the single_lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_lab,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: LabAdapter.ViewHolder, position: Int) {
        //Find your 3 text view
        val lab_name = holder.itemView.findViewById<MaterialTextView>(R.id.lab_name)
        val  permit_id = holder.itemView.findViewById<MaterialTextView>(R.id.permit_id)
        val  email = holder.itemView.findViewById<MaterialTextView>(R.id.email)
        // Assume one Lab
        val lab = itemList[position]
        lab_name.text = lab.lab_name
        permit_id.text = lab.permit_id
        email.text = lab.email
        // When one lab is clicked, move to Lab tests Activity
        holder.itemView.setOnClickListener {
            //pASS THE iD OF LAB CLICKED
            // carry it with Bundles, Preference
            val id = lab.lab_id
            val name = lab.lab_name
            val i = Intent(context, LabTestsActivity::class.java)
            i.putExtra("key1", id)
            i.putExtra("key2", name)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)

        }

    }

    override fun getItemCount(): Int {
        return itemList.size // Count how many Items in the list

    }

    fun filterList(filterList: List<Lab>){
        itemList = filterList
        notifyDataSetChanged()
    }

    //Earlier we mentioned item List is Empty!
    // We will get data from our API, then bring it to below function
    fun setListItems(data: List<Lab>){
        itemList = data //map/link the data to itemList
        notifyDataSetChanged()
    //Tell this adapter class that now itemList i loaded with data
    }
}