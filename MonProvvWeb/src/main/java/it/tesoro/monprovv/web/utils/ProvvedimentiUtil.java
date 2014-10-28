package it.tesoro.monprovv.web.utils;

import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.util.StringUtils;

import java.io.Serializable;

public class ProvvedimentiUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082301323404159336L;
	
	public static String addRowTableAllegatiAjax(Allegato allegato){
		if(StringUtils.isNotEmpty(allegato)){
			StringBuffer ret = new StringBuffer();
			ret.append("<tr>");
			ret.append("<td>"+allegato.getId()+"</td>");
			ret.append("<td><a href=\"private/ricercaProv/downloadAllegato/"+allegato.getId()+"\" class=\"download\">"+allegato.getDescrizione()+"</a></td>");
			ret.append("<td>"+StringUtils.convertBytesToKb(allegato.getDimensione(),true)+"</td>");
			ret.append("<td class=\"vcenter center\"><a href=\"javascript:void(0)\" id=\"eliminaAllegato\" ><i class=\"icon-trash icon-large\" title=\"Elimina allegato\"></i></a></td>");
			ret.append("</tr>");
			return ret.toString();
		}
		return null;
	}
	
	public static String addRowTableAssegnatariAjax(Assegnazione assegnazione){
		if(StringUtils.isNotEmpty(assegnazione)){
			StringBuffer ret = new StringBuffer();
			ret.append("<tr>");
			ret.append("<td>"+assegnazione.getId()+"</td>");
			ret.append("<td>"+assegnazione.getOrgano().getDenominazione()+"</td>");
			ret.append("<td></td>");
			ret.append("<td></td>");
			ret.append("<td></td>");
			ret.append("<td class=\"vcenter center\"><a href=\"#modalCronologia\" role=\"button\" data-toggle=\"modal\"><i class=\"icon-time icon-large\"></i></a></td>");
			ret.append("<td class=\"vcenter center\"><a href=\"javascript:void(0)\" id=\"eliminaAssegnazione\" ><i class=\"icon-trash icon-large\" title=\"Elimina assegnazione\"></i></a></td>");
			ret.append("<td class=\"vcenter center\"><a href=\"#modalSollecito\" role=\"button\" data-toggle=\"modal\"><i class=\"icon-envelope-alt icon-large\" title=\"Invio sollecito\"></i></a></td>");
			ret.append("</tr>");
			return ret.toString();
		}
		return null;
	}

}
