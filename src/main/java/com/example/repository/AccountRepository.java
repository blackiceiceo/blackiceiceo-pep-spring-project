package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT * FROM account WHERE username = :username AND password = :password", nativeQuery = true)
    public Optional<Account> findAccountByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM account WHERE username = :username", nativeQuery = true)
    public Optional<Account> findAccountByUsername(String username);

}
