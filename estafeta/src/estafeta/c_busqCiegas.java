package estafeta;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class c_busqCiegas {
    
    private int a_Llave;
    private String a_Origen;
    private String a_Destino;
    private float a_Costo1;
    private float a_Costo2;
    
    private Object [][] a_Grafo;
    private String [] a_G=null;
    private c_arbolT a_arbolT;
    private c_cola a_colaS;
    private c_cola a_Abierto;
    private c_pila a_pilaW;
    private List a_tablaA;

    /**
     * @name: m_busquedaAnchura
     * @description: Metodo para buscar por anchura la mejor ruta por zona
     */
    public void m_busquedaAnchura(){
        StringBuffer v_Origen,v_Destino;
        c_eliminados v_Eliminados;
        Scanner v_Entrada;        
        //Sea G un grafo conexo con los vertices de G ordenados
        m_fillGrafo();         
        m_fillG();
        if(a_G!=null){
            try{
                v_Entrada=new Scanner(System.in);
                //Nodo Origen
                System.out.print("\nSucursal de Origen: ");
                a_Origen=v_Entrada.next();
                v_Origen=new StringBuffer(a_Origen);
                v_Origen.setLength(17);
                //Nodo Destino
                System.out.print("Sucursal de Destino: ");
                a_Destino=v_Entrada.next();
                v_Destino=new StringBuffer(a_Destino);
                v_Destino.setLength(17);
                //Validacion de nodos de origen y destino
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
                //Si los nodos de origen y destino son validos
                if(v_bdOrigen&&v_bdDestino){
                    //Sea T el subgrafo formado por V1(Raiz)
                    a_arbolT=new c_arbolT(v_Origen.toString(),0,0);
                    //Asuma S<-(Vi)
                    a_colaS=new c_cola();
                    a_colaS.m_insertarCola(v_Origen.toString());
                    //Origen igual a destino
                    if(v_Origen.equals(v_Destino)){
                        a_colaS.m_vaciaCola();
                    }
                    //Origen diferente del destino
                    if(!v_Origen.equals(v_Destino)){
                        do{
                            //Para todo x elemento de S
                            String X=a_colaS.m_getVertice();
                            //Para todo y elemento de G
                            for (int i = 0; i < a_G.length; i++) {
                                String Y=a_G[i];
                                // (X,Y) esta en G y (X,Y) no forme un circuito en T
                                if(m_buscaGrafo(X,Y)&&a_arbolT.m_buscaArbol(Y)){
                                    //Añadir (X,Y) a T
                                    a_arbolT.m_insertaArbol(X,Y,m_buscaCosto(X,Y,2),m_buscaCosto(X,Y,3));
                                    //Añadir los hijos de S en T
                                    a_colaS.m_insertarCola(Y);
                                    //Si Y igual al destino parar
                                    if(Y.equals(v_Destino.toString())){
                                        i = a_G.length;
                                        a_colaS.m_vaciaCola();
                                    }
                                }
                            }
                            //Si todavia hay elementos en la cola sacar uno
                            if(a_colaS.m_getRaiz()!=null){
                                a_colaS.m_sacarCola();
                            }
                        //Si no se pueden agregar mas lados (X,Y) termine
                        }while(a_colaS.m_getRaiz()!=null);    
                    }
                    //Imprime el arbol generador T
                    a_arbolT.m_imprimeArbol();
                    //Imprime los costos
                    a_arbolT.m_imprimeCosto();
                }else{
                    //Si el origen no es valido
                    if(!v_bdOrigen)
                        System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                    //Si el destino no es valido
                    if(!v_bdDestino)
                        System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }//Fin del método m_busquedaAnchura
    
    /**
     * @name: m_busquedaProfundidad
     * @description: Metodo para buscar por profundad la mejor ruta
     */
    public void m_busquedaProfundidad(){
        StringBuffer v_Origen,v_Destino;
        c_eliminados v_Eliminados;
        Scanner v_Entrada;        
        //Sea G un grafo conexo con los vertices de G ordenados
        m_fillGrafo();
        m_fillG();
        if(a_G!=null){
            try{
                v_Entrada=new Scanner(System.in);
                // Nodo de Origen
                System.out.print("\nSucursal de Origen: ");
                a_Origen=v_Entrada.next();
                v_Origen=new StringBuffer(a_Origen);
                v_Origen.setLength(17);
                // Nodo de Destino
                System.out.print("Sucursal de Destino: ");
                a_Destino=v_Entrada.next();
                v_Destino=new StringBuffer(a_Destino);
                v_Destino.setLength(17);
                //Validacion de nodos de origen y destino
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
                    //Sea T el subgrafo formado por V1(Raiz)
                    a_arbolT=new c_arbolT(v_Origen.toString(),0,0);
                    //Asuma W<-(V1)
                    a_pilaW=new c_pila();
                    a_pilaW.m_insertaPila(v_Origen.toString());
                    //Origen diferente del destino
                    if(!v_Origen.equals(v_Destino)){
                        do{
                            for (int i = 0; i < a_G.length; i++) {
                                //Tomese el lado (W,K) con K mínimo
                                String W=a_pilaW.m_getVertice();
                                String K=a_G[i];
                                // (W,K) esta en G y (W,K) no forme un circuito en T
                                if(m_buscaGrafo(W,K)&&a_arbolT.m_buscaArbol(K)){
                                    // Agregar (W,K) a T
                                    a_arbolT.m_insertaArbol(W,K,m_buscaCosto(W,K,2),m_buscaCosto(W,K,3));
                                    //Tomese W<-K
                                    a_pilaW.m_insertaPila(K);
                                    i=-1;
                                    //Si K igual al destino parar
                                    if(K.equals(v_Destino.toString())){
                                        i = a_G.length;
                                        a_pilaW.m_vaciaPila();
                                    }
                                }
                            }
                            //Si todavia hay elementos en la pila sacar uno
                            if(a_pilaW.m_getRaiz()!=null){
                                a_pilaW.m_sacaPila();
                            }
                            //Si no se pueden agregar mas lados (X,Y) termine
                        }while(a_pilaW.m_getRaiz()!=null);
                    }
                    //Imprime el arbol generador T
                    a_arbolT.m_imprimeArbol();
                    //Imprime los costos
                    a_arbolT.m_imprimeCosto();
                }else{
                    //Si el origen no es valido
                    if(!v_bdOrigen)
                        System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                    //Si el destino no es valido
                    if(!v_bdDestino)
                        System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }// Fin del metodo m_busquedaProfundidad
    
    /**
     * @name: m_fillGrafo
     * @description: Metodo llenar el grafo con los registros del archivo maestro
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
    }// Fin del método m_fillGrafo
    
    /**
     * @name: m_fillG
     * @description: Metodo llenar G con los vertices del archivo maestro
     */
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
    }// Fin del método m_fillG
    
    /**
     * @name: m_buscaGrafo
     * @description: Metodo para buscar (X,Y) en el grafo
     * @param p_X : Nodo origen
     * @param p_Y : Nodo destino
     * @return Verdadero si se encuentra en el grafo
     */
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
    }// Fin del método buscaGrafo
    
    /**
     * @name: m_buscaCosto
     * @description: Metodo para obtener los costos del grafo
     * @param p_X : Nodo origen
     * @param p_Y : Nodo destino
     * @param p_Indice : Direccion de los costos
     * @return Costo
     */
    private float m_buscaCosto(String p_X,String p_Y,int p_Indice){
        float v_Costo=0;
        String v_X,v_Y;
        for (int i = 0; i < a_Grafo.length; i++) {
            v_X=(String)a_Grafo[i][0];
            v_Y=(String)a_Grafo[i][1];
            if(v_X.equals(p_X)&&v_Y.equals(p_Y))
                v_Costo=(float)a_Grafo[i][p_Indice];
        }
        return v_Costo;
    }   
    
    /**
     * @name: m_busquedaGrafoO
     * @description: Metodo para buscar la ruta mas corto por Grafo O
     * @param p_Index Direccion del costo
     */
    public void m_busquedaGrafoO(int p_Index){
        StringBuffer v_Origen,v_Destino;
        c_eliminados v_Eliminados;
        List v_Sucesores=null;
        a_tablaA = new ArrayList();
        c_tablaA v_Regristro;
        boolean v_Bandera=false;
        String v_n,v_Anterior=null;
        float v_CostoN=0;
        Scanner v_Entrada;
        m_fillGrafo();
        m_fillG();
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
                //Inicialización
                a_Abierto=new c_cola();
                // Abierta= (inicial)
                a_Abierto.m_insertarCola(v_Origen.toString());              
                v_n=a_Abierto.m_getVertice();
                v_Regristro = new c_tablaA(v_n,v_Anterior,v_CostoN,v_Sucesores);
                a_tablaA.add(v_Regristro);
                do{
                    //n=ExtraerPrimero(Abierta)
                    v_n=a_Abierto.m_getVertice();
                    //Si no es el objetivo
                    if(!v_n.equals(v_Destino.toString())){
                        //Obtiene los sucesores de n - S=Sucesores(n)
                        v_Sucesores = new ArrayList();
                        for (int i = 0; i < a_Grafo.length; i++) {
                            if(v_n.equals((String)a_Grafo[i][0])){
                                c_sucesor v_Sucesor=new c_sucesor((String)a_Grafo[i][1],(float)a_Grafo[i][p_Index]);
                                v_Sucesores.add(v_Sucesor);
                            }
                        }
                        // Añade S a la entrada de n en la tabla_a
                        for (int i = 0; i < a_tablaA.size(); i++) {
                            v_Regristro=(c_tablaA)a_tablaA.get(i);
                            if(v_Regristro.m_getN().equals(v_n)){
                                v_Regristro.m_setSucesores(v_Sucesores);
                                a_tablaA.set(i,v_Regristro);
                            }
                        }
                        //Para cada q de S 
                        for (int i = 0; i < v_Sucesores.size(); i++) {   
                            // Si q pertenece a tabla_a
                            c_sucesor v_Sucesor=(c_sucesor)v_Sucesores.get(i);
                            for (int j = 0; j < a_tablaA.size(); j++) {
                                v_Regristro=(c_tablaA)a_tablaA.get(j);
                                if(v_Regristro.m_getN().equals(v_Sucesor.m_getSucesor())){
                                    v_Bandera=true;
                                }
                            }
                            
                            if(v_Bandera){
                                //Rectificar(q,n,Coste(n,q))
                                String q=v_n;
                                String n= v_Sucesor.m_getSucesor();
                                float costoNQ= v_Sucesor.m_getCosto();
                                m_Rectificar(n,q,costoNQ,3);
                            }else{
                                //Anterior(q)=n
                                v_Anterior=v_n;
                                //Coste(inicial,n)
                                for (int j = 0; j < a_tablaA.size(); j++) {
                                    v_Regristro=(c_tablaA)a_tablaA.get(j);
                                    if(v_Anterior.equals(v_Regristro.m_getN())){
                                        v_CostoN=v_Regristro.m_getCosto();
                                    }
                                }
                                //Coste(Inicial,q)=Coste(Inicial,n)+Coste(n,q);
                                v_CostoN+=v_Sucesor.m_getCosto();
                                //Coloca q en la tabla_a
                                v_Regristro = new c_tablaA(v_Sucesor.m_getSucesor(),v_Anterior,v_CostoN,null);
                                a_tablaA.add(v_Regristro);
                                //Abierta=Mezclar(q,Abierta)
                                a_Abierto.m_insertarCola(v_Sucesor.m_getSucesor());
                            }
                            v_Bandera=false;
                        }
                        a_Abierto.m_sacarCola();
                    }else{
                        //Si el el objetivo vacia la cola e imprime la ruta
                        a_Abierto.m_vaciaCola();
                        //m_imprimeTablaA();
                        m_imprimeCaminoTablaA(v_Destino.toString());
                    }
                }while(a_Abierto.m_getRaiz()!=null);    
            }else{
                if(!v_bdOrigen)
                    System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                if(!v_bdDestino)
                    System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        System.out.println("");
    }// Fin del método m_GrafoO
    
    /**
     * @name: m_busquedaGrafoO
     * @description: Metodo para buscar la ruta mas corto por Grafo O con parametros (Usado para conseguir la heuristica en A y A*)
     * @param p_Origen Nodo Origen
     * @param p_Destino Nodo Destino
     * @param p_Index Direccion del costo
     * @return Costo de la ruta (Heuristica)
     */
    public float m_busquedaGrafoO(String p_Origen,String p_Destino,int p_Index){
        StringBuffer v_Origen,v_Destino;
        c_eliminados v_Eliminados;
        List v_Sucesores=null;
        a_tablaA = new ArrayList();
        c_tablaA v_Regristro;
        boolean v_Bandera=false;
        String v_n,v_Anterior=null;
        float v_CostoN=0;
        m_fillGrafo();
        m_fillG();
        c_tablaA v_Registro;
        try{
            a_Origen=p_Origen;
            v_Origen=new StringBuffer(a_Origen);
            v_Origen.setLength(17);
            a_Destino=p_Destino;
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
                //Inicialización
                a_Abierto=new c_cola();
                // Abierta= (inicial)
                a_Abierto.m_insertarCola(v_Origen.toString());              
                v_n=a_Abierto.m_getVertice();
                v_Regristro = new c_tablaA(v_n,v_Anterior,v_CostoN,v_Sucesores);
                a_tablaA.add(v_Regristro);
                do{
                    //n=ExtraerPrimero(Abierta)
                    v_n=a_Abierto.m_getVertice();
                    //Si no es el objetivo
                    if(!v_n.equals(v_Destino.toString())){
                        //Obtiene los sucesores de n - S=Sucesores(n)
                        v_Sucesores = new ArrayList();
                        for (int i = 0; i < a_Grafo.length; i++) {
                            if(v_n.equals((String)a_Grafo[i][0])){
                                c_sucesor v_Sucesor=new c_sucesor((String)a_Grafo[i][1],(float)a_Grafo[i][p_Index]);
                                v_Sucesores.add(v_Sucesor);
                            }
                        }
                        // Añade S a la entrada de n en la tabla_a
                        for (int i = 0; i < a_tablaA.size(); i++) {
                            v_Regristro=(c_tablaA)a_tablaA.get(i);
                            if(v_Regristro.m_getN().equals(v_n)){
                                v_Regristro.m_setSucesores(v_Sucesores);
                                a_tablaA.set(i,v_Regristro);
                            }
                        }
                        //Para cada q de S 
                        for (int i = 0; i < v_Sucesores.size(); i++) {   
                            // Si q pertenece a tabla_a
                            c_sucesor v_Sucesor=(c_sucesor)v_Sucesores.get(i);
                            for (int j = 0; j < a_tablaA.size(); j++) {
                                v_Regristro=(c_tablaA)a_tablaA.get(j);
                                if(v_Regristro.m_getN().equals(v_Sucesor.m_getSucesor())){
                                    v_Bandera=true;
                                }
                            }
                            
                            if(v_Bandera){
                                //Rectificar(q,n,Coste(n,q))
                                String q=v_n;
                                String n= v_Sucesor.m_getSucesor();
                                float costoNQ= v_Sucesor.m_getCosto();
                                m_Rectificar(n,q,costoNQ,3);
                            }else{
                                //Anterior(q)=n
                                v_Anterior=v_n;
                                //Coste(inicial,n)
                                for (int j = 0; j < a_tablaA.size(); j++) {
                                    v_Regristro=(c_tablaA)a_tablaA.get(j);
                                    if(v_Anterior.equals(v_Regristro.m_getN())){
                                        v_CostoN=v_Regristro.m_getCosto();
                                    }
                                }
                                //Coste(Inicial,q)=Coste(Inicial,n)+Coste(n,q);
                                v_CostoN+=v_Sucesor.m_getCosto();
                                //Coloca q en la tabla_a
                                v_Regristro = new c_tablaA(v_Sucesor.m_getSucesor(),v_Anterior,v_CostoN,null);
                                a_tablaA.add(v_Regristro);
                                //Abierta=Mezclar(q,Abierta)
                                a_Abierto.m_insertarCola(v_Sucesor.m_getSucesor());
                            }
                            v_Bandera=false;
                        }
                        a_Abierto.m_sacarCola();
                    }else{
                        a_Abierto.m_vaciaCola();
                        for (int i = 0; i < a_tablaA.size(); i++) {
                           c_tablaA v_retCosto=(c_tablaA) a_tablaA.get(i);
                            if(v_retCosto.m_getN().equals(v_Destino.toString())){
                                v_CostoN=v_retCosto.m_getCosto();
                            }
                        }
                    }
                }while(a_Abierto.m_getRaiz()!=null);    
            }else{
                if(!v_bdOrigen)
                    System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                if(!v_bdDestino)
                    System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return v_CostoN;
    }// Fin del método m_GrafoO
    
    
    /**
     * @name: m_Rectificar
     * @description: Metodo rectificar los costos de n
     * @param p_N  : n
     * @param p_P  : q
     * @param p_CostePN : Coste(n,q)
     * @param p_Index : Direccion del costo
     */
    private void m_Rectificar(String p_N,String p_P,float p_CostePN,int p_Index){
        float v_CosteP=0;
        float v_CosteN=0;
        float v_Temp;
        c_tablaA v_Registro;
        for (int j = 0; j < a_tablaA.size(); j++) {
            v_Registro=(c_tablaA)a_tablaA.get(j);
            if(p_P.equals(v_Registro.m_getN())){
                v_CosteP=v_Registro.m_getCosto();
            }
        }
        for (int j = 0; j < a_tablaA.size(); j++) {
            v_Registro=(c_tablaA)a_tablaA.get(j);
            if(p_N.equals(v_Registro.m_getN())){
                v_CosteN=v_Registro.m_getCosto();
            }
        }
        if((v_CosteP+p_CostePN)<v_CosteN){
            for (int j = 0; j < a_tablaA.size(); j++) {
                v_Registro=(c_tablaA)a_tablaA.get(j);
                if(p_N.equals(v_Registro.m_getN())){
                    v_Registro.m_setCosto(v_CosteP+p_CostePN);
                    v_Registro.m_setAnterior(p_P);
                    a_tablaA.set(j,v_Registro);
                }
            }
            m_rectificarLista(p_P,p_Index);
        }
    }// Fin del método m_Rectificar
    
    /**
     * @name: m_rectificarLista
     * @description: Metodo rectificar los costos modificados
     * @param p_N : n
     * @param p_Index : Direccion del costo 
     */
    private void m_rectificarLista(String p_N,int p_Index){
        List v_Sucesores = new ArrayList();
        c_tablaA v_Registro;
        String v_n=p_N;
        for (int j = 0; j < a_tablaA.size(); j++) {
            v_Registro=(c_tablaA)a_tablaA.get(j);
            if(p_N.equals(v_Registro.m_getN())){
                v_Sucesores=v_Registro.m_getSucesores();
            }
        }
        for (int i = 0; i < v_Sucesores.size(); i++) {
            c_sucesor v_Sucesor = (c_sucesor) v_Sucesores.get(i);
            m_Rectificar(v_Sucesor.m_getSucesor(),p_N,v_Sucesor.m_getCosto(),p_Index);
        }
    }// Fin del método m_rectificarLista
    
    /**
     * @name: m_imprimeTablaA
     * @description: Metodo para imprimir la TablaA
     */
    private void m_imprimeTablaA(){
        c_eliminados v_Eliminado = new c_eliminados();
        c_tablaA v_Registro;
        List v_Sucesores;
        c_sucesor v_Sucesor;
        System.out.print("\nN\t");
        System.out.print("Anterior\t");
        System.out.print("Costo\t\t");
        System.out.println("Sucesores\n");
        for (int i = 0; i < a_tablaA.size(); i++) {
            v_Registro=(c_tablaA) a_tablaA.get(i);
            System.out.print("\u001B[31m"+v_Eliminado.m_buscaNodo(v_Registro.m_getN())+"\t");
            System.out.print("\u001B[34m"+v_Eliminado.m_buscaNodo(v_Registro.m_getAnterior())+"\t\t");
            System.out.print("\u001B[30m"+v_Registro.m_getCosto()+"\t\t");
            if(v_Registro.m_getSucesores()!=null){
                v_Sucesores=v_Registro.m_getSucesores();
                for (int j = 0; j < v_Sucesores.size(); j++) {
                    v_Sucesor=(c_sucesor)v_Sucesores.get(j);
                    if(j>0){
                        System.out.println("\t\t\t\t\t\u001B[31m"+v_Eliminado.m_buscaNodo(v_Sucesor.m_getSucesor())+"\u001B[30m:\u001B[34m"+v_Sucesor.m_getCosto()+"\u001B[30m");
                    }else{
                        System.out.println("\u001B[31m"+v_Eliminado.m_buscaNodo(v_Sucesor.m_getSucesor())+"\u001B[30m:\u001B[34m"+v_Sucesor.m_getCosto()+"\u001B[30m");
                    }
                }
            }else{
                System.out.println("");
            }
        }
    }// Fin del método m_imprimeTablaA
    
    /**
     * @name: m_imprimeCaminoTablaA
     * @description: Metodo para imprimir el camino y coste del nodo inicial al destino
     * @param p_Destino : Nodo destino
     */
    private void m_imprimeCaminoTablaA(String p_Destino){
        System.out.println("\n"+m_imprimeCaminoTabla(p_Destino));
        c_tablaA v_Registro;
        for (int i = 0; i < a_tablaA.size(); i++) {
            v_Registro=(c_tablaA) a_tablaA.get(i);
            if(v_Registro.m_getN().equals(p_Destino)){
                System.out.println("Costo: \u001B[31m"+v_Registro.m_getCosto()+"\u001B[30m");
            }
        }
    }// Fin del m_imprimeCaminoTablaA
    
    /**
     * @name: m_imprimeCaminoTabla
     * @description: Metodo recursivo para recuperar el camino del nodo inicial al destino
     * @param p_Destino : Nodo destino
     * @return Cadena con el recorrido del nodo origen al destino
     */
    private String m_imprimeCaminoTabla(String p_Destino){
        c_eliminados v_Eliminado = new c_eliminados();
        String v_Camino="";
        c_tablaA v_Registro;
        for (int i = 0; i < a_tablaA.size(); i++) {
            v_Registro=(c_tablaA) a_tablaA.get(i);
            if(v_Registro.m_getN().equals(p_Destino)){
                if(v_Registro.m_getAnterior()!=null){
                    v_Camino=m_imprimeCaminoTabla(v_Registro.m_getAnterior());
                    v_Camino=v_Camino+" ⇒ \u001B[34m"+v_Eliminado.m_buscaNodo(v_Registro.m_getN())+"\u001B[30m";
                }else{
                    v_Camino="\u001B[34m"+v_Registro.m_getN()+"\u001B[30m";
                }
            }
        }
        return v_Camino;
    }
}
