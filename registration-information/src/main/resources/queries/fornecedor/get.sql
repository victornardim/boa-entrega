SELECT
    id,
    endereco_id,
    nome_fantasia,
    razao_social,
    cnpj
FROM
    fornecedor
WHERE
    id = :id