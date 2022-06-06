package com.asdhull.splitbills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.asdhull.splitbills.adapters.GroupNames;
import com.asdhull.splitbills.modals.GroupsNameModal;

import java.util.ArrayList;

public class CreateGroups extends AppCompatActivity {
    ArrayList <GroupsNameModal> GroupNameList;
    RecyclerView RecyclerView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_groups);
        GroupNameList = new ArrayList<>();
        RecyclerView1=findViewById(R.id.recylerGroupNames);
        GroupNameList.add(new GroupsNameModal("1","Group Name", "121"));
        GroupNameList.add(new GroupsNameModal("2","Group Name1", "122"));
        GroupNameList.add(new GroupsNameModal("3","Group Name2", "123"));
        GroupNameList.add(new GroupsNameModal("4","Group Name3", "124"));

        GroupNames adapter=new GroupNames(GroupNameList);
        RecyclerView1.setAdapter(adapter);

        RecyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }
}