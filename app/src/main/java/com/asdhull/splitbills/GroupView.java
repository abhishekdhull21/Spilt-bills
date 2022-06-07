package com.asdhull.splitbills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.asdhull.splitbills.modals.GroupsNameModal;

public class GroupView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_view);
        Intent i = getIntent();
        Bundle bundle=i.getExtras();

        GroupsNameModal group = bundle.getParcelable("group");
        TextView txtGroupName = findViewById(R.id.txtGroupNameGroupView);

        txtGroupName.setText(group.getGroupName());
    }
}