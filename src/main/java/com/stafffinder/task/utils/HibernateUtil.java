package com.stafffinder.task.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	
  private static final SessionFactory sessionFactory;
  
    static {
      try {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        throw new ExceptionInInitializerError(e);
      }
    }

    public static SessionFactory getSessionFactory() {
      return sessionFactory;
    }
    
}