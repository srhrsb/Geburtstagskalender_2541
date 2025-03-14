package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton addBtn, deleteBtn, searchBtn, showAllBtn;

    private JTextField idTf, firstNameTf, lastNameTf, dateTf;

    //Konstruktor der die Breite und Höhe des Fensters annimmt
    public MainView( int width, int height ){
           setSize(width, height);
           setFont( new Font("Arial", Font.PLAIN, 24) );
           setTitle("Geburtstagskalender");
           setDefaultCloseOperation( EXIT_ON_CLOSE ); //stellt das Verhalten beim Schließen ein
           addUIComponents();
           setVisible(true);
    }

    /**
     * Bedienelemente hinzufügen
     */
    private void addUIComponents(){

        //Man sollte die Bedienelemente am besten zuerst
        //in Panels einfügen und dann erst dem Fenster die Panels zuordnen
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //Button erzeugen
        addBtn = new JButton("Hinzufügen");
        deleteBtn = new JButton("Löschen");
        searchBtn = new JButton("Suchen");
        showAllBtn = new JButton("Alle Anzeigen");

        //hinzufügen der Button zum BottomPanel
        bottomPanel.add( addBtn );
        bottomPanel.add( deleteBtn );
        bottomPanel.add( searchBtn );
        bottomPanel.add( showAllBtn );

        //Gridlayout für Toppanel einstellen
        topPanel.setLayout( new GridLayout(4, 2, 5, 10) );
        topPanel.setBorder( new EmptyBorder(5,5,5,5));

        //Textfelder und Label
        JLabel idLabel = new JLabel("ID");
        idTf = new JTextField();

        JLabel firstNameLabel = new JLabel("Vorname");
        firstNameTf = new JTextField();

        JLabel lastNameLabel = new JLabel("Nachname");
        lastNameTf = new JTextField();

        JLabel dateLabel = new JLabel("Datum");
        dateTf = new JTextField();

        topPanel.add(idLabel);
        topPanel.add(idTf);

        topPanel.add(firstNameLabel);
        topPanel.add(firstNameTf);

        topPanel.add(lastNameLabel);
        topPanel.add(lastNameTf);

        topPanel.add(dateLabel);
        topPanel.add(dateTf);

         //alle Panel dem Fenster hinzufügen
         add(topPanel, BorderLayout.NORTH);
         add(bottomPanel, BorderLayout.SOUTH);

    }

    public void addOnAddBirthdayAction( ActionListener listener ){
        addBtn.addActionListener( listener);
    }

    public void addOnDeleteBirthdayAction( ActionListener listener ){
        deleteBtn.addActionListener( listener);
    }

    public void addOnSearchBirthdayAction( ActionListener listener ){
        searchBtn.addActionListener( listener);
    }

    public void addOnShowAllAction( ActionListener listener ){
        showAllBtn.addActionListener( listener);
    }

    public String getFirstName(){
        return firstNameTf.getText();
    }

    public String getLastName(){
        return lastNameTf.getText();
    }

    public String getDate(){
        return dateTf.getText();
    }

    public String getID(){
        return idTf.getText();
    }

    public void setID( String id){
        idTf.setText( id );
    }

    public void showInfoMessage( String message ){
        JOptionPane.showMessageDialog( this, message, "Info", JOptionPane.INFORMATION_MESSAGE );
    }

    public void showErrorMessage( String message ){
        JOptionPane.showMessageDialog( this, message, "Fehler", JOptionPane.ERROR_MESSAGE );
    }

}
