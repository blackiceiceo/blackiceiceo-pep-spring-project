package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query(value = "SELECT * FROM message WHERE postedBy = :postedBy", nativeQuery = true)
    public Optional<List<Message>> findAllMessagesByAccountId(int postedBy);

}
