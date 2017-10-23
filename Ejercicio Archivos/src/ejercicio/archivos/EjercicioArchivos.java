/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class EjercicioArchivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*  try {
            // TODO code application logic here
            Banco bancolombia =new Banco("Bancolombia");
            bancolombia.addManager("ADMIN", 1);
            bancolombia.addCliente("felipe", 1, 1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
         */
          ArrayList<Banco> bancos = new ArrayList<>();        
        bancos=CargarBancos();
        for(Banco b:bancos){
            System.out.println(b.getName());
        }
        ArrayList<Banco>comodin=CargarManagers(bancos);
        bancos.clear();
        bancos=comodin;
        System.out.println("managers:");
        for(Banco b:bancos){
            ArrayList<Administrador>admin=b.getManagers();
            
            for(Administrador a:admin){
                System.out.println(a.getName());
            }
        }
        
        
        comodin=CargarClientes(bancos);
        //bancos.clear();
        bancos=comodin;
        System.out.println("prueba");
        for(Banco b:bancos){
            System.out.println(b.getName());
        }
        for(Banco b:bancos){
            ArrayList<Cliente>clientes=b.getClients();
            for(Cliente c:clientes){
                System.out.println("ID "+c.getId()+" Nombre "+c.getName()+" Manager "+c.NombreManager());
            
            }
            
                    }
        
        comodin=CargarCuentas(bancos);
        
        bancos=comodin;
        for(Banco b:bancos){
          ArrayList<Cuenta>cuentas=b.getAccounts();
            System.out.println("Banco"+b.getName());
          for(Cuenta c:cuentas){
              System.out.println("Id Cuenta: "+c.getId()+" propietario: "+c.NombreTitular()+" Credito: "+c.getCredit());
          
          }
        
        }
        comodin=cargarTransacciones(bancos);
        bancos=comodin;
        for(Banco b:bancos){
        ArrayList<Cuenta>cuentas=b.getAccounts();
            System.out.println("Banco"+b.getName());
          for(Cuenta c:cuentas){
              System.out.println("Id Cuenta: "+c.getId()+" propietario: "+c.NombreTitular()+" Credito: "+c.getCredit());
          
          }
        
        }
        
        System.out.println("Bienvenido");
        boolean permanecer = true;
        Scanner entrada = new Scanner(System.in);
        int opcion, id = 0,idmanager=0;
        String nombre, banco;
        Banco bank;
        File bank1;
        do {
            System.out.println("1.crear un nuevo banco");
            System.out.println("2.insertar un manager");
            System.out.println("3. insertar un cliente");
            System.out.println("4.realizar una consignacion");
            System.out.println("5. agregar una cuenta");
            opcion = entrada.nextInt();
            double monto;
            switch (opcion) {
                case 1:
                    System.out.println("inserte el nombre del banco");
                    nombre = entrada.next();
                    bancos.add(new Banco(nombre));
                    bank1 = new File("Bancos/" + nombre + ".txt");
                    if (bank1.exists()) {

                    } else {
                        try {
                            bank1.createNewFile();
                            PrintStream salida = new PrintStream(bank1);
                            salida.println(nombre);
                            salida.flush();
                            salida.close();

                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;
                case 2:
                    System.out.println(" ingrese el nombre del banco al que quiere agregar el manager");
                    banco = entrada.next();
                    System.out.println("ingrese la identificacion del manager");
                    try {
                        id = entrada.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("entrada no permitida");
                    }
                    System.out.println("ingrese el nombre del manager");
                    nombre = entrada.next();
                    for (Banco b : bancos) {
                        if (b.getName().equalsIgnoreCase(banco)) {
                            try {
                                b.addManager(nombre, id);
                            } catch (FileNotFoundException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }

                            break;
                        }

                    }
                    break;
                case 3:
                    System.out.println("ingrese el nombre del banco al que quiere agregar el cliente");
                    banco = entrada.next();
                    System.out.println("ingrese el id del cliente");
                    id = entrada.nextInt();
                    System.out.println("ingrese el id del manager");
                    idmanager=entrada.nextInt();
                    System.out.println("ingrese el nombre del cliente");
                    nombre = entrada.next();
                    for (Banco b : bancos) {
                        if (b.getName().equalsIgnoreCase(banco)) {
                            try {
                                b.addCliente(nombre,idmanager, id);
                            } catch (FileNotFoundException ex) {
                                System.out.println(ex.getMessage());
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;

                        }

                    }
                    break;
                case 4:
                    System.out.println("ingrese el nombre del banco en el que quiere consignar");
                    banco = entrada.next();
                    System.out.println("ingrese el id del cliente");
                    id = entrada.nextInt();
                    System.out.println("ingrese el monto de la consignacion");
                    monto = entrada.nextDouble();
                    for (Banco b : bancos) {
                       if (b.getName().equalsIgnoreCase(banco)) {
                            try {
                                
                                b.consignar(id, monto);
                            } catch (FileNotFoundException ex) {
                                System.out.println(ex.getMessage());
                            }

                        }
                    }
                    break;
                    case 5:
                        System.out.println("ingrese el nombre del banco");
                        banco=entrada.next();
                        System.out.println("ingrese el id del cliente que desea abrir una nueva cuenta");
                        id=entrada.nextInt();
                        for(Banco b:bancos){
                        if(b.getName().equalsIgnoreCase(banco)){
                            try {
                                b.addCuenta(id);
                                
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        
                        }
                        
                        }
            break;
                    default:
                        System.out.println("entrada no valida intente de nuevo");
            }
            System.out.println("desea continuar? y/other");
            permanecer = entrada.next().equalsIgnoreCase("y");
            
            
            
        } while (permanecer);

    }

    public static ArrayList<Banco> CargarBancos() {
        File carpeta = new File("Bancos");
        Scanner entrada;
        String nombrebanco;
        ArrayList<Banco> bancos = new ArrayList<>();
        if (carpeta.isDirectory()) {
            String[] archivos = carpeta.list();
            for (String s : archivos) {
                try {
                    entrada = new Scanner(new File("Bancos/"+s));
                    nombrebanco=entrada.next();
                    bancos.add(new Banco(nombrebanco));
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    return bancos;
    }
    public static ArrayList<Banco> CargarManagers(ArrayList<Banco>bancos){
    ArrayList<Banco> banks=new ArrayList<>();
       for(Banco b:bancos){
        try {
            b.CargarManagers();
            banks.add(b);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
       return banks;
    }
 public static ArrayList<Banco> CargarCuentas(ArrayList<Banco> bancos) {
    ArrayList<Banco>bank=new ArrayList<>();
        File cuentas=new File("Cuentas");
    File archivointerno;
    Scanner entrada=null;
    String Banco=null,NombrePropietario,cadena;
    int idcuenta=0,IdTitular=0;
    String archivos[];
    if(cuentas.isDirectory()){
    archivos=cuentas.list();
    for(String s:archivos){
    archivointerno=new File("Cuentas/"+s);
        try {
            entrada=new Scanner(archivointerno);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
      while(entrada.hasNext()){
      cadena=entrada.next();
      if(cadena.equalsIgnoreCase("Banco:")){
      Banco=entrada.next();
      
      }if(cadena.equalsIgnoreCase("IDCuenta:")){
      idcuenta=entrada.nextInt();
      
      }if(cadena.equalsIgnoreCase("Titular:")){
      NombrePropietario=entrada.next();
      
      }
      if(cadena.equalsIgnoreCase("IdTitular:")){
      IdTitular=entrada.nextInt();
      
      }
      for(Banco b:bancos){
      if(b.getName().equalsIgnoreCase(Banco)){
          try {
              b.addCuenta(IdTitular);
              
          } catch (IOException ex) {
              System.out.println(ex.getMessage());
          }
      
      }
      }
      } 
    
    
    }
    
    }
    return bancos;
    }
   public static ArrayList<Banco> cargarTransacciones(ArrayList<Banco>bancos){
  double monto=0;
  int Idcuenta=0;
       ArrayList<Banco>bank=new ArrayList<>();
       ArrayList<Cuenta>cuentas=new ArrayList<>();
   File archivointerno;
   Scanner entrada;
   String archivos[];
String cadena=null;

   File Carpeta=new File("Transaccion");
   if(Carpeta.isDirectory()){
   archivos=Carpeta.list();
   for(String s:archivos){
   archivointerno=new File("Transaccion/"+s);
       try {
           entrada=new Scanner(archivointerno);
            while(entrada.hasNext()){
            cadena=entrada.next();
                if(cadena.equalsIgnoreCase("IdCuenta:")){
               Idcuenta =entrada.nextInt();
                }
                //entrada.next();
 monto+=entrada.nextDouble();
   
   }
            
            
            for(Banco b:bancos){
            ArrayList<Cuenta>counts=b.getAccounts();
            ArrayList<Cuenta>fin=new ArrayList<>();
                /*for(int i=0;i<b.getAccounts().size();i++){
                if(b.getAccounts().get(i).getId()==Idcuenta){
                b.getAccounts().get(i).Consignar(monto);
                
                
                }
                
                }*/
               for(Cuenta c:counts){
               if(c.getId()==Idcuenta){
               c.Consignar(monto);
               fin.add(c);
               
               }
                       
               
               }
            b.setAccounts(fin);
            
            }
       } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
       }
  
   }
   }
   return bancos;
   }
    public static ArrayList<Banco> CargarClientes(ArrayList<Banco>bancos){
    ArrayList<Banco>bank=new ArrayList<>();
    ArrayList<Administrador>managers;
        File Clientes=new File("Clientes");
    File archivointerno;
    Scanner entrada;
    String archivos[]=null;
    String Banco=null;
    String Cliente=null,Manager=null;
    String cadena;
    int Id=0,IdManager=0;
    if(Clientes.isDirectory()){
    archivos=Clientes.list();
    
    }
    for(String s:archivos){
    archivointerno=new File("Clientes/"+s);
        try {
            entrada=new Scanner(archivointerno);
            while(entrada.hasNext()){
            cadena=entrada.next();
                if(cadena.equalsIgnoreCase("Banco:")){
            Banco=entrada.next();
            }if(cadena.equalsIgnoreCase("NombredelCliente:")){
            Cliente=entrada.next();
            }if(cadena.equalsIgnoreCase("Id:")){
           Id=entrada.nextInt();
            }if(cadena.equalsIgnoreCase("Manager:")){
            Manager=entrada.next();
            }
            
            }
            for(Banco b:bancos){
            managers=b.getManagers();
            for(Administrador a:managers){
            if(a.getName().equalsIgnoreCase(Manager))
            IdManager=a.getId();
            
             
            }
            }
            for(Banco b:bancos){
            if(b.getName().equalsIgnoreCase(Banco)){
                try {
                    b.addCliente(Cliente,IdManager,Id);
                    
                } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                }
            
            }
            
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }
    
    return bancos;
    }
}

