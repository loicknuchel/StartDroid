package com.lknuchel.startdroid.data.contentprovider;

import android.provider.BaseColumns;

public class SharedInformation {
	public SharedInformation() {
	}

	public static final class Cours implements BaseColumns {
		private Cours() {
		}

		public static final String COURS_ID = "COURS";
		public static final String COURS_NAME = "COURS_NAME";
		public static final String COURS_DESC = "COURS_DESC";
	}
}
