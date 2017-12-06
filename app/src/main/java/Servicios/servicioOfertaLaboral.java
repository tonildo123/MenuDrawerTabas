package Servicios;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PROGRAMACION5 on 04/12/2017.
 */

public class servicioOfertaLaboral {
    private Document doc;
    private Element selectorDiv;
    private String  s="";;

    private String urlOfertaLaboral = "https://clasificados.lagaceta.com.ar/categoria/137/pedidos-empleos";
    private static final servicioOfertaLaboral oferta = new servicioOfertaLaboral();

    public static servicioOfertaLaboral getInstance(){
        return oferta;
    }

    public ArrayList<String> laboralOf(String url){
        ArrayList<String> lista = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            if(doc == null){
                return null;
            }

            Element select = doc.getElementsByClass("resultado").first();

            Elements element3 = doc.getElementsByTag("h1");
            Elements elements1 = select.getElementsByClass("item");

            lista.add(element3.text());

            for (Element element : elements1) {
                lista.add(element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
    public String getUrlOfertaLaboral() {
        return urlOfertaLaboral;
    }

    public void setUrlOfertaLaboral(String urlOfertaLaboral) {
        this.urlOfertaLaboral = urlOfertaLaboral;
    }




}
