package estafeta;

public class c_cola {
    
    private c_nodoL a_Raiz=null;    
    
    public void m_Insertar(String p_Vertice){
        if(a_Raiz==null){
            a_Raiz=new c_nodoL(p_Vertice);
        }else{
            c_nodoL v_Temporal = a_Raiz;
            c_nodoL v_Nuevo = new c_nodoL(p_Vertice);
            while(v_Temporal.m_getSiguiente()!=null){
                v_Temporal=v_Temporal.m_getSiguiente();
            }
            v_Temporal.m_setSiguiente(v_Nuevo);
        }
    }
    
    public void m_EliminaPrimero(){
        a_Raiz=a_Raiz.m_getSiguiente();
    }
    
    public String m_getVertice (){
        return a_Raiz.m_getVertice();
    }
    
    public c_nodoL m_getRaiz(){
        return a_Raiz;
    }
    
    public void m_Vacia(){
        a_Raiz=null;
    }
}
