/*
 * To change this license header, choose License Headers in Project Properties.
 */
package estafeta;

public class c_nodoA {
    
    private int a_Llave;
    private long a_Direccion;
    private long a_dirIndice;
    private c_nodoA a_Izquierdo;
    private c_nodoA a_Derecho;
    
    public c_nodoA(int p_Llave,long p_Direccion,long p_dirIndice, c_nodoA p_Izquierdo, c_nodoA p_Derecho){
        a_Llave = p_Llave;
        a_Direccion = p_Direccion;
        a_dirIndice =p_dirIndice;
        a_Izquierdo = p_Izquierdo;
        a_Derecho = p_Derecho;
    }
    
    public c_nodoA(int p_Llave,long p_Direccion,long p_dirIndice){
        this(p_Llave,p_Direccion,p_dirIndice,null,null);
    }
    
    public int m_getLlave(){
        return a_Llave;
    }
    
    public long m_getDireccion(){
        return a_Direccion;
    }
    
    public c_nodoA m_getIzquierdo(){
        return a_Izquierdo;
    }
    
    public c_nodoA m_getDerecho(){
        return a_Derecho;
    }
    
    public void m_setIzquierdo(c_nodoA p_Izquierdo){
        a_Izquierdo=p_Izquierdo;
    }
    
    public void m_setDerecho(c_nodoA p_Derecho){
        a_Derecho=p_Derecho;
    }
    
    public long m_getDirIndice(){
        return a_dirIndice;
    }
}
