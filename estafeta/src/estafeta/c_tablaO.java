package estafeta;

import java.util.List;

public class c_tablaO {
    
    private String a_n;
    private String a_Anterior;
    private float a_Costo;
    private List a_Sucesores;
    
    public c_tablaO(String p_n,String p_Anterior,float p_Costo,List p_Sucesores){
        a_n=p_n;
        a_Anterior=p_Anterior;
        a_Costo=p_Costo;
        a_Sucesores=p_Sucesores;
    }
    
    public String m_getN(){
        return a_n;
    }
    
    public String m_getAnterior(){
        return a_Anterior;
    }
    
    public float m_getCosto(){
        return a_Costo;
    }
    
    public List m_getSucesores(){
        return a_Sucesores;
    }
    
    public void m_setSucesores(List p_Sucesores){
        a_Sucesores=p_Sucesores;
    }
    
    public void m_setCosto(float p_Costo){
        a_Costo=p_Costo;
    }
    
    public void m_setAnterior(String p_Anterior){
        a_Anterior=p_Anterior;
    }
            
}
