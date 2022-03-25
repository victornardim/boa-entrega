SELECT
    id,
    endereco_id,
    tipo,
    capacidade,
    capacidade_tipo
FROM
    deposito
WHERE
    id = :id