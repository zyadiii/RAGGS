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
            Color.decode("#fa311b");

    public static final Color BACKGROUND =
            Color.decode("#e9e9e9");

    public static final Color PANEL_BACKGROUND =
            Color.decode("#c7c7c7");

    public static final Color TEXT =
            Color.decode("#2C3E50");

    public static final Color BORDER =
            Color.decode("#000000");
    
    public static final Color SIDEBAR =
        Color.decode("#354c64");

    public static final Color SIDEBAR_TEXT = 
        Color.decode("#e9e9e9");

    // ----- FONTS -----

    public static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 24);

    public static final Font HEADER_FONT =
            new Font("Segoe UI", Font.BOLD, 16);

    public static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    public static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 14);

    
}
