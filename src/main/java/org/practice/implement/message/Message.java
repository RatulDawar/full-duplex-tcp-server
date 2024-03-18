package org.practice.implement.message;


import org.practice.implement.group.GroupID;
import org.practice.request.constants.RequestTypes;

public class Message {
    private final GroupID groupID;
    private final String data;
    private Boolean joinRequest;

    Message(GroupID groupID, String data, Boolean joinRequest){
        this.groupID = groupID;
        this.data = data;
        this.joinRequest = joinRequest;
    }

    public GroupID getGroupID() {
        return groupID;
    }

    public String getData() {
        return data;
    }

    public Boolean getJoinRequest() {
        return joinRequest;
    }

    public static Message parseMessage(String messageString) {

        String[] parts = messageString.split("@", 2);
        if(parts.length != 2) throw new IllegalArgumentException("Invalid message format\n");
        String groupName = parts[0];
        String requestMessage = parts[1].trim().strip();
        return new Message(new GroupID(groupName),requestMessage,requestMessage.equals(RequestTypes.REQUEST_TYPE_ADD));

    }
}
