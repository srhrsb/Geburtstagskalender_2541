package controller;

import model.Birthday;
import view.MainView;

import java.util.ArrayList;


public class Controller {

    //    private Birthday[] birthdays = new Birthday[10];
    //    Array wäre ungeeignet,da nicht dynamisch sondern eine feste Größe hat

    //Birthday[] birthday = new Birthday[10];
    ArrayList<Birthday> birthdayList;
    MainView view;



    /**
     * Konstruktor - führt initial Anweisungen aus
     */
    public Controller(){

        view = new MainView( 500,300 );

        birthdayList = new ArrayList<>();
        //Testen: Objekt von Birthday wird erzeugt als Test
        birthdayList.add( new Birthday("id123", "Böttcher", "Sebastian","27.11.1976") );
        System.out.println( birthdayList.getFirst().getId() );

        //Weiteres Objekt Birthday
        birthdayList.add( new Birthday("id234", "Böttcher", "Jeanette","8.02.1970") );
        System.out.println( birthdayList.get(1).getId() );

        //Id Funktion testen:
        //1. Id erzeugen mit dem Namen "Johannes Schölch"
        String id = createID("Schölch","Johannes");//erzeugen der ID

        //2. Birthday Objekt mit den Daten von Johannes erzeugen und in der
        //Liste speichern
        birthdayList.add( new Birthday(id, "Schölch", "Johannes", "25.3.1988"));

        //Das Objekt Birthday mit den Daten von Johannes holen
        //mit Hilfe der Methode "getBirthdayById"
        Birthday johannes = getBirthdayByID(id);

        //zur Kontrolle Nachnamen ausgeben
        System.out.println(johannes.getLastname());
    }

    /**
     * Fügt einen Geburtstag zur birthdayList hinzu und prüft
     * zunächst ob es ein BirthdayObject mit genau dieser erzeugten Id schon existiert
     * @return
     */
    public boolean addBirthday( String firstName, String lastName, String date){
        //ToDo: Die Id besorgen
        String id = createID(firstName, lastName);

        //ToDo: Prüfen ob die Id schon existiert, wenn ja Methode verlassen
        if(getBirthdayByID(id) == null){ //ist kein gültiges Objekt zurückgekommen?
            Birthday bday = new Birthday( id, lastName, firstName, date); //Birthday Objekt erzeugen
            return birthdayList.add(bday); //Objekt in Liste speichern und Erfolgsmeldung zurückgeben
        }
        return false;
    }

    /**
     * Die Liste mit den gespeicherten Geburtstagen (Klasse Birthday)
     * wird per Schleife durchlaufen, falls ein Objekt Birthday mit der
     * gesuchten ID vorhanden ist, wird dieses zurückgegeben
     * @param id
     * @return
     */
    private Birthday getBirthdayByID( String id ){

        for( Birthday bday : birthdayList){
            //ist das das Objekt mit der gesuchten ID?
            if(id.equals( bday.getId() ) ){ //Id stimmt überein
                return bday;
            }
        }
        return null;
    }

    /**
     * Löscht den Geburtstag mit der angegebenen ID
     * @param id
     * @return Erfolgsmeldung
     */
    private boolean deleteBirthdayByID( String id ){

        for( Birthday bday : birthdayList){
            //ist das das Objekt mit der gesuchten ID?
            if(id.equals( bday.getId() ) ){ //Id stimmt überein -> entfernen

                //Objekt aus Liste löschen und Erfolg als boolean zurückgeben
                boolean success =  birthdayList.remove( bday );
                return success;
            }
        }
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
