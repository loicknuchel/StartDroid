package com.lknuchel.startdroid.persistance.sqlite;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lknuchel.startdroid.model.People;

public class PeopleHelper extends SqliteAdapterImpl<People> {

	public PeopleHelper(Context context) {
		super(context);

		dbTable = SqliteConstants.PEOPLE_TABLE;
		idCol = SqliteConstants.PEOPLE_COL_ID;
	}

	public People getWithName(final String name) {
		Cursor c = mDb.query(dbTable, allColumns(),
				SqliteConstants.PEOPLE_COL_NAME + " LIKE \"" + name + "\"",
				null, null, null, null);
		return cursorToObject(c);
	}

	public List<People> getPeoplesLinkedWithCompany(final String companyName) {
		// SELECT * FROM People p LEFT OUTER JOIN Company c ON c.name =
		// p.companyName
		// WHERE c.name = name
		String p = SqliteConstants.PEOPLE_TABLE;
		String c = SqliteConstants.COMPANY_TABLE;
		String sql = "SELECT * FROM " + p + " LEFT OUTER JOIN " + c + " ON "
				+ c + "." + SqliteConstants.COMPANY_COL_NAME + " = " + p + "."
				+ SqliteConstants.PEOPLE_COL_COMPANYNAME + " WHERE " + c + "."
				+ SqliteConstants.COMPANY_COL_NAME + " = '" + companyName + "'";
		Cursor res = mDb.rawQuery(sql, null);
		return cursorToObjectList(res);
	}

	@Override
	protected ContentValues createValues(People val) {
		ContentValues values = new ContentValues();
		values.put(SqliteConstants.PEOPLE_COL_NAME, val.getName());
		values.put(SqliteConstants.PEOPLE_COL_COMPANYNAME, val.getCompanyName());
		return values;
	}

	@Override
	protected People affectCursor(final Cursor c) {
		People ret = new People();
		ret.setId(c.getInt(SqliteConstants.PEOPLE_NUM_COL_ID));
		ret.setName(c.getString(SqliteConstants.PEOPLE_NUM_COL_NAME));
		ret.setCompanyName(c.getString(SqliteConstants.PEOPLE_NUM_COL_COMPANYNAME));
		return ret;
	}

	@Override
	protected String[] allColumns() {
		return new String[] { SqliteConstants.PEOPLE_COL_ID,
				SqliteConstants.PEOPLE_COL_NAME,
				SqliteConstants.PEOPLE_COL_COMPANYNAME };
	}
}
