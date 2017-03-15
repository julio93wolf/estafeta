package estafeta;
public class c_nodoT {
    
    private String a_Vertice;
    private int a_noHijos;
    private c_nodoT a_Hijos[];
    private c_nodoT a_HijosT[];

    public c_nodoT(String p_Vector) {
        a_Vertice=p_Vector;
    }
    
    private void m_copiaVertices(){
        a_HijosT=new c_nodoT[a_noHijos+1];
        for (int i = 0; i < a_noHijos; i++) {
            a_HijosT[i]=a_Hijos[i];
        }
    }
    
    public void m_aumentaVertices(c_nodoT p_Vector){
        m_copiaVertices();
        a_HijosT[a_noHijos]=p_Vector;
        a_Hijos = a_HijosT;
        a_noHijos++;
    }
    
    public String m_getVertice(){
        return a_Vertice;
    }
    
    public int m_getHijos(){
        return a_noHijos;
    }
    
    public c_nodoT m_getHijo(int p_Indice){
        return a_Hijos[p_Indice];
    }
}
