
package estafeta;

public class c_nodoLista {
    
    private String a_Vertice;
    private c_nodoLista a_Siguiente;
    
    public c_nodoLista(String p_Vertice,c_nodoLista p_Siguiente){
        a_Vertice=p_Vertice;
        a_Siguiente=p_Siguiente;    
    }
    
    public c_nodoLista(String p_Vertice){
        this(p_Vertice,null);
    }
    
    public void m_setSiguiente(c_nodoLista p_Siguiente){
        a_Siguiente=p_Siguiente;
    }
    
    public String m_getVertice(){
        return a_Vertice;
    }
    
    public c_nodoLista m_getSiguiente(){
        return a_Siguiente;
    }
}
