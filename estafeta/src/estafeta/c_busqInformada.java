package estafeta;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class c_busqInformada {
    
    private int a_Llave;
    private String a_Origen;
    private String a_Destino;
    private float a_Costo1;
    private float a_Costo2;
    private Object [][] a_Grafo;
    private String [] a_G=null;
    private Scanner a_Entrada;
    private List a_Abierto=null;
    private List a_Cerrado=null;
    private StringBuffer a_sbOrigen,a_sbDestino;
    

    public void m_busquedaA(int p_Index){
        String v_EA="";
        List v_Sucesores;
        c_sucesorA v_Sucesor;
        c_tabla v_Registro;
        c_tabla v_regSucesor;
        c_busqCiegas v_Hueristica=new c_busqCiegas();
        boolean v_bdOrigen=false;
        boolean v_bdDestino=false;
        boolean v_bdAbierto=true;
        boolean v_bdCerrado=true;
        c_eliminados v_Eliminados;
        float v_Gn=0,v_Hn=0,v_Fn=0;
        m_fillGrafo();
        m_fillG();
        try{
            a_Entrada=new Scanner(System.in);
            System.out.print("\nSucursal de Origen: ");
            a_Origen=a_Entrada.next();
            a_sbOrigen=new StringBuffer(a_Origen);
            a_sbOrigen.setLength(17);
            System.out.print("Sucursal de Destino: ");
            a_Destino=a_Entrada.next();
            a_sbDestino=new StringBuffer(a_Destino);
            a_sbDestino.setLength(17);
            v_Eliminados = new c_eliminados();
            if(v_Eliminados.m_buscaEliminado(a_sbOrigen.toString()))
                for (int i = 0; i < a_G.length; i++) {
                    if(a_G[i].equals(a_sbOrigen.toString()))
                        v_bdOrigen=true;
                }
            if(v_Eliminados.m_buscaEliminado(a_sbDestino.toString()))
                for (int i = 0; i < a_G.length; i++) {
                    if(a_G[i].equals(a_sbDestino.toString()))
                        v_bdDestino=true;
                }
            if(v_bdOrigen&&v_bdDestino){
                a_Abierto=new ArrayList();
                a_Cerrado=new ArrayList();
                v_Hn=v_Hueristica.m_busquedaGrafoO(a_Origen, a_Destino, p_Index);
                v_Fn=v_Gn+v_Hn;
                v_Registro=new c_tabla(a_sbOrigen.toString(),null,v_Gn,v_Hn,v_Fn);
                a_Abierto.add(v_Registro);
                do{
                    //EA <- Primer elemento de abiertos
                    v_Registro=(c_tabla)a_Abierto.get(0);
                    v_EA=v_Registro.m_getN();
                    //Elmina EA de abiertos
                    a_Abierto.remove(0);
                    //EA a Cerrado
                    a_Cerrado.add(v_Registro);
                    //Si EA no es la meta
                    if(!v_EA.equals(a_sbDestino.toString())){
                        //Expandir nodos de EA
                        v_Sucesores = new ArrayList();
                        for (int i = 0; i < a_Grafo.length; i++) {
                            if(v_EA.equals((String)a_Grafo[i][0])){
                                float v_tempGn=(float)a_Grafo[i][p_Index];
                                float v_tempHn=v_Hueristica.m_busquedaGrafoO((String)a_Grafo[i][1], a_Destino, p_Index);
                                float v_tempFn=v_tempGn+v_tempHn;
                                v_Sucesor=new c_sucesorA((String)a_Grafo[i][1],v_tempGn,v_tempHn,v_tempFn);
                                v_Sucesores.add(v_Sucesor);
                                
                            }
                        }
                        Collections.sort(v_Sucesores, new c_ordSucesores());   
                        //Para cada sucesor q de EA
                        for (int i = 0; i < v_Sucesores.size(); i++) {
                            v_Sucesor=(c_sucesorA)v_Sucesores.get(i);
                            //Calcular f(q)
                            v_Gn=v_Registro.m_getGn()+v_Sucesor.m_getGn();
                            v_Hn=v_Hueristica.m_busquedaGrafoO(v_Sucesor.m_getN(), a_Destino, p_Index);
                            v_Fn=v_Gn+v_Hn;
                            v_regSucesor=new c_tabla(v_Sucesor.m_getN(),v_Registro.m_getN(),v_Gn,v_Hn,v_Fn);
                            v_bdAbierto=false;
                            v_bdCerrado=false;
                            for (int j = 0; j < a_Abierto.size(); j++) {
                                c_tabla v_tempRegAbierto = (c_tabla) a_Abierto.get(j);
                                if(v_tempRegAbierto.m_getN().equals(v_Sucesor.m_getN())){
                                    v_bdAbierto=true;
                                }
                            }
                            for (int j = 0; j < a_Cerrado.size(); j++) {
                                c_tabla v_tempRegCerrado = (c_tabla) a_Cerrado.get(j);
                                if(v_tempRegCerrado.m_getN().equals(v_Sucesor.m_getN())){
                                    v_bdCerrado=true;
                                }
                            }
                            
                            if(v_bdAbierto||v_bdCerrado){
                                //Si q esta en abierto o cerrado
                                if(v_bdAbierto){
                                    for (int j = 0; j < a_Abierto.size(); j++) {
                                        c_tabla v_tempRegAbierto = (c_tabla) a_Abierto.get(j);
                                        if(v_tempRegAbierto.m_getN().equals(v_Sucesor.m_getN())){
                                            //Compara el valor nueno de f(q) con el anterior
                                            if(v_tempRegAbierto.m_getFn()>v_Fn){
                                                //Colocar EA como nuevo padre de q
                                                v_tempRegAbierto.m_setPadre(v_EA);
                                                v_tempRegAbierto.m_setGn(v_Gn);
                                                v_tempRegAbierto.m_setHn(v_Hn);
                                                v_tempRegAbierto.m_setFn(v_Fn);
                                                a_Abierto.set(j,v_tempRegAbierto);
                                            }
                                        }
                                    }
                                }
                                if(v_bdCerrado){
                                    //Si esta en cerrado eliminar de cerrado y llevar a abierto
                                    for (int j = 0; j < a_Cerrado.size(); j++) {
                                        c_tabla v_tempRegCerrado = (c_tabla) a_Cerrado.get(j);
                                        if(v_tempRegCerrado.m_getN().equals(v_Sucesor.m_getN())){
                                            if(v_tempRegCerrado.m_getFn()>v_Fn){
                                                v_tempRegCerrado.m_setPadre(v_EA);
                                                v_tempRegCerrado.m_setGn(v_Gn);
                                                v_tempRegCerrado.m_setHn(v_Hn);
                                                v_tempRegCerrado.m_setFn(v_Fn);
                                                a_Abierto.add(v_tempRegCerrado);
                                                a_Cerrado.remove(j);
                                            }
                                        }
                                    }
                                }
                            }else{
                                //Si q no esta en abierto ni en cerrado, colocar en abierto
                                a_Abierto.add(v_regSucesor);
                            }
                        }
                        //Ordernaer Abierta
                        Collections.sort(a_Abierto, new c_ordAbierta());   
                    }else{
                        //Termino
                        m_imprimeCamino(a_sbDestino.toString());
                        a_Abierto.removeAll(a_Abierto);
                    }
                }while(a_Abierto.size()!=0);
            }else{
                if(!v_bdOrigen)
                    System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                if(!v_bdDestino)
                    System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
            }
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println(e.hashCode());
        }
    }
    
    private void m_imprimeCamino(String p_Destino){
        System.out.println("\n"+m_imprimeCaminoA(p_Destino));
        c_tabla v_Registro;
        for (int i = 0; i < a_Abierto.size(); i++) {
            v_Registro=(c_tabla) a_Abierto.get(i);
            if(v_Registro.m_getN().equals(p_Destino)){
                System.out.println("Costo: \u001B[31m"+v_Registro.m_getGn()+"\u001B[30m");
            }
        }
        for (int i = 0; i < a_Cerrado.size(); i++) {
            v_Registro=(c_tabla) a_Cerrado.get(i);
            if(v_Registro.m_getN().equals(p_Destino)){
                System.out.println("Costo: \u001B[31m"+v_Registro.m_getGn()+"\u001B[30m");
            }
        }
    }
    
    private String m_imprimeCaminoA(String p_Destino){
        c_eliminados v_Eliminado = new c_eliminados();
        String v_Camino="";
        c_tabla v_regAbierto;
        c_tabla v_regCerrado;
        for (int i = 0; i < a_Abierto.size(); i++) {
            v_regAbierto=(c_tabla) a_Abierto.get(i);
            if(v_regAbierto.m_getN().equals(p_Destino)){
                if(v_regAbierto.m_getPadre()!=null){
                    v_Camino=m_imprimeCaminoA(v_regAbierto.m_getPadre());
                    v_Camino=v_Camino+" ⇒ \u001B[34m"+v_Eliminado.m_buscaNodo(v_regAbierto.m_getN())+"\u001B[30m";
                }else{
                    v_Camino="\u001B[34m"+v_regAbierto.m_getN()+"\u001B[30m";
                }
            }
        }
        for (int i = 0; i < a_Cerrado.size(); i++) {
            v_regCerrado=(c_tabla) a_Cerrado.get(i);
            if(v_regCerrado.m_getN().equals(p_Destino)){
                if(v_regCerrado.m_getPadre()!=null){
                    v_Camino=m_imprimeCaminoA(v_regCerrado.m_getPadre());
                    v_Camino=v_Camino+" ⇒ \u001B[34m"+v_Eliminado.m_buscaNodo(v_regCerrado.m_getN())+"\u001B[30m";
                }else{
                    v_Camino="\u001B[34m"+v_regCerrado.m_getN()+"\u001B[30m";
                }
            }
        }
        return v_Camino;
    }
    
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
    
    
}
