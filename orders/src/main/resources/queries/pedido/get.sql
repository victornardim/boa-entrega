SELECT
    id,
    codigo_rastreio,
    "data",
    cliente_id
FROM
    pedido
WHERE
    id = :id