package com.example.kutumbreadsms.data.source.phonebook

import android.content.Context
import android.database.Cursor
import android.net.Uri


abstract class PhoneBookService : Cursor {
    companion object {
        var uri = Uri.parse("content://sms/inbox")
        fun create(context: Context): Cursor? {
            return context.getContentResolver().query(uri, null, null, null, null);
        }
    }
}