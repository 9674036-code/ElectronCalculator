// Ethan Tang|4A|Partner: Mo Spiegel


import java.util.Map;
import java.util.Scanner;


public class ElectronCalc {
   static Map<Character, Boolean> toBinary = (Map.of('1', true, '0', false));
   static Map<Boolean, Character> backBinary = (Map.of(true, '1', false, '0'));
   static Scanner input = new Scanner(System.in);
   static Boolean cOut= false;
   static String num1="";
   static String num2="";
   static String result="";
   public static Electron electron = new Electron();
   public static void main(String[] args) {
    System.out.println("Welcome to the electron calculator that can only perform addition! Theoretically, this calculator would be the most physically compact calculator possible in its physical form, but occasionally* produces inaccurate results due to quantum tunneling caused by the small circuit and transistor design that allows it to be so compact. However, for the digital version of this calculator, the only special attribute of it is that it occasionally* produces inaccurate results. Have fun! (*frequently)");
    while (true){
       try{
       System.out.println("Please enter a positive integer:");
       int tempnum1 = Integer.parseInt (input.nextLine().replace (" ",""));
       System.out.println("Please enter another positive integer:");
       int tempnum2 = Integer.parseInt (input.nextLine().replace (" ",""));
       if (tempnum1 < 0 ||tempnum2 < 0){
           System.out.println("Invalid input");
           continue;
       }
       num1 = new StringBuilder (Integer.toBinaryString (tempnum1)).reverse().toString();
       num2 = new StringBuilder (Integer.toBinaryString (tempnum2)).reverse().toString();
       }catch(NumberFormatException e){
           System.out.println("Invalid input");
           continue;
       }


       if (num1.length()<num2.length()){
           compute(num1,num2);
       }else{
           compute(num2,num1);
       }
       System.out.println(Integer.parseInt(num1, 2) + " + " + Integer.parseInt(num2, 2) + " = " + Integer.parseInt(result, 2));
       System.out.println("Number of instances of electron tunneling: " +electron.tunNum+ " Gates that have been tunneled through: " +electron.gate);
       System.out.println("Do you want to perform another calculation? yes/no: ");
       if (!input.nextLine().equals("yes")){
           System.out.println("Ok. Bye Bye.");
           break;
       }
       result="";
       electron.resetClass();
       cOut=false;
    }
    input.close();
   }




   public static void compute(String binary1, String binary2){
      for (int i=0;i<binary1.length();i++) {
        result = result + backBinary.get (fullAdder (toBinary.get (binary1.charAt (i)), toBinary.get (binary2.charAt (i))));
      }
      if (binary1.length()==binary2.length()){
          result=result+backBinary.get(cOut);
      }else if(cOut){
          for (int i = binary1.length(); cOut||i<binary2.length()+1; i++){
             if (i==binary2.length()){
                result=result+backBinary.get(cOut);
             }else{
                result=result+backBinary.get(fullAdder(false,toBinary.get(binary2.charAt(i))));
             }
          }
      }else{
          result=result+binary2.substring(binary1.length(),binary2.length());
      }
      result=new StringBuilder(result).reverse().toString();
      num1=new StringBuilder(num1).reverse().toString();
      num2=new StringBuilder(num2).reverse().toString();
   }




   public static boolean OR(boolean i1,boolean i2){
       return (i1||i2);
   }
   public static boolean AND(boolean i1,boolean i2){
       if(!(i2 && i1)){
          return(electron.andGateTunnel(i1,i2));
       }else{
          return (i1&&i2);
       }
   }
   public static boolean NOT(boolean i1){
       if(!i1 && electron.notGateTunnel()){
          return (i1);
       }else{
          return (!i1);
       }
   }    
   public static boolean fullAdder(boolean bit1,boolean bit2){
       boolean temp=(halfAdder(halfAdder(bit1,bit2),cOut));
       if(AND(bit1,bit2)){
           cOut=true;
       }else{
           cOut=AND(cOut,OR(bit1,bit2));
       }
       return temp;
   }
   public static boolean halfAdder(boolean bit1,boolean bit2){
       boolean tempCOut=AND(bit1,bit2);
       boolean temp=OR(bit1,bit2);
       if (AND(tempCOut,temp)) {
           temp=NOT(AND(tempCOut,temp));
       }
       return(temp);
   }
}
/*
PAIR INTEGRATION NOTES


MY ROLE: Entry Point


ONE DECISION WE HAD TO AGREE ON:


We had to agree on whether to add the full computation in the constructor for the probability of quantum tunneling and compute it natively instead of using an already solved probability as a constant in the class. Ultimately we decided to include the full computation in the class and run it once in the constructor in order to retain educational value while also reducing overhead. 


ONE PROBLEM THAT APPEARED WHEN THE FILES MET:


HOW WE SOLVED IT:


ONE THING I NOW UNDERSTAND BETTER ABOUT CLASSES OR OBJECTS:
*/



