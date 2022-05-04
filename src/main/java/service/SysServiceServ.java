package service;

import Connection.SessionFactorySingleton;
import entity.Expert;
import entity.SysService;
import entity.enums.ServiceModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.SysServiceRepo;
import service.baseService.CRUDService;

import java.util.List;

public class SysServiceServ extends CRUDService<SysService> {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private SysServiceRepo sysServiceRepo = new SysServiceRepo();
    @Override
    public SysService save(SysService sysService) {
        try {
            for (SysService s:
                 findAll()) {
                if (sysService.getName().equals(s.getName())) {
                    throw new LogicException("we can't add service with same name");
                }
            }
            if (sysService.getModel().equals(ServiceModel.sub)) {
                if (sysService.getBasePrice() == null || sysService.getBasePrice() <= 100) {
                    throw new LogicException("base price for service must be at least 100 coin");
                }
            }
            if (sysService.getModel().equals(ServiceModel.main)) {
                if (sysService.getBasePrice() != null) {
                    throw new LogicException("main service have not base price");
                }
            }
            if (sysService.getModel() == null) {
                throw new LogicException("service must have a model");
            }
            if (sysService.getModel().equals(ServiceModel.sub)) {
                if (sysService.getParentService() == null) {
                    throw new LogicException("every sub service must have main service");
                }
            }
            return super.save(sysService);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    public void deleteById(Integer id) {
        SysService sysService = findById(id);
        if (sysService.getModel().equals(ServiceModel.main)) {
            for (SysService s:
                 findAll()) {
                if (s.getParentService().getId() == sysService.getId()) {
                    super.delete(s);
                }
            }
        }
        super.delete(sysService);
    }

    public void updateInformation(Integer id, String name, String description, Double basePrice, ServiceModel model) {
        SysService sysService = findById(id);
        sysService.setName(name);
        sysService.setDescription(description);
        sysService.setBasePrice(basePrice);
        sysService.setModel(model);
        super.update(sysService);
    }

    public SysService findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return sysServiceRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<SysService> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return sysServiceRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
