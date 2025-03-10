package controller;

import model.Birthday;
import view.MainView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Controller {

    //    private Birthday[] birthdays = new Birthday[10];
    //    Array wäre ungeeignet,da nicht dynamisch sondern eine feste Größe hat

    //Birthday[] birthday = new Birthday[10];

    private MainView view;
    private BirthdayDAO dao;

    //Kostanten----------------------
    private final int MIN_TEXTFIELD_LENGTH = 1;


    /**
     * Konstruktor - führt initial Anweisungen aus
     */
    public Controller(){

        view = new MainView( 400,210 );
        dao = new BirthdayDAO();

        //Eventlistener bei den Button im View anmelden
        view.addOnAddBirthdayAction( this::onAddAction );
        view.addOnDeleteBirthdayAction( this::onDeleteAction );
        view.addOnSearchBirthdayAction( this::onSearchAction );
        view.addOnShowAllAction( this::onShowAllAction );
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
            boolean success = dao.addBirthday( firstname, lastname, date );

            //Nutzer informieren, dass es geklappt hat einen Geburtstag hinzuzufügen
            if(success){
                dao.save();
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

        //Birthdayobjekt mit der Id aus dem Textfeld holen
        String id = view.getID();

        //Objekt aus Liste entfernen
        var success = dao.deleteBirthdayByID(id);

        if(success){
            dao.save();
            view.showInfoMessage("Der Geburtstag wurde gelöscht");
        }
        else{
            view.showErrorMessage("Der Geburtstag konnte nicht gelöscht werden");
        }
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

        for( Birthday bday : dao.getBirthdayList()){
            text += bday.getFirstname() + " ";
            text += bday.getLastname() + " ";
            text += bday.getDate() + "\n\n";
        }

        view.showInfoMessage(text);
    }

    //---Add Methoden

}
