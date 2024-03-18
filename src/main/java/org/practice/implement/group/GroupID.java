package org.practice.implement.group;

public record GroupID(String value) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupID other = (GroupID) obj;
        return value.equals(other.value());
    }
}
