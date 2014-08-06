package com.nsn.cmatrix.dao.impl;

import org.springframework.stereotype.Repository;

import com.nsn.cmatrix.dao.DAOProject;
import com.nsn.cmatrix.model.ModelProject;

@Repository("daoProject")
public class DAOProjectImpl 
extends DAOBaseImpl<ModelProject> implements DAOProject
{

}
