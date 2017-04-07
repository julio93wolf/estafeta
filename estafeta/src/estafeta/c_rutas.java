package estafeta;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class c_rutas {

    private int a_Llave;
    private String a_Origen;
    private String a_Destino;
    private float a_Costo1;
    private float a_Costo2;
    
    private c_arbol a_Indice;
    
    public void m_Ingresa(){
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
                    
                    System.out.print("Distancia: ");
                    a_Costo1=v_Entrada.nextFloat();
                    
                    System.out.print("Tiempo: ");
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
    public void m_leeSecuencial(){
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
                    System.out.print(a_Costo1+"\t");
                    System.out.print(a_Costo2+"\n");
                }
                v_apActual=v_Maestro.getFilePointer();
            }
            v_Maestro.close();
        }catch(Exception e){
            System.out.println("\u001B[31mError: No se pudo abrir el archivo\u001B[30m");
        }
    }// Fin del método m_leeSecuencial
        
    /**
     * @name: m_Arbol
     * @description: Método para ingresar los registros del archivo indice en un arbo 
     * para la busqueda secuencial indexada
     */
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
    }//Fin del método m_Arbol
    
    /**
     * @name: m_leeAleatorio
     * @description: Metodo para buscar un registro del archivo maestro de manera
     * aleatoria mediante busqueda secuencial indexada
     */
    public void m_leeAleatorio(){
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
                    m_Arbol();
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
                        System.out.print(a_Costo1+"\t");
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
    }//Fin del método m_leeAleatorio
    
    /**
     * @name: m_Modifica
     * @description: Método para modificar un registro del archivo maestro ubicando
     * el registro mediante busqueda secuencial indexada
     */
    public void m_Modifica(){
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
                    m_Arbol();
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
                        
                        System.out.print("Distancia: ");
                        a_Costo1=v_Entrada.nextFloat();
                        
                        System.out.print("Tiempo: ");
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
    }//Fin del método m_Modifica
    
    
    /**
     * @name: m_Elimina
     * @description: Metodo para eliminar un registro del archivo maestro marcando el registro
     */
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
                    m_Arbol();
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
                    System.out.println("\n\u001B[31mNodo Eliminado!\u001B[30m\n");
                    System.out.println("\n\u001B[31m¿Desea eliminar otra ruta?\u001B[30m");
                    System.out.println("\u001B[34m[Si]\u001B[30m=1\n\u001B[34m[No]\u001B[30m=Cualquier tecla");
                    System.out.print("Opcion: ");
                    v_Opcion=v_Opcion=v_Entrada.next();
                }catch(Exception e){
                    System.out.println("Error: Valor invalido");
                }
            }while("1".equals(v_Opcion));
        }
    }// Fin del método m_Elimina
    
    /**
     * @name: m_eliminaNodo
     * @description: Metodo para eliminar un nodo del archivo maestro, marcando todos los registros
     * que esten asociados al nodo
     */
    public void m_eliminaNodo(int p_Opcion){
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
                    
                    if(v_Eliminados.m_buscaEliminado(v_nodoBuffer.toString())){
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
                                    v_Maestro.writeInt(p_Opcion);
                                    v_Indice.writeInt(p_Opcion);
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
                        System.out.println("\n\u001B[31mNodo ["+v_nodoBuffer+"] eliminado\u001B[30m");
                    }else{
                        System.out.println("\n\u001B[31mNodo ["+v_nodoBuffer+"] no existe\u001B[30m");
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
    }//Fin del método m_eliminaNodo
    
}
