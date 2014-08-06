package com.nsn.cmatrix.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractBaseDao {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  protected final Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }
}
