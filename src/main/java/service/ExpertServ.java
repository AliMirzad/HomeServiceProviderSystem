package service;

import Connection.SessionFactorySingleton;
import entity.*;
import entity.enums.ExpertUserStatus;
import entity.enums.OrderStatus;
import entity.enums.ServiceModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.ExpertRepo;
import service.baseService.CRUDService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpertServ extends CRUDService<Expert> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ExpertRepo expertRepo = new ExpertRepo();
    private final WalletServ walletServ = new WalletServ();
    private final SysServiceServ sysServiceServ = new SysServiceServ();
    private final OrderServ orderServ = new OrderServ();
    private final OfferServ offerServ = new OfferServ();
    private final CustomerServ customerServ = new CustomerServ();

    @Override
    public Expert save(Expert expert) {
        try {
            Wallet wallet = Wallet.builder().balance(100D).build();
            walletServ.save(wallet);
            expert.setWallet(wallet);
            if (expert.getEmail().isEmpty() || expert.getEmail() == null) {
                throw new LogicException("we can't add expert without email");
            }
            for (Expert e :
                    findAll()) {
                if (expert.getEmail().equals(e.getEmail())) {
                    throw new LogicException("this email used before");
                }
            }
            return super.save(expert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public void delete(Expert expert) {
        try {
            Expert expert1 = findById(expert.getId());
            if (expert1.getWallet() == null) {
                throw new LogicException("how you dont have wallet");
            }
            super.delete(expert);
            walletServ.delete(expert1.getWallet());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void saveService(Integer id, Integer serviceId) {
        try {
            Expert expert = findById(id);
            if (expert == null) {
                throw new LogicException("this expert not found");
            }
            SysService sysService = sysServiceServ.findById(serviceId);
            if (sysService == null) {
                throw new LogicException("this service not found");
            }
            Set<SysService> services = expert.getServices();
            services.add(sysService);
            expert.setServices(services);
            expert.setStatus(ExpertUserStatus.accepted);
            super.update(expert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void saveOffer(Integer id,Integer orderId, Offer offer) {
        try {
            Order order = orderServ.findById(orderId);
            if (order == null) throw new LogicException("order must be in db or we can't do this work");
            Expert expert = findById(id);
            if (expert == null) throw new LogicException("how you want offer and you aren't expert");
            Set<Offer> offers = order.getOffers();
            if (offer.getOfferPrice() == null) {
                throw new LogicException("you need an price for this order");
            }
            if (offer.getOfferPrice() <= order.getOfferPrice()) {
                throw new LogicException("you can't do work cheaper then order offer");
            }
            if (offer.getStartTime().isBefore(LocalDateTime.now())) {
                throw new LogicException("you can't offer before this time");
            }
            if (offer.getStartTime().isBefore(order.getOrderRegisterTime())) {
                throw new LogicException("how you wan't do thiss before it orderd?");
            }
            if (offer.getEndTime().isBefore(offer.getStartTime())) {
                throw new LogicException("you cant end before start time");
            }
            offer.setRegistrationDateTime(LocalDateTime.now());
            offers.add(offer);
            offerServ.save(offer);
            expert.setOffers(offers);
            order.setOffers(offers);
            order.setStatus(OrderStatus.suggestion);
            super.update(expert);
            orderServ.update(order);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void updateInformation(Integer id, String nationalCode, String firstName, String password) {
        Expert expert = findById(id);
        expert.setNationalCode(nationalCode);
        expert.setFirstName(firstName);
        expert.setPassword(password);
        super.update(expert);
    }

    public void updatePassword(Integer id, String password) {
        try {
            Expert expert1 = findById(id);
            expert1.setPassword(password);
            super.update(expert1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void updateService(Integer id, SysService sysService) {
        try {
            SysService sysService1 = sysServiceServ.findById(sysService.getId());
            Expert expert = findById(id);
            if (expert.getStatus().equals(ExpertUserStatus.waiting))
                throw new LogicException("first your account must be activate");
            if (sysService1.getParentService() == null) {
                throw new LogicException("you can only add subService to your services");
            }
            Set<SysService> sysServices = new HashSet<>();
            sysServices.add(sysService1);
            expert.setServices(sysServices);
            super.update(expert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    //TODO: save offer
//    public void saveOffer(Integer id, Offer offer) {
//        try {
//            Order order = orderService.findById(offer.getOrder().getId());
//            Expert expert = findById(id);
//            offer.setOrder(order);
//            offer.setExpertUser(expertUser);
//            if (offer.getExpertUser() == null) {
//                throw new LogicException("for register offer you must set expert");
//            }
//            if (offer.getExpertUser().getStatus().equals(ExpertUserStatus.waiting)) {
//                throw new LogicException("expert account must be accepted");
//            }
//            if (offer.getOrder() == null) {
//                throw new LogicException("you need valid order to register offer");
//            }
//            if (offer.getRegistrationDateTime().isBefore(offer.getOrder().getOrderRegisterTime())) {
//                throw new LogicException("offer date time must be after order register date time");
//            }
//            if (offer.getStartTime().isBefore(offer.getRegistrationDateTime()) || offer.getEndTime().isBefore(offer.getStartTime())) {
//                throw new LogicException("offer start time must be after register and end time must be after start");
//            }
//            Order order1 = offer.getOrder();
//            order.setStatus(OrderStatus.suggestion);
//            orderService.update(order1);
//            try (var session = sessionFactory.getCurrentSession()) {
//                var transaction = session.getTransaction();
//                try {
//                    transaction.begin();
//                    offerRepository.save(offer);
//                    transaction.commit();
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                    return null;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//        return null;
//    }

    public List<Expert> findByParameters(String firstName, String lastName,
                                         String nationalCode, String email,
                                         LocalDateTime registerTimeDate) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertRepo.findByParameters(firstName,
                        lastName,
                        nationalCode,
                        email,
                        registerTimeDate);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }

    public Expert findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertRepo.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }

    public List<Expert> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertRepo.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }
}
