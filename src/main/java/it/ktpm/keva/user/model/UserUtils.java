package it.ktpm.keva.user.model;

public class UserUtils {
    public static class User{
        public static final String TABLE_NAME = "K_USER";
        public static final String USER_NAME = "K_USER_NAME";
        public static final String PASSWORD = "K_PASSWORD";
        public static final String FULL_NAMNE = "K_FULL_NAMNE";
        public static final String DISPLAY_NAME = "K_DISPLAY_NAME";
        public static final String AVATAR = "K_AVATAR";
        public static final String EMAIL = "K_EMAIL";
        public static final String STATUS = "K_STATUS";
        public static final String MAJORITY = "K_MAJORITY";
        public static final String FACEBOOK = "K_FACEBOOK";
        public static final String DEPARTMENT = "K_DEPARTMENT";
        public static final String HOBBY = "K_HOBBY";
    }

    public static class UserGroupMappedUser{
        public static final String USER_MAP_USERGROUP = "users";
        public static final String JOIN_TABLE = "K_USERGROUP_USER";
        public static final String USERGROUP_ID = "K_USERGROUP_ID";
        public static final String USER_ID = "K_USER_ID";

    }
}
