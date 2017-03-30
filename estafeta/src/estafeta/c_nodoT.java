package estafeta;
public class c_nodoT {
    
    private String a_Vertice;
    private float a_Costo1;
    private float a_Costo2;
    private int a_noHijos;
    private c_nodoT [] a_Hijos;

    public c_nodoT(String p_Vertice,float p_Costo1,float p_Costo2) {
        a_Vertice=p_Vertice;
        a_Costo1=p_Costo1;
        a_Costo2=p_Costo2;
    }
    
    public void m_insertaVertice(c_nodoT p_Vector){
        c_nodoT [] v_HijosT;
        v_HijosT=new c_nodoT[a_noHijos+1];
        for (int i = 0; i < a_noHijos; i++) {
            v_HijosT[i]=a_Hijos[i];
        }
        v_HijosT[a_noHijos]=p_Vector;
        a_Hijos = v_HijosT;
        a_noHijos++;
    }
    
    public String m_getVertice(){
        return a_Vertice;
    }
    
    public float m_getCosto1(){
        return a_Costo1;
    }
    
    public float m_getCosto2(){
        return a_Costo2;
    }
    
    public int m_getHijos(){
        return a_noHijos;
    }
    
    public c_nodoT m_getHijo(int p_Indice){
        return a_Hijos[p_Indice];
    }
}
