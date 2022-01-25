//Metodo: Varios métodos de tabla Teléfono
package proyectotelefonica;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class me_tipo_animal {
    
    Connection db = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst=null;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    int tipID;
    String nomCl;
    String sql;
    Boolean a=false;

    //Conexion base datos    
    public void conecciondb() {
      try {
          db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Veterinaria","postgres","PostBl33gg27"); 
              //Actualize ruta .../basedatos,usuario,contraseña
          } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} 
    }

    //Ingreso (unico) tabla tipo_animal
    public void inserta_tipo() throws SQLException, ParseException {
        if (a==false) { 
            conecciondb();
            tipID=Integer.parseInt(tipo_animal.a5.getText());
            nomCl=tipo_animal.a7.getText();
            sql = "insert into tipo_animal (id_tipo, nombre_tipo) values (?,?)";
            pst = db.prepareStatement(sql);
            pst.setInt(1,tipID);
            pst.setString(2,nomCl);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se Guardo..."); 
        } 
    }

    //Actualiza tabla tipo_animal
    public void actuali_tipo() throws SQLException, ParseException {
        if (a==true) { 
            conecciondb();
            nomCl=tipo_animal.a7.getText();
            sql = "update tipo_animal set nombre_tipo=? where id_tipo='"+tipID+"'";
            pst = db.prepareStatement(sql);
            pst.setString(1,nomCl);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se Actualizo..."); 
        } 
    }

    //Consulta tabla tipo_animal si existe número
    public void consulta_tipo() throws SQLException {
        conecciondb(); 
        st = db.createStatement();
        tipID=Integer.parseInt(tipo_animal.a5.getText());
        rs = st.executeQuery("select * from tipo_animal where id_tipo='"+tipID+"'");
        if (rs.next()) {
            a=true;
            tipo_animal.a7.setText(rs.getString(2));
        }else {
            JOptionPane.showMessageDialog(null,"No Existe...");
            a=false;
        } 
    }

    //Elimina tabla tipo_animal si existe id y no está en otra tabla relacionada
    public void elimina_tipo() throws SQLException {
        try {
            if (a==true) { 
                conecciondb();
                int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
                if (resp!=1) {
                    st.execute("delete from tipo_animal where id_tipo='"+tipID+"'");
                    JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); 
                } 
            }
        }
        catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} 
    }

    //Limpiar datos entrada de tipo_animal
    public void limpia() {
        tipo_animal.a5.setText("");
        tipo_animal.a7.setText("");
    }

}


