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
    
//    $('#risultatiRicerca .table > tbody > tr:eq(0)').click(function() {
//    	window.location.href = "dettaglio.html";
//    });
//    
//    $('#risultatiRicerca .table > tbody > tr:eq(1)').click(function() {
//    	window.location.href = "dettaglio_assegnatario.html";
//    });
//    
//    $('#risultatiRicerca .table > tbody > tr:eq(2)').click(function() {
//    	window.location.href = "dettaglio_non_assegnatario.html";
//    });
//    
    
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
    
//    $('#risultatiRicerca .table > tbody > tr:eq(0)').click(function() {
//    	window.location.href = "dettaglio.html";
//    });
//    
//    $('#risultatiRicerca .table > tbody > tr:eq(1)').click(function() {
//    	window.location.href = "dettaglio_assegnatario.html";
//    });
//    
//    $('#risultatiRicerca .table > tbody > tr:eq(2)').click(function() {
//    	window.location.href = "dettaglio_non_assegnatario.html";
//    });
    
    
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
    
    
    
    ///////////////////////////////////////////////////////////////////7
    
    
    
    $('#risultatiRicerca .table > tbody > tr').click(function() {
    	var customerId = $(this).find("td:first").html();  
    	var currentUrl = $(location).attr('pathname'); 
    	window.location.href = currentUrl+"/dettaglio/"+customerId;
    	
    });
    
    $("#denominazioneNuovoOrganoDiv").hide();
    $("#denominazioneEstesaNuovoOrganoDiv").hide();
    $("#listaOrganiInterniNuovoOrganoDiv").hide();

    $('#tipoNuovoOrgano').on('change', function () {
    	var val = $(this).val();
    	var option1 = "E"; //Esterna
    	var option2 = "I"; //Interna
    	if( val==option1){
    		$("#denominazioneNuovoOrganoDiv").show();
    		$("#denominazioneEstesaNuovoOrganoDiv").show();
    		$("#listaOrganiInterniNuovoOrganoDiv").hide();
    		//Interna

    	}else if( val==option2){
			$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").show();
    		//Esterna
    		
    	}else{
    		$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").hide();
    		//NO SELECT
    		
    	}

    });  
    
    if($('#tipoNuovoOrgano').length > 0){
    	var val = $('#tipoNuovoOrgano').val();
    	var option1 = "E"; //Esterna
    	var option2 = "I"; //Interna
    	if( val==option1){
    		$("#denominazioneNuovoOrganoDiv").show();
    		$("#denominazioneEstesaNuovoOrganoDiv").show();
    		$("#listaOrganiInterniNuovoOrganoDiv").hide();
    		//Interna

    	}else if( val==option2){
			$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").show();
    		//Esterna
    		
    	}else{
    		$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").hide();
    		//NO SELECT
    		
    	}

    }
    
    $('#autocompleteUo').attr('autocomplete','off');
    $('#autocompleteUo').typeahead({
    	minLength: 2,
        source: function(query, process) {
            objects = [];
            map = {};
            $.get(
            		'enti/nuovo/autocompletamento',
            		{ query: query },
            		function (data) {
            			$.each(data, function(i, object) {
                            map[object.nome] = object;
                            objects.push(object.nome);
                        });      
                        process(objects);
            		}); 
        },
        updater: function(item) {
            $('#hiddenIdUo').val(map[item].id);
            return item;
        }
    });   
    
    $('#autocompleteUo').on('change', function () {
    	var organoDenominazione = $.trim( $(this).val() );
    	if( organoDenominazione=='' ){
    		$('#hiddenIdUo').val('');
    	}
    });

	$("#inserimentoUtenteInternoDiv").hide();
	$("#inserimentoUtenteEsternoDiv").hide();
	
    $('#tipoNuovoUtente').on('change', function () {
    	var val = $(this).val();
    	var optionInterno = "I"; //Interna
    	var optionEsterno = "E"; //Esterna
    	if(val==optionInterno){
    		//Inerimento Intenro
    		$("#inserimentoUtenteInternoDiv").show();
    		$("#inserimentoUtenteEsternoDiv").hide();
    	}else if(val==optionEsterno){
    		//Inserimento Esterno
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteEsternoDiv").show();
    	}else{
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteEsternoDiv").hide();
    	}
    });
    
    
    $('#organoDenominazione').attr('autocomplete','off');
    $('#organoDenominazione').typeahead({
    	minLength: 2,
        source: function(query, process) {
            objects = [];
            map = {};
            $.get(
            		'utenti/nuovo/autocomporganoesterno',
            		{ query: query },
            		function (data) {
            			$.each(data, function(i, object) {
                            map[object.descrizione] = object;
                            objects.push(object.descrizione);
                        });      
                        process(objects);
            		}); 
        },
        updater: function(item) {
            $('#hiddenIdOrgano').val(map[item].id);
            return item;
        }
    });   
    
    $('#organoDenominazione').on('change', function () {
    	var organoDenominazione = $.trim( $(this).val() );
    	if( organoDenominazione=='' ){
    		$('#hiddenIdOrgano').val('');
    	}
    });
    
    /****** GESTIONE PROVVEDIMENTO ******/
	$('#allegatoProvvedimento').change(function() {
		$('#textAllegato').val($(this).val());
	});
	
	$("button#allegatoInserisci").click(function(){
		var formData = $('form#allegatoForm').serialize();
		var files = $('input[type=file]');
		// You should sterilise the file names
		$.each(files, function(key, value)
		{
			formData = formData + '&filenames[]=' + value;
		});
		alert(formData);
//		var data = $('form#allegatoForm').serialize();
//		var oMyForm = new FormData();
//		oMyForm.append("file", file.files[0]);
        $.ajax({
        	type: "GET",
        	url: "inserisciAllegato",
            data: formData,
            async: false,
            contentType: false,
            cache: false,
        	success: function(msg){
        		alert("aggiunto allegato!!");
        		alert(msg);
        	},
        	error: function(){
        		alert("failure");
        	}
        });
	});

	
//    $.post(
//    		'provvedimento/aggiungi/allegato',
//    		{ query: query },
//    		function (data) {
//    			$.each(data, function(i, object) {
//                    map[object.nome] = object;
//                    objects.push(object.nome);
//                });      
//                process(objects);
//    		}); 
//	
//	$.post( "test.php", { func: "getNameAndTime" }, function( data ) {
//		  console.log( data.name ); // John
//		  console.log( data.time ); // 2pm
//		}, "json");
	$('#allegatoInserisci').click(function() {
		$('#allegato > tbody:last').append('<tr><td>3</td><td>Fileaasd a.doc</td><td>300Mb</td></tr>');
	});

    
    
});

