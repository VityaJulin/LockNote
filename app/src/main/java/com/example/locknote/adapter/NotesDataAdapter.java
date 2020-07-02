package com.example.locknote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.locknote.App;
import com.example.locknote.Executor;
import com.example.locknote.Note;
import com.example.locknote.R;
import com.example.locknote.database.StorageComponent;

import java.util.ArrayList;
import java.util.List;

public class NotesDataAdapter extends BaseAdapter {
    private List<Note> notes;
    private List<Note> notesDb;
    private LayoutInflater inflater;
    StorageComponent component;

    public NotesDataAdapter(Context context, List<Note> notes) {
        if (notes == null) {
            this.notes = new ArrayList<>();
        } else {
            this.notes = notes;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        component = App.getComponent();
    }

    public void addNote(Note note) {
        this.notes.add(note);
        notifyDataSetChanged();
    }

    public void addAllNotes(List<Note> noteList) {
        for (Note note : noteList) {
            this.notes.add(note);
        }
        notifyDataSetChanged();
    }

    void removeNote(final int position) {
        Executor.IOThread(new Runnable() {
            @Override
            public void run() {
                notesDb = component.getStorage().getNoteDao().getAllNote();
                Note note = notesDb.get(position);
                component.getStorage().getNoteDao().deleteNote(note);
            }
        });
        notes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        if (position < notes.size()) {
            return notes.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.note_list_view, parent, false);
        }

        Note noteData = notes.get(position);

        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.body);
        TextView deadline = view.findViewById(R.id.deadline);
        ImageButton delete = view.findViewById(R.id.btn_delete);

        if (!noteData.getNoteTitle().equals("")) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        if (!noteData.getNoteDeadline().equals("")) {
            deadline.setVisibility(View.VISIBLE);
        } else {
            deadline.setVisibility(View.GONE);
        }

        title.setText(noteData.getNoteTitle());
        subtitle.setText(noteData.getNoteBody());
        deadline.setText(noteData.getNoteDeadline());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeNote(position);
            }
        });

        return view;
    }
}
