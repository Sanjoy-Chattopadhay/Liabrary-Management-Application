package com.example.librarymangement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelping extends SQLiteOpenHelper {
    public static final String dbName = "libMang.db";
    public static final int dbVersion = 1;

    public static final String tableName = "adminTable";

    public static final String column_Id="admin_id";
    public static final String column_Name="admin_name";
    public static final String column_Username="admin_username";
    public static final String column_Password="admin_password";

    public static final String tablec = "candidateTable";

    public static final String cid="candidate_id";
    public static final String cname="candidate_name";
    public static final String phone="candidate_username";
    public static final String fbook="fbook";
    public static final String sbook="sbook";
    public static final String tbook="tbook";

    public static final String tableb = "bookTable";

    public static final String bid="book_id";
    public static final String bname="book_name";
    public static final String price="book_price";

    Context context;

    public DatabaseHelping(@Nullable Context context) {
        super(context, dbName, null, dbVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+" ("+column_Id+" integer primary key autoincrement, "+column_Name +" text, "+
                column_Username +" varchar, "+column_Password +" varchar)");
        db.execSQL("create table "+tablec+" ("+cid +" integer primary key autoincrement, "+cname +" text, "+phone +" varchar, "+fbook +" text, "+sbook +" text, "+tbook +" text)");
        db.execSQL("create table "+tableb+" ("+bid +" integer primary key autoincrement, "+bname +" text, "+price +" varchar)");


    }



        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tableName);
            db.execSQL("drop table if exists "+tablec);
            db.execSQL("drop table if exists "+tableb);



    }

    public void registerUser(RegisterUserModel registerUserModel){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name,registerUserModel.getName());
        contentValues.put(column_Username,registerUserModel.getusername());
        contentValues.put(column_Password,registerUserModel.getPassword());

        database.insert(tableName,null,contentValues);
    }
    public boolean loginUser(String email,String password){
        String[] columns = {column_Id};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = column_Username + " = ? " + " AND " + column_Password + " = ? ";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(tableName, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }
    public Cursor getadmin(){
        SQLiteDatabase admin = getWritableDatabase();
        Cursor cursor = admin.rawQuery("Select * From adminTable",null);
        return cursor;


    }


//    //registation of candidate and more

    public void registerCandidate(RegisterUserModel registerUserModel) {
        SQLiteDatabase candidate = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cname, registerUserModel.getcname());
        contentValues.put(phone, registerUserModel.getphone());
        candidate.insert(tablec, null, contentValues);
    }
    //modify data of candidate
    public void modify(String id,String name,String phone) {
        SQLiteDatabase candidate=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("candidate_id",id);
        contentValues.put("candidate_name",name);
        contentValues.put("candidate_username",phone);
        candidate.update(tablec, contentValues,"candidate_id = ? ", new String[]{id});
        candidate.close();
    }


    //delete candidate
    public boolean deletedata(String vid){
        SQLiteDatabase candidate=this.getWritableDatabase();
        String qry="select * from "+tablec+" where candidate_id="+vid;
        Cursor cursor=candidate.rawQuery(qry,null);
        if (cursor.getCount()>0){
            candidate.delete("candidateTable","candidate_id=?",new String[]{vid});
            return true;
        }
        else {
            return false;
        }
    }


    public Cursor getdata(){
        SQLiteDatabase candi = getWritableDatabase();
        Cursor cursor = candi.rawQuery("Select * From candidateTable",null);
        return cursor;
    }
    //get data rows data
    public Cursor getcdata(String vid){
        SQLiteDatabase candidate = this.getWritableDatabase();
        String qry="select * from "+tablec+" where candidate_id="+vid;
        Cursor cursor=candidate.rawQuery(qry,null);
        return cursor;
    }


 //   //record mainting of book
    public void registerBook(RegisterUserModel registerUserModel) {
        SQLiteDatabase book = getWritableDatabase();
        ContentValues books = new ContentValues();
        books.put(bname, registerUserModel.getbname());
        books.put(price, registerUserModel.getprice());
        book.insert(tableb, null, books);
    }
    public Cursor getbdata(){
        SQLiteDatabase book = getWritableDatabase();
        Cursor cursor = book.rawQuery("Select * From bookTable",null);
        return cursor;
    }
    public Cursor getbbdata(String vid){
        SQLiteDatabase book = this.getWritableDatabase();
        String qry="select * from "+tableb+" where book_id="+vid;
        Cursor cursor=book.rawQuery(qry,null);
        return cursor;
    }
    public boolean deletebdata(String vid){
        SQLiteDatabase book=this.getWritableDatabase();
        String qry="select * from "+tableb+" where book_id="+vid;
        Cursor cursor=book.rawQuery(qry,null);
        if (cursor.getCount()>0){
            book.delete("bookTable","book_id=?",new String[]{vid});
            return true;
        }
        else {
            return false;
        }
    }
    public boolean bmodify(String id,String name,String price) {
        SQLiteDatabase book=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("book_id",id);
        contentValues.put("book_name",name);
        contentValues.put("book_price",price);
        Cursor cursor=book.rawQuery("Select * From bookTable where book_id=?", new String[]{id});
        if (cursor.getCount()>0) {
            book.update(tableb, contentValues, "book_id = ? ", new String[]{id});
            book.close();
            return true;
        }
        else
            return false;
    }
    public boolean bavi(String id){
        String[] columns = {bid};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = bid + " = ? ";

        String[] selectionArgs = {id};

        Cursor cursor = db.query(tableb, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }




    ///issue and return

    public boolean check(String id,String book){
        String[] columns = {cid};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String selection = cid + " = ? " + " AND " + fbook + " IS NULL ";
        String[] selectionArgs = {id};
        Cursor cursor = db.query(tablec, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();


        String sselection = cid + " = ? " + " AND " + sbook + " IS NULL ";
        String[] sselectionArgs = {id};
        Cursor scursor = db.query(tablec, columns, sselection, sselectionArgs,  null,null, null);
        int scursorCount = scursor.getCount();

        String tselection = cid + " = ? " + " AND " + tbook + " IS NULL ";
        String[] tselectionArgs = {id};
        Cursor tcursor = db.query(tablec, columns, tselection, tselectionArgs,  null,null, null);
        int tcursorCount = tcursor.getCount();



        if (cursorCount > 0) {
            contentValues.put("fbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;
        }
        else if (scursorCount > 0){
            contentValues.put("sbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;

        }
        else if (tcursorCount > 0){
            contentValues.put("tbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;

        }
        return false;

    }
    public boolean cavi(String id){
        String[] columns = {cid};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = cid + " = ? ";

        String[] selectionArgs = {id};

        Cursor cursor = db.query(tablec, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }
    public boolean returnb(String id,String book,String rbook){
        String[] columns = {cid};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String selection = cid + " = ? " + " AND " + fbook + " = ? ";
        String[] selectionArgs = {id,rbook};
        Cursor cursor = db.query(tablec, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();


        String sselection = cid + " = ? " + " AND " + sbook + " = ? ";
        String[] sselectionArgs = {id,rbook};
        Cursor scursor = db.query(tablec, columns, sselection, sselectionArgs,  null,null, null);
        int scursorCount = scursor.getCount();

        String tselection = cid + " = ? " + " AND " + tbook + " = ? ";
        String[] tselectionArgs = {id,rbook};
        Cursor tcursor = db.query(tablec, columns, tselection, tselectionArgs,  null,null, null);
        int tcursorCount = tcursor.getCount();



        if (cursorCount > 0) {
            contentValues.put("fbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;
        }
        else if (scursorCount > 0){
            contentValues.put("sbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;

        }
        else if (tcursorCount > 0){
            contentValues.put("tbook",book);
            db.update(tablec,contentValues,"candidate_id = ? ",new String[]{id});
            return true;

        }
        return false;

    }
    public boolean checkreturn(String id,String book){
        String[] columns = {cid};
        SQLiteDatabase db = this.getWritableDatabase();


        String selection = cid + " = ? " + " AND " + fbook + " = ? ";
        String[] selectionArgs = {id,book};
        Cursor cursor = db.query(tablec, columns, selection, selectionArgs,  null,null, null);
        int cursorCount = cursor.getCount();


        String sselection = cid + " = ? " + " AND " + sbook + " = ? ";
        String[] sselectionArgs = {id,book};
        Cursor scursor = db.query(tablec, columns, sselection, sselectionArgs,  null,null, null);
        int scursorCount = scursor.getCount();

        String tselection = cid + " = ? " + " AND " + tbook + " = ? ";
        String[] tselectionArgs = {id,book};
        Cursor tcursor = db.query(tablec, columns, tselection, tselectionArgs,  null,null, null);
        int tcursorCount = tcursor.getCount();



        if (cursorCount > 0) {
            return true;
        }
        else if (scursorCount > 0){
            return true;

        }
        else if (tcursorCount > 0){
            return true;
        }
        return false;

    }


}
