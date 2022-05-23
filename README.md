# firebaseAuthPhoneNumOTP
Firebase authentication using phone number and otp


So If You are using Firebase Auth there is no need of SharedPreference for logged in session management instead you can use.

FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    if(user != null){
    
    startActivity(new Intent(LoginActivity.this, DashActivity.class)));
    finish();
    }
    
 The above piece of code needed to be writen on login activity.
    
Also see the commit to know how logged in session can be implemented with Shared Preference.

This demo Firebase Phone Auth is completed as based on OTP authentication, where it includes logged in session using both inbuilt firebase funtions and shared preference. Hopefully this will help anyone looking for such way of authentication. Happy coding ;)
