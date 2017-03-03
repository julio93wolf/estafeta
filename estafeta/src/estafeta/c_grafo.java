package estafeta;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @name: c_grafo.java
 * @description: La clase contiene los metodos de ingreso y lectura de los nodos
 * @version 17.3.3
 * @author Valle Rodriguez Julio Cesar
 */
public class c_grafo {
    
    private int a_Llave;
    private int a_cpOrigen;
    private int a_cpDestino;
    private float a_Distancia;
    
    /**
     * @name: c_grafo
     * @description: Constructor de la clase c_grafo
     */
    c_grafo(){
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
                System.out.println("\tMenu\n");
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
                
            }
            case 3:{
                
            }
            case 4:{
                
            }
        }
    } // Fin del método m_Opcion
    
    private void m_Ingresa(){
        RandomAccessFile v_Maestro = null;
        RandomAccessFile v_Indice = null;
        Scanner v_Entrada;
        int v_Opcion=0;
        
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","rw");
            v_Indice = new RandomAccessFile("src/files/indice.dat","rw");
        }catch(Exception e){
            System.out.println("Error: No se pudieron abrir los archivos");
        }
        
        if(v_Maestro!=null&&v_Indice!=null){
            do{ 
                try{
                    v_Entrada = new Scanner(System.in);
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
                    
                    System.out.println("\n¿Desea agregar otro camino?");
                    System.out.println("[Si]=1\n[No]=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Entrada.nextInt();
                }catch(Exception e){
                    System.out.println("Error: Valor no valido");
                }
            }while(v_Opcion==1);
        }
    }
}
