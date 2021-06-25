package com.example.api.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
    @Query("SELECT s From user s WHERE s.email=?1")
    Optional<user>finduserByEmail(String email);
    @Query("SELECT s From user s WHERE s.username=?1")
    Optional<user>findusersByUserName(String username);
}
