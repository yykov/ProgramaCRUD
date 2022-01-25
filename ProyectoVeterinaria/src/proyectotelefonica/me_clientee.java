//Metodo: Varios métodos de tabla cliente
package proyectotelefonica;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;

public class me_clientee {
    
    Connection db = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst=null;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    int numID, antig, cant_masc;
    String genero,ceduCl,nomCl,apelCl,direccion,telefono_cl;
    Date fech_reg,fech_uv, fech_regi;
    java.util.Date utilDate;
    String sql;
    Boolean a=false;

    //Conexion base datos    
    public void conecciondb() {
      try {
          db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Veterinaria","postgres","PostBl33gg27"); 
              //Actualize ruta .../basedatos,usuario,contraseña
          } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} 
    }

    //Ingreso (unico) tabla clientee
    public void inserta_cliente() throws SQLException, ParseException {
        if (a==false) { 
            conecciondb();
            numID=Integer.parseInt(clientee.a5.getText());
            genero=clientee.a6.getText();
            ceduCl=clientee.a7.getText();
            nomCl=clientee.a8.getText();
            apelCl=clientee.a10.getText();
            direccion=clientee.a9.getText();
            telefono_cl = clientee.a11.getText();
            fech_reg = formato.parse(clientee.a12.getText());
            antig = Integer.parseInt(clientee.a15.getText());
            fech_uv = formato.parse(clientee.a13.getText());
            cant_masc = Integer.parseInt(clientee.a14.getText());
            sql = "insert into cliente (id_cliente,cedula_cl,nombre_cl,apellido_cl,genero_cl,direccion_cl,telefono_cl,fecha_registro_cl,antiguedad,fecha_ultima_visita,cant_mascota) values (?,?,?,?,?,?,?,?,?,?,?)";
            pst = db.prepareStatement(sql);
            pst.setInt(1,numID);
            pst.setString(2,ceduCl);
            pst.setString(3,nomCl);
            pst.setString(4,apelCl);
            pst.setString(5,genero);
            pst.setString(6,direccion);
            pst.setString(7,telefono_cl);
            java.sql.Date sqld = new java.sql.Date(fech_reg.getTime());
            pst.setDate(8,sqld);
            pst.setInt(9,antig);
            java.sql.Date sqlfd = new java.sql.Date(fech_reg.getTime());
            pst.setDate(10,sqlfd);
            pst.setInt(11,cant_masc);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se Guardo..."); 
        } 
    }

    //Actualiza tabla clientee
    public void actuali_cliente() throws SQLException, ParseException {
        if (a==true) { 
            conecciondb();
            genero=clientee.a6.getText();
            ceduCl=clientee.a7.getText();
            nomCl=clientee.a8.getText();
            apelCl=clientee.a10.getText();
            direccion=clientee.a9.getText();
            telefono_cl = clientee.a11.getText();
            fech_reg = formato.parse(clientee.a12.getText());
            antig = Integer.parseInt(clientee.a15.getText());
            fech_uv = formato.parse(clientee.a13.getText());
            cant_masc = Integer.parseInt(clientee.a14.getText());
            sql = "update cliente set cedula_cl=?, nombre_cl=?, apellido_cl=?, genero_cl=?, direccion_cl=?, telefono_cl=?, fecha_registro_cl=?, antiguedad=?, fecha_ultima_visita=?, cant_mascota=? where id_cliente='"+numID+"'";
            pst = db.prepareStatement(sql);
            pst.setString(1,ceduCl);
            pst.setString(2,nomCl);
            pst.setString(3,apelCl);
            pst.setString(4,genero);
            pst.setString(5,direccion);
            pst.setString(6,telefono_cl);
            java.sql.Date sqld = new java.sql.Date(fech_reg.getTime());
            pst.setDate(7,sqld);
            pst.setInt(8,antig);
            java.sql.Date sqlfd = new java.sql.Date(fech_reg.getTime());
            pst.setDate(9,sqlfd);
            pst.setInt(10,cant_masc);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se Actualizo..."); 
        } 
    }

    //Consulta tabla clientee si existe id
    public void consulta_cliente() throws SQLException {
        conecciondb(); 
        st = db.createStatement();
        numID=Integer.parseInt(clientee.a5.getText());
        rs = st.executeQuery("select * from cliente where id_cliente='"+numID+"'");
        if (rs.next()) {
            a=true;
            clientee.a7.setText(rs.getString(2));
            clientee.a8.setText(rs.getString(3));
            clientee.a10.setText(rs.getString(4));
            clientee.a6.setText(rs.getString(5));
            clientee.a9.setText(rs.getString(6));
            clientee.a11.setText(rs.getString(7));
            clientee.a12.setText(rs.getString(8));
            clientee.a15.setText(rs.getString(9));
            clientee.a13.setText(rs.getString(10));
            clientee.a14.setText(rs.getString(11));
        }else {
            JOptionPane.showMessageDialog(null,"No Existe...");
            a=false;
        } 
    }

    //Elimina tabla clientee si existe cedula y no está en otra tabla relacionada
    public void elimina_cliente() throws SQLException {
        try {
            if (a==true) { 
                conecciondb();
                int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
                if (resp!=1) {
                    st.execute("delete from cliente where id_cliente='"+numID+"'");
                    JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); 
                } 
            }
        }
        catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} 
    }

    //Limpiar datos entrada de clientee
    public void limpia() {
        clientee.a5.setText("");
        clientee.a6.setText("");
        clientee.a7.setText("");
        clientee.a8.setText("");
        clientee.a9.setText("");
        clientee.a10.setText("");
        clientee.a11.setText("");
        clientee.a12.setText("");
        clientee.a13.setText("");
        clientee.a14.setText("");
        clientee.a15.setText("");
    }

}


