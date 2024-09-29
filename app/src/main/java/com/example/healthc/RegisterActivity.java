package com.example.healthc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername ,edMail ,edPassword ,edConfirm;
    Button btn ;
    TextView tv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextRegisterUsername1);
        edMail = findViewById(R.id.editTextRegisterMail);
        edPassword = findViewById(R.id.editTextRegisterPassword);
        edConfirm = findViewById(R.id.editTextConfirmPassword);
        btn = findViewById(R.id.Registerbutton);
        tv = findViewById(R.id.textViewExsistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,loginActivity2.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edMail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length() == 0|| email.length() ==0 || password.length() ==0 || confirm.length() ==0){
                    Toast.makeText(RegisterActivity.this, "Please fill your details!", Toast.LENGTH_SHORT).show();

                }else {
                    if (password.compareTo(confirm) == 0){
                      if (isvalid(password)){
                          db.register(username,email,password);
                          Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(RegisterActivity.this,loginActivity2.class));
                      }else {
                          Toast.makeText(RegisterActivity.this, "Password must contain at least 8 characters, having letter ,digit and a special character ", Toast.LENGTH_SHORT).show();
                      }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isvalid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }

    }

}