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
import com.collins.medilabsapp.models.Dependant
import com.collins.medilabsapp.models.Lab
import com.collins.medilabsapp.models.LabTests
import com.google.android.material.textview.MaterialTextView

class DependantAdapter (var context: Context):
    RecyclerView.Adapter<DependantAdapter.ViewHolder>() {


    //Create a list and connect it with our model
    var itemList : List<Dependant> = listOf() //Its empty

    //Create a class here, will hold our views in single_lab xml
    inner class ViewHolder(itemViews: View): RecyclerView.ViewHolder(itemViews)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DependantAdapter.ViewHolder {
        //access/inflate the single_lab xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_dependant,
            parent, false)

        return ViewHolder(view) //pass the single lab to ViewHolder
    }

    override fun onBindViewHolder(holder: DependantAdapter.ViewHolder, position: Int) {
        //Find your 3 text view
        val dep_name = holder.itemView.findViewById<MaterialTextView>(R.id.dep_name)
        val  dep_others = holder.itemView.findViewById<MaterialTextView>(R.id.dep_others)
        val  dep_dob = holder.itemView.findViewById<MaterialTextView>(R.id.dep_dob)
        // Assume one Lab
        val item = itemList[position]
        dep_name.text = item.surname
        dep_others.text = item.others
        dep_dob.text = item.dob
        holder.itemView.setOnClickListener {
            val i = Intent(context, SingleLabTest::class.java)
            i.putExtra("surname",item.surname)
            i.putExtra("others",item.others)
            i.putExtra("dob",item.dob)


            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size // Count how many Items in the list

    }

    fun filterList(filterList: List<Dependant>){
        itemList = filterList
        notifyDataSetChanged()
    }

    //Earlier we mentioned item List is Empty!
    // We will get data from our API, then bring it to below function
    fun setListItems(data: List<Dependant>){
        itemList = data //map/link the data to itemList
        notifyDataSetChanged()
    //Tell this adapter class that now itemList i loaded with data
    }
}