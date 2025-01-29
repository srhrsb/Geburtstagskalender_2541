package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {

    private JButton addBtn, deleteBtn, searchBtn;

    private JTextField firstNameTf, lastNameTf, dateTf;

    //Konstruktor der die Breite und Höhe des Fensters annimmt
    public MainView( int width, int height ){

           setSize(width, height);

           setFont( new Font("Arial", Font.PLAIN, 24) );

           setTitle("Geburtstagskalender");
           setDefaultCloseOperation( EXIT_ON_CLOSE );
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

         //hinzufügen des Buttons zum BottomPanel
         bottomPanel.add( addBtn);

         //Gridlayout für Toppanel einstellen
        topPanel.setLayout( new GridLayout(3, 2, 5, 10) );
        topPanel.setBorder( new EmptyBorder(5,5,5,5));


         //Textfelder und Label
         //ToDo: 3 Label + Textfelder erzeugen für unsere Daten Nachname, Vorname, Datum

        JLabel firstNameLabel = new JLabel("Vorname");
        firstNameTf = new JTextField();

        JLabel lastNameLabel = new JLabel("Nachname");
        lastNameTf = new JTextField();

        JLabel dateLabel = new JLabel("Datum");
        dateTf = new JTextField();

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





}
