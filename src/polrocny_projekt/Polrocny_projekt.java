/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polrocny_projekt;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author admin
 */
public class Polrocny_projekt 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        
       
        Connection conn = null;
        
        
        try
        {
            Scanner scanner = new Scanner(System.in);  
            
            
            
            
            String url = "jdbc:mysql://localhost:3306/slovnik_databaza";
            String user = "root";
            String password = ""; 
            conn = (Connection) DriverManager.getConnection(url, user, password); 
           
             
            System.out.println("Vyber si slovník slovensko-anglický(1) alebo anglicko-slovenský(2): ");
            String jazyk = scanner.nextLine();
            
            if ("1".equals(jazyk))
            {
                
                System.out.println("Zadaj slovenské slovo: ");
                String skslovo = scanner.nextLine();
                
                String skslct = "SELECT Anglicko_Slovensky FROM slovnik WHERE Slovensky = ?";
                PreparedStatement dotaz = conn.prepareStatement(skslct);
                dotaz.setString(1, skslovo);
             
                ResultSet vypis = dotaz.executeQuery();
                while (vypis.next()) 
                {
                String preklad = (String) vypis.getString("Anglicko_Slovensky");
                System.out.println("Zadané slovo sa povie po anglicky: " + preklad);
                }
            
                
                
            }
            
            if ("2".equals(jazyk))
            {
                System.out.println("Zadaj anglické slovo: ");
                String angslovo = scanner.nextLine();
                
                String engslct = "SELECT Slovensko_Anglicky FROM slovnik WHERE Anglicky = ?";
                PreparedStatement dotaz = conn.prepareStatement(engslct);
                dotaz.setString(1, angslovo);
             
                ResultSet vypis = dotaz.executeQuery();
                while (vypis.next()) 
                {
                String preklad = (String) vypis.getString("Slovensko_Anglicky");
                
                System.out.println("Zadané slovo znamená po slovensky: " + preklad);
                }
             
            }
             
            
        }
        
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("error");
            
        }
        finally
        {
            try
            {
                if(conn != null)
                    conn.close();
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
            System.out.println("Koniec");
        }
        
        System.out.println("out"); 
        
        
        
    }
    
  
    
    
    
}
