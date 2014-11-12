package it.tesoro.monprovv.utils;


public interface Constants {

	//STATO
	public static String ASSEGNATO = "ASS" ;
	public static String ACCETTATO = "ACC" ;
	public static String RIFIUTATO = "RIF" ;
	public static String RICHIESTO = "RIC" ; 
	public static String FINE_LAVORAZIONE = "FLA" ; 
	public static Integer INSERITO_ID = 2 ;
	public static Integer ASSEGNATO_ID = 9 ;
	public static Integer RIFIUTATO_ID = 10 ;
	
	//ACTION SUBMIT
	public static String SALVA = "Salva" ;
	public static String MODIFICA = "Modifica" ;
	public static String AVANTI = "Avanti" ;
	public static String INDIETRO = "Indietro" ;
	
	//TIPO OPERAZIONI STORICO
	public static String AGGIORNAMENTO = "Aggiornamento" ;
	public static String INSERIMENTO = "Inserimento" ;
	public static String CANCELLAZIONE = "Cancellazione" ;
	
	// STATO PROVVEDIMENTO
	public static final String STATO_EVENTUALE = "EVT";	
	public static final String STATO_INSERITO = "INS";	
	public static final String STATO_IN_ISTRUTTORIA = "ISR";
	public static final String STATO_SOSPESO = "SOS";
	public static final String STATO_CHIUSURA_LAVORI = "CHL";	
	public static final String STATO_ADOTTATO = "ADO";
	public static final String STATO_NON_ATTUABILE = "NOA";	
	public static final String STATO_SUPERATO = "SUP";
	

	//TIPO PROVVEDIMENTO
	public static String CONCERTANTE_MEF = "CONC_MEF" ;
	
	// REPORT TIPI
	public static final String TIPO_PDF = "PDF";
	public static final String TIPO_XLS = "XLS";
}
