package repository;

import Connection.SessionFactorySingleton;
import entity.ExpertUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpertUserRepository extends CRUDRepository<ExpertUser> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public ExpertUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from ExpertUser as a where a.id = :id", ExpertUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<ExpertUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from ExpertUser as a", ExpertUser.class);
        return query.list();
    }

    public List<ExpertUser> findByParameters(String firstName, String lastName,
                                               String nationalCode, String email,
                                               LocalDateTime registerTimeDate) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var contactQuery = criteriaBuilder.createQuery(ExpertUser.class);
        var root = contactQuery.from(ExpertUser.class);
        contactQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("firstName"), firstName));
        }

        if (lastName != null && !lastName.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("lastName"), lastName));
        }

        if (nationalCode != null && !nationalCode.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("nationalCode"), nationalCode));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("email"), email));
        }
        if (registerTimeDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("registertime"), registerTimeDate));
        }
        contactQuery
                .where(predicates.toArray(new javax.persistence.criteria.Predicate[0]))
                .orderBy(criteriaBuilder.asc(root.get("firstName")));
        return session.createQuery(contactQuery).list();
    }
}
