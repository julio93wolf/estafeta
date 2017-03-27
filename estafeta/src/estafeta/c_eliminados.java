package estafeta;

import java.io.RandomAccessFile;

public class c_eliminados {
    
    public void m_Inserta(int p_Nodo){
        RandomAccessFile v_Eliminados=null;
        if(m_buscaEliminado(p_Nodo)){
            try{
                v_Eliminados = new RandomAccessFile("src/files/eliminados.dat","rw");
                v_Eliminados.seek(v_Eliminados.length());
                v_Eliminados.writeInt(p_Nodo);
                v_Eliminados.close();
            }catch(Exception e){}
        }
    }
    
    public boolean m_buscaEliminado(int p_Nodo){
        boolean v_Bandera=true;
        long v_apActual, v_apFinal;
        RandomAccessFile v_Eliminados=null;
        int v_Nodo;
        try{
            v_Eliminados = new RandomAccessFile("src/files/eliminados.dat","r");
        }catch(Exception e){}
        if(v_Eliminados!=null){
            try{
                v_apActual=v_Eliminados.getFilePointer();
                v_apFinal=v_Eliminados.length();
                while(v_apActual!=v_apFinal){
                    v_Nodo=v_Eliminados.readInt();
                    if(v_Nodo==p_Nodo){
                        v_Bandera=false;
                    }
                    v_apActual=v_Eliminados.getFilePointer();
                }
            }catch(Exception e){
                
            }
        }
        try{
            v_Eliminados.close();
        }catch(Exception e){}
        return v_Bandera;
    }
    
    
    public String m_buscaNodo(int p_Nodo){
        String v_Bandera=""+p_Nodo;
        long v_apActual, v_apFinal;
        RandomAccessFile v_Eliminados=null;
        int v_Nodo;
        try{
            v_Eliminados = new RandomAccessFile("src/files/eliminados.dat","r");
        }catch(Exception e){}
        if(v_Eliminados!=null){
            try{
                v_apActual=v_Eliminados.getFilePointer();
                v_apFinal=v_Eliminados.length();
                while(v_apActual!=v_apFinal){
                    v_Nodo=v_Eliminados.readInt();
                    if(v_Nodo==p_Nodo){
                        v_Bandera="*";
                    }
                    v_apActual=v_Eliminados.getFilePointer();
                }
            }catch(Exception e){
                
            }
        }
        try{
            v_Eliminados.close();
        }catch(Exception e){}
        return v_Bandera;
    }
}
