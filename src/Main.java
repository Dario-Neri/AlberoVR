// Nome: Dario  Cognome: Neri     Matricola: 7158839

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Albero<String> albero = new AlberoVP<>();
        NodoVP<String> radiceUno = new NodoVP<>("radiceUno");
        albero.inserisciRadiceInAlberoVuoto("radiceUno");
        verificaTrue(albero.getRadice().equals(radiceUno));            //verifica metodo inserisciRadiceInAlberoVuoto

        NodoVP<String> radiceDue = new NodoVP<>("radiceDue");
        albero.inserisciRadiceInAlberoPieno("radiceDue");
        verificaTrue(albero.getRadice().equals(radiceDue));            //verifica inserisciRadiceInAlberoPieno
        verificaTrue(albero.getPadre(radiceUno).equals(radiceDue));    //Verifica metodo get padre

        verificaTrue(albero.listaFigli(radiceDue).contains(radiceUno));//verifica medoto listaFigli

        verificaTrue(albero.livelloNodo(radiceUno) == 1);       //verifica metodo livello nodo
        verificaTrue(albero.livelloNodo(radiceDue) == 0);

        NodoVP<String> nodoUno = new NodoVP<>("nodoUno");       //verifica metodo aggiungiFiglio
        albero.aggiungiFiglio(radiceDue, "nodoUno");
        verificaTrue(albero.getPadre(nodoUno).equals(radiceDue));

        verificaTrue(albero.numeroFigliNodo(radiceDue) == 2);   //verifica metodo numeroFigli

        verificaTrue(albero.getContenutoNodo(radiceDue).equals("radiceDue"));// verifica metodo getContenutoNodo

        verificaTrue(albero.altezzaAlbero() == 1);              // verifica metodo altezzaAlbero

        verificaTrue(albero.foglieAlbero() == 2);

        //costruzione albero
        NodoVP<String> nodoDue = new NodoVP<>("nodoDue");
        NodoVP<String> nodoTre = new NodoVP<>("nodoTre");
        NodoVP<String> nodoQuattro = new NodoVP<>("nodoQuattro");
        NodoVP<String> nodoCinque = new NodoVP<>("nodoCinque");
        NodoVP<String> nodoSei = new NodoVP<>("nodoSei");
        albero.aggiungiFiglio(radiceUno, "nodoDue");
        albero.aggiungiFiglio(radiceUno, "nodoTre");
        albero.aggiungiFiglio(nodoUno, "nodoQuattro");
        albero.aggiungiFiglio(nodoQuattro, "nodoCinque");
        albero.aggiungiFiglio(nodoQuattro, "nodoSei");

        List<NodoVP<String>> listaVisitaProf = Arrays.asList(radiceDue, radiceUno, nodoDue, nodoTre, nodoUno, nodoQuattro, nodoCinque, nodoSei);
        verificaTrue(albero.visitaInProfondita().equals(listaVisitaProf));
//        System.out.println(listaVisitaProf);
//        System.out.println(albero.visitaInProfondita());

        List<NodoVP<String>> listaVisitaAmpiezza = Arrays.asList(radiceDue, radiceUno, nodoUno, nodoDue, nodoTre, nodoQuattro, nodoCinque, nodoSei);
        verificaTrue(albero.visitaInAmpiezza().equals(listaVisitaAmpiezza));
//        System.out.println(listaVisitaAmpiezza);
//        System.out.println(albero.visitaInAmpiezza());

        System.out.println(albero);
        //OutPut => [radiceDue[radiceUno[nodoDue[]nodoTre[]]nodoUno[nodoQuattro[nodoCinque[]nodoSei[]]]]
    }

    private static void verificaTrue(boolean valore){
        if(!valore){
            throw new RuntimeException("valore booleano non true");
        }
    }
}


