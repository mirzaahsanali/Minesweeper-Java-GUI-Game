// package Game;
// import java.beans.Statement;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// class createDB{

//     private static final String url = "jdbc:mysql://127.0.0.1:3306/minesweeper";
//     private static final String user = "root";
//     private static final String password = "password";

//     public static void main(String[] args){
//         try{
//             Class.forName("com.mysql.cj.jdbc.Driver");
//         }catch (ClassNotFoundException e){
//             System.out.println(e.getMessage());
//         }

//         try{
//             Connection con = DriverManager.getConnection(url,user,password);
//             Statement st = (Statement) con.createStatement();
//             System.out.println("Connected Successfully!");
//         }catch (SQLException e){
//             System.out.println(e.getMessage());
//         }

//     }
// }