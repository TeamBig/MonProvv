
<div class="container collapse" id="campiRicerca">
	<div class="row">
		<div class="span12">
			<h3 class="underline">
				<span>Ricerca provvedimento</span>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="span6">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="governo">Governo</label>
					<div class="controls">
						<select id="governo" class="input-xlarge">
							<option>Tutti</option>
							<option>Monti</option>
							<option>Letta</option>
							<option>Renzi</option>
						</select>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="art">Art.</label>
					<div class="controls">
						<input type="text" id="art" class="input-small">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="titoloOggetto">Titolo /
						Oggetto</label>
					<div class="controls">
						<input type="text" id="titoloOggetto" class="input-xlarge">
					</div>
				</div>

				<div class="control-group">
					<div class="controls">
						<h5>
							<a data-toggle="collapse" data-target="#ricercaAvanzata"
								id="btnRicercaAvanzata"> Ricerca avanzata <i
								class="icon-caret-down icon-large" id="btnRicAvDown"></i> <i
								class="icon-caret-up icon-large" id="btnRicAvUp"></i>
							</a>
						</h5>
					</div>
				</div>

			</div>
		</div>
		<div class="span6">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="tipologia">Tipologia</label>
					<div class="controls">
						<select id="tipologia" class="input-xlarge">
							<option>Tutte</option>
							<option>Proponente MEF</option>
							<option>Concertante MEF</option>
							<option>Concerto preventivo</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="comma">Comma</label>
					<div class="controls">
						<input type="text" id="comma" class="input-small">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="statoDiAttuazione">Stato
						di attuazione</label>
					<div class="controls">
						<select id="statoDiAttuazione" class="input-xlarge">
							<option>Tutti gli stati</option>
							<option>Inserito</option>
							<option>Sospeso</option>
							<option>Fine lavorazione</option>
							<option>Chiusura lavori</option>
							<option>Adottato</option>
							<option>Non attuabile</option>
							<option>Superato</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row collapse" id="ricercaAvanzata">
		<div class="span6">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="dataDa">Termine di
						scadenza</label>
					<div class="controls form-inline">
						<label for="dp1v">Da&nbsp;</label> <input type="text" id="dp1v"
							class="input-small" maxlength="10" placeholder="GG/MM/AAAAA">&nbsp;
						<i class="icon-calendar icon-large" id="dp1"></i> <label
							for="dp2v">&nbsp;&nbsp;A&nbsp;</label> <input type="text"
							id="dp2v" class="input-small" maxlength="10"
							placeholder="GG/MM/AAAAA">&nbsp;<i
							class="icon-calendar icon-large" id="dp2"></i>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputFonte">Fonte
						normativa</label>
					<div class="controls">
						<input type="text" id="inputFonte" class="input-xlarge">
					</div>
				</div>

			</div>
		</div>
		<div class="span6">
			<div class="form-horizontal">

				<div class="control-group">
					<label class="control-label" for="provvDaAdottare">Provvedimento
						da adottare</label>
					<div class="controls">
						<select id="provvDaAdottare" class="input-xlarge">
							<option>Tutti</option>
							<option>Convenzione</option>
							<option>D.I.</option>
							<option>D.M.</option>
							<option>Decreto direttoriale</option>
							<option>d.P.C.M.</option>
							<option>DPR (regolamento delegificazione)</option>
							<option>Altro</option>
						</select>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="enteAssegnatario">Amm./uffici
						coinvolti</label>
					<div class="controls">
						<select id="enteAssegnatario" class="input-xlarge multiselect"
							multiple="multiple">
							<option>Tutti</option>
							<option>Agenzia Entrate e Territorio</option>
							<option>Agenzia Dogane e Monopoli</option>
							<option>Agenzia Entrate</option>
							<option>Dipartimento finanze (DLTFF)</option>
							<option>Dip.to Tesoro</option>
							<option>Guardia di finanza</option>
							<option>Ragioneria Generale dello Stato</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<div class="form-horizontal">
				<div class="control-group">
					<div class="form-actions pull-right">
						<button type="submit" class="btn " id="ricerca">
							Ricerca &nbsp;<i class="icon-search"></i>
						</button>
						<button type="button" class="btn " id="annulla">
							Pulisci &nbsp;<i class="icon-eraser"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<h3 class="text-left underline">
				<span>Elenco provvedimenti</span>
				<button type="submit" class="btn btn-primary pull-right" id="nuovo">
					Nuovo provvedimento &nbsp;<i class="icon-plus"></i>
				</button>
				<button class="btn pull-right" id="toggleRicerca"
					data-toggle="collapse" data-target="#campiRicerca"
					style="margin-right: 10px;">
					Toggle ricerca &nbsp;<i class="icon-search"></i>
				</button>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Governo</th>
						<th>Tipologia</th>
						<th>Fonte nominativa</th>
						<th>Art.</th>
						<th>Comma</th>
						<th>Provv. da adottare</th>
						<th>Titolo / Oggetto</th>
						<th>Stato di attuazione</th>
						<th>Capofila</th>
						<th>Proponente</th>
						<th>Assegnazione</th>
						<th>Allegati</th>
						<!-- 
							<th>
								Elimina 
							</th>
							 -->
					</tr>
				</thead>
				<tbody>
					<tr class="color-capofila">
						<td><a href="#">1</a></td>
						<td>Letta</td>
						<td>Concertante MEF</td>
						<td>D.L. 145/2013 conv. Legge 9/2014</td>
						<td>2</td>
						<td>1 lett. h)</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Definizione criteri e
								modalità di concessione aevolazioni in favore della nuova
								imprenditorialità nei settori dei beni e dell'erogazione dei
								servizi</span></td>
						<td>Inserito</td>
						<td>Agenzia Dogane e Monopoli</td>
						<td>Min.affari europei</td>
						<td class="nowrap">Agenzia Entrate e Territorio&nbsp;&nbsp; <i
							class="icon-check " title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip " title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
							RGS&nbsp;&nbsp; <i class="icon-remove-sign "
							title="Assegnazione rifiutata"></i><br> Dipartimento finanze
							(DLTFF)&nbsp;&nbsp; <i class="icon-check "
							title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip " title="Allegati inseriti"></i>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
						<!-- 
							<td class="center">
								<i class="icon-trash icon-large gray"></i>
							</td>
							 -->
					</tr>
					<tr class="color-assegnatario">
						<td><a href="#">2</a></td>
						<td>Monti</td>
						<td>Proponente MEF</td>
						<td>L. 147/2013</td>
						<td>1</td>
						<td>264</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Determinazione
								dell'indennità onnicomprensiva spettante al personale delle
								Forze armate impiegato ai ses</span></td>
						<td>Inserito</td>
						<td>Dip.to Tesoro</td>
						<td></td>
						<td class="nowrap">Agenzia Dogane e Monopoli&nbsp;&nbsp; <i
							class="icon-check" title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
							RGS&nbsp;&nbsp; <i class="icon-remove-sign "
							title="Assegnazione rifiutata"></i><br>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
						<!-- 
							<td class="center">
								<i class="icon-trash icon-large gray"></i>
							</td>
							 -->
					</tr>
					<tr>
						<td><a href="#">3</a></td>
						<td>Monti</td>
						<td>Proponente MEF</td>
						<td>L. 147/2013</td>
						<td>1</td>
						<td>269</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Determinazione annuale
								della percentuale (comunque non inferiore al 20%) di cui
								all’art. 188-bis del TUIR (riduzione forfettaria cambio euro
								franco svizzero ai fini dell’IRPEF persone fisiche Campione
								d’Italia).</span></td>
						<td>Inserito</td>
						<td>Dip.to Tesoro</td>
						<td></td>
						<td class="nowrap">Agenzia Territorio&nbsp;&nbsp; <i
							class="icon-check" title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
							RGS&nbsp;&nbsp; <i class="icon-remove-sign"
							title="Assegnazione rifiutata"></i><br>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
						<!-- 
							<td class="center">
								<i class="icon-trash icon-large gray"></i>
							</td>
							 -->
					</tr>
					<tr>
						<td><a href="#">4</a></td>
						<td>Letta</td>
						<td>Proponente MEF</td>
						<td>L. 171/2013</td>
						<td>1</td>
						<td>8</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Ridefinizione delle
								percentuali di fruizione dei crediti di imposta rideterminati ai
								sensi del co. 577, nel caso in cui il monitoraggio dei crediti
								di imposta indichi che sia in procinto di verificarsi uno
								“splafonamento”.</span></td>
						<td>Adottato</td>
						<td>RGS</td>
						<td></td>
						<td class="nowrap">Dip.to tesoro&nbsp;&nbsp; <i
							class="icon-check" title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br> Agenzia
							Entrate&nbsp;&nbsp; <i class="icon-check"
							title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
					</tr>
					<tr>
						<td><a href="#">5</a></td>
						<td>Letta</td>
						<td>Proponente MEF</td>
						<td>L. 45/2013</td>
						<td>2</td>
						<td>66</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Definizione delle
								modalità per i pagamento in via telematica dell’imposta di bollo
								dovuta per istanze e atti e provvedimenti anche attraverso
								utilizzo di carte di credito</span></td>
						<td>Adottato</td>
						<td>Dip.to tesoro</td>
						<td></td>
						<td class="nowrap">Dip.to finanze&nbsp;&nbsp; <i
							class="icon-check" title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
							RGS&nbsp;&nbsp; <i class="icon-check"
							title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br> Agenzia
							Entrate e Territorio&nbsp;&nbsp; <i class="icon-remove-sign"
							title="Assegnazione rifiutata"></i><br>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
					</tr>
					<tr>
						<td><a href="#">6</a></td>
						<td>Letta</td>
						<td>Proponente MEF</td>
						<td>L. 5/2012</td>
						<td>3</td>
						<td>78</td>
						<td>D.I.</td>
						<td class="titoloOggetto"><span>Eventuale definizione
								di ulteriori criteri per garantire la tracciabilità delle
								erogazioni liberali in danaro a favore dei partiti politici.</span></td>
						<td>Sospeso</td>
						<td>RGS</td>
						<td></td>
						<td class="nowrap">Dip.to finanze&nbsp;&nbsp; <i
							class="icon-remove-sign" title="Assegnazione rifiutata"></i><br>
							Agenzia Entrate e Territorio&nbsp;&nbsp; <i class="icon-check"
							title="Assegnazione presa in carico"></i>&nbsp;<i
							class="icon-paper-clip" title="Allegati inseriti"></i>&nbsp;<i
							class="icon-file-alt" title="Nota inserita"></i><br>
						</td>
						<td class="center"><i class="icon-paper-clip icon-large gray"
							title="Allegati al provvedimento presenti"></i></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="span12">
			<button type="submit" class="btn pull-right" id="esportaXLS"
				style="margin-right: 10px;">
				Esporta in excel &nbsp;<i class="icon-ms-excel"></i>
			</button>

			<ul class="unstyled">
				<li><span class="icon-stack"> <i
						class="icon-sign-blank icon-stack-base color-capofila"></i>
				</span> <span> Provvedimenti inseriti o di cui si è capofila </span></li>
				<li><span class="icon-stack"> <i
						class="icon-sign-blank icon-stack-base color-assegnatario"></i>
				</span> <span> Provvedimenti assegnati </span></li>
			</ul>


			<div class="pagination pagination-centered">
				<ul>
					<li><a class="disabled" href="#">&#171;</a></li>
					<li><a class="active" href="#">1</a></li>
					<li><a class="disabled" href="#">&#187;</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>