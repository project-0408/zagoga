package com.javalec.project_zagoga.mapper.sql;

import com.javalec.project_zagoga.dto.Images;
import com.javalec.project_zagoga.dto.Room;
import org.apache.ibatis.jdbc.SQL;

public class RoomSQL {
    private static final String TABLE="ROOMS";
    private static final String Images="IMAGES";
    public static final String GET_ROOM_DETAIL="select * from " + TABLE;

    public String detail(int r_ghno){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("R_GHNO = #{r_ghno}")
                .ORDER_BY("R_NO desc")
                .LIMIT(1)
                .toString();
    }

    public String insertRoom(Room room){
        return new SQL()
                .INSERT_INTO(TABLE)
                .INTO_COLUMNS("R_NAME", "R_PMIN", "R_PMAX", "R_FEE", "R_DETAIL", "R_GHNO")
                .INTO_VALUES("#{room.r_name}", "#{room.r_pmin}", "#{room.r_pmax}", "#{room.r_fee}", "#{room.r_detail}", "#{room.r_ghno}")
                .toString();
    }



    public String imageInsert(Images images){
        return new SQL()
                .INSERT_INTO(Images)
                .INTO_COLUMNS("I_NAME", "I_PATH", "I_RNO")
                .INTO_VALUES("#{images.i_name}", "#{images.i_path}", "#{images.i_rno}")
                .toString();
    }

    public String list(int r_ghno){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .WHERE("R_GHNO = #{r_ghno}")
                .toString();
    }

    public String getGhno(int h_no){
        return new SQL()
                .SELECT("GH_NO")
                .FROM("GHOUSE")
                .WHERE("GH_HNO #{h_no}")
                .ORDER_BY("GH_NO DESC")
                .LIMIT(1)
                .toString();
    }


    public String getDetail(int r_no){
        return new SQL()
                .SELECT("*")
                .FROM(TABLE)
                .JOIN("IMAGES I on ROOMS.R_NO = I.I_RNO")
                .WHERE("I_RNO = #{r_no}")
                .ORDER_BY("I_NO DESC")
                .toString();
    }
}
