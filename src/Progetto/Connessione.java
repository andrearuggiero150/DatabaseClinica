package Progetto;

import java.sql.*;
import java.util.ArrayList;

public class Connessione {
    private Connection conn = null;

    public Connessione() {
        String url = "jdbc:mysql://localhost:3306/progetto";
        String user = "root";
        String password = "andrea11";
        try {
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null)
                System.out.println("Connessione stabilita");
        } catch (SQLException e) {
                throw new RuntimeException("Connessione fallita");
        }
    }

    public int inserimentoPaziente(String CF, String Nome, String Cognome, String CAP, String Via, String Citta) {
        String query = "INSERT INTO Paziente(CF,Nome,Cognome,CAP,Via,Citta) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, CF);
            statement.setString(2, Nome);
            statement.setString(3, Cognome);
            statement.setString(4, CAP);
            statement.setString(5, Via);
            statement.setString(6, Citta);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public ArrayList<Paziente> selectAll() {
        String query = "SELECT * FROM Paziente;";
        ArrayList<Paziente> listaPazienti = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                String temp3 = rs.getString(3);
                String temp4 = rs.getString(4);
                String temp5 = rs.getString(5);
                String temp6 = rs.getString(6);
                listaPazienti.add(new Paziente(temp1, temp2, temp3, temp4, temp5, temp6));
            }
        } catch (SQLException e) {
            return null;
        }
        return listaPazienti;
    }

    public int eliminaPaziente(String CF) {
        String query = "DELETE FROM Paziente WHERE CF = ?;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, CF);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public ArrayList<Paziente> cercaConCitta(String Citta) {
        String query = "SELECT * FROM Paziente WHERE Citta = ?";
        ArrayList<Paziente> listaPazienti = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, Citta);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                String temp3 = rs.getString(3);
                String temp4 = rs.getString(4);
                String temp5 = rs.getString(5);
                String temp6 = rs.getString(6);
                listaPazienti.add(new Paziente(temp1, temp2, temp3, temp4, temp5, temp6));
            }
        } catch (SQLException e) {
            return null;
        }
        return listaPazienti;
    }

    public int modificaNome(String CF, String newNome) {
        String query = "UPDATE Paziente SET Nome = ? WHERE CF = ?;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newNome);
            statement.setString(2, CF);
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
