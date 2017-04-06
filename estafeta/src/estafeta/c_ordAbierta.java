
package estafeta;

import java.util.Comparator;

public class c_ordAbierta implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
        c_tabla v_abierta1= (c_tabla)t;
        c_tabla v_abierta2= (c_tabla)t1;
        
        Float Fn1= v_abierta1.m_getFn();
        Float Fn2= v_abierta2.m_getFn();
        return Fn1.compareTo(Fn2);
    }
    
}
