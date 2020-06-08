package com.example.locknote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesDataAdapter extends BaseAdapter {
    private List<NoteData> notes;
    private LayoutInflater inflater;

    NotesDataAdapter(Context context, List<NoteData> notes) {
        if (notes == null) {
            this.notes = new ArrayList<>();
        } else {
            this.notes = notes;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void addNote(NoteData note) {
        this.notes.add(note);
        notifyDataSetChanged();
    }

    void removeNote(int position) {
        notes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public NoteData getItem(int position) {
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

        NoteData noteData = notes.get(position);

        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.body);
        TextView deadline = view.findViewById(R.id.deadline);
        ImageButton delete = view.findViewById(R.id.btn_delete);

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
