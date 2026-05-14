package ProyectoED;

public class Llibre {
    private String titol;
    private String autor;
    private boolean prestat;

    public Llibre(String titol, String autor) {
        this.titol = titol;
        this.autor = autor;
        this.prestat = false; // Per defecte, un llibre nou està disponible [cite: 28]
    }

    // Getters
    public String getTitol() { return titol; }
    public String getAutor() { return autor; }
    
    // Mètodes d'estat per al GestorBiblioteca
    public boolean esPrestat() { return prestat; }
    
    public void prestar() { 
        this.prestat = true; 
    }
    
    public void retornar() { 
        this.prestat = false; 
    }

    @Override
    public String toString() {
        return titol + " de " + autor + (prestat ? " (En préstec)" : " (Disponible)");
    }
}
