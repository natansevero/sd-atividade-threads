
package br.edu.ifpb.sd.infra;

/**
 *
 * @author natan
 */
public interface OperationDao {
    public void insert(int id, String name);
    public void update(int id);
    public void delete(int id);
    public int selectLastId();
}
