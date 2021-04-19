package kr.co.softcampus.playground2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.playground2.models.Contact


class ContactRecyclerAdapter : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>(){

    private var mCheckedMap = HashMap<Contact,Boolean>()
    private var mItems : List<Contact> = ArrayList()
    fun setItems(itmes : List<Contact>){
        mItems = itmes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact,parent,false)

        val viewHolder : ContactViewHolder = ContactViewHolder(view)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(contactViewHolder: ContactRecyclerAdapter.ContactViewHolder, position: Int) {

        val contact : Contact = mItems[position]
        contactViewHolder.pictureImageView.setImageResource(R.mipmap.ic_launcher)
        contactViewHolder.nameTextView.text = contact.getName()

        val isChecked : Boolean? = mCheckedMap.get(contact)
        contactViewHolder.checkBox.isChecked


    }
    override fun getItemCount(): Int {
        return mItems.size
    }

    //ViewHolder
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemView = itemView as ConstraintLayout

        val pictureImageView = itemView.findViewById<ImageView>(R.id.pictureImageView)!!
        val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        val checkBox : CheckBox = itemView.findViewById(R.id.checkBox)





    }


}
