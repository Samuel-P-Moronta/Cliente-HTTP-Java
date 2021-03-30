package Logical;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Metodos {
    private static String URL, urlAux, inputs = "",sendRequest = "";
    private static Document doc;
    private Elements auxElement;
    private Connection.Response ConResp;

    //Validar si la URL es correcta
    public boolean urlIsCorrect(String url) {
        boolean validated = false;
        try {
            doc = Jsoup.connect(url).get();
            ConResp = Jsoup.connect(url).execute();
            validated = true;

        } catch (Exception e) {
            validated = false;
        }
        return validated;
    }

    //a) Indicar la cantidad de lineas del recurso retornado.
    public int getCantLines(String url) throws IOException {
        //Return length of line
        return Jsoup.connect(url).execute().body().split("\n").length;
    }

    //b) Indicar la cantidad de párrafos (p) que contiene el documento HTML.
    public int getAmountParagraphs(Document document) {
        //Return Amount of Paragraphs from getElementsByTag
        return document.getElementsByTag("p").size();
    }

    //c) Indicar la cantidad de imágenes (img) dentro de los párrafos que contiene el archivo HTML.
    public int getAmountImg(Document document) {
        //return size of images inside of paragraphs
        return document.getElementsByTag("p").select("img").size();
    }

    //Funcion Auxiliar cantidad de imagenes totales en general
    public int getAmountImgAux(Document document) {
        //return cant of images
        return document.getElementsByTag("img").size();
    }
    //Funcion auxiliar para que me devuelve forms
    public Elements getFormElements(Document document) {
        //return element by tag of form provide
        return document.getElementsByTag("form");
    }

    //d) indicar la cantidad de formularios (form) que contiene el HTML por
    //categorizando por el método implementado POST o GET.
    public int getForms(int x, Document document) {
        int auxPost = 0, auxGet = 0, result = 0;
        //x var to controller if is a get or post
        // Post: 1, Get: 2
        if (x == 1) {
            Elements formElements = getFormElements(document);
            int i = 0, formElementsSize = formElements.size();
            while (i < formElementsSize) {
                Element form = formElements.get(i);
                if (form.attr("method").equalsIgnoreCase("POST")) {
                    auxPost++;
                }
                //result equals current method
                result = auxPost;
                i++;
            }
        }
        //Get
        if (x == 2) {
            Elements formElements = getFormElements(document);
            int i = 0, formElementsSize = formElements.size();
            while (i < formElementsSize) {
                Element form = formElements.get(i);
                if (form.attr("method").equalsIgnoreCase("GET")) {
                    auxGet++;
                }
                //result equals current method
                result = auxGet;
                i++;
            }
        }

        return result;
    }

    //e) Para cada formulario mostrar los campos del tipo input y su
    //respectivo tipo que contiene en el documento HTML.
    public String showInputsForm(Document document) {
        int i = 0, y = 0, j = 0,n =0;
        //controller number of forms: form 1, form2, form3....
        while (j < getFormElements(document).size()) {
            inputs += "\n[Form: " + (++i) + "]\n";
            //controller loop
            int k = 0;
            if (k < getFormElements(document).get(j).getElementsByTag("input").size()) {
                do {
                    String auxInput = getFormElements(document).get(j).getElementsByTag("input").get(k).attr("type").toString();
                    inputs += "The Tag Input " + (++y) + " is: " + "[" + auxInput + "]" + "\n";
                    ++k;
                } while (k < getFormElements(document).get(j).getElementsByTag("input").size());
            }
            //Line separator
            inputs += "\n-----------------------------\n";
            ++j;
        }
        //return inputs
        return inputs;
    }
    //F)
    public String sendPostRequest(Document document) throws IOException {
        //Auxiliary function
        Elements formElements = getFormElements(document);
        int i = 0, formElementsSize = formElements.size();
        while (i < formElementsSize) {
            Element form = formElements.get(i);
            if (form.attr("method").equalsIgnoreCase("POST")) {
                //Connection resonse
                Connection.Response Response = ((FormElement) form).submit().data("asignatura", "practica1").header("matricula", "20170570").execute();
                //send post request
                sendRequest += Response.body();
            }
            i++;
        }
        //return body response
        return sendRequest;
    }
    //Samuel Peña Moronta
    //20170570

}
