package com.capstone.uniculture.message.controller;

import com.capstone.uniculture.message.dto.ChatMessageDTO;
import com.capstone.uniculture.message.entity.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class ChatController {
  private final SimpMessageSendingOperations messagingTemplate;   //브로커 통해서 메시지 전달

  @MessageMapping("/chat/message")    //메시지 보내기.
  public void message(ChatMessageDTO message) {

    if (MessageType.ENTER.equals(message.getType())) { //입장할때
      message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    }
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);  //roomId로 메시지 전송
  }

  @MessageMapping("/chat/room/leave")   //채팅방 나가기
  public void chatRoomLeave(ChatMessageDTO message, SimpMessageHeaderAccessor headerAccessor) throws IOException {
    message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
    /*
     * TODO 세션 제거 코드
     */
    messagingTemplate.convertAndSend("/sub/chat/room/"+ message.getRoomId(), message);
  }
}
