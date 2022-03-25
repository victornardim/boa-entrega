package controller

import (
	"net/http"

	"authenticator/internal/model"
	"authenticator/internal/service"

	"github.com/labstack/echo/v4"
)

func Authenticate(context echo.Context) error {
	form := &model.Credentials{}

	err := context.Bind(form)
	if err != nil {
		return model.ResponseError(context, model.NewError(err.Error()))
	}

	jwt, err := service.Authenticate(form)
	if err != nil {
		return model.ResponseError(context, model.NewError(err.Error()))
	}

	return context.String(http.StatusCreated, *jwt)
}
