package com.Maktab.Final.model.repository;


import com.Maktab.Final.model.entity.baseEntity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByNationalCodeAndPassword(String email, String password);

    @Override
    List<User> findAll(Specification<User> specification);
}

