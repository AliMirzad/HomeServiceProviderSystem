package com.Maktab.Final.model.repository;


import com.Maktab.Final.model.entity.baseEntity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByNationalCodeAndPassword(String email, String password);

    User findUserById(Integer id);

    List<User> findAll(Specification<User> specification);

    @Query("select u from User u where u.nationalCode = :nationalCode")
    Optional<User> findByNationalCode(@Param("nationalCode") String nationalCode);
}

