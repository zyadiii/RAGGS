package frontend.components;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.utilities.Theme;

public class CRUDButtonComponent extends JPanel{
    
    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JButton refreshButton;

    public CRUDButtonComponent() {

        setLayout(new BorderLayout(20, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        addButton = createButton("Add");
        editButton = createButton("Edit");
        deleteButton = createButton("Delete");

        refreshButton = createButton("Refresh");

        addButton.setBackground(Theme.SUCCESS);
        addButton.setForeground(Color.WHITE);

        editButton.setBackground(Theme.WARNING);
        editButton.setForeground(Color.WHITE);

        deleteButton.setBackground(Theme.DANGER);
        deleteButton.setForeground(Color.WHITE);

        refreshButton.setBackground(Theme.PRIMARY);
        refreshButton.setForeground(Color.WHITE);

        leftButtons.add(addButton);
        leftButtons.add(editButton);
        leftButtons.add(deleteButton);

        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        refreshPanel.add(refreshButton);

        add(leftButtons, BorderLayout.WEST);
        add(refreshPanel, BorderLayout.EAST);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(110, 35));
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        return button;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}

