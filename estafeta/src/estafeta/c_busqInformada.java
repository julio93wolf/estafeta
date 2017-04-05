package estafeta;

import java.io.RandomAccessFile;
import java.util.ArrayList;
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
    private StringBuffer a_sbOrigen,a_sbDestino;

    private void m_busquedaA(int p_Index){
        String v_EA;
        List v_Sucesores;
        c_sucesor v_Sucesor;
        c_abierto v_Registro=null;
        boolean v_bdOrigen=false;
        boolean v_bdDestino=false;
        c_eliminados v_Eliminados;
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
                v_Registro = new c_abierto(a_sbOrigen.toString(),null,0,0);
                a_Abierto.add(v_Registro);
                do{
                    v_Registro=(c_abierto)a_Abierto.get(0);
                    v_EA=v_Registro.m_getNodo();
                    v_Sucesores = new ArrayList();
                    for (int i = 0; i < a_Grafo.length; i++) {
                        if(v_EA.equals((String)a_Grafo[i][0])){
                            v_Sucesor=new c_sucesor((String)a_Grafo[i][1],(float)a_Grafo[i][p_Index]);
                            v_Sucesores.add(v_Sucesor);
                        }
                    }
                    for (int i = 0; i < v_Sucesores.size(); i++) {
                        v_Sucesor=(c_sucesor)v_Sucesores.get(0);
                        
                    }
                }while(a_Abierto.size()!=0);
            }else{
                if(!v_bdOrigen)
                    System.out.println("\u001B[31mError: No existe el origen\u001B[30m");
                if(!v_bdDestino)
                    System.out.println("\u001B[31mError: No existe el destino\u001B[30m");
            }
        }catch(Exception e){
            
        }
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
