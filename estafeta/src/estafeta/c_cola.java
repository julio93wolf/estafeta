package estafeta;

public class c_cola {
    
    private c_nodoC a_Raiz=null;    
    
    public void m_insertarCola(int p_Vertice){
        if(a_Raiz==null){
            a_Raiz=new c_nodoC(p_Vertice);
        }else{
            c_nodoC v_Temporal = a_Raiz;
            c_nodoC v_Nuevo = new c_nodoC(p_Vertice);
            while(v_Temporal.m_getSiguiente()!=null){
                v_Temporal=v_Temporal.m_getSiguiente();
            }
            v_Temporal.m_setSiguiente(v_Nuevo);
        }
    }
    
    public void m_sacarCola(){
        a_Raiz=a_Raiz.m_getSiguiente();
    }
    
    public int m_getVertice (){
        return a_Raiz.m_getVertice();
    }
    
    public c_nodoC m_getRaiz(){
        return a_Raiz;
    }
    
    public void m_vaciaCola(){
        a_Raiz=null;
    }
}
