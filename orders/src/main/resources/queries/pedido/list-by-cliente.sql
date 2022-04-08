SELECT
    id,
    codigo_rastreio,
    "data",
    cliente_id
FROM
    pedido
WHERE
    cliente_id = :clienteId
OFFSET
    :offset
LIMIT
    :limit