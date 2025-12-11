/***************************************************************************
 * Programa:  Cálculo de X para Distribución t-Student (Prog 6)
 * Clase:     App
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Punto de entrada.
 ***************************************************************************/

public class App {

    public static void main(String[] args) {
        
        Logic5a mainController = null;
        int exitStatus = 0;

        try {
            // Inicialización y ejecución
            mainController = new Logic5a();
            mainController.run();
            
            exitStatus = 0;

        } catch (IllegalArgumentException e) {
            System.err.println("\n>> Error de Argumento: " + e.getMessage());
            System.err.println(">> Por favor verifica que los datos numéricos estén en el rango correcto.");
            exitStatus = 1;

        } catch (ArithmeticException e) {
            System.err.println("\n>> Error Matemático: " + e.getMessage());
            System.err.println(">> Hubo un fallo en el cálculo (posible división por cero).");
            exitStatus = 1;

        } catch (Exception e) {
            System.err.println("\n>> Error Inesperado del Sistema:");
            e.printStackTrace();
            exitStatus = 1;

        } finally {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("Fin de ejecución.");
            System.out.println("-".repeat(40));
        }

        System.exit(exitStatus);
    }
}
