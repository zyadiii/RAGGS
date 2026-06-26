package frontend.utilities;

import java.awt.Color;
import java.awt.Font;

public class Theme {

    private Theme() {}

    // ----- MAIN COLORS -----

    public static final Color PRIMARY =
            Color.decode("#2980B9");

    public static final Color SUCCESS =
            Color.decode("#2ECC71");

    public static final Color WARNING =
            Color.decode("#F39C12");

    public static final Color DANGER =
            Color.decode("#FA311B");

    public static final Color BACKGROUND =
            Color.decode("#E9E9E9");

    public static final Color PANEL_BACKGROUND =
            Color.decode("#C7C7C7");

    public static final Color CARD_BACKGROUND =
            Color.WHITE;

    public static final Color TEXT =
            Color.decode("#2C3E50");

    public static final Color BORDER =
            Color.decode("#000000");

    public static final Color SIDEBAR =
            Color.decode("#354C64");

    public static final Color SIDEBAR_TEXT =
            Color.decode("#E9E9E9");

    // ----- FONTS -----

    public static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 24);

    public static final Font HEADER_FONT =
            new Font("Segoe UI", Font.BOLD, 16);

    public static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    public static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 14);

    public static final Font CARD_TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 18);

    public static final Font CARD_VALUE_FONT =
            new Font("Segoe UI", Font.BOLD, 34);
}