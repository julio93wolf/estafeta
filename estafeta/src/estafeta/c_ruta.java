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
      
    private c_busquedas o_busquedas;
    private c_rutas o_rutas;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_ruta(){
        o_rutas = new c_rutas();
        o_busquedas = new c_busquedas();
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
        
        switch(p_Opcion){
            case 1:{
                o_rutas.m_Ingresa();
                break;
            }
            case 2:{
                o_rutas.m_leeSecuencial();
                break;
            }
            case 3:{
                o_rutas.m_leeAleatorio();
                break;
            }
            case 4:{
                o_rutas.m_Modifica();
                break;
            }
            case 5:{
                o_rutas.m_eliminaNodo();
                break;
            }
            case 6:{
                o_busquedas.m_busquedaAnchura();
                break;
            }
            case 7:{
                o_busquedas.m_busquedaProfundidad();
                break;
            }
            case 8:{
                m_menuGrafoO();
                break;
            }
        }
    } // Fin del método m_Opcion
    
    
    
    /**
     * @name: m_menuGrafoO
     * @description: Menu se seleccion para el metodo de busqueda de GrafoO
     */
    private void m_menuGrafoO(){
        Scanner v_Entrada;
        boolean v_Bandera=true;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n");
                System.out.println("\u001B[34m[1]\u001B[30m Menor Costo_1");
                System.out.println("\u001B[34m[2]\u001B[30m Menor Costo_2");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<3){
                    m_opcionGrafoO(v_Opcion);
                    v_Bandera=false;
                }
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Bandera);
    }
    
    /**
     * @name: m_opcionGrafoO
     * @description: Metodo que ingresa la opcion elegida en el menu de busqueda
     * GrafoO
     * @param p_Opcion Direccion del Costo
     */
    private void m_opcionGrafoO(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_busquedas.m_busquedaGrafoO(2);
                break;
            }
            case 2:{
                o_busquedas.m_busquedaGrafoO(3);
                break;
            }
        }
    } // Fin del método m_Opcion
}
