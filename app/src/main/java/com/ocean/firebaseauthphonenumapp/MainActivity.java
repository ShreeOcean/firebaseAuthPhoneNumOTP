package com.ocean.firebaseauthphonenumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ocean.firebaseauthphonenumapp.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private String verificationId;
    private String phone;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // below line is for getting instance
        // of our FirebaseAuth.
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnGetOtp.setOnClickListener(this);
        binding.btnVerifyOtp.setOnClickListener(this);

        /** callback method is called on Phone auth provider.
         initializing our callbacks for on
         verification callback method.**/
        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                String code = phoneAuthCredential.getSmsCode();
                Log.d(TAG, "onVerificationCompleted: " + phoneAuthCredential);
                if (code != null){
                    binding.inputTextOtp.setText(code);
                    verifyCode(code);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onVerificationFailed: " + e.getMessage());
                if (e instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(MainActivity.this, "Invalid Request: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }else if (e instanceof FirebaseTooManyRequestsException){
                    Toast.makeText(MainActivity.this, "The SMS quota for the project has been exceeded... " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                //Todo: Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Log.d(TAG, "onCodeSent:" + verificationId + "/---/" +forceResendingToken);

                verificationId = s;
                mResendToken = forceResendingToken;
            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_otp:

                if (TextUtils.isEmpty(binding.inputTextPhoneNum.getText().toString())){
                    binding.inputTextPhoneNum.setError("This feild can not be empty !!!");
                }else if (binding.inputTextPhoneNum.getText().length() != 10){
                    binding.inputTextPhoneNum.setError("Input a valid phone number !!!");
                }else {
                    phone = "+91" + binding.inputTextPhoneNum.getText().toString();
                    sendVerificationCode(phone);//signIn With Phone Auth Credential

                    binding.outlinedTextFieldPhNum.setVisibility(View.GONE);
                    binding.btnGetOtp.setVisibility(View.GONE);

                    binding.outlinedTextFieldOTP.setVisibility(View.VISIBLE);
                    binding.btnVerifyOtp.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btn_verify_otp:
                    if (TextUtils.isEmpty(binding.inputTextOtp.getText().toString())){
                        binding.inputTextOtp.setError("OTP is empty !!!");
                    }else if (binding.inputTextOtp.getText().length() != 6){
                        binding.inputTextOtp.setError("OTP is of 6 digit !!!");
                    }else {
                        String code = binding.inputTextOtp.getText().toString();
                        verifyCode(code);
                    }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void verifyCode(String code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);//TODO: verificationId is null
            signInWithCredential(credential);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT ).show();
        }
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("phoneNum", phone);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void sendVerificationCode(String phone) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions authOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                                                        .setPhoneNumber(phone)// Phone number to verify
                                                        .setTimeout(60L, TimeUnit.SECONDS)// Timeout and unit
                                                        .setActivity(this)// Activity (for callback binding)
                                                        .setCallbacks(mCallBack)// OnVerificationStateChangedCallbacks
                                                        .build();
        PhoneAuthProvider.verifyPhoneNumber(authOptions);
    }



}