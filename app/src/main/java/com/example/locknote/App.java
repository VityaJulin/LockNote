package com.example.locknote;

import android.app.Application;

import com.example.locknote.database.DaggerStorageComponent;
import com.example.locknote.database.StorageComponent;
import com.example.locknote.database.StorageModule;

public class App extends Application {

    private static StorageComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .build();
    }


    public static StorageComponent getComponent() {
        return component;
    }
}
