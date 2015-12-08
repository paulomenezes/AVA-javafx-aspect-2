package com.ufrpe.ava.aspecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public aspect LoggingAspect {
	
	pointcut registrarLogin(String registro) : call(* com.ufrpe.ava.negocio.controladores.ControladorLogging.registrarLogin(..)) && args(registro);
	pointcut registrarMatricula(String registro) : call(* com.ufrpe.ava.negocio.controladores.ControladorLogging.registrarMatricula(..)) && args(registro);
	pointcut registrarPersistencia(String registro) : call(* com.ufrpe.ava.negocio.controladores.ControladorLogging.registrarPersistencia(..)) && args(registro);
	
     after(String registro) : registrarLogin(registro){
    	 
    	 try{
	    	 FileWriter fw = new FileWriter("login.txt", true );
	    	 BufferedWriter bw = new BufferedWriter( fw );
	    	 bw.write( registro +" - Realizou Login no Sistema - "+ new Date() );
	    	 bw.newLine();
	    	 bw.close();
	    	 fw.close();
    	 
    	 }catch(Exception e){
    		 
    		 System.out.println(e.getMessage());
    	 }
    }
     
     
     after(String registro) : registrarMatricula(registro){
    	 
    	 try{
	    	 FileWriter fw = new FileWriter("matriculas.txt", true );
	    	 BufferedWriter bw = new BufferedWriter( fw );
	    	 bw.write( registro +"\n"+ new Date() );
	    	 bw.newLine();
	    	 bw.close();
	    	 fw.close();
    	 
    	 }catch(Exception e){
    		 
    		 System.out.println(e.getMessage());
    	 }
    	 
     }
     
     after(String registro) : registrarPersistencia(registro){
    	 
    	 try{
	    	 FileWriter fw = new FileWriter("persistencia.txt", true );
	    	 BufferedWriter bw = new BufferedWriter( fw );
	    	 bw.write( registro +"\n"+ new Date() );
	    	 bw.newLine();
	    	 bw.close();
	    	 fw.close();
    	 
    	 }catch(Exception e){
    		 
    		 System.out.println(e.getMessage());
    	 }
    	 
     }
     

}
