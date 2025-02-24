package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.UserDatabase;

public class Login extends AppCompatActivity {
    EditText email , password ;
    TextView forgetPass ;
    Button login  , google ;
    UserDatabase userDatabase ;
//    GoogleSignInOptions signInOptions;
//    GoogleSignInClient signInClient;
    CheckBox RememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetPass = findViewById(R.id.forgetpassword);
        email= findViewById(R.id.emaileditg) ;
        password = findViewById(R.id.passwordedit) ;
        login = findViewById(R.id.login_btn) ;
//        google = findViewById(R.id.google) ;
        userDatabase = new UserDatabase(this);
        //userDatabase.addUsersFromAPI();
        RememberMe = findViewById(R.id.remember_me) ;
        Intent intent= new Intent(Login.this , WelcomeAfterLogin.class) ;
        SharedPreferences preferences = getSharedPreferences("checkedbox",MODE_PRIVATE);
        String checked = preferences.getString("remember", "");
        if(checked.equals("true")){

            startActivity(intent);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString() ;
                String pass = password.getText().toString() ;
                if(!mail.equals("") && !pass.equals("")) {
                    if(mail.equals("nada@gmail.com") && pass.equals("1")){
                        Intent intent= new Intent(Login.this , AdminMain.class) ;
                        finish();
                        startActivity(intent);
                        return;
                    }
                    Cursor checkPasswordAndEmail = userDatabase.checkPasswordAndEmail(mail, pass);
                    checkPasswordAndEmail.moveToNext();

                    if (checkPasswordAndEmail.getCount()>0) {
                        String c = checkPasswordAndEmail.getString(0);
                        SharedPreferences preferences = getSharedPreferences("Userid",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userid", c );
                        editor.apply();
                        startActivity(intent);
                    }else {
                        email.setText("");
                        password.setText("");
                        Toast.makeText(Login.this, "Your Email or Password is wrong ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(mail.equals("") )
                        Toast.makeText(Login.this, "Enter Your Email", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Login.this, "Enter Your Password", Toast.LENGTH_LONG).show();
                }
            }
        });
        RememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkedbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                }else if(!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkedbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });
//        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        signInClient = GoogleSignIn.getClient(this,signInOptions);
//        GoogleSignInAccount signInAccount =GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount != null){
//            finish();
//            startActivity(intent);
//        }

//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
//                ,new ActivityResultCallback<ActivityResult>(){
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if(result.getResultCode() == Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                            try{
//                                task.getResult(ApiException.class);
//                                finish();
//                                startActivity(intent);
//                            }catch (ApiException e){
//                                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });

//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = signInClient.getSignInIntent();
//                activityResultLauncher.launch(signInIntent);
//            }
//        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Login.this,Forget_Password.class);
                finish();
                startActivity(intent1);
            }
        });

    }
//    public void  getData(){
//        Call<List<Marvel>> call = RetrofitClient.getInstance().getApi().getUsers();
//        call.enqueue(new Callback<List<Marvel>>() {
//            @Override
//            public void onResponse(Call<List<Marvel>> call, Response<List<Marvel>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "response is not success", Toast.LENGTH_SHORT).show();
//
//                }else {
//                    marvels = response.body();
//                    MyCustomAdapter array = new MyCustomAdapter(MainActivity.this, marvels) ;
//                    list.setAdapter(array);
//                    list.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
////                    list1.setAdapter(array);
////                    list1.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false ));
////                    list2.setAdapter(array);
////                    list2.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false ));
//                }
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<List<Marvel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//    }
//    private ProgressDialog myProgressDialog(){
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setTitle("Fetching API");
//        dialog.setMessage("Loading Data");
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setCancelable(false);
//        return  dialog ;
//    }
}