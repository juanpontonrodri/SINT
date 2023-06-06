package p2;


//import static p2.FrontEnd.doGetPhase02;

import java.io.*;
import java.util.ArrayList;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class Sint185P2 extends HttpServlet{


    private static final String password="holaqueta1";

    
    PrintWriter out=null;
   
    public void init() throws ServletException{ //We parse the documents just once
      try{
        DataModel.parser();
    }catch(Exception e){}
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

      ArrayList<String> countries= DataModel.getQ1Countries();   
  //We set the parameters to the null default
      String phase = null;
      String p = null;
      String method = null;
      boolean auto = false;
      String country=null;
      String albumaid=null;
      String live=null;
      String comment=null;
      String lg=null;
      out = res.getWriter();
      res.setCharacterEncoding("UTF-8");

      try {
        //we get the parameters
          phase = req.getParameter("pphase");
          p = req.getParameter("p");
          method = req.getParameter("auto");
          if(method!=null){//if auto is set to something different from null we set a boolean
          auto = Boolean.parseBoolean(method);}
          if (p==null) {
              if (!auto) {
                  FrontEnd.doGetPhase03NoP(out);//No password phase
                 //System.out.println("no passwd no auto");

              } else {
                  FrontEnd.autoPhase03NoP(out);
                  //System.out.println("no passwd  auto");

              }
          } else {
            //System.out.println(" contraseña: "+p);
              if (!p.equals(password)) {
                  if (!auto) {
                      FrontEnd.doGetPhase03WrP(out);//Wrong passwd phase
                  } else {
                      FrontEnd.autoPhase03WrP(out);
                  }
              }else{
                if(phase==null){//if we dont have phase, we set the default
                  phase="01";
                }

                  switch (phase) {//we select the phases
                    case "01":
                    live=DataModel.getLive();
                    comment=DataModel.getComment();
                    lg=DataModel.getLg();
                    if(!auto) {
                      FrontEnd.doGetPhase01(out,comment,live,lg);}//browsermode if auto is false
                      else {FrontEnd.autoPhase01(out);}//auto mode if auto is true
                      break;
                    case "02": 
                    if(!auto) {                   
                      FrontEnd.doGetPhase02(out,DataModel.errorsURL, DataModel.fatalErrorsURL);}
                      else {FrontEnd.autoPhase02(out,DataModel.errorsURL, DataModel.fatalErrorsURL);}
                      break;
                    case "11":
                    if(!auto) {
                      FrontEnd.doGetPhase11(out, countries);}
                      else {FrontEnd.autoPhase11(out, countries);}
                      break;
                    case "12":                    
                      country=req.getParameter("pcountry");
                      if(country!=null){//If we have a country parameter we get the albums and then we chose between auto or browser
                        ArrayList<Album> albums=DataModel.getQ1Albums(country);
                        if(!auto) {
                          FrontEnd.doGetPhase12(out,country, albums);}
                        else {FrontEnd.autoPhase12(out,country, albums);}
                      }
                      else{//if we dont have country parameter we call the phase 03 with the missing parameter
                        if(!auto) {
                        FrontEnd.doGetPhase03Mis(out,  "pcountry");}
                        else {FrontEnd.autoPhase03Mis(out,  "pcountry");}
                      }
                      break;
                    case "13":
                      albumaid=req.getParameter("paid");
                      country=req.getParameter("pcountry");
                      if((country!=null)){
                        if(albumaid!=null){//its the same process but with tow parameters
                          ArrayList<Song> songs = DataModel.getQ1Songs(country,albumaid);
                          if(!auto) {
                          FrontEnd.doGetPhase13(out,country, albumaid, songs);}
                          else {FrontEnd.autoPhase13(out,country, albumaid, songs);}
                        }
                        else{
                          if(!auto) {
                          FrontEnd.doGetPhase03Mis(out, "paid");}
                          else {FrontEnd.autoPhase03Mis(out, "paid");}
                        }
                      }else{
                        if(!auto) {FrontEnd.doGetPhase03Mis(out,  "pcountry");}
                        else {FrontEnd.autoPhase03Mis(out,  "pcountry");}
                      }
                      break;                      
                    default://in case of wrong phase
                    if(!auto) {FrontEnd.doGetPhase01(out,comment,live,lg);}
                    else {FrontEnd.autoPhase01(out);}
                      break;
                  }
                  
               
                
              }
          }
      } catch (Exception e) {
        System.out.println(e.getStackTrace());
      }
  }
}
