package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    TextView signin;
    Button signup;
    FirebaseAuth mAuth;
    //DatabaseReference databaseReference;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth= FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Records");

        email=findViewById(R.id.emailRegister);
        password=findViewById(R.id.passwordRegister);
        signin=findViewById(R.id.signinRegister);
        signup=findViewById(R.id.registerButton);
        progressBar=findViewById(R.id.progressbarIdRegister);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.registerButton){
            userRegister();
        }
        else if(view.getId()==R.id.signinRegister){
            Intent intent = new Intent(getApplicationContext(),SignIn.class);
            startActivity(intent);
        }
    }

    private void userRegister() {
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        if(emailString.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches())
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Enter a valid email address",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
        else if(passwordString.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
        else if(passwordString.length()<6){
            Toast toast = Toast.makeText(getApplicationContext(),"Password length must be at least 6",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
        else{

        /* this also works but keeps data in realtime database
            progressBar.setVisibility(View.VISIBLE);
            String key = databaseReference.push().getKey();
            User user = new User(emailString,usernameString,passwordString);
            databaseReference.child(key).setValue(user);
            Toast toast = Toast.makeText(getApplicationContext(),"Measurement added",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            progressBar.setVisibility(View.GONE);
        */
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        Toast toast = Toast.makeText(getApplicationContext(),"Register is Successful",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Register is not Successful",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();

                    }
                }
            });
            progressBar.setVisibility(View.GONE);

        }




    }
}