package com.asdhull.splitbills.modals;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.asdhull.splitbills.utils.Time;

public class Group {
    private String id,name,uid;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Group(String name, String uid) {
        this.name = name;
        this.uid = uid;
        this.id = uid + Time.getCurrentTime();
    }

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
