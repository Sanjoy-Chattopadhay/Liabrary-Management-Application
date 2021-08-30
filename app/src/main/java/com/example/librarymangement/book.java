package com.example.librarymangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class book extends AppCompatActivity {
    EditText edt_bid,edt_bname,edt_price;
    Button btn_add,btn_mang,btn_bsrch,btn_bdel,btn_bmodify;
    DatabaseHelping databasehelping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        edt_bid=findViewById(R.id.edt_bid);
        edt_bname=findViewById(R.id.edt_bname);
        edt_price=findViewById(R.id.edt_price);
        btn_add=findViewById(R.id.btn_add);
        btn_bsrch=findViewById(R.id.btn_bsrch);
        btn_bdel=findViewById(R.id.btn_bdel);
        btn_mang=findViewById(R.id.btn_mang);
        btn_bmodify=findViewById(R.id.btn_bmodify);

        databasehelping=new DatabaseHelping(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(condition()) {
                    RegisterUserModel books = new RegisterUserModel();
                    books.setbname(edt_bname.getText().toString().trim());
                    books.setprice(edt_price.getText().toString().trim());
                    databasehelping.registerBook(books);
                    Toast.makeText(book.this, "Book Registation sucessful", Toast.LENGTH_LONG).show();
                }
            }
            private boolean condition() {
                if(edt_bname.getText().toString().trim().length()>=3 && edt_price.getText().toString().trim().length()>=1){

                    return true;
                }
                else {
                    Toast.makeText(book.this,"book name must be 3 letter price can not blank",Toast.LENGTH_LONG).show();
                    return false;

                }
            }
        });
        btn_mang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mang=new Intent(book.this,datahandle.class);
                startActivity(mang);
                finish();
            }
        });
        btn_bsrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean bookavi=databasehelping.bavi(edt_bid.getText().toString().trim());
                    if (bookavi) {
                        try {
                            if (verify()) {
                                Cursor res = databasehelping.getbbdata(edt_bid.getText().toString().trim());
                                while (res.moveToNext()) {
                                    edt_bname.setText(res.getString(1));
                                    edt_price.setText(res.getString(2));
                                }

                            }


                        } catch (Exception e) {

                        }
                    }
                    else{
                        edt_bname.setText("");
                        edt_price.setText("");
                        Toast.makeText(book.this,"no such book is avilable",Toast.LENGTH_LONG).show();
                    }

            }

            public boolean verify() {
                if(edt_bid.getText().toString().trim().isEmpty()){
                    Toast.makeText(book.this," id must be there ",Toast.LENGTH_LONG).show();
                    return false;
                }
                else
                    return true;
            }
        });
        btn_bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()){

                    boolean bookavi=databasehelping.bavi(edt_bid.getText().toString().trim());
                    if (bookavi) {
                        try {
                            boolean checkdel = databasehelping.deletebdata(edt_bid.getText().toString().trim());
                            if (checkdel) {
                                Toast.makeText(book.this, "deletion sucess", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(book.this, "deletion  not sucess", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {

                        }
                    }
                    else{
                        edt_bname.setText("");
                        edt_price.setText("");
                        Toast.makeText(book.this,"no such book is avilable",Toast.LENGTH_LONG).show();
                    }


                }
            }
            public boolean verify() {
                if(edt_bid.getText().toString().trim().isEmpty()){
                    Toast.makeText(book.this," id must be there ",Toast.LENGTH_LONG).show();
                    return false;
                }
                else
                    return true;
            }
        });
        btn_bmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bookavi=databasehelping.bavi(edt_bid.getText().toString().trim());
                if (bookavi) {

                    if (verify() && edt_bname.getText().toString().trim().length()>=3 && edt_price.getText().toString().trim().length()>=1 ) {
                        try {
                            boolean chkm=databasehelping.bmodify(edt_bid.getText().toString().trim(), edt_bname.getText().toString().trim(), edt_price.getText().toString().trim());
                            if (chkm) {
                                Toast.makeText(book.this, "book modification sucessful", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(book.this, "book modification not sucessful", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(book.this, "book modification not sucessful", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(book.this,"Enter valid input",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    edt_bname.setText("");
                    edt_price.setText("");
                    Toast.makeText(book.this, "no such book is avilable", Toast.LENGTH_LONG).show();
                }
            }
            public boolean verify() {
                if(edt_bid.getText().toString().trim().isEmpty()){
                    Toast.makeText(book.this," id must be there ",Toast.LENGTH_LONG).show();
                    return false;
                }
                else
                    return true;
            }
        });

    }
}
