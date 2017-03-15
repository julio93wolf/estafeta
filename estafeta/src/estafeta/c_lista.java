
package estafeta;

public class c_lista {
    
    c_nodoLista a_Raiz=null;
    
    public void m_Insertar(String p_Vertice){
        if(a_Raiz==null){
            a_Raiz=new c_nodoLista(p_Vertice);
        }else{
            c_nodoLista v_Temporal = a_Raiz;
            c_nodoLista v_Nuevo = new c_nodoLista(p_Vertice);
            while(v_Temporal.m_getSiguiente()!=null){
                v_Temporal=v_Temporal.m_getSiguiente();
            }
            v_Temporal.m_setSiguiente(v_Nuevo);
        }
    }
    
}
