package service;

import Connection.SessionFactorySingleton;
import entity.AdminUser;
import org.hibernate.SessionFactory;
import service.baseService.CRUDService;

public class AdminUserService extends CRUDService<AdminUser> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
}
