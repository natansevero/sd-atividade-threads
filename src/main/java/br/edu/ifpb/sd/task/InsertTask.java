
package br.edu.ifpb.sd.task;

import br.edu.ifpb.sd.infra.SharedControl;

/**
 *
 * @author natan
 */
public class InsertTask implements Runnable {

    private final SharedControl control;
    
    public InsertTask(SharedControl control) {
        this.control = control;
    }
    
    @Override
    public void run() {
        
            while(true) {
//                    Thread.sleep(1000);
                control.insertSharedControl();
            }
        
    }
    
}
