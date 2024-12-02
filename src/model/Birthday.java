package model;

public class Birthday {

    private String id;
    private String lastname;
    private String firstname;
    private String date;

    //ToDo:Klasse vervollständigen
    //Felder, Constructor, Getter und Setter anlegen

    public Birthday(String id, String lastname, String firstname, String date) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
