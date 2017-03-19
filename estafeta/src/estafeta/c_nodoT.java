package estafeta;
public class c_nodoT {
    
    private int a_Vertice;
    private int a_noHijos;
    private c_nodoT a_Hijos[];
    //private c_nodoT a_HijosT[];

    public c_nodoT(int p_Vector) {
        a_Vertice=p_Vector;
    }
    
    public void m_insertaVertice(c_nodoT p_Vector){
        c_nodoT v_HijosT[];
        v_HijosT=new c_nodoT[a_noHijos+1];
        for (int i = 0; i < a_noHijos; i++) {
            v_HijosT[i]=a_Hijos[i];
        }
        //m_copiaVertices();
        v_HijosT[a_noHijos]=p_Vector;
        a_Hijos = v_HijosT;
        a_noHijos++;
    }
    
    public int m_getVertice(){
        return a_Vertice;
    }
    
    public int m_getHijos(){
        return a_noHijos;
    }
    
    public c_nodoT m_getHijo(int p_Indice){
        return a_Hijos[p_Indice];
    }
}
