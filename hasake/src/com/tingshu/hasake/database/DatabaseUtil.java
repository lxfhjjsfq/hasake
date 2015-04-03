package com.tingshu.hasake.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtil {

	private static DatabaseUtil mInstance;
	private static SQLiteDatabase mDatabase;

	// 音乐下载
	public static final String DOWN_TABLE = "DOWN_TABLE";
	public static final String DOWN_ID = "_id";
	public static final String DOWN_ALBUM = "album";
	public static final String DOWN_KIND = "kind";
	public static final String DOWN_NAME = "name";
	public static final String DOWN_PIC = "pic";
	public static final String DOWN_URL = "downUrl";
	public static final String DOWN_PATH = "path";
	public static final String DOWN_STATE = "state";
	public static final String DOWN_CUR = "cur";
	public static final String DOWN_TOTAL = "total";

	public static DatabaseUtil getInstance() {
		if (mInstance == null) {
			throw new IllegalArgumentException(
					"没有初始化数据库，请调用DatabaseUtil.initDatabase()");
		}
		return mInstance;
	}

	public static void initDatabase(Context context) {
		if (mInstance == null) {
			mInstance = new DatabaseUtil(context);
		}
	}

	private DatabaseUtil(Context context) {
		DataBaseHelper mdBHelper = new DataBaseHelper(context);
		mDatabase = mdBHelper.getWritableDatabase();
		mDatabase.execSQL(createDownloadTable());
	}

	public String createDownloadTable() {
		return "CREATE TABLE " + "IF NOT EXISTS " + DOWN_TABLE + " ( "
				+ DOWN_ID + " INTEGER PRIMARY KEY," + DOWN_ALBUM + " TEXT,"
				+ DOWN_KIND + " TEXT," + DOWN_NAME + " TEXT," + DOWN_PIC
				+ " TEXT," + DOWN_URL + " TEXT," + DOWN_PATH + " TEXT,"
				+ DOWN_STATE + " TEXT," + DOWN_CUR + " INTEGER," + DOWN_TOTAL
				+ " INTEGER)";
	}

	public long insertCache(DownloadDao dao) {
		if (isDownloadExist(dao)) {
			return -1;
		}
		ContentValues map = new ContentValues();
		map.put(DOWN_ALBUM, dao.getAlbum());
		map.put(DOWN_KIND, dao.getKind());
		map.put(DOWN_NAME, dao.getName());
		map.put(DOWN_PIC, dao.getPic());
		map.put(DOWN_URL, dao.getDownUrl());
		map.put(DOWN_PATH, dao.getPath());
		map.put(DOWN_STATE, dao.getState());
		map.put(DOWN_CUR, dao.getCurLength());
		map.put(DOWN_TOTAL, dao.getTotalLength());
		return mDatabase.insert(DOWN_TABLE, DOWN_ID, map);
	}

	public int updateCache(DownloadDao dao) {
		ContentValues map = new ContentValues();
		map.put(DOWN_ALBUM, dao.getAlbum());
		map.put(DOWN_KIND, dao.getKind());
		map.put(DOWN_NAME, dao.getName());
		map.put(DOWN_PIC, dao.getPic());
		map.put(DOWN_URL, dao.getDownUrl());
		map.put(DOWN_PATH, dao.getPath());
		map.put(DOWN_STATE, dao.getState());
		map.put(DOWN_CUR, dao.getCurLength());
		map.put(DOWN_TOTAL, dao.getTotalLength());
		return mDatabase.update(DOWN_TABLE, map, (DOWN_ID + "=" + dao.getId()),
				null);
	}

	public List<DownloadDao> getCacheList() {
		ArrayList<DownloadDao> list = new ArrayList<DownloadDao>();
		Cursor cursor = mDatabase.query(DOWN_TABLE, null, null, null, null,
				null, null);
		try {

			while (cursor != null && cursor.moveToNext()) {
				int cid = cursor.getColumnIndex(DOWN_ID);
				int id = cursor.getInt(cid);
				cid = cursor.getColumnIndex(DOWN_ALBUM);
				String album = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_KIND);
				String kind = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_NAME);
				String name = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_PIC);
				String pic = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_URL);
				String url = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_PATH);
				String path = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_STATE);
				String state = cursor.getString(cid);
				cid = cursor.getColumnIndex(DOWN_CUR);
				long cur = cursor.getLong(cid);
				cid = cursor.getColumnIndex(DOWN_TOTAL);
				long total = cursor.getLong(cid);
				DownloadDao dao = new DownloadDao(id, album, kind, name, pic,
						url, path, state, cur, total);
				list.add(dao);
			}
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	public void deleteCache(String url) {
		mDatabase.delete(DOWN_TABLE,
				DOWN_URL + "=?", new String[] { url });
	}

	public void deleteCaches(String[] urls) {
		mDatabase.delete(DOWN_TABLE,
				DOWN_URL + " in", urls);
	}

	public boolean isDownloadExist(DownloadDao dao) {
		String sql = "select * from " + DOWN_TABLE + " where " + DOWN_URL
				+ "=?";
		try {
			Cursor cursor = mDatabase.rawQuery(sql,
					new String[] { dao.getDownUrl() });
			if (cursor.moveToFirst()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
