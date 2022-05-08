package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    public Comment findCommentByExpert(Expert expert);
    public Comment findCommentByCustomer(Customer customer);
}
