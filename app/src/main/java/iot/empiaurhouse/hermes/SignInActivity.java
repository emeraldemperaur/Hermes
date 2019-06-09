package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;

import static com.facebook.GraphRequest.TAG;


public class SignInActivity extends Activity implements
        View.OnClickListener {


    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;


    String facebookUserId = "";
    CallbackManager mCallbackManager;
    LoginButton FBLoginButton;
    Button GoogleLoginButton;
    TextView SignInTitle;
    EditText FullName;
    EditText EmailAddress;
    String FullNameString;
    String EmailAddressString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.viewfadein,R.anim.viewfadeout);
        setContentView(R.layout.activity_sign_in);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        FBLoginButton = findViewById(R.id.fblogin_button);
        GoogleLoginButton = findViewById(R.id.googlesignin_button);
        FullName = findViewById(R.id.SignIn_Fullname);
        EmailAddress = findViewById(R.id.SignIn_Email);
        SignInTitle = findViewById(R.id.SignInTitle);
        FBLoginButton.setReadPermissions("email", "public_profile");
        FBLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        GoogleLoginButton.setOnClickListener(this);




    }







    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        showProgressDialog();


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String DisplayName = user.getDisplayName();
                                SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
                                SharedPreferences.Editor HermesIOeditor = HermesIO.edit();
                                HermesIOeditor.putString("DisplayName", DisplayName);
                                HermesIOeditor.apply();
                                CaduceusIntent();

                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.signin_activity), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

                        }


                        hideProgressDialog();
                    }
                });
    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);

            }
        }


    }




    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        showProgressDialog();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
                            SharedPreferences.Editor HermesIOeditor = HermesIO.edit();
                            if (user != null) {
                                facebookUserId = user.getUid();
                                String photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";
                                String DisplayName = user.getDisplayName();
                                HermesIOeditor.putString("DisplayName", DisplayName);
                                HermesIOeditor.putString("photoUrl", photoUrl);
                                HermesIOeditor.apply();
                                CaduceusIntent();

                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Facebook Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        hideProgressDialog();
                        // ...
                    }
                });
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.googlesignin_button:
                signIn();
                break;
            // ...
        }

    }



    private void signIn() {
        Intent GoogleSignInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(GoogleSignInIntent, RC_SIGN_IN);
    }




    public void DISCOVER_BTN(View view) {


        FullNameString = FullName.getText().toString();
        EmailAddressString = EmailAddress.getText().toString();
        if (!isValidName(FullNameString)) {
            FullName.setError("Please enter your name");
        }

        if (!isValidEmail(EmailAddressString)) {
            EmailAddress.setError("Invalid Email");
        }

        if (isValidName(FullNameString) && isValidEmail(EmailAddressString)) {


            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
            String datetime = dateformat.format(c.getTime());
            SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
            SharedPreferences.Editor HermesIOeditor = HermesIO.edit();
            HermesIOeditor.putString("DisplayName", FullNameString);
            HermesIOeditor.putString("EmailAddress", EmailAddressString);
            HermesIOeditor.apply();
            CaduceusIntent();


        }



    }



    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.googlesignin_loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }



    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }




    public void initForge(String DisplayName, String Email, String MSID, String City, String Country, String Date, String APILevel ){




    }



    public void CaduceusIntent(){

        Intent caduceusintent = new Intent(this, CaduceusActivity.class);
        startActivity(caduceusintent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);



    }







    public void SIGNINHELP_BTN(View view) {
        String EHhelp_url = "https://www.empiaurhouse.com/showcase";
        Intent eh_help = new Intent(Intent.ACTION_VIEW);
        eh_help.setData(Uri.parse(EHhelp_url));
        startActivity(eh_help);
    }

    public void GOOGLE_SIGNIN_BTN(View view) {


    }










    //  email text view validator
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // name text view validator
    private boolean isValidName(String name) {
        if (name != null && !name.isEmpty()) {
            return true;
        }
        return false;
    }




    @Override
    protected void onResume()
    {
        super.onResume();

    }




    @Override
    protected void onPause()
    {
        super.onPause();
        SignInTitle.clearAnimation();




    }

    @Override
    public void onStart() {
        super.onStart();

    }




    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }




}
