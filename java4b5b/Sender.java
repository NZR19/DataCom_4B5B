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

/**
 *
 * @author Admin
 */
public class Sender {
     String inputString;
    String outputString;
    String signal_string;
    String new_string;
    FileWriter fw;
    FileReader fr;
    String[] I4b = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    String[] I5b = {"11110", "01001", "10100", "10101", "01010", "01011", "01110", "01111", "10010", "10011", "10110", "10111", "11010", "11011", "11100", "11101"};

    Sender() throws FileNotFoundException, IOException{
        fr = new FileReader("D:\\Semester_10\\Java\\Java4B5B\\src\\java4b5b\\labin.txt");
        fw = new FileWriter("D:\\Semester_10\\Java\\Java4B5B\\src\\java4b5b\\labtemp.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while(true){
            int x = br.read();
            i++;
            if(x == -1){
                applicationLayer(inputString);
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);
            if(i == 125)
            {
                applicationLayer(inputString);
                i = 0;
                inputString = "";
            }
        }
        fw.close();
    }
    
    
    void applicationLayer(String s) throws IOException{
        String mod_s = "A-H" + s;
        presentationLayer(mod_s);
    }
    
    void presentationLayer(String s) throws IOException{
        String mod_s = "P-H" + s;
        sessionLayer(mod_s);
    }
    
    void sessionLayer(String s) throws IOException{
        String mod_s = "S-H" + s;
        transportLayer(mod_s);
    }
    
    void transportLayer(String s) throws IOException{
        String mod_s = "T-H" + s;
        networkLayer(mod_s);
    }
    
    void networkLayer(String s) throws IOException{
        String mod_s = "N-H" + s;
        dataLinkLayer(mod_s);
    }
    
    void dataLinkLayer(String s) throws IOException{
        String mod_s = "D-H" + s + "D-T";
        physicalLayer(mod_s);
    }
    
    void physicalLayer(String s) throws IOException{
        String mod_s = "PH-H" + s;
        outputString = "";
        for(int i = 0 ; i < mod_s.length();i++){
            char c = mod_s.charAt(i);
            String sr = Integer.toBinaryString(c);
            int sr_len = sr.length();
            if(sr_len != 8)
            {
                for(int j = 0 ; j< 8 - sr_len ;j++){
                sr = "0" + sr;
                    //System.out.println("here" + sr);
                }
            }
            
            outputString = outputString + sr;
        }
        B4b5b(outputString);
    }
    void B4b5b(String s) throws IOException {
       signal_string = "";
       int i,j;
       for(i=0;i < s.length(); i+=4){
           
           new_string=s.substring(i, i+4);
           for(j=0;j <16;j++){
               if (new_string.equals(I4b[j]) ) {
                 signal_string = signal_string + I5b[j]; 
                } 
            }
               
        }
          fw.write(signal_string); 
       }
    
}
