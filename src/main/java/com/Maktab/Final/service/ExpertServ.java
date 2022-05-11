package com.Maktab.Final.service;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.entity.enums.OfferStatus;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import com.Maktab.Final.repository.ExpertCriteriaRepo;
import com.Maktab.Final.repository.ExpertRepo;
import com.Maktab.Final.service.serviceInterface.ExpertServInt;
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

    public ExpertServ(ExpertRepo expertRepo, ExpertCriteriaRepo expertCriteriaRepo, OfferServ offerServ, SubServiceServ subServiceServ, Expert_ServiceServ expert_serviceServ, WalletServ walletServ) {
        this.expertRepo = expertRepo;
        this.expertCriteriaRepo = expertCriteriaRepo;
        this.offerServ = offerServ;
        this.subServiceServ = subServiceServ;
        this.expert_serviceServ = expert_serviceServ;
        this.walletServ = walletServ;
    }

    //---------------------------------------------------------methods
    @Override
    public Expert findExpertById(Integer id) {
        return expertRepo.findExpertById(id);
    }

    @Override
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password) {
        return expertRepo.findExpertByNationalCodeAndPassword(nationalCode, password);
    }

    @Transactional
    public void createExpert(Expert expert) {
        expert.setPoint(100);
        expertRepo.save(expert);
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        Expert expert = findExpertById(id);
        expert.setPassword(newPassword);
        expertRepo.save(expert);
    }

    @Transactional
    public void saveOffer(Integer id, Offer offer) {
        Expert expert = expertRepo.findExpertById(id);
        offer.setRegistrationDateTime(LocalDateTime.now());
        offer.setExpert(expert);
        offer.setStatus(OfferStatus.pending);
        offerServ.save(offer);
    }

    @Transactional
    public void saveSubService(Integer expertId, Integer subServiceId) {
        Expert expert = expertRepo.findExpertById(expertId);
        SubService subService = subServiceServ.findSubServiceById(subServiceId);
        Expert_Service expert_service = Expert_Service.builder().expert(expert).subService(subService).build();
        expert_serviceServ.save(expert_service);
    }

    @Transactional
    public void saveWallet(Integer id, Wallet wallet) {
        Expert expert = expertRepo.findExpertById(id);
        wallet.setExpert(expert);
        walletServ.save(wallet);
    }

    @Transactional
    public void update(Expert expert) {
        expertRepo.save(expert);
    }

    @Override
    public Page<Expert> getCustomers(ExpertPage expertPage,
                                       ExpertSearchCriteria expertSearchCriteria) {
        return expertCriteriaRepo.findAllWithFilters(expertPage, expertSearchCriteria);
    }
}
