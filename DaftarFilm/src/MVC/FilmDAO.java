/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.sql.*;
import javax.swing.JOptionPane;

public class FilmDAO {
    private int jmlData;
    private Connection koneksi;
    private Statement statement;
    //constructor berfungsi utk melakukan sebuah koneksi saat ada object baru dibuat
    public FilmDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/film";
            koneksi = DriverManager.getConnection(url, "root", "");
            statement = koneksi.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Class Not Found : " + ex);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
        }
    }
    
    public void Insert(FilmModel Model){
        try{
            String query = "INSERT INTO data_film VALUES (NULL,'"+Model.getJudul()+"','"+
                    Model.getTipe()+"','"+Model.getEpisode()+"','"+
                    Model.getGenre()+"','"+Model.getStatus()+"','"+Model.getRating()+"')";
            statement.executeUpdate(query); //execute querynya
            JOptionPane.showMessageDialog(null, "Data disimpan");
        } catch (Exception sql){
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public void Update(FilmModel Model) {
        try{
            String query = "UPDATE data_film SET tipe = '"+Model.getTipe()+
                    "', judul = '"+Model.getJudul()+"', episode = '"+Model.getEpisode()+
                    "', genre = '"+Model.getGenre()+"', status = '"+Model.getStatus()+
                    "', rating = '"+Model.getRating()+"' WHERE id = '"+Model.getId()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil di Update");
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
    
    public void Delete(FilmModel Model) {
        try{
            String query = "DELETE FROM data_film WHERE id = '"+Model.getId()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil di Hapus");
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
    
    //utk mengambil data dari db dan mengatur ke dalam tabel
    public String[][] readFilm(){
        try{
            int jmlData = 0; //menampung jmlh data
            //baris sejumlah data, kolomnya ada 8
            String data[][] = new String[getJmldata()][8];
            //penganmbilan dta dlm java dari db
            String query = "SELECT * FROM data_film";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = String.valueOf(jmlData+1);//penomoran
                data[jmlData][1] = resultSet.getString("id");//id adalah nama field di database
                data[jmlData][2] = resultSet.getString("judul");
                data[jmlData][3] = resultSet.getString("tipe");
                data[jmlData][4] = resultSet.getString("episode");
                data[jmlData][5] = resultSet.getString("genre");
                data[jmlData][6] = resultSet.getString("status");
                data[jmlData][7] = resultSet.getString("rating");
                jmlData++; //barisnya berpindah terus
            }
            return data;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    public String[][] readSearch(FilmModel Model){
        try{
            int jmlData = 0; //menampung jmlh data
            String judul = Model.getSearch();
            //baris sejumlah data, kolomnya ada 8
            String data[][] = new String[getJmldataSearch(judul)][8];
            //penganmbilan dta dlm java dari db
            String query = "SELECT * FROM data_film WHERE judul LIKE '%" + Model.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = String.valueOf(jmlData+1);//penomoran
                data[jmlData][1] = resultSet.getString("id");//id adalah nama field di database
                data[jmlData][2] = resultSet.getString("judul");
                data[jmlData][3] = resultSet.getString("tipe");
                data[jmlData][4] = resultSet.getString("episode");
                data[jmlData][5] = resultSet.getString("genre");
                data[jmlData][6] = resultSet.getString("status");
                data[jmlData][7] = resultSet.getString("rating");
                jmlData++; //barisnya berpindah terus
            }
            return data;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    public int getJmldata(){
        int jmlData = 0;
        try{
            //pengambilan data kedlm java dri db
            String query = "SELECT * FROM data_film";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){//lanjut kedata selanjutnya, jmlData bertambah
                jmlData++;
            }
            return jmlData;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
    
    public int getJmldataSearch(String judul){
        int jmlData = 0;
        try{
            //pengambilan data kedlm java dri db
            String query = "SELECT * FROM data_film WHERE judul LIKE '%" + judul + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){//lanjut kedata selanjutnya, jmlData bertambah
                jmlData++;
            }
            return jmlData;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

}

