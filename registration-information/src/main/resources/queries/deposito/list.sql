SELECT
    id,
    endereco_id,
    tipo,
    capacidade,
    capacidade_tipo
FROM
    deposito
OFFSET
    :offset
LIMIT
    :limit