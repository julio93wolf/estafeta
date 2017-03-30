package estafeta;

public class c_sucesor {
    
    private String a_Sucesor;
    private float a_Costo;

    public c_sucesor(String p_Sucesor,float p_Costo){
        a_Sucesor=p_Sucesor;
        a_Costo=p_Costo;
    }
    
    public String m_getSucesor(){
        return a_Sucesor;
    }
    
    public float m_getCosto(){
        return a_Costo;
    }
}
