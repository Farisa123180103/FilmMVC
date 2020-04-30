
package MVC;

import java.awt.event.*;
import javax.swing.*;

public class FilmController {
    FilmModel filmmodel;//utk import class Model
    FilmView filmview; //utk import class View
    FilmDAO filmdao; //utk import class DAO
    static String id, judul, tipe, episode, genre, status, rating;
    
    public FilmController(FilmModel filmmodel, FilmView filmview, FilmDAO filmdao){
        this.filmmodel = filmmodel;
        this.filmview = filmview;
        this.filmdao = filmdao;
        
        if(filmdao.getJmldata() != 0){ //utk mengecek apakah DB ada data atau tidak
            String dataFilm[][] = filmdao.readFilm();
            filmview.tabel.setModel((new JTable(dataFilm, filmview.namaKolom)).getModel());
        } else { 
            // kalau tdk ada mka akan muncul pop up
            JOptionPane.showMessageDialog(null, "Data tidak ada!");
        }
        
        filmview.create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String judul = filmview.getJudul();
                String tipe = filmview.getTipe();
                String episode = filmview.getEpisode();
                String genre = filmview.getGenre();
                String status = filmview.getStatus();
                String rating = filmview.getRating();
                
                if(judul.isEmpty() || tipe.isEmpty() || episode.isEmpty() || genre.isEmpty() || status.isEmpty() || rating.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Harap isi semua field");
                } else {
                    //memasukkan data kedlm Model
                    filmmodel.setFilmModel(judul, tipe, episode, genre, status, rating);
                    //menjalankan perintah insert di DAO
                    filmdao.Insert(filmmodel);
                    
                }
            }
        });
        
        filmview.tabel.addMouseListener(new MouseAdapter(){
           @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int baris = filmview.tabel.getSelectedRow();
                
                id          = filmview.tabel.getValueAt(baris,1).toString();
                judul       = filmview.tabel.getValueAt(baris,2).toString();
                tipe        = filmview.tabel.getValueAt(baris,3).toString();
                episode     = filmview.tabel.getValueAt(baris,4).toString();
                genre       = filmview.tabel.getValueAt(baris,5).toString();
                status      = filmview.tabel.getValueAt(baris,6).toString();
                rating      = filmview.tabel.getValueAt(baris,7).toString();
                
                filmview.txjudul.setText(judul);
                filmview.txtipe.setText(tipe);
                filmview.txepisode.setText(episode);
                filmview.txgenre.setText(genre);
                filmview.txrating.setText(rating);
                filmview.status.setSelectedItem(status);

            }
        });
        
        filmview.delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                int input = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus Data dengan Judul " + judul + "?","Delete Contact",JOptionPane.YES_NO_OPTION);

                if (input == 0){
                    filmmodel.setId(id);
                    filmdao.Delete(filmmodel);
                }
            }
        });
        
        filmview.update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String judul = filmview.getJudul();
                String tipe = filmview.getTipe();
                String episode = filmview.getEpisode();
                String genre = filmview.getGenre();
                String status = filmview.getStatus();
                String rating = filmview.getRating();
                
                if(judul.isEmpty() || tipe.isEmpty() || episode.isEmpty() || genre.isEmpty() || status.isEmpty() || rating.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Harap isi semua field");
                } else {
                    //memasukkan data kedlm Model
                    filmmodel.setId(id);
                    //memasukkan data kedlm Model
                    filmmodel.setFilmModel(judul, tipe, episode, genre, status, rating);
                    //menjalankan perintah insert di DAO
                    filmdao.Update(filmmodel);
                    
                }
            }
        });
        
        filmview.refresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String dataFilm[][] = filmdao.readFilm();
                filmview.tabel.setModel((new JTable (dataFilm, filmview.namaKolom)).getModel());
                
                filmview.txjudul.setText(null);
                filmview.txtipe.setText(null);
                filmview.txepisode.setText(null);
                filmview.txgenre.setText(null);
                filmview.txrating.setText(null);
            }
        });
        
        filmview.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                filmview.setVisible(false);
            }
        });
        
        filmview.search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String search = filmview.getSearch();
                
                if(search.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Harap isi field search");
                } else {
                    //memasukkan data kedlm Model
                    filmmodel.setSearch(search);
                    //menampilkan search
                    String dataFilm[][] = filmdao.readSearch(filmmodel);
                    filmview.tabel.setModel((new JTable (dataFilm, filmview.namaKolom)).getModel());
                    
                }
            }
        });
    }
}

