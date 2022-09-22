DROP FUNCTION IF EXISTS get_id_country;
CREATE FUNCTION get_id_country(v VARCHAR) RETURNS UUID AS $$
    BEGIN
        RETURN id_country FROM country WHERE UPPER(unaccent(acronym)) = UPPER(unaccent(v));
    END;
$$ LANGUAGE plpgsql;