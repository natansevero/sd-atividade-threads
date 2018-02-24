/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sd.task;

import br.edu.ifpb.sd.infra.SharedControl;

/**
 *
 * @author natan
 */
public class DeleteTask implements Runnable {
    private final SharedControl control;
    
    public DeleteTask(SharedControl control) {
        this.control = control;
    }

    @Override
    public void run() {
        while(true) {
//                Thread.sleep(1000);
            control.deleteSharedControl();
        }
    }
    
    
}
