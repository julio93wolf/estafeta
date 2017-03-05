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
    private int a_cpOrigen;
    private int a_cpDestino;
    private float a_Distancia;
    
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
                System.out.println("\n\tMenu\n");
                System.out.println("[1] Agrega camino");
                System.out.println("[2] Muestra caminos");
                System.out.println("[3] Busca camino");
                System.out.println("[4] Modifica camino");
                System.out.println("[5] Salir");
                System.out.print("Opcion: ");
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
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/files/indice.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudieron abrir los archivos");
        }
        if(v_Maestro!=null&&v_Indice!=null){
            do{ 
                try{
                    v_Opcion="";
                    v_Entrada = new Scanner(System.in);
                    v_Maestro.seek(v_Maestro.length());
                    v_Indice.seek(v_Indice.length());
                    v_indDireccion=v_Maestro.getFilePointer();
                    
                    System.out.print("\nLlave: ");
                    a_Llave=v_Entrada.nextInt();
                    System.out.print("Sucursal de Origen: ");
                    a_cpOrigen=v_Entrada.nextInt();
                    System.out.print("Sucursal de Destino: ");
                    a_cpDestino=v_Entrada.nextInt();
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeInt(a_cpOrigen);
                    v_Maestro.writeInt(a_cpDestino);
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
            }while(v_Opcion=="1");
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
        System.out.println("\tCaminos\n");
        System.out.print("No.\t");
        System.out.print("Origen\t");
        System.out.print("Destino\t");
        System.out.println("Distancia");
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            while(v_apActual!=v_apFinal){
                System.out.print(v_Maestro.readInt()+"\t");
                System.out.print(v_Maestro.readInt()+"\t");
                System.out.print(v_Maestro.readInt()+"\t");
                System.out.print(v_Maestro.readFloat()+"\n");
                v_apActual=v_Maestro.getFilePointer();
            }
            System.out.println("");
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivo");
        }
    }// Fin del método m_leeSecuencial
    
    private void m_leeAleatorio(){
        int v_Llave;
        String v_Opcion="";
        long v_tamRegistro=0,v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivos maestro");
        }
        if(v_Maestro!=null){
            try{
                v_Maestro.readInt();
                v_Maestro.readInt();
                v_Maestro.readInt();
                v_Maestro.readFloat();
                v_tamRegistro=v_Maestro.getFilePointer(); //Regresa direccion del Puntero
            }catch(Exception e){
                System.out.println("Error: No se pudo abrir el archivos maestro");
            }
            do{
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de camino: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistro;
                    v_Maestro.seek(v_Desplazamiento);
                    System.out.print("\nNo.\t");
                    System.out.print("Origen\t");
                    System.out.print("Destino\t");
                    System.out.println("Distancia");
                    System.out.print(v_Maestro.readInt()+"\t");
                    System.out.print(v_Maestro.readInt()+"\t");
                    System.out.print(v_Maestro.readInt()+"\t");
                    System.out.print(v_Maestro.readFloat()+"\n\n");
                    System.out.println("\n¿Desea buscar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while(v_Opcion=="1");
        }
    }
    
    private void m_Modifica(){        
        int v_Llave;
        char v_Opcion='1';
        long v_tamRegistro=0,v_Desplazamiento=0;
        RandomAccessFile v_Maestro = null;
        Scanner v_Entrada;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudo abrir el archivos maestro");
        }
        if(v_Maestro!=null){
            try{
                v_Maestro.readInt();
                v_Maestro.readInt();
                v_Maestro.readInt();
                v_Maestro.readFloat();
                v_tamRegistro=v_Maestro.getFilePointer(); //Regresa direccion del Puntero
            }catch(Exception e){
                System.out.println("Error: No se pudo abrir el archivos maestro");
            }
            do{
                try{
                    v_Entrada=new Scanner(System.in);
                    System.out.print("Ingrese el no. de camino: ");
                    v_Llave=v_Entrada.nextInt();
                    v_Desplazamiento=(v_Llave-1)*v_tamRegistro;
                    v_Maestro.seek(v_Desplazamiento);
                    v_Maestro.readInt();
                    
                    System.out.print("Sucursal de Origen: ");
                    a_cpOrigen=v_Entrada.nextInt();
                    System.out.print("Sucursal de Destino: ");
                    a_cpDestino=v_Entrada.nextInt();
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_cpOrigen);
                    v_Maestro.writeInt(a_cpDestino);
                    v_Maestro.writeFloat(a_Distancia);
                    
                    System.out.println("\n¿Desea modificar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Entrada.next().charAt(0);
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while(v_Opcion=='1');
        }
    }//Fin del Método
}
