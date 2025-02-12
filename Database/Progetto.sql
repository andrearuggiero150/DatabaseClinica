drop database if exists progetto;
create database progetto;

use progetto;

create table Paziente (
CF char(16) primary key,
Nome varchar(30) not null,
Cognome varchar(30) not null,
CAP char(5) not null,
Via varchar(30) not null,
Citta varchar(30) not null );

create table Reparto (
ID char(5) primary key,
Nome varchar(30) not null,
Telefono char(10) not null );

create table Esame (
ID char(5) primary key,
Nome varchar(30) not null, 
Prezzo dec(10,2) not null, 
RepartoID char(10) not null,
foreign key (RepartoID) references Reparto(ID) );

create table PrenotazioneEsame (
PazienteCF char(16) not null,
EsameID char(10) not null,
DataPrenotazione date not null,
primary key (PazienteCF, EsameID, DataPrenotazione),
foreign key (PazienteCF) references Paziente(CF),
foreign key (EsameID) references Esame(ID) );

create table Dottore (
Matricola char(10) primary key,
Nome varchar(30) not null, 
Cognome varchar(30) not null, 
Specializzazione varchar(30) not null,
RepartoID char(10) not null,
GiornoLibero varchar(30) not null,
foreign key (RepartoID) references Reparto(ID) );

create table Cellulare (
Numero char(10) primary key,
DottoreMatricola char(10) not null,
foreign key (DottoreMatricola) references Dottore(Matricola) );

create table Infermiere (
Matricola char(10) primary key,
Nome varchar(30) not null, 
Cognome varchar(30) not null, 
Tipo varchar(30) not null,
RepartoID char(10) not null,
GiornoLibero varchar(30) not null,
foreign key (RepartoID) references Reparto(ID) );

create table Associazione (
RagSoc varchar(30) not null, 
Citta varchar(30) not null,
primary key (RagSoc, Citta) );

create table Volontario (
Matricola char(10) primary key,
Nome varchar(30) not null, 
Cognome varchar(30) not null, 
RepartoID char(10) not null,
GiornoLibero varchar(30) not null,
AssociazioneRagSoc varchar(30) not null,
AssociazioneCitta varchar(30) not null,
foreign key (AssociazioneRagSoc, AssociazioneCitta) references Associazione(RagSoc, Citta),
foreign key (RepartoID) references Reparto(ID) );

insert into Paziente values
("RGGNDR03A16A509R", "Andrea", "Ruggiero", "83020", "Via Casal di Creta", "Forino"),
("RGGMRA76C06A509W", "Mario", "Ruggiero", "83020", "Via Casal di Creta", "Forino"),
("CNRPVM75C46A752G", "Antonio", "Rossi", "83100", "Via Roma", "Avellino"),
("BBKXTG53E67E255R", "Marco", "Verdi", "83100", "Via Garibaldi", "Avellino"),
("RRHKFV27C47A354P", "Francesco", "Esposito", "84120", "Via Forno", "Salerno"),
("HDHTLL52P29I190A", "Luca", "Finelli", "84120", "Via Napoleone", "Salerno"),
("RHCZQN35L13C847A", "Luigi", "Serini", "83100", "Via Garibaldi", "Avellino"),
("LSJBGD61A09F605D", "Oreste", "Sacco", "83100", "Via Roma", "Avellino"),
("FRBTXS75A14G008V", "Giovanni", "Grotta", "83042", "Via Castello", "Atripalda"),
("CGFMYR91C07C576C", "Sabatino", "Righelli", "83042", "Via Rosaria", "Atripalda");

insert into Reparto values
("R0001", "Allergologia", "0825101010"),
("R0002", "Cardiologia", "0825101011"),
("R0003", "NeuroPsichiatria", "0825101012"),
("R0004", "Pediatria", "0825101013"),
("R0005", "Radiologia", "0825101014"),
("R0006", "Terapia Intensiva", "0825101015"),
("R0007", "Chirurgia", "0825101016"),
("R0008", "Oculistica", "0825101017"),
("R0009", "Pneumolofgia", "0825101018"),
("R0010", "Dermatologia", "0825101019");

insert into Esame values
("E0001", "Prove Allergiche", "35", "R0001"),
("E0002", "Radiografia Torace", "30", "R0005"),
("E0003", "Radiografia Arto", "10.50", "R0005"),
("E0004", "Ecocolordoppler", "100", "R0002"),
("E0005", "Elettrocardiogramma", "20", "R0002"),
("E0006", "Spirometria", "25.50", "R0009"),
("E0007", "Test NeuroPsicologico", "63.80", "R0003"),
("E0008", "Emogasanalisi", "30", "R0009"),
("E0009", "Test Oculistico", "50", "R0008"),
("E0010", "Test Dermatologico", "30.80", "R0010");

insert into PrenotazioneEsame values 
("RGGNDR03A16A509R", "E0006", "2023-02-10"),
("FRBTXS75A14G008V", "E0006", "2023-02-10"),
("CNRPVM75C46A752G", "E0002", "2022-02-11"),
("CNRPVM75C46A752G", "E0003", "2023-02-11"),
("RHCZQN35L13C847A", "E0009", "2022-10-20"),
("RRHKFV27C47A354P", "E0005", "2022-02-10"),
("CGFMYR91C07C576C", "E0001", "2022-02-10"),
("RGGNDR03A16A509R", "E0001", "2022-02-10"),
("HDHTLL52P29I190A", "E0008", "2023-02-10"),
("HDHTLL52P29I190A", "E0010", "2023-02-10"),
("CNRPVM75C46A752G", "E0001", "2022-02-10"),
("LSJBGD61A09F605D", "E0001", "2022-02-10"),
("LSJBGD61A09F605D", "E0002", "2022-02-10"),
("LSJBGD61A09F605D", "E0003", "2022-02-10"),
("LSJBGD61A09F605D", "E0004", "2022-02-10"),
("LSJBGD61A09F605D", "E0005", "2022-02-10"),
("LSJBGD61A09F605D", "E0006", "2022-02-10"),
("LSJBGD61A09F605D", "E0007", "2022-02-10"),
("LSJBGD61A09F605D", "E0008", "2022-02-10"),
("LSJBGD61A09F605D", "E0009", "2022-02-10"),
("LSJBGD61A09F605D", "E0010", "2022-02-10"),
("LSJBGD61A09F605D", "E0002", "2022-04-10"),
("LSJBGD61A09F605D", "E0003", "2022-04-10");

insert into Dottore values 
("AAA0001AAA", "Mario", "Belli", "Chirurgo", "R0007", "Martedì"),
("AAA0002AAA", "Antonio", "Verdi", "Cardiologo", "R0002", "Lunedì"),
("AAA0003AAA", "Luigi", "Grassi", "Pediatra", "R0004", "Sabato"),
("AAA0004AAA", "Maria", "Lembi", "Oculista", "R0008", "Mercoledì"),
("AAA0005AAA", "Sara", "Della Cerra", "Dermatologo", "R0010", "Venerdì");

insert into Infermiere values 
("BBB0001BBB", "Vittorio", "Scotti", "Ferrista", "R0007", "Giovedì"),
("BBB0002BBB", "Dolores", "Camilleri", "Corsia", "R0003", "Lunedì"),
("BBB0003BBB", "Caterina", "Praga", "Pronto Soccorso", "R0004", "Mercoledì"),
("BBB0004BBB", "Raffaele", "Tommasetti", "Corsia", "R0001", "Giovedì"),
("BBB0005BBB", "Luciana", "Amato", "Pronto Soccorso", "R0005", "Martedì");

insert into Associazione values
("Croce Rossa", "Avellino"),
("Croce Rossa", "Salerno"),
("AVO", "Napoli");

insert into Volontario values 
("CCC0001CCC", "Giovanni", "Forza", "R0003", "Martedì", "Croce Rossa", "Avellino"),
("CCC0002CCC", "Antonia", "Nibali", "R0001", "Sabato", "Croce Rossa", "Salerno"),
("CCC0003CCC", "Donatello", "Viviani", "R0001", "Giovedì", "AVO", "Napoli"),
("CCC0004CCC", "Maria", "Ferrazzi", "R0007", "Lunedì", "AVO", "Napoli"),
("CCC0005CCC", "Aldo", "Guidi", "R0009", "Lunedì", "Croce Rossa", "Avellino");

insert into Cellulare values 
("3338247609", "AAA0001AAA"),
("3478154902", "AAA0001AAA"),
("3280164923", "AAA0002AAA"),
("3297592461", "AAA0004AAA"),
("3449275924", "AAA0004AAA"),
("3337584191", "AAA0005AAA"),
("3478294142", "AAA0003AAA"),
("3776485771", "AAA0002AAA"),
("3997285922", "AAA0003AAA"),
("3478265924", "AAA0002AAA");











