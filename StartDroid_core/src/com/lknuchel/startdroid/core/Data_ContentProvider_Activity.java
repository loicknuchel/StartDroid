package com.lknuchel.startdroid.core;

import com.knuchel.startdroid.core.R;
import com.lknuchel.startdroid.data.contentprovider.AndroidProvider;
import com.lknuchel.startdroid.data.contentprovider.SharedInformation.Cours;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Data_ContentProvider_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_contentprovider);
		insertRecords();
		displayContentProvider();
	}

	private void displayContentProvider() {
		String columns[] = new String[] { Cours.COURS_ID, Cours.COURS_NAME, Cours.COURS_DESC };
		Uri mContacts = AndroidProvider.CONTENT_URI;
		Cursor cur = managedQuery(mContacts, columns, null, null, null);
		Toast.makeText(Data_ContentProvider_Activity.this, cur.getCount() + "",
				Toast.LENGTH_LONG).show();

		if (cur.moveToFirst()) {
			String name = null;
			do {
				name = cur.getString(cur.getColumnIndex(Cours.COURS_ID)) + " " +
						cur.getString(cur.getColumnIndex(Cours.COURS_NAME)) + " " + 
						cur.getString(cur.getColumnIndex(Cours.COURS_DESC));
				Toast.makeText(this, name + " ", Toast.LENGTH_LONG).show();
			} while (cur.moveToNext());
		}

	}

	private void insertRecords() {
		ContentValues contact = new ContentValues();
		contact.put(Cours.COURS_NAME, "Android");
		contact.put(Cours.COURS_DESC, "Introduction à la programmation sous Android");
		getContentResolver().insert(AndroidProvider.CONTENT_URI, contact);

		contact.clear();
		contact.put(Cours.COURS_NAME, "Java");
		contact.put(Cours.COURS_DESC, "Introduction à la programmation Java");
		getContentResolver().insert(AndroidProvider.CONTENT_URI, contact);

		contact.clear();
		contact.put(Cours.COURS_NAME, "Iphone");
		contact.put(Cours.COURS_DESC, "Introduction à l'objectif C");
		getContentResolver().insert(AndroidProvider.CONTENT_URI, contact);
	}
}