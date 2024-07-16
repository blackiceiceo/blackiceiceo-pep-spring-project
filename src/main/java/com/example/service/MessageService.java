package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.MessageCreationException;
import com.example.exception.MessageNotFoundException;
import com.example.exception.MessageUpdateException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message){

        if( message.getMessageText().isBlank() ||
            message.getMessageText().length() > 255 ||
            !messageRepository.findById(message.getPostedBy()).isPresent())
        {
            throw new MessageCreationException();
        }

        messageRepository.save(message);
    }
    
    public List<Message> getAllMessages() {
        return (List<Message>)messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        Optional<Message> optMsg= messageRepository.findById(messageId);

        if(optMsg.isPresent()){
            Message msg = optMsg.get();
            return msg;
        }else{
            throw new MessageNotFoundException("");
        }
    }

    public int updateMessageById(int messageid, String messageText){

        if( messageText.isBlank() ||
            !messageRepository.findById(messageid).isPresent() ||
            messageText.length() > 255
        )
        {
            throw new MessageUpdateException();
        }

        Optional<Message> optMsg = messageRepository.findById(messageid);

        Message msg = optMsg.get();
        msg.setMessageText(messageText);
        messageRepository.save(msg);
        return 1;

    }

    public List<Message> findAllMessagesByAccountId(int accountId){
        Optional<List<Message>> messages = messageRepository.findAllMessagesByAccountId(accountId);
        return messages.get();

    }

    public int deleteMessageById(int messageId) {
        Optional<Message> optMsg = messageRepository.findById(messageId);

        if(optMsg.isPresent()){
            Message msg = optMsg.get();
            messageRepository.delete(msg);
            return 1;
        }else{
            throw new MessageNotFoundException("");
        }
    }

}
