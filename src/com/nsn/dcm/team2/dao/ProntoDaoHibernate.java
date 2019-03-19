package com.nsn.dcm.team2.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.nsn.dcm.team2.model.*;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class ProntoDaoHibernate extends HibernateDaoSupport implements
		ProntoDao {


	public void save(Pronto pr) {
		getHibernateTemplate().save(pr);
	}

	public void update(Pronto pr) {
		getHibernateTemplate().saveOrUpdate(pr);
	}



	public void delete(Pronto pr) {
		getHibernateTemplate().delete(pr);
	}

	@SuppressWarnings("unchecked")
	public List<Pronto> findAll() {
		return (List<Pronto>) getHibernateTemplate().find("from Pronto");
	}

	public Pronto findById(java.lang.Integer id) {

		try {
			Pronto instance = (Pronto) getHibernateTemplate().get(
					"ProntoTable", id);
			if (instance == null) {

			} else {

			}
			return instance;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public Pronto get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
