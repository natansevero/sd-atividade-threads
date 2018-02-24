
package br.edu.ifpb.sd.infra;

import br.edu.ifpb.sd.task.DeleteTask;
import br.edu.ifpb.sd.task.EndTask;
import br.edu.ifpb.sd.task.InsertTask;
import br.edu.ifpb.sd.task.UpdateTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author natan
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService app = Executors.newCachedThreadPool();
   
// Captura o ultimo id inserido e passa para o sharedControl
//        OperationDao operationDao = new OperationDaoImpl();
        
        SharedControl control = new SharedControl();
        
        app.execute(new InsertTask(control));
        app.execute(new UpdateTask(control));
        app.execute(new DeleteTask(control));
        app.execute(new EndTask());
        
        app.shutdown();
    }
}
