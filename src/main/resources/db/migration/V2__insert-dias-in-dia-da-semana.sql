DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.tables
        WHERE table_schema = 'public'
        AND table_name = 'dia_da_semana'
    ) THEN

        INSERT INTO dia_da_semana(
	    id, descricao)
	    VALUES (1, 'SEGUNDA-FEIRA'),
           (2, 'TERÇA-FEIRA'),
           (3, 'QUARTA-FEIRA'),
           (4, 'QUINTA-FEIRA'),
           (5, 'SEXTA-FEIRA'),
           (6, 'SÁBADO');
        
    ELSE
        RAISE NOTICE 'A tabela dia_da_semana não existe, portanto o insert não foi realizado.';
    END IF;
END $$;