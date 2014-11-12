package it.tesoro.monprovv.service;

import it.tesoro.monprovv.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("reportService")
public class ReportService {
	
	private static final Log log = LogFactory.getLog(ReportService.class);
	

	public ByteArrayOutputStream generaReport(String tipoOutput, String nomeStampa, HashMap<String,Object> parameters, Collection<?> coll) 
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream isRep = this.getClass().getResourceAsStream("stampe/" + nomeStampa + ".jrxml");
		
		if (isRep == null) {
			log.error("Si e' verificato un errore durante la lettura del report /" + nomeStampa + ".jrxml");
			return null;
		}		
		
		try {
					
			JasperReport jr = JasperCompileManager.compileReport(isRep);
			JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JRBeanCollectionDataSource(coll));

			if (Constants.TIPO_PDF.equals(tipoOutput)){
				JRPdfExporter jPdfExporter = new JRPdfExporter();
				jPdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
				jPdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
				jPdfExporter.exportReport();				
			} 
			if (Constants.TIPO_XLS.equals(tipoOutput)){
				JRXlsExporter JrXlsExporter = new JRXlsExporter();
				JrXlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
				JrXlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
				JrXlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				JrXlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				JrXlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				JrXlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				JrXlsExporter.exportReport();
			}			
			
		} catch (JRException e) {
			log.error("Si e' verificato un errore durante la generazione del report /" + nomeStampa + ".jrxml");
			return null;
		}
		
		return baos;
	}
}
