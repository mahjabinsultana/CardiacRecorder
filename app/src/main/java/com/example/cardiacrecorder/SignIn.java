package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    TextView signup;
    Button signin;
    //DatabaseReference dref;
    FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        //dref = FirebaseDatabase.getInstance().getReference("Records");
        mAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.emailLogin);
        password=findViewById(R.id.passwordLogin);
        signin=findViewById(R.id.signinButton);
        signup=findViewById(R.id.signupLogin);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signupLogin){
            Intent intent = new Intent(getApplicationContext(),SignUp.class);
            startActivity(intent);

        }
        else if(view.getId()==R.id.signinButton){
            userLogin();
        }
    }

    private void userLogin() {
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Login Unsuccessful!",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        });

    }
}