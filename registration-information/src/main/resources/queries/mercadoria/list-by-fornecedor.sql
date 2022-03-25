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
    fornecedor_id = :fornecedorId
OFFSET
    :offset
LIMIT
    :limit