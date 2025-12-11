/***************************************************************************
 * Clase:     Logic5a
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Controlador lógico. Gestiona entradas y orquesta la búsqueda.
 ***************************************************************************/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logic5a {

    private Data inputHandler;
    private SimpleDateFormat dateFormatter;

    public Logic5a() {
        this.inputHandler = new Data();
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    // Método principal público
    public void run() throws Exception {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   CÁLCULO DE X (DISTRIBUCIÓN t-STUDENT)");
        System.out.println("=".repeat(60));
        
        startInputProcess();
    }

    private void startInputProcess() throws Exception {
        System.out.println("\n--- Ingreso de Datos ---\n");

        // 1. Lectura de P
        System.out.print("Valor de p (objetivo, 0 < p < 1): ");
        double targetP = this.inputHandler.getDouble();

        // 2. Lectura de DOF
        System.out.print("Grados de libertad (dof > 0): ");
        int dof = this.inputHandler.getInteger();

        // 3. Lectura del Error
        System.out.print("Error permitido (ej: 0.00001): ");
        double epsilon = this.inputHandler.getDouble();

        System.out.println();

        // Validaciones lógicas básicas
        if (targetP <= 0.0 || targetP >= 1.0) {
            System.out.println("Error: 'p' debe ser mayor a 0 y menor a 1. Valor dado: " + targetP);
            return;
        }

        if (dof <= 0) {
            System.out.println("Aviso: 'dof' ajustado a valor positivo.");
            dof = Math.abs(dof);
        }

        if (epsilon <= 0.0) {
            System.out.println("Aviso: Error inválido, usando valor por defecto 0.00001");
            epsilon = 0.00001;
        }

        // Mostrar resumen previo
        System.out.println("--- Configuración ---");
        System.out.printf("  P objetivo: %.10f%n", targetP);
        System.out.printf("  DOF:        %d%n", dof);
        System.out.printf("  Error max:  %.10f%n", epsilon);

        // Ejecución del algoritmo
        System.out.println("\n>>> Iniciando búsqueda...\n");
        
        SearchAlgorithm searcher = new SearchAlgorithm(targetP, dof, epsilon);
        double foundX = searcher.solve();
        
        // Resultados
        printResults(targetP, dof, foundX, searcher);
        saveToFile(targetP, dof, epsilon, foundX, searcher);
    }

    private void printResults(double p, int dof, double x, SearchAlgorithm searcher) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("   RESULTADOS");
        System.out.println("-".repeat(50));
        System.out.printf("x calculado:      %.10f%n", x);
        System.out.printf("Iteraciones:      %d%n", searcher.getTotalIterations());
        System.out.printf("Error final:      %.10f%n", searcher.getLastError());
        
        System.out.println("\nTabla Resumen:");
        System.out.printf("%-10s %-10s %-15s%n", "p", "dof", "x");
        System.out.println("-".repeat(35));
        System.out.printf("%-10.6f %-10d %-15.10f%n", p, dof, x);
    }

    private void saveToFile(double p, int dof, double error, double x, SearchAlgorithm searcher) {
        String filename = "Out5.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("REPORTE DE EJECUCIÓN - t-STUDENT");
            writer.println("Autor: Emma Hernandez Mendoza");
            writer.println("Fecha: " + this.dateFormatter.format(new Date()));
            writer.println("=".repeat(50));
            
            writer.println("\n[Entrada]");
            writer.printf("P objetivo: %.10f%n", p);
            writer.printf("DOF:        %d%n", dof);
            writer.printf("Tolerancia: %.10f%n", error);
            
            writer.println("\n[Salida]");
            writer.printf("X encontrado:   %.10f%n", x);
            writer.printf("Iteraciones:    %d%n", searcher.getTotalIterations());
            writer.printf("P alcanzado:    %.10f%n", searcher.getLastPCalculated());
            writer.printf("Error real:     %.10f%n", searcher.getLastError());
            
            writer.println("\n" + "=".repeat(50));
            System.out.println("\n✓ Archivo guardado exitosamente: " + filename);

        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }
}
