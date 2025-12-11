/***************************************************************************
 * Clase:     SimpsonIntegration
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Implementación Regla de Simpson 1/3.
 ***************************************************************************/

public class SimpsonIntegration {
    
    private TDistribution distFunction;
    private double xLimit;
    private int dof;
    private double tolerance;
    private int initialSegments;
    
    public SimpsonIntegration(double x, int dof, double error, int segments) {
        this.xLimit = x;
        this.dof = dof;
        this.tolerance = error;
        // Asegurar segmentos pares
        this.initialSegments = (segments % 2 == 0) ? segments : segments + 1;
        this.distFunction = new TDistribution(dof);
    }
    
    // Método principal de cálculo adaptativo
    public double compute() {
        int currentSegments = this.initialSegments;
        double previousArea = 0.0;
        double currentArea = 0.0;
        double diff;
        int iter = 0;
        
        do {
            iter++;
            if (iter > 1) previousArea = currentArea;
            
            currentArea = calculateSegmentedArea(currentSegments);
            
            // Calcular diferencia (error relativo de integración)
            if (iter == 1) {
                diff = this.tolerance + 1.0; // Forzar segunda vuelta
            } else {
                diff = Math.abs(currentArea - previousArea);
            }
            
            currentSegments *= 2; // Duplicar precisión
            
        } while (iter < 2 || diff >= this.tolerance);
        
        return currentArea;
    }
    
    private double calculateSegmentedArea(int n) {
        double w = this.xLimit / n; // Ancho (h)
        double sum = this.distFunction.eval(0.0) + this.distFunction.eval(this.xLimit);
        
        // Sumatoria optimizada
        for (int i = 1; i < n; i++) {
            double val = this.distFunction.eval(i * w);
            // Si es impar * 4, si es par * 2
            sum += (i % 2 == 1) ? (4.0 * val) : (2.0 * val);
        }
        
        return (w / 3.0) * sum;
    }
}
