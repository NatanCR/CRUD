import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseCRUD {

    // Create

    public boolean inserir(Adicional adicional) {

        String sql = "INSERT INTO adicional (nome, valor) VALUES (?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, adicional.getNome());
            stmt.setDouble(2, adicional.getValor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con, stmt);
        }
    }

    // Read

    public List<Adicional> mostrar() {

        Connection con = ConexaoBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Adicional> adicionais = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM adicional");
            rs = stmt.executeQuery();
            while (rs.next()) {

                Adicional adicional = new Adicional();

                adicional.setId(rs.getInt("id"));
                adicional.setNome(rs.getString("nome"));
                adicional.setValor(rs.getDouble("valor"));
                adicionais.add(adicional);
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        } finally {
            ConexaoBanco.closeConnection(con, stmt);
        }

        return adicionais;
    }

    // Update

    public boolean editar(Adicional adicional) {

        String sql = "UPDATE adicional SET nome = ?, valor = ?  where id = ? ";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, adicional.getNome());
            stmt.setDouble(2, adicional.getValor());
            stmt.setInt(3, adicional.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con, stmt);
        }
    }

    // Delete

    public boolean excluir(Adicional adicional) {

        String sql = "DELETE FROM adicional where id = ? ";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, adicional.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con, stmt);
        }
    }
}
