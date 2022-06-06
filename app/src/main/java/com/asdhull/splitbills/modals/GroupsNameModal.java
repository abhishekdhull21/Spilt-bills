package com.asdhull.splitbills.modals;

public class GroupsNameModal {
    private String id, GroupName, PCount;

    public GroupsNameModal(String id, String groupName, String PCount) {
        this.id = id;
        GroupName = groupName;
        this.PCount = PCount;
    }

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
}
