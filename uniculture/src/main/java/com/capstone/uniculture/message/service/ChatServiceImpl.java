package com.capstone.uniculture.message.service;

import com.capstone.uniculture.message.dto.ChatRoomDTO;
import com.capstone.uniculture.message.entity.ChatRoom;
import com.capstone.uniculture.message.repository.ChatMessageRepository;
import com.capstone.uniculture.message.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
  private final ChatRoomRepository chatRoomRepository;
  private final ChatMessageRepository chatMessageRepository;

  public List<ChatRoomDTO> findAllRoom(){
    List<ChatRoom> chatRooms = chatRoomRepository.findAll();
    return chatRooms.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public ChatRoomDTO findRoomById(String roomId){
    return chatRoomRepository.findById(roomId)
            .map(this::convertToDTO)
            .orElse(null);
  }

  public ChatRoomDTO createChatRoom(String name){
    ChatRoom chatRoom = new ChatRoom(name);
    return convertToDTO(chatRoomRepository.save(chatRoom));
  }
  public ChatRoomDTO convertToDTO(ChatRoom chatRoom) {
    ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
    chatRoomDTO.setRoomId(chatRoom.getRoomId());
    chatRoomDTO.setName(chatRoom.getName());
    return chatRoomDTO;
  }

}
