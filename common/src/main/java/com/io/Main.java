package com.io;

import java.io.*;
import java.util.Scanner;

/**
 * Author Bryan.C <br>
 * Date 2018/7/26 13:46
 */
public class Main {
  public static void main(String[] arg){
    InputStream inputStream = null;
    OutputStream outputStream = null;
    Scanner scanner=null;
    try {
      inputStream=new FileInputStream("D:\\test.txt");
      outputStream=new FileOutputStream("D:\\test1.txt");
      scanner=new Scanner(inputStream,"UTF-8");
      while (scanner.hasNextLine()){
        outputStream.write(scanner.nextLine().getBytes());
      }

      byte[] data=new byte[1024];
      int result=inputStream.read(data);
      while (result != -1){
        System.out.println(new String(data,0,result,"UTF-8"));
        result=inputStream.read(data);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        inputStream.close();
        scanner.close();
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
