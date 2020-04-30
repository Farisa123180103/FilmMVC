/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

/**
 *
 * @author HP
 */
public class FilmMVC {
    FilmView filmview = new FilmView();
    FilmModel filmmodel = new FilmModel();
    FilmDAO filmDAO = new FilmDAO();
    FilmController filmcontroller = new FilmController(filmmodel, filmview, filmDAO);
}
