package com.example.librarymangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registation extends AppCompatActivity {
    EditText edt_name,edt_username,edt_password;
    Button btn_register,btn_retur;
    DatabaseHelping databaseHelping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        btn_register=findViewById(R.id.btn_register);
        edt_name=findViewById(R.id.edt_name);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        btn_retur=findViewById(R.id.btn_retur);
        databaseHelping = new DatabaseHelping(this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    RegisterUserModel model = new RegisterUserModel();
                    model.setName(edt_name.getText().toString().trim());
                    model.setusername(edt_username.getText().toString().trim());
                    model.setPassword(edt_password.getText().toString().trim());

                    databaseHelping.registerUser(model);

                    Intent intent = new Intent(registation.this, login.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(registation.this, "Registation sucessful", Toast.LENGTH_LONG).show();
                }


            }
            boolean validation(){
                if (edt_name.getText().toString().trim().length()>=3 && edt_username.getText().toString().trim().length()>=3 && edt_password.getText().toString().trim().length()==5){
                    return true; }
                else{
                    Toast.makeText(registation.this,"name and username not less than 3,password must be 5 digit",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        });
        btn_retur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retur=new Intent(registation.this,login.class);
                startActivity(retur);
                finish();

            }
        });
    }
}
