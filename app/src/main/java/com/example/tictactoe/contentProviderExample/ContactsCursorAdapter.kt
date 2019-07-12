package com.example.tictactoe.contentProviderExample

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.tictactoe.R
import com.example.tictactoe.rxjavaexample.utils.Constants

class ContactsCursorAdapter: CursorAdapter {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {

        return LayoutInflater.from(context).inflate(R.layout.contacts_list_item,parent,false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        var contactName = cursor?.getString(cursor.getColumnIndex(Constants.CONTACT_NAME))

        var contactsPhone = cursor?.getString(cursor.getColumnIndex(Constants.CONTACT_PHONE))

        var nameTextView = view?.findViewById<TextView>(R.id.nameTextView)
        var phoneTextView = view?.findViewById<TextView>(R.id.phoneTextView)

        if (nameTextView != null) {
            nameTextView.text = contactName
        }
        if (phoneTextView != null) {
            phoneTextView.text = contactsPhone
        }



    }

    constructor(context: Context, cursor: Cursor?, flags: Int):super(context,cursor,flags)

}