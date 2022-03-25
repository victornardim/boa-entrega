package service

import (
	"errors"
	"time"

	"authenticator/internal/model"
	"authenticator/internal/repository"

	"github.com/golang-jwt/jwt"
)

const (
	expire = 86400 // one day
	issuer = "lOt8Se2zjaDCjP6ZxEEnBd7cLBVyJR9V"
	secret = "4iy3XCOvuqXJN5tI5oiBOVDaqkvF9g4S"
)

func Authenticate(credentials *model.Credentials) (*string, error) {
	exists, err := repository.CredentialsExists(credentials)
	if err != nil {
		return nil, err
	}

	if !exists {
		return nil, errors.New("user or password incorrect")
	}

	jwt, err := generateToken(credentials)
	if err != nil {
		return nil, err
	}

	return jwt, nil
}

func generateToken(credentials *model.Credentials) (*string, error) {
	token := jwt.NewWithClaims(jwt.SigningMethodHS512, jwt.MapClaims{
		"sub": credentials.Username,
		"iss": issuer,
		"exp": time.Now().Unix() + expire,
	})

	jwt, err := token.SignedString([]byte(secret))
	if err != nil {
		return nil, err
	}

	return &jwt, nil
}
