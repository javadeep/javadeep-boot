package com.javadeep.boot.jpa.bo;

public final class UserBO {

    private final Long groupId;

    private final Long count;

    public UserBO(Long groupId, Long count) {
        this.groupId = groupId;
        this.count = count;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Long getCount() {
        return count;
    }
}
