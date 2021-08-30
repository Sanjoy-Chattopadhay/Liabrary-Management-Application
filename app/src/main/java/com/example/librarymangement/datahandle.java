package com.example.librarymangement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class datahandle extends AppCompatActivity {
    Button btn_book,btn_candidate,btn_backm,btn_candidatelist,btn_admin,btn_booklist;
    DatabaseHelping databasehelping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datahandle);
        btn_book=findViewById(R.id.btn_book);
        btn_candidate=findViewById(R.id.btn_candidate);
        btn_backm=findViewById(R.id.btn_backm);
        btn_admin=findViewById(R.id.btn_admin);
        btn_booklist=findViewById(R.id.btn_booklist);
        btn_candidatelist=findViewById(R.id.btn_candidatelist);
        databasehelping=new DatabaseHelping(this);
        btn_backm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mang=new Intent(datahandle.this,management.class);
                startActivity(mang);
                finish();
            }
        });
        btn_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent candidate=new Intent(datahandle.this,candidate.class);
                startActivity(candidate);
                finish();
            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent book=new Intent(datahandle.this,book.class);
                startActivity(book);
                finish();
            }
        });
        btn_candidatelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=databasehelping.getdata();
                if (res.getCount()==0){
                    Toast.makeText(datahandle.this,"no Entry",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("phone:"+res.getString(2)+"\n");
                    buffer.append("1st book:"+res.getString(3)+"\n");
                    buffer.append("2nd book:"+res.getString(4)+"\n");
                    buffer.append("3rd book:"+res.getString(5)+"\n\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(datahandle.this);
                builder.setCancelable(true);
                builder.setTitle("candidate list");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        btn_booklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=databasehelping.getbdata();
                if (res.getCount()==0){
                    Toast.makeText(datahandle.this,"no Entry",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id:"+res.getString(0)+"\n");
                    buffer.append("Book name:"+res.getString(1)+"\n");
                    buffer.append("price:"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(datahandle.this);
                builder.setCancelable(true);
                builder.setTitle("book list");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=databasehelping.getadmin();
                if (res.getCount()==0){
                    Toast.makeText(datahandle.this,"no Entry",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer xyz=new StringBuffer();
                while (res.moveToNext()){
                    xyz.append("id:"+res.getString(0)+"\n");
                    xyz.append("username:"+res.getString(1)+"\n");
                    xyz.append("name:"+res.getString(2)+"\n");
                    xyz.append("password:"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(datahandle.this);
                builder.setCancelable(true);
                builder.setTitle("admin list");
                builder.setMessage(xyz.toString());
                builder.show();

            }
        });

    }
}
