package service;

import Connection.SessionFactorySingleton;
import entity.Service;
import entity.enums.ServiceModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.ServiceRepository;
import service.baseService.CRUDService;

import java.util.List;

public class ServiceService extends CRUDService<Service> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    public Service save(Service service) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                if (service.getModel().equals(ServiceModel.sub) & service.getBasePrice() == null || service.getModel().equals(ServiceModel.sub) & service.getBasePrice() <= 100) {
                    throw new LogicException("you must have price over 100 coin");
                }
                List<Service> services = serviceRepository.findAll();
                for (Service s :
                        services) {
                    if (service.getName().equals(s.getName())) {
                        throw new LogicException("we can't have same name for services");
                    }
                }
                if (service.getModel().equals(ServiceModel.sub)) {
                    if (serviceRepository.findById(service.getParentService().getId()) == null) {
                        throw new LogicException("every sub service must have a created main service");
                    }
                }
                serviceRepository.save(service);
                transaction.commit();
                return service;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
                return null;
            }
        }
    }
    public Service findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return serviceRepository.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
