package it.tesoro.monprovv.dao;

import it.tesoro.monprovv.dao.common.AbstractCommonDAO;
import it.tesoro.monprovv.model.UtenteAstage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("utenteAstageDAO")
public class UtenteAstageDAO extends AbstractCommonDAO<UtenteAstage>{

	public UtenteAstage findByCodiceFiscale(String codiceFiscale) {
		String hql = "from UtenteAstage u where u.codiceFiscale = :codiceFiscale ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codiceFiscale", codiceFiscale);
		
		List<UtenteAstage> risultati =  findByHqlQuery(hql, params);
		if (!CollectionUtils.isEmpty(risultati)) {
			return risultati.get(0);
		}
		return null;
	}

}
