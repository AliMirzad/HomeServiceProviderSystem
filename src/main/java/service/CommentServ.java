package service;

import Connection.SessionFactorySingleton;
import entity.Comment;
import entity.Customer;
import entity.Expert;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.CommentRepo;
import service.baseService.CRUDService;

import java.util.Set;

public class CommentServ extends CRUDService<Comment> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CommentRepo commentRepo = new CommentRepo();
    private final CustomerServ customerServ = new CustomerServ();
    private final ExpertServ expertServ = new ExpertServ();

    public void saveComment(Integer customerId, Integer expertId, Comment comment) {
        Customer customer = customerServ.findById(customerId);
        if (customer == null) {
         throw new LogicException("we cant add comment w/o recognizing you");
        }
        Expert expert = expertServ.findById(expertId);
        if (expert == null) {
            throw new LogicException("we cant add comment w/o accepted expert");
        }
        Set<Comment> comments = expert.getComments();
        comments.add(comment);
        expert.setComments(comments);
        if (expert.getPoint() == null) {
            expert.setPoint(1);
        }
        if (comment.getPoint() == 1) {
            Integer point = expert.getPoint() + 1;
            expert.setPoint(point);
        }
        if (comment.getPoint() == 0) {
            Integer point = expert.getPoint() - 1;
            expert.setPoint(point);
        }
        super.save(comment);
        expertServ.update(expert);
    }
}
