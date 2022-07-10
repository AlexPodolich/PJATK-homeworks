package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;

public class ItemCellRenderer extends JPanel implements ListCellRenderer<Upgrade>{

    JLabel name = new JLabel(" ");
    JLabel desription = new JLabel(" ");
    JLabel price = new JLabel(" ");
    JLabel status = new JLabel(" ");

    public ItemCellRenderer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBorder(BorderFactory.createLineBorder(Color.black,2));
        this.setBackground(new Color(136, 174, 210));

        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        desription.setAlignmentX(Component.CENTER_ALIGNMENT);

        price.setAlignmentX(Component.CENTER_ALIGNMENT);

        status.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon coin = new ImageIcon("resources/coin.png");
        Image image = coin.getImage(); // transform it
        Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        coin = new ImageIcon(newimg);  // transform it back

        price.setIcon(coin);

        add(name);
        add(Box.createVerticalStrut(15));
        add(desription);
        add(Box.createVerticalStrut(10));
        add(price);
        add(Box.createVerticalStrut(10));
        add(status);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(250, 150);
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }



    @Override
    public Component getListCellRendererComponent(JList<? extends Upgrade> list, Upgrade value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());

        name.setFont(new Font(null, Font.BOLD, 20));

        desription.setFont(new Font(null, Font.PLAIN, 16));

        price.setFont(new Font(null, Font.ITALIC, 16));

        status.setFont(new Font(null, Font.ITALIC, 20));
        if(status.getText().equals("status: Disabled")){
            status.setForeground(Color.RED);
        }else {
            status.setForeground(Color.GREEN);
        }


        name.setText(value.getName());
        desription.setText(value.getDescription());
        price.setText(String.valueOf(value.getPrice()));
        if(value.isStatus()){
            status.setText("status: Activated");
            status.setForeground(Color.GREEN);
        }else {
            status.setText("status: Disabled");
            status.setForeground(Color.RED);
        }

        name.setForeground(getForeground());
        desription.setForeground(getForeground());
        price.setForeground(getForeground());

        setEnabled(list.isEnabled());
        setFont(list.getFont());
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
