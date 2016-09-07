package hari.firebase_database;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Hari Prasad on 9/7/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
