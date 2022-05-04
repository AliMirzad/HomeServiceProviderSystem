package repository;

import Connection.SessionFactorySingleton;
import entity.Comment;
import org.hibernate.SessionFactory;

import java.util.List;

public class CommentRepo {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Comment findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Comment as a where a.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Comment> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Comment as a", Comment.class);
        return query.list();
    }
}
