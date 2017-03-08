package estafeta;

import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @name: c_camino.java
 * @description: La clase contiene los metodos de ingreso y lectura de los nodos
 * @version 17.3.3
 * @author Valle Rodriguez Julio Cesar
 */
public class c_caminos {
    
    private int a_Llave;
    private String a_cdOrigen;
    private String a_cdDestino;
    private float a_Distancia;
    private c_arbol a_Arbol;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_caminos(){
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
                System.out.println("\n\tMenú\n");
                System.out.println("[1] Agrega ruta");
                System.out.println("[2] Muestra ruta");
                System.out.println("[3] Busca ruta");
                System.out.println("[4] Modifica ruta");
                System.out.println("[5] Salir");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<6)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("Error: Valor fuera de rango");
            }catch(Exception e){
                System.out.println("Error: Valor invalido");
            }
        }while(v_Opcion!=5);
    }// Fin del método m_Menu
    
    /**
     * @name: m_Opcion
     * @description: Redirecciona la opcion seleccionada por el usuario con 
     * el metodo que realizará la acción deseada
     * @param p_Opcion 
     */
    private void m_Opcion(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                m_Ingresa();
                break;
            }
            case 2:{
                m_Arbol();
                m_leeSecuencial();
                break;
            }
            case 3:{
                m_Arbol();
                m_leeAleatorio();
                break;
            }
            case 4:{
                m_Modifica();
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
            System.out.println("Error: No se pudieron abrir los archivos");
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
                    v_bfOrigen.setLength(30);
                    System.out.print("Sucursal de Destino: ");
                    a_cdDestino=v_Entrada.next();
                    v_bfDestino=new StringBuffer(a_cdDestino);
                    v_bfDestino.setLength(30);
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeChars(v_bfOrigen.toString());
                    v_Maestro.writeChars(v_bfDestino.toString());
                    v_Maestro.writeFloat(a_Distancia);
                    
                    v_Indice.writeInt(a_Llave);
                    v_Indice.writeLong(v_indDireccion);
                    
                    System.out.println("\n¿Desea agregar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor no valido");
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
        System.out.println("\n\tRutas\n");
        System.out.print("No.\t");
        System.out.print("Origen\t\t");
        System.out.print("Destino\t\t");
        System.out.println("Distancia");
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            char v_cdOrigen[]=new char[30],v_cdDestino[]=new char[30],v_Temporal;
            while(v_apActual!=v_apFinal){
                System.out.print(v_Maestro.readInt()+"\t");
                for (int i = 0; i < v_cdOrigen.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdOrigen[i]=v_Temporal;
                }
                a_cdOrigen= new String(v_cdOrigen);
                System.out.print(a_cdOrigen+"\t");
                for (int i = 0; i < v_cdDestino.length; i++) {
                    v_Temporal = v_Maestro.readChar();
                    v_cdDestino[i]=v_Temporal;
                }
                a_cdDestino= new String(v_cdDestino);
                System.out.print(a_cdDestino+"\t");
                System.out.print(v_Maestro.readFloat()+"\n");
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivo");
        }
    }// Fin del método m_leeSecuencial
    
    private void m_leeAleatorio(){
        int v_Llave;
        String v_Opcion="1";
        long v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivos maestro");
        }
        if(v_Maestro!=null){
            do{
                v_Opcion="1";
                try{
                    char v_cdOrigen[]=new char[30],v_cdDestino[]=new char[30],v_Temporal;
                    v_Entrada=new Scanner(System.in);
                    System.out.print("\nIngrese el no. de ruta: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=a_Arbol.m_Busca(v_Llave);
                    v_Maestro.seek(v_Desplazamiento);
                    System.out.print("\nNo.\t");
                    System.out.print("Origen\t\t");
                    System.out.print("Destino\t\t");
                    System.out.println("Distancia");
                    System.out.print(v_Maestro.readInt()+"\t");
                    for (int i = 0; i < v_cdOrigen.length; i++) {
                        v_Temporal = v_Maestro.readChar();
                        v_cdOrigen[i]=v_Temporal;
                    }
                    a_cdOrigen= new String(v_cdOrigen);
                    System.out.print(a_cdOrigen+"\t");
                    for (int i = 0; i < v_cdDestino.length; i++) {
                        v_Temporal = v_Maestro.readChar();
                        v_cdDestino[i]=v_Temporal;
                    }
                    a_cdDestino= new String(v_cdDestino);
                    System.out.print(a_cdDestino+"\t");
                    System.out.print(v_Maestro.readFloat()+"\n");
                    System.out.println("\n¿Desea buscar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while("1".equals(v_Opcion));
        }
    }
    
    private void m_Modifica(){        
        int v_Llave;
        String v_Opcion="1";
        long v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivos maestro");
        }
        if(v_Maestro!=null){
            do{
                v_Opcion="1";
                try{
                    StringBuffer v_bfOrigen = null;
                    StringBuffer v_bfDestino = null;
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de camino: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=a_Arbol.m_Busca(v_Llave);
                    v_Maestro.seek(v_Desplazamiento);
                    v_Maestro.readInt();
                    
                    System.out.print("Sucursal de Origen: ");
                    a_cdOrigen=v_Entrada.next();
                    v_bfOrigen=new StringBuffer(a_cdOrigen);
                    v_bfOrigen.setLength(30);
                    System.out.print("Sucursal de Destino: ");
                    a_cdDestino=v_Entrada.next();
                    v_bfDestino=new StringBuffer(a_cdDestino);
                    v_bfDestino.setLength(30);
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    
                    v_Maestro.writeChars(v_bfOrigen.toString());
                    v_Maestro.writeChars(v_bfDestino.toString());
                    v_Maestro.writeFloat(a_Distancia);
                    
                    System.out.println("\n¿Desea modificar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while("1".equals(v_Opcion));
        }
    }//Fin del Método
    
    private void m_Arbol(){
        a_Arbol = new c_arbol();
        RandomAccessFile v_Indice;
        long v_apActual,v_apFinal;
        try{
            v_Indice = new RandomAccessFile("src/files/indice.dat","r");
            v_apActual=v_Indice.getFilePointer();
            v_apFinal=v_Indice.length();
            while(v_apActual!=v_apFinal){
                v_Indice.readInt();
                v_Indice.readLong();
                a_Arbol.m_Insertar(v_Indice.readInt(),v_Indice.readLong());
                v_apActual=v_Indice.getFilePointer();
            }
            v_Indice.close();
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivo");
        }
    }
}
