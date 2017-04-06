package estafeta;

public class c_sucesorA{
    
    private String a_N;
    private float a_Gn;
    private float a_Hn;
    private float a_Fn;

    public c_sucesorA(String p_N,float p_Gn,float p_Hn,float p_Fn) {
        a_N=p_N;
        a_Gn=p_Gn;
        a_Hn=p_Hn;
        a_Fn=p_Fn;
    }
    
    public String m_getN(){return a_N;}
    public float m_getGn(){return a_Gn;}
    public float m_getHn(){return a_Hn;}
    public float m_getFn(){return a_Fn;}
    
    public void m_setN(String p_N){a_N=p_N;}
    public void m_setGn(float p_Gn){a_Gn=p_Gn;}
    public void m_setHn(float p_Hn){a_Hn=p_Hn;}
    public void m_setFn(float p_Fn){a_Fn=p_Fn;}

}
