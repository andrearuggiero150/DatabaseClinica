package Progetto;

public class Paziente {
    private String CF;
    private String nome;
    private String cognome;
    private String CAP;
    private String via;
    private String citta;

    public Paziente(String CF, String nome, String cognome, String CAP, String via, String citta) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.CAP = CAP;
        this.via = via;
        this.citta = citta;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return CF + ", " + nome + ", " + cognome + ", " + CAP + ", " + via + ", " + citta;
    }
}
