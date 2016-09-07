package hari.firebase_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText et_message;
    private Button btn_store;
    private TextView tv_old_data;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_old_data = (TextView) findViewById(R.id.tv_old_data);
        et_message = (EditText) findViewById(R.id.et_message);
        btn_store = (Button) findViewById(R.id.btn_store);
        btn_store.setOnClickListener(this);

        myFirebaseRef = new Firebase("https://firedb-8c893.firebaseio.com");

        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv_old_data.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "onCancelled: " + firebaseError);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_store:
                if (et_message.getText().toString().length() == 0) {
                    et_message.setError("Should not be empty!");
                    return;
                }
                myFirebaseRef.child("message").setValue(et_message.getText().toString());
                break;
        }
    }
}
