
package Maini;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import Maini.log;
import admin.admindashboard;
import admin.admpfp;
import admin.loginadm;
import kamarjer.kamarjerprofil;
import kamarjer.kamarjerdashboard;
import kamarjer.loginkamarjer;
import rezervime.rezervime;
import rezervime.rezervimeloged;

public class maini {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Brew Street");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        
        CardLayout cards = new CardLayout();
        JPanel container = new JPanel(cards);
        container.setBounds(0, 0, 1000, 800); 
        
//        CardLayout cardsadm = new CardLayout();
//        JPanel containeradm = new JPanel(cardsadm);
//        containeradm.setBounds(0, 0, 200, 800); 
        
        log log1 = new log(container, cards);
        JPanel log = log1.getPanel();
        
        loginadm log2 = new loginadm(container, cards);
        JPanel loginadm = log2.getPanel();
        
        loginkamarjer log3 = new loginkamarjer(container, cards);
        JPanel loginkamarjer = log3.getPanel();
        
        rezervime rezer = new rezervime(container, cards);
        JPanel rezervime = rezer.getPanel();

        admindashboard ad = new admindashboard(container, cards);
        JPanel admindashboard = ad.getPanel();
        
        rezervimeloged rz = new rezervimeloged(container, cards);
        JPanel rezervimeloged = rz.getPanel();
        
        kamarjerdashboard km = new kamarjerdashboard(container, cards);
        JPanel kamarjerdashboard = km.getPanel();
        
        kamarjerprofil km1 = new kamarjerprofil(container, cards);
        JPanel kamarjerprofil = km1.getPanel();
        
        admpfp admpfp1 = new admpfp(container, cards);
        JPanel admpfp = admpfp1.getPanel();
        
        frame.add(container);
        container.add(rezervime, "rezervime");
        container.add(admpfp, "admpfp");
        container.add(kamarjerprofil, "kamarjerprofil");
        container.add(kamarjerdashboard, "kamarjerdashboard");
        container.add(rezervimeloged, "rezervimeloged");
        container.add(log, "log");
        container.add(admindashboard, "admindashboard");
        container.add(loginkamarjer, "loginkamarjer");
        container.add(loginadm, "loginadm");
        cards.show(container,"log");
    }
}
