package com.capstone.uniculture.message.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Data
public class ChatRoomDTO {

  private String roomId;
  private String name;

}
