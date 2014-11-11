$(document).ready(function() {
	
    $('.multiselect').multiselect({
            nonSelectedText: 'Tutti',
            numberDisplayed: 20
	});
    
	$('.custom-headers').multiSelect({
		  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"12\"'>",
		  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"4\"'>",
		  afterInit: function(ms){
		    var that = this,
		        $selectableSearch = that.$selectableUl.prev(),
		        $selectionSearch = that.$selectionUl.prev(),
		        selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
		        selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

		    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
		    .on('keydown', function(e){
		      if (e.which === 40){
		        that.$selectableUl.focus();
		        return false;
		      }
		    });

		    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
		    .on('keydown', function(e){
		      if (e.which == 40){
		        that.$selectionUl.focus();
		        return false;
		      }
		    });
		  },
		  afterSelect: function(){
		    this.qs1.cache();
		    this.qs2.cache();
		  },
		  afterDeselect: function(){
		    this.qs1.cache();
		    this.qs2.cache();
		  }
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
    $("#dataAtto").datepicker({
    	format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "it"
    }).on('changeDate', function(ev){
	        $("#dataAttoV").val(ev.format('dd/mm/yyyy'));
        });
    
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
    
    
    // SALVA E INVIA NOTIFICHE PROVVEDIMENTO
    
    $('#salvaeinvianotifica').click( function () {
    	var target = $(location).attr('pathname');
    	target = target + '/salvaeinvianotifica?id=' + $('#idProvvedimento').val();
    	// load the url and show modal on success
        $("#modalSalvaInviaNotifica .modal-body").load(target, function() { 
             $("#modalSalvaInviaNotifica").modal("show"); 
        });
    });
    
    $('#inviaNotifica').click( function () {
    	var oForm1 = $('#formEmailSalvaENotifica');
    	oForm1.submit();
    });
    
    
    $(document).on('click', '#tokenfieldemail', function() {
	    var element = $(this);
	    element.myTagsinput(element);
    
    });    
    
    $.fn.myTagsinput = function (element) {
    	element.tagsinput({
        	itemValue: 'email',
        	itemText: function(item) {
        		return item.cognome + " " + item.nome;
        	},
        	freeInput: true,
        	allowDuplicates: false,
//        	maxTags: 3,
        	trimValue: true,
        	showAutocompleteOnFocus: true,
//        	tagClass: function(item) {
//        	    return (item.length > 10 ? 'big' : 'small');
//        	},
        	typeahead: {
        		source: function(param) {
        			return $.getJSON(
        					'autocomplateutentemail',
        					{ query: param }
//        					,function(data){
//        						$.each(data, function(i, object) {
//        							alert(object.codice);
//        							alert(object.descrizione);
//                                });
//        					}
        			);
        		}
        	}
        });
    }
    
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

	
	$(document).on('change', '.btn-file :file', function() {
	    var input = $(this);
	    var numFiles = input.get(0).files ? input.get(0).files.length : 1;
	    var label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	    $('#textAllegato').val(label);
	});
	
	$('.progress').hide();
	
	$("button#allegatoInserisci").click(function(){
		
		var progress = $('.progress');
		var bar = $('.bar');
		var percent = $('.percent');
		
		 $('#allegatoForm').ajaxForm({
			 dataType: 'text',   
			 contentType: "multipart/form-data",
			 beforeSubmit: function() {
		        	$("#allegatoProvvedimento").attr('disabled','disabled');
		        	$("#descrizioneAllegato").attr('disabled','disabled');
		        	$("button#allegatoInserisci").attr('disabled','disabled');
		        	
		        	progress.show();
		        	var percentVal = '0%';
			        bar.width(percentVal)
			        percent.html(percentVal);
		        },
			    uploadProgress: function(event, position, total, percentComplete) {
			        var percentVal = percentComplete + '%';
			       
			        bar.removeAttr('style');
			        bar.attr('style', 'width: ' + percentVal + ';');
			        
			        if( percentComplete == '100' )
			        	percentVal = 'Elaborazione in corso...';
			        percent.html(percentVal);
			    },
			    success: function(data) {

			        progress.hide();
			        bar.removeAttr('style');
			        bar.attr('style', 'width: 0%;');
		        },
		        complete: function(data) {
					
		        	responseText = $.parseJSON(data.responseText);
		        	
		        	addRowAllegato(responseText);
					addAllegatiUpdList(responseText.id);
					
					$("button#allegatoInserisci").removeAttr('disabled');
		        	$("#allegatoProvvedimento").removeAttr('disabled');
		        	$("#descrizioneAllegato").removeAttr('disabled');
			        
					resetFormAllegati();
					
		        }
		    }).submit(); 
		
	});
	
	function addAllegatiUpdList(id){
		var idAllegatiUpdList = $('#idAllegatiUpdList').val();
		if( idAllegatiUpdList == '' ){
			$('#idAllegatiUpdList').val(id);
		}else{
			var appo = $('#idAllegatiUpdList').val();
			$('#idAllegatiUpdList').val(idAllegatiUpdList + ',' + id)
		}
	}
	
	$("button#aggiornaProvvedimento").click(function(){
		$( "#provvedimentoModifica" ).submit();
	});

	$("button#apriNuovoProvvedimento").click(function(){
		var currentUrl = $(location).attr('pathname'); 
		window.location = currentUrl+"/nuovo";
	});
	
//	$("button#annullaModificaProvvedimento").click(function(){
//		$('<input />').attr('type', 'hidden')
//        .attr('name', 'action')
//        .attr('value', 'Annulla')
//        .appendTo('#provvedimentoDettaglio');
//		$( "#provvedimentoDettaglio" ).submit();
//	});
//	
//	$("button#modificaProvvedimento").click(function(){
//		$('<input />').attr('type', 'hidden')
//        .attr('name', 'action')
//        .attr('value', 'Modifica')
//        .appendTo('#provvedimentoDettaglio');
//		$( "#provvedimentoDettaglio" ).submit();
//	});
	
//	$("button#salvaDettaglio").click(function(){
//		$('<input />').attr('type', 'hidden')
//        .attr('name', 'action')
//        .attr('value', 'SalvaDettaglio')
//        .appendTo('#provvedimentoDettaglio');
//		$( "#provvedimentoDettaglio" ).submit();
//	});
	
	$("#statoDiAttuazioneDettaglio").change(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'cambioStato')
//        .attr('value', 'CambioStato')
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
	
	// MODALE RICHIESTA ASSEGNAZIONE
	gestionePopupRichiestaAssegnazione();
	
	//INSERIMENTO
	gestioneInserimento();
	
	//GESTIONE MODALE CRONOLOGIA
	$("a[data-target=#modalCronologia]").click(function(ev) {
	    ev.preventDefault();
	    var target = $(this).attr("href");

	    // load the url and show modal on success
	    $("#modalCronologia .modal-body").load(target, function() { 
	         $("#modalCronologia").modal("show"); 
	    });
	});
});

function eliminaNessunRisultatoAssegnatario(id){
	if($(id+" tr.empty")) {
		$(id+" tr.empty").fadeOut( 500 );
	}
}


// GESTIONE NOTIFICHE
function gestioneNotifiche() {
	
	
	$("#mostraTutteLeNotifiche").click(function(e) {
		e.stopPropagation();
	    e.preventDefault();
	    
	    window.location.href = $(this).attr("href");
	});
	
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
		    	
		    	 popoverNotifiche.popover({
				    	placement : 'bottom', 
				    	html: 'true',
				    	trigger: 'manual',
				    	content : content // '<div id="popOverBox"><span>Provvedimento non di competenza<span></div>'
			    }).parent().on('click', '.aprinotifica', function(e) {
			    	e.preventDefault();
					e.stopPropagation();
					var url = $(this).find("a").attr("href");
					window.location.href = url;		
			    }); 
				 popoverNotifiche.popover('show');
		    });
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

//MODALE RICHIESTA ASSEGNAZIONE
function gestionePopupRichiestaAssegnazione() {
	
	$('#richiediAssegnazione').click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		$("#modalRichiestaAssegnazione").modal().on('shown', function() {
			
			$("#richiediAssegnazioneModal").click(function(e) {
				e.stopPropagation();
				e.preventDefault();
				
				$(this).closest("form").append("<input type='hidden' name='richiediAssegnazione' />").submit();
				
			});
			
		}); 
	});
	
}


function gestioneInserimento(){
    $('#insertAssegnatarioFromInserimento').click( submit_assegnatarioIns );
    $('#assegnazioneForm').find('input').keydown(keypressedAssIns);
    
    $('#allegatoInserisciIns').click( submit_allegatoIns );
    $('#allegatiInsForm').find('input').keydown(keypressedAllIns);	
}
function addUpdList(idCampo,id){
	var idAllegatiUpdList = $(idCampo).val();
	if( idAllegatiUpdList == '' ){
		$(idCampo).val(id);
	}else{
		var appo = $(idCampo).val();
		$(idCampo).val(idAllegatiUpdList + ',' + id)
	}
}
function addRowAssegnazione(item,inserimento){
	if(inserimento==true){
		$('#assegnazione > tbody:last').append(
				$('<tr>')
					.append($('<td>').attr("class","hidden").text(item.id))
					.append($('<td>').text(item.nomeAssegnatario))
					.append($('<td>').attr("class","center vcenter").html(
							$('<a></a>').attr("href","javascript:void(0)").attr("id","eliminaAssegnatario").html('<i class="icon-trash icon-large gray" title="Elimina assegnatario"></i>')
					))
		);
	} else {
		
	}
}

function addRowAllegato(item){
	eliminaNessunRisultatoAllegato();
	$('#allegato > tbody:last').append(
			$('<tr>')
				.append($('<td>').text(item.id))
				.append($('<td>').html(
						$('<a></a>').attr("class","download").attr("href","downloadAllegato?id="+item.id).append(item.descrizione)
				))
				.append($('<td>').text(item.dimensione))
				.append($('<td>').attr("class","center vcenter").html(
						$('<a></a>').attr("href","javascript:void(0)").attr("id","eliminaAllegato").html('<i class="icon-trash icon-large gray" title="Elimina allegato"></i>')
				))
	);
}
function resetFormAllegati(){
	$('#allegatoForm').find("input[type=text],input[type=file]").val("");
}
function eliminaNessunRisultatoAllegato(){
	if($("#allegato tr.empty")) {
		$("#allegato tr.empty").fadeOut( 500 );
	}
}
function submit_assegnatarioIns( event ) {
    event.preventDefault();
    do_submitAssegnatarioIns();
    return false;
}
function submit_allegatoIns( event ) {
    event.preventDefault();
    do_submitAllegatoIns();
    return false;
}

function keypressedAllIns( event ) {
    var charcode = (event.which) ? event.which : window.event.keyCode ;
    if ( charcode == 13 ) {
        return submit_allegatoIns( event );
    }
    return true;
}
function keypressedAssIns( event ) {
    var charcode = (event.which) ? event.which : window.event.keyCode ;
    if ( charcode == 13 ) {
        return submit_assegnatarioIns( event );
    }
    return true;
}

function do_submitAssegnatarioIns() {
	var formData = $('#assegnatario').serialize();
	
    $.ajax({
    	type: 'GET',
    	url: 'addAssegnatario',
        data: formData,
		dataType : 'json',
		processData : false,
		contentType : false,
		success : function(response) {
			eliminaNessunRisultatoAssegnatario("#assegnazione");
			addRowAssegnazione(response,true);
        	addUpdList('#idAssegnatariUpdList',response.id);
    	},
    	error: function(){
    		alert("Inserimento non riuscito");
    	}
    });
}
function do_submitAllegatoIns() {
	var progress = $('.progress');
	var bar = $('.bar');
	var percent = $('.percent');
	
	 var req = $('#provvedimentoInserisci').ajaxSubmit({
		 dataType: 'text',   
		 contentType: "multipart/form-data",
		 url: "modifica/inserisciAllegato",
		 beforeSubmit: function() {
	        	$("#allegatoProvvedimento").attr('disabled','disabled');
	        	$("#descrizioneAllegato").attr('disabled','disabled');
	        	$("button#allegatoInserisci").attr('disabled','disabled');
	        	
	        	progress.show();
	        	var percentVal = '0%';
		        bar.width(percentVal)
		        percent.html(percentVal);
	        },
		    uploadProgress: function(event, position, total, percentComplete) {
		        var percentVal = percentComplete + '%';
		       
		        bar.removeAttr('style');
		        bar.attr('style', 'width: ' + percentVal + ';');
		        
		        if( percentComplete == '100' )
		        	percentVal = 'Elaborazione in corso...';
		        percent.html(percentVal);
		    },
		    success: function(data) {

		        progress.hide();
		        bar.removeAttr('style');
		        bar.attr('style', 'width: 0%;');
	        },
	        complete: function(data) {
				
	        	responseText = $.parseJSON(data.responseText);
	        	
	        	addRowAllegato(responseText);
				addUpdList('#idAllegatiUpdList',responseText.id);
				
				$("button#allegatoInserisci").removeAttr('disabled');
	        	$("#allegatoProvvedimento").removeAttr('disabled');
	        	$("#descrizioneAllegato").removeAttr('disabled');
		        
				resetFormAllegati();
				
	        }
	    });
}

