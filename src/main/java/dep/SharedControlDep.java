
package dep;

import br.edu.ifpb.sd.infra.OperationDao;
import br.edu.ifpb.sd.infra.OperationDaoImpl;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author natan
 * DEPRECIADO
 */
public class SharedControlDep {
    // Criando bloqueio para controlar o SharedControl
    private final Lock accessLock = new ReentrantLock(true);
    
    // Condiçoes para controlar o insert, update e delete
    private final Condition canInsert = accessLock.newCondition();
    private final Condition canUpdate = accessLock.newCondition();
    private final Condition canDelete = accessLock.newCondition();
    
    private int id;
    // Se alguma operaçao esta em ativa ou nao
    private boolean occupied = false;
    
    OperationDao operationDao = new OperationDaoImpl();
    
    public SharedControlDep(int id) {
        this.id = id;
    }
    
    public void insertSharedControl() {
        accessLock.lock();
        
        try {
            occupied = true;
            
            // Incrementando o id
            ++id;
            // Inserindo o dado no banco
            operationDao.insert(id, "operation " + id);
            System.out.println("Inseriu " + id);
            
            occupied = false;
            
            canUpdate.signal();
            
        } finally {
            accessLock.unlock();
        }
        
    }
    
    public void updateSharedControl() {
        accessLock.lock();
        
        try {
            while(occupied) {
                canUpdate.await();
            }
            
            occupied = true;
            
            operationDao.update(id);
            System.out.println("Atualizou " + id);

            
            occupied = false;
            
            canDelete.signal();
            
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            accessLock.unlock();
        }
    }
    
    public void deleteSharedControl() {
        accessLock.lock();
        
        try {
            while(occupied) {
                canDelete.await();
            }
            
            occupied = true;
            
            operationDao.delete(id);
            System.out.println("Deletou " + id);

            
            occupied = false;
            
            canInsert.signal();
            
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            accessLock.unlock();
        }
    }
}
