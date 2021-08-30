package com.example.librarymangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class candidate extends AppCompatActivity {
    EditText edt_canme,edt_cid,edt_phone;
    Button btn_cadd,btn_update,btn_man,btn_csrch,btn_cdel;
    DatabaseHelping databasehelping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);
        edt_canme=findViewById(R.id.edt_cname);
        edt_cid=findViewById(R.id.edt_cid);
        edt_phone=findViewById(R.id.edt_phone);
        btn_cadd=findViewById(R.id.btn_cadd);
        btn_man=findViewById(R.id.btn_man);
        btn_update=findViewById(R.id.btn_update);
        btn_csrch=findViewById(R.id.btn_csrch);
        btn_cdel=findViewById(R.id.btn_cdel);
        databasehelping=new DatabaseHelping(this);

        btn_cadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(condition()) {
                    RegisterUserModel candidate = new RegisterUserModel();
                    candidate.setcname(edt_canme.getText().toString().trim());
                    candidate.setphone(edt_phone.getText().toString().trim());
                    databasehelping.registerCandidate(candidate);


                    Toast.makeText(candidate.this, "Student Registation sucessful", Toast.LENGTH_LONG).show();
                }


            }

            private boolean condition() {
                if(edt_canme.getText().toString().trim().length()>=3 && edt_phone.getText().toString().trim().length()==10){
                    Toast.makeText(candidate.this,"Entery done",Toast.LENGTH_LONG).show();
                    return true;
                }
                else {
                    Toast.makeText(candidate.this,"Enter valid detail",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chk=databasehelping.cavi(edt_cid.getText().toString().trim());
                if (chk) {
                    try {
                        if(edt_cid.getText().toString().trim().length()>=1 && edt_canme.getText().toString().trim().length()>=3 && edt_phone.getText().toString().trim().length()==10) {
                            databasehelping.modify(edt_cid.getText().toString().trim(), edt_canme.getText().toString().trim(), edt_phone.getText().toString().trim());
                            Toast.makeText(candidate.this, "candidate modification sucessful", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(candidate.this, "Candidate modification not sucessful", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                    }
                }
                else
                    Toast.makeText(candidate.this, "not such candidate available", Toast.LENGTH_LONG).show();


            }
        });
        btn_cdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean chk=databasehelping.cavi(edt_cid.getText().toString().trim());
                    if (chk) {
                        boolean checkdel = databasehelping.deletedata(edt_cid.getText().toString().trim());
                        if (checkdel) {
                            Toast.makeText(candidate.this, "deletion sucess", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(candidate.this, "deletion  not sucess", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                        Toast.makeText(candidate.this, "no such candidate is available", Toast.LENGTH_LONG).show();

                }
                catch (Exception e){

                }


            }
        });
        btn_csrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean chk=databasehelping.cavi(edt_cid.getText().toString().trim());
                    if(chk) {

                        Cursor res = databasehelping.getcdata(edt_cid.getText().toString().trim());
                        while (res.moveToNext()) {
                            edt_canme.setText(res.getString(1));
                            edt_phone.setText(res.getString(2));
                        }
                    }
                    else {
                        edt_canme.setText("");
                        edt_phone.setText("");
                        Toast.makeText(candidate.this, "no such id available", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){

                }
            }
        });
        btn_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mang=new Intent(candidate.this,datahandle.class);
                startActivity(mang);
                finish();
            }
        });


    }
}
