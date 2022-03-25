SELECT
    id,
    fornecedor_id,
    deposito_id,
    nome,
    tipo,
    codigo_barras,
    data_validade,
    quantidade
FROM
    mercadoria
WHERE
    deposito_id = :depositoId
OFFSET
    :offset
LIMIT
    :limit