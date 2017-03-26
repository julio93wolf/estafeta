/*
 *
 */
package estafeta;

/**
 *
 * @author Valle
 */
public class c_arbol {
   
    private c_nodoA a_Raiz=null;
    
    public void m_insertarArbol(int p_Llave, long p_Direccion, long p_dirIndice){
        c_nodoA v_Nuevo;
        c_nodoA v_Anterior = null;
        c_nodoA v_Recorrido;
          
        if(a_Raiz==null){
            a_Raiz=new c_nodoA(p_Llave, p_Direccion, p_dirIndice);
        }else{
            v_Nuevo = new c_nodoA(p_Llave, p_Direccion, p_dirIndice);
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
    
    public long m_buscaRegistro(int p_Llave){
        long v_Direccion=-1;
        c_nodoA v_Recorrido;
        c_nodoA v_Anterior;
        
        if(a_Raiz==null){
            System.out.println("No hay caminos");
        }else{
            v_Recorrido=a_Raiz;
            v_Anterior=a_Raiz;
            while(v_Anterior.m_getLlave()!=p_Llave && v_Recorrido!=null){
                v_Anterior=v_Recorrido;
                if(v_Recorrido.m_getLlave()>p_Llave){
                    v_Recorrido=v_Recorrido.m_getIzquierdo();
                }else{
                    v_Recorrido=v_Recorrido.m_getDerecho();
                }
            }
            if(v_Anterior.m_getLlave()==p_Llave){
                v_Direccion=v_Anterior.m_getDireccion();
            }else{
                System.out.println("La ruta ["+p_Llave+"] no se encuentra en el arbol");
            }
        }
        return v_Direccion;
    }
    
    public long m_buscaRegIndice(int p_Llave){
        long v_Direccion=-1;
        c_nodoA v_Recorrido;
        c_nodoA v_Anterior;
        
        if(a_Raiz==null){
            System.out.println("No hay caminos");
        }else{
            v_Recorrido=a_Raiz;
            v_Anterior=a_Raiz;
            while(v_Anterior.m_getLlave()!=p_Llave && v_Recorrido!=null){
                v_Anterior=v_Recorrido;
                if(v_Recorrido.m_getLlave()>p_Llave){
                    v_Recorrido=v_Recorrido.m_getIzquierdo();
                }else{
                    v_Recorrido=v_Recorrido.m_getDerecho();
                }
            }
            if(v_Anterior.m_getLlave()==p_Llave){
                v_Direccion=v_Anterior.m_getDirIndice();
            }else{
                System.out.println("La ruta ["+p_Llave+"] no se encuentra en el arbol");
            }
        }
        return v_Direccion;
    }
    
}
