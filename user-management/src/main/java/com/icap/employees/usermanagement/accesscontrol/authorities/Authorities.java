package com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities;

public enum Authorities {

    CREATE_USER("Create User"),
    UPDATE_USER("Update User"),
    DISABLE_USER("Disable User"),
    LOCK_USER("Lock User"),

    CREATE_GROUP_AUTHORITY("Can assign authority to group"),
    DELETE_GROUP_AUTHORITY("Can delete authority from group"),

    CREATE_GROUP_USER("Can assign group to a user"),

    CREATE_USER_AUTHORITY("Can assign authority to a user"),
    DELETE_USER_AUTHORITY("Can delete authority from user"),

    CREATE_GROUP("Can create a group"),
    UPDATE_GROUP("Can update group details"),

    CREATE_CATEGORY("Create Category"),
    UPDATE_CATEGORY("Update Category"),
    DELETE_CATEGORY("Delete Category"),

    CREATE_EVENT_PACKAGE("CREATE_EVENT_PACKAGE"),
    UPDATE_EVENT_PACKAGE("UPDATE_EVENT_PACKAGE"),
    DELETE_EVENT_PACKAGE("DELETE_EVENT_PACKAGE"),

    CREATE_EVENT("CREATE_EVENT"),
    UPDATE_EVENT("UPDATE_EVENT"),
    DELETE_EVENT("DELETE_EVENT"),

    READ_GENERAL_AUDITS("READ_GENERAL_AUDITS"),
    READ_AUDIT("READ_AUDIT"),
    READ_USER_AUDITS("READ_USER_AUDITS"),
    READ_ORGANIZATION_AUDITS("READ_ORGANIZATION_AUDITS");





    public final String description;

    Authorities(String description) {
        this.description = description;
    }
}
