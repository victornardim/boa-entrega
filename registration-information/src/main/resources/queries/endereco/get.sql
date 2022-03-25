SELECT
    id,
    tipo,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    uf,
    cep,
    latitude,
    longitude
FROM
    endereco
WHERE
    id = :id