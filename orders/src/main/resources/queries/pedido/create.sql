INSERT INTO
    pedido (
        id,
        codigo_rastreio,
        "data",
        cliente_id
    )
VALUES
    (
        :id,
        :codigoRastreio,
        now() at time zone 'utc',
        :clienteId
    )