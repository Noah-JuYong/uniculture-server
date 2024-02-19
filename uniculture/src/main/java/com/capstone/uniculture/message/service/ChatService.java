package com.capstone.uniculture.message.service;

import com.capstone.uniculture.message.dto.ChatRoomDTO;

import java.util.List;

public interface ChatService {
  List<ChatRoomDTO> findAllRoom();
  ChatRoomDTO createChatRoom(String name);
  ChatRoomDTO findRoomById(String roomId);
}
