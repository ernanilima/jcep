DROP ALIAS IF EXISTS get_id_state;
CREATE ALIAS get_id_state AS  $$
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.UUID;
@CODE
UUID getIdStateByAcronym(final Connection conn, String v) throws SQLException {
    Collator instance = Collator.getInstance();
    StringBuffer sql = new StringBuffer();

    sql.append("SELECT id_state FROM state ");
    sql.append("WHERE UPPER(unaccent(acronym))=UPPER(unaccent('" + v + "'));");

    PreparedStatement ps = conn.prepareStatement(sql.toString());
    ResultSet rs = ps.executeQuery();

    UUID uuid = UUID.randomUUID();
    while (rs.next())
        uuid = UUID.fromString(rs.getString(1));
    return uuid;
}
$$;