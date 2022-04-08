INSERT INTO
    pedido_andamento (
        id,
        pedido_id,
        "data",
        status
    )
VALUES
    (
        :id,
        :pedidoId,
        now() at time zone 'utc',
        :status
    )
