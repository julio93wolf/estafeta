package estafeta;

import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @name: c_camino.java
 * @description: La clase contiene los metodos de ingreso y lectura de los nodos
 * @version 17.3.3
 * @author Valle Rodriguez Julio Cesar
 */
public class c_ruta {
    
    private int a_Llave;
    private String a_cdOrigen;
    private String a_cdDestino;
    private float a_Distancia;
    private c_arbol a_Indice;
    private c_lista a_ListaG;
    private c_arbolT a_arbolT;
    private c_cola a_colaS;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_ruta(){
        m_busquedaAnchura();
        m_Menu();
    }// Fin del constructor
    
    /**
     * @name: m_Menu
     * @description: Este metodo muestra un menu con diferentes opciones para ingresar
     * o ver los nodos agregados ademas de modificarlos
     */
    private void m_Menu(){
        Scanner v_Entrada;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n\t\u001B[31mMenú\u001B[30m\n");
                System.out.println("\u001B[34m[1]\u001B[30m Agrega ruta");
                System.out.println("\u001B[34m[2]\u001B[30m Muestra ruta");
                System.out.println("\u001B[34m[3]\u001B[30m Busca ruta");
                System.out.println("\u001B[34m[4]\u001B[30m Modifica ruta");
                System.out.println("\u001B[34m[5]\u001B[30m Eliminar ruta");
                System.out.println("\u001B[34m[6]\u001B[30m Salir");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<7)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Opcion!=6);
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
                m_Elimina();
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
        StringBuffer v_bfOrigen = null;
        StringBuffer v_bfDestino = null;
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
                    a_cdOrigen=v_Entrada.next();
                    v_bfOrigen=new StringBuffer(a_cdOrigen);
                    v_bfOrigen.setLength(40);
                    System.out.print("Sucursal de Destino: ");
                    a_cdDestino=v_Entrada.next();
                    v_bfDestino=new StringBuffer(a_cdDestino);
                    v_bfDestino.setLength(40);
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeChars(v_bfOrigen.toString());
                    v_Maestro.writeChars(v_bfDestino.toString());
                    v_Maestro.writeFloat(a_Distancia);
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
        System.out.println("\n\t\t\u001B[31mRutas\u001B[30m\n");
        System.out.print("No.\t");
        System.out.print("Origen\t\t\t\t");
        System.out.print("Destino\t\t\t");
        System.out.println("Distancia");
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            char v_cdOrigen[]=new char[40],v_cdDestino[]=new char[40],v_Temporal;
            while(v_apActual!=v_apFinal){
                a_Llave=v_Maestro.readInt();
                for (int i = 0; i < v_cdOrigen.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdOrigen[i]=v_Temporal;
                }
                a_cdOrigen= new String(v_cdOrigen);
                for (int i = 0; i < v_cdDestino.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdDestino[i]=v_Temporal;
                }
                a_cdDestino= new String(v_cdDestino);
                a_Distancia=v_Maestro.readFloat();
                if(a_Llave>0){
                    System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                    System.out.print("\u001B[34m"+a_cdOrigen+"\u001B[30m");
                    for (int i = 0; i < (a_cdOrigen.length()-24)/8; i++) {
                        System.out.print("\t");
                    }
                    System.out.print("\u001B[34m"+a_cdDestino+"\u001B[30m");
                    for (int i = 0; i < (a_cdDestino.length()-24)/8; i++) {
                        System.out.print("\t");
                    }
                    System.out.print(a_Distancia+"\n");
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
    }// Fin del método m_leeSecuencial
    
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
                    char v_cdOrigen[]=new char[40],v_cdDestino[]=new char[40],v_Temporal;
                    v_Entrada=new Scanner(System.in);
                    System.out.print("\nIngrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRuta(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        System.out.print("\nNo.\t");
                        System.out.print("Origen\t\t");
                        System.out.print("Destino\t\t");
                        System.out.println("Distancia");
                        a_Llave=v_Maestro.readInt();
                        for (int i = 0; i < v_cdOrigen.length; i++) {
                            v_Temporal = v_Maestro.readChar();
                            v_cdOrigen[i]=v_Temporal;
                        }
                        a_cdOrigen= new String(v_cdOrigen);
                        for (int i = 0; i < v_cdDestino.length; i++) {
                            v_Temporal = v_Maestro.readChar();
                            v_cdDestino[i]=v_Temporal;
                        }
                        a_cdDestino= new String(v_cdDestino);
                        System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_cdOrigen+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_cdDestino+"\u001B[30m\t");
                        System.out.print(v_Maestro.readFloat()+"\n");
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
    }
    
    private void m_Modifica(){        
        int v_Posicion;
        String v_Opcion="1";
        long v_Desplazamiento=-1;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
        if(v_Maestro!=null){
            do{
                v_Opcion="1";
                try{
                    StringBuffer v_bfOrigen = null;
                    StringBuffer v_bfDestino = null;
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRuta(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        v_Maestro.readInt();
                        System.out.print("Sucursal de Origen: ");
                        a_cdOrigen=v_Entrada.next();
                        v_bfOrigen=new StringBuffer(a_cdOrigen);
                        v_bfOrigen.setLength(40);
                        System.out.print("Sucursal de Destino: ");
                        a_cdDestino=v_Entrada.next();
                        v_bfDestino=new StringBuffer(a_cdDestino);
                        v_bfDestino.setLength(40);
                        System.out.print("Distancia: ");
                        a_Distancia=v_Entrada.nextFloat();

                        v_Maestro.writeChars(v_bfOrigen.toString());
                        v_Maestro.writeChars(v_bfDestino.toString());
                        v_Maestro.writeFloat(a_Distancia);
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
                    StringBuffer v_bfOrigen = null;
                    StringBuffer v_bfDestino = null;
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRuta(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        v_Maestro.writeInt(-1);
                        v_Indice.seek(a_Indice.m_buscaDirIndice(v_Posicion));
                        v_Indice.writeInt(-1);
                    }
                    System.out.println("\n\u001B[31m¿Desea eliminar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                    System.out.println(e.toString());
                }
            }while("1".equals(v_Opcion));
        }
    }
    
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
                    a_Indice.m_Insertar(v_Llave,v_Direccion,v_apActual);
                }
                v_apActual=v_Indice.getFilePointer();
            }
            v_Indice.close();
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivo");
        }
    }
    
    private void m_busquedaAnchura(){
        Scanner v_Entrada;
        StringBuffer v_bfOrigen = null;
        StringBuffer v_bfDestino = null;
        m_grafoConexo();
        if(a_ListaG!=null){
            try{
                v_Entrada=new Scanner(System.in);
                System.out.print("Sucursal de Origen: ");
                a_cdOrigen=v_Entrada.next();
                v_bfOrigen=new StringBuffer(a_cdOrigen);
                v_bfOrigen.setLength(40);
                a_cdOrigen= new String(v_bfOrigen);
                System.out.print("Sucursal de Destino: ");
                a_cdDestino=v_Entrada.next();
                v_bfDestino=new StringBuffer(a_cdDestino);
                v_bfDestino.setLength(40);
                a_cdDestino= new String(v_bfDestino);
                a_arbolT=new c_arbolT(a_cdOrigen);
                a_colaS=new c_cola();
                a_colaS.m_Insertar(a_cdOrigen);
                do{
                    String X=a_colaS.m_getVertice();
                    c_lista v_Temporal = a_ListaG;
                    while(v_Temporal.m_getRaiz()!=null){
                        String Y=v_Temporal.m_getVertice();                        
                        if(m_buscaG(X,Y)&&a_arbolT.m_Busca(Y)){
                            a_arbolT.m_Inserta(X, Y);
                            a_colaS.m_Insertar(Y);
                            if(Y.equals(a_cdDestino)){
                                v_Temporal=null;
                                a_colaS=null;
                            }
                        }
                        v_Temporal.m_Siguiente();
                    }
                    m_grafoConexo();
                    a_colaS.m_EliminaPrimero();
                }while(a_colaS.m_getRaiz()!=null);
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    private void m_grafoConexo(){
        String v_Anterior="";
        a_ListaG=new c_lista();
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            char v_cdOrigen[]=new char[40],v_cdDestino[]=new char[40],v_Temporal;
            while(v_apActual!=v_apFinal){
                a_Llave=v_Maestro.readInt();
                for (int i = 0; i < v_cdOrigen.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdOrigen[i]=v_Temporal;
                }
                a_cdOrigen= new String(v_cdOrigen);
                
                for (int i = 0; i < v_cdDestino.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdDestino[i]=v_Temporal;
                }
                a_cdDestino= new String(v_cdDestino);
                a_Distancia=v_Maestro.readFloat();
                if(a_Llave>0){
                    if(!v_Anterior.equals(a_cdOrigen)){
                        a_ListaG.m_Insertar(a_cdOrigen);
                        v_Anterior=a_cdOrigen;
                    }
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
    }
    
    private boolean m_buscaG(String p_X,String p_Y){
        boolean v_Bandera=false;
        String v_Padre,v_Hijo;
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            char v_cdOrigen[]=new char[40],v_cdDestino[]=new char[40],v_Temporal;
            while(v_apActual!=v_apFinal){
                v_Maestro.readInt();
                for (int i = 0; i < v_cdOrigen.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdOrigen[i]=v_Temporal;
                }
                v_Padre= new String(v_cdOrigen);
                for (int i = 0; i < v_cdDestino.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdDestino[i]=v_Temporal;
                }
                v_Hijo= new String(v_cdDestino);
                v_Maestro.readFloat();
                if(a_Llave>0){
                    if(v_Padre.equals(p_X)&&v_Hijo.equals(p_Y))
                        v_Bandera=true;
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
        return v_Bandera;
    }   
}
