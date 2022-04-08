SELECT
    pedido_id,
    mercadoria_id,
    quantidade
FROM
    pedido_mercadoria
WHERE
    pedido_id = :pedidoId