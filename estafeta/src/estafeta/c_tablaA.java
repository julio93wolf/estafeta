package estafeta;

import java.util.List;

public class c_tablaA {
    
    private int a_n;
    private int a_Anterior;
    private float a_Peso;
    private List a_Sucesores;
    
    public c_tablaA(int p_n,int p_Anterior,float p_Peso,List p_Sucesores){
        a_n=p_n;
        a_Anterior=p_Anterior;
        a_Peso=p_Peso;
        a_Sucesores=p_Sucesores;
    }
    
    public int m_getN(){
        return a_n;
    }
    
    public int m_getAnterior(){
        return a_Anterior;
    }
    
    public float m_getPeso(){
        return a_Peso;
    }
    
    public List m_getSucesores(){
        return a_Sucesores;
    }
    
    public void m_setSucesores(List p_Sucesores){
        a_Sucesores=p_Sucesores;
    }
}
