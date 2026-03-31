package com.dev.util;

import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for common UI operations
 * Used across all controllers to avoid code duplication
 */
public class UIUtils {
    
    /**
     * Show error alert dialog
     * @param message Error message to display
     */
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
    /**
     * Show success alert dialog
     * @param message Success message to display
     */
    public static void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success - HeloCare");
        alert.setHeaderText(message);
        alert.show();
    }
    
    /**
     * Show warning alert dialog
     * @param message Warning message to display
     */
    public static void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
    /**
     * Format LocalDate to readable string (dd/MM/yyyy)
     * @param date Date to format
     * @return Formatted date string
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return "";
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
