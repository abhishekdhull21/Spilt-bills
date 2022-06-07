package com.asdhull.splitbills;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asdhull.splitbills.modals.Group;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateGroupView extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;

    private Button mContactPick,btnAddUser;
    private Button mContactName;
    private TextView gName;
    private FirebaseAuth mAuth;
    private String uid;
    private FirebaseFirestore db;

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults)
//    {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION && grantResults.length > 0)
//        {
//            updateButton(grantResults[0] == PackageManager.PERMISSION_GRANTED);
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode != Activity.RESULT_OK) return;
//
//        if (requestCode == REQUEST_CONTACT && data != null)
//        {
//            Uri contactUri = data.getData();
//
//            // Specify which fields you want your
//            // query to return values for
//            String[] queryFields = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
//
//            // Perform your query - the contactUri
//            // is like a "where" clause here
//            Cursor cursor = this.getContentResolver()
//                    .query(contactUri, queryFields, null, null, null);
//            try
//            {
//                // Double-check that you
//                // actually got results
//                if (cursor.getCount() == 0) return;
//
//                // Pull out the first column
//                // of the first row of data
//                // that is your contact's name
//                cursor.moveToFirst();
//
//                String name = cursor.getString(0);
//                mContactName.setText(name);
//
//            }
//            finally
//            {
//                cursor.close();
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_view);
        // Intent to pick contacts
//        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();
        db = FirebaseFirestore.getInstance();
        mContactPick = findViewById(R.id.contact_pick);
        mContactName = findViewById(R.id.btnAddGroupWithUser);
        btnAddUser = findViewById(R.id.btnAddGroupWithUser);
        gName = findViewById(R.id.txtGroupNameGroupView);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: btnAddUser");
                String groupName = gName.getText().toString();
                createGroupAndSaveToDB(groupName);
            }
        });




//        requestContactsPermission();
//        updateButton(hasContactsPermission());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createGroupAndSaveToDB(String groupName){
        Log.d("TAG", "createGroupAndSaveToDB: ");
        CollectionReference dbCourses = db.collection("groups");

        // adding our data to our courses object class.
        Group groups = new Group(groupName,uid);

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(groups).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(CreateGroupView.this, "Group Created Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateGroupView.this,CreateGroups.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(CreateGroupView.this, "Fail to create Group \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void updateButton(boolean enable)
//    {
//        mContactPick.setEnabled(enable);
//        mContactName.setEnabled(enable);
//    }

//    private boolean hasContactsPermission()
//    {
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
//                PackageManager.PERMISSION_GRANTED;
//    }

    // Request contact permission if it
    // has not been granted already
//    private void requestContactsPermission()    {
//        if (!hasContactsPermission())
//        {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
//        }
//    }

}