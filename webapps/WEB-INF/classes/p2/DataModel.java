package p2;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
//import java.io.IOException;
//import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
//import java.util.Deque;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedList;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
import java.util.Collections;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Node;

public class DataModel {


//Constants:

    private static final String initialDocument="https://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml";
    private static final String initialURL="https://alberto.gil.webs.uvigo.es/SINT/22-23/";
    public static ArrayList<String> fatalErrorsURL = new ArrayList<String>();
    public static ArrayList<String> errorsURL = new ArrayList<String>();

    
//Map of documents:
    static HashMap<String,Document> map = new HashMap<String,Document>();
    static Document doc_fich =null;
//main parser method
    public static void parser() throws Exception {

//lists to store the documents already parsed, and the documents on queu
        LinkedList<String> queu= new LinkedList<String>();
        LinkedList<String> done= new LinkedList<String>();

        DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder= dBuilderFactory.newDocumentBuilder();
        
        
        queu.add(initialDocument);
        done.add("https://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml");

        try {
            doc_fich=dBuilder.parse("http://localhost:7185/sint185/fich.xml");
            
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\nerror fich\n\n\n\n\n\n\n\n");
        }
        //this methods parses the documents on queu until there is nothing left in the list
        while(!queu.isEmpty()){
//I declare the parameters
            NodeList nlyear = null;
            String year=null;
            Document doc = null;
            String docURL=null;
            String nameXML=null;

            try {
                //I get the document url
                docURL = queu.pop();
                doc=dBuilder.parse(docURL);//I parse the document with the document builder
                nlyear = doc.getElementsByTagName("Year");//I get the year to check if it is an error file
                Element yearElement = (Element) nlyear.item(0);
                year = yearElement.getTextContent().trim();
                Integer yearNumber = Integer.parseInt(year);
                if(yearNumber>2021 || yearNumber<1980){//check betwwen the range
                    errorsURL.add(docURL);
                    done.add(docURL);
                    continue;//I add the document to the errors list, to the done list(to avoid parsing it again)
                }                //Icontinue to the top of the loop to keep parsing the queu
                else{
                map.put(year,doc);//if there is no error Iput the docuemtn in the map and in the done list
                }
                done.add(docURL);
                

                NodeList nlXMList = doc.getElementsByTagName("MuML");//I search the nodes with MuMl name
		        Element elemXML;
                
                
		        
		        for (int x = 0; x < nlXMList.getLength(); x++) {//loop to check the different elements of the node list
			        elemXML = (Element)nlXMList.item(x);
			        nameXML = elemXML.getTextContent().trim();//here Ihave the text of the MuML element
                    if((!done.contains(initialURL + nameXML))){//I check if it is already parsed or if it is already in the queu
                        if(!queu.contains(initialURL + nameXML)){
                            queu.add(initialURL + nameXML);}//if it isnt, Iadd it to the queu list
                        }
                        
		        }
            } catch (SAXException e) {//if the file is not weel formed this excpetion is thrown when it is parsed
                fatalErrorsURL.add(docURL);//I add it to the fatal error list and the actual document to the done list
                done.add(docURL);
                continue;
            }
            

        }
        
    } 

    public static ArrayList<String> getQ1Countries(){

        ArrayList<String> countries = new ArrayList<>();//list of countries to be returned

        for (Document xmlDocument : map.values()) {//I go throuhg each document in the map
          NodeList countryNodes = xmlDocument.getElementsByTagName("Country"); //I get the countries elements and the Iget the text of the element
    
          for (int i = 0; i < countryNodes.getLength(); i++) {
            Element countryElement = (Element) countryNodes.item(i);
    
            String country = countryElement.getTextContent().trim();
            if(!countries.contains(country)) {countries.add(country);}//if it is not in the list, I add it
          }
        }
        Collections.sort(countries, (s1, s2) -> s2.compareTo(s1));//I sort the list in reverse alphabetical order
       
        return countries;
    }

    public static String getLive(){
        String live=null;
        NodeList liveNodes=doc_fich.getElementsByTagName("live");
        Element liveElement = (Element) liveNodes.item(0);
        live=liveElement.getTextContent().trim();
        return live;
    }

    public static String getComment(){


        String comment =null;

        NodeList nl1=doc_fich.getElementsByTagName("enero");
        Element e1 = (Element) nl1.item(0);
        System.out.println("hola");
        try{
            NodeList nl = e1.getChildNodes();//this code checks the chldnodes of the album and picks the text node, the review
            for(int i=0;i<nl.getLength();i++){
                if(nl.item(i).getNodeType()==Node.TEXT_NODE){
                    String textContent = nl.item(i).getTextContent();
                    if (!textContent.trim().isEmpty()) {//this is to filter escape and blank carachters
                        comment = textContent.trim();
                        break;
                    }
                }
            }
        }catch(Exception e){
            System.out.print((e.getStackTrace()));
        }
        return comment;
    }

    public static String getLg(){

        String lg=null;

        NodeList lgNodes=doc_fich.getElementsByTagName("lang");
        Element lgElement = (Element) lgNodes.item(0);
        lg = lgElement.getAttribute("value");
        return lg;
    }


    public static ArrayList<Album> getQ1Albums(String country){

        ArrayList<Album> albums = new ArrayList<>();

        //i create an xpath for this method
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        String xpathTarget = "//Album[Country='"+country+"']";//I need the albums with the country required
        for (Document xmlDocument : map.values()) {
            NodeList yearnl = xmlDocument.getElementsByTagName("Year");//I get the year of the document for adding it to the album constructor
            Element yearElement = (Element)yearnl.item(0);
            String year = yearElement.getTextContent().trim();
            
          try{
            NodeList nlAlbums= (NodeList)xpath.evaluate(xpathTarget, xmlDocument, XPathConstants.NODESET);//I get get the albums with the country required
            for (int co=0; co<nlAlbums.getLength(); co++) {
                Element elemAlbum = (Element)nlAlbums.item(co);
                NodeList nlAlbumCountries = elemAlbum.getElementsByTagName("Country");//i get the different elements 
                Element elemAlbumCountry = (Element)nlAlbumCountries.item(0);
                String albumCountry = elemAlbumCountry .getTextContent().trim();
                NodeList nlAlbumName = elemAlbum.getElementsByTagName("Name");
                Element elemAlbumName = (Element)nlAlbumName.item(0);
                String albumName = elemAlbumName.getTextContent().trim();

                String record=null;
                try{NodeList nlAlbumRec = elemAlbum.getElementsByTagName("Recording");
                Element elemAlbumRec = (Element)nlAlbumRec.item(0);
                record = elemAlbumRec.getTextContent().trim();
                }
                catch(Exception e){

                }

                String albumSinger=null;
                try{NodeList nlAlbumSinger = elemAlbum.getElementsByTagName("Singer");//this one is for getting the singer value and if it doesnt have one
                Element elemAlbumSinger = (Element)nlAlbumSinger.item(0);
                albumSinger = elemAlbumSinger.getTextContent().trim();

                }catch(NullPointerException e){
                        NodeList nlAlbumSinger = elemAlbum.getElementsByTagName("Group");//the exception checks for the group element
                        Element elemAlbumSinger = (Element)nlAlbumSinger.item(0);
                        albumSinger = elemAlbumSinger.getTextContent().trim();
                }
                   

                String albumaid = elemAlbum.getAttribute("aid");
                String albumReview=null;


                try{NodeList nlAlbumReview = elemAlbum.getChildNodes();//this code checks the chldnodes of the album and picks the text node, the review
                    for(int i=0;i<nlAlbumReview.getLength();i++){
                        if(nlAlbumReview.item(i).getNodeType()==Node.TEXT_NODE){
                            String textContent = nlAlbumReview.item(i).getTextContent();
                            if (!textContent.trim().isEmpty()) {//this is to filter escape and blank carachters
                                albumReview = textContent.trim();
                                break;
                            }
                        }
                    }
                }catch(Exception e){}
                    
                    
                if (!albums.contains(new Album(albumName, albumCountry, albumSinger, albumaid,albumReview,year,record))) {//we check and add to the list
                    albums.add(new Album(albumName, albumCountry, albumSinger, albumaid,albumReview,year,record));
                    }
                

            
            }
                
            }catch(Exception e){
            }
                
            
        
        
        }
        Collections.sort(albums);//we sort alphabetically
        return albums;

        
        

        
    }

     public static ArrayList<Song> getQ1Songs(String country, String album){

        ArrayList<Song> songs = new ArrayList<>();

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        String xpathTarget = "/Music/Album[@aid='" + album + "']/Country";

        
        for (Document xmlDocument : map.values()) {
            try{
            NodeList nlCountries= (NodeList)xpath.evaluate(xpathTarget, xmlDocument, XPathConstants.NODESET);
           
            Element elemCountryName = (Element)nlCountries.item(0);
            String countryName = elemCountryName.getTextContent().trim();
            String xpathTargetSongs="/Music/Album[@aid='" + album + "']/Song";
            if(countryName.equals(country)){
                NodeList nlSongs= (NodeList)xpath.evaluate(xpathTargetSongs, xmlDocument, XPathConstants.NODESET);//we et the nodelist of songs
                for (int i = 0; i < nlSongs.getLength(); i++) {
                    Element songElement = (Element) nlSongs.item(i);
                    String songLang=songElement.getAttribute("lang");

                    NodeList nlSongName = songElement.getElementsByTagName("Title");
                    Element elemSongName = (Element)nlSongName.item(0);
                    String songName = elemSongName.getTextContent().trim();

                    NodeList nlSongSinger = songElement.getElementsByTagName("Composer");
                    Element elemSongSinger = (Element)nlSongSinger.item(0);
                    String songComposer = elemSongSinger.getTextContent().trim();

                    NodeList nlgenre = songElement.getElementsByTagName("Genre");
                    ArrayList<String> songGenre= new ArrayList<String>();
                    boolean pop=false;//bolean to check if it is pop
                    for (int g = 0; g < nlgenre.getLength(); g++) {
                    Element genreElement = (Element) nlgenre.item(g);
                    String genre = genreElement.getTextContent().trim();
                    songGenre.add(genre);//we get the genre and add it to the list of genres
                    if(genre.equals("Pop")){
                        pop=true;
                    }
                    }
                    if(pop){//if it is popo we check and add
                        if (!songs.contains(new Song(songName, songLang,songComposer,songGenre))) {
                        songs.add(new Song(songName, songLang,songComposer,songGenre));}
                    }
                }
            
            
            }
            }catch(Exception e){
                
            }
        }
            Collections.sort(songs);

            return songs;
    }

}
	