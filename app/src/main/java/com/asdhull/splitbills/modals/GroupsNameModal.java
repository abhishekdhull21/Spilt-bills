package com.asdhull.splitbills.modals;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupsNameModal implements Parcelable {
    private String id, GroupName, PCount;

    public GroupsNameModal(String id, String groupName, String PCount) {
        this.id = id;
        GroupName = groupName;
        this.PCount = PCount;
    }

    protected GroupsNameModal(Parcel in) {
        id = in.readString();
        GroupName = in.readString();
        PCount = in.readString();
    }

    public static final Creator<GroupsNameModal> CREATOR = new Creator<GroupsNameModal>() {
        @Override
        public GroupsNameModal createFromParcel(Parcel in) {
            return new GroupsNameModal(in);
        }

        @Override
        public GroupsNameModal[] newArray(int size) {
            return new GroupsNameModal[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getPCount() {
        return PCount;
    }

    public void setPCount(String PCount) {
        this.PCount = PCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(GroupName);
        parcel.writeString(PCount);
    }
}
