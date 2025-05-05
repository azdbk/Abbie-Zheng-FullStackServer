import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.sql.*;

import java.net.InetSocketAddress;
import java.util.Map;

//For compiling on the shell on repl: Same on mac
//javac -cp sqlite-jdbc-3.23.1.jar: Main.java
//java -cp sqlite-jdbc-3.23.1.jar: Main

//Use for windows
//javac -cp sqlite-jdbc-3.23.1.jar; Main.java
class Main {

 public static void main(String[] args)throws IOException{
    (new Main()).init();
  }


  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init() throws IOException{
    // create port
    int port = 8500;

    //create HTTPserver object
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    // create database object
    Database db = new Database("jdbc:sqlite:bakerydata.db");

    server.createContext("/", new RouteHandler("You are connected, but route not given or incorrect..."));


    //all
    String home = "";
    home  = "SELECT Products.ProductID, Products.ProductName, Products.Cost, Products.Description, Type.TypeName, Notes.Note, Image.ImageURL, Category.CategoryName, Stock.Availability";
    home += " FROM Products ";
    home += " INNER JOIN Type ON Products.ProductID = Type.ProductID ";
    home += " INNER JOIN Notes ON Products.ProductID = Notes.ProductID ";
    home += " INNER JOIN Image ON Products.ProductID = Image.ProductID ";
    home += " INNER JOIN Category ON Products.ProductID = Category.ProductID";
    home += " INNER JOIN Stock ON Products.ProductID = Stock.ProductID;";

    server.createContext("/home", new RouteHandler(db, home));
    
    //cakes
    String cakes = "";
    cakes  = "SELECT Products.ProductID, Products.ProductName, Products.Cost, Products.Description, Type.TypeName, Notes.Note, Image.ImageURL, Category.CategoryName, Stock.Availability";
    cakes += " FROM Products ";
    cakes += " INNER JOIN Type ON Products.ProductID = Type.ProductID ";
    cakes += " INNER JOIN Notes ON Products.ProductID = Notes.ProductID ";
    cakes += " INNER JOIN Image ON Products.ProductID = Image.ProductID ";
    cakes += " INNER JOIN Category ON Products.ProductID = Category.ProductID";
    cakes += " INNER JOIN Stock ON Products.ProductID = Stock.ProductID";
    cakes += " WHERE Category.CategoryName = 'Cake';";

    server.createContext("/cakes", new RouteHandler(db, cakes));

    //drinks
    String drinks = "";
    drinks  = "SELECT Products.ProductID, Products.ProductName, Products.Cost, Products.Description, Type.TypeName, Notes.Note, Image.ImageURL, Category.CategoryName, Stock.Availability";
    drinks += " FROM Products ";
    drinks += " INNER JOIN Type ON Products.ProductID = Type.ProductID ";
    drinks += " INNER JOIN Notes ON Products.ProductID = Notes.ProductID ";
    drinks += " INNER JOIN Image ON Products.ProductID = Image.ProductID ";
    drinks += " INNER JOIN Category ON Products.ProductID = Category.ProductID";
    drinks += " INNER JOIN Stock ON Products.ProductID = Stock.ProductID";
    drinks += " WHERE Category.CategoryName = 'Drinks';";

    server.createContext("/drinks", new RouteHandler(db, drinks));

    //pastry
    String pastries = "";
    pastries  = "SELECT Products.ProductID, Products.ProductName, Products.Cost, Products.Description, Type.TypeName, Notes.Note, Image.ImageURL, Category.CategoryName, Stock.Availability";
    pastries += " FROM Products ";
    pastries += " INNER JOIN Type ON Products.ProductID = Type.ProductID ";
    pastries += " INNER JOIN Notes ON Products.ProductID = Notes.ProductID ";
    pastries += " INNER JOIN Image ON Products.ProductID = Image.ProductID ";
    pastries += " INNER JOIN Category ON Products.ProductID = Category.ProductID";
    pastries += " INNER JOIN Stock ON Products.ProductID = Stock.ProductID";
    pastries += " WHERE Category.CategoryName = 'Pastry';";

    server.createContext("/pastries", new RouteHandler(db, pastries));
    
    //start server once all contexts are created
    server.start();
    System.out.println("Server is listening on port "+port);
    
   }    
}


