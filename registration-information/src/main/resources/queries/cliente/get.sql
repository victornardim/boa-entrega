SELECT
    id,
    endereco_id,
    nome,
    cpf,
    telefone,
    email
FROM
    cliente
WHERE
    id = :id