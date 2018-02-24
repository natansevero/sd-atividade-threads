/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.sd.task;

import java.util.Scanner;

/**
 *
 * @author natan
 */
public class EndTask implements Runnable {

    @Override
    public void run() {
        Scanner toRead = new Scanner(System.in);
        int n = toRead.nextInt();
        System.exit(0);
        
    }
    
}
