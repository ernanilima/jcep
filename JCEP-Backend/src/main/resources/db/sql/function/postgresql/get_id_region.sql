DROP FUNCTION IF EXISTS get_id_region;
CREATE FUNCTION get_id_region(v VARCHAR) RETURNS UUID AS $$
    BEGIN
        RETURN id_region FROM region WHERE UPPER(unaccent(name)) = UPPER(unaccent(v));
    END;
$$ LANGUAGE plpgsql;