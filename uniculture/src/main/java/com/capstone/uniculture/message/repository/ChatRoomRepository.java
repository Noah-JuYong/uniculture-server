package com.capstone.uniculture.message.repository;

import com.capstone.uniculture.message.dto.ChatRoomDTO;
import com.capstone.uniculture.message.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String > {
}
