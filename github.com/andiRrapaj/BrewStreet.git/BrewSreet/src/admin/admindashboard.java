package admin;

import java.awt.CardLayout;
import java.awt.*;
import java.awt.Color;
import java.awt.color.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//
public class admindashboard {
    private JPanel container;
    private CardLayout cards;
    private JPanel layeredPane; 
    private CardLayout layeredCards;
    private Color originalColor = UIManager.getColor("Button.background");
    private JButton selectedButton = null;

    public admindashboard(JPanel container, CardLayout cards) {
        this.container = container;
        this.cards = cards;
    }

    /**
     * @wbp.parser.entryPoint
     */
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(200, 50, 999, 750);
        panel.setBackground(new Color(130, 159, 96));

        layeredPane = new JPanel();
        layeredPane.setBounds(148, 50, 851, 719);
        layeredCards = new CardLayout();
        layeredPane.setLayout(layeredCards);
        panel.add(layeredPane);

        adminbills bil = new adminbills();
        JPanel adminbills = bil.getPanel();

        adminusers us = new adminusers(container, cards);
        JPanel adminusers = us.getPanel();

        inventory in = new inventory(container, cards);
        JPanel inventory = in.getPanel();

        tabels tb = new tabels();
        JPanel tabels = tb.getPanel();

        products pr = new products(container, cards);
        JPanel products = pr.getPanel();

        layeredPane.add(adminusers, "adminusers");
        layeredPane.add(inventory, "inventory");
        layeredPane.add(tabels, "tabels");
        layeredPane.add(products, "products");
        layeredPane.add(adminbills, "adminbills");

        JButton btn1 = new JButton("Bills & Orders");
        btn1.setBounds(20, 203, 128, 30);
        btn1.addActionListener(e -> changePanel(btn1, "adminbills"));
        panel.add(btn1);

        JButton btn2 = new JButton("Manage Users");
        btn2.setBounds(20, 270, 128, 30);
        btn2.addActionListener(e -> changePanel(btn2, "adminusers"));
        panel.add(btn2);

        JButton btn3 = new JButton("Inventory");
        btn3.setBounds(20, 339, 128, 30);
        btn3.addActionListener(e -> changePanel(btn3, "inventory"));
        panel.add(btn3);

        JButton btn4 = new JButton("Manage Tables");
        btn4.setBounds(20, 402, 128, 30);
        btn4.addActionListener(e -> changePanel(btn4, "tabels"));
        panel.add(btn4);

        JButton btn5 = new JButton("Manage Products");
        btn5.setBounds(20, 461, 128, 30);
        btn5.addActionListener(e -> changePanel(btn5, "products"));
        panel.add(btn5);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(119, 135, 136));
        panel_1.setBounds(0, 0, 999, 50);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("Profile");
        btnNewButton.setBounds(857, 10, 85, 21);
        btnNewButton.addActionListener(e -> cards.show(container, "admpfp"));
        panel_1.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Log Out");
        btnNewButton_1.setBounds(736, 10, 85, 21);
        btnNewButton_1.addActionListener(e -> cards.show(container, "loginadm"));
        panel_1.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("Brew-Street Management Side");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 14, 213, 26);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Dashboard");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 70, 128, 38);
        panel.add(lblNewLabel_1);

        return panel;
    }

    private void changePanel(JButton button, String panelName) {
        if (selectedButton != null) {
            selectedButton.setBackground(originalColor);
        }
        button.setBackground(Color.LIGHT_GRAY);
        selectedButton = button;
        layeredCards.show(layeredPane, panelName);
    }
}
