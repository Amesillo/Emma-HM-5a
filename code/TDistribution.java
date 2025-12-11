/***************************************************************************
 * Clase:     TDistribution
 * Autor:     Emma Hernandez Mendoza
 * Fecha:     10-12-2025
 * Descripción: Función de densidad t-Student.
 ***************************************************************************/

public class TDistribution {
    
    private double dof;
    private double constantTerm; // Precalculado para eficiencia
    
    public TDistribution(double dof) {
        if (dof <= 0) throw new IllegalArgumentException("DOF debe ser > 0");
        this.dof = dof;
        this.constantTerm = computeConstant();
    }
    
    // Evaluar f(x)
    public double eval(double x) {
        double base = 1.0 + (x * x) / this.dof;
        double exponent = -(this.dof + 1.0) / 2.0;
        
        return this.constantTerm * Math.pow(base, exponent);
    }
    
    private double computeConstant() {
        // Requiere GammaFunction (Clase externa asumida existente)
        double num = GammaFunction.calculateHalf(this.dof); // Gamma((v+1)/2)
        double den = Math.sqrt(this.dof * Math.PI) * GammaFunction.calculate(this.dof / 2.0);
        
        return num / den;
    }
}
