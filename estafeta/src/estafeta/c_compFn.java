package estafeta;

import java.util.Comparator;
import java.lang.*;

 
public class c_compFn implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
        c_sucesorA v_sucesor1=(c_sucesorA)t;
        c_sucesorA v_sucesor2=(c_sucesorA)t1;
        
        Float Fn1= v_sucesor1.m_getFn();
        Float Fn2= v_sucesor2.m_getFn();
        
        return Fn1.compareTo(Fn2);
    }
    
}

