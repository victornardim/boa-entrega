SELECT
    id,
    codigo_rastreio,
    "data",
    cliente_id
FROM
    pedido
WHERE
    id IN (
        SELECT
          pedido_id
        FROM (
          SELECT
          	pedido_id,
          	status,
            row_number() over (partition by pedido_id order by data desc) as row_number
          FROM
            pedido_andamento
        ) andamento
        WHERE
            row_number = 1 AND
            status = :status
        OFFSET
            :offset
        LIMIT
            :limit
    )