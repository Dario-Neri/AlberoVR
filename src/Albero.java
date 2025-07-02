import java.util.List;

// Nome: Dario  Cognome: Neri     Matricola: 7158839

public interface Albero<T> {
    // inserire la radice in un albero vuoto e restituirla;
    NodoVP<T> inserisciRadiceInAlberoVuoto(T info);
    // inserire una nuova radice in un albero non vuoto in modo che la vecchia
    // radice diventi figlia di quella nuova, che deve essere restituita;
    NodoVP<T> inserisciRadiceInAlberoPieno(T info);
    // restituire la radice di un albero;
    NodoVP<T> getRadice();
    // restituire la lista dei figli di un nodo;
    List<NodoVP<T>> listaFigli(NodoVP<T> nodo);
    // restituire il padre di un nodo;
    NodoVP<T> getPadre(NodoVP<T> nodo);
    // restituire il numero di nodi dell’albero;
    int numeroNodi();
    // resitutire il livello di un nodo;
    int livelloNodo(NodoVP<T> nodo);
    // aggiungere e restituire un nodo con informazione data come figlio di un
    // nodo gi`a presente (che sar`a passato come parametro al metodo, insieme
    // all’informazione);
    NodoVP<T> aggiungiFiglio(NodoVP<T> padre, T info);
    // restituire il numero di figli di un nodo dell’albero;
    int numeroFigliNodo(NodoVP<T> nodo);
    // restituire il contenuto di un nodo dell’albero;
    T getContenutoNodo(NodoVP<T> nodo);
    // restituire l’altezza dell’albero;
    int altezzaAlbero();
    // resitutire il numero delle foglie di un nodo;
    int foglieAlbero();
    // cambiare il contenuto di un nodo dell’albero;
    void setContenuto(NodoVP<T> nodo, T contenuto);
    // restituire la lista dei nodi dell’albero visitato in profondit`a;
    List<NodoVP<T>> visitaInProfondita();
    // restituire la lista dei nodi dell’albero visitato in ampiezza;
    List<NodoVP<T>> visitaInAmpiezza();
    // restituire una stringa che rappresenti l’albero:
    String stringaAlbero();
}
