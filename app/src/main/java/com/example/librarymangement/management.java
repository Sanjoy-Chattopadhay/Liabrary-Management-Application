package com.example.librarymangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class management extends AppCompatActivity {
    Button btn_back,btn_data,btn_get,btn_issue,btn_return;
    TextView txt_cname,txt_phone,txt_issue,txt1_issue,txt2_issue;
    EditText edt_candidateid,edt_issue,txt_return;
    DatabaseHelping databasehelping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        btn_back=findViewById(R.id.btn_back);
        btn_data=findViewById(R.id.btn_data);
        btn_get=findViewById(R.id.btn_get);
        btn_issue=findViewById(R.id.btn_issue);
        btn_return=findViewById(R.id.btn_return);
        txt_cname=findViewById(R.id.txt_cname);
        txt_return=findViewById(R.id.txt_return);
        txt_phone=findViewById(R.id.txt_phone);
        txt_issue=findViewById(R.id.txt_issue);
        txt1_issue=findViewById(R.id.txt1_issue);
        txt2_issue=findViewById(R.id.txt2_issue);
        edt_candidateid=findViewById(R.id.edt_candidateid);
        edt_issue=findViewById(R.id.edt_issue);
        databasehelping=new DatabaseHelping(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(management.this,login.class);
                startActivity(back);
                finish();
            }
        });
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent handle=new Intent(management.this,datahandle.class);
                startActivity(handle);
                finish();
            }
        });
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean chk=databasehelping.cavi(edt_candidateid.getText().toString().trim());
                    if(chk) {

                        Cursor res = databasehelping.getcdata(edt_candidateid.getText().toString().trim());
                        while (res.moveToNext()) {
                            txt_cname.setText(res.getString(1));
                            txt_phone.setText(res.getString(2));
                            txt_issue.setText("1st book id-"+res.getString(3)+"\n");
                            txt1_issue.setText("2nd book id-"+res.getString(4)+"\n");
                            txt2_issue.setText("3rd book id-"+res.getString(5)+"\n");
                        }
                    }
                    else{
                        txt_cname.setText("");
                        txt_phone.setText("");
                        txt_issue.setText("");
                        txt_issue.setText("");
                        txt_issue.setText("");
                        Toast.makeText(management.this, "no such id available", Toast.LENGTH_LONG).show();

                    }
                }
                catch (Exception e){

                }
            }
        });
        btn_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_candidateid.getText().toString().trim().length()>=1) {
                    boolean chk=databasehelping.cavi(edt_candidateid.getText().toString().trim());
                    if(chk) {
                        boolean bookavi=databasehelping.bavi(edt_issue.getText().toString().trim());
                        if (bookavi) {
                            try {
                                boolean res = databasehelping.check(edt_candidateid.getText().toString().trim(), edt_issue.getText().toString().trim());
                                if (res) {
                                    Cursor bb = databasehelping.getcdata(edt_candidateid.getText().toString().trim());
                                    while (bb.moveToNext()) {
                                        txt_cname.setText(bb.getString(1));
                                        txt_phone.setText(bb.getString(2));
                                        txt_issue.setText("1st book id-" + bb.getString(3) + "\n");
                                        txt1_issue.setText("2nd book id-" + bb.getString(4) + "\n");
                                        txt2_issue.setText("3rd book id-" + bb.getString(5) + "\n");
                                        Toast.makeText(management.this, "sucessful", Toast.LENGTH_LONG).show();
                                    }
                                } else
                                    Toast.makeText(management.this, "You Have Issued three Book", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(management.this, " not sucessful", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                            Toast.makeText(management.this, " no such book available", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(management.this, " not such candidate in table", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(management.this, " id must be there", Toast.LENGTH_LONG).show();
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_candidateid.getText().toString().length()>=1) {
                    boolean chk=databasehelping.cavi(edt_candidateid.getText().toString().trim());
                    if (chk)
                    {
                        boolean chkissue=databasehelping.checkreturn(edt_candidateid.getText().toString().trim(),txt_return.getText().toString().trim());
                        if (chkissue)
                        {
                            try {
                                boolean res = databasehelping.returnb(edt_candidateid.getText().toString().trim(), null,txt_return.getText().toString().trim());
                                if (res) {
                                    Cursor bb = databasehelping.getcdata(edt_candidateid.getText().toString().trim());
                                    while (bb.moveToNext()) {
                                        txt_cname.setText(bb.getString(1));
                                        txt_phone.setText(bb.getString(2));
                                        txt_issue.setText("1st book id-" + bb.getString(3) + "\n");
                                        txt1_issue.setText("2nd book id-" + bb.getString(4) + "\n");
                                        txt2_issue.setText("3rd book id-" + bb.getString(5) + "\n");
                                        Toast.makeText(management.this, "returned", Toast.LENGTH_LONG).show();
                                    }
                                } else
                                    Toast.makeText(management.this, " not returned", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(management.this, " something wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                            Toast.makeText(management.this, "candidate have not issue this book", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(management.this, "no such candidate in data", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(management.this, " candidate id mus be there", Toast.LENGTH_LONG).show();
            }
        });

    }
}
