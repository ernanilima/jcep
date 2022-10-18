DROP ALIAS IF EXISTS get_id_country;
CREATE ALIAS get_id_country AS  $$
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.UUID;
@CODE
UUID getIdCountryByAcronym(final Connection conn, String v) throws SQLException {
    Collator instance = Collator.getInstance();
    StringBuffer sql = new StringBuffer();

    sql.append("SELECT id_country FROM country ");
    sql.append("WHERE UPPER(unaccent(acronym))=UPPER(unaccent('" + v + "'));");

    PreparedStatement ps = conn.prepareStatement(sql.toString());
    ResultSet rs = ps.executeQuery();

    UUID uuid = UUID.randomUUID();
    while (rs.next())
        uuid = UUID.fromString(rs.getString(1));
    return uuid;
}
$$;