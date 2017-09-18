package com.rodrigues.sucesso2017;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;





public class MainActivity extends AppCompatActivity {

  private FirebaseDatabase mFirebaseDataBase;
  private FirebaseAuth mFirebaseAuth;
  private DatabaseReference mMensagemDatabaseReference;
  private ChildEventListener mChieldEventListener;
  private FirebaseAuth.AuthStateListener mAuthStateListener;

  public static final int RC_SIGN_IN = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Inicializar firebase
    mFirebaseDataBase = FirebaseDatabase.getInstance();//Inicializar firebase
    mFirebaseAuth = FirebaseAuth.getInstance();

    mMensagemDatabaseReference = mFirebaseDataBase.getReference().child("Mensagem");//Selecionar filho
    mMensagemDatabaseReference.push().setValue("It's a secret to everybody Link");//Escrever

    //Push serve para criar IDs diferentes para cada mensagem que é setada, para não gerar conflito
    //caso duas mensagens sejam iguais

    // Ler da database
    mMensagemDatabaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.

        String value = dataSnapshot.getValue(String.class);
        String TAG="1";
        Log.d(TAG, "Value is: " + value);

        TextView primeiraView = (TextView) findViewById(R.id.primeiraView);
        primeiraView.setText(value);
      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        String TAG="0";
        Log.w(TAG, "Failed to read value.", error.toException());
      }

    });
    mAuthStateListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
          Toast.makeText(MainActivity.this,"Logado com sucesso, Bem vindo",Toast.LENGTH_SHORT).show();

        }
        else{
          startActivityForResult(
                  AuthUI.getInstance()
                          .createSignInIntentBuilder()
                          .setIsSmartLockEnabled(false)//não salvar credencias para logar automatico
                          .setAvailableProviders(
                                  Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                          new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                          new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))

                          .build(),
                  RC_SIGN_IN);

        }

      }
    };


  }

  @Override
  protected void onPause() {
    super.onPause();
    mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mFirebaseAuth.addAuthStateListener(mAuthStateListener);
  }
}
