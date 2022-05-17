# firebaseAuthPhoneNumOTP
Firebase authentication using phone number and otp


So If You are using Firebase Auth there is no need of SharedPreference for logged in session management instead you can use.

FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    if(user != null){
    
    startActivity(new Intent(LoginActivity.this, DashActivity.class)));
    finish();
    }
    
Also see the commit to know how logged in session can be implemented with Shared Preference.
