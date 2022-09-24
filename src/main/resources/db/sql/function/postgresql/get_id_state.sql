DROP FUNCTION IF EXISTS get_id_state;
CREATE FUNCTION get_id_state(v VARCHAR) RETURNS UUID AS $$
    BEGIN
        RETURN id_state FROM state WHERE UPPER(unaccent(acronym)) = UPPER(unaccent(v));
    END;
$$ LANGUAGE plpgsql;