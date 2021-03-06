package com.javalec.project_zagoga.controller;

import com.javalec.project_zagoga.dto.Booking;
import com.javalec.project_zagoga.dto.BookingRoomGhouseUsers;
import com.javalec.project_zagoga.dto.Users;
import com.javalec.project_zagoga.mapper.AdminMapper;
import com.javalec.project_zagoga.services.BookService;
import com.javalec.project_zagoga.services.HostService;
import com.javalec.project_zagoga.services.UsersService;
import com.javalec.project_zagoga.vo.HostVO;
import com.javalec.project_zagoga.vo.UsersVO;
import lombok.AllArgsConstructor;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;
    private final HostService hostService;
    private final BookService bookingService;
    
    @GetMapping("/user_list")
    public String user_list(Model model) {
        List<Users> userList = this.usersService.userList();
//        System.out.println(userList);
        model.addAttribute("userList",userList);
        return "/admin/user_list";
    }

    // user detail page: load one user information.
    @GetMapping("/user_detail")
    public String user_detail(HttpServletRequest request,Model model) {
    	int u_no = Integer.parseInt(request.getParameter("u_no"));
    	UsersVO user = usersService.userInfo(u_no);
    	model.addAttribute("userInfo",user);
        return "/admin/user_detail";
    }

    // ADMIN: 사업자 정보 디테일 ( 사업 승인 /거절 )
    @GetMapping("/host_detail")
    public String host_detail(HttpServletRequest request,Model model) {
    	String h_bizno = request.getParameter("h_bizno");
    	HostVO host = hostService.hostInfo(h_bizno);
    	model.addAttribute("hostInfo",host);
        return "/admin/host_detail";
    }

    // ADMIN: 사업자 리스트
    @RequestMapping("/host_list")
    public String hostList(Model model) {
        List<HostVO> host = hostService.hostList();
//        System.out.println("host.toString : " + host.toString());
        model.addAttribute("hList", host);
        return "/admin/host_list";
    }
    
    @RequestMapping("/user_booking_list")
    public String user_booking_list(Model model){
    	List<Users> userList = usersService.userList();
    	model.addAttribute("userList",userList);
    	return "/admin/user_booking_list";
    }
    @RequestMapping("/user_booking_detail")
    public String user_booking_detail(HttpServletRequest request , Model model){
    	String u_no = request.getParameter("u_no");
    	List<BookingRoomGhouseUsers> bookingList = usersService.userBookList(u_no);
    	model.addAttribute("bookingList",bookingList);
    	return "/admin/user_booking_detail";
    }

    @GetMapping("/admin_approve")
    public String admin_approve(@RequestParam("h_no") int h_no){ // 승인
        hostService.admin_approve(h_no);

        return "redirect:/admin/host_list";
    }
    @GetMapping("/admin_reject")
    public String admin_reject(@RequestParam("h_no") int h_no){ // 거절 or 정지

        hostService.admin_reject(h_no);

        return "redirect:/admin/host_list";
    }

}
