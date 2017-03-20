package estafeta;

public class c_arbolT {
    c_nodoT a_Raiz = null;

    public c_arbolT(int p_Raiz) {
        a_Raiz=new c_nodoT(p_Raiz);
    }
    
    public void m_InsertaArbol(int p_Padre,int p_Hijo){
        m_InsertaArbol(a_Raiz,p_Padre,p_Hijo);
    }
    
    private void m_InsertaArbol(c_nodoT p_Raiz,int p_Padre,int p_Hijo){
        c_nodoT v_Raiz = p_Raiz;
        c_nodoT v_Nuevo= new c_nodoT(p_Hijo);
        int X= v_Raiz.m_getVertice();
        if(X==p_Padre){
            v_Raiz.m_insertaVertice(v_Nuevo);
        }else{
            for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
                if(v_Raiz.m_getHijo(i).m_getVertice()==p_Padre){
                    v_Raiz.m_getHijo(i).m_insertaVertice(v_Nuevo);
                }else{
                    m_InsertaArbol(v_Raiz.m_getHijo(i), p_Padre, p_Hijo);
                }
            }
        }
    }
    
    public boolean m_BuscaArbol(int p_Hijo){
        boolean v_Bandera=true;
        c_nodoT v_Raiz=a_Raiz;
        v_Bandera=m_BuscaArbol(v_Raiz, p_Hijo,v_Bandera);
        return v_Bandera;
    }
    
    private boolean m_BuscaArbol(c_nodoT p_Raiz,int p_Hijo,boolean p_Bandera){
        boolean v_Bandera=p_Bandera;
        c_nodoT v_Raiz = p_Raiz;
        int Y= v_Raiz.m_getVertice();
        if(Y==p_Hijo){
            v_Bandera=false;
        }else{
            for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
                c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
                v_Bandera=m_BuscaArbol(v_Hijo,p_Hijo,v_Bandera);
            }
        }
        return v_Bandera;
    }
    
    public void m_ImprimeArbol(){
        int v_Tabulacion=0;
        c_nodoT v_Raiz=a_Raiz;
        m_ImprimeArbol(v_Raiz,v_Tabulacion);
    }
    
    private void m_ImprimeArbol(c_nodoT p_Raiz,int p_Tabulacion){
        int v_Tabulacion=p_Tabulacion;
        for (int i = 0; i < v_Tabulacion; i++) {
            System.out.print("┃ ");
        }
        c_nodoT v_Raiz = p_Raiz;
        int v_Vertice=v_Raiz.m_getVertice();
        System.out.println("┠ "+v_Vertice);
        for (int i = 0; i < v_Raiz.m_getHijos(); i++) {
            c_nodoT v_Hijo=v_Raiz.m_getHijo(i);
            m_ImprimeArbol(v_Hijo,v_Tabulacion+1);
        }
    }
}
