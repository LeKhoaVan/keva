package it.ktpm.keva.project.model;

public class ProjectUtils {
    public static class Project {
        public static final String TABLE_NAME = "k_project";
        public static final String NAME = "k_name";
        public static final String CODE = "k_code";
        public static final String DESCRIPTION = "k_description";
        public static final String STATUS = "k_status";
        public static final String STARTDAY = "k_startday";
        public static final String TIME = "k_time";

    }

    public static class Work {
        public static final String TABLE_NAME = "k_work";
        public static final String NAME = "k_name";
        public static final String CODE = "k_code";
        public static final String DESCRIPTION = "k_description";
        public static final String ID_PROJECT = "k_id_project";
        public static final String STARTDAY = "k_startday";
        public static final String ENDDAY = "k_endday";

        public static final String STATUS = "k_status";
        public static final String ID_USER = "k_id_user";
    }
}
