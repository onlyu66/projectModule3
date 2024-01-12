package com.ra.function;

public class Function {
    public static void displayTable(String[] headers, String[][] data){
        // Calculate column widths
        int[] columnWidths = calculateColumnWidths(headers, data);

        // Display headers
        displayRow(headers, columnWidths);

        // Display separator
        displaySeparator(columnWidths);

        // Display data rows
        for (String[] row : data) {
            displayRow(row, columnWidths);
        }
    }

    private static int[] calculateColumnWidths(String[] headers, String[][] data){
        int[] columnWidths = new int[headers.length];

        // Initialize with header lengths
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        // Update with data lengths
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }

        return columnWidths;
    }

    private static void displayRow(String[] row, int[] columnWidths){
        for (int i = 0; i < row.length; i++) {
            System.out.printf("%-" + (columnWidths[i] + 3) + "s", row[i]);
        }
        System.out.println();
    }

    private static void displaySeparator(int[] columnWidths){
        for (int width : columnWidths) {
            System.out.print("+");
            for (int i = 0; i < width+1; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }
}
