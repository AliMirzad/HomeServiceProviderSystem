package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Comment;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findCommentByExpert(Expert expert);
    public List<Comment> findCommentByCustomer(Customer customer);
}
