/*
 * To change this license header, choose License Headers in Project Properties.
 */
package estafeta;

public class c_nodo {
    
    private int a_Llave;
    private long a_Direccion;
    private c_nodo a_Izquierdo;
    private c_nodo a_Derecho;
    
    public c_nodo(int p_Llave,long p_Direccion, c_nodo p_Izquierdo, c_nodo p_Derecho){
        a_Llave = p_Llave;
        a_Direccion = p_Direccion;
        a_Izquierdo = p_Izquierdo;
        a_Derecho = p_Derecho;
    }
    
    public c_nodo(int p_Llave,long p_Direccion){
        this(p_Llave,p_Direccion,null,null);
    }
    
    public int m_getLlave(){
        return a_Llave;
    }
    
    public long m_getDireccion(){
        return a_Direccion;
    }
    
    public c_nodo m_getIzquierdo(){
        return a_Izquierdo;
    }
    
    public c_nodo m_getDerecho(){
        return a_Derecho;
    }
    
    public void m_setIzquierdo(c_nodo p_Izquierdo){
        a_Izquierdo=p_Izquierdo;
    }
    
    public void m_setDerecho(c_nodo p_Derecho){
        a_Derecho=p_Derecho;
    }
}
