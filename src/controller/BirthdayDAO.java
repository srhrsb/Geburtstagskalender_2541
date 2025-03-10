package controller;

import model.Birthday;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BirthdayDAO {
    private ArrayList<Birthday> birthdayList;
    private final String PATH = "save.csv";
    private final String SEPARATOR = ",";

    public BirthdayDAO() {
        birthdayList = load();
    }

    private ArrayList<Birthday> load(){
         ArrayList<Birthday> list = new ArrayList<>(); //leer Liste

        FileReader fileReader = null;
        //1. Exception Handling (Fehlerbehandlung)
        try {
            fileReader = new FileReader( PATH );

            BufferedReader bufferedReader = new BufferedReader( fileReader );

            String line;

            //2. Zeilenweise durch Datei laufen
            while( (line = bufferedReader.readLine() ) != null){

                //line ist immer die aktuelle Zeile
                //ToDo: Spliten Sie die Zeile line in ein Array mit den Einzeldaten
                String[] data = line.split(SEPARATOR);

                //3. Für jede Zeile aus den Daten ein Objekt von Birthday erzeugen
                //4. Objekt der BirthdayList hinzufügen
                birthdayList.add( new Birthday(
                                        data[0],
                                        data[1],
                                        data[2],
                                        data[3]
                                   )
                                );
            }
        }
        catch( IOException e){
            System.err.println("Fehler: " + e.getMessage());
        }
        finally{
            if(fileReader!= null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.err.println("Error" + e.getMessage());
                }
            }
        }









        return list;
    }

    public void save(){
        //1. Exception Handling (Fehlerbehandlung)
        FileWriter filewriter = null;

        try{
            filewriter = new FileWriter( PATH );

            //ToDo: Durchlaufen Sie die Arraylist birthdayList und legen sie
            //ToDo: zunächst lokale Variablen mit den jeweils zu speichernden Daten an

            //2. Objekte aus der Liste holen und mit Schleife durchlaufen
            for( var bday : birthdayList){

                //3. Zeilenweise jeden Listeneintrag via Getter in die Datei schreiben
                if(bday != null) {
                    String id = bday.getId();
                    String lastname = bday.getLastname();
                    String firstname = bday.getFirstname();
                    String date = bday.getDate();

                    String line = id +SEPARATOR+lastname+SEPARATOR+firstname+SEPARATOR+date+System.lineSeparator();
                    filewriter.write(line);
                }
            }
        }
        catch( IOException e){
            System.err.println("Fehler: " + e.getMessage());
        }
        finally{
            if(filewriter!= null){
                try {
                    filewriter.close();
                } catch (IOException e) {
                    System.err.println("Error" + e.getMessage());
                }
            }
        }

        //ToDo: Sorgen Sie dafür, dass neu hinzugefügte Geburtstage sofort gespeichert werden
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
