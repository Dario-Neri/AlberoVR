// Nome: Dario  Cognome: Neri     Matricola: 7158839

public class NodoVP<T> {

    private T valore;                 //variabile T generico per i valori dei nodi

    public NodoVP(T valore) {
        if(valore == null){
            throw new IllegalArgumentException("il valore del nodo non può essere nullo");
        }
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    @Override
    public int hashCode() {
        return valore.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        NodoVP<T> nodo = (NodoVP<T>)obj;
        return this.valore.equals(nodo.valore);
    }

    @Override
    public String toString() {
        return valore.toString();
    }

}
