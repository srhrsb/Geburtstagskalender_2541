package controller;

import model.Birthday;

import java.util.ArrayList;


public class Controller {

    //    private Birthday[] birthdays = new Birthday[10];
    //    Array wäre ungeeignet,da nicht dynamisch sondern eine feste Größe hat

    //Birthday[] birthday = new Birthday[10];
    ArrayList<Birthday> birthdayList;

    public Controller(){

        birthdayList = new ArrayList<>();
        //Testen
        birthdayList.add( new Birthday("id123", "Böttcher", "Sebastian","27.11.1976") );
        System.out.println( birthdayList.getFirst().getId() );

        birthdayList.add( new Birthday("id234", "Böttcher", "Jeanette","8.02.1970") );
        System.out.println( birthdayList.get(1).getId() );

        //Id Funktion testen
        String id = createID("Schölch","Johannes");//erzeugen der ID

        birthdayList.add( new Birthday(id, "Schölch", "Johannes", "25.3.1988"));

        Birthday johannes = getBirthdayByID(id);

        System.out.println(johannes.getLastname());
    }

    private Birthday getBirthdayByID( String id ){

        for( Birthday bday : birthdayList){
            //ist das das Objekt mit der gesuchten ID?
            if(id.equals( bday.getId())){ //Id stimmt überein
                return bday;
            }
        }

        return null;
    }

    //Freiwillige Hausaufgabe
    private boolean deleteBirthdayByID( String id ){
        //Tipp: Listen haben eine remove() Methode

        return false;
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
