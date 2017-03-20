
package estafeta;

public class c_nodoP {
    
    private int a_Vertice;
    private c_nodoP a_Siguiente;
    
    public c_nodoP(int p_Vertice){
        this(p_Vertice,null);
    }
    
    private c_nodoP(int p_Vertice,c_nodoP p_Siguiente){
        a_Vertice=p_Vertice;
        a_Siguiente=p_Siguiente;    
    }
    
    public void m_setSiguiente(c_nodoP p_Siguiente){
        a_Siguiente=p_Siguiente;
    }
    
    public c_nodoP m_getSiguiente(){
        return a_Siguiente;
    }
    
    public int m_getVertice(){
        return a_Vertice;
    }
}
