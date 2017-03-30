package estafeta;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @name: c_camino.java
 * @description: La clase contiene los metodos de ingreso, lectura y busqueda de rutas
 * @version 17.3.3
 */
public class c_ruta {
    
    private int a_Llave;
    private String a_Origen;
    private String a_Destino;
    private float a_Costo1;
    private float a_Costo2;
    
    private c_arbol a_Indice;
    private Object [][] a_Grafo;
    private String [] a_G=null;
    private c_arbolT a_arbolT;
    private c_cola a_colaS;
    private c_cola a_Abierto;
    private c_pila a_pilaW;
    private List a_tablaA;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_ruta(){
        m_Menu();
    }// Fin del constructor
    
    /**
     * @name: m_Menu
     * @description: Este metodo muestra un menu con diferentes operaciones que se pueden realizar
     */
    private void m_Menu(){
        Scanner v_Entrada;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n\t\u001B[31mMenú\u001B[30m\n");
                System.out.println("\u001B[34m[1]\u001B[30m Agrega ruta");
                System.out.println("\u001B[34m[2]\u001B[30m Muestra rutas");
                System.out.println("\u001B[34m[3]\u001B[30m Busca ruta");
                System.out.println("\u001B[34m[4]\u001B[30m Modifica ruta");
                System.out.println("\u001B[34m[5]\u001B[30m Eliminar nodo");
                System.out.println("\u001B[34m[6]\u001B[30m Busqueda en anchura");
                System.out.println("\u001B[34m[7]\u001B[30m Busqueda en profundidad");
                System.out.println("\u001B[34m[8]\u001B[30m Busqueda Grafos O");
                System.out.println("\u001B[34m[9]\u001B[30m Salir");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<10)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Opcion!=9);
    }// Fin del método m_Menu
    
    /**
     * @name: m_Opcion
     * @description: Redirecciona la opcion seleccionada por el usuario con 
     * el metodo que realizará la acción deseada
     * @param p_Opcion 
     */
    private void m_Opcion(int p_Opcion){
        m_Arbol();
        switch(p_Opcion){
            case 1:{
                m_Ingresa();
                break;
            }
            case 2:{
                m_leeSecuencial();
                break;
            }
            case 3:{
                m_leeAleatorio();
                break;
            }
            case 4:{
                m_Modifica();
                break;
            }
            case 5:{
                m_eliminaNodo();
                break;
            }
            case 6:{
                m_busquedaAnchura();
                break;
            }
            case 7:{
                //m_busquedaProfundidad();
                break;
            }
            case 8:{
                //m_busquedaGrafoO();
                break;
            }
        }
    } // Fin del método m_Opcion
    
    /**
     * @name: m_Ingresa
     * @description: Método para ingresar los caminos disponibles y su distancia
     */
    private void m_Ingresa(){
        RandomAccessFile v_Maestro = null;
        RandomAccessFile v_Indice = null;
        Scanner v_Entrada;
        long v_indDireccion;
        String v_Opcion="";
        StringBuffer v_Origen,v_Destino;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/files/indice.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudieron abrir los archivos\u001B[30m");
        }
        if(v_Maestro!=null&&v_Indice!=null){
            do{ 
                try{
                    v_Opcion="1";
                    v_Entrada = new Scanner(System.in);
                    v_Maestro.seek(v_Maestro.length());
                    v_Indice.seek(v_Indice.length());
                    v_indDireccion=v_Maestro.getFilePointer();
                    
                    System.out.print("\nLlave: ");
                    a_Llave=v_Entrada.nextInt();
                    
                    System.out.print("Sucursal de Origen: ");
                    a_Origen=v_Entrada.next();
                    v_Origen=new StringBuffer(a_Origen);
                    v_Origen.setLength(17);
                    
                    System.out.print("Sucursal de Destino: ");
                    a_Destino=v_Entrada.next();
                    v_Destino=new StringBuffer(a_Destino);
                    v_Destino.setLength(17);
                    
                    System.out.print("Costo_1: ");
                    a_Costo1=v_Entrada.nextFloat();
                    
                    System.out.print("Costo_2: ");
                    a_Costo2=v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeChars(v_Origen.toString());
                    v_Maestro.writeChars(v_Destino.toString());
                    v_Maestro.writeFloat(a_Costo1);
                    v_Maestro.writeFloat(a_Costo2);
                    
                    v_Indice.writeInt(a_Llave);
                    v_Indice.writeLong(v_indDireccion);
                    
                    System.out.println("\n\u001B[31m¿Desea agregar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor no valido\u001B[30m");
                }
            }while("1".equals(v_Opcion));
            try{
                v_Maestro.close();
                v_Indice.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }// Fin del mètodo m_Ingresa
    
    /**
     * @name: m_leeSecuencial
     * @description: Lee de manera secuencial los registros del archivo maestro
     */
    private void m_leeSecuencial(){
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        System.out.println("\n\t\u001B[31mRutas\u001B[30m\n");
        System.out.print("No.\t");
        System.out.print("Origen\t");
        System.out.print("Destino\t");
        System.out.print("Distancia\t");
        System.out.println("Tiempo");
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            
            while(v_apActual!=v_apFinal){
                
                a_Llave=v_Maestro.readInt();
                
                char v_Origen[] = new char[17];
                for (int i = 0; i < v_Origen.length; i++) {
                    v_Origen[i]=v_Maestro.readChar();
                }
                a_Origen= new String(v_Origen);
                
                char v_Destino[] = new char[17];
                for (int i = 0; i < v_Destino.length; i++) {
                    v_Destino[i]=v_Maestro.readChar();
                }
                a_Destino= new String(v_Destino);
                
                a_Costo1=v_Maestro.readFloat();
                a_Costo2=v_Maestro.readFloat();
                
                if(a_Llave>0){
                    System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                    System.out.print("\u001B[34m"+a_Origen+"\u001B[30m\t");
                    System.out.print("\u001B[34m"+a_Destino+"\u001B[30m\t");
                    System.out.print(a_Costo1+"\t\t");
                    System.out.print(a_Costo2+"\n");
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
    }// Fin del método m_leeSecuencial
    
    private void m_Arbol(){
        a_Indice = new c_arbol();
        RandomAccessFile v_Indice;
        long v_apActual,v_apFinal,v_Direccion;
        int v_Llave;
        try{
            v_Indice = new RandomAccessFile("src/files/indice.dat","r");
            v_apActual=v_Indice.getFilePointer();
            v_apFinal=v_Indice.length();
            while(v_apActual!=v_apFinal){
                v_Llave=v_Indice.readInt();
                v_Direccion=v_Indice.readLong();
                if (v_Llave>0) {
                    a_Indice.m_insertarArbol(v_Llave,v_Direccion,v_apActual);
                }
                v_apActual=v_Indice.getFilePointer();
            }
            v_Indice.close();
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivo");
        }
    }
    
    private void m_leeAleatorio(){
        int v_Posicion;
        String v_Opcion="1";
        long v_Desplazamiento=-1;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[31m");
        }
        if(v_Maestro!=null){
            do{
                v_Opcion="1";
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("\nIngrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRegistro(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        System.out.print("\nNo.\t");
                        System.out.print("Origen\t");
                        System.out.print("Destino\t");
                        System.out.print("Distancia\t");
                        System.out.println("Tiempo");
                        
                        a_Llave=v_Maestro.readInt();
                        
                        char v_Origen[] = new char[17];
                        for (int i = 0; i < v_Origen.length; i++) {
                            v_Origen[i]=v_Maestro.readChar();
                        }
                        a_Origen= new String(v_Origen);

                        char v_Destino[] = new char[17];
                        for (int i = 0; i < v_Destino.length; i++) {
                            v_Destino[i]=v_Maestro.readChar();
                        }
                        a_Destino= new String(v_Destino);
                        
                        a_Costo1=v_Maestro.readFloat();
                        a_Costo2=v_Maestro.readFloat();
                        System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_Origen+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_Destino+"\u001B[30m\t");
                        System.out.print(a_Costo1+"\t\t");
                        System.out.print(a_Costo2+"\n");
                    }
                    System.out.println("\n\u001B[31m¿Desea buscar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
                }
            }while("1".equals(v_Opcion));
        }
        try{
            v_Maestro.close();
        }catch(Exception e){
            
        }
    }
    
    private void m_Modifica(){        
        int v_Posicion;
        String v_Opcion="1";
        long v_Desplazamiento=-1;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        StringBuffer v_Origen,v_Destino;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null){
            do{
                v_Opcion="1";
                try{
                    
                    v_Entrada=new Scanner(System.in);
                    System.out.print("\nIngrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRegistro(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        v_Maestro.readInt();
                        
                        System.out.print("Sucursal de Origen: ");
                        a_Origen=v_Entrada.next();
                        v_Origen=new StringBuffer(a_Origen);
                        v_Origen.setLength(17);
                        
                        System.out.print("Sucursal de Destino: ");
                        a_Destino=v_Entrada.next();
                        v_Destino=new StringBuffer(a_Destino);
                        v_Destino.setLength(17);
                        
                        System.out.print("Costo_1: ");
                        a_Costo1=v_Entrada.nextFloat();
                        
                        System.out.print("Costo_2: ");
                        a_Costo2=v_Entrada.nextFloat();

                        v_Maestro.writeChars(v_Origen.toString());
                        v_Maestro.writeChars(v_Destino.toString());
                        v_Maestro.writeFloat(a_Costo1);
                        v_Maestro.writeFloat(a_Costo2);
                    }
                    System.out.println("\n\u001B[31m¿Desea modificar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
                    System.out.println(e.toString());
                }
            }while("1".equals(v_Opcion));
        }
        try{
            v_Maestro.close();
        }catch(Exception e){
            
        }
    }//Fin del Método
    
    private void m_Elimina(){
        int v_Posicion;
        String v_Opcion="1";
        long v_Desplazamiento=-1;
        RandomAccessFile v_Maestro = null,v_Indice=null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/files/indice.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null && v_Indice != null){
            do{
                v_Opcion="1";
                try{                    
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRegistro(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        v_Maestro.writeInt(-1);
                        v_Indice.seek(a_Indice.m_buscaRegIndice(v_Posicion));
                        v_Indice.writeInt(-1);
                    }
                    System.out.println("\n\u001B[31m¿Desea eliminar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while("1".equals(v_Opcion));
        }
    }  
    
    private void m_eliminaNodo(){
        c_eliminados v_Eliminados;
        RandomAccessFile v_Maestro = null,v_Indice=null;
        long v_apActual,v_apFinal;
        Scanner v_Entrada;
        String v_Opcion="1";
        String v_Nodo;
        StringBuffer v_nodoBuffer;
        
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/files/indice.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
        if(v_Maestro!=null&&v_Indice!=null){
            do{
                try{
                    v_Opcion="1";
                    v_Eliminados = new c_eliminados();
                    v_apActual=v_Maestro.getFilePointer();
                    v_apFinal=v_Maestro.length();
                    v_Entrada=new Scanner(System.in);

                    System.out.print("\nIngrese el nodo: ");
                    v_Nodo=v_Entrada.next();
                    v_nodoBuffer=new StringBuffer(v_Nodo);
                    v_nodoBuffer.setLength(17);
                    
                    while(v_apActual!=v_apFinal){
                        a_Llave=v_Maestro.readInt();
                        
                        char v_Origen[] = new char[17];
                        for (int i = 0; i < v_Origen.length; i++) {
                            v_Origen[i]=v_Maestro.readChar();
                        }
                        a_Origen= new String(v_Origen);

                        char v_Destino[] = new char[17];
                        for (int i = 0; i < v_Destino.length; i++) {
                            v_Destino[i]=v_Maestro.readChar();
                        }
                        a_Destino= new String(v_Destino);
                        
                        v_Maestro.seek(v_apActual);
                        if(a_Llave>0){
                            if(a_Origen.equals(v_nodoBuffer.toString())||a_Destino.equals(v_nodoBuffer.toString())){
                                v_Maestro.writeInt(-1);
                                v_Indice.writeInt(-1);
                                v_Eliminados.m_Inserta(v_nodoBuffer.toString());
                            }else{
                                v_Maestro.readInt();
                                v_Indice.readInt();
                            }
                        }else{
                            v_Maestro.readInt();
                            v_Indice.readInt();
                        }
                        v_Origen = new char[17];
                        for (int i = 0; i < v_Origen.length; i++) {
                            v_Origen[i]=v_Maestro.readChar();
                        }
                        a_Origen= new String(v_Origen);

                        v_Destino = new char[17];
                        for (int i = 0; i < v_Destino.length; i++) {
                            v_Destino[i]=v_Maestro.readChar();
                        }
                        
                        a_Destino= new String(v_Destino);
                        v_Maestro.readFloat();
                        v_Maestro.readFloat();
                        v_Indice.readLong();
                        v_apActual=v_Maestro.getFilePointer();
                    }
                    System.out.println("\n\u001B[31m¿Desea eliminar otro nodo?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while("1".equals(v_Opcion));            
        }
        try{
            v_Maestro.close();
            v_Indice.close();
        }catch(Exception e){}
    }
    
    private void m_busquedaAnchura(){
        StringBuffer v_Origen,v_Destino;
        c_eliminados v_Eliminados;
        Scanner v_Entrada;        
        m_fillGrafo();
        m_fillG();
        if(a_G!=null){
            try{
                v_Entrada=new Scanner(System.in);
                
                System.out.print("\nSucursal de Origen: ");
                a_Origen=v_Entrada.next();
                v_Origen=new StringBuffer(a_Origen);
                v_Origen.setLength(17);

                System.out.print("Sucursal de Destino: ");
                a_Destino=v_Entrada.next();
                v_Destino=new StringBuffer(a_Destino);
                v_Destino.setLength(17);
                
                boolean v_bdOrigen=false;
                boolean v_bdDestino=false;
                v_Eliminados = new c_eliminados();
                
                if(v_Eliminados.m_buscaEliminado(v_Origen.toString()))
                    for (int i = 0; i < a_G.length; i++) {
                        if(a_G[i].equals(v_Origen.toString()))
                            v_bdOrigen=true;
                    }
                
                if(v_Eliminados.m_buscaEliminado(v_Destino.toString()))
                    for (int i = 0; i < a_G.length; i++) {
                        if(a_G[i].equals(v_Destino.toString()))
                            v_bdDestino=true;
                    }
                
                if(v_bdOrigen&&v_bdDestino){
                    a_arbolT=new c_arbolT(v_Origen.toString());
                    a_colaS=new c_cola();
                    a_colaS.m_insertarCola(v_Origen.toString());
                    if(v_Origen.equals(v_Destino)){
                        a_colaS.m_vaciaCola();
                    }
                    if(!v_Origen.equals(v_Destino)){
                        do{
                            String X=a_colaS.m_getVertice();
                            for (int i = 0; i < a_G.length; i++) {
                                String Y=a_G[i];
                                if(m_buscaGrafo(X,Y)&&a_arbolT.m_buscaArbol(Y)){
                                    a_arbolT.m_insertaArbol(X, Y);
                                    a_colaS.m_insertarCola(Y);
                                    if(Y.equals(v_Destino.toString())){
                                        i = a_G.length;
                                        a_colaS.m_vaciaCola();
                                    }
                                }
                            }
                            if(a_colaS.m_getRaiz()!=null){
                                a_colaS.m_sacarCola();
                            }
                        }while(a_colaS.m_getRaiz()!=null);    
                    }
                    a_arbolT.m_imprimeArbol();
                    System.out.println("\n"+a_arbolT.m_imprimeCamino(v_Destino.toString()));
                }else{
                    if(!v_bdOrigen)
                        System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                    if(!v_bdDestino)
                        System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    /*
    private void m_busquedaProfundidad(){
        c_eliminados v_Eliminados;
        Scanner v_Entrada;        
        m_fillGrafo();
        m_fillG();
        if(a_G!=null){
            try{
                v_Entrada=new Scanner(System.in);
                System.out.print("\nSucursal de Origen: ");
                a_Origen=v_Entrada.nextInt();
                System.out.print("Sucursal de Destino: ");
                a_Destino=v_Entrada.nextInt();
                boolean v_bdOrigen=false;
                boolean v_bdDestino=false;
                v_Eliminados = new c_eliminados();
                if(v_Eliminados.m_buscaEliminado(a_Origen))
                    for (int i = 0; i < a_G.length; i++) {
                        if(a_G[i]==a_Origen)
                            v_bdOrigen=true;
                    }
                if(v_Eliminados.m_buscaEliminado(a_Destino))
                    for (int i = 0; i < a_G.length; i++) {
                        if(a_G[i]==a_Destino)
                            v_bdDestino=true;
                    }
                if(v_bdOrigen&&v_bdDestino){
                    a_arbolT=new c_arbolT(a_Origen);
                    a_pilaW=new c_pila();
                    a_pilaW.m_insertaPila(a_Origen);
                    if(a_Origen!=a_Destino){
                        do{
                            for (int i = 0; i < a_G.length; i++) {
                                int X=a_pilaW.m_getVertice();
                                int Y=a_G[i];
                                if(m_buscaGrafo(X,Y)&&a_arbolT.m_buscaArbol(Y)){
                                    a_arbolT.m_insertaArbol(X, Y);
                                    a_pilaW.m_insertaPila(Y);
                                    i=-1;
                                    if(Y==a_Destino){
                                        i = a_G.length;
                                        a_pilaW.m_vaciaPila();
                                    }
                                }
                            }
                            if(a_pilaW.m_getRaiz()!=null){
                                a_pilaW.m_sacaPila();
                            }
                        }while(a_pilaW.m_getRaiz()!=null);
                    }
                    a_arbolT.m_imprimeArbol();
                    System.out.println("\n"+a_arbolT.m_imprimeCamino(a_Destino));
                }else{
                    if(!v_bdOrigen)
                        System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                    if(!v_bdDestino)
                        System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    private void m_busquedaGrafoO(){
        c_eliminados v_Eliminados;
        a_tablaA = new ArrayList();
        c_tablaA v_Regristro;
        boolean v_Bandera=false;
        int v_n,v_Anterior=0;
        float v_Peso=0;
        Scanner v_Entrada;
        m_fillGrafo();
        m_fillG();
        try{
            v_Entrada=new Scanner(System.in);
            System.out.print("\nSucursal de Origen: ");
            a_Origen=v_Entrada.nextInt();
            System.out.print("Sucursal de Destino: ");
            a_Destino=v_Entrada.nextInt();
            boolean v_bdOrigen=false;
            boolean v_bdDestino=false;
            v_Eliminados = new c_eliminados();
            if(v_Eliminados.m_buscaEliminado(a_Origen))
                for (int i = 0; i < a_G.length; i++) {
                    if(a_G[i]==a_Origen)
                        v_bdOrigen=true;
                }
            if(v_Eliminados.m_buscaEliminado(a_Destino))
                for (int i = 0; i < a_G.length; i++) {
                    if(a_G[i]==a_Destino)
                        v_bdDestino=true;
                }
            if(v_bdOrigen&&v_bdDestino){
                a_Abierto=new c_cola();
                a_Abierto.m_insertarCola(a_Origen);
                do{
                    v_n=a_Abierto.m_getVertice();
                    a_Abierto.m_sacarCola();
                    //Obtiene los sucesores de n
                    List v_Sucesores = new ArrayList();
                    for (int i = 0; i < a_Grafo.length; i++) {
                        if((int)a_Grafo[i][0]==v_n){
                            c_sucesor v_Sucesor=new c_sucesor((int)a_Grafo[i][1],(float)a_Grafo[i][2]);
                            v_Sucesores.add(v_Sucesor);
                        }
                    }
                    v_Regristro = new c_tablaA(v_n,v_Anterior,v_Peso,v_Sucesores);
                    a_tablaA.add(v_Regristro);
                    //Se recorre cada uno de los sucesores
                    for (int i = 0; i < v_Sucesores.size(); i++) {
                        c_sucesor v_Sucesor=(c_sucesor)v_Sucesores.get(i);
                        for (int j = 0; j < a_tablaA.size(); j++) {
                            v_Regristro=(c_tablaA)a_tablaA.get(j);
                            if(v_Regristro.m_getN()==v_Sucesor.m_getSucesor()){
                                v_Bandera=true;
                            }
                        }
                        if(v_Bandera){
                            
                        }else{
                            v_Anterior=v_n;
                            v_Peso=v_Sucesor.m_getPeso();
                            v_Regristro = new c_tablaA(v_n,v_Anterior,v_Peso,null);
                        }
                        v_Bandera=false;
                    }
                    
                }while(a_Abierto.m_getRaiz()!=null);    
                System.out.println("");
            }else{
                if(!v_bdOrigen)
                    System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                if(!v_bdDestino)
                    System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
            }
        }catch(Exception e){}
    }
    */
    
    private void m_fillGrafo(){
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        a_Grafo=null;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            int v_tamañoGrafo=0;
            
            while(v_apActual!=v_apFinal){
                v_Maestro.readInt();
                char v_Origen[] = new char[17];
                for (int i = 0; i < v_Origen.length; i++) {
                    v_Origen[i]=v_Maestro.readChar();
                }
                char v_Destino[] = new char[17];
                for (int i = 0; i < v_Destino.length; i++) {
                    v_Destino[i]=v_Maestro.readChar();
                }
                v_Maestro.readFloat();
                v_Maestro.readFloat();
                v_tamañoGrafo++;
                v_apActual=v_Maestro.getFilePointer();
            }
            a_Grafo=new Object[v_tamañoGrafo][4];
            v_Maestro.seek(0);
            v_tamañoGrafo=0;
            v_apActual=v_Maestro.getFilePointer();
            while(v_apActual!=v_apFinal){
                a_Llave=v_Maestro.readInt();
                char v_Origen[] = new char[17];
                for (int i = 0; i < v_Origen.length; i++) {
                    v_Origen[i]=v_Maestro.readChar();
                }
                a_Origen= new String(v_Origen);
                char v_Destino[] = new char[17];
                for (int i = 0; i < v_Destino.length; i++) {
                    v_Destino[i]=v_Maestro.readChar();
                }
                a_Destino= new String(v_Destino);
                a_Costo1=v_Maestro.readFloat();
                a_Costo2=v_Maestro.readFloat();
                a_Grafo[v_tamañoGrafo][0]= a_Origen;
                a_Grafo[v_tamañoGrafo][1]= a_Destino;
                a_Grafo[v_tamañoGrafo][2]= a_Costo1;
                a_Grafo[v_tamañoGrafo][3]= a_Costo2;
                v_tamañoGrafo++;
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo maestro\u001B[30m");
        }
    }
    
    
    private void m_fillG(){
        a_G=null;
        String v_Anterior="";
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            int v_tamañoG=0;
            while(v_apActual!=v_apFinal){
                v_Maestro.readInt();
                char v_Origen[] = new char[17];
                for (int i = 0; i < v_Origen.length; i++) {
                    v_Origen[i]=v_Maestro.readChar();
                }
                a_Origen= new String(v_Origen);
                char v_Destino[] = new char[17];
                for (int i = 0; i < v_Destino.length; i++) {
                    v_Destino[i]=v_Maestro.readChar();
                }
                v_Maestro.readFloat();
                v_Maestro.readFloat();
                if(!v_Anterior.equals(a_Origen)){
                    v_Anterior=a_Origen;
                    v_tamañoG++;
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            a_G=new String[v_tamañoG];
            v_Maestro.seek(0);
            v_apActual=v_Maestro.getFilePointer();
            v_Anterior="";
            int v_Indice=0;
            while(v_apActual!=v_apFinal){
                v_Maestro.readInt();
                char v_Origen[] = new char[17];
                for (int i = 0; i < v_Origen.length; i++) {
                    v_Origen[i]=v_Maestro.readChar();
                }
                a_Origen= new String(v_Origen);
                char v_Destino[] = new char[17];
                for (int i = 0; i < v_Destino.length; i++) {
                    v_Destino[i]=v_Maestro.readChar();
                }
                v_Maestro.readFloat();
                v_Maestro.readFloat();
                if(!v_Anterior.equals(a_Origen)){
                    a_G[v_Indice]=a_Origen;
                    v_Indice++;
                    v_Anterior=a_Origen;
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo maestro\u001B[30m");
        }
    }
    
    private boolean m_buscaGrafo(String p_X,String p_Y){
        boolean v_Bandera=false;
        String v_X,v_Y;
        for (int i = 0; i < a_Grafo.length; i++) {
            v_X=(String)a_Grafo[i][0];
            v_Y=(String)a_Grafo[i][1];
            if(v_X.equals(p_X)&&v_Y.equals(p_Y))
                v_Bandera=true;
        }
        return v_Bandera;
    }   
}
