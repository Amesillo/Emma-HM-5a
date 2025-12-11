/***************************************************************************
 * Clase:     SearchAlgorithm
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Algoritmo de búsqueda iterativa para hallar x dado p.
 ***************************************************************************/

public class SearchAlgorithm {
    
    private double targetP;
    private int dof;
    private double tolerance;
    
    // Estado de la búsqueda
    private int iterationsDone;
    private double finalError;
    private double lastPCalculated;
    
    public SearchAlgorithm(double targetP, int dof, double tolerance) {
        this.targetP = targetP;
        this.dof = dof;
        this.tolerance = tolerance;
        this.iterationsDone = 0;
    }
    
    public double solve() {
        // Valores iniciales según especificación
        double currentX = 1.0;
        double delta = 0.5;
        double currentError = 0.0;
        double previousError = 0.0;
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s%n", "Iter", "X_Actual", "P_Calc", "Error", "Delta");
        System.out.println("-------------------------------------------------------------------------");
        
        boolean converged = false;
        
        while (!converged) {
            this.iterationsDone++;
            
            // 1. Calcular integral con Simpson
            // Nota: Se reinicia el integrador con segmentos base (10)
            SimpsonIntegration integrator = new SimpsonIntegration(currentX, this.dof, this.tolerance, 10);
            double calculatedP = integrator.compute();
            this.lastPCalculated = calculatedP;
            
            // 2. Calcular error
            currentError = calculatedP - this.targetP;
            
            // 3. Ajuste de Delta (si cambia el signo del error, nos pasamos, reducir paso)
            if (this.iterationsDone > 1) {
                boolean signChanged = (Math.signum(currentError) != Math.signum(previousError));
                if (signChanged) {
                    delta /= 2.0;
                    // Debug opcional: System.out.println("   -> Ajuste fino (Delta reducido)");
                }
            }
            
            previousError = currentError;
            
            // Mostrar estado
            System.out.printf("%-5d %-15.8f %-15.8f %-15.8f %-15.8f%n", 
                    this.iterationsDone, currentX, calculatedP, currentError, delta);
            
            // Verificar convergencia
            if (Math.abs(currentError) <= this.tolerance) {
                converged = true;
                this.finalError = currentError;
                break;
            }
            
            // 4. Ajustar X para siguiente iteración
            if (currentError < 0) {
                currentX += delta; // Necesitamos más área
            } else {
                currentX -= delta; // Necesitamos menos área
            }
            
            // Protección contra X negativo
            if (currentX < 0) currentX = 0;
            
            // Failsafe
            if (this.iterationsDone > 150) {
                System.out.println(">> Alerta: Límite de iteraciones excedido.");
                break;
            }
        }
        
        return currentX;
    }
    
    // Getters
    public int getTotalIterations() { return this.iterationsDone; }
    public double getLastError() { return this.finalError; }
    public double getLastPCalculated() { return this.lastPCalculated; }
}
