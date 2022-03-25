package main

import (
	"authenticator/internal/controller"

	"github.com/labstack/echo/v4"
)

func main() {
	server := echo.New()

	server.POST("/authenticate", controller.Authenticate)

	server.Start(":1323")
}
