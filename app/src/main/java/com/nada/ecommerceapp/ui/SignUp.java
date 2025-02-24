package com.nada.ecommerceapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.UserDatabase;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    EditText user , mail , pass , confirmPass , data_of_birth ;
    Button login ;

    CheckBox checkBox ;
    UserDatabase userDatabase ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
  //  private FirebaseAuth auth;
    ProgressBar progressBar ;
    String password , us , gmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.forgetpasswordProgressbar);
        user = findViewById(R.id.nameeditg);
        mail = findViewById(R.id.emaileditg);
        pass=findViewById(R.id.passwordedit);
        confirmPass = findViewById(R.id.confirmpasswordedittext);
        checkBox = findViewById(R.id.by_creating) ;
        login = findViewById(R.id.login) ;
        data_of_birth = findViewById(R.id.birthDateeditg);
        login.setEnabled(false);
        login.setBackground(getDrawable(R.drawable.button_next));
        userDatabase = new UserDatabase(SignUp.this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    login.setBackground(getDrawable(R.drawable.button_shape));
                else
                    login.setBackground(getDrawable(R.drawable.button_next));
                login.setEnabled(isChecked);
            }
        });
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( !(user.getText().toString()).isEmpty()) {
                    user.setCompoundDrawablesWithIntrinsicBounds(null , null , getDrawable(R.drawable.ic_baseline_check_circle_24) , null);
                }else{
                    user.setCompoundDrawablesWithIntrinsicBounds(null , null , null , null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = pass.getText().toString() ;
                String confirmPassword = confirmPass.getText().toString() ;
                us = user.getText().toString()  ;
                gmail = mail.getText().toString();
                if(password.equals(confirmPassword) && !password.equals("")){
                    Boolean result = userDatabase.insertData(us,  gmail , password) ;
//                    if(!TextUtils.isEmpty(us)&& !TextUtils.isEmpty(gmail)&& !TextUtils.isEmpty(password) && gmail.matches(emailPattern)){
//                        //SignUpUser();
//                    }
                    if(result) {
                        Intent intent = new Intent(SignUp.this, WelcomeAfterLogin.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignUp.this, "Please ,try to sign up later ", Toast.LENGTH_LONG).show();
                    }

                }else{
                    pass.setText("");
                    confirmPass.setText("");
                    pass.setBackground(getDrawable(R.drawable.error_pass));
                    confirmPass.setBackground(getDrawable(R.drawable.error_pass));
                }
            }
        });

        data_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day  =calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        data_of_birth.setText( (year+"/"+month+"/"+dayOfMonth) );
                    }
                },year,month,day);
                datePickerDialog.setTitle("Select date");
                datePickerDialog.show();
            }
        });
    }
//    private void SignUpUser(){
//        auth.createUserWithEmailAndPassword(us , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(SignUp.this, "Success ", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignUp.this, WelcomeAfterLogin.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//    }
}