package Logical;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String url = "";
    public static void main(String[] args) throws IOException {
        Metodos testing = new Metodos();
        System.out.println("_______________________________________________________");
        System.out.println("\n| NOMBRE        |  MATRICULA/ID      | PRACTICA        | ");
        System.out.println("-------------------------------------------------------- ");
        System.out.println("\n| SAMUEL PEÑA   |  20170570/10131492 |  CLIENTE-HTTP   | ");
        System.out.println("-------------------------------------------------------- ");
        System.out.println("Insert the URL of the page: ");
        Scanner sc = new Scanner(System.in);
        url = sc.nextLine();
        //Checking if an url provide is correct or not
        if (testing.urlIsCorrect(url) == true) {
            Document document = Jsoup.connect(url).get();
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("\nA-) Cant of line returned from URL:"+"\n" + url + " [" + testing.getCantLines(url) + "]");
            System.out.println("\nB-) Amount of Paragraph: "+ " [" + testing.getAmountParagraphs(document) + "]");
            System.out.println("\nC-) Images inside Paragraphs: "+ " [" + testing.getAmountImg(document) + "]"+"\nTotal Amount of Images :"+testing.getAmountImgAux(document));
            System.out.println("\nD-) The Forms: "+ testing.getFormElements(document).size()+" ["+"POST: "+testing.getForms(1,document)+"]"+"["+"GET: "+testing.getForms(2,document)+"]");
            System.out.println("\nE-) The Forms inputs: "+ testing.showInputsForm(document));
            System.out.println("\nF-) The server response: "+testing.sendPostRequest(document));

        } else {
            System.out.println("The Url: " + url + " is invalid, try again");
        }
    }
}
