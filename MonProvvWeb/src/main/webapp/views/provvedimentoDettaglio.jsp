	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span>Dettaglio Provvedimento</span></h3>
			</div>
		</div>
		<form class="form-horizontal">
			<div class="row">
				<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<label class="control-label">Governo</label>
							<div class="controls">
								<span>Letta</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Tipologia</label>
							<div class="controls">
								<span>Concertante MEF</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Fonte
								normativa</label>
							<div class="controls">
								<span>D.L. 145/2013 conv. Legge 9/2014</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="art">Art.</label>
							<div class="controls">
								<span>2</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="comma">Comma</label>
							<div class="controls">
								<span>1 lett. h)</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="provvDaAdottare">Provvedimento da adottare</label>
							<div class="controls">
								<span>D.I.</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="titoloOggetto">Titolo /
								Oggetto</label>
							<div class="controls">
								<span>Definizione criteri e modalità di concessione aevolazioni in favore della nuova imprenditorialità nei settori dei beni e dell'erogazione dei servizi</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="titoloOggetto">Termine di scadenza</label>
							<div class="controls">
								<span>21/01/2014</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="statoDiAttuazioneDettaglio">Stato
								di attuazione</label>
							<div class="controls">
								<select id="statoDiAttuazioneDettaglio" class="input-xlarge">
									<option>Inserito</option>
									<option>Sospeso</option>
									<option selected>Fine lavorazione</option>
									<option>Chiusura lavori</option>
									<option>Adottato</option>
									<option>Non attuabile</option>
									<option>Superato</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="enteCapofila">Capofila (DD)</label>
							<div class="controls">
								<span>Agenzia Dogane e Monopoli</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="proponente">Proponente
								(DD)</label>
							<div class="controls">
								<span>Min.affari europei</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="proponente">Parere</label>
							<div class="controls">
								<span>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</span>
							</div>
						</div>
				</div>
			</div>
			<!-- Allegati insert -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline">
						<span>Allegati</span>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<table class="table table-hover table-bordered" style="width: 100%">
						<thead>
							<tr>
								<th>Id</th>
								<th>Descrizione</th>
								<th>Dimensione</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td class="vcenter"><a href="" class="download">Documento 1</a></td>
								<td>200 kb</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- allegati end -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline"><span>Assegnatari</span></h3>
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th class="medium">
									Organo
								</th>
								<th>
									Presa in carico
								</th>
								<th class="medium">
									Allegati
								</th>
								<th>
									Note
								</th>
								<th>
									Cronologia Modifiche
								</th>
								<th class="center">
									Elimina
								</th>								
								<th class="center">
									Sollecito
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="vcenter">
									Ag. Territorio 
								</td>
								<td class="vcenter center">
									<i class="icon-check icon-large"></i>
								</td>
								<td>
									<a href="" class="download">Documento 1</a> (PDF - 1Mb)<br>
									<a href="" class="download">Documento 2</a> (Excel - 350Kb)<br>
									<a href="" class="download">Documento 3</a> (Testo - 1Kb) <br>
								</td>
								<td>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
								</td>
								<td class="vcenter center">
									<a href="#modalCronologia" role="button" data-toggle="modal"><i class="icon-time icon-large"></i></a>
								</td>	
								<td class="vcenter center">
									<i class="icon-trash icon-large gray"></i>
								</td>
								<td class="vcenter center">
									<a href="#modalSollecito" role="button" data-toggle="modal"><i class="icon-envelope-alt icon-large" title="Invio sollecito"></i></a>
								</td>
							</tr>
 							<tr>
								<td>
									MEF (Dipto finanze)  
								</td>
								<td class="vcenter center">
									<a href="#" id="popoverRifiuto"><i class="icon-remove-sign icon-large" title="Assegnazione rifiutata"></i>&nbsp;Motivazione rifiuto</a>
								</td>
								<td>
								</td>
								<td>
									
								</td>
								<td class="vcenter center">
									<a href="#modalCronologia" data-toggle="modal"><i class="icon-time icon-large" title="Apri cronologia"></i></a>
								</td>	
								<td class="vcenter center">
									<i class="icon-trash icon-large" title="Elimina assegnazione"></i>
								</td>
								<td class="vcenter center">
									<a href="#modalSollecito" role="button" data-toggle="modal"><i class="icon-envelope-alt icon-large" title="Invio sollecito"></i></a>
								</td>															
							</tr>
							<tr id="assegnatarioDipTesoro">
								<td class="vcenter">
									Dip.to Tesoro
								</td>
								<td class="vcenter">
									
								</td>
								<td class="vcenter">
									
								</td>
								<td>
									
								</td>
								<td class="vcenter center">
								</td>									
								<td class="vcenter center">
									<a href="#" id="deleteEnte"><i class="icon-trash icon-large gray"></i></a>
								</td>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="control-group">
					<label class="control-label" for="enteAssegnatario">Nuovo assegnatario</label>
					<div class="controls">
						<select id="enteAssegnatario" class="span3">
									<option>Sceglierne uno...</option>  
									<option>Agenzia Entrate e Territorio                                                                                                 </option>
									<option>Agenzia Dogane e Monopoli                                                                                                    </option>
									<option>Agenzia Entrate                                                                                                              </option>
									<option>Dipartimento finanze (DLTFF)                                                                                                 </option>
									<option>Dip.to Tesoro                                                                                                                </option>
									<option>Guardia di finanza                                                                                                           </option>
									<option>Ragioneria Generale dello Stato                                                                                              </option>
						</select>
						<button type="button" id="insertEnte" class="btn">Aggiungi &nbsp;<i class="icon-plus"></i></button>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<div class="form-actions pull-right">
							<button type="submit" class="btn btn-primary" id="salva">Salva &nbsp;<i class="icon-save"></i></button>
							<button type="button" class="btn" id="annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
							<button type="submit" class="btn" id="modifica">Modifica &nbsp;<i class="icon-edit"></i></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<!--  finestra modale -->
 
	<div id="modalCronologia" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Cronologia modifiche</h3>
		</div>
		<div class="modal-body">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>Data</th>
						<th>Operazione</th>
						<th>Organo</th>
						<th>Utente</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>09-08-2014 09:37</td>
						<td>Inserimento nota</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Marco Iezzi</td>
					</tr>
					<tr>
						<td>10-08-2014 14:30</td>
						<td>Modifica nota</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Francesco Carlucci</td>
					</tr>
					<tr>
						<td>10-08-2014 15:56</td>
						<td>Inserimento allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Daniele Fiorio</td>
					</tr>
					<tr>
						<td>13-08-2014 10:23</td>
						<td>Inserimento allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Daniele Fiorio</td>
					</tr>
					<tr>
						<td>20-08-2014 17:10</td>
						<td>Cancellazione allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Alessandro Bartolucci</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Chiudi</button>
		</div>
	</div>
	
	<div id="modalSalvaInviaNotifica" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Invia notifica</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="email"></input>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="oggetto">Oggetto</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="oggetto"></input>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="art">Testo</label>
					<div class="controls">
						<textarea class="input-xlarge" rows="10"></textarea>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="inviaNotifica">Invia notifica <i class="icon-send"></i></button>
		</div>
	</div>
<!-- fine finestra modale -->

<!--  modal invio sollecito  -->
	<div id="modalSollecito" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Invio sollecito</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="art">Testo</label>
					<div class="controls">
						<textarea class="input-xlarge" rows="10"></textarea>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="richiediAssegnazione">Invia &nbsp;<i class="icon-location-arrow"></i></button>
		</div>
	</div>