package com.javalec.project_zagoga.controller;


import com.javalec.project_zagoga.dto.GhouseRoomImages;
import com.javalec.project_zagoga.dto.Images;
import com.javalec.project_zagoga.dto.Room;
import com.javalec.project_zagoga.dto.RoomImages;
import com.javalec.project_zagoga.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    //20210704 02:58 확인 (체크인&아웃 가져와야함..) r_no 4번으로 테스트
    @RequestMapping(value = "/getDetail/{r_no}", method = RequestMethod.GET)
    public String getDetail(@PathVariable("r_no") int r_no, Model model) {
        List<RoomImages> roomImages = this.roomService.getDetail(r_no);
        model.addAttribute("getDetail", roomImages);
        return "/room/room_detail";
    }


    //룸작성페이지 화면!!
    @RequestMapping(value = "/write/{gh_no}", method = RequestMethod.GET)
    public String write(@PathVariable("gh_no")int gh_no, Model model){
        model.addAttribute("gh_no", gh_no);
        return "/room/room_write";
    }

    //정상작동!! 210703 21:52
    @RequestMapping(value = "/room_write")
    public String room_write(Room room){
//        System.out.println(room);
        roomService.insertRoom(room);
        return "redirect:/room/detail/"+room.getR_ghno();
    }

//  room_write -> detail(이미지 넣기전 방금넣은 room data 가져오기) -> room_images
    @RequestMapping(value = "/detail/{r_ghno}")
    public String detail(@PathVariable("r_ghno")int r_ghno, Model model){
        Room room = roomService.detail(r_ghno);
        model.addAttribute("RM", room);
        System.out.println("room.toString() : " + room.toString());
        return "/room/room_images";
    }
    
    //이미지업로드 컨트롤러 20210703 정상작동
    @PostMapping("/room_images/{r_no1},{h_no}")
    public String room_images(Images images, @PathVariable("r_no1")int r_no1 , @PathVariable("h_no")int h_no, @RequestParam("files") List<MultipartFile> files) {
//        Room room = roomService.detail(r_ghno);
//        System.out.println("room/room_images = room.toString() : " + room.toString());
//        String Path = "/resources/rooms_image/";
        images.setI_rno(r_no1);
        String fileAddr = "C:\\Users\\yeon\\IdeaProjects\\project-zagoga\\src\\main\\resources\\static\\rooms_image\\";
        List<String> safeDB = new ArrayList<>();
        List<Integer> r_no = new ArrayList<>();

        try {
            for (int i=0; i<files.toArray().length; i++){
//                long imageName = System.currentTimeMillis();
                String imageName = String.valueOf(System.currentTimeMillis());
                String OriginName = files.get(i).getOriginalFilename();
                String transfer = fileAddr + imageName + "_" + OriginName;
                File transferto = new File(transfer);
//                System.out.println("room/room_images : " + transfer);

                safeDB.add(imageName + "_" + OriginName);
                r_no.add(images.getI_rno());
//                System.out.println(safeDB.get(i));
//                images.setI_name(System.currentTimeMillis() + "_" + files.get(i).getOriginalFilename());
                files.get(i).transferTo(transferto);
//                System.out.println("in for "+i+" : "+images.toString());
            }
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println("safeDB.toString : " + safeDB);
//        System.out.println("images.toString : " + images);
        try {
            for (int i=0; i<safeDB.size(); i++){
                images.setI_name(safeDB.get(i));
                images.setI_rno(r_no.get(i));
                images.setI_path("C:\\Users\\yeon\\IdeaProjects\\project_zagoga\\src\\main\\resources\\static\\rooms_image\\");
                roomService.imageInsert(images);
            }
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/ghouse/host_myPageHouseInfo/"+h_no;
    }

    //0704 03:37 확인
    @RequestMapping(value = "/mypageRoomInfo/{r_no}", method = RequestMethod.GET)
    public String mypageRoomInfo(@PathVariable("r_no")int r_no, Model model){
        List<RoomImages> roomImages = roomService.mypageRoomInfo(r_no);
        System.out.println("roomImages.toString() : "+roomImages.toString());
//        System.out.println(roomImages.get(0).toString());
        model.addAttribute("room", roomImages);
        return "/mypage/mypage_room_info";
    }

//  20210706 15:47 확인
    @RequestMapping("/update")
    public String update(Room room){
//        System.out.println("room.toString : " + room.toString());
        roomService.update(room);
        return "redirect:/ghouse/host_myPageHouseInfo/"+room.getR_ghno();
    }

    //확인
    @RequestMapping("/ImagesDelete/{r_no}")    // 9, 3 테스트
    public String ImagesDelete(@PathVariable("r_no") int r_no){
        roomService.ImagesDelete(r_no);
        return "redirect:/room/RoomDelete/"+r_no;
    }

    @RequestMapping("/RoomDelete/{r_no},{h_no}")
    public String RoomDelete(@PathVariable("r_no") int r_no, @PathVariable("h_no")int h_no){
        System.out.println("roomDel r_no : " + r_no);
        roomService.RoomDelete(r_no);
        return "redirect:/host/host_myGhouse/"+h_no;
    }
}
