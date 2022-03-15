import java.io.*;
import java.util.Scanner;

public class Converter {
//This software have purpose to convert any CSV file to HTML
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
// Welcome the user
        System.out.println(""" 
                welcome to CSV/HTML converter
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                Enter your CSV file path
                """);
// Enter the CSV file
         String file = sc.nextLine(); //for the purpose of testing I'll user file path name as const//
        //String file = "/Users/zela/Documents/Webapp/CSV to HTML converter/src/doctorList-CSV format.csv";
        BufferedReader fileReader = null;
        final String DELIMITER = ",";
        try {
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(file));

//  =-=-=-=-=-=- ADD EXCEPTION HERE

            //Create file before using it
            System.out.println("Enter your name file");
            String name = sc.nextLine();
            File html_file = new File(name+".html");
            if(html_file.createNewFile()){
                System.out.println("File Created with: " + html_file.getName());
            }
            else{
                System.out.println("AIE AIE AIE This name seems taken");
                System.out.println("Second and last chance to enter valid File name");
                String name2 = sc.nextLine();
                html_file = new File(name2+".html");
                if(html_file.createNewFile()){
                    System.out.println("File Created with: " + html_file.getName());
                }
                else{
                    System.out.println("Another fail... System is now closing");
                    System.exit(0);
                    }
            }
//          Variables
            String line = "";
            int nb_line = 0;
            FileWriter writer= new FileWriter(html_file); //Fill writer

            while ((line = fileReader.readLine()) != null) {
//      Since we are not able to use DATA STRUCTURE, I'll be going with nb_line as index
                String[] tokens = line.split(","); //Creating token array storing every String
//      Entry and caption
                if(nb_line == 0){ //always 0 line
                    writer.write("<html>  \n  <body>  \n  <table> \n <caption>"+line+"</caption>"+ "\n");
                }
//      Attribute
                if(nb_line == 1){ //Always first line
                    writer.write("<tr>  \n");
                    for(String token: tokens){
                        writer.write("   <th>" + token + "</th> \n");
                    }
                    writer.write("</tr>  ");
                }
//      We don't the length of the values so it will not have an if
                writer.write("<tr> \n");
                for(String token: tokens){ //Loop through all token
                    if(nb_line == 0){
                        continue; //Don't store the title
                    }
                    if(nb_line == 1){
                        continue; //Don't store the attributes
                    }

//      Look for Note because that mean it's the "last" line of the CSV //      Finally SPAN

                    if(token.startsWith("Note")){
                        writer.write("</table> \n <span>"+token+"</span>  \n  </body> \n </html>");
                        break;
                    }
                    writer.write("   <td>" + token + "</td> \n ");
                }
                writer.write("</tr> \n ");

                nb_line ++; //Iterator
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

