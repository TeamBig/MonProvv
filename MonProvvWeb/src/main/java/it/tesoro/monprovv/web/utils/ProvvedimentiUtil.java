package it.tesoro.monprovv.web.utils;

import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Assegnazione;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.utils.StringUtils;

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
			ret.append("<td><a href=\"downloadAllegato/"+allegato.getId()+"\" class=\"download\">"+allegato.getDescrizione()+"</a></td>");
			ret.append("<td>"+StringUtils.convertBytesToKb(allegato.getDimensione(),true)+"</td>");
			ret.append("<td class=\"vcenter center\"><a href=\"javascript:void(0)\" id=\"eliminaAllegato\" ><i class=\"icon-trash icon-large gray\" title=\"Elimina allegato\"></i></a></td>");
			ret.append("</tr>");
			return ret.toString();
		}
		return null;
	}
	
	public static String addRowTableAssegnatariAjax(Assegnazione assegnazione,boolean isNuovoProvv){
		if(StringUtils.isNotEmpty(assegnazione)){
			StringBuffer ret = new StringBuffer();
			if(isNuovoProvv){
				ret.append("<tr>");
				ret.append("<td class=\"hidden\">"+assegnazione.getId()+"</td>");
				ret.append("<td>"+assegnazione.getOrgano().getDenominazione()+"</td>");
				ret.append("<td class=\"vcenter center\"><a href=\"javascript:void(0)\" id=\"eliminaAssegnazione\" ><i class=\"icon-trash icon-large\" title=\"Elimina assegnazione\"></i></a></td>");
				ret.append("</tr>");	
			} else {
				ret.append("<tr>");
				ret.append("<td class=\"hidden\">"+assegnazione.getId()+"</td>");
				ret.append("<td>"+assegnazione.getOrgano().getDenominazione()+"</td>");
				ret.append("<td class=\"vcenter center\"></td>");
				ret.append("<td></td>");
				ret.append("<td></td>");
				ret.append("<td class=\"vcenter center\"><a href=\"#modalCronologia\" role=\"button\" data-toggle=\"modal\"><i class=\"icon-time icon-large\"></i></a></td>");
				ret.append("<td class=\"vcenter center\"><a href=\"javascript:void(0)\" id=\"eliminaAssegnazione\" ><i class=\"icon-trash icon-large\" title=\"Elimina assegnazione\"></i></a></td>");
				ret.append("</tr>");
			}
			return ret.toString();
		}
		return null;
	}
	
	public static void gestioneSalvaAllegati(Provvedimento provvedimento, Provvedimento provvAggiornato, GestioneProvvedimentoFacade gestioneProvvedimentoFacade) {
		Allegato ele;
		 //Elimino gli allegati rimossi in maschera
		 for(Integer tmp: provvedimento.getIdAllegatiDelList()){
			 if(tmp!=null){
				 gestioneProvvedimentoFacade.eliminaAllegato(tmp); 
			 }
		 }
		 //Setto il provvedimento per i nuovi allegati
		 for(Integer tmp: provvedimento.getIdAllegatiUpdList()){
			 if(tmp!=null){
				 ele = gestioneProvvedimentoFacade.getAllegatoByIdnoProv(tmp);
				 if(ele!=null && ele.getProvvedimento()==null){
					 ele.setProvvedimento(provvAggiornato);
					 gestioneProvvedimentoFacade.aggiornaAllegato(ele);
				 }
			 }
		 }
	}

}
