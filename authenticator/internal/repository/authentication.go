package repository

import (
	"authenticator/internal/model"
	"authenticator/internal/util"

	"github.com/go-pg/pg"
)

func CredentialsExists(credentials *model.Credentials) (bool, error) {
	database := pg.Connect(&pg.Options{
		User:     "postgres",
		Password: "postgres",
		Database: "authenticator",
		Addr:     "172.17.0.1:5432",
	})
	defer database.Close()

	query := `
		SELECT
			senha
		FROM
			credenciais
		WHERE
			usuario = ?
	`

	var storedPassword string
	_, err := database.QueryOne(&storedPassword, query, credentials.Username)
	if err != nil {
		return false, err
	}

	credentialsExists := (util.Encrypt(credentials.Password) == storedPassword)

	return credentialsExists, nil
}
