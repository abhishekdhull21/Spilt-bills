package com.asdhull.splitbills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.asdhull.splitbills.adapters.GroupNames;
import com.asdhull.splitbills.modals.Group;
import com.asdhull.splitbills.modals.GroupsNameModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CreateGroups extends AppCompatActivity {
    ArrayList <GroupsNameModal> GroupNameList;
    RecyclerView RecyclerView1;
    private FirebaseFirestore db;
    FirebaseAuth mAuth;
    GroupNames adapter;
    Button btnCreateGroup;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_groups);
        GroupNameList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        RecyclerView1=findViewById(R.id.recylerGroupNames);
        btnCreateGroup = findViewById(R.id.btnCreateGroup);
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();
        getGroupFromDB();




        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateGroups.this,CreateGroupView.class));
            }
        });
    }

    public void getGroupFromDB(){
        Log.d("TAG", "getGroupFromDB: "+uid);
        db.collection("groups")
                .whereEqualTo("uid", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "data fetched for: "+uid);

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                GroupNameList.add(new GroupsNameModal(document.getString("id"), document.getString("name"),"0" ));
                            }
                            loadRecyclerView();
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    public void loadRecyclerView(){
        adapter=new GroupNames(this,GroupNameList);
        RecyclerView1.setAdapter(adapter);

        RecyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }
}