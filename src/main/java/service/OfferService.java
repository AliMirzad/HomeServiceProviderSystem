package service;

import Connection.SessionFactorySingleton;
import entity.ExpertUser;
import entity.Offer;
import entity.Order;
import entity.enums.ExpertUserStatus;
import entity.enums.OrderStatus;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.OfferRepository;
import service.baseService.CRUDService;

import java.util.List;

public class OfferService extends CRUDService<Offer> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final OfferRepository offerRepository = new OfferRepository();
    private final OrderService orderService = new OrderService();
    private final ExpertUserService expertUserService = new ExpertUserService();

    @Override
    public Offer save(Offer offer) {
        try {
            Order order = orderService.findById(offer.getOrder().getId());
            ExpertUser expertUser = expertUserService.findById(offer.getExpertUser().getId());
            offer.setOrder(order);
            offer.setExpertUser(expertUser);
            if (offer.getExpertUser() == null) {
                throw new LogicException("for register offer you must set expert");
            }
            if (offer.getExpertUser().getStatus().equals(ExpertUserStatus.waiting)) {
                throw new LogicException("expert account must be accepted");
            }
            if (offer.getOrder() == null) {
                throw new LogicException("you need valid order to register offer");
            }
            if (offer.getRegistrationDateTime().isBefore(offer.getOrder().getOrderRegisterTime())) {
                throw new LogicException("offer date time must be after order register date time");
            }
            if (offer.getStartTime().isBefore(offer.getRegistrationDateTime()) || offer.getEndTime().isBefore(offer.getStartTime())) {
                throw new LogicException("offer start time must be after register and end time must be after start");
            }
            Order order1 = offer.getOrder();
            order.setStatus(OrderStatus.suggestion);
            orderService.update(order1);
            try (var session = sessionFactory.getCurrentSession()) {
                var transaction = session.getTransaction();
                try {
                    transaction.begin();
                    offerRepository.save(offer);
                    transaction.commit();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public Offer findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return offerRepository.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<Offer> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return offerRepository.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
