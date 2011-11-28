package com.lknuchel.startdroid.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.knuchel.startdroid.core.R;
import com.lknuchel.startdroid.persistance.sqlite.SqliteConstants;
import com.lknuchel.startdroid.persistance.sqlite.PeopleHelper;
import com.lknuchel.startdroid.persistance.sqlite.CompanyHelper;
import com.lknuchel.startdroid.model.People;
import com.lknuchel.startdroid.model.Company;

public class Persistance_Sqlite_CRUDActivity extends Activity {
	private Context c;
	private EditText nameEdit;
	private EditText companyNameEdit;
	private Button createBtn;
	private ListView resultListView;
	private String intentData = "people";
	private List<Company> companyList = null;
	private List<People> peopleList = null;
	private List<String> keyValueListString = null;
	private ArrayAdapter<String> keyValueListAdapter = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persistance_sqlite_crud);
		c = getApplicationContext();
		intentData = getIntent().getStringExtra("objetType");
		setUp();
		onCLickValidate();
		// testsReflexivite();
	}

	@Override
	public void onResume() {
		super.onResume();
		displayList(intentData);
	}

	protected void setUp() {
		nameEdit = (EditText) findViewById(R.activity_persistance_sqlite_crud.nameEdit);
		companyNameEdit = (EditText) findViewById(R.activity_persistance_sqlite_crud.companyNameEdit);
		createBtn = (Button) findViewById(R.activity_persistance_sqlite_crud.createBtn);
		resultListView = (ListView) findViewById(R.activity_persistance_sqlite_crud.keyValueList);
		if (intentData.equals("people")) {
			nameEdit.setHint(getResources().getString(R.string.DSCA_peoplename));
			companyNameEdit.setHint(getResources().getString(
					R.string.DSCA_companyname));
		} else {
			nameEdit.setHint(getResources()
					.getString(R.string.DSCA_companyname));
			companyNameEdit.setVisibility(View.GONE);
		}
	}

	private void onCLickValidate() {
		createBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				saveInSqlite(nameEdit.getText().toString(), companyNameEdit
						.getText().toString(), intentData);
				nameEdit.setText("");
				companyNameEdit.setText("");
				displayList(intentData);

				if (intentData.equals("people")) {
					Toast.makeText(
							c,
							getResources().getString(R.string.DSCA_people)
									+ " "
									+ getResources().getString(
											R.string.DSCA_created),
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(
							c,
							getResources().getString(R.string.DSCA_company)
									+ " "
									+ getResources().getString(
											R.string.DSCA_created),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		resultListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> a, final View v,
					final int position, final long id) {
				int objId;
				if (intentData.equals("people")) {
					objId = peopleList.get(position).getId();
				} else {
					objId = companyList.get(position).getId();
				}
				Toast.makeText(
						c,
						getResources().getString(R.string.DSCA_positionClicked)
								+ " " + position + " ("
								+ getResources().getString(R.string.DSCA_id)
								+ " " + objId + ")", Toast.LENGTH_SHORT).show();
			}
		});

		resultListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, final int position, long id) {
						if (intentData.equals("people")) {
							Toast.makeText(
									c,
									companyList.get(position).getName()
											+ " "
											+ getResources().getString(
													R.string.DSCA_removed)
											+ ".", Toast.LENGTH_SHORT).show();
							removeInSqlite(companyList.get(position).getId(),
									intentData);
						} else {
							Toast.makeText(
									c,
									peopleList.get(position).getName()
											+ " "
											+ getResources().getString(
													R.string.DSCA_removed)
											+ ".", Toast.LENGTH_SHORT).show();
							removeInSqlite(peopleList.get(position).getId(),
									intentData);

						}
						displayList(intentData);
						return true;
					}
				});
	}

	private void saveInSqlite(String name, String companyName, String type) {
		if (type.equals("people")) {
			People val = new People(name, companyName);
			PeopleHelper helper = new PeopleHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();

			People b = helper.getWithName(val.getName());
			if (b != null) {
				helper.delete(b.getId());
			}

			helper.insert(val);

			helper.close();
		} else {
			Company val = new Company(name);
			CompanyHelper helper = new CompanyHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();

			Company b = helper.getWithName(val.getName());
			if (b != null) {
				helper.delete(b.getId());
			}

			helper.insert(val);

			helper.close();
		}
	}

	private boolean removeInSqlite(long id, String type) {
		if (type.equals("keyValue")) {
			CompanyHelper helper = new CompanyHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();
			helper.delete(id);
			helper.close();
		} else {

			PeopleHelper helper = new PeopleHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();
			helper.delete(id);
			helper.close();
		}
		return true;
	}

	private void displayList(String type) {
		List<Company> tmpList = null;
		List<People> tmpList2 = null;

		keyValueListString = new ArrayList<String>();
		companyList = new ArrayList<Company>();
		peopleList = new ArrayList<People>();

		if (type.equals("people")) {
			PeopleHelper helper = new PeopleHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();

			tmpList2 = helper.getAll(SqliteConstants.PEOPLE_COL_NAME + " DESC",
					null);

			helper.close();

			if (tmpList2 != null) {
				for (People b : tmpList2) {
					keyValueListString.add(b.getId() + ". " + b.getName()
							+ " - " + b.getCompanyName());
					peopleList.add(b);
				}
			}
		} else {
			CompanyHelper helper = new CompanyHelper(
					Persistance_Sqlite_CRUDActivity.this);
			helper.open();

			tmpList = helper.getAll(SqliteConstants.COMPANY_COL_NAME + " DESC",
					null);

			helper.close();

			if (tmpList != null) {
				for (Company b : tmpList) {
					keyValueListString.add(b.getId() + ". " + b.getName());
					companyList.add(b);
				}
			}
		}

		keyValueListAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, keyValueListString);
		resultListView.setAdapter(keyValueListAdapter);
		keyValueListAdapter.notifyDataSetChanged();
	}

	private void testsReflexivite() {
		Company tmp = new Company();
		// Toast.makeText(c,
		// "CanonicalName: "+tmp.getClass().getCanonicalName(),
		// Toast.LENGTH_LONG).show();
		// Class cl = KeyValue.class;
		Class cl = People.class;

		Field fs[] = cl.getDeclaredFields();
		Toast.makeText(c, fs.length + " field found", Toast.LENGTH_LONG).show();
		for (Field f : fs) {
			Toast.makeText(
					c,
					"field: " + f.getName() + " / type: "
							+ f.getGenericType().toString(), Toast.LENGTH_LONG)
					.show();
		}

		Class[] interfaces = cl.getInterfaces();
		Toast.makeText(c, interfaces.length + " interface found",
				Toast.LENGTH_LONG).show();
		for (Class inter : interfaces) {
			Toast.makeText(c, "interface: " + inter.getName(),
					Toast.LENGTH_LONG).show();
		}
	}
}
