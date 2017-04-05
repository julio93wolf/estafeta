/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estafeta;

/**
 *
 * @author Valle
 */
public class c_cerrado {
    private String a_Nodo;
    private String a_Padre;
    private float a_Fq;
    private float a_Gq;
    
    public c_cerrado(String p_Nodo,String p_Padre,float p_Fq,float p_Gq){
        a_Nodo=p_Nodo;
        a_Padre=p_Padre;
        a_Fq=p_Fq;
        a_Gq=p_Gq;
    }
    
    public String m_getNodo(){return a_Nodo;}
    public String m_getPadre(){return a_Padre;}
    public float m_getFq(){return a_Fq;}
    public float m_getGq(){return a_Gq;}
    
    public void m_setNodo(String p_Nodo){a_Nodo=p_Nodo;}
    public void m_setPadre(String p_Padre){a_Padre=p_Padre;}
    public void m_setFq(float p_Fq){a_Fq=p_Fq;}
    public void m_setGq(float p_Gq){a_Gq=p_Gq;}
}
