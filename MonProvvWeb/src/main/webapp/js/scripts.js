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
//    $div.load('motivazionerifiuto.html #modalRifiuto', function() {
//    	content = $(this).html();
//    });

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
    	window.location.href = currentUrl+"/dettaglio?id="+customerId;
    });
    
//    $("#denominazioneNuovoOrganoDiv").hide();
//    $("#denominazioneEstesaNuovoOrganoDiv").hide();
//    $("#listaOrganiInterniNuovoOrganoDiv").hide();

    /****** GESTIONE AMMINISTRAZIONE ******/

    $("a#delete4risultatiRicerca").click(function(){
    	var retval;
    	bootbox.confirm("Sei sicuro di voler procedere con la cancellazione della riga?", function(result) {
    		if(result){
    			var url = $("a#delete4risultatiRicerca").attr('href');    
    			$(location).attr('href',url);
    		}
    	});
    	return false;
    });
    
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
            		'nuovo/autocompletamento',
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

//	$("#inserimentoUtenteInternoDiv").hide();
//	$("#inserimentoUtenteEsternoDiv").hide();
	
    $('#tipoNuovoUtente').on('change', function () {
    	var val = $(this).val();
    	var optionInterno = "I"; //Interna
    	var optionEsterno = "E"; //Esterna
    	if(val==optionInterno){
    		//Inerimento Intenro
    		$("#inserimentoUtenteInternoDiv").show();
    		$("#inserimentoUtenteInternoDivOrg").show();
    		$("#inserimentoUtenteEsternoDiv").hide();
    		$("#cognome").attr('readonly', true);
    		$("#nome").attr('readonly', true);
    		$("#codiceFiscale").attr('readonly', true);
    		$("#email").attr('readonly', true);
    	}else if(val==optionEsterno){
    		//Inserimento Esterno
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteInternoDivOrg").hide();
    		$("#inserimentoUtenteEsternoDiv").show();
    		$("#cognome").attr('readonly', false);
    		$("#nome").attr('readonly', false);
    		$("#codiceFiscale").attr('readonly', false);
    		$("#email").attr('readonly', false);
    	}else{
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteEsternoDiv").hide();
    		$("#inserimentoUtenteInternoDivOrg").hide();
    		$("#cognome").attr('readonly', true);
    		$("#nome").attr('readonly', true);
    		$("#codiceFiscale").attr('readonly', true);
    		$("#email").attr('readonly', true);
    	}
    });
    
    if($('#tipoNuovoUtente').length > 0){
    	var val = $('#tipoNuovoUtente').val();
    	var optionInterno = "I"; //Interna
    	var optionEsterno = "E"; //Esterna
    	if(val==optionInterno){
    		//Inerimento Intenro
    		$("#inserimentoUtenteInternoDiv").show();
    		$("#inserimentoUtenteInternoDivOrg").show();
    		$("#inserimentoUtenteEsternoDiv").hide();
    		$("#cognome").attr('readonly', true);
    		$("#nome").attr('readonly', true);
    		$("#codiceFiscale").attr('readonly', true);
    		$("#email").attr('readonly', true);
    	}else if(val==optionEsterno){
    		//Inserimento Esterno
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteInternoDivOrg").hide();
    		$("#inserimentoUtenteEsternoDiv").show();
    		$("#cognome").attr('readonly', false);
    		$("#nome").attr('readonly', false);
    		$("#codiceFiscale").attr('readonly', false);
    		$("#email").attr('readonly', false);
    	}else{
    		$("#inserimentoUtenteInternoDiv").hide();
    		$("#inserimentoUtenteEsternoDiv").hide();
    		$("#inserimentoUtenteInternoDivOrg").hide();
    		$("#cognome").attr('readonly', true);
    		$("#nome").attr('readonly', true);
    		$("#codiceFiscale").attr('readonly', true);
    		$("#email").attr('readonly', true);
    	}
    }
    
    
    $('#organoDenominazioneEst').attr('autocomplete','off');
    $('#organoDenominazioneEst').typeahead({
    	minLength: 2,
        source: function(query, process) {
            objects = [];
            map = {};
            $.get(
            		$(location).attr('pathname')+'/autocomporganoesterno',
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
    
    $('#organoDenominazioneEst').on('change', function () {
    	var organoDenominazione = $.trim( $(this).val() );
    	if( organoDenominazione=='' ){
    		$('#hiddenIdOrgano').val('');
    	}
    });
    
    	$('#nominativoUtente').attr('autocomplete','off');
    $('#nominativoUtente').typeahead({
    	minLength: 2,
        source: function(query, process) {
            objects = [];
            map = {};
            $.get(
            		'nuovo/autocomputenteinterno',
            		{ query: query },
            		function (data) {
            			$.each(data, function(i, object) {
                            map[object.cognome+' '+object.nome] = object;
                            objects.push(object.cognome+' '+object.nome);
                        });      
                        process(objects);
            		}); 
        },
        matcher: function(item){
        	return true;
        },
        updater: function(item) {
        	$('#cognome').val(map[item].cognome);
        	$('#nome').val(map[item].nome);
        	$('#codiceFiscale').val(map[item].codiceFiscale);
        	$('#email').val(map[item].email);
        	$('#organoDenominazioneInterni').val(map[item].organo);
        	$('#organo').val(map[item].idOrgano);
        	$('#hiddenUtenteAstage').val(map[item].id);
            return item;
        }
    });   
    
    $('#nominativoUtente').on('change', function () {
    	var nominativoUtente = $.trim( $(this).val() );
    	if( nominativoUtente=='' ){
    		$('#cognome').val('');
        	$('#nome').val('');
        	$('#codiceFiscale').val('');
        	$('#email').val('');
        	$('#organoDenominazioneInterni').val('');
        	$('#organo').val('');
        	$('#hiddenUtenteAstage').val('');
    	}
    });
    
    /****** FINE GESTIONE AMMINISTRAZIONE ******/
    
    /****** GESTIONE PROVVEDIMENTO ******/
//	$('#allegatoProvvedimento').change(function() {
//		$('#textAllegato').val($(this).val());
//	});
	

	
	$(document).on('change', '.btn-file :file', function() {
	    var input = $(this);
	    var numFiles = input.get(0).files ? input.get(0).files.length : 1;
	    var label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	    $('#textAllegato').val(label);
	});

	
//	var bar = $('.bar');
//	var percent = $('.percent');

	$('#allegatoForm').ajaxForm({
	    beforeSend: function() {
//	        var percentVal = '0%';
//	        bar.width(percentVal)
//	        percent.html(percentVal);
	    },
	    uploadProgress: function(event, position, total, percentComplete) {
//	        var percentVal = percentComplete + '%';
//	        bar.width(percentVal)
//	        percent.html(percentVal);
	    },
	    success: function() {
//	        var percentVal = '100%';
//	        bar.width(percentVal)
//	        percent.html(percentVal);
	    },
		complete: function(xhr) {
//			var text = xhr.responseText.replace('<PRE>','').replace('</PRE>','');
//			var html = $.parseHTML( text );
//			addRowAllegati(html);
			
			addRowAllegati(xhr.responseJSON);
			addAllegatiUpdList(xhr);
		}
	}); 

	
	function addRowAllegati(item)
    {
	    
//		$('#allegato > tbody:last').append(
//		$('<tr>').html(
//	        $('td').text(item.id),
//	        $('td').text(item.descrizione),
//	        $('td').text(item.dimensione),
//	        $('td').text(item.id)
//	    ).appendTo('#allegato');
    }
	
	function addAllegatiUpdList(xhr){
		eliminaNessunRisultatoAllegato();
		resetFormAllegati();
		
//		$('#allegato > tbody:last').append('<tr><td>'+xhr.responseJSON.id+'</td></tr>');
		
		
		var idAllegatiUpdList = $('#idAllegatiUpdList').val();
		
		if( idAllegatiUpdList == '' ){
			$('#idAllegatiUpdList').val(xhr.responseJSON.id);
		}else{
			var appo = $('#idAllegatiUpdList').val();
			$('#idAllegatiUpdList').val(idAllegatiUpdList + ',' +xhr.responseJSON.id)
		}
		
//		var match;
//		if(riga[0].data){
//			$('#allegato > tbody:last').append(riga[0].data);
//			var regex = new RegExp("\\<tr\\>\\<td\\>(.{0,}?)\\</td\\>", "g");
//			var match;
//			match = regex.exec(riga[0].data);
//		}else{
//			alert('chrome');
//			$('#allegato > tbody:last').append(riga[0]);
//			var regex = new RegExp("\\<tr\\>\\<td\\>(.{0,}?)\\</td\\>", "g");
//			var match;
//			match = regex.exec(riga[0]);
//		}
//
//		if( match != null ){
//			if( $('#idAllegatiUpdList').val() == '' ){
//				$('#idAllegatiUpdList').val(match[1]);
//			}else{
//				var appo = $('#idAllegatiUpdList').val();
//				$('#idAllegatiUpdList').val(appo + ',' + match[1])
//			}
//		}
		
	}

	function eliminaNessunRisultatoAllegato(){
		if($("#allegato tr.empty")) {
			$("#allegato tr.empty").fadeOut( 500 );
		}
	}
	function eliminaNessunRisultatoAssegnatario(){
		if($("#assegnazione tr.empty")) {
			$("#assegnazione tr.empty").fadeOut( 500 );
		}
	}
	
	function resetFormAllegati(){
		$('#allegatoForm').find("input[type=text],input[type=file]").val("");
	}
	
	$("button#aggiornaProvvedimento").click(function(){
		$( "#provvedimentoModifica" ).submit();
	});

	$("button#apriNuovoProvvedimento").click(function(){
		var currentUrl = $(location).attr('pathname'); 
		window.location = currentUrl+"/nuovo";
	});
	
	$("button#annullaModificaProvvedimento").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'action')
        .attr('value', 'Annulla')
        .appendTo('#provvedimentoDettaglio');
		$( "#provvedimentoDettaglio" ).submit();
	});
	
	$("button#modificaProvvedimento").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'action')
        .attr('value', 'Modifica')
        .appendTo('#provvedimentoDettaglio');
		$( "#provvedimentoDettaglio" ).submit();
	});
	
	$("button#noteAllegatiProvvedimento").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'action')
        .attr('value', 'noteallegati')
        .appendTo('#provvedimentoDettaglio');
		$( "#provvedimentoDettaglio" ).submit();
	});
	
	$(document).on('click', '#eliminaAllegato', function() { 
		var idAllegato = $(this).parent().siblings(":first").text(); 
		var trTableToDelete = $(this).parent().parent();
		var idAllegatiDelList = $('#idAllegatiDelList').val();
		idAllegatiDelList = idAllegatiDelList + idAllegato + ',';
		$('#idAllegatiDelList').val(idAllegatiDelList);
		trTableToDelete.remove();
	});
	
	$("button#saveNoteAllegati").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'action')
        .attr('value', 'save')
        .appendTo('#provvedimento');
		$( "#provvedimento" ).submit();
	});
	
	$("button#annullaNoteAllegati").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'action')
        .attr('value', 'cancel')
        .appendTo('#provvedimento');
		$( "#provvedimento" ).submit();
	});
	
	$(document).on('click', '#eliminaAllegatoOld', function() { 
		var idAllegato = $(this).parent().siblings(":first").text(); 
		var trTableToDelete = $(this).parent().parent();
	    $.ajax({
	    	type: 'GET',
	    	url: 'deleteAllegato/'+idAllegato,
			dataType : 'text',
			processData : false,
			contentType : false,
			success : function(response) {
				trTableToDelete.remove();
	    	},
	    	error: function(){
	    		alert("error");
	    	}
	    });
	});
	
//	$("a#eliminaAllegato").click(function(){
//		var idAllegato = $(this).parent().siblings(":first").text(); 
//		var trTableToDelete = $(this).parent().parent();
//	    $.ajax({
//	    	type: 'GET',
//	    	url: 'deleteAllegato/'+idAllegato,
//			dataType : 'text',
//			processData : false,
//			contentType : false,
//			success : function(response) {
//				trTableToDelete.remove();
//	    	},
//	    	error: function(){
//	    		alert("error");
//	    	}
//	    });
//	});
	
	
	$("button#insertAssegnatario").click(function(){
		var formData = $('#assegnazioneForm').serialize();
	
	    $.ajax({
	    	type: 'GET',
	    	url: 'inserisciAssegnatario',
	        data: formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			success : function(response) {
				eliminaNessunRisultatoAssegnatario();
				$('#assegnazione > tbody:last').append(response);
	    	},
	    	error: function(){
	    		alert("Inserimento non riuscito");
	    	}
	    });
	});
	
	
	$('#custom-headers').multiSelect({
		  selectableHeader: "<div class='custom-header'>Provvedimenti</div>",
		  selectionHeader: "<div class='custom-header'>Provvedimenti selezionati</div>"
//		  selectableFooter: "<div class='custom-header'>Selectable footer</div>",
//		  selectionFooter: "<div class='custom-header'>Selection footer</div>"
	});
    /****** FINE GESTIONE PROVVEDIMENTO ******/

    // NOTIFICHE
	gestioneNotifiche();
});




// GESTIONE NOTIFICHE
function gestioneNotifiche() {
	
	// popover
	var popoverNotifiche = $("#popoverNotifiche"); 
	popoverNotifiche.click(function(e) {
	    e.stopPropagation();
	    e.preventDefault();
	    
	    
	    if (popoverNotifiche.hasClass('in')) {
	    	popoverNotifiche.popover('hide');
	    } else {
		    var $div = $("<div>");
		    var content = "";
		    var url = popoverNotifiche.attr("data-url");

		    $div.load(url + " #elencoNotifiche", function() {
		    	content = $(this).html();
		    });

		    popoverNotifiche.popover({
		    	placement : 'bottom', 
		    	html: 'true',
		    	trigger: 'manual',
		    	content : content // '<div id="popOverBox"><span>Provvedimento non di competenza<span></div>'
	    	}); 
		    
		    popoverNotifiche.popover('show');
	    }
	    
	    
	});

	// badge
	var href = popoverNotifiche.attr("href");
	$.get(href, function(data) {
		if (data > 0) {
			$("#countNotifiche").text(data);
			$("#notifBadge").show();
		}
	});
}
