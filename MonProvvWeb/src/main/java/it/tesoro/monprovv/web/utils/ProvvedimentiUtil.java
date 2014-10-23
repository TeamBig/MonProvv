package it.tesoro.monprovv.web.utils;

import it.tesoro.monprovv.model.Allegato;

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
			ret.append("<td>"+allegato.getDescrizione()+"</td>");
			ret.append("<td>"+StringUtils.convertBytesToKb(allegato.getDimensione(),true)+"</td>");
			ret.append("<td></td>");
			ret.append("</tr>");
			return ret.toString();
		}
		return null;
	}

}
