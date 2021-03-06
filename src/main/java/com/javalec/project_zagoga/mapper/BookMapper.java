package com.javalec.project_zagoga.mapper;

import com.javalec.project_zagoga.dto.Booking;
import com.javalec.project_zagoga.dto.BookingForHost;
import com.javalec.project_zagoga.dto.BookingRoomGhouseUsers;
import com.javalec.project_zagoga.dto.Users;
import com.javalec.project_zagoga.mapper.sql.BookSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BookMapper {
    //유저정보 가져오기
    @SelectProvider(type = BookSQL.class, method = "getUserInfo")
    Users getUserInfo(@Param("u_no")int u_no, int r_no);

    //예약정보 가져오기
    @SelectProvider(type = BookSQL.class, method = "bookConfirmInfo")
    List<Booking> bookConfirmInfo(@Param("u_no")int u_no, @Param("r_no")int r_no);

    @SelectProvider(type = BookSQL.class, method = "bookingListForHost")
    List<BookingForHost> bookingListForHost(@Param("h_no") int h_no);

    @InsertProvider(type = BookSQL.class, method = "insertBook")
    int insertBook(@Param("booking")Booking booking);

//    @SelectProvider(type = BookSQL.class, method = "getBookInfo")
//    List<BookingRoomGhouseUsers> getBookInfo(@Param("u_no")int u_no, @Param("r_no")int r_no);

    @SelectProvider(type = BookSQL.class, method = "getBookingConfirm")
    BookingRoomGhouseUsers getBookingConfirm(@Param("info")BookingRoomGhouseUsers info);

    @UpdateProvider(type = BookSQL.class, method = "updateBookingState")
    int updateBookingState(@Param("b_no") int b_no, @Param("b_state") int b_state);
}
