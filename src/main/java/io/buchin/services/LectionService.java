package io.buchin.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.buchin.exception.LectionDAOException;
import io.buchin.models.dao.LectionDAO;
import io.buchin.models.pojo.Lection;

import java.util.List;

/**
 * Created by fedinskiy on 24.02.17.
 */
@Component
public class LectionService {
	private static Logger logger = Logger.getLogger(LectionService.class);
	@Autowired
	private LectionDAO lectionDAO;
	
	public List<Lection> getAllLections() {
		try {
			return lectionDAO.getAllLections();
		} catch (LectionDAOException e) {
			return null;
		}
	}
	
	public boolean deleteLections(String[] chosen) {
		if (null==chosen) return false;
		if (chosen.length==0) return false;
		
		try {
			lectionDAO.deleteLectionsById(chosen);
		} catch (LectionDAOException e) {
			return false;
		}
		return true;
	}
	
	
	public Lection getLectionById(String id) {
		if (null==id) return null;
		if (id.isEmpty()) return null;
		try {
			return lectionDAO.getLectionById(id);
		} catch (LectionDAOException e) {
			return null;
		}
	}
	
	public void saveLection(Lection lection) {
		if (null==lection) return;
		try {
			lectionDAO.saveLection(lection);
		} catch (LectionDAOException e) {
			logger.error(e);
		}
	}
}
