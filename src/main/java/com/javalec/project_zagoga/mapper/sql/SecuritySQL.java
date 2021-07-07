package com.javalec.project_zagoga.mapper.sql;

import com.javalec.project_zagoga.dto.Users;
import com.javalec.project_zagoga.security.AuthValue;
import org.apache.ibatis.jdbc.SQL;

public class SecuritySQL {

    private static final String TABLE = "SECURITY";

//        작성 방법은 아래 페이지 참조
//    https://mybatis.org/mybatis-3/ko/statement-builders.html
    public String loadUserByName(String username) {
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("USERNAME = #{username}")
                .toString();
    }

    public String insertAuthValue(AuthValue authValue) {
        return new SQL()
                .INSERT_INTO(TABLE)
                .VALUES("USERNAME", "#{authValue.username}")
                .VALUES("PASSWORD", "#{authValue.password}")
                .VALUES("ROLE", "#{authValue.role}")
                .toString();
    }

    public String updatePW(int sc_no, String encPwd) {
        return new SQL()
                .UPDATE(TABLE)
                .SET("PASSWORD = #{encPwd}")
                .WHERE("SC_NO = #{sc_no}")
                .toString();
    }
}