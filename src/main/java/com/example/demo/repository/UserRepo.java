package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.Village;
import com.example.demo.enums.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    List<User> getAllByVillage(Village village);

    List<User> findAllByRoleIn(Collection<role> roles);

    Optional<User> findByUsername(String username);

    List<User>  getAllByVillageVillageId(Long villageVillageId);


    User findUserByUserId(Long userId);
}
