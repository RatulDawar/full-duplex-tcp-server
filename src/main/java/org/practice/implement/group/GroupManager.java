package org.practice.implement.group;

import org.practice.implement.client.ClientID;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GroupManager {

    Map<GroupID, Group> groups;

    public GroupManager(){
        this.groups = new HashMap<>();
    }


    public synchronized void  addToGroup(GroupID groupID, ClientID clientID){
        groups.putIfAbsent(groupID,new Group(groupID));
        groups.get(groupID).add(clientID);
    }

    public void removeFromGroup(GroupID groupID,ClientID clientID){
        Optional<Group> group = Optional.ofNullable(groups.get(groupID));
        group.ifPresent(value -> value.remove(clientID));
    }

    public void removeFromAllGroups(ClientID clientID){
        for (Map.Entry<GroupID, Group> entry : groups.entrySet()) {
            removeFromGroup(entry.getKey(),clientID);
        }
    }

    public Group getGroup(GroupID groupID) throws IllegalArgumentException{
        Optional<Group> group = Optional.ofNullable(groups.get(groupID));
        if(group.isPresent()){
            return group.get();
        }else{
            throw new IllegalArgumentException("Invalid group ID");
        }
    }


}
