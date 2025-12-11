/***************************************************************************
 * Clase:     GammaFunction
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Cálculo recursivo de la función Gamma.
 ***************************************************************************/

public class GammaFunction {

    /**
     * Calcula Gamma(x) usando recursividad.
     * Gamma(n) = (n-1)!
     */
    public static double calculate(double x) {
        // Validación básica
        if (x <= 0) {
            throw new IllegalArgumentException("El valor x debe ser positivo. Recibido: " + x);
        }
        
        // Caso Base 1: Gamma(1) = 1
        if (Math.abs(x - 1.0) < 1.0e-10) {
            return 1.0;
        }
        
        // Caso Base 2: Gamma(0.5) = Raíz de Pi
        if (Math.abs(x - 0.5) < 1.0e-10) {
            return Math.sqrt(Math.PI);
        }
        
        // Paso Recursivo: Gamma(x) = (x-1) * Gamma(x-1)
        return (x - 1.0) * calculate(x - 1.0);
    }
    
    /**
     * Método auxiliar para la distribución t-Student.
     * Calcula Gamma((n+1)/2).
     */
    public static double calculateHalf(double n) {
        return calculate((n + 1.0) / 2.0);
    }
}
