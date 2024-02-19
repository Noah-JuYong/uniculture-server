package com.capstone.uniculture.message.entity;

import com.capstone.uniculture.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
  @Id
  private String roomId;
  private String name;

  @ManyToMany   //사용자 테이블에 채팅방 추가해야함 (mappedBy = "chatRooms");
  private Set<Member> members = new HashSet<>();

  @OneToMany(mappedBy = "chatRoom")
  private List<ChatMessage> messages = new ArrayList<>();

  public ChatRoom(String name){
    this.name = name;
    this.roomId = UUID.randomUUID().toString();
  }
}
