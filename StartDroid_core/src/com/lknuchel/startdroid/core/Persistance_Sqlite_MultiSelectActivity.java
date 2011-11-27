package com.lknuchel.startdroid.core;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.knuchel.startdroid.core.R;
import com.lknuchel.startdroid.model.People;
import com.lknuchel.startdroid.persistance.sqlite.PeopleHelper;

public class Persistance_Sqlite_MultiSelectActivity extends Activity {
	private Context c;
	private EditText nameEdit;
	private Button search;
	private ListView results;
	private List<String> ListViewString = null;
	private ArrayAdapter<String> ListViewAdapter = null;
	private ArrayList<People> ListViewValues = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perststance_sqlite_select);
		c = getApplicationContext();
		setUp();
		onCLickValidate();
	}

	@Override
	public void onResume() {
		super.onResume();
		doRequestAndDisplayList();
	}

	protected void setUp() {
		nameEdit = (EditText) findViewById(R.activity_demo_sqlite_select.nameEdit);
		search = (Button) findViewById(R.activity_demo_sqlite_select.search);
		results = (ListView) findViewById(R.activity_demo_sqlite_select.results);
	}

	private void onCLickValidate() {
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				doRequestAndDisplayList();
			}
		});
	}

	protected void doRequestAndDisplayList() {
		String companyName = nameEdit.getText().toString().trim();
		List<People> tmpRes = null;
		if (!companyName.equals("")) {
			PeopleHelper helper = new PeopleHelper(
					Persistance_Sqlite_MultiSelectActivity.this);
			helper.open();

			tmpRes = helper.getPeoplesLinkedWithCompany(companyName);

			helper.close();

			displayList(tmpRes, companyName);
		}
	}

	protected void displayList(List<People> tmpRes, String name) {
		ListViewString = new ArrayList<String>();
		ListViewValues = new ArrayList<People>();

		if (tmpRes != null) {
			for (People b : tmpRes) {
				ListViewString.add(name + " - " + b.getName() + " (id: "
						+ b.getId() + ")");
				ListViewValues.add(b);
			}
		} else {
			Toast.makeText(c, "No results found !!!", Toast.LENGTH_LONG).show();
		}

		ListViewAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ListViewString);
		results.setAdapter(ListViewAdapter);
		ListViewAdapter.notifyDataSetChanged();
	}
}
