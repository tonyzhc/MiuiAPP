package com.example.wangdaopeng.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabelListAdapter extends RecyclerView.Adapter<LabelListAdapter.ViewHolder> {
    private List<String> labels;
    private boolean multiSelect = false;
    private ArrayList<Integer> deleted = new ArrayList<>();
    private static final String BACKGROUND_COLOR = "#FAFAFA";
    private RecyclerView myRecyclerView;

    //ActionMode is for the multiSelect delete function
    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            multiSelect = true;
            menu.add("删除");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Collections.sort(deleted, Collections.<Integer>reverseOrder());
            for(Integer item : deleted) {
                System.out.println(labels.remove(item.intValue()));
            }
            actionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            multiSelect = false;
            deleted.clear();
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView;

        public void setFocus(final Integer itemIndex) {
            final String original = myTextView.getText().toString();
            myTextView.setCursorVisible(true);
            myTextView.setFocusableInTouchMode(true);
            myTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            myTextView.requestFocus();

            myTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    myTextView.setCursorVisible(false);
                    myTextView.setFocusableInTouchMode(false);
                    String content = myTextView.getText().toString();
                    if(content.equals("")) {
                        Toast.makeText(myTextView.getContext(), "标签不能为空", Toast.LENGTH_SHORT).show();
                        myTextView.setText(original);
                    } else {
                        labels.set(itemIndex.intValue(), content);
                        System.out.println("label changed to " + content);
                    }
                }
            });
/*
            myTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        myTextView.setCursorVisible(false);
                        myTextView.setFocusableInTouchMode(false);
                        String content = myTextView.getText().toString();
                        if(content.equals("")) {
                            Toast.makeText(myTextView.getContext(), "标签不能为空", Toast.LENGTH_SHORT).show();
                            myTextView.setText(original);
                            labels.set(itemIndex.intValue(), original);
                        } else {
                            labels.set(itemIndex.intValue(), content);
                            System.out.println("label changed to " + content);
                        }
                    }
                }
            });*/
        }

        //Handles what happens when user select them
        public void selectItem(final Integer itemIndex) {
            if (multiSelect) {
                //this is the case for deleting items
                if (deleted.contains(itemIndex)) {
                    //if item already selected, clicking again will cancel selection
                    deleted.remove(itemIndex);
                    myTextView.setBackgroundResource(R.drawable.label_background);
                } else {
                    //otherwise add it to the selection
                    deleted.add(itemIndex);
                    myTextView.setBackgroundColor(Color.LTGRAY);
                }
            } else {
                //if not in the multiselect/deleting mode, edit label
                setFocus(itemIndex);
            }
        }

        public ViewHolder(View v) {
            super(v);
            myTextView = v.findViewById(R.id.label_text);
            //myTextView.setBackgroundResource(R.drawable.label_background);
        }

        public void update(final Integer i) {
            final String label_data = labels.get(i);
            myTextView.setText(label_data);
            if(deleted.contains(i)) {
                myTextView.setBackgroundColor(Color.LTGRAY);
            } else {
                myTextView.setBackgroundResource(R.drawable.label_background);
            }
            myTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ((AppCompatActivity) view.getContext()).startSupportActionMode(actionModeCallbacks);
                    selectItem(i);

                    return true;
                }
            });

            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTextView.setSelected(true);
                    selectItem(i);
                }
            });
        }
    }

    public LabelListAdapter(List<String> labels_) {
        labels = labels_;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.label_adapter, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.update(i);
        //viewHolder.myTextView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        myRecyclerView = recyclerView;
    }

    public String getLabels() {
        StringBuilder builder = new StringBuilder();
        for(String label : labels) {
            builder.append("#").append(label);
        }
        return builder.toString();
    }

    public void addLabel(String label) {
        labels.add(label);
        notifyItemInserted(labels.size() - 1);
        myRecyclerView.scrollToPosition(labels.size() - 1);
    }
}