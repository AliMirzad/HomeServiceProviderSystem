package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Comment;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.repository.CommentRepository;
import com.Maktab.Final.model.service.serviceInterface.CommentServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentServiceInterface {
    private final CommentRepository commentRepository;
    private final ExpertService expertService;
    private final CustomerService customerService;

    public CommentService(CommentRepository commentRepository, ExpertService expertService, CustomerService customerService) {
        this.commentRepository = commentRepository;
        this.expertService = expertService;
        this.customerService = customerService;
    }

    @Override
    public List<Comment> findCommentByExpert(Expert expert) {
        if (expertService.findExpertById(expert.getId()) == null) throw new LogicErrorException("comment expert not found");
        List<Comment> comments = commentRepository.findCommentByExpert(expert);
        if (comments == null) throw new LogicErrorException("comment list is empty");
        return comments;
    }

    @Override
    public List<Comment> findCommentByCustomer(Customer customer) {
        if (customerService.findCustomerById(customer.getId()) == null) throw new LogicErrorException("comment customer not found");
        List<Comment> comments = commentRepository.findCommentByCustomer(customer);
        if (comments == null) throw new LogicErrorException("comment list is empty");
        return comments;
    }

    public void create(Comment comment, Integer customerId, Integer expertId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) throw new LogicErrorException("comment customer not found");
        Expert expert = expertService.findExpertById(expertId);
        if (expert == null) throw new LogicErrorException("comment expert not found");
        comment.setCustomer(customer);
        comment.setExpert(expert);
        if (comment.getPoint()) expert.setPoint(expert.getPoint() + 1);
        else expert.setPoint(expert.getPoint() - 1);
        if (customerService.findCustomerById(comment.getCustomer().getId()) == null) throw new LogicErrorException("comment customer not found");
        commentRepository.save(comment);
    }
}
