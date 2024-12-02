package controller;

import model.Birthday;

public class Controller {
    private Birthday birthday;
    public Controller(){
        //Testen
        birthday = new Birthday("id123", "Böttcher", "Sebastian","27.11.1976");
        System.out.println( birthday.getId() );

        //Id Funktion testen
        String id = createID("Böttcher","Sebastian");
        System.out.println(id);
    }

    /**
     * Erzeugt aus den ersten 2 Buchstaben des Nachnamens und den ersten
     * 2 des Vornamens und einer 6-stelligen Zufallszahl eine ID
     * @param lastname Nachname
     * @param firstname Vorname
     * @return Id
     */
    private String createID( String lastname, String firstname ){

       String sub1 =  lastname.substring(0,2);
       String sub2 =  firstname.substring(0,2);

       int randomNumber = (int)(Math.random() * 999999);
       return sub1 + sub2 + randomNumber;
    }
}
