package com.nsn.dcm.team2.dao;

import java.util.List;
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
public interface ProntoDao {
	Pronto get(Integer id);

	void save(Pronto pr);

	void update(Pronto pr);

	void delete(Integer id);

	void delete(Pronto pr);

	List<Pronto> findAll();

}