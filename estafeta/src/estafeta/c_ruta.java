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
    private int a_munOrigen;
    private int a_munDestino;
    private float a_Distancia;
    
    private c_arbol a_Indice;
    private int [][] a_Grafo;
    private int [] a_G=null;
    private c_arbolT a_arbolT;
    private c_cola a_colaS;
    
    /**
     * @name: c_caminos
     * @description: Constructor de la clase c_grafo
     */
    c_ruta(){
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
                System.out.println("\u001B[34m[6]\u001B[30m Busqueda en anchura");
                System.out.println("\u001B[34m[7]\u001B[30m Salir");
                System.out.print("Opción: ");
                v_Opcion=v_Entrada.nextInt();
                if(v_Opcion>0&&v_Opcion<8)
                    m_Opcion(v_Opcion);
                else
                    System.out.println("\u001B[31mError: Valor fuera de rango\u001B[30m");
            }catch(Exception e){
                System.out.println("\u001B[31mError: Valor invalido\u001B[30m");
            }
        }while(v_Opcion!=7);
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
            case 6:{
                m_busquedaAnchura();
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
                    a_munOrigen=v_Entrada.nextInt();
                    
                    System.out.print("Sucursal de Destino: ");
                    a_munDestino=v_Entrada.nextInt();
                    
                    System.out.print("Distancia: ");
                    a_Distancia=v_Entrada.nextFloat();
                    
                    v_Maestro.writeInt(a_Llave);
                    v_Maestro.writeInt(a_munOrigen);
                    v_Maestro.writeInt(a_munDestino);
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
        System.out.println("\n\t\u001B[31mRutas\u001B[30m\n");
        System.out.print("No.\t");
        System.out.print("Origen\t");
        System.out.print("Destino\t");
        System.out.println("Distancia");
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            
            while(v_apActual!=v_apFinal){
                
                a_Llave=v_Maestro.readInt();
                a_munOrigen= v_Maestro.readInt();
                a_munDestino= v_Maestro.readInt();
                a_Distancia=v_Maestro.readFloat();
                
                if(a_Llave>0){
                    System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                    System.out.print("\u001B[34m"+a_munOrigen+"\u001B[30m\t");
                    System.out.print("\u001B[34m"+a_munDestino+"\u001B[30m\t");
                    System.out.print(a_Distancia+"\n");
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
                    a_Indice.m_Insertar(v_Llave,v_Direccion,v_apActual);
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
                    v_Desplazamiento=a_Indice.m_buscaRuta(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        System.out.print("\nNo.\t");
                        System.out.print("Origen\t\t");
                        System.out.print("Destino\t\t");
                        System.out.println("Distancia");
                        a_Llave=v_Maestro.readInt();
                        a_munOrigen=v_Maestro.readInt();
                        a_munDestino=v_Maestro.readInt();
                        a_Distancia=v_Maestro.readFloat();
                        System.out.print("\u001B[31m"+a_Llave+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_munOrigen+"\u001B[30m\t");
                        System.out.print("\u001B[34m"+a_munDestino+"\u001B[30m\t");
                        System.out.print(a_Distancia+"\n");
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
                    
                    v_Entrada=new Scanner(System.in);
                    System.out.print("\nIngrese el no. de ruta: ");
                    v_Posicion=v_Entrada.nextInt();
                    v_Desplazamiento=a_Indice.m_buscaRuta(v_Posicion);
                    if(v_Desplazamiento>=0){
                        v_Maestro.seek(v_Desplazamiento);
                        v_Maestro.readInt();
                        
                        System.out.print("Sucursal de Origen: ");
                        a_munOrigen=v_Entrada.nextInt();
                        
                        System.out.print("Sucursal de Destino: ");
                        a_munDestino=v_Entrada.nextInt();
                        
                        System.out.print("Distancia: ");
                        a_Distancia=v_Entrada.nextFloat();

                        v_Maestro.writeInt(a_munOrigen);
                        v_Maestro.writeInt(a_munDestino);
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
    
    private void m_busquedaAnchura(){
        Scanner v_Entrada;        
        m_fillGrafo();
        m_fillG();
        if(a_G!=null){
            try{
                v_Entrada=new Scanner(System.in);
                System.out.print("\nSucursal de Origen: ");
                a_munOrigen=v_Entrada.nextInt();
                System.out.print("Sucursal de Destino: ");
                a_munDestino=v_Entrada.nextInt();
                
                
                boolean v_bdOrigen=false;
                boolean v_bdDestino=false;
                for (int i = 0; i < a_G.length; i++) {
                    if(a_G[i]==a_munOrigen)
                        v_bdOrigen=true;
                    if(a_G[i]==a_munDestino)
                        v_bdDestino=true;
                }
                if(v_bdOrigen&&v_bdDestino){
                    a_arbolT=new c_arbolT(a_munOrigen);
                    a_colaS=new c_cola();
                    a_colaS.m_Insertar(a_munOrigen);
                    if(a_munOrigen==a_munDestino){
                        a_colaS.m_Vacia();
                    }
                    do{
                        int X=a_colaS.m_getVertice();
                        for (int i = 0; i < a_G.length; i++) {
                            int Y=a_G[i];
                            if(m_buscaGrafo(X,Y)&&a_arbolT.m_Busca(Y)){
                                a_arbolT.m_Inserta(X, Y);
                                a_colaS.m_Insertar(Y);
                                if(Y==a_munDestino){
                                    i = a_G.length;
                                    a_colaS.m_Vacia();
                                }
                            }
                        }
                        if(a_colaS.m_getRaiz()!=null){
                            a_colaS.m_EliminaPrimero();
                        }
                    }while(a_colaS.m_getRaiz()!=null);
                }else{
                    if(!v_bdOrigen)
                        System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                    if(!v_bdDestino)
                        System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
                }
                System.out.println("\n");
                a_arbolT.m_Imprime();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    private void m_fillGrafo(){
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        int v_Llave;
        int v_Origen;
        int v_Destino;
        float v_Distancia;
        a_Grafo=null;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            int v_tamañoGrafo=0;
            
            while(v_apActual!=v_apFinal){
                v_Llave=v_Maestro.readInt();
                v_Origen=v_Maestro.readInt();
                v_Destino=v_Maestro.readInt();
                v_Distancia=v_Maestro.readFloat();
                if(v_Llave>0){
                    v_tamañoGrafo++;
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            a_Grafo=new int[v_tamañoGrafo][2];
            v_Maestro.seek(0);
            v_tamañoGrafo=0;
            v_apActual=v_Maestro.getFilePointer();
            while(v_apActual!=v_apFinal){
                v_Llave=v_Maestro.readInt();
                v_Origen=v_Maestro.readInt();
                v_Destino=v_Maestro.readInt();
                v_Distancia=v_Maestro.readFloat();
                if(v_Llave>0){
                    a_Grafo[v_tamañoGrafo][0]= v_Origen;
                    a_Grafo[v_tamañoGrafo][1]= v_Destino;
                    v_tamañoGrafo++;
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivos maestro\u001B[30m");
        }
    }
    
    private void m_fillG(){
        a_G=null;
        int v_Anterior=0;
        int v_Llave;
        int v_munOrigen;
        int v_munDestino;
        float v_Distancia;
        RandomAccessFile v_Maestro;
        long v_apActual,v_apFinal;
        try{
            v_Maestro = new RandomAccessFile("src/files/maestro.dat","r");
            v_apActual=v_Maestro.getFilePointer();
            v_apFinal=v_Maestro.length();
            int v_tamañoG=0;
            while(v_apActual!=v_apFinal){
                v_Llave=v_Maestro.readInt();
                v_munOrigen=v_Maestro.readInt();
                v_munDestino=v_Maestro.readInt();
                v_Distancia=v_Maestro.readFloat();
                if(v_Llave>0){
                    if(v_Anterior!=v_munOrigen){
                        v_Anterior=v_munOrigen;
                        v_tamañoG++;
                    }
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            a_G=new int[v_tamañoG];
            v_Maestro.seek(0);
            v_apActual=v_Maestro.getFilePointer();
            v_Anterior=0;
            int v_Indice=0;
            while(v_apActual!=v_apFinal){
                v_Llave=v_Maestro.readInt();
                v_munOrigen= v_Maestro.readInt();
                v_munDestino= v_Maestro.readInt();
                v_Distancia=v_Maestro.readFloat();
                if(v_Llave>0){
                    if(v_Anterior!=v_munOrigen){
                        a_G[v_Indice]=v_munOrigen;
                        v_Indice++;
                        v_Anterior=v_munOrigen;
                    }

                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
    }
    
    private boolean m_buscaGrafo(int p_X,int p_Y){
        boolean v_Bandera=false;
        for (int i = 0; i < a_Grafo.length; i++) {
            if(a_Grafo[i][0]==p_X&&a_Grafo[i][1]==p_Y)
                v_Bandera=true;
        }
        return v_Bandera;
    }   
    
}
