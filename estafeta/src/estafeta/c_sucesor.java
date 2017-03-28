package estafeta;

public class c_sucesor {
    
    private int a_Sucesor;
    private float a_Peso;

    public c_sucesor(int p_Sucesor,float p_Peso){
        a_Sucesor=p_Sucesor;
        a_Peso=p_Peso;
    }
    
    public int m_getSucesor(){
        return a_Sucesor;
    }
    
    public float m_getPeso(){
        return a_Peso;
    }
}
