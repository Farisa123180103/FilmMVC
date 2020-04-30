
package MVC;

import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class FilmView extends JFrame{
    JLabel ljudul,ltipe,lepisode,lgenre,lstatus,lrating;
    JTextField txjudul,txtipe,txepisode,txgenre,txrating,txsearch;
    JButton create, delete, update, exit, refresh, search;
    JTable tabel;
    JComboBox status;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;//buat scroll
    Object namaKolom[] = {"#", "ID", "Judul", "Tipe", "Episode", "Genre", "Status", "Rating"};//membuat kolom dlm array
    Statement statement;
    
    public FilmView(){
        setTitle("FORM PENGISIAN DATA FILM");
        String cstatus[] = {"Selesai","Belum"};//membuat isi combobox
        //atur tabel
        tableModel = new DefaultTableModel(namaKolom,0); //0 baris
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);
        //atur tabel
        ljudul = new JLabel("Judul");
        ltipe = new JLabel("Tipe");  
        lepisode = new JLabel("Episode");
        lgenre = new JLabel("Genre");
        lstatus = new JLabel("Status");
        lrating = new JLabel("Rating");
        //atur textfield
        txjudul = new JTextField("");
        txtipe = new JTextField("");
        txepisode = new JTextField("");
        txgenre = new JTextField("");
        txrating = new JTextField("");
        txsearch = new JTextField("");
        //atur ComboBox
        status = new JComboBox(cstatus);
        //atur tombol
        create = new JButton("Create");
        delete = new JButton("Delete");
        update = new JButton("Update");
        refresh = new JButton("Refresh");
        exit = new JButton("Exit");
        search = new JButton("Search");
        
        setLayout(null);
        add(ljudul);
        add(ltipe);
        add(lepisode);
        add(lgenre);
        add(lstatus);
        add(lrating);
        add(txjudul);
        add(txtipe);
        add(txepisode);
        add(txgenre);
        add(txsearch);
        add(status);
        add(txrating);
        add(create);
        add(delete);
        add(update);
        add(refresh);
        add(exit);
        add(search);
        add(scrollPane);
        
        ljudul.setBounds(25, 230, 50, 20);
        ltipe.setBounds(25, 280, 50, 20);
        lepisode.setBounds(25, 330, 50, 20);
        lstatus.setBounds(210, 280, 50, 20);
        lgenre.setBounds(25, 380, 50, 20);
        lrating.setBounds(210, 330, 50, 20);
        txjudul.setBounds(25, 250, 335, 20);
        txtipe.setBounds(25, 300, 150, 20);
        txepisode.setBounds(25, 350, 150, 20);
        status.setBounds(210, 300, 100, 20);
        txgenre.setBounds(25, 400, 335, 20);
        txrating.setBounds(210, 350, 150, 20);
        txsearch.setBounds(520, 250, 200, 20);
        search.setBounds(740, 247, 75, 25);
        refresh.setBounds(420, 395, 80, 25);
        create.setBounds(520, 395, 75, 25);
        update.setBounds(615, 395, 75, 25);
        delete.setBounds(710, 395, 75, 25);
        exit.setBounds(800, 395, 55, 25);
        scrollPane.setBounds(25, 25, 825, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        setSize(900,500); //atur luas jendela
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public String getJudul(){
        return txjudul.getText();
    }
    
    public String getTipe(){
        return txtipe.getText();
    }
    
    public String getEpisode(){
        return txepisode.getText();
    }
    
    public String getGenre(){
        return txgenre.getText();
    }
    
    public String getRating(){
        return txrating.getText();
    }
    
    public String getSearch(){
        return txsearch.getText();
    }
    
    public String getStatus(){
        return status.getSelectedItem().toString();
    }
}
