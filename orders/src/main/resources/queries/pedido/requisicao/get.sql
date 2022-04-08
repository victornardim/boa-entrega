SELECT
    requisicao_id,
    pedido_id
FROM
    pedido_requisicao
WHERE
    requisicao_id = :requisicaoId