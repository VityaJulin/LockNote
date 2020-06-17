package com.example.locknote;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {StorageModule.class})
public interface StorageComponent {
    NotesDataBase getStorage();
}

