package com.Maktab.Final.service;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.repository.CommentRepo;
import com.Maktab.Final.service.serviceInterface.CommentServInt;
import exception.LogicException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServ implements CommentServInt {
    //--------------------------------------------------------------needed
    private final CommentRepo commentRepo;
    private final ExpertServ expertServ;
    private final CustomerServ customerServ;

    public CommentServ(CommentRepo commentRepo, ExpertServ expertServ, CustomerServ customerServ) {
        this.commentRepo = commentRepo;
        this.expertServ = expertServ;
        this.customerServ = customerServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public List<Comment> findCommentByExpert(Expert expert) {
        try {
            Expert expert1 = expertServ.findExpertById(expert.getId());
            if (expert1 == null) throw new LogicException("this expert isn't exists");
            List<Comment> comments = commentRepo.findCommentByExpert(expert);
            if (comments == null) throw new LogicException("we cant find this comment");
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public List<Comment> findCommentByCustomer(Customer customer) {
        try {
            Customer customer1 = customerServ.findCustomerById(customer.getId());
            if (customer1 == null) throw new LogicException("we dont have this customer");
            List<Comment> comments = commentRepo.findCommentByCustomer(customer);
            if (comments == null) throw new LogicException("we cant find this comment");
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(Comment comment) {
        try {
            if (comment.getComment() == null || comment.getComment().isEmpty())
                throw new LogicException("we cant add null comment");
            Expert expert = expertServ.findExpertById(comment.getExpert().getId());
            if (expert == null) throw new LogicException("we dont have this expert");
            if (comment.getPoint() == true) expert.setPoint(expert.getPoint() + 1);
            else expert.setPoint(expert.getPoint() - 1);
            Customer customer = customerServ.findCustomerById(comment.getCustomer().getId());
            if (customer == null) throw new LogicException("we dont have this customer");
            commentRepo.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }
}
