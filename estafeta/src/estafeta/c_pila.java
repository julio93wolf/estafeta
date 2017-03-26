package estafeta;

public class c_pila {
    private c_nodoP a_Raiz=null;    
    
    public void m_insertaPila(int p_Vertice){
        if(a_Raiz==null){
            a_Raiz=new c_nodoP(p_Vertice);
        }else{
            c_nodoP v_Siguiente = a_Raiz;
            c_nodoP v_Raiz = new c_nodoP(p_Vertice);
            v_Raiz.m_setSiguiente(v_Siguiente);
            a_Raiz=v_Raiz;
        }
    }
    
    public void m_sacaPila(){
        a_Raiz=a_Raiz.m_getSiguiente();
    }
    
    public int m_getVertice (){
        return a_Raiz.m_getVertice();
    }
    
    public c_nodoP m_getRaiz(){
        return a_Raiz;
    }
    
    public void m_vaciaPila(){
        a_Raiz=null;
    }
}
