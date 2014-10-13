$(document).ready(function() {

    $('.multiselect').multiselect({
            nonSelectedText: 'Tutti',
            numberDisplayed: 20
	});
    
    var campiRicerca = $("#campiRicerca");
    var btnToggleRicerca = $("#toggleRicerca");
    
    btnToggleRicerca.html("Mostra campi di ricerca &nbsp;<i class=\"icon-search\"></i>");
    campiRicerca.on('hide', function () {
    	btnToggleRicerca.html("Mostra campi di ricerca &nbsp;<i class=\"icon-search\"></i>");
    }).on('show', function() {
    	btnToggleRicerca.html("Nascondi campi di ricerca &nbsp;<i class=\"icon-search\"></i>");
	});
    
    
    
    var btnRicAvDown = $("#btnRicAvDown");
    var btnRicAvUp = $("#btnRicAvUp");
    btnRicAvUp.hide();
    $('#ricercaAvanzata').on('hide', function () {
    	  btnRicAvDown.show();
    	  btnRicAvUp.hide();
    });
    
    $('#ricercaAvanzata').on('show', function () {
  	  btnRicAvDown.hide();
  	  btnRicAvUp.show();
    });
    
    $("#salva").click(function() {
    	if($(this).is(":submit")){
    		window.location.href = "index.html";
    	}
    });
    
    $("#annulla, #indietro").click(function() {
    	window.location.href = "index.html";
    });
    
    var risultatiRicerca = $("#risultatiRicerca");
    //risultatiRicerca.hide();
    
    $("#ricerca").click(function() {
         risultatiRicerca.show();
    });
    
    $("#nuovo").click(function() {
    	window.location.href = "inserimento_step1.html";
    });
   
    $("#avantiStep2").click(function() {
    	window.location.href = "inserimento_step2.html";
    });
    
    $("#avantiStep3").click(function() {
    	window.location.href = "inserimento_step3.html";
    });
    
    $("#indietroStep1").click(function() {
    	window.location.href = "inserimento_step1.html";
    });
    
    $("#indietroStep2").click(function() {
    	window.location.href = "inserimento_step2.html";
    });
    
    
    $("#modifica").click(function() {
    	window.location.href = "modifica.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(0)').click(function() {
    	window.location.href = "dettaglio.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(1)').click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(2)').click(function() {
    	window.location.href = "dettaglio_non_assegnatario.html";
    });
    
    
    $("#salvaModifica").click(function() {
    	window.location.href = "dettaglio.html";
    });
    
    $("#vaiaricerca").click(function() {
    	window.location.href = "index.html";
    });
    
    $("#inserisciNote").click(function() {
    	window.location.href = "inserimentoNoteAllegati.html";
    });
    
    $("#salvaNote").click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $("#annullaNote").click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $("#avantiStep3").click(function() {
    	window.location.href = "inserimento_step3.html";
    });
    
    $("#indietroStep1").click(function() {
    	window.location.href = "inserimento_step1.html";
    });
    
    $("#indietroStep2").click(function() {
    	window.location.href = "inserimento_step2.html";
    });
    
    
    $("#modifica").click(function() {
    	window.location.href = "modifica.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(0)').click(function() {
    	window.location.href = "dettaglio.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(1)').click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $('#risultatiRicerca .table > tbody > tr:eq(2)').click(function() {
    	window.location.href = "dettaglio_non_assegnatario.html";
    });
    
    
    $("#salvaModifica").click(function() {
    	window.location.href = "dettaglio.html";
    });
    
    $("#vaiaricerca").click(function() {
    	window.location.href = "index.html";
    });
    
    $("#inserisciNote").click(function() {
    	window.location.href = "inserimentoNoteAllegati.html";
    });
    
    $("#salvaNote").click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $("#annullaNote").click(function() {
    	window.location.href = "dettaglio_assegnatario.html";
    });
    
    $("#rifiutoAssegnazione").click(function() {
    	window.location.href = "index.html";
    });
    
    $("#richiediAssegnazione").click(function() {
    	window.location.href = "index.html";
    });
    
    //Inserimento Enti Assegnatari
    
    var assegnatarioDipTesoro = $("#assegnatarioDipTesoro");
    assegnatarioDipTesoro.hide();
    
    
    $("#insertEnte").click(function() {
    	//assegnatarioDipTesoro.show();
    	assegnatarioDipTesoro.fadeIn( 1000 );
    	$("#noRecord").hide();
    });
    
    $("#deleteEnte").click(function(e) {
    	assegnatarioDipTesoro.fadeOut( 1000 );
    	$("#noRecord").show();
    	e.preventDefault();
    });
    
    
    $('#popoverRifiuto').on('click', function(e) {e.preventDefault(); return true;});
    
    var $div = $('<div>');
    var content = 'ciao';
    $div.load('motivazionerifiuto.html #modalRifiuto', function() {
    	content = $(this).html();
    });

    $("#popoverRifiuto").click( function(e) {  
    	$(this).popover({
	    	placement : 'top', // top, bottom, left or right
	    	title : 'Rifiuto assegnazione', 
	    	html: 'true',
	    	trigger: 'manual',
	    	content : content // '<div id="popOverBox"><span>Provvedimento non di competenza<span></div>'
    	}); 
    	$(this).popover('toggle');
    	e.stopPropagation();
    });
    
    
    
    // NOTIFICHE
    $("#popoverNotifiche").popover({
    	placement : 'bottom', // top, bottom, left or right
    	title : 'Notifiche&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', 
    	html: 'true', 
    	content : '<div id="popOverBox"><span>L\'utente Filippo Neri ha richiesto l\'assegnazione del provvedimento LP 1 per l\'organo Dip.to Tesoro</span><br><a href="richiestaassegnazione.html">Clicca qui</a> per gestire la richiesta<hr/><span>L\'utente Carlo Bianchi ha rifiutato l\'assegnazione del provvedimento LP 1 per l\'organo Ragioneria Generale dello Stato</span><br><a href="motivazionerifiuto.html">Clicca qui</a> per vedere la motivazione</div>'
    });

    
    
    // INSERIMENTO
    $("#dp1").datepicker({
    	format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "it"
    }).on('changeDate', function(ev){
	        $("#dp1v").val(ev.format('dd/mm/yyyy'));
        });
  
 // INSERIMENTO
    $("#dp2").datepicker({
    	format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "it"
    }).on('changeDate', function(ev){
	        $("#dp2v").val(ev.format('dd/mm/yyyy'));
        });
    
    
    $('#modal').on('show', function () {
        $(this).find('.modal-body').css({
               width:'auto', //probably not needed
               height:'auto', //probably not needed 
               'max-height':'100%'     
        });
    });
    
    $('#statoDiAttuazioneDettaglio').on('change', function () {
    	var val = $(this).val();
    	var valoreAction = "Chiusura lavori";
    	if(val==valoreAction){
    		$("#salva").text("Salva e invia notifica");
    		$("#salva").attr('data-toggle', 'modal');
    		$("#salva").attr('data-target', '#modalSalvaInviaNotifica');
    		$("#salva").attr('type', 'button');
    		
    	} else {
    		$("#salva").html("Salva &nbsp;<i class=\"icon-save\"></i>");
    		$("#salva").attr('type', 'submit');
    	}
    });
    
    $("#proponenteDiv").hide();
    $('#statoDiAttuazione').on('change', function () {
    	var val = $(this).val();
    	var option1 = "Concertante MEF";
    	var option2 = "Concerto preventivo";
    	if((val==option1) || (val==option2)){
    		$("#proponenteDiv").show();
    	} else {
    		$("#proponenteDiv").hide();
    	}
    });  
});
