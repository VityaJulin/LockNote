package com.example.locknote;

import android.app.Application;

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
