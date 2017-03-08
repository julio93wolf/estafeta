/*
 *
 */
package estafeta;

/**
 *
 * @author Valle
 */
public class c_arbol {
   
    private c_nodo a_Raiz=null;
    
    public void m_Insertar(int p_Llave, long p_Direccion){
        c_nodo v_Nuevo;
        c_nodo v_Anterior = null;
        c_nodo v_Recorrido;
        
        if(a_Raiz==null){
            a_Raiz=new c_nodo(p_Llave, p_Direccion);
        }else{
            v_Nuevo = new c_nodo(p_Llave, p_Direccion);
            v_Recorrido = a_Raiz;
            while(v_Recorrido!=null){
                v_Anterior = v_Recorrido;
                if(v_Recorrido.m_getLlave()>v_Nuevo.m_getLlave()){
                    v_Recorrido=v_Recorrido.m_getIzquierdo();
                }else{
                    v_Recorrido=v_Recorrido.m_getDerecho();
                }
            }
            if(v_Anterior.m_getLlave()>v_Nuevo.m_getLlave()){
                v_Anterior.m_setIzquierdo(v_Nuevo);
            }else{
                v_Anterior.m_setDerecho(v_Nuevo);
            }
        }
    }
    
    public long m_Busca(int p_Llave){
        long v_Direccion=0;
        c_nodo v_Recorrido;
        c_nodo v_Anterior;
        
        if(a_Raiz==null){
            System.out.println("Arbol vacio");
        }else{
            v_Recorrido=a_Raiz;
            v_Anterior=a_Raiz;
            while(v_Anterior.m_getLlave()!=p_Llave && v_Recorrido!=null){
                v_Anterior=v_Recorrido;
                if(v_Recorrido.m_getLlave()>p_Llave){
                    v_Recorrido.m_getIzquierdo();
                }else{
                    v_Recorrido.m_getDerecho();
                }
            }
            if(v_Anterior.m_getLlave()==p_Llave){
                //System.out.println("El valor se encuentra en el arbol: "+v_Anterior.m_getDireccion());
                v_Direccion=v_Anterior.m_getDireccion();
            }else{
                System.out.println("La llave ["+p_Llave+"] no se encuentra en el arbol");
            }
        }
        return v_Direccion;
    }
}
