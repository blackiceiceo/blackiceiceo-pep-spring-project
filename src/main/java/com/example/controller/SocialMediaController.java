package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountCreationException;
import com.example.exception.AccountDupeUsernameException;
import com.example.exception.AccountMisMatchException;
import com.example.exception.MessageCreationException;
import com.example.exception.MessageNotFoundException;
import com.example.exception.MessageUpdateException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //-------------------------------------------------------Account-----------------------------------------------------------------------------------

    @PostMapping("register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        accountService.addAccount(account);
        return ResponseEntity.status(200).body(account);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account accountPresent = accountService.userLogin(account);

        return ResponseEntity.status(200).body(accountPresent);

    }

    //----------------------------------------------------------Messages---------------------------------------------------------------------------------------

    @PostMapping("messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        messageService.addMessage(message);
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
        Message msg = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(msg);
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromAccountById(@PathVariable int accountId){
        List<Message> messages = messageService.findAllMessagesByAccountId(accountId);
        return ResponseEntity.status(200).body(messages);
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        int rowsUpdated = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(200).body(rowsUpdated);
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> updateMessageTextById(@PathVariable int messageId, @RequestBody Message message){
        int rowsUpdated = messageService.updateMessageById(messageId, message.getMessageText());
        return ResponseEntity.status(200).body(rowsUpdated);
    }

    //-------------------------------------------------------------Excepption Handlers-------------------------------------------------------------------------

    @ExceptionHandler(MessageCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMessageCreation(MessageCreationException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(AccountMisMatchException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAccountMisMatch (AccountMisMatchException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(AccountDupeUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAccountDupUsername (AccountDupeUsernameException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(AccountCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccountCreation (AccountCreationException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleMessageNotFound (MessageNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MessageUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUpdateMessage(MessageUpdateException ex){
        return ex.getMessage();
    }
}
