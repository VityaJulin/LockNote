package com.example.locknote;

import dagger.Component;

@Component(modules = {StorageModule.class})
public interface StorageComponent {
    NotesDataBase getStorage();
}

