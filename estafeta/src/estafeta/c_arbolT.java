package estafeta;

public class c_arbolT {
    c_nodoT a_Raiz = null;

    public c_arbolT(String p_Raiz) {
        a_Raiz=new c_nodoT(p_Raiz);
    }
    
    public void m_Inserta(String p_Padre,String p_Hijo){
        m_Inserta(a_Raiz,p_Padre,p_Hijo);
    }
    
    private void m_Inserta(c_nodoT p_Temporal,String p_Padre,String p_Hijo){
        c_nodoT v_Temporal = p_Temporal;
        c_nodoT v_Nuevo= new c_nodoT(p_Hijo);
        String X= v_Temporal.m_getVertice();
        if(X.equals(p_Padre)){
            v_Temporal.m_aumentaVertices(v_Nuevo);
        }else{
            for (int i = 0; i < v_Temporal.m_getHijos(); i++) {
                if(v_Temporal.m_getHijo(i).m_getVertice().equals(p_Padre)){
                    v_Temporal.m_getHijo(i).m_aumentaVertices(v_Nuevo);
                }else{
                    m_Inserta(p_Temporal.m_getHijo(i), p_Padre, p_Hijo);
                }
            }
        }
        System.out.println("");
    }
    
    public boolean m_Busca(String p_Hijo){
        boolean v_Bandera=true;
        c_nodoT v_Temporal=a_Raiz;
        v_Bandera=m_Busca(v_Temporal, p_Hijo);
        return v_Bandera;
    }
    
    private boolean m_Busca(c_nodoT p_Temporal,String p_Hijo){
        boolean v_Bandera=true;
        c_nodoT v_Temporal = p_Temporal;
        String X= v_Temporal.m_getVertice();
        if(X.equals(p_Hijo)){
            v_Bandera=false;
        }else{
            for (int i = 0; i < v_Temporal.m_getHijos(); i++) {
                if(v_Temporal.m_getHijo(i).m_getVertice().equals(p_Hijo)){
                    v_Bandera=false;
                }else{
                    v_Bandera=m_Busca(p_Temporal.m_getHijo(i),p_Hijo);
                }
            }
        }
        return v_Bandera;
    }
}
