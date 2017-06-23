package com.rodrigues.sucesso2017;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

  private final String TAG = "LoginActivity";
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    //Autenticação
    mAuth = FirebaseAuth.getInstance();
    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          // User is signed in
          Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
          // User is signed out
          Log.d(TAG, "onAuthStateChanged:signed_out");
        }
        // ...
      }
    };

  }
  @Override
  public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }
  @Override
  public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }
  public void signInUser(View view) {
    EditText login_ET = (EditText) findViewById(R.id.login_ET);
    EditText senha_ET = (EditText) findViewById(R.id.senha_ET);


    final String email = login_ET.getText().toString();
    final String password = senha_ET.getText().toString();

    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                  Log.w(TAG, "signInWithEmail:failed", task.getException());
                  Toast.makeText(LoginActivity.this, R.string.auth_failed,
                          Toast.LENGTH_SHORT).show();
                }
                else {
                  Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                  startActivity(intent2);
                }
              }
            });
  }

  public void cadastrarIntent(View view) {
    Intent intent = new Intent(this,CadastroActivity.class);
    startActivity(intent);
  }
}
