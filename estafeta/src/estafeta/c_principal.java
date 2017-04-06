package estafeta;

import java.util.Scanner;

/**
 * @name: c_camino.java
 * @description: La clase contiene los metodos de ingreso, lectura y busqueda de rutas
 * @version 17.3.3
 */
public class c_principal {
    
    private c_busqInformada o_busqInformada;
    private c_busqCiegas o_busqCiegas;
    private c_rutas o_Rutas;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_principal(){
        o_Rutas = new c_rutas();
        o_busqCiegas = new c_busqCiegas();
        o_busqInformada = new c_busqInformada();
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
                System.out.println("\u001B[34m[6]\u001B[30m Busqueda a ciegas");
                System.out.println("\u001B[34m[7]\u001B[30m Busqueda informada");
                System.out.println("\u001B[34m[8]\u001B[30m Salir");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<9)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Opcion!=8);
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
                o_Rutas.m_Ingresa();
                break;
            }
            case 2:{
                o_Rutas.m_leeSecuencial();
                break;
            }
            case 3:{
                o_Rutas.m_leeAleatorio();
                break;
            }
            case 4:{
                o_Rutas.m_Modifica();
                break;
            }
            case 5:{
                m_menuElimina();
                break;
            }
            case 6:{
                m_menuBusqCiegas();
                break;
            }
            case 7:{
                m_menuBusqIformada();
                break;
            }
        }
    } // Fin del método m_Opcion
    
    
    /**
     * 
     */
    private void m_menuElimina(){
        Scanner v_Entrada;
        boolean v_Bandera=true;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n\u001B[31m¿Desea mantener adyacencias?\u001B[30m");
                System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=2");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<3){
                    m_opcElimina(v_Opcion);
                    v_Bandera=false;
                }
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Bandera);
    }
    
    private void m_opcElimina(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_Rutas.m_eliminaNodo(-1);
                break;
            }
            case 2:{
                o_Rutas.m_eliminaNodo(-2);
                break;
            }
        }
    }
    
    /**
     * @name: m_menuBusqCiegas
     * @description: Menu de seleccion par busqueda a ciegas
     */
    private void m_menuBusqCiegas(){
        Scanner v_Entrada;
        boolean v_Bandera=true;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n");
                System.out.println("\u001B[34m[1]\u001B[30m Busqueda por zona");
                System.out.println("\u001B[34m[2]\u001B[30m Busqueda por ruta");
                System.out.println("\u001B[34m[3]\u001B[30m Busqueda por menor costo");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<4){
                    m_opcBusqCiegas(v_Opcion);
                    v_Bandera=false;
                }
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Bandera);
    }// Fin del metodo m_menuBusqCiegas
    
    private void m_opcBusqCiegas(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_busqCiegas.m_busquedaAnchura();
                break;
            }
            case 2:{
                o_busqCiegas.m_busquedaProfundidad();
                break;
            }
            case 3:{
                m_menuGrafoO();
                break;
            }
        }
    }
    
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
                    m_opcGrafoO(v_Opcion);
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
    private void m_opcGrafoO(int p_Opcion){
        switch(p_Opcion){
            case 1:{
                o_busqCiegas.m_busquedaGrafoO(2);
                break;
            }
            case 2:{
                o_busqCiegas.m_busquedaGrafoO(3);
                break;
            }
        }
    } // Fin del método m_Opcion
    
    private void m_menuBusqIformada(){
        Scanner v_Entrada;
        boolean v_Bandera=true;
        int v_Opcion=0;
        do{
            try{
                v_Entrada=new Scanner(System.in);
                System.out.println("\n");
                System.out.println("\u001B[34m[1]\u001B[30m Mejor camino");
                System.out.println("\u001B[34m[2]\u001B[30m Mejor camino con menor procesos");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<3){
                    m_menuCostoBusqInformada(v_Opcion);
                    v_Bandera=false;
                }
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Bandera);
    }
    
    private void m_menuCostoBusqInformada(int p_Opcion){
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
                    m_opcBusqInformada(p_Opcion,v_Opcion);
                    v_Bandera=false;
                }
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Bandera);
    }
    
    private void m_opcBusqInformada(int p_Opcion,int p_Costo){
        int v_Costo=(p_Costo==1)?2:3;
        switch(p_Opcion){
            case 1:{
                o_busqInformada.m_busquedaA(v_Costo);
                break;
            }
            case 2:{
                o_busqInformada.m_busquedaAEstrella(v_Costo);
                break;
            }
        }
    }
}