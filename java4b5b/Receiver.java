/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java4b5b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Receiver {
      String inputString;
    String outputString;
    String signal_string;
    String new_string;

    FileWriter fw;
    FileReader fr;
    String[] I4b = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    String[] I5b = {"11110", "01001", "10100", "10101", "01010", "01011", "01110", "01111", "10010", "10011", "10110", "10111", "11010", "11011", "11100", "11101"};

    Receiver() throws FileNotFoundException, IOException {
        fr = new FileReader("D:\\Semester_10\\Java\\Java4B5B\\src\\java4b5b\\labtemp.txt");
        fw = new FileWriter("D:\\Semester_10\\Java\\Java4B5B\\src\\java4b5b\\labout.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while (true) {
            int x = br.read();
            i++;
            if (x == -1) {
                B4b5b(inputString);
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);

            if (i == 2100) {

                B4b5b(inputString);
                i = 0;
                inputString = "";
            }
        }
        fw.close();

    }

    void B4b5b(String s) throws IOException {
        signal_string = "";
        int i, j;

        for(i=0;i < s.length(); i+=5){
           
           new_string=s.substring(i, i+5);
           for(j=0;j <16;j++){
               if (new_string.equals(I5b[j]) ) {
                 signal_string = signal_string + I4b[j]; 
                } 
            }
               
        }
        physicalLayer(signal_string);

    }

   String error_string(){
        String s="";
        for(int i=0;i<150;i++){
            s=s+"#";
            
        }
        return s;
    }
     void physicalLayer(String s) throws IOException{
        outputString = "";
        
        Random rand = new Random();
      int int_random = rand.nextInt(100);
        if(int_random<0){
            
            outputString=error_string();
            outputString = outputString.substring(4);
        datalinkLayer(outputString);
            
        }
        else{
            int i;
        for(i = 0 ; i < s.length();i+=8)
        {
            String temp = s.substring(i, i+8);
            int x = Integer.parseInt(temp, 2);
            char ch = (char) x;
            outputString = outputString + Character.toString(ch);
        }
        outputString = outputString.substring(4);
        datalinkLayer(outputString);
    }
        }
    
    void datalinkLayer(String s) throws IOException{
        outputString = s.substring(3,s.length()-3);
        networkLayer(outputString);
                
    }
    
    void networkLayer(String s) throws IOException{
        outputString = s.substring(3);
        transportLayer(outputString);
    }
    
    void transportLayer(String s) throws IOException{
        outputString = s.substring(3);
        sessionLayer(outputString);
    }
    
    
    void sessionLayer(String s) throws IOException{
        outputString = s.substring(3);
        presentationLayer(outputString);
    }
    
    void presentationLayer(String s) throws IOException{
        outputString = s.substring(3);
        applicationLayer(outputString);
    }
    
    void applicationLayer(String s) throws IOException{
        outputString = s.substring(3);
        fw.write(outputString);
    }
}
