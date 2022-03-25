UPDATE
    mercadoria
SET
    fornecedor_id = :fornecedorId,
    deposito_id = :depositoId,
    nome = :nome,
    tipo = :tipo,
    codigo_barras = :codigoBarras,
    data_validade = :dataValidade,
    quantidade = :quantidade
WHERE
    id = :id