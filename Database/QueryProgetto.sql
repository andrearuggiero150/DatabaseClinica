-- Elencare in ordine decrescente sul Cognome i pazienti residenti ad Avellino in via Roma o in via Garibaldi (CF, Cognome)
SELECT CF, Cognome
FROM Paziente p 
WHERE Citta = "Avellino" and (Via = "Via Roma" or Via = "Via Garibaldi")
ORDER BY Cognome DESC;

-- Elencare tutti i pazienti che hanno prenotato in data 2023-02-10 una Spirometria (CF, Cognome)
SELECT p.CF, p.Cognome
FROM Paziente p, PrenotazioneEsame pe, Esame e
WHERE p.CF = pe.PazienteCF and e.ID = pe.EsameID and pe.DataPrenotazione = "2023-02-10" and e.Nome = "Spirometria";

-- Visualizzare in numero totale di prenotazioni effettuate nel 2022 
SELECT count(*) as Totale
FROM PrenotazioneEsame
WHERE year(DataPrenotazione) = 2022;

-- Visualizzare per ogni paziente il numero di prenotazioni effettuate (CF, Cognome, Nome)
SELECT p.CF, p.Cognome, p.Nome, count(*) as Totale
FROM PrenotazioneEsame pe, Paziente p
WHERE p.CF = pe.PazienteCF 
GROUP BY p.CF, p.Cognome, p.Nome;

-- Visualizzare gli esami che sono stati prenotati almeno 3 volta (Nome)
SELECT e.Nome, count(*) as Totale
FROM PrenotazioneEsame pe, Esame e
WHERE e.ID = pe.EsameID
GROUP BY e.Nome
HAVING Totale > 2;

-- Visualizzare l'esame in assoluto più prenotato nel 2022 (Nome, Prezzo)
drop view if exists Contatore;
create view Contatore as
SELECT e.Nome, e.Prezzo, count(*) as Totale
FROM Esame e, PrenotazioneEsame pe
WHERE e.ID = pe.EsameID and year(pe.DataPrenotazione) = 2022
GROUP BY e.Nome, e.Prezzo;

SELECT *
FROM Contatore
WHERE Totale = (SELECT max(Totale) FROM Contatore);

-- Elencare i pazienti che hanno prenotato sia una Radiografia Torace che una Radiografia Arto (CF, Cognome, Nome)
SELECT DISTINCT p.CF, p.Cognome, p.Nome
FROM Paziente p, PrenotazioneEsame pe, Esame e
WHERE e.ID = pe.EsameID and p.CF = pe.PazienteCF and e.Nome = "Radiografia Torace" and p.CF in (SELECT p.CF
																								FROM Paziente p, PrenotazioneEsame pe, Esame e
																								WHERE e.ID = pe.EsameID and p.CF = pe.PazienteCF and e.Nome = "Radiografia Arto");
                                                                                                    
-- Elencare i pazienti che hanno prenotato tutti gli esami nel 2022 (CF, Cognome, Nome)
SELECT p.CF, p.Cognome, p.Nome
FROM Paziente p
WHERE not exists (SELECT *
				  FROM Esame e
                  WHERE not exists (SELECT *
									FROM PrenotazioneEsame pe
                                    WHERE year(pe.DataPrenotazione) = 2022 and e.ID = pe.EsameID and p.CF = pe.PazienteCF));
                                    
-- Elencare per ogni Paziente l'ammontare speso in esami (CF Paziente)
SELECT pe.PazienteCF, sum(e.Prezzo) as Totale
FROM PrenotazioneEsame pe, Esame e
WHERE e.ID = pe.EsameID
GROUP BY pe.PazienteCF;

-- Elencare i pazienti che hanno prenotato almeno un esame
SELECT DISTINCT pe.PazienteCF
FROM PrenotazioneEsame pe, Paziente p
WHERE p.CF = pe.PazienteCF;

-- Elencare i pazienti che non hanno prenotato alcun esame
SELECT CF
FROM Paziente
WHERE CF not in (SELECT pe.PazienteCF 
				 FROM Paziente p, PrenotazioneEsame pe 
                 WHERE p.CF = pe.PazienteCF);
                 
                 

