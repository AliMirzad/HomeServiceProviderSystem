package service;

import Connection.SessionFactorySingleton;
import entity.*;
import entity.enums.OrderStatus;
import entity.enums.TransactionModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.SysTransactionRepo;
import service.baseService.CRUDService;

import java.util.List;

public class SysTransactionServ extends CRUDService<SysTransaction> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final SysTransactionRepo transactionRepo = new SysTransactionRepo();
    private final OfferServ offerServ = new OfferServ();
    private final CustomerServ customerServ = new CustomerServ();
    private final ExpertServ expertServ = new ExpertServ();
    private final OrderServ orderServ = new OrderServ();
    private final WalletServ walletServ = new WalletServ();

    public void payForOrder(Integer customer, Integer expert, Integer offer, Integer order) {
        try {
            Customer customer1 = customerServ.findById(customer);
            Expert expert1 = expertServ.findById(expert);
            Offer offer1 = offerServ.findById(offer);
            Order order1 = orderServ.findById(order);
            Wallet eWallet = expert1.getWallet();
            Wallet cWallet = customer1.getWallet();
            Double cRemain = cWallet.getBalance();
            Double eRemain = eWallet.getBalance();
            if (order1.getStatus().equals(OrderStatus.done)) {
                if (cWallet.getBalance() <= offer1.getOfferPrice()) {
                    throw new LogicException("you don't have enough coin");
                }
                cWallet.setBalance(cWallet.getBalance() - offer1.getOfferPrice());
                eWallet.setBalance(eWallet.getBalance() + offer1.getOfferPrice());
                walletServ.update(cWallet);
                walletServ.update(eWallet);
                order1.setStatus(OrderStatus.paid);
                orderServ.update(order1);
            }
            SysTransaction withdrew = SysTransaction.builder().model(TransactionModel.withdrew).walletId(cWallet.getId()).remain(cRemain).amount(cWallet.getBalance()).build();
            SysTransaction deposit = SysTransaction.builder().model(TransactionModel.deposit).walletId(eWallet.getId()).remain(eRemain).amount(cWallet.getBalance()).build();
            super.save(withdrew);
            super.save(deposit);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public SysTransaction findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return transactionRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<SysTransaction> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return transactionRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
