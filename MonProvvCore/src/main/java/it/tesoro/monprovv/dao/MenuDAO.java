package it.tesoro.monprovv.dao;

import java.util.List;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.Menu;

import org.springframework.stereotype.Component;

@Component("menuDAO")
public class MenuDAO extends AbstractCommonDAO<Menu> {
	
	public List<Menu> findFirstLevelOrdered() {

		String hql = "from Menu as m where m.parentMenu is null order by m.ordinamento";
		return findByHqlQuery(hql, null);
	}
	
}
