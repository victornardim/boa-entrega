package model

import (
	"net/http"
	"time"

	"github.com/labstack/echo/v4"
)

const format = "2006-01-02T15:04:05.999999Z07:00"

type Error struct {
	Message   string `json:"message"`
	Timestamp string `json:"timestamp"`
}

func NewError(message string) *Error {
	return &Error{
		Message:   message,
		Timestamp: time.Now().Format(format),
	}
}

func ResponseError(context echo.Context, err *Error) error {
	return context.JSON(http.StatusUnauthorized, err)
}
