package com.Maktab.Final.service;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.entity.enums.OfferStatus;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import com.Maktab.Final.repository.ExpertCriteriaRepo;
import com.Maktab.Final.repository.ExpertRepo;
import com.Maktab.Final.service.serviceInterface.ExpertServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class ExpertServ implements ExpertServInt {
    //---------------------------------------------------------needed
    private final ExpertRepo expertRepo;
    private final ExpertCriteriaRepo expertCriteriaRepo;
    private final OfferServ offerServ;
    private final SubServiceServ subServiceServ;
    private final Expert_ServiceServ expert_serviceServ;
    private final WalletServ walletServ;
    private final OrderServ orderServ;

    public ExpertServ(ExpertRepo expertRepo, ExpertCriteriaRepo expertCriteriaRepo, @Lazy OfferServ offerServ, @Lazy SubServiceServ subServiceServ, @Lazy Expert_ServiceServ expert_serviceServ, @Lazy WalletServ walletServ,@Lazy OrderServ orderServ) {
        this.expertRepo = expertRepo;
        this.expertCriteriaRepo = expertCriteriaRepo;
        this.offerServ = offerServ;
        this.subServiceServ = subServiceServ;
        this.expert_serviceServ = expert_serviceServ;
        this.walletServ = walletServ;
        this.orderServ = orderServ;
    }

    //---------------------------------------------------------methods
    @Override
    public Expert findExpertById(Integer id) {
        try {
            Expert expert = expertRepo.findExpertById(id);
            if (expert == null) throw new LogicException("we dont have this expert");
            return expert;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password) {
        try {
            Expert expert = expertRepo.findExpertByNationalCodeAndPassword(nationalCode, password);
            if (expert == null) throw new LogicException("we dont have this expert");
            return expert;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }

    }

    @Transactional
    public void createExpert(Expert expert) {
        try {
            Expert expert1 = expertRepo.findExpertByNationalCodeAndPassword(expert.getNationalCode(), expert.getPassword());
            if (expert1 != null) throw new LogicException("we already have this expert");
            if (expert.getEmail() == null) throw new LogicException("we need email for expert");
            expert.setRegisterTime(LocalDateTime.now());
            expert.setPoint(100);
            expertRepo.save(expert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        try {
            Expert expert = findExpertById(id);
            if (expert == null) throw new LogicException("we dont have this expert");
            if (expert.getPassword().equals(newPassword)) throw new LogicException("this is your old password");
            expert.setPassword(newPassword);
            expertRepo.save(expert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveOffer(Integer id, Offer offer) {
        try {
            Expert expert = expertRepo.findExpertById(id);
            if (expert == null) throw new LogicException("we dont have this expert");
            Order order = orderServ.findOrderById(offer.getOrder().getId());
            if (order == null) throw new LogicException("we need order");
            offer.setRegistrationDateTime(LocalDateTime.now());
            offer.setExpert(expert);
            offer.setStatus(OfferStatus.pending);
            offer.setOrder(order);
            offerServ.save(offer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveSubService(Integer expertId, Integer subServiceId) {
        try {
            Expert expert = expertRepo.findExpertById(expertId);
            if (expert == null) throw new LogicException("we dont have this expert");
            SubService subService = subServiceServ.findSubServiceById(subServiceId);
            if (subService == null) throw new LogicException("we dont have this sub service");
            Expert_Service expert_service = Expert_Service.builder().expert(expert).subService(subService).build();
            expert_serviceServ.save(expert_service);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveWallet(Integer id, Wallet wallet) {
        try {
            Expert expert = expertRepo.findExpertById(id);
            if (expert == null) throw new LogicException("we dont have this expert");
            wallet.setExpert(expert);
            walletServ.save(wallet);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Override
    public Page<Expert> getExpert(ExpertPage expertPage, ExpertSearchCriteria expertSearchCriteria) {
        return expertCriteriaRepo.findAllWithFilters(expertPage, expertSearchCriteria);
    }

    @Transactional
    public void update(Expert expert) {
        expertRepo.save(expert);
    }
}
