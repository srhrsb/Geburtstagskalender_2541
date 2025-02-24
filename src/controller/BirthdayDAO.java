package controller;

import model.Birthday;

import java.util.ArrayList;

public class BirthdayDAO {
    private ArrayList<Birthday> birthdayList;

    public BirthdayDAO() {
        birthdayList = load();
    }

    private ArrayList<Birthday> load(){
         ArrayList<Birthday> list = new ArrayList<>();

        //1. Zeilweise durch Datei laufen

        //2. Für jede Zeile aus den Daten ein Objekt von Birthday erzeugen

        //3. Objekt der BirthdayList hinzufügen

        return list;
    }

    private void save(){

        //1. Objekte aus der Liste holen und mit Schleife durchlaufen

        //2. Zeilweise jeden Listeneintrag via Getter in die Datei schreiben

    }




    /**
     * Fügt einen Geburtstag zur birthdayList hinzu und prüft
     * zunächst ob es ein BirthdayObject mit genau dieser erzeugten Id schon existiert
     * @return
     */
    public boolean addBirthday( String firstName, String lastName, String date){
        //ToDo: Die Id besorgen
        String id = createID(firstName, lastName);
        //view.setID(id);

        //ToDo: Prüfen ob die Id schon existiert, wenn ja Methode verlassen
        if(getBirthdayByID(id) == null){ //ist kein gültiges Objekt zurückgekommen?
            Birthday bday = new Birthday( id, lastName, firstName, date); //Birthday Objekt erzeugen
            return birthdayList.add(bday); //Objekt in Liste speichern und Erfolgsmeldung zurückgeben
        }
        return false;
    }

    public ArrayList<Birthday> getBirthdayList() {
        return birthdayList;
    }

    /**
     * Die Liste mit den gespeicherten Geburtstagen (Klasse Birthday)
     * wird per Schleife durchlaufen, falls ein Objekt Birthday mit der
     * gesuchten ID vorhanden ist, wird dieses zurückgegeben
     * @param id
     * @return
     */
    public Birthday getBirthdayByID( String id ){

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
    public boolean deleteBirthdayByID( String id ){

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
