package service;

import Connection.SessionFactorySingleton;
import entity.Service;
import entity.enums.ServiceModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.ServiceRepository;
import service.baseService.CRUDService;

public class ServiceService extends CRUDService<Service> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    public Service save(Service service) {
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                if (service.getBasePrice() == null || service.getBasePrice() == 0 || service.getBasePrice() <= 100) {
                    throw new LogicException("you must have price over 100 coin");
                }
                if (service.getName().equals(serviceRepository.findById(service.getId()).getName())) {
                    throw new LogicException("we can't have same name for services");
                }
                if (service.getModel().equals(ServiceModel.sub)) {
                    if (serviceRepository.findById(service.getParentService().getId()) == null) {
                        throw new LogicException("every sub service must have a created main service");
                    }
                }
            } catch (Exception e) {
                e.getMessage();
                transaction.rollback();
            }
        }
        return super.save(service);
    }
}
