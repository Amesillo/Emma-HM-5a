/***************************************************************************
 * Clase:     Output
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Utilidades de formateo y reporte.
 ***************************************************************************/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {
    
    public Output() {
        // Constructor vacío
    }
    
    public void logToConsole(String msg) {
        System.out.println("[LOG] " + msg);
    }
    
    // Método auxiliar para guardar resultados simples (Append mode)
    public void appendResult(String filename, double x, int dof, double p) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.printf("%.5f %d %.8f%n", x, dof, p);
        } catch (IOException e) {
            System.err.println("No se pudo escribir en " + filename);
        }
    }
}
