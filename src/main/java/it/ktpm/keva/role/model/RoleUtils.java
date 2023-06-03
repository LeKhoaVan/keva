package it.ktpm.keva.role.model;

public class RoleUtils {

    public static class RoleMappedOperation{
        public static final String SERVICE_MAPPED_ROLE = "operations";
        public static final String JOIN_TABLE = "K_ROLE_OPERATION";
        public static final String JOIN_TABLE_ROLE_ID = "K_ROLE_ID";
        public static final String JOIN_TABLE_OPERATION_ID = "K_OPERATION_ID";
    }

    public static class RoleMappedUserGroup{
        public static final String GROUP_MAPPED_ROLE = "userGroups";
        public static final String JOIN_TABLE = "K_ROLE_USERGROUP";
        public static final String JOIN_TABLE_ROLE_ID = "K_ROLE_ID";
        public static final String JOIN_TABLE_OPERATION_ID = "K_USERGROUPD_ID";
    }

   public static class RoleTable{
        public static final String TABLE_NAME = "K_ROLE";
        public static final String CODE = "K_CODE";
        public static final String NAME = "K_NAME";
        public static final String DESCRIPTION = "K_DESCRIPTION";
    }

    public static class UserGroup{
        public static final String TABLE_NAME = "K_GROUP";
        public static final String CODE = "K_CODE";
        public static final String NAME = "K_NAME";
        public static final String DESCRIPTION = "K_DESCRIPTION";
    }

    public static class Operation{
        public static final String TABLE_NAME = "K_OPERATION";
        public static final String CODE = "K_CODE";
        public static final String NAME = "K_NAME";
        public static final String DESCRIPTION = "K_DESCRIPTION";
        public static final String type = "K_TYPE";
    }

    public static class Module{
        public static final String TABLE_NAME = "K_MODULE";
        public static final String CODE = "K_CODE";
        public static final String NAME = "K_NAME";
        public static final String DESCRIPTION = "K_DESCRIPTION";
    }


}
