package frontend.components;

import frontend.utilities.ThemeUtil;

import javax.swing.*;
import java.awt.*;

public class SearchBarComponent extends JPanel {

    private final JTextField searchField;
    private final JButton searchButton;

    public SearchBarComponent() {
        setLayout(new BorderLayout(10, 10));
        setOpaque(false);

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 32));
        ThemeUtil.styleTextField(searchField);

        searchButton = new JButton("Search");
        ThemeUtil.styleButton(searchButton);

        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}