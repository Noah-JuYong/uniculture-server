package com.capstone.uniculture.message.controller;

import com.capstone.uniculture.message.dto.ChatRoomDTO;
import com.capstone.uniculture.message.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
  private final ChatServiceImpl chatServiceImpl;

  //채팅 리스트
  @GetMapping("/room")
  public String rooms(Model model) {
    return "/chat/room";
  }

  //모든 채팅방
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoomDTO> room() {
    return chatServiceImpl.findAllRoom();
  }

  //채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoomDTO createRoom(@RequestParam String name) {
    return chatServiceImpl.createChatRoom(name);
  }

  //채팅방 입장 화면?
  @GetMapping("/room/enter/{roomId}")
  public String roomDetail(Model model, @PathVariable String roomId) {
    model.addAttribute("roomId", roomId);
    return "/chat/roomdetail";
  }

  //특정 채팅방 조회
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoomDTO roomInfo(@PathVariable String roomId) {
    return chatServiceImpl.findRoomById(roomId);
  }
}
