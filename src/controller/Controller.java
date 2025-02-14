package controller;

import model.Birthday;
import view.MainView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Controller {

    //    private Birthday[] birthdays = new Birthday[10];
    //    Array wäre ungeeignet,da nicht dynamisch sondern eine feste Größe hat

    //Birthday[] birthday = new Birthday[10];
    ArrayList<Birthday> birthdayList;
    MainView view;

    //Kostanten----------------------
    private final int MIN_TEXTFIELD_LENGTH = 1;


    /**
     * Konstruktor - führt initial Anweisungen aus
     */
    public Controller(){

        view = new MainView( 400,210 );

        //Eventlistener bei den Button im View anmelden
        view.addOnAddBirthdayAction( this::onAddAction );
        view.addOnDeleteBirthdayAction( this::onDeleteAction );
        view.addOnSearchBirthdayAction( this::onSearchAction );
        view.addOnShowAllAction( this::onShowAllAction );

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

    //Actions------------------------------------------------------------

    /**
     * Aufgerufen via Event des "AddButton"
     * @param event Event-Objekt
     */
    private void onAddAction( ActionEvent event ){
        System.out.println( event.getActionCommand() );

        //Daten aus Textfelder holen
        String firstname = view.getFirstName().trim(); //trim entfernt die Leerzeichen bzw. Umbrüche
        String lastname = view.getLastName().trim();
        String date = view.getDate();

        //Prüfen ob Daten zulässig
        if( isValidText(firstname) && isValidText(lastname) && isValidText(date) ){

            //Daten an Methode AddBirthday übergeben
            boolean success = addBirthday( firstname, lastname, date );

            //Nutzer informieren, dass es geklappt hat einen Geburtstag hinzuzufügen
            if(success){
                view.showInfoMessage("Geburtstag von "+firstname+" "+lastname+" wurde hinzugefügt.");
            }
            else{
                //nicht geklappt
                view.showErrorMessage("Es ist ein Fehler aufgetreten.");
            }
        }
        else{
            //Eingabe falsch
            view.showErrorMessage("Bitte überprüfen Sie die Eingaben in den Textfeldern");
        }

    }

    private boolean isValidText( String text ){
        return text.length() > MIN_TEXTFIELD_LENGTH;
    }

    /**
     * Aufgerufen via Event des "DeleteButton"
     * @param event Event-Objekt
     */
    private void onDeleteAction( ActionEvent event ){
        System.out.println( event.getActionCommand() );


    }

    /**
     * Aufgerufen via Event des "SearchButton"
     * @param event Event-Objekt
     */
    private void onSearchAction( ActionEvent event ){
        System.out.println( event.getActionCommand() );


    }

    /**
     * Aufgerufen via Event des "Zeige Alles"
     * @param event Event-Objekt
     */
    private void onShowAllAction( ActionEvent event ){
        System.out.println( event.getActionCommand() );

        String text = "Alle Geburtstage:\n\n";

        for( Birthday bday : birthdayList){
            text += bday.getFirstname() + " ";
            text += bday.getLastname() + " ";
            text += bday.getDate() + "\n\n";
        }

        view.showInfoMessage(text);
    }

    //---Add Methoden
    /**
     * Fügt einen Geburtstag zur birthdayList hinzu und prüft
     * zunächst ob es ein BirthdayObject mit genau dieser erzeugten Id schon existiert
     * @return
     */
    public boolean addBirthday( String firstName, String lastName, String date){
        //ToDo: Die Id besorgen
        String id = createID(firstName, lastName);
        view.setID(id);

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
