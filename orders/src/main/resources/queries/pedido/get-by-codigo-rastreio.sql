SELECT
    id,
    codigo_rastreio,
    "data",
    cliente_id
FROM
    pedido
WHERE
    codigo_rastreio = :codigoRastreio