package it.tesoro.monprovv.web.controllers;

import it.tesoro.monprovv.facade.GestionePregressoFacade;
import it.tesoro.monprovv.facade.GestioneProvvedimentoFacade;
import it.tesoro.monprovv.model.Allegato;
import it.tesoro.monprovv.model.Organo;
import it.tesoro.monprovv.model.Provvedimento;
import it.tesoro.monprovv.model.Stato;
import it.tesoro.monprovv.sicurezza.CustomUser;
import it.tesoro.monprovv.web.utils.AlertUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class AmministratoreController {

	protected static Logger logger = Logger.getLogger(AmministratoreController.class);

	@Autowired
	private GestionePregressoFacade gestionePregressoFacade;
	
	@Autowired
	private GestioneProvvedimentoFacade gestioneProvvedimentoFacade;
	
	@Autowired
	private AlertUtils alertUtils;
	
	@RequestMapping(value= {"/private/admin"}, method = RequestMethod.GET)
	public String caricaHomepagePrivata(Model model, SecurityContextHolderAwareRequestWrapper request)  {
		String redirectUrl = null;
		
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUser) {
			redirectUrl = "/private/admin/enti";
		} else {
			redirectUrl = "/public";
		}
		
		return "redirect:" + redirectUrl;
	}
	
	@RequestMapping(value = "/private/admin/manuale", method = RequestMethod.GET)
	public String manuale(Model model) {
		return "guida";
	}
	
	@RequestMapping(value = "/private/admin/manuale", method = RequestMethod.POST, params = "procedi")
	public String uploadManuale(MultipartHttpServletRequest request, HttpServletResponse response, Model model) {
		
		try {
			
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = request.getFile(itr.next());
			
					
			Allegato allegato = gestioneProvvedimentoFacade.getAllegatoById(-1);
			if (allegato == null) {
				alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, "Manuale non trovato sul database", false);
				return "guida";
			}
			
			Blob manuale = Hibernate.createBlob(file.getBytes());
			allegato.setContenuto(manuale);
			allegato.setDimensione(file.getBytes().length);
			
			gestioneProvvedimentoFacade.aggiornaManuale(allegato);
			
			alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Manuale aggiornato con successo", false);
			return "guida";
		} catch (Exception e) {
			alertUtils.message(model, AlertUtils.ALERT_TYPE_ERROR, "Errore generico", false);
			return "guida";
		}
	}
	
	
	@RequestMapping(value = "/private/admin/pregresso", method = RequestMethod.GET)
	public String pregresso(Model model) {
		return "pregresso";
	}
	
	@RequestMapping(value = "/private/admin/pregresso", method = RequestMethod.POST, params = "procedi")
	public String avviaCaricamento(MultipartHttpServletRequest request, HttpServletResponse response) {
		
		
		impostaUtente();
		
		
		try {
			
			Iterator<String> itr = request.getFileNames();
			MultipartFile file = request.getFile(itr.next());
			
			InputStream fis = new ByteArrayInputStream(file.getBytes());
			
			Workbook workbook = WorkbookFactory.create(fis);
			
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			CellStyle styleWhite = workbook.createCellStyle();
			styleWhite.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			styleWhite.setFillPattern(CellStyle.SOLID_FOREGROUND);

			CellStyle styleYellow = workbook.createCellStyle();
			styleYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			styleYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			
			Sheet sheet = workbook.getSheetAt(1);
			
			Organo organoMinistero = gestionePregressoFacade.recuperaOrganoByDenominazione("MINISTERO DELL'ECONOMIA E DELLE FINANZE");
			Stato statoInserito = gestionePregressoFacade.recuperaStatoByDescrizione("Inserito");
			
			Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
             
                try {
                
	                int j = row.getRowNum();
	                
	                // salto la prima riga
	                if (j == 0) {
	                	continue; 
	                }
	                
	                System.err.println("row="+ row.getRowNum());
	
	                Provvedimento provvedimento = new Provvedimento();
	                provvedimento.setOrganoCapofila(organoMinistero);
	                provvedimento.setOrganoInseritore(organoMinistero);
	                
	                // governo
	                int index = j + 1;
	                CellReference cellReference = new CellReference("A" + index); 
	                String value = evaluateCell(cellReference, sheet, evaluator);
	                if (value == null) {
	                	continue;
	                }
	                provvedimento.setGoverno(gestionePregressoFacade.recuperaGovernoByCodice(value));
	                
	                // tipo provvedimento
	                cellReference = new CellReference("D" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                provvedimento.setTipoProvvedimento(gestionePregressoFacade.recuperaTipoProvvedimentoByDescrizione(value));
	                
	                // fonte normativa
	                cellReference = new CellReference("G" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                provvedimento.setFonteNormativa(value);
	                
	                // articolo
	                cellReference = new CellReference("I" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                if (value != null) {
	                	provvedimento.setArticolo(value.replaceAll("\\.0", ""));
	                }
	                
	                // comma
	                cellReference = new CellReference("J" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                if (value != null) {
	                	provvedimento.setComma(value.replaceAll("\\.0", ""));
	                }
	                
	                
	                // tipo provvedimento da adottare
	                cellReference = new CellReference("M" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                provvedimento.setTipoProvvDaAdottare(gestionePregressoFacade.recuperaTipoProvvedimentoDaAdottareByDescrizione(value));
	                
	                // titolo oggetto
	                cellReference = new CellReference("N" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                provvedimento.setOggetto(Hibernate.createClob(value));
	                
	
	                // stato di attuazione
	                cellReference = new CellReference("T" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                Stato stato = gestionePregressoFacade.recuperaStatoByDescrizione(value);
	                if (stato == null) {
	                	provvedimento.setStato(statoInserito);
	                } else {
	                	provvedimento.setStato(stato);
	                }
	                
	                // annotazione ad uso interno
	                cellReference = new CellReference("U" + index);
	                value = evaluateCell(cellReference, sheet, evaluator);
	                if (value != null) {
	                	provvedimento.setNoteInterne(value.substring(0, Math.min(value.length() - 1, 3999)));
	                }
	                
	                
	                // salvataggio
	                gestionePregressoFacade.inserisciProvvedimento(provvedimento);
	                
                } catch (Exception ex) {
                	
                	System.out.println("ROW ERROR " + row.getRowNum());
                	row.getCell(0).setCellStyle(styleYellow);
                }
            }
            
           
            response.setHeader("Content-Disposition", "attachment;filename=\""
					+ "risultati.xlsx\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			workbook.write(out);

			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}		
	
		return null;
	}

	
	@RequestMapping(value = "/private/admin/pregresso", method = RequestMethod.POST, params = "cancellaPregresso")
	public String cancellaPregresso(MultipartHttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		gestionePregressoFacade.eliminaPregresso();
		
		alertUtils.message(model, AlertUtils.ALERT_TYPE_SUCCESS, "Pregresso cancellato", false);
		return "pregresso";
	}	
	
	
	private void impostaUtente() {
		
		CustomUser user = new CustomUser("PREGRESSO", "pregresso", true, true, true, true, new ArrayList<GrantedAuthority>() ); 
		
	   	Authentication authentication = new UsernamePasswordAuthenticationToken(user, "pregresso");
        SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
	
	private String evaluateCell(CellReference cellRef, Sheet sheet, FormulaEvaluator evaluator) {
		Cell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());
		if (cell != null) {
			CellValue cellValue = evaluator.evaluate(cell);
			if (cellValue != null) {
				switch (cellValue.getCellType()) {
			    case Cell.CELL_TYPE_BOOLEAN:
			        return String.valueOf(cellValue.getBooleanValue());
			    case Cell.CELL_TYPE_NUMERIC:
			    	return String.valueOf(cellValue.getNumberValue());
			    case Cell.CELL_TYPE_STRING:
			        return cellValue.getStringValue();
			    case Cell.CELL_TYPE_BLANK:
			        break;
			    case Cell.CELL_TYPE_ERROR:
			        break;
	
			    // CELL_TYPE_FORMULA will never happen
			    case Cell.CELL_TYPE_FORMULA: 
			        break;
				}
			}
		}
		return null;
	}
	
	
}