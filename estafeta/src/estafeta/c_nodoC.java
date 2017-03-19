package estafeta;

public class c_nodoC {
    private int a_Vertice;
    private c_nodoC a_Siguiente;
    
    public c_nodoC(int p_Vertice,c_nodoC p_Siguiente){
        a_Vertice=p_Vertice;
        a_Siguiente=p_Siguiente;    
    }
    
    public c_nodoC(int p_Vertice){
        this(p_Vertice,null);
    }
    
    public void m_setSiguiente(c_nodoC p_Siguiente){
        a_Siguiente=p_Siguiente;
    }
    
    public int m_getVertice(){
        return a_Vertice;
    }
    
    public c_nodoC m_getSiguiente(){
        return a_Siguiente;
    }
}
