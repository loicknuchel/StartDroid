package com.lknuchel.startdroid.persistance.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lknuchel.startdroid.model.Company;

public class CompanyHelper extends SqliteAdapterImpl<Company> {

	public CompanyHelper(Context context) {
		super(context);
		dbTable = SqliteConstants.COMPANY_TABLE;
		idCol = SqliteConstants.COMPANY_COL_ID;
	}

	public Company getWithName(final String key) {
		Cursor c = mDb.query(dbTable, allColumns(),
				SqliteConstants.COMPANY_COL_NAME + " LIKE \"" + key + "\"",
				null, null, null, null);
		return cursorToObject(c);
	}

	@Override
	protected ContentValues createValues(Company val) {
		ContentValues values = new ContentValues();
		values.put(SqliteConstants.COMPANY_COL_NAME, val.getName());
		return values;
	}

	@Override
	protected Company affectCursor(final Cursor c) {
		Company keyValue = new Company();
		keyValue.setId(c.getInt(SqliteConstants.COMPANY_NUM_COL_ID));
		keyValue.setName(c.getString(SqliteConstants.COMPANY_NUM_COL_NAME));
		return keyValue;
	}

	@Override
	protected String[] allColumns() {
		return new String[] { SqliteConstants.COMPANY_COL_ID,
				SqliteConstants.COMPANY_COL_NAME };
	}
}
