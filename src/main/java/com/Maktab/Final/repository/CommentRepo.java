package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    public List<Comment> findCommentByExpert(Expert expert);
    public List<Comment> findCommentByCustomer(Customer customer);
}
