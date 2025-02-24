package com.nada.ecommerceapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.UserDatabase;
import com.nada.ecommerceapp.util.SendEmail;

import java.util.Random;

public class Forget_Password extends AppCompatActivity {
    EditText email ;
    Button resetPassword;
    ProgressBar progressBar ;
    private FirebaseAuth auth;

    UserDatabase userDatabase ;

    String mail = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = findViewById(R.id.emaileditg);
        resetPassword = findViewById(R.id.reset);
        userDatabase = new UserDatabase(this);
//        progressBar = findViewById(R.id.forgetpasswordProgressbar);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString();
                if(!mail.isEmpty()){
                    boolean checkEmail = userDatabase.checkEmail(mail);
                    if(checkEmail) {
                        sendEmail(mail);
                    }else {
                        Toast.makeText(Forget_Password.this, "This Email doesn't have account", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Forget_Password.this, "Email cell is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private  void  ResetPassword(){
//        progressBar.setVisibility(View.VISIBLE);
//        resetPassword.setVisibility(View.INVISIBLE);
//        auth = FirebaseAuth.getInstance();
//
//        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(Forget_Password.this, "Reset Password", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Forget_Password.this , Login.class);
//                startActivity(intent);
//                finish();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Forget_Password.this, "Error", Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.INVISIBLE);
//                resetPassword.setVisibility(View.VISIBLE);
//            }
//        });
//
//    }
    private void sendEmail(String mail) {
        String subject = "Forget Password";
        Random rand = new Random();
        int random = rand.nextInt(100000);
        String message = "Your recover code is "+ random ;

        SendEmail.sendEmail(mail, subject, message);
    }
}