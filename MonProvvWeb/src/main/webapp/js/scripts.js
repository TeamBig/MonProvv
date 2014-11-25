$(document).ready(function() {
	
    $('.multiselect').multiselect({
            nonSelectedText: 'Tutti',
            numberDisplayed: 2,
            maxWidth: 250,
            nSelectedText: 'selezionati'
            
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
    
    
    $("#ricerca").click(function() {
         $("risultatiRicercaProvvedimenti").show();
         $("risultatiRicerca").show();
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
    
    
    // INSERIMENTO
    $("#dataAtto").datepicker({
    	format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "it"
    }).on('changeDate', function(ev){
	        $("#dataAttoV").val(ev.format('dd/mm/yyyy'));
	        $( "#dataAttoV" ).focus();
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
    
  
    $('#risultatiRicerca .table > tbody > tr').click(function() {
    	var customerId = $(this).find("td:first").html();  
    	
    	if( $.isNumeric( customerId ) ){
        	var currentUrl = $(location).attr('pathname'); 
        	window.location.href = currentUrl+"/dettaglio?id="+customerId;
    	}
    });
    
    
    /****** GESTIONE AMMINISTRAZIONE ******/

    confirmCancellazioneAdmin();
    
function confirmCancellazioneAdmin(){
    $(".deleteEnte").click(function(){
    	var retval;
    	var td = $(this);
    	bootbox.confirm("Sei sicuro di voler procedere con l'eliminazione dell'Organo?", function(result) {
    		if(result){
    			var url = td.find("a#delete4risultatiRicerca").attr('href');    
    			$(location).attr('href',url);
    		}
    	});
    	return false;
    });
    
    $(".deleteUtente").click(function(){
    	var retval;
    	var td = $(this);
    	bootbox.confirm("Sei sicuro di voler procedere con l'eliminazione dell'Utente?", function(result) {
    		if(result){
    			var url = td.find("a#delete4risultatiRicerca").attr('href');    
    			$(location).attr('href',url);
    		}
    	});
    	return false;
    });
};
    
	gestioneOrganoAdmin();

function gestioneOrganoAdmin() {

	function gestioneVisibilita(val){
		var option1 = "E"; //Esterna
		var option2 = "I"; //Interna
		if( val==option1){
			//Interna
			$("#denominazioneNuovoOrganoDiv").show();
			$("#denominazioneEstesaNuovoOrganoDiv").show();
			$("#listaOrganiInterniNuovoOrganoDiv").hide();
		}else if( val==option2){
			//Esterna
			$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").show();
		}else{
			//NO SELECT
			$("#denominazioneNuovoOrganoDiv").hide();
			$("#denominazioneEstesaNuovoOrganoDiv").hide();
			$("#listaOrganiInterniNuovoOrganoDiv").hide();
		}
	}
	
	$('#tipoNuovoOrgano').on('change', function () {
    	var val = $(this).val();
    	gestioneVisibilita(val);
    });  
    
    if($('#tipoNuovoOrgano').length > 0){
    	var val = $('#tipoNuovoOrgano').val();
    	gestioneVisibilita(val);
    }
    
    $('#autocompleteUo').attr('autocomplete','off');
    
    var primoElemento = null;
    
    $('#autocompleteUo').typeahead({
    	minLength: 2,
        source: function(query, process) {
            objects = [];
            map = {};
            primoElemento = null;
            $.get(
            		'nuovo/autocompletamento',
            		{ query: query },
            		function (data) {
            			$.each(data, function(i, object) {
                            if( primoElemento == null){
                            	 primoElemento = object;
                            }
            				map[object.nome] = object;
                            objects.push(object.nome);
                        });      
                        process(objects);
            		}); 
        },
        updater: function(item) {
            $('#hiddenIdUo').val(map[item].id);
            primoElemento = map[item];
            return item;
        }
    }).on('blur', function(){
    	if(primoElemento==null){
    		$('#hiddenIdUo').val(null);
    	}else{
    		$('#hiddenIdUo').val(primoElemento.id);
    		$('#autocompleteUo').val(primoElemento.nome);
    	}    	
    });   
    
    $('#autocompleteUo').on('change', function () {
    	var organoDenominazione = $.trim( $(this).val() );
    	if( organoDenominazione=='' ){
    		$('#hiddenIdUo').val('');
    	}
    });
};

//	$("#inserimentoUtenteInternoDiv").hide();
//	$("#inserimentoUtenteEsternoDiv").hide();
	
    
//    $("#sesso").focus(function(){
//	    if ( $("#sesso").is('[readonly]') ){
//	    	if ($("#sesso").is(":checked")) {
//	            $('#sesso').prop('disabled', false);
//	        }
//	        else {
//	            $('#sesso').prop('disabled', 'disabled');
//	        }
//	    }
//    });
    
//    $(this).focusout(function(){
//    	$('#sesso').prop('disabled', 'disabled');
//    });
    
//    $('#organoDenominazioneEst').attr('autocomplete','off');
//    $('#organoDenominazioneEst').typeahead({
//    	minLength: 2,
//        source: function(query, process) {
//            objects = [];
//            map = {};
//            $.get(
//            		$(location).attr('pathname')+'/autocomporganoesterno',
//            		{ query: query },
//            		function (data) {
//            			$.each(data, function(i, object) {
//                            map[object.descrizione] = object;
//                            objects.push(object.descrizione);
//                        });      
//                        process(objects);
//            		}); 
//        },
//        updater: function(item) {
//            $('#hiddenIdOrgano').val(map[item].id);
//            return item;
//        }
//    });   
//    
//    $('#organoDenominazioneEst').on('change', function () {
//    	var organoDenominazione = $.trim( $(this).val() );
//    	if( organoDenominazione=='' ){
//    		$('#hiddenIdOrgano').val('');
//    	}
//    });
    
    //GESTIONE SOLLECITO
    $(document).on('click', '#anchorModalSollecito', function() { 
    	var idAssegnatarioSollecito = $(this).parent().siblings(":first").text(); 
    	 $('#idAssegnatarioSollecito').val(idAssegnatarioSollecito);
    });
    
    
    
    $('#inviaSollecitoModal').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		var oForm = $(this).closest("form");
		oForm.append("<input type='hidden' name='inviaSollecito' />");
		oForm.submit();
	});
    
    // SALVA E INVIA NOTIFICHE PROVVEDIMENTO
    
//    var dataVal=[{id:0,tag:'Volvo'},{id:1,tag:'Saab'},{id:2,tag:'Mercedes'},{id:3,tag:'Fiat'},{id:4,tag:'Audi'}];
//    
    
//    $.fn.tokenfieldemail = function (element) {
//    	element.tagsinput({
//        	itemValue: 'email',
//        	itemText: 'descrizione',
//        	freeInput: true,
//        	allowDuplicates: false,
//        	trimValue: true,
//        	showAutocompleteOnFocus: true,
//        	typeahead: {
//        		source: function(param) {
//        			if( param.length < 2)
//        				return null;
//        			else
//	        			return $.getJSON(
//	        					'autocomplateutentemail',
//	        					{ query: param }
//	        			);
//        		}
//        	}
//        });
//    }
    
    // FINE SALVA E INVIA NOTIFICHE PROVVEDIMENTO
    
    
    // INSERIMENTO UTENTE
    gestineInserimentoUtnete();
    
    
function gestineInserimentoUtnete(){
	
	$("#dataNascita").datepicker({
    	format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "it"
    }).on('changeDate', function(ev){
	        $("#dataNascitaV").val(ev.format('dd/mm/yyyy'));
	        $( "#dataNascitaV" ).focus();
	});
    
    $('#tipoNuovoUtente').on('change', function () {
    	cleanUtente();
    	update_form_inserimento_utente();
    });
    
    if($('#tipoNuovoUtente').length > 0){
    	 update_form_inserimento_utente();
    }
    
    function cleanUtente(){
    	$('#nominativo').val('');
    	$('#cognome').val('');
    	$('#nome').val('');
    	$('#dataNascitaV').val('');
    	$('#sesso').val('');
    	$('#sessoHidden').val('');
    	$('#codiceFiscale').val('');
    	$('#email').val('');
    	$('#organoDenominazioneInterni').val('');
    	$('#organoUteEsterno').val('');
    	$('#ruoloUtente').val('');	
    	$("#flgAmministratore").attr("checked", false);
    	$('#hiddenIdOrgano').val('');
    	$('#hiddenUtenteAstage').val('');
    };
    
    function update_form_inserimento_utente(){
    	var val = $('#tipoNuovoUtente').val();
    	var optionInterno = "I"; //Interna
    	var optionEsterno = "E"; //Esterna
    	
    	if(val==optionInterno){
    		//Inerimento Intenro
    		$("#inserimentoUtenteInternoNominativoDiv").show();
    		$("#inserimentoUtenteInternoOrganoDiv").show();
    		$("#inserimentoUtenteEsternoOrganoDiv").hide();
    		$("#dataNascita").hide();
    		$('#sesso').prop('disabled', 'disabled');
    		setreadonly(true);
    	}else if(val==optionEsterno){
    		//Inserimento Esterno
    		$("#inserimentoUtenteInternoNominativoDiv").hide();
    		$("#inserimentoUtenteInternoOrganoDiv").hide();
    		$("#inserimentoUtenteEsternoOrganoDiv").show();
    		$("#dataNascita").show();
    		$('#sesso').prop('disabled', false);
    		setreadonly(false);
    	}else{
    		$("#inserimentoUtenteInternoNominativoDiv").hide();
    		$("#inserimentoUtenteInternoOrganoDiv").show();
    		$("#inserimentoUtenteEsternoOrganoDiv").hide();
    		$("#dataNascita").hide();
    		$('#sesso').prop('disabled', 'disabled');
    		setreadonly(true);
    	}
    };
    
    function setreadonly(readonly){
   		$("#cognome").attr('readonly', readonly);
		$("#nome").attr('readonly', readonly);
		$("#codiceFiscale").attr('readonly', readonly);
		$("#dataNascitaV").attr('readonly', readonly);
		$("#sesso").attr('readonly', readonly);
		$("#email").attr('readonly', readonly);
    }
    
	$('#nominativoUtente').attr('autocomplete','off');
	
	var objects = [];
    var map = {};
    
	function updater(item) {
    	$('#cognome').val(map[item].cognome);
    	$('#nome').val(map[item].nome);
    	$('#codiceFiscale').val(map[item].codiceFiscale);
    	$('#email').val(map[item].email);
    	$('#organoDenominazioneInterni').val(map[item].organo);
    	$('#hiddenIdOrgano').val(map[item].idOrgano);
    	$('#hiddenUtenteAstage').val(map[item].id);
    	$('#sesso').val(map[item].sesso);
    	$('#sessoHidden').val(map[item].sesso);
    	$('#dataNascitaV').val(map[item].dataNascita);
        return item;
    }
	
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
        updater: function (item) {
        	$('#cognome').val(map[item].cognome);
        	$('#nome').val(map[item].nome);
        	$('#codiceFiscale').val(map[item].codiceFiscale);
        	$('#email').val(map[item].email);
        	$('#organoDenominazioneInterni').val(map[item].organo);
        	$('#hiddenIdOrgano').val(map[item].idOrgano);
        	$('#hiddenUtenteAstage').val(map[item].id);
        	$('#sesso').val(map[item].sesso);
        	$('#sessoHidden').val(map[item].sesso);
        	$('#dataNascitaV').val(map[item].dataNascita);
            return item;
        }
    });  
	
    $('#nominativoUtente').on('change', function () {
    	var nominativoUtente = $.trim( $(this).val() );
    	if( nominativoUtente=='' ){
    		cleanUtente();
    	}
    });
	
}
   
    /****** FINE GESTIONE AMMINISTRAZIONE ******/
    
	/************** FORMATTAZIONE DATA****************/
	$(".dataValid").on('keypress', 
			function() {
		if(window.event.keyCode>=48 && window.event.keyCode<=57){
			if($(this).val().length == 2 || $(this).val().length == 5 ){
				$(this).val($(this).val() + "/");
			}else if($(this).val().length == 10 ){
				return false;
			}
		}else{
			return false;
		}
	});
	/************** FINE FORMATTAZIONE DATA****************/


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
		
		var url =  "modifica/inserisciAllegato";
		if (detectIE()) {
			
			var token = $("meta[name='_csrf']").attr("content");
			
			url += "?_csrf=" + token;
		}
		
		 $('#allegatoForm').ajaxForm({
			 dataType: 'text',   
			 contentType: "multipart/form-data",
			 url: url,
			 beforeSend: function(xhr) {
				 var token = $("meta[name='_csrf']").attr("content");
				 var header = $("meta[name='_csrf_header']").attr("content");
				 xhr.setRequestHeader(header, token);
			 },
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
	
	
	
	$('#senzaTermine').click(function () {
		testcheckedsenzaTermine();
	});
	testcheckedsenzaTermine();
	
	function testcheckedsenzaTermine(){
		if($("#senzaTermine").is(':checked')){
		    // checked
			$("#dp1").hide();
			$("#dp1v").val('');
			$("#dp1v").attr('readonly', true);
			$("#plus30").hide();
			$("#plus60").hide();
			$("#plus90").hide();
			$("#plus120").hide();
		}else{
			// unchecked
			$("#dp1").show();
			$("#plus30").show();
			$("#plus60").show();
			$("#plus90").show();
			$("#plus120").show();
			$("#dp1v").attr('readonly', false);
		}
	}
	
	$("#statoDiAttuazioneDettaglio").change(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'cambioStato')
        .appendTo('#provvedimentoDettaglio');
		$( "#provvedimentoDettaglio" ).submit();
	});
	
	$("button#annullaIndietroDettaglio").click(function(){
		$('<input />').attr('type', 'hidden')
        .attr('name', 'annullaIndietroDettaglio')
        .appendTo('#provvedimentoModifica');
		$( "#provvedimentoModifica" ).submit();
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
		var oForm = $('#provvedimento');
		oForm.append("<input type='hidden' name='saveNoteAllegati' />");
		oForm.submit();
	});
	
	$("button#annullaNoteAllegati").click(function(){
		var oForm = $('#provvedimento');
		oForm.append("<input type='hidden' name='cancelNoteAllegati' />");
		oForm.submit();
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
	
	$(document).on('click', '#eliminaAssegnatario', function() { 
		var id = $(this).parent().siblings(":first").text(); 
		var trTableToDelete = $(this).parent().parent();
		$.ajax({
	    	type: 'GET',
	    	url: 'deleteAssegnatario/'+id,
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(response) {
				trTableToDelete.remove();
				if($('#idOrganiAggiunti').val()!=undefined){
					var arrayOrganiAggiunti = $('#idOrganiAggiunti').val().split(',');
					arrayOrganiAggiunti = $.grep(arrayOrganiAggiunti, function(value) {
						return value != response.idOrgano;
					});
					$('#idOrganiAggiunti').val(arrayOrganiAggiunti);
				}
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
				eliminaNessunRisultatoAssegnatario('#assegnazione');
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
	
	// EXPORT XLS
	gestioneExportXls();
	
	// LOGOUT
	gestioneLogout();

    // NOTIFICHE
	gestioneNotifiche();
	
	// MODALE RICHIESTA ASSEGNAZIONE
	gestionePopupRichiestaAssegnazione();

	// MODALE RIFIUTO ASSEGNAZIONE
	gestionePopupRifiutoAssegnazione(); 
	
	//INSERIMENTO
	gestioneInserimento();
	
	//POPOVER RIFIUTO
	gestionePopoverRifiuto();
	
	//GESTIONE MODIFICA TERMINE SCADENZA +30 +60 +90 +120 GIORNI
	gestioneTermineScadenza();
	
	//GESTIONE NORMATTIVA
	gestioneNormattiva();
	
	//GESTIONE MODALE CRONOLOGIA
	$("a[data-target=#modalCronologia]").click(function(ev) {
	    ev.preventDefault();
	    var target = $(this).attr("href");

	    // load the url and show modal on success
	    $("#modalCronologia .modal-body").load(target, function() { 
	         $("#modalCronologia").modal("show"); 
	    });
	});
	
	// GESTIONE INVIO MAIL FINE LAVORI
	gestioneInvioMailFineLavori();
});

function eliminaNessunRisultatoAssegnatario(id){
	if($(id+" tr.empty")) {
		$(id+" tr.empty").fadeOut( 500 );
	}
}

//LOGOUT
function gestioneExportXls() {
    $('#esportaXLS').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		var input = $("<input type='hidden' name='exportXls' />");
		
		$("form#ricercaProvvedimenti").append(input).submit();
		input.remove();
	});
}

// LOGOUT
function gestioneLogout() {
    $('#logout').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		var oForm = $(this).closest("form");
		oForm.submit();
	});
}

// GESTIONE INVIO MAIL FINE LAVORI
function gestioneInvioMailFineLavori() {

    function myFormatSelection(item) { 
		var retval = item.descrizione;
    	return retval; 
	};
	
   
    $('#salvaeinvianotifica').click( function () {
    	var target = $(location).attr('pathname');
    	target = target + '/salvaeinvianotifica?id=' + $('#idProvvedimento').val();
    	// load the url and show modal on success
        $("#modalSalvaInviaNotifica .modal-body").load(target, function() { 
             $("#modalSalvaInviaNotifica").on('shown', function() {
            	 
//            	 $(document).tokenfieldemail($("#tokenfieldemail"));
            	 
            	 $("#destinatariemail").select2({
 					multiple: true,
 					placeholder: "destinatari notifica",
 					allowClear: true,
 					minimumInputLength: 2,
 					ajax:{
 						url: "autocomplateutentemail",
 						dataType: 'json',
 						quietMillis: 250,
 						data: function (term) {
 							return {query: term};
 				        },
 				        results: function (data, page) {
						    return { results: data };
						}
 					},
 					formatSelection: myFormatSelection,
 					formatResult: myFormatSelection
 				
 				}); 
            	 
             }).modal("show");
        });
    });
    
    $('#inviaNotificaModal').click( function () {
    	var oForm = $(this).closest("form");
    	oForm.append("<input type='hidden' name='tokenfieldemail' value='"+$("#destinatariemail").val()+"' />"); 
    	oForm.append("<input type='hidden' name='salvaenotifica' />");
    	oForm.submit();
    });

	
}



// GESTIONE NOTIFICHE
function gestioneNotifiche() {
	
	var firstTime = true;
	var position = 0;
	
	$(".aprinotifica").click(function(e) {
    	e.preventDefault();
		e.stopPropagation();
		var url = $(this).find("a").attr("href");
		window.location.href = url;		
    }); 
	
	
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
	    
	    
	    if ($("#popoverNotifiche + .popover").hasClass('in')) {
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
				    	content : content 
			    }).on('shown', function () {

			    	if (firstTime) {
			    		position = parseInt($('#popoverNotifiche + .popover').css('left')) - 190;
			    		firstTime = false;
			    	}
			    		
				    $("#popoverNotifiche + .popover").css('left', position + 'px');
				    $("#popoverNotifiche + .popover > .arrow").css('left', '87.5%');
				    	
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


//GESTIONE POPOVER RIFIUTO
function gestionePopoverRifiuto() {
	
	// popover
	var popoverRifiuto = $(".popoverRifiuto"); 
	popoverRifiuto.click(function(e) {
	    e.stopPropagation();
	    e.preventDefault();
	    
	    
	    if ($(".popoverRifiuto + .popover").hasClass('in')) {
	    	popoverRifiuto.popover('hide');
	    } else {
		    var $div = $("<div>");
		    var content = "";
		    var url = popoverRifiuto.attr("href");

		    $div.load(url + " #contentRifiuto", function() {
		    	content = $(this).html();
		    	
		    	popoverRifiuto.popover({
				    	placement : 'top', 
				    	html: 'true',
				    	trigger: 'manual',
				    	title: 'Motivazione rifiuto',
				    	content : content 
		    	 }); 
				
		    	popoverRifiuto.popover('show');
		    });
	    }
	    
	    
	});
}

//MODALE RICHIESTA ASSEGNAZIONE
function gestionePopupRichiestaAssegnazione() {
	
	$('#richiediAssegnazione').click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		$("#modalRichiestaAssegnazione").on('shown', function() {
			
			$("#richiediAssegnazioneModal").click(function(e) {
				e.stopPropagation();
				e.preventDefault();
				
				$(this).closest("form").append("<input type='hidden' name='richiediAssegnazione' />").submit();
				
			}).modal({show: false});
			
		}); 
		
		$("#modalRichiestaAssegnazione").modal('show');
	});
	
}

//MODALE RIFIUTO ASSEGNAZIONE
function gestionePopupRifiutoAssegnazione() {
	
	$('#rifiutaAssegnazione').click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		$("#modalRifiutoAssegnazione").on('shown', function() {
			
			$("#rifiutoAssegnazioneModal").click(function(e) {
				e.stopPropagation();
				e.preventDefault();
				
				$(this).closest("form").append("<input type='hidden' name='rifiutaAssegnazione' />").submit();
				
			}).modal({show: false});
		}); 
		
		$("#modalRifiutoAssegnazione").modal('show');
		
	});
	
}


function gestioneInserimento(){
	if($('#tipologia').val()=="" || $('#tipologia').val()==1){
		$("#proponenteDiv").hide();
	} else {
		$("#proponenteDiv").show();
	}
    $('#tipologia').on('change', function () {
    	var val = $(this).val();
    	var option1 = "2"; //Concertante MEF
    	var option2 = "3"; //Concerto preventivo
    	if((val==option1) || (val==option2)){
    		$("#proponenteDiv").show();
    	} else {
    		$("#proponenteDiv").hide();
    	}
    }); 
	
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
				.append($('<td>').text(item.id).attr("class","hidden"))
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
	var arrayOrganiAggiunti = $('#idOrganiAggiunti').val().split(',');
	//verifico se l'organo è gia stato aggiunto
	if($.inArray( $('#assegnatario').val(), arrayOrganiAggiunti )<0){
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
	        	addUpdList('#idOrganiAggiunti',response.idOrgano);
	    	},
	    	error: function(){
	    		alert("Inserimento non riuscito");
	    	}
	    });
	}
}
function do_submitAllegatoIns() {
	var progress = $('.progress');
	var bar = $('.bar');
	var percent = $('.percent');

	var url =  "modifica/inserisciAllegato";
	if (detectIE()) {
		
		var token = $("meta[name='_csrf']").attr("content");
		
		url += "?_csrf=" + token;
	}
	
	
	var req = $('#provvedimentoInserisci').ajaxSubmit({
		 dataType: 'text',   
		 contentType: "multipart/form-data",
		 url: url,
		 beforeSend: function(xhr, settings) {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				xhr.setRequestHeader(header, token);
		 },
		 beforeSubmit: function(arr, $form, options) {
//			var token = $("meta[name='_csrf']").attr("content");
//			var header = $("meta[name='_csrf_header']").attr("content");
//			xhr.setRequestHeader(header, token);
			
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

function gestioneNormattiva(){
	$("#dataAttoV, #tipoAtto, #numeroAtto, #art").each(function(){
		$(this).focusout(function(){
		  var staticUrl = "http://www.normattiva.it/uri-res/N2Ls?urn:nir:stato:";
		  var dataAtto = $("#dataAttoV").val().replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$3-$2-$1");
		  
		  if (dataAtto == $("#dataAttoV").val()) {
			  dataAtto = "NaN-NaN-NaN";
		  }
		  
		  var numeroAtto = $("#numeroAtto").val();
		  var articolo = $("#art").val();
		  
		  var enableLink = false;
		  
		  var tipoAtto = $("#tipoAtto option:selected" ).val();
		  //COSTITUZIONE - CS
		  if(tipoAtto=='1'){
			  var append = "costituzione:1947-12-27";
			  if(articolo!=undefined && articolo!=""){
				  append = append+"~art"+articolo;
			  }
			  staticUrl=staticUrl+append;
			  enableLink = true;
		  }
		  //DECRETO LEGGE - DL == 2 --- //LEGGE - L == 3
		  if(tipoAtto=='2' || tipoAtto=='3'){
			  var append = "";
			  if(tipoAtto=='2'){
				  append = "decreto.legge:AAAA-MM-GG;NNN";
			  }
			  if(tipoAtto=='3'){
				  append = "legge:AAAA-MM-GG;NNN";
			  }
			  if(dataAtto!=undefined && dataAtto!="NaN-NaN-NaN"){
				  append = append.replace("AAAA-MM-GG", dataAtto);
			  }
			  if(numeroAtto!=undefined && numeroAtto!=""){
				  append = append.replace("NNN",numeroAtto);
			  }
			  if(articolo!=undefined && articolo!=""){
				  append = append+"~art"+articolo;
			  }
			  if(dataAtto!=undefined && dataAtto!="NaN-NaN-NaN" && numeroAtto!=undefined && numeroAtto!=""){
				  enableLink= true;
			  }
			  
			  staticUrl=staticUrl+append;
		  }
		  
		  if($("#linkNormattiva").val()==undefined){
			  $('<a>',{
				    text:staticUrl,
				    href:staticUrl,
				    id:'linkNormattiva',			    
				}).appendTo('#linkNormattivaSpan');
			  if($("#collNormattiva").val()!=undefined && $("#collNormattiva").val()!=""){
				  $("#linkNormattiva").text($("#collNormattiva").val());
				  $("#linkNormattiva").attr('href',$("#collNormattiva").val());
				  $("#linkNormattiva").attr('target',"_blank");
			  }
		  }
		  
		  if(enableLink){
			  $("#linkNormattiva").text(staticUrl);
			  $("#linkNormattiva").attr('href',staticUrl);
			  $("#linkNormattiva").attr('target',"_blank");
			  $("#collNormattiva").val(staticUrl);
		  } else {
			  $("#linkNormattiva").text('Inserimento non completato');
			  $("#linkNormattiva").attr('href','#');
			  $("#linkNormattiva").attr('target',"_self");
		  }
		  

		  
		  //$("#collNormattiva").val(staticUrl);
		});
	});
	  if($("#linkNormattiva").val()==undefined){
		  $('<a>',{
			    id:'linkNormattiva',			    
			}).appendTo('#linkNormattivaSpan');
		  if($("#collNormattiva").val()!=undefined && $("#collNormattiva").val()!=""){
			  $("#linkNormattiva").text($("#collNormattiva").val());
			  $("#linkNormattiva").attr('href',$("#collNormattiva").val());
			  $("#linkNormattiva").attr('target',"_blank");
		  }
	  }
}


function detectIE() {
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf('MSIE ');
    var trident = ua.indexOf('Trident/');

    if (msie > 0) {
        // IE 10 or older => return version number
        return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
    }

    if (trident > 0) {
        // IE 11 (or newer) => return version number
        var rv = ua.indexOf('rv:');
        return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
    }

    // other browser
    return false;
}


function gestioneTermineScadenza(){
	
	function dateToString(dData){
	    /*
		var ndateArr = dData.toString().split(' ');
	    var Months = 'Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec';
	    var dd = ndateArr[2].toString();
	    var mm = ((parseInt(Months.indexOf(ndateArr[1])/4)+1)).toString();;
	    var yyyy = ndateArr[3];
	    */
		var dd = (dData.getDate()).toString();
	    var mm = (dData.getMonth() + 1).toString();
	    var yyyy = (dData.getFullYear()).toString();
	    var sData = ((dd[1])?dd:'0'+dd) +'/'+ ((mm[1])?mm:'0'+mm) +'/'+ yyyy;
	    return sData;
	}
	function addDayToTermineScadenza(addDay){
		var oldDate = $("#dp1v").val();
		var newDate;
		if(oldDate==""){
		    oldDate = dateToString(new Date());
		}
		var numbers = oldDate.match(/\d+/g); 
		newDate = new Date(numbers[2], numbers[1]-1, parseInt(numbers[0])+addDay);
		var sNewDate = oldDate = dateToString(newDate);
		$('#dp1v').val(sNewDate);
	}
	
	$("#plus30").on('click', function(){
		addDayToTermineScadenza(30);
	});
	$("#plus60").on('click', function(){
		addDayToTermineScadenza(60);
	});
	$("#plus90").on('click', function(){
		addDayToTermineScadenza(90);
	});
	$("#plus120").on('click', function(){
		addDayToTermineScadenza(120);
	});
	
}