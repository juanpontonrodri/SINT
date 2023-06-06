package p2;

import java.io.PrintWriter;
import java.util.ArrayList;
public class FrontEnd {
    

    public static void autoPhase01(PrintWriter out) {
        // create a new StringBuilder to store the response
        StringBuilder response = new StringBuilder();
    
        // append the beginning of the XML document to the StringBuilder
        response.append("<?xml version='1.0' encoding='utf-8' ?>\n");
        // append the opening tag for the service element to the StringBuilder
        response.append("<service>\n");
        // append the status element to the StringBuilder
        response.append("   <status>OK</status>\n");
        // append the closing tag for the service element to the StringBuilder
        response.append("</service>\n");
    
        // print the response to the output stream
        out.println(response.toString());
    }

    
    public static void doGetPhase01(PrintWriter out) {
        // Start building the HTML code
        String html = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset='utf-8'>\n"
                    + "<title>Music Information service</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Welcome to this service</h1>\n"
                    + "<h2>Please, select a query:</h2>\n"
                    //links to the next phases
                    + "<li><a href='?p=holaqueta1&pphase=02'>Show error files</a></li>\n"
                    + "<li><a href='?p=holaqueta1&pphase=11'>Query 1: Pop songs of an Album of a Country</a></li>\n"
                    
                    + "</body>\n"
                    +"<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>"
                    + "</html>\n";
    
        // Print the final response to the output stream
        out.println(html);
    }
    
    
    

    public static void doGetPhase02(PrintWriter out, ArrayList<String> errorsURL, ArrayList<String> fatalErrorsURL) {
        // Start building the HTML code
        String html = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset='utf-8'>\n"
                    + "<title>Music Information service</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Music Information Service</h1>\n"
                    + "<h2>Files with errors: " + errorsURL.size() + "</h2>\n";
    
        // Iterate through the list of errorsURL using a for-each loop
        for (String error : errorsURL) {
            html += "<br>\n"
                  + "<li>" + error + "</li>\n";
        }
        html += "<br>\n"
              + "<h2>Files with fatal errors: " + fatalErrorsURL.size() + "</h2>\n";
    
        // Iterate through the list of fatalErrorsURL using a for-each loop
        for (String fatalError : fatalErrorsURL) {
            html += "<br>\n"
                  + "<li>" + fatalError + "</li>\n";
        }
        html += "<br>\n"
              +"<button><a href='?p=holaqueta1&pphase=01'>Back</a></button>"
                  
              + "</body>\n"
              +"<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>"
              + "</html>\n";
    
        // Print the HTML code to the output stream
        out.println(html);
    }
    


    public static void autoPhase02(PrintWriter out, ArrayList<String> errorsURL, ArrayList<String> fatalErrorsURL) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8' ?>\n");
        sb.append("<wrongDocs>\n");
        if (!errorsURL.isEmpty()) {
            sb.append("  <errors>\n");
            for (String errorURL : errorsURL) {
                sb.append("    <error><file>" + errorURL + "</file></error>\n");
            }
            sb.append("  </errors>\n");
        }
        if (!fatalErrorsURL.isEmpty()) {
            sb.append("  <fatalerrors>\n");
            for (String fatalErrorURL : fatalErrorsURL) {
                sb.append("    <fatalerror><file>" + fatalErrorURL + "</file></fatalerror>\n");
            }
            sb.append("  </fatalerrors>\n");
        } 
        sb.append("</wrongDocs>\n");
        out.print(sb.toString());
    }
    

//in order to avoid switches or ifs in the Front End I have three methods for phase 03 


    public static void doGetPhase03NoP(PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta charset='utf-8'>\n");
        sb.append("<title>Music Information Service</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>Music Information Service</h1>\n");
        
        sb.append("<h2>No password</h2>\n");
               
        sb.append("</body>\n");
        sb.append("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        sb.append("</html>\n");
        out.print(sb.toString());
    }

    public static void doGetPhase03WrP(PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta charset='utf-8'>\n");
        sb.append("<title>Music Information Service</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>Music Information Service</h1>\n");
        
        sb.append("<h2>Wrong password</h2>\n");
               
        sb.append("</body>\n");
        sb.append("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        sb.append("</html>\n");
        out.print(sb.toString());
    }

    public static void doGetPhase03Mis(PrintWriter out,String missing) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta charset='utf-8'>\n");
        sb.append("<title>Music Information Service</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>Music Information Service</h1>\n");
        //we print the missing parameter
        sb.append("<h2>No parameter :" + missing + "</h2>\n");
        
        sb.append("</body>\n");
        sb.append("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        sb.append("</html>\n");
        out.print(sb.toString());
    }


    

    public static void autoPhase03NoP(PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
        
        sb.append("<wrongRequest>no passwd</wrongRequest>\n");
                
        out.print(sb.toString());
    }

    public static void autoPhase03WrP(PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
       
        sb.append("<wrongRequest>bad passwd</wrongRequest>\n");
           
        out.print(sb.toString());
    }

    public static void autoPhase03Mis(PrintWriter out,String missing) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
       
        sb.append("<wrongRequest>no param:"+missing+"</wrongRequest>\n");
           
        out.print(sb.toString());
    }
    

    public static void doGetPhase11(PrintWriter out, ArrayList<String> countries) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta charset='utf-8'>\n");
        sb.append("<title>Music Information Service</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>Music Information Service</h1>\n");
        sb.append("<h2>Query 1: Phase 1</h2>\n");
        sb.append("<h3>Please, select a Country:</h3>\n");
        sb.append("<ol>\n");
        for (String country : countries) {
            // Añade un enlace que lleva al usuario a la siguiente fase del proceso pasando el nombre del pais como parametro
            sb.append("<li><a href='?p=holaqueta1&pphase=12&pcountry=" + country + "'>" + country + "</a></li>\n");
            
        }
        sb.append("</ol>\n");
        sb.append("<button><a href='?p=holaqueta1&pphase=01'>Home</a></button>");

        sb.append("</body>\n");
        sb.append("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        sb.append("</html>\n");
        out.print(sb.toString());
    }
    
    
    public static void autoPhase11(PrintWriter out, ArrayList<String> countries) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
        sb.append("<countries>\n");
        for (String country : countries) {
            sb.append("<country>" + country + "</country>\n");
        }
        sb.append("</countries>\n");
        out.print(sb.toString());
    }
    

    public static void doGetPhase12(PrintWriter out,String country,ArrayList<Album> albums){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>"); 
        out.println("<meta charset='utf-8'>");
        out.println("<title>Music Information Service</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Music Information Service</h1>");
        out.println("<h2>Query 1: Phase 2 (Country = "+ country+")</h2>");
        out.println("<h3>Please, select a Album:</h3>");
        out.println("<ol>");
        //Lista de albums con enlace a la siguiente fase
        for (int index = 0; index < albums.size(); index++) {
            out.print("<li><a href='?p=holaqueta1&pphase=" + 13 + "&pcountry=" +country + "&paid=" +albums.get(index).getaid()+"'>Album = '"+albums.get(index).getName()+"'</a> --- Year= '"+albums.get(index).getYear()+"' --- Performer='" + albums.get(index).getSinger()+"' --- Review = '" + albums.get(index).getReview()+"'</li>");
        }
        out.println("</ol>");
        out.println("<button><a href='?p=holaqueta1&pphase=01'>Home</a></button>");
        out.println("<button><a href='?p=holaqueta1&pphase=11'>Back</a></button>");
        out.println("</body>");
        out.println("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        out.println("</html>");
        

    }

    public static void autoPhase12(PrintWriter out,String country,ArrayList<Album> albums){
        out.println("<?xml version='1.0' encoding='utf-8'?>");
        out.println("<albums>");

       for (int index = 0; index < albums.size(); index++) {
        out.println("<album year='" + albums.get(index).getYear()+"' performer='"+albums.get(index).getSinger()+"' review='"+albums.get(index).getReview()+"'>"+ albums.get(index).getName()+"</album>");
    }
        
        out.println("</albums>");

    }

    public static void doGetPhase13(PrintWriter out,String country,String album, ArrayList<Song> songs){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>"); 
        out.println("<meta charset='utf-8'>");
        out.println("<title>Music Information Service</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Music Information Service</h1>");
        out.println("<h2>Query 1: Phase 3 (Country = "+ country+", Album="+album+")</h2>");
        out.println("<h3>This is the query result:</h3>");
        out.println("<ol>");
        //Lista de canciones
        for (Song song : songs) {
            String songGenres="";
            for(String genre : song.getGenre()){//Añade a la String los generos de las canciones y elimina la ultima coma
                songGenres+=genre+",";
            }
            songGenres=songGenres.substring(0,songGenres.length()-1);
            out.print("<li>Title='"+song.getTitle()+"'--- Language = '"+song.getLang()+"'--- Genres= '"+ songGenres+"' --- Composer='" + song.getComposer()+"'</li>");
        }
        out.println("</ol>");
        out.println("<button><a href='?p=holaqueta1&pphase=01'>Home</a></button>");
        out.println("<button><a href='?p=holaqueta1&pphase=12&pcountry=" +country+"'>Back</a></button>");
        out.println("</body>");
        out.println("<div style='position: fixed; bottom: 0; ''>\n<p>&copy; Juan Pontón Rodríguez</p>\n</div>");
        out.println("</html>");
        

    }

    public static void autoPhase13(PrintWriter out,String country,String album, ArrayList<Song> songs){
        out.println("<?xml version='1.0' encoding='utf-8'?>");
        out.println("<songs>");
        //mismo que en doget
       for (Song song : songs) {
        String songGenres="";
            for(String genre : song.getGenre()){
                songGenres+=genre+",";
            }
        songGenres=songGenres.substring(0,songGenres.length()-1);
        out.println("<song lang='" + song.getLang()+"' genres='"+songGenres+"' composer='"+song.getComposer()+"'>"+ song.getTitle()+"</song>");
    }
        
        out.println("</songs>");

    }

}
