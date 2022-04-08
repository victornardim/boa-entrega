SELECT
    id,
    "data",
    status
FROM
    pedido_andamento
WHERE
    pedido_id = :pedidoId