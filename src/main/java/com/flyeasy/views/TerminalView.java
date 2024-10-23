package com.flyeasy.views;

public class TerminalView {

    private static final String BORDER_HORIZONTAL = "-";
    private static final int WIDTH = 80; 

    private static void drawHorizontalBorder() {
        System.out.println(BORDER_HORIZONTAL.repeat(WIDTH + 2)); 
    }

    public static void displayMessage(String message) {
        String[] lines = message.split("\n");
        drawHorizontalBorder();
        for (String line : lines) {
            System.out.println("  " + line + "  "); 
        }
        drawHorizontalBorder();
        System.out.println();
    }

    public static void displayMenu(String menuContent) {
        System.out.println();
        displayMessage(menuContent);
        System.out.println(); 
    }

    public static void displayHeader(String headerContent) {
        System.out.println("  " + headerContent + "  "); 
        drawHorizontalBorder();
        System.out.println(); 
    }

    public static void displaySeparation() {
        System.out.println();
        drawHorizontalBorder();
        System.out.println();
    }
}
