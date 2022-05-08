package com.Maktab.Final.service;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.entity.Wallet;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import com.Maktab.Final.repository.ExpertRepo;
import com.Maktab.Final.service.serviceInterface.ExpertServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpertServ implements ExpertServInt {
    //---------------------------------------------------------needed
    private final ExpertRepo expertRepo;
    private final OfferServ offerServ;
    private final SubServiceServ subServiceServ;
    private final Expert_ServiceServ expert_serviceServ;
    private final WalletServ walletServ;

    public ExpertServ(ExpertRepo expertRepo, OfferServ offerServ, SubServiceServ subServiceServ, Expert_ServiceServ expert_serviceServ, WalletServ walletServ) {
        this.expertRepo = expertRepo;
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
        offer.setExpert(expert);
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
}
