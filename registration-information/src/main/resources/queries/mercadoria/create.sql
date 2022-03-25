INSERT INTO
    mercadoria (
        id,
        fornecedor_id,
        deposito_id,
        nome,
        tipo,
        codigo_barras,
        data_validade,
        quantidade
    )
VALUES
    (
        :id,
        :fornecedorId,
        :depositoId,
        :nome,
        :tipo,
        :codigoBarras,
        :dataValidade,
        :quantidade
    )