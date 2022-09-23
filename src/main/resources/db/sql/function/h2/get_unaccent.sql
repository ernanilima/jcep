DROP ALIAS IF EXISTS unaccent;
CREATE ALIAS unaccent AS  $$
import java.text.Normalizer;
@CODE
String getNoAccents(String v) {
    String normalizer = Normalizer.normalize(v, Normalizer.Form.NFKD);
    return normalizer.replaceAll("\\p{M}", "");
}
$$;
