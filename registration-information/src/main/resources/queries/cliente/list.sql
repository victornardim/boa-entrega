SELECT
    id,
    endereco_id,
    nome,
    cpf,
    telefone,
    email
FROM
    cliente
OFFSET
    :offset
LIMIT
    :limit