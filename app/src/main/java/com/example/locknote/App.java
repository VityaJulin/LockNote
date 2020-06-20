package com.example.locknote;

import android.app.Application;

public class App extends Application {

    private static StorageComponent component;
    private static Keystore keystore;

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

    public static Keystore getKeystore() {
        return keystore;
    }
}
