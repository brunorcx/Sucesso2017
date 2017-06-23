package com.rodrigues.sucesso2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

  private final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    myRef.setValue("Escrevendo na banco de dados versao 1");

    // Read from the database
    myRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        String value = dataSnapshot.getValue(String.class);
        Log.d(TAG, "Value is: " + value);

        TextView primeiraView = (TextView) findViewById(R.id.primeiraView);
        primeiraView.setText(value);
      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Failed to read value
        String TAG = "0";
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }
}
