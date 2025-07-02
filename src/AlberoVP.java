import java.util.*;

// Nome: Dario  Cognome: Neri     Matricola: 7158839

public class AlberoVP<T> implements Albero<T> {

    private NodoVP<T> radice = null;                       //memoriziamo la radice per convenienza
    private List<NodoVP<T>> nodi = new ArrayList<>();      //lista nodi
    private List<NodoVP<T>> padri = new ArrayList<>();     //lista padri
    private Map<T, Boolean> valoriInAlbero = new HashMap<>();

    /**
     * @param info: l'informazione da inserire nel nodo
     * @return la nuova radice oppure null se l'albero non è vuoto
     */
    @Override
    public NodoVP<T> inserisciRadiceInAlberoVuoto(T info) {
            if(radice == null){
                this.radice = new NodoVP<>(info);
                padri.addLast(null);
                nodi.addLast(radice);
                valoriInAlbero.put(info, true);
                return this.getRadice();
            }
        return null;
    }

    /**
     * Ritorna la nuova radice che sarà padre di quella precedente
     * Si preservs il primo elemento (index 0) null della lista padri come padre della nuova radice
     *
     * @param info: l'informazione da inserire in un nodo che sarà la nuova radice
     * @return nuvova radice
     */
    @Override
    public NodoVP<T> inserisciRadiceInAlberoPieno(T info) {
        if(!valoriInAlbero.containsKey(info)){
            NodoVP<T> nuovaRadice = new NodoVP<>(info);
            padri.add(1, nuovaRadice);
            nodi.addFirst(nuovaRadice);
            radice = nuovaRadice;
            valoriInAlbero.put(info, true);
            return radice;
        }
        throw new IllegalArgumentException("Questo tipo di albero non accetta valori duplicati nel nodo");
    }

    /**
     * @return la radice dell'albero o null se non esiste
     */
    @Override
    public NodoVP<T> getRadice() {
        return this.radice;
    }

    /**
     * @param nodo: nodo padre lista dei figli
     * @return la lista dei figli di un nodo, se una foglia la lista è vuota
     */
    @Override
    public List<NodoVP<T>> listaFigli(NodoVP<T> nodo) {
        List<NodoVP<T>> lista = new ArrayList<NodoVP<T>>();
        if(nodo == null){
            lista.add(this.radice);
            return lista;
        }
        for (int i = 0; i < this.padri.size(); i++) {
            if ((padri.get(i) != null) && (padri.get(i).equals(nodo))) {
                lista.add(nodi.get(i));
            }
        }
        return lista;
    }

    /**
     * @param nodo di cui si vuole trovato il padre
     * @return il padre del nodo o null se è la radice
     */
    @Override
    public NodoVP<T> getPadre(NodoVP<T> nodo) {
        int i = nodi.indexOf(nodo);
        if (i >= 0) {
            return padri.get(i);
        }
        return null;
    }

    /**
     * @return il numero di nodi dell'albero
     */
    @Override
    public int numeroNodi() {
        return nodi.size();
    }

    /**
     * @param nodo di cui vogliamo calcolare il livello, 0 se è laradice
     * @return il numero intero del livello dell'albero
     */
    @Override
    public int livelloNodo(NodoVP<T> nodo) {
        int livello = 0;
        while (getPadre(nodo) != null) {
            nodo = getPadre(nodo);
            livello++;
        }
        return livello;
    }

    /**
     * Dopo aver controllato se abbiamo già un nodo con lo stesso valore
     * Aggiungiamo il nuovo nodo con quel valore alla lista dei figli
     * e il padre alla lista ddei padri
     *
     * @param padre
     * @param info
     * @return
     */
    @Override
    public NodoVP<T> aggiungiFiglio(NodoVP<T> padre, T info) {
        if(!valoriInAlbero.containsKey(info)) {
            NodoVP<T> nodoFiglio = new NodoVP<>(info);
            nodi.addLast(nodoFiglio);
            padri.addLast(padre);
            return nodoFiglio;
        }
        throw new IllegalArgumentException("Questo tipo di albero non accetta valori duplicati nei nodi");
    }

    /**
     * @param nodo
     * @return il numero della lista dei figli del nodo in input
     */
    @Override
    public int numeroFigliNodo(NodoVP<T> nodo) {
        return listaFigli(nodo).size();
    }

    /**
     * @param nodo
     * @return il valore del nodo in input
     */
    @Override
    public T getContenutoNodo(NodoVP<T> nodo) {
        return nodo.getValore();
    }

    /**
     * @return altezza albero utilizzando il metodo helper
     */
    @Override
    public int altezzaAlbero() {
        return altezzaAlberoHelper(this.radice);
    }

    /**
     * metodo che aiuta ha trovare l'altezza dell'albero
     * @param nodo
     * @return altezza
     */
    private int altezzaAlberoHelper(NodoVP<T> nodo) {
        int max = 0;
        if(listaFigli(nodo).isEmpty()){
            return max;
        }
        for(NodoVP<T> figlio : listaFigli(nodo)){
            int altezzaFiglio = altezzaAlberoHelper(figlio) + 1;
            if( altezzaFiglio > max)
                max = altezzaFiglio;
        }
        return max;
    }

    /**
     * creo una lista dove inserisco tutti i nodi che non sono padri
     *
     * @return la lunghezza della lista cioè il numero di figli
     */
    @Override
    public int foglieAlbero() {
        List<NodoVP<T>> result = new ArrayList<>(nodi);
        result.removeAll(padri); // differenza tra liste
        return result.size();
    }

    /**
     * inserisce nel nodo in inpunt il valore T che ci è statto fornito
     *
     * @param nodo
     * @param contenuto
     */
    @Override
    public void setContenuto(NodoVP<T> nodo, T contenuto) {
        nodo.setValore(contenuto);
    }

    /**
     * Inizializzo una lista stack (proprietà LIFO) e ci aggiungo la radice
     * Finchè stack non è vuoto, estraggo un elemento e lo aggiungo alla lista della vistita in profondità
     * Aggiungo a stack il primo figlio del nodo estratto da stack precedentemente
     *
     * @return la lista in ordine della visita in profondità vuota se la radice non esiste
     */
    @Override
    public List<NodoVP<T>> visitaInProfondita() {
        List<NodoVP<T>> ret = new ArrayList<>();
        if(radice == null)
            return ret;
        LinkedList<NodoVP<T>> stack = new LinkedList<>();
        stack.push(radice);
        while(!stack.isEmpty()){
            NodoVP<T> nodoCorrente = stack.pop();
            ret.add(nodoCorrente);
            //for(NodoVP<T> figlio : listaFigli(nodoCorrente)){
            //      stack.push(figlio);}
            for(int i = listaFigli(nodoCorrente).size() - 1; i >= 0; i--){
                stack.push(listaFigli(nodoCorrente).get(i));
            }
        }
        return ret;
    }

    /**
     * Inizializzo una lista Queue e ci aggiungo la radice
     * Finchè la queue non è vuota estraggo un elemento e lo aggiungo alla lista
     * Aggiungo alla lista queue tutti i figli di quell'elemento
     *
     * @return lista ordinata visita in ampiezza vuota se la radice non esiste
     */
    @Override
    public List<NodoVP<T>> visitaInAmpiezza() {
        List<NodoVP<T>> ret = new ArrayList<>();
        if(radice == null)
            return ret;
        LinkedList<NodoVP<T>> queue = new LinkedList<>();
        queue.add(radice);
        while(!queue.isEmpty()){
            NodoVP<T> nodoCorrente = queue.poll();
            ret.add(nodoCorrente);
            queue.addAll(listaFigli(nodoCorrente));
        }
        return ret;
    }

    /**
     * @return la stringa dell'albero
     */
    @Override
    public String stringaAlbero() {
        if (radice == null) return "[]";
        return stringaAlberoHelper(radice);
    }

    private String stringaAlberoHelper(NodoVP<T> node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.getValore()).append("[");
        for (NodoVP<T> figlio : listaFigli(node)) {
            sb.append(stringaAlberoHelper(figlio));
        }
        sb.append("]");
        return sb.toString();
    }
}