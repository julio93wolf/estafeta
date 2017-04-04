package estafeta;

public class c_arbolT {
    c_nodoT a_Raiz = null;

    public c_arbolT(String p_Vertice,float p_Costo1,float p_Costo2) {
        a_Raiz=new c_nodoT(p_Vertice,p_Costo1,p_Costo2);
    }
    
    public void m_insertaArbol(String p_Padre,String p_Hijo,float p_Costo1,float p_Costo2){
        m_insertaArbol(a_Raiz,p_Padre,p_Hijo,p_Costo1,p_Costo2);
    }
    
    private void m_insertaArbol(c_nodoT p_Raiz,String p_Padre,String p_Hijo,float p_Costo1,float p_Costo2){
        c_nodoT v_Raiz = p_Raiz;
        c_nodoT v_Nuevo= new c_nodoT(p_Hijo,p_Costo1,p_Costo2);
        String X= v_Raiz.m_getVertice();
        if(X.equals(p_Padre)){
            v_Raiz.m_insertaVertice(v_Nuevo);
        }else{
            for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
                if(v_Raiz.m_getHijo(i).m_getVertice().equals(p_Padre)){
                    v_Raiz.m_getHijo(i).m_insertaVertice(v_Nuevo);
                }else{
                    m_insertaArbol(v_Raiz.m_getHijo(i), p_Padre, p_Hijo,p_Costo1,p_Costo2);
                }
            }
        }
    }
    
    public boolean m_buscaArbol(String p_Vertice){
        boolean v_Bandera=true;
        c_nodoT v_Raiz=a_Raiz;
        v_Bandera=m_buscaArbol(v_Raiz, p_Vertice,v_Bandera);
        return v_Bandera;
    }
    
    private boolean m_buscaArbol(c_nodoT p_Raiz,String p_Vertice,boolean p_Bandera){
        boolean v_Bandera=p_Bandera;
        c_nodoT v_Raiz = p_Raiz;
        String Y= v_Raiz.m_getVertice();
        if(Y.equals(p_Vertice)){
            v_Bandera=false;
        }else{
            for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
                c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
                v_Bandera=m_buscaArbol(v_Hijo,p_Vertice,v_Bandera);
            }
        }
        return v_Bandera;
    }
    
    public void m_imprimeArbol(){
        int v_Tabulacion=0;
        c_nodoT v_Raiz=a_Raiz;
        System.out.println("");
        m_imprimeArbol(v_Raiz,v_Tabulacion);
    }
    
    private void m_imprimeArbol(c_nodoT p_Raiz,int p_Tabulacion){
        c_eliminados v_Eliminado = new c_eliminados();
        int v_Tabulacion=p_Tabulacion;
        for (int i = 0; i < v_Tabulacion; i++) {
            System.out.print("┃ ");
        }
        c_nodoT v_Raiz = p_Raiz;
        String v_Vertice=v_Raiz.m_getVertice();
        System.out.println("┠ \u001B[31m"+v_Eliminado.m_buscaNodo(v_Vertice)+"\u001B[30m");
        for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
            c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
            m_imprimeArbol(v_Hijo,v_Tabulacion+1);
        }
    }
            
    public void m_imprimeCosto(){
        c_nodoT v_Raiz=a_Raiz;
        System.out.println("\nCosto_1: "+m_imprimeCosto1(v_Raiz));
        System.out.println("Costo_2: "+m_imprimeCosto2(v_Raiz));
    }
    
    private float m_imprimeCosto1(c_nodoT p_Raiz){
        c_nodoT v_Raiz = p_Raiz;
        float v_Costo=0;
        v_Costo+=v_Raiz.m_getCosto1();
        for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
            c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
            v_Costo+=m_imprimeCosto1(v_Hijo);
        }
        return v_Costo;
    }
    
    private float m_imprimeCosto2(c_nodoT p_Raiz){
        c_nodoT v_Raiz = p_Raiz;
        float v_Costo=0;
        v_Costo+=v_Raiz.m_getCosto2();
        for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
            c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
            v_Costo+=m_imprimeCosto2(v_Hijo);
        }
        return v_Costo;
    }
}
