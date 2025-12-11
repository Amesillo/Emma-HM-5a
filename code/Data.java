/***************************************************************************
 * Clase:     Data
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Manejador de entradas de usuario (Scanner).
 ***************************************************************************/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Data {
    
    private Scanner scanner;
    
    public Data() {
        this.scanner = new Scanner(System.in);
    }
    
    // Lectura segura de enteros
    public int getInteger() {
        while (true) {
            try {
                int input = this.scanner.nextInt();
                this.scanner.nextLine(); // Limpiar buffer
                return input;
            } catch (InputMismatchException e) {
                System.out.print(">> Entrada inválida. Ingrese un número entero: ");
                this.scanner.nextLine(); // Consumir entrada errónea
            }
        }
    }
    
    // Lectura segura de doubles
    public double getDouble() {
        while (true) {
            try {
                double input = this.scanner.nextDouble();
                this.scanner.nextLine(); // Limpiar buffer
                return input;
            } catch (InputMismatchException e) {
                System.out.print(">> Entrada inválida. Ingrese un número decimal (ej. 1.5): ");
                this.scanner.nextLine();
            }
        }
    }
    
    // Utilidad para cerrar flujo
    public void closeConnection() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }
}
