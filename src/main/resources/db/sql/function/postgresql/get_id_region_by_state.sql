DROP FUNCTION IF EXISTS get_id_region_by_state;
CREATE FUNCTION get_id_region_by_state(v VARCHAR) RETURNS UUID AS $$
    BEGIN
        RETURN id_region FROM state
            INNER JOIN region ON state.region_id = region.id_region
            WHERE UPPER(unaccent(acronym)) = UPPER(unaccent(v));
    END;
$$ LANGUAGE plpgsql;