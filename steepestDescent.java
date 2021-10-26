/**
 * steepestDecent
 */
import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class steepestDescent {

    public  LinkedList<Double> gradienteF1(double x_k, double y_k){
        LinkedList<Double> aplicando=new LinkedList<Double>();
        double fx=(y_k*Math.cos(x_k*y_k))+(32*x_k);
        double fy=(x_k*Math.cos(x_k*y_k))+(50*y_k);
        aplicando.add(fx);
        aplicando.add(fy);
        return aplicando;
    }
    public  LinkedList<Double> gradienteF2(double x_k, double y_k){
        LinkedList<Double> aplicando=new LinkedList<Double>();
        double fx=(-400*x_k*y_k)+(400*Math.pow(x_k, 3))+(2*x_k)-2;
        double fy=(200*y_k)-(200*Math.pow(x_k, 2));
        aplicando.add(fx);
        aplicando.add(fy);
        return aplicando;
    }



    public Double norma_gradiente(LinkedList<Double> gradiente){
        double norma=0;
        if(gradiente.size()>=2){
            for (double funciones : gradiente) {
                norma+=Math.pow(funciones, 2);
            }
        }
        return Math.sqrt(norma);
    }


    public Double obtener_x_k(double variable, double actual,double constante){
        double nuevo=actual-(constante*variable);
        return nuevo;
    }



    public static void main(String[] args) throws Exception{
        LinkedList<Double> constantes=new LinkedList<Double>();


        BufferedReader lector= new BufferedReader(new InputStreamReader(System.in));
        steepestDescent im=new steepestDescent();
        System.out.println("############- Map 2 -############");
        System.out.println("\n\t-Steepest Descent-\n");
        System.out.println("\n1) Funciòn (i)");
        System.out.println("2) Funciòn (ii)");
        System.out.println("3) Salir\n");

        System.out.print("Ingrese la opciòn que desea: ");
        int opcion=Integer.parseInt(lector.readLine());
        while(opcion!=1 && opcion!=2 && opcion!=3){
            System.out.print("\nIngrese la opciòn que desea: ");
            opcion=Integer.parseInt(lector.readLine());
        }
        if(opcion==3){
            
            System.out.println("\n\tSaliendo...");
            System.exit(0);
        
        }

        System.out.println("\n\t-1) Step-size constante");
        System.out.println("\t-2) Step-size variable\n");
        System.out.print("Ingrese el Step-size que desea: ");
        int op2=Integer.parseInt(lector.readLine());
        while(op2!=1 && op2!=2){
            System.out.print("\nIngrese el Step-size que desea: ");
            op2=Integer.parseInt(lector.readLine());
        }
        if(op2==1){
            constantes.add(0.001);
            constantes.add(0.01);
            constantes.add(0.1);
            constantes.add(0.5);
            constantes.add(1.0);
        }else{
            System.out.println("\n\t-Utilizando mètodo variable\n");
        }

        if(opcion==1){
            if(op2==1){
                for (Double constante : constantes) {
                    FileWriter datos_norma=new FileWriter("Datos"+constante+".txt");
                    double actual_x=1.15;
                    double actual_y=1.15;
                    int contador=0;
                    LinkedList<Double> vector_gradiente_aplicado=new LinkedList<Double>();
                    vector_gradiente_aplicado=im.gradienteF1(actual_x, actual_y);
                    contador++;
                    while(im.norma_gradiente(vector_gradiente_aplicado)>0 ){
                        datos_norma.write(contador+"\t\t"+im.norma_gradiente(vector_gradiente_aplicado)+"\n");
                        if(contador==1000){
                           break;
                        }
                        vector_gradiente_aplicado=im.gradienteF1(actual_x, actual_y);
                        actual_x=im.obtener_x_k(vector_gradiente_aplicado.getFirst(), actual_x, constante); 
                        actual_y=im.obtener_x_k(vector_gradiente_aplicado.getLast(), actual_y, constante);             
                        contador++;
                    }
                    System.out.println("\n\nConstante alpha ->"+constante+"\n----------------------------------------------------------------------------------------------------");
                    System.out.print("Iteraciòn: "+contador);
                    System.out.print("\t|\tPunto x_k: ["+actual_x+", "+actual_y+"]");
                    System.out.print("\t|\tDireccion: "+vector_gradiente_aplicado);
                    System.out.print("\t|\tNorma: "+im.norma_gradiente(vector_gradiente_aplicado));
                    System.out.println();
    
                }
            }else{
                    double actual_x=1.15;
                    double actual_y=1.15;
                    int contador=0;
                    double constante=0;
                    LinkedList<Double> vector_gradiente_aplicado=new LinkedList<Double>();
                    vector_gradiente_aplicado=im.gradienteF1(actual_x, actual_y);
                    contador++;
                    while(im.norma_gradiente(vector_gradiente_aplicado)>0 ){
                        if(contador==1000){
                           break;
                        }
                        constante=1/(2*(double)contador);
                        vector_gradiente_aplicado=im.gradienteF1(actual_x, actual_y);
                        actual_x=im.obtener_x_k(vector_gradiente_aplicado.getFirst(), actual_x, constante); 
                        actual_y=im.obtener_x_k(vector_gradiente_aplicado.getLast(), actual_y, constante);     

                        contador++;
                    }
                    System.out.println("\n\nConstante alpha ->"+constante+"\n----------------------------------------------------------------------------------------------------");
                    System.out.print("Iteraciòn: "+contador);
                    System.out.print("\t|\tPunto x_k: ["+actual_x+", "+actual_y+"]");
                    System.out.print("\t|\tDireccion: "+vector_gradiente_aplicado);
                    System.out.print("\t|\tNorma: "+im.norma_gradiente(vector_gradiente_aplicado));
                    System.out.println("\n\n");

            }
                 
        }else if(opcion == 2){
            if(op2==1){
                for (Double constante : constantes) {
                    FileWriter datos_norma=new FileWriter("Datos"+constante+".txt");
                    
                    double actual_x=1.15;
                    double actual_y=1.15;
                    int contador=0;
                    LinkedList<Double> vector_gradiente_aplicado=new LinkedList<Double>();
                    vector_gradiente_aplicado=im.gradienteF2(actual_x, actual_y);
                    contador++;
                    while(im.norma_gradiente(vector_gradiente_aplicado)>0 ){
                        datos_norma.write(contador+"\t\t"+im.norma_gradiente(vector_gradiente_aplicado)+"\n");
                        if(contador==1000){
                            break;
                        }
                        vector_gradiente_aplicado=im.gradienteF2(actual_x, actual_y);
                        actual_x=im.obtener_x_k(vector_gradiente_aplicado.getFirst(), actual_x, constante); 
                        actual_y=im.obtener_x_k(vector_gradiente_aplicado.getLast(), actual_y, constante);                   
                        contador++;

                    }
                    System.out.println("\n\nConstante alpha ->"+constante+"\n----------------------------------------------------------------------------------------------------");
                    System.out.print("Iteraciòn: "+contador);
                    System.out.print("\t|\tPunto x_k: ["+actual_x+", "+actual_y+"]");
                    System.out.print("\t|\tDireccion: "+vector_gradiente_aplicado);
                    System.out.print("\t|\tNorma: "+im.norma_gradiente(vector_gradiente_aplicado));
                    System.out.println();
                }

            }else{
                double actual_x=1.15;
                double actual_y=1.15;
                int contador=0;
                double constante=0;
                LinkedList<Double> vector_gradiente_aplicado=new LinkedList<Double>();
                vector_gradiente_aplicado=im.gradienteF2(actual_x, actual_y);
                contador++;
                while(im.norma_gradiente(vector_gradiente_aplicado)>0 ){
                    if(contador==1000){
                        break;
                    }
                    constante=1/(2*(double)contador);
                    vector_gradiente_aplicado=im.gradienteF2(actual_x, actual_y);
                    actual_x=im.obtener_x_k(vector_gradiente_aplicado.getFirst(), actual_x, constante); 
                    actual_y=im.obtener_x_k(vector_gradiente_aplicado.getLast(), actual_y, constante);                   
                    contador++;
                }
                System.out.println("\n\nConstante alpha ->"+constante+"\n----------------------------------------------------------------------------------------------------");
                System.out.print("Iteraciòn: "+contador);
                System.out.print("\t|\tPunto x_k: ["+actual_x+", "+actual_y+"]");
                System.out.print("\t|\tDireccion: "+vector_gradiente_aplicado);
                System.out.print("\t|\tNorma: "+im.norma_gradiente(vector_gradiente_aplicado));
                System.out.println("\n\n");

            }

        }
    }
}

