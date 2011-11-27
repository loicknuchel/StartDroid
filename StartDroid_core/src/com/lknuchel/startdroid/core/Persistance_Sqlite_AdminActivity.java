package com.lknuchel.startdroid.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.knuchel.startdroid.core.R;
import com.lknuchel.startdroid.persistance.sqlite.PeopleHelper;
import com.lknuchel.startdroid.persistance.sqlite.CompanyHelper;
import com.lknuchel.startdroid.model.People;
import com.lknuchel.startdroid.model.Company;

public class Persistance_Sqlite_AdminActivity extends Activity {
	private Context c;
	private Button fill;
	private Button drop;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persistance_sqlite_admin);
		c = getApplicationContext();
		setUp();
		onCLickValidate();
	}

	protected void setUp() {
		fill = (Button) findViewById(R.activity_demo_sqlite_admin.fill);
		drop = (Button) findViewById(R.activity_demo_sqlite_admin.drop);
	}

	private void onCLickValidate() {
		fill.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				fillDb();
			}
		});

		drop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dropDb();
			}
		});
	}

	private void dropDb() {
		CompanyHelper db = new CompanyHelper(c);
		db.open();
		db.deleteAll();
		db.close();

		PeopleHelper db2 = new PeopleHelper(c);
		db2.open();
		db2.deleteAll();
		db2.close();
	}

	private void fillDb() {
		CompanyHelper db = new CompanyHelper(c);
		db.open();
		db.insert(new Company("microsoft"));
		db.insert(new Company("apple"));
		db.insert(new Company("oracle"));
		db.close();

		PeopleHelper db2 = new PeopleHelper(c);
		db2.open();
		db2.insert(new People("loic", 1));
		db2.insert(new People("claude", 0));
		db2.insert(new People("michel", 5));
		db2.insert(new People("john", 2));
		db2.insert(new People("lola", 1));
		db2.close();
	}
}
