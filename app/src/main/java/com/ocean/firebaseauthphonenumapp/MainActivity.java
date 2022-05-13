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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ocean.firebaseauthphonenumapp.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private String verificationId;

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
                    String phone = "+91" + binding.inputTextPhoneNum.getText().toString();
                    sendVerificationCode(phone);

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

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("phoneNum", binding.inputTextPhoneNum.getText().toString());
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

    /** callback method is called on Phone auth provider.
        initializing our callbacks for on
        verification callback method.**/
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            final String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                binding.inputTextOtp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("OnVerificationFailed:", "onVerificationFailed: " + e.getMessage());
        }
    };

}